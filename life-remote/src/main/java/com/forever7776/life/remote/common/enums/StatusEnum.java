package com.forever7776.life.remote.common.enums;

/**
 * @author kz
 * @date 2017-12-21
 */
public enum StatusEnum {

    NORMAL(0, "正常"),
    DELETE(1, "刪除");

    private Integer key;
    private String desc;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    StatusEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
