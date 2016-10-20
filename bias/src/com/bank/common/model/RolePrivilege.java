package com.bank.common.model;

import java.io.Serializable;
import java.util.Date;

public class RolePrivilege implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -2321717860611927120L;

    private Integer roleId;

    private Integer privilegeId;

    private Date createdTime;

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return this.privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RolePrivilege [roleId=");
        builder.append(roleId);
        builder.append(", privilegeId=");
        builder.append(privilegeId);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append("]");
        return builder.toString();
    }

}
