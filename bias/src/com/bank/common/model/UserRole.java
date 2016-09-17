package com.bank.common.model;

import java.util.Date;

public class UserRole {

    private Integer userId;
    private Integer roleId;
    private Date createdTime;

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

    
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserRole [userId=");
        builder.append(userId);
        builder.append(", roleId=");
        builder.append(roleId);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append("]");
        return builder.toString();
    }

}
