package com.runner.pay.config;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-12 16:54
 */
public class PayConfig {

    //支付宝相关配置
    public static final String ALIPAY_APPID="2017091608770636";
    public static final String ALIPAY_PRIVATEKEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCh1qI8uo1qhrcePsa5JUAoYUX8HfPuBt7kc90aCP1M/v61/uzaU/lyGQeChKV3jdDTn2Lcq6kT5JBl3TLiaYHmO6cId1nQAIUxiT9zhB9crc4wAx8CVabMbcqUefs7Xsp+YhhUgU5X6GOS3emkUeL7RegBnL8vayfEBeUDgBxsk/K/VygBA8sapsEhnoOrB6bhMY4GaJrxb0kg9Ej8x4kpExLcxkT+UgcOiJvh6vpBZo5CJsiPQkFvSsNsWY2uSDudSL/KqpMxz+yPfVvZDt4fOfyi+CfYR43Jlo4tsT7joqH2JT06BH+KdJyc1D3Lqw7w/WdmZtmoLghH0kRZawrLAgMBAAECggEAYYtpm+rhQ7zQ8HTr+DogknYW5Z/0H5qai93d/Uw/yEHFqlJt1iZZKlE1upBS311l6beesdzxeuD/u7X4bokjV27K/YpaYsl9fl74FJslAApuRXgMH68aawsd2CIxsBYxPL3JZl3Np6SVJ7eDlJwakFMRRK+CeIVAoaDf6R01hKctkYnnE0wT+ffQNKWsISoEyiKVT3g5fur7iPOuDlDXsfi6Mm+e75wCXTmRRHmb8lPBAMLV+Kj5DFxg8dwNz81Fs4ZM2Aq0lBaTfy1H1zSlM1m42wcsMYDcgdEH9aq+OgqK+cny6umgs7/Alg7IgV/9b7AhKdvAqLy2ERUJtooj2QKBgQDeIoDW3HuTq7sBaBnu63f7icT2RM3fApfOiGM4UDtxPvc5dS5S//o3E8p+rbp21FfBeyLOJFd9dg/eu+ETA+63QMPw4Kq4AH/EA5AFohaOQ0IKFDjYyxfyD8ajA4USDwdiaW2/vmMeAtGSv+W5zWb9/t49LOTwzEW904+yOGcmhQKBgQC6guDZ0Ob4o9nx5XwZXEe2di4MupARHceGzmolyDvs3Qi/w+8QntrDvfqIJoqoxOG5NVi3jtjkqtJtMaPyxqNWTabWOOTLbrsqlvPUmeCl0j3FVFKAGcV7/b9XkLvh1DtnIe6rhhZCVB4e4bL/katpOTgulhmSMaWIaztGU0F1DwKBgQCTeobdn/6vuSlsMqhdFppPN1W8R0wDjt4o8iYlwibk9e//hswdsPN307zyQ/dzY2FsBIvEHx6zHkpFD6nMDSVVJzuv1gmiJjqtccwR4V5mT0MuG+TuElCwlkbD/ddAeRfm/6Ys0oNN7oMjkiI8LKH/alI0fXT2Zji7YhWaNpZNXQKBgEU6q0duWS1VdGJrcgLf0+aQO0uSPEN+MD+Dgrb/ee7TpJm5mpUqwb0CWWoMFE/MtJRQjtujdDJ8jZrmYBqPTLWOIS1G9PXl5idK3Lq/Wzlxrmf+gpj19+2sJEfWe0a5xkrjt3mHTd/U5VFFKXHfmiZ2jLoOEPPI5c6bLudNo/BVAoGBAMvwRxLO4xb11Ip4rnEHkw3Qn8lrddoC3/m7haHYZ5DyGe8wdCdEi6wyk5MvlNQdqdVg5bqV0AiotIBcd5Pemabun2WaB11h/6SSb6wKY4Fnz+H155zaEww4no9BTG9llqQV7H8AS77dN1bxhcpE/MGFoB9JFU0D+BwXAnth4z1u";
    public static final String ALIPAY_PUBLICKEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx7jJT+PSEM6ZiimTW0SGUfg4cJU04H/mQqkL2mk7KaHXFQqMh4US6xYkDlaEXzOOfxevuBqWOaB4/8TleO1CHZHXWHu9Xc+iYtJPNJGrxoGLM+6Cg9IafJTygRoaqdH0SoVMpxFdOpUftNdXHO+G0ZpS/7c1zpn8G64zN5J17IFrLcUlsEnSgOrJxsS2Q50b44er0KQlj76pehB2sTveHS2vdhqXzrv+oq99XtUKEY1a3nwDjXneI7YYKLHD9KU53pti/ibLDkOEjO4+DRowd+wfSwkmWGVL3X320mvCfrg/aMN71B/cyyhW0mQ4cxqh2UcnpxLm0v/+uC7dSCyAJwIDAQAB";

    //微信相关配置
    public static String APP_ID = "wx632c8f211f8122c6";
    public static String MCH_ID = "1497984412";
    public static String API_KEY = "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";
    public static String NOTIFY_URL = "http://localhost:8085/";

    //常用API接口
    public static String WXPAY_UNIFIE = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static String WXPAY_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
    public static String WXPAY_CLOSE = "https://api.mch.weixin.qq.com/pay/closeorder";
    public static String WXPAY_BILL = "https://api.mch.weixin.qq.com/pay/downloadbill";

}
