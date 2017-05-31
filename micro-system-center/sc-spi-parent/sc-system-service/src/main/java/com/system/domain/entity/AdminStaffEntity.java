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
@Table(name = "admin_staff", schema = "public_service_db", catalog = "")
public class AdminStaffEntity {
    private Integer staffId;
    private String code;
    private String pwd;
    private Integer departId;
    private Timestamp insTm;
    private Byte leader;
    private Byte status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String staffRoster;
    private String staffName;
    private String staffPost;
    private String staffPhone;
    private String staffEmail;
    private Integer systemId;
    //
    // @OneToMany(fetch = FetchType. LAZY, cascade ={CascadeType. ALL})
    // private AdminDepartEntity adminDepartEntity;
    //
    // public AdminDepartEntity getAdminDepartEntity() {
    //     return adminDepartEntity;
    // }
    //
    // public void setAdminDepartEntity(AdminDepartEntity adminDepartEntity) {
    //     this.adminDepartEntity = adminDepartEntity;
    // }
    private Integer dailyAccessNum;
    private Integer minuteAccessNum;

    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "depart_id")
    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    @Basic
    @Column(name = "ins_tm")
    public Timestamp getInsTm() {
        return insTm;
    }

    public void setInsTm(Timestamp insTm) {
        this.insTm = insTm;
    }

    @Basic
    @Column(name = "leader")
    public Byte getLeader() {
        return leader;
    }

    public void setLeader(Byte leader) {
        this.leader = leader;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    @Basic
    @Column(name = "staff_roster")
    public String getStaffRoster() {
        return staffRoster;
    }

    public void setStaffRoster(String staffRoster) {
        this.staffRoster = staffRoster;
    }

    @Basic
    @Column(name = "staff_name")
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name = "staff_post")
    public String getStaffPost() {
        return staffPost;
    }

    public void setStaffPost(String staffPost) {
        this.staffPost = staffPost;
    }

    @Basic
    @Column(name = "staff_phone")
    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    @Basic
    @Column(name = "staff_email")
    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    @Basic
    @Column(name = "system_id")
    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminStaffEntity that = (AdminStaffEntity) o;

        if (staffId != null ? !staffId.equals(that.staffId) : that.staffId != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;
        if (departId != null ? !departId.equals(that.departId) : that.departId != null) return false;
        if (insTm != null ? !insTm.equals(that.insTm) : that.insTm != null) return false;
        if (leader != null ? !leader.equals(that.leader) : that.leader != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (staffRoster != null ? !staffRoster.equals(that.staffRoster) : that.staffRoster != null) return false;
        if (staffName != null ? !staffName.equals(that.staffName) : that.staffName != null) return false;
        if (staffPost != null ? !staffPost.equals(that.staffPost) : that.staffPost != null) return false;
        if (staffPhone != null ? !staffPhone.equals(that.staffPhone) : that.staffPhone != null) return false;
        if (staffEmail != null ? !staffEmail.equals(that.staffEmail) : that.staffEmail != null) return false;
        if (systemId != null ? !systemId.equals(that.systemId) : that.systemId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = staffId != null ? staffId.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (departId != null ? departId.hashCode() : 0);
        result = 31 * result + (insTm != null ? insTm.hashCode() : 0);
        result = 31 * result + (leader != null ? leader.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (staffRoster != null ? staffRoster.hashCode() : 0);
        result = 31 * result + (staffName != null ? staffName.hashCode() : 0);
        result = 31 * result + (staffPost != null ? staffPost.hashCode() : 0);
        result = 31 * result + (staffPhone != null ? staffPhone.hashCode() : 0);
        result = 31 * result + (staffEmail != null ? staffEmail.hashCode() : 0);
        result = 31 * result + (systemId != null ? systemId.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "daily_access_num")
    public Integer getDailyAccessNum() {
        return dailyAccessNum;
    }

    public void setDailyAccessNum(Integer dailyAccessNum) {
        this.dailyAccessNum = dailyAccessNum;
    }

    @Basic
    @Column(name = "minute_access_num")
    public Integer getMinuteAccessNum() {
        return minuteAccessNum;
    }

    public void setMinuteAccessNum(Integer minuteAccessNum) {
        this.minuteAccessNum = minuteAccessNum;
    }
}
