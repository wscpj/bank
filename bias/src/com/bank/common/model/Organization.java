package com.bank.common.model;

import java.io.Serializable;

public class Organization implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 48294060246191737L;
    private Integer id;
    private String organizationName;
    private String organizationCode;
    private String createdTime;
    private String updatedTime;
    private Boolean isDeleted;

    public Organization() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Organization [id=");
        builder.append(id);
        builder.append(", organizationName=");
        builder.append(organizationName);
        builder.append(", organizationCode=");
        builder.append(organizationCode);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", updatedTime=");
        builder.append(updatedTime);
        builder.append(", isDeleted=");
        builder.append(isDeleted);
        builder.append("]");
        return builder.toString();
    }

}
