package com.runner.entity.pojo;

/**
 * @Description: TODO
 * @Author FCJ
 * @Date 2020/8/20 15:58
 * @Version V1.0
 */
public class attention {
    private Integer id;
    private Integer myuid;
    private Integer otherid;
    private Integer flag;

    public attention() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMyuid() {
        return myuid;
    }

    public void setMyuid(Integer myuid) {
        this.myuid = myuid;
    }

    public Integer getOtherid() {
        return otherid;
    }

    public void setOtherid(Integer otherid) {
        this.otherid = otherid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
