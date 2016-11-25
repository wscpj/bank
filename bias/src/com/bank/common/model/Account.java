package com.bank.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {

    private static final long serialVersionUID = -8536544572064446926L;
    private Integer id;
    private String accountNum;// 卡号
    private Integer opener;// 开户人
    private Integer cardType;// 卡类型
    private String openTime;
    private String bankBook;// 存折号
    private Integer accountType;// 账户类型(一般账户和股东账户)
    private Integer cardMark;// 卡标志
    private String payPasssWord;// 支付密码
    private Integer accountStatus;//
    private Integer depositorId;// 储户ID
    private BigDecimal remainMoney;// 账户余额
    private String interestStartTime;// 起息日期
    private String interestStopTime;// 结息日期
    private Integer rateId;// 利率ID（由账户类型，类型，存钱时间决定）
    private String constantCode;// 存款定期编号
    private final Boolean isDeleted = false;
    private String createdTime;//
    private String updatedTime;//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public Integer getOpener() {
        return opener;
    }

    public void setOpener(Integer opener) {
        this.opener = opener;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getBankBook() {
        return bankBook;
    }

    public void setBankBook(String bankBook) {
        this.bankBook = bankBook;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getCardMark() {
        return cardMark;
    }

    public void setCardMark(Integer cardMark) {
        this.cardMark = cardMark;
    }

    public String getPayPasssWord() {
        return payPasssWord;
    }

    public void setPayPasssWord(String payPasssWord) {
        this.payPasssWord = payPasssWord;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
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

    public Integer getDepositorId() {
        return depositorId;
    }

    public void setDepositorId(Integer depositorId) {
        this.depositorId = depositorId;
    }

    public BigDecimal getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(BigDecimal remainMoney) {
        this.remainMoney = remainMoney;
    }

    public String getInterestStartTime() {
        return interestStartTime;
    }

    public void setInterestStartTime(String interestStartTime) {
        this.interestStartTime = interestStartTime;
    }

    public String getInterestStopTime() {
        return interestStopTime;
    }

    public void setInterestStopTime(String interestStopTime) {
        this.interestStopTime = interestStopTime;
    }

    public Integer getRateId() {
        return rateId;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public String getConstantCode() {
        return constantCode;
    }

    public void setConstantCode(String constantCode) {
        this.constantCode = constantCode;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

}
