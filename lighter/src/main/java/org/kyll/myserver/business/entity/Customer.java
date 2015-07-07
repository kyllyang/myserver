package org.kyll.myserver.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Kyll
 * Date: 2015-06-19 9:10
 */
@Entity
@Table(name = "MS_LI_CUSTOMER")
public class Customer implements Serializable {
	private Long id;
	private Date visitDate;
	private String companyName;
	private String linkMan;
	private String job;
	private String phone;
	private String officePhone;
	private String email;
	private String level;
	private String visitResult;

	public Customer() {
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID_")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "VISIT_DATE_")
	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	@Column(name = "COMPANY_NAME_")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "LINK_MAN_")
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "JOB_")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "PHONE_")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "OFFICE_PHONE_")
	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "EMAIL_")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "LEVEL_")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "VISIT_RESULT_")
	public String getVisitResult() {
		return visitResult;
	}

	public void setVisitResult(String visitResult) {
		this.visitResult = visitResult;
	}
}
