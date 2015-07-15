package org.kyll.myserver.business.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-07-15 19:59
 */
@Entity
@Table(name = "MS_LI_PRODUCT")
public class Product implements Serializable {
	private Long id;
	private String name;
	private String model;
	private String unit;
	private Double amount;
	private Double inTaxPrice;
	private Double inTaxTotal;
	private Double outTaxPrice;
	private Double outTaxTotal;
	private Double grossMargin;
	private Vendor vendor;
	private Customer customer;
	private Project project;

	public Product() {
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

	@Column(name = "NAME_")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MODEL_")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "UNIT_")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "AMOUNT_")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "IN_TAX_PRICE_")
	public Double getInTaxPrice() {
		return inTaxPrice;
	}

	public void setInTaxPrice(Double inTaxPrice) {
		this.inTaxPrice = inTaxPrice;
	}

	@Column(name = "IN_TAX_TOTAL_")
	public Double getInTaxTotal() {
		return inTaxTotal;
	}

	public void setInTaxTotal(Double inTaxTotal) {
		this.inTaxTotal = inTaxTotal;
	}

	@Column(name = "OUT_TAX_PRICE_")
	public Double getOutTaxPrice() {
		return outTaxPrice;
	}

	public void setOutTaxPrice(Double outTaxPrice) {
		this.outTaxPrice = outTaxPrice;
	}

	@Column(name = "OUT_TAX_TOTAL_")
	public Double getOutTaxTotal() {
		return outTaxTotal;
	}

	public void setOutTaxTotal(Double outTaxTotal) {
		this.outTaxTotal = outTaxTotal;
	}

	@Column(name = "GROSS_MARGIN_")
	public Double getGrossMargin() {
		return grossMargin;
	}

	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VERNOR_ID_")
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
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
