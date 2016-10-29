package com.bank.common.dto;

import java.io.Serializable;

public class PrivilegeDTO implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -8517521178912625151L;

    private Integer id;
    private String displayName;
    private String privilegeName;
    private String privilegeCode;
    private String url;
    private Integer parentId;
    private Integer roleId;
    private String roleCode;
    private Integer roleName;
    private String parentName;
    private Boolean isDeleted;
    private String createdTime;
    private String updatedTime;

    public PrivilegeDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleName() {
        return roleName;
    }

    public void setRoleName(Integer roleName) {
        this.roleName = roleName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PrivilegeDTO [id=");
        builder.append(id);
        builder.append(", displayName=");
        builder.append(displayName);
        builder.append(", privilegeName=");
        builder.append(privilegeName);
        builder.append(", privilegeCode=");
        builder.append(privilegeCode);
        builder.append(", url=");
        builder.append(url);
        builder.append(", parentId=");
        builder.append(parentId);
        builder.append(", roleId=");
        builder.append(roleId);
        builder.append(", roleCode=");
        builder.append(roleCode);
        builder.append(", roleName=");
        builder.append(roleName);
        builder.append(", parentName=");
        builder.append(parentName);
        builder.append(", isDeleted=");
        builder.append(isDeleted);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", updatedTime=");
        builder.append(updatedTime);
        builder.append("]");
        return builder.toString();
    }




}
