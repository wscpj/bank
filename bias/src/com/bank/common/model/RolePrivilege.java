package com.bank.common.model;

import java.io.Serializable;

public class RolePrivilege implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2321717860611927120L;

    private Integer id;
    private Integer roleId;
    private Integer privilegeId;
    private Boolean isDeleted;
    private String createdTime;
    private String updatedTime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getPrivilegeId() {
        return privilegeId;
    }
    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isDeleted == null) ? 0 : isDeleted.hashCode());
        result = prime * result + ((privilegeId == null) ? 0 : privilegeId.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result + ((updatedTime == null) ? 0 : updatedTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RolePrivilege other = (RolePrivilege) obj;
        if (createdTime == null) {
            if (other.createdTime != null)
                return false;
        } else if (!createdTime.equals(other.createdTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isDeleted == null) {
            if (other.isDeleted != null)
                return false;
        } else if (!isDeleted.equals(other.isDeleted))
            return false;
        if (privilegeId == null) {
            if (other.privilegeId != null)
                return false;
        } else if (!privilegeId.equals(other.privilegeId))
            return false;
        if (roleId == null) {
            if (other.roleId != null)
                return false;
        } else if (!roleId.equals(other.roleId))
            return false;
        if (updatedTime == null) {
            if (other.updatedTime != null)
                return false;
        } else if (!updatedTime.equals(other.updatedTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RolePrivilege [id=");
        builder.append(id);
        builder.append(", roleId=");
        builder.append(roleId);
        builder.append(", privilegeId=");
        builder.append(privilegeId);
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
