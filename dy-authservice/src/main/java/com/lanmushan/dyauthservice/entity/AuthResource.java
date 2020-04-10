package com.lanmushan.dyauthservice.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "auth_tb_resource")
public class AuthResource {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private String resourceName;
    private String resourceDescription;
    private String resourceUrl;
    private String iconDefault;
    private Integer fkParentId;
    private String resourceType; //page,api,menu,elm
    private String resourceId;
    private Integer disabled;
    private Integer orderIndex;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getIconDefault() {
        return iconDefault;
    }

    public void setIconDefault(String iconDefault) {
        this.iconDefault = iconDefault;
    }

    public Integer getFkParentId() {
        return fkParentId;
    }

    public void setFkParentId(Integer fkParentId) {
        this.fkParentId = fkParentId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthResource that = (AuthResource) o;
        return id.equals(that.id) &&
                fkParentId.equals(that.fkParentId) &&
                orderIndex.equals(that.orderIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkParentId, orderIndex);
    }
}
