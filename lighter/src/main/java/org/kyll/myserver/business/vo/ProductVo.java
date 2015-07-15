package org.kyll.myserver.business.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-07-15 20:10
 */
public class ProductVo implements Vo<Long> {
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
	private Long vendorId;
	private String vendorName;
	private Long customerId;
	private String customerCompanyName;
	private Long projectId;
	private String projectName;

	public ProductVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getInTaxPrice() {
		return inTaxPrice;
	}

	public void setInTaxPrice(Double inTaxPrice) {
		this.inTaxPrice = inTaxPrice;
	}

	public Double getInTaxTotal() {
		return inTaxTotal;
	}

	public void setInTaxTotal(Double inTaxTotal) {
		this.inTaxTotal = inTaxTotal;
	}

	public Double getOutTaxPrice() {
		return outTaxPrice;
	}

	public void setOutTaxPrice(Double outTaxPrice) {
		this.outTaxPrice = outTaxPrice;
	}

	public Double getOutTaxTotal() {
		return outTaxTotal;
	}

	public void setOutTaxTotal(Double outTaxTotal) {
		this.outTaxTotal = outTaxTotal;
	}

	public Double getGrossMargin() {
		return grossMargin;
	}

	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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
}
