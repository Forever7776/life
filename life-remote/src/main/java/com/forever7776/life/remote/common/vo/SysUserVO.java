package com.forever7776.life.remote.common.vo;

import java.io.Serializable;

/**
 * @author kz
 * @date 2017-12-19
 */
public class SysUserVO implements Serializable {

    private String userName;

    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "SysUserVO{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
