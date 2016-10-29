package com.bank.common.dto;

import java.io.Serializable;

public class RoleSetPrivilegeDTO implements Serializable {

    private static final long serialVersionUID = -685880548423581225L;

    private Integer privilegeId;
    private Integer parentPrivilegeId;
    private String privilegeDisplayName;
    private Integer id;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Integer getParentPrivilegeId() {
        return parentPrivilegeId;
    }

    public void setParentPrivilegeId(Integer parentPrivilegeId) {
        this.parentPrivilegeId = parentPrivilegeId;
    }

    public String getPrivilegeDisplayName() {
        return privilegeDisplayName;
    }

    public void setPrivilegeDisplayName(String privilegeDisplayName) {
        this.privilegeDisplayName = privilegeDisplayName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime
                * result
                + ((parentPrivilegeId == null) ? 0 : parentPrivilegeId
                        .hashCode());
        result = prime
                * result
                + ((privilegeDisplayName == null) ? 0 : privilegeDisplayName
                        .hashCode());
        result = prime * result
                + ((privilegeId == null) ? 0 : privilegeId.hashCode());
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
        RoleSetPrivilegeDTO other = (RoleSetPrivilegeDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (parentPrivilegeId == null) {
            if (other.parentPrivilegeId != null)
                return false;
        } else if (!parentPrivilegeId.equals(other.parentPrivilegeId))
            return false;
        if (privilegeDisplayName == null) {
            if (other.privilegeDisplayName != null)
                return false;
        } else if (!privilegeDisplayName.equals(other.privilegeDisplayName))
            return false;
        if (privilegeId == null) {
            if (other.privilegeId != null)
                return false;
        } else if (!privilegeId.equals(other.privilegeId))
            return false;
        return true;
    }

}