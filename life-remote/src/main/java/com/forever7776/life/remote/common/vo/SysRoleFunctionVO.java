package com.forever7776.life.remote.common.vo;

import java.io.Serializable;

/**
 * 角色-功能-权限
 *
 * @author KZ
 * @date 2017-12-21
 */
public class SysRoleFunctionVO implements Serializable {

    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 功能ID
     */
    private String functionId;
    /**
     * 功能所对应的url
     */
    private String functionUrl;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    @Override
    public String toString() {
        return "SysRoleFunctionVO{" +
                "roleId='" + roleId + '\'' +
                ", functionId='" + functionId + '\'' +
                ", functionUrl='" + functionUrl + '\'' +
                '}';
    }
}
