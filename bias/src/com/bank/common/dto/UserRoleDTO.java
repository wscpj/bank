package com.bank.common.dto;

import net.sf.oval.constraint.NotNull;

public class UserRoleDTO {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer roleId;

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserRoleDTO [userId=");
        builder.append(userId);
        builder.append(", roleId=");
        builder.append(roleId);
        builder.append("]");
        return builder.toString();
    }

}
