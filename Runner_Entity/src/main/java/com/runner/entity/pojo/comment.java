package com.runner.entity.pojo;

/**
 * @Description: TODO
 * @Author FCJ
 * @Date 2020/8/20 16:00
 * @Version V1.0
 */
public class comment {
    private Integer id;
    private Integer did;
    private String content;
    private Integer uid;
    private Integer praise;

    public comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }
}
