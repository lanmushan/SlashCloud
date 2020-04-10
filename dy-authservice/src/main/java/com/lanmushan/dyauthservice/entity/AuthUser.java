package com.lanmushan.dyauthservice.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "auth_tb_user")
public class AuthUser implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String username;
    private String phone;
    private String idCard;
    private String email;
    private Integer sex;
    private String account;
    private String loginPassword;
    private String operatePassword;
    private String salt;
    private Date createTime;
    private Date updateTime;
    private Integer isLock;
    private String accountType;
    private String headImgAddress;
    private Integer deleted;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getOperatePassword() {
        return operatePassword;
    }

    public void setOperatePassword(String operatePassword) {
        this.operatePassword = operatePassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getHeadImgAddress() {
        return headImgAddress;
    }

    public void setHeadImgAddress(String headImgAddress) {
        this.headImgAddress = headImgAddress;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", account='" + account + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", operatePassword='" + operatePassword + '\'' +
                ", salt='" + salt + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isLock=" + isLock +
                ", accountType='" + accountType + '\'' +
                ", headImgAddress='" + headImgAddress + '\'' +
                '}';
    }
}