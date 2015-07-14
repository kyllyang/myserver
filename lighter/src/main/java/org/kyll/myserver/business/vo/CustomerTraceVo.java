package org.kyll.myserver.business.vo;

import org.kyll.myserver.base.common.Vo;
import org.kyll.myserver.base.util.ConstUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2015-07-14 19:47
 */
public class CustomerTraceVo implements Vo<Long> {
    private Long id;
    private Long customerId;
    private String customerCompanyName;
    @DateTimeFormat(pattern = ConstUtils.DATE_FORMAT)
    private Date visitDate;
    private String linkMan;
    private String job;
    private String phone;
    private String officePhone;
    private String email;
    private String level;
    private String visitResult;

    public CustomerTraceVo() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getVisitResult() {
        return visitResult;
    }

    public void setVisitResult(String visitResult) {
        this.visitResult = visitResult;
    }
}
