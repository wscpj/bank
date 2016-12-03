package com.bank.common.model;

import java.io.Serializable;

public class Depositor implements Serializable{
    /**
     * 储户表
     */
    private static final long serialVersionUID = 3085123657410621407L;

    private Integer id;

    private String depositorName;// 储户姓名

    private String cardNum;// 身份证号

    private String depositorCode;// 储户编号

    private Integer gender;// 储户性别

    private String birthday;// 储户生日

    private String joinTime;// 加入时间

    private Integer politicalStatus;// 政治面貌

    private String duty;// 担当职务

    private String workTime;// 任职时间

    private Integer creditLevel;// 信用等级

    private String telephone;// 固定电话

    private String mobilePhone;// 手机

    private String introducer;// 介绍人

    private String address;// 成员住址

    private String image;// 储户个人照片

    private Integer orgId;// 机构ID

    private String orgName;//机构名称

    private Boolean isDeleted = false;

    private String createdTime;

    private String updatedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepositorName() {
        return depositorName;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName == null ? null : depositorName.trim();
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum == null ? null : cardNum.trim();
    }

    public String getDepositorCode() {
        return depositorCode;
    }

    public void setDepositorCode(String depositorCode) {
        this.depositorCode = depositorCode == null ? null : depositorCode.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime == null ? null : joinTime.trim();
    }

    public Integer getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(Integer politicalStatus) {
        this.politicalStatus = politicalStatus;
    }



    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public Integer getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(Integer creditLevel) {
        this.creditLevel = creditLevel;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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

    public String getIntroducer() {
        return introducer;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}