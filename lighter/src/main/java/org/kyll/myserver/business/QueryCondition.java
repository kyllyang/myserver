package org.kyll.myserver.business;

/**
 * User: Kyll
 * Date: 2015-06-19 9:31
 */
public class QueryCondition {
	private Long customerId;
	private Long projectId;
	private Long employeeId;
	private Long areaId;

	public QueryCondition() {
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
}
