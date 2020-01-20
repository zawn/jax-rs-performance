package org.example.rest.jaxrs;

import java.util.Date;

public class Account {
    private Integer userId;

    private String phone;

    private String userName;

    private String realName;

    private String identyCode;

    private String licenseCode;

    private String gender;

    private String avatar;

    private String imId;

    private String roleCodes;

    private String roleNames;

    private String phaseCode;

    private String coachCode;

    private String cityCode;

    private String cityName;

    private String schoolCode;

    private String schoolName;

    private String bankCardCode;

    private Short account;

    private Short auditState;

    private String createdBy;

    private String createdFrom;

    private Date createdTime;


    public Account() {
    }

    public Account(Account account) {
        this.setUserId(account.getUserId());
        this.setPhone(account.getPhone());
        this.setUserName(account.getUserName());
        this.setRealName(account.getRealName());
        this.setIdentyCode(account.getIdentyCode());
        this.setLicenseCode(account.getLicenseCode());
        this.setGender(account.getGender());
        this.setAvatar(account.getAvatar());
        this.setImId(account.getImId());
        this.setRoleCodes(account.getRoleCodes());
        this.setRoleNames(account.getRoleNames());
        this.setSchoolCode(account.getSchoolCode());
        this.setSchoolName(account.getSchoolName());
        this.setCityCode(account.getCityCode());
        this.setCityName(account.getCityName());
        this.setPhaseCode(account.getPhaseCode());
        this.setCoachCode(account.getCoachCode());
        this.setBankCardCode(account.getBankCardCode());
        this.setAccount(account.getAccount());
        this.setAuditState(account.getAuditState());
        this.setCreatedBy(account.getCreatedBy());
        this.setCreatedFrom(account.getCreatedFrom());
        this.setCreatedTime(account.getCreatedTime());
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(String createdFrom) {
        this.createdFrom = createdFrom;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdentyCode() {
        return identyCode;
    }

    public void setIdentyCode(String identyCode) {
        this.identyCode = identyCode == null ? null : identyCode.trim();
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode == null ? null : licenseCode.trim();
    }

    public String getGender() {
        return gender;
    }

    public boolean isMale() {
        return "男".equals(getGender());
    }

    public boolean isFemale() {
        return "女".equals(getGender());
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getImId() {
        return imId;
    }

    public void setImId(String imId) {
        this.imId = imId == null ? null : imId.trim();
    }

    public String getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(String roleCodes) {
        this.roleCodes = roleCodes;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getPhaseCode() {
        return phaseCode;
    }

    public void setPhaseCode(String phaseCode) {
        this.phaseCode = phaseCode == null ? null : phaseCode.trim();
    }

    public String getCoachCode() {
        return coachCode;
    }

    public void setCoachCode(String coachCode) {
        this.coachCode = coachCode == null ? null : coachCode.trim();
    }

    public String getBankCardCode() {
        return bankCardCode;
    }

    public void setBankCardCode(String bankCardCode) {
        this.bankCardCode = bankCardCode == null ? null : bankCardCode.trim();
    }

    public Short getAccount() {
        return account;
    }

    public void setAccount(Short account) {
        this.account = account;
    }

    public Short getAuditState() {
        return auditState;
    }

    public void setAuditState(Short auditState) {
        this.auditState = auditState;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}