package com.system.domain.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by maven on 2017/3/26.
 */

@Entity
@Table(name = "admin_depart", schema = "public_service_db", catalog = "")
public class AdminDepartEntity {
    private String parent;
    private String name;
    private Byte type;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Integer departId;

    @Basic
    @Column(name = "parent")
    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type")
    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Id
    @Column(name = "depart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminDepartEntity that = (AdminDepartEntity) o;

        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (departId != null ? !departId.equals(that.departId) : that.departId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (departId != null ? departId.hashCode() : 0);
        return result;
    }
    //
    // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @JoinColumn(name = "depart_id", nullable = false)
    // private AdminStaffEntity adminStaffEntity=new AdminStaffEntity();
    //
    // public AdminStaffEntity queryByUserNameEntity() {
    //     return adminStaffEntity;
    // }
    //
    // public void setAdminStaffEntity(AdminStaffEntity adminStaffEntity) {
    //     this.adminStaffEntity = adminStaffEntity;
    // }
}
