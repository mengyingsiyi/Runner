package com.runner.userservice.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.runner.cache.exception.CacheException;
import com.runner.commons.constant.SystemConstant;
import com.runner.commons.dto.UserCodeLoginDto;
import com.runner.commons.dto.UserLoginDto;
import com.runner.commons.dto.UserRegisterDto;
import com.runner.commons.dto.userDto.UpdatePasswordDto;
import com.runner.commons.jwt.JwtUtil;
import com.runner.commons.sms.AliySmsUtil;
import com.runner.commons.util.*;
import com.runner.commons.vo.R;
import com.runner.entity.pojo.User;
import com.runner.userservice.dao.UserDao;
import com.runner.userservice.service.CacheService;
import com.runner.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @paragram: Runner
 * @author: wangyang
 * @create: 2020/8/19 21:48
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private CacheService cacheService;

    @Value("${runner.pass.prikey}")
    private String key;

    /**
     * 检测手机号是否注册过
     * @param tel 要检测的手机号
     * @return
     */
    @Override
    public R selectByTel(String tel) {
        if (StringUtil.checkStr(tel)) {
            if (null == userDao.selectByTel(tel)) {
                return R.ok("未注册过");
            }
            return R.fail("该手机号已被注册，换号重试" );
        }
        return R.fail("输入手机号");
    }

    /**
     * 用户注册
     * @param registerDto 封装的用于注册的用户的信息
     * @return
     */
    @Override
    @Transactional
    public R register(UserRegisterDto registerDto) {
        //判断手机哈是否可用
        if (selectByTel(registerDto.getUTel()).getCode()==10000) {
            //手机号未被注册  可以使用
            //设置密码为密文
            registerDto.setUPassword(EncryptUtil.rsaEnc(key,registerDto.getUPassword()));
            System.out.println(registerDto.getUPassword());
            User user = BeanUtilCopy.copy(User.class,registerDto);
            if (userDao.register(user) > 0){
                return R.ok("注册好了，滚去登录");
            }
            return R.ok("注册失败，原因不明");
        }
        return R.fail("狗子，换号，这个用过了");
    }

    /**
     * 密码登录
     * @param loginDto 封装的登录的用户信息
     * @return
     * @throws CacheException
     */
    @Override
    public R login(UserLoginDto loginDto) throws CacheException {
        //如果缓存中没有   则未登录状态
        if (!cacheService.check(SystemConstant.USER_PHONE+loginDto.getUTel().trim())) {
            //校验账户是否存在
            User user = userDao.selectByTel(loginDto.getUTel());
            //System.out.println(user);
            if (user!=null) {
                //说明有这个账户，可以进行密码对比
                if (user.getUPassword().equals(EncryptUtil.rsaEnc(key,loginDto.getUPassword()))) {
                    //密码正确   登陆成功   信息存到redis中
                    String token = JwtUtil.creatJwt(user.getUTel().trim());
                    cacheService.set(SystemConstant.USER_PHONE+loginDto.getUTel(),SystemConstant.TOKEN_TIME,user.getUTel());
                    cacheService.set(SystemConstant.USER_TOKEN+token,SystemConstant.TOKEN_TIME,JSON.toJSONString(user));
                    return R.ok("登好了，滚去耍吧",token);
                }
                return R.fail("密码都不对，给爷爬");
            }
            return R.fail("号都木有，滚去注册");
        }
        return R.fail("都登录过了还登   闹呢？");
    }
        /*if (jedisCore.check(RedisKeyConfig.PHONE_FOR+loginDto.getUTel())){
            //账号被冻结
            return R.fail("狗子，你号被封了"+jedisCore.ttl(RedisKeyConfig.PHONE_FOR+loginDto.getUTel())+"后再玩儿吧");
        } else if (jedisCore.check(RedisKeyConfig.PHONE_TOKEN+loginDto.getUTel())) {
            //令牌已经存在，登陆过了
            return R.fail("你已经登过了  滚 ");
        } else{
            //校验数据库
            User user = userDao.selectByTel(loginDto.getUTel());
            boolean isfalse = true;
            if (user!= null) {
                //说明有这个账号  开始比较密码
                if (user.getUPassword().equals(EncryptUtil.rsaEnc(key,loginDto.getUPassword()))){
                    //密码也对了   准许登录    并把登录信息存储到redis中
                    String token = TokenUtil.createToken(user.getUId());
                    jedisCore.set(RedisKeyConfig.PHONE_TOKEN+loginDto.getUTel(),token,RedisKeyConfig.TOKEN_TIME);
                    jedisCore.set(RedisKeyConfig.TOKEN_USER+token, JSON.toJSONString(user),RedisKeyConfig.TOKEN_TIME);
                    isfalse = true;
                    return R.ok("狗子，耍去吧",token);
                }
            }
            if (isfalse) {
                //登录失败，校验次数，十分钟三次则封号
                //看是不是十分钟内已经错误两次了
                if (jedisCore.keys(RedisKeyConfig.PHONE_ERROR+loginDto.getUTel())==2) {
                    //错误两次，冻结三十分钟
                    jedisCore.set(RedisKeyConfig.PHONE_FOR+loginDto.getUTel(),System.currentTimeMillis()+"",RedisKeyConfig.TOKENFOR_TIME);
                }
                //错误不到三次，记录本次错误
                jedisCore.set(RedisKeyConfig.PHONE_ERROR+loginDto.getUTel()+":"+System.currentTimeMillis(),"",RedisKeyConfig.PHONERROR_TIME);
            }
            //错误次数限定，10分钟，三次，冻结账号30分钟
            R.fail("账号密码错了   给老子爬");
        }
        return null;
    }*/


    /**
     * 验证码登录
     * @param codeLoginDto 验证码登录封装的登陆手机号  和验证码
     * @return
     */
    @Override
    public R loginCode(UserCodeLoginDto codeLoginDto) {
        boolean check = cacheService.check(SystemConstant.USER_CODE + codeLoginDto.getUTel());
        System.out.println(check);
        if (true==check) {
            //缓存里没有这个验证码  需要发送验证码  然后和cache里边的验证码对比
            /*String code = cacheService.get(SystemConstant.USER_CODE + codeLoginDto.getUTel());
            String substring = code.substring(1);
            String substring1 = substring.substring(0,substring.length()-1);*/
            String string1 = cacheService.get(SystemConstant.USER_CODE + codeLoginDto.getUTel()).replaceAll("\"", "");
            if (codeLoginDto.getCode().equals(string1)){
                //说明验证码验证通过，存到令牌中，然后存入缓存中
                String token = JwtUtil.creatJwt(codeLoginDto.getUTel());
                User user = userDao.selectByTel(codeLoginDto.getUTel());
                cacheService.set(SystemConstant.USER_PHONE+user.getUTel(),SystemConstant.TOKEN_TIME,user.getUTel());
                cacheService.set(SystemConstant.USER_TOKEN+token,SystemConstant.TOKEN_TIME, JSON.toJSONString(user));
                return R.ok("登了，耍吧");
            }
            return R.fail("验证码都给整错了，还能干点啥");
        }
        return R.fail("先获取验证码吧");
    }

    /**
     * 制作个验证码  用于短信验证
     * @param tel  要发送到的手机号
     * @return
     */
    @Override
    public R madeCode(String tel) {
        if (null!=userDao.selectByTel(tel)) {
            int code = NumUtil.creatCode(6);
            System.out.println("验证码是------------------->"+code);
            if (AliySmsUtil.sendSmsCode(tel,code)) {
                cacheService.set(SystemConstant.USER_CODE+tel,SystemConstant.MAIL_CODE_TIME,String.valueOf(code));
                return R.ok("发送了，眼睛盯紧点",code);
            }
                return R.fail("发送失败，等下别急");
        }
        return R.fail("还没注册嘞  滚犊子");
    }

    /**
     * 退出登录
     * @param token 已登录账户的token
     * @return
     */
    @Override
    public R logout(String token) {
        //判断令牌是否存在，不存在直接提示已退出
        if (cacheService.check(SystemConstant.USER_TOKEN + token)) {
            System.out.println("redis里存在token--------------------------->" + token);
            User user = JSON.parseObject(cacheService.get(SystemConstant.USER_TOKEN + token), User.class);
            System.out.println("获取到的User------------------>" + user);
            if (null != user) {
                //删除redis里的user信息
                cacheService.del(SystemConstant.USER_PHONE + user.getUTel());
                cacheService.del(SystemConstant.USER_TOKEN + token);
                return R.ok("退了退了");
            }
        }
        return R.fail("都退八百年了，还退个毛线啊");
        /*if (StringUtil.checkStr(token)){
            System.out.println("a");
            User user = JSON.parseObject(cacheService.get(SystemConstant.USER_TOKEN + token), User.class);
            System.out.println("logout_____"+user);
            if (null!=user) {
                //删除redis里的user信息
                cacheService.del(SystemConstant.USER_PHONE+user.getUTel());
                cacheService.del(SystemConstant.USER_TOKEN+token);
                R.ok("退了退了");
            }

        }
        return R.fail("都退八百年了，还退个毛线啊");
    }*/
    }

    @Override
    public R updatePassword(UpdatePasswordDto passwordDto) {
        //判断用户输入的验证码和redis的验证码是否一致
        String code = cacheService.get(SystemConstant.USER_CODE + passwordDto.getUTel()).replaceAll("\"", "");

        if (passwordDto.getCode().equals(code)){
            //验证码核对好   先把用户输入的密码转换成密文
            passwordDto.setUPassword(EncryptUtil.rsaEnc(key,passwordDto.getUPassword()));
            //修改密码操作
            if (userDao.uodatePassword(passwordDto)>0){
                //修改成功
                return R.ok("改好了 滚");
            }
        }
        return R.fail("发动机发生故障,修改失败");
    }

}
