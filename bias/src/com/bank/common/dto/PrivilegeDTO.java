package com.bank.common.dto;


public class PrivilegeDTO {

    private Integer id;
    private String name;
    private String createdTime;
    private String code;
    private String type;
    private Integer parentId;
    private String roleCode;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
        builder.append(", name=");
        builder.append(name);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", code=");
        builder.append(code);
        builder.append(", type=");
        builder.append(type);
        builder.append(", parentId=");
        builder.append(parentId);
        builder.append(", roleCode=");
        builder.append(roleCode);
        builder.append("]");
        return builder.toString();
    }
}
