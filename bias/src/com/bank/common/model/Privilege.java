package com.bank.common.model;

import java.io.Serializable;


public class Privilege implements Serializable{

    private static final long serialVersionUID = -4038295529366581455L;

    private Integer id;
    private String displayName;
    private String privilegeName;
    private String privilegeCode;
    private String url;
    private Integer parentId;
    private String parentName;
    private Boolean isDeleted;
    private String createdTime;
    private String updatedTime;
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

    public String getParentName() {
        return parentName;
    }
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((createdTime == null) ? 0 : createdTime.hashCode());
        result = prime * result
                + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((isDeleted == null) ? 0 : isDeleted.hashCode());
        result = prime * result
                + ((parentId == null) ? 0 : parentId.hashCode());
        result = prime * result
                + ((privilegeCode == null) ? 0 : privilegeCode.hashCode());
        result = prime * result
                + ((privilegeName == null) ? 0 : privilegeName.hashCode());
        result = prime * result
                + ((updatedTime == null) ? 0 : updatedTime.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        Privilege other = (Privilege) obj;
        if (createdTime == null) {
            if (other.createdTime != null)
                return false;
        } else if (!createdTime.equals(other.createdTime))
            return false;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
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
        if (parentId == null) {
            if (other.parentId != null)
                return false;
        } else if (!parentId.equals(other.parentId))
            return false;
        if (privilegeCode == null) {
            if (other.privilegeCode != null)
                return false;
        } else if (!privilegeCode.equals(other.privilegeCode))
            return false;
        if (privilegeName == null) {
            if (other.privilegeName != null)
                return false;
        } else if (!privilegeName.equals(other.privilegeName))
            return false;
        if (updatedTime == null) {
            if (other.updatedTime != null)
                return false;
        } else if (!updatedTime.equals(other.updatedTime))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Privilege [id=");
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
