package com.bank.common.dto;

import java.io.Serializable;
import java.util.Date;

import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.NotNull;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 507787094734167396L;

    private Integer id;

    @NotNull
    @MaxLength(45)
    private String userName;

    @NotNull
    @MaxLength(32)
    private String password;

    @MaxLength(45)
    private String firstName;

    @MaxLength(45)
    private String lastName;

    private String originalIcon;
    private String icon;
    private String iconBase64;
    private String smallIcon;
    private Boolean deleted;

    private Date createdTime;
    private Date updatedTime;

    // @NotNull(when = "groovy:_this.isTeacher = true")
    private Integer orgId;

    private Boolean isTeacher;

    private String inOrgId;
    private String email;

    private String roleName;

    private String score;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOriginalIcon() {
        return originalIcon;
    }

    public void setOriginalIcon(String originalIcon) {
        this.originalIcon = originalIcon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconBase64() {
        return iconBase64;
    }

    public void setIconBase64(String iconBase64) {
        this.iconBase64 = iconBase64;
    }

    public String getSmallIcon() {
        return this.smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return this.updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getInOrgId() {
        return inOrgId;
    }

    public void setInOrgId(String inOrgId) {
        this.inOrgId = inOrgId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserDTO [id=");
        builder.append(id);
        builder.append(", userName=");
        builder.append(userName);
        builder.append(", password=");
        builder.append(password);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append(", originalIcon=");
        builder.append(originalIcon);
        builder.append(", icon=");
        builder.append(icon);
        builder.append(", iconBase64=");
        builder.append(iconBase64);
        builder.append(", smallIcon=");
        builder.append(smallIcon);
        builder.append(", deleted=");
        builder.append(deleted);
        builder.append(", createdTime=");
        builder.append(createdTime);
        builder.append(", updatedTime=");
        builder.append(updatedTime);
        builder.append(", orgId=");
        builder.append(orgId);
        builder.append(", isTeacher=");
        builder.append(isTeacher);
        builder.append(", inOrgId=");
        builder.append(inOrgId);
        builder.append(", email=");
        builder.append(email);
        builder.append(", roleName=");
        builder.append(roleName);
        builder.append(", score=");
        builder.append(score);
        builder.append("]");
        return builder.toString();
    }

}
