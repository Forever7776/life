package com.forever7776.life.core.entity.sys;

public class SysRole {
    private String id;

    private String roleCode;

    private String roleName;

    private Integer status;

    private String roleteMb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleteMb() {
        return roleteMb;
    }

    public void setRoleteMb(String roleteMb) {
        this.roleteMb = roleteMb == null ? null : roleteMb.trim();
    }
}