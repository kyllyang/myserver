package org.kyll.myserver.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Kyll
 * Date: 2015-07-15 13:28
 */
@Entity
@Table(name = "MS_LI_EXPENSE")
public class Expense implements Serializable {
	private Long id;
	private Date startDate;
	private Date endDate;
	private Double carExpense;
	private Double cityTrafficExpense;
	private Double subsidyExpense;
	private Double otherExpense;
	private Area area;
	private Customer customer;
	private Project project;

	public Expense() {
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

	@Column(name = "START_DATE_")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "END_DATE_")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "CAR_EXPENSE_")
	public Double getCarExpense() {
		return carExpense;
	}

	public void setCarExpense(Double carExpense) {
		this.carExpense = carExpense;
	}

	@Column(name = "CITY_TRAFFIC_EXPENSE_")
	public Double getCityTrafficExpense() {
		return cityTrafficExpense;
	}

	public void setCityTrafficExpense(Double cityTrafficExpense) {
		this.cityTrafficExpense = cityTrafficExpense;
	}

	@Column(name = "SUBSIDY_EXPENSE_")
	public Double getSubsidyExpense() {
		return subsidyExpense;
	}

	public void setSubsidyExpense(Double subsidyExpense) {
		this.subsidyExpense = subsidyExpense;
	}

	@Column(name = "OTHER_EXPENSE_")
	public Double getOtherExpense() {
		return otherExpense;
	}

	public void setOtherExpense(Double otherExpense) {
		this.otherExpense = otherExpense;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID_")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID_")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID_")
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
