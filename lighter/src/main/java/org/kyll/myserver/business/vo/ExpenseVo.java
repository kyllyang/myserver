package org.kyll.myserver.business.vo;

import org.kyll.myserver.base.common.Vo;
import org.kyll.myserver.base.util.ConstUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * User: Kyll
 * Date: 2015-07-15 14:12
 */
public class ExpenseVo implements Vo<Long> {
	private Long id;
	@DateTimeFormat(pattern = ConstUtils.DATE_FORMAT)
	private Date startDate;
	@DateTimeFormat(pattern = ConstUtils.DATE_FORMAT)
	private Date endDate;
	private Double carExpense;
	private Double cityTrafficExpense;
	private Double subsidyExpense;
	private Double otherExpense;
	private Long areaId;
	private String areaName;
	private Long customerId;
	private String customerCompanyName;
	private Long projectId;
	private String projectName;

	private Integer days;
	private Double thisTimeTotal;

	public ExpenseVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getCarExpense() {
		return carExpense;
	}

	public void setCarExpense(Double carExpense) {
		this.carExpense = carExpense;
	}

	public Double getCityTrafficExpense() {
		return cityTrafficExpense;
	}

	public void setCityTrafficExpense(Double cityTrafficExpense) {
		this.cityTrafficExpense = cityTrafficExpense;
	}

	public Double getSubsidyExpense() {
		return subsidyExpense;
	}

	public void setSubsidyExpense(Double subsidyExpense) {
		this.subsidyExpense = subsidyExpense;
	}

	public Double getOtherExpense() {
		return otherExpense;
	}

	public void setOtherExpense(Double otherExpense) {
		this.otherExpense = otherExpense;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Double getThisTimeTotal() {
		return thisTimeTotal;
	}

	public void setThisTimeTotal(Double thisTimeTotal) {
		this.thisTimeTotal = thisTimeTotal;
	}
}
