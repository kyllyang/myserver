package org.kyll.myserver.base.gis.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-10-03 12:21
 */
public class OlControlVo implements Vo<Long> {
	private Long id;
	private String controlClassName;
	private String controlEnabled;
	private String autoHide;
	private String className;
	private String collapsed;
	private String collapseLabel;
	private String collapsible;
	private String coordinateFormat;
	private String delta;
	private String duration;
	private String extent;
	private String keys;
	private String label;
	private String labelActive;
	private String layers;
	private String maxResolution;
	private String minResolution;
	private String minWidth;
	private String projection;
	private String tipLabel;
	private String undefinedHTML;
	private String units;
	private String zoomInLabel;
	private String zoomInTipLabel;
	private String zoomOutLabel;
	private String zoomOutTipLabel;

	public OlControlVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getControlClassName() {
		return controlClassName;
	}

	public void setControlClassName(String controlClassName) {
		this.controlClassName = controlClassName;
	}

	public String getControlEnabled() {
		return controlEnabled;
	}

	public void setControlEnabled(String controlEnabled) {
		this.controlEnabled = controlEnabled;
	}

	public String getAutoHide() {
		return autoHide;
	}

	public void setAutoHide(String autoHide) {
		this.autoHide = autoHide;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCollapsed() {
		return collapsed;
	}

	public void setCollapsed(String collapsed) {
		this.collapsed = collapsed;
	}

	public String getCollapseLabel() {
		return collapseLabel;
	}

	public void setCollapseLabel(String collapseLabel) {
		this.collapseLabel = collapseLabel;
	}

	public String getCollapsible() {
		return collapsible;
	}

	public void setCollapsible(String collapsible) {
		this.collapsible = collapsible;
	}

	public String getCoordinateFormat() {
		return coordinateFormat;
	}

	public void setCoordinateFormat(String coordinateFormat) {
		this.coordinateFormat = coordinateFormat;
	}

	public String getDelta() {
		return delta;
	}

	public void setDelta(String delta) {
		this.delta = delta;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabelActive() {
		return labelActive;
	}

	public void setLabelActive(String labelActive) {
		this.labelActive = labelActive;
	}

	public String getLayers() {
		return layers;
	}

	public void setLayers(String layers) {
		this.layers = layers;
	}

	public String getMaxResolution() {
		return maxResolution;
	}

	public void setMaxResolution(String maxResolution) {
		this.maxResolution = maxResolution;
	}

	public String getMinResolution() {
		return minResolution;
	}

	public void setMinResolution(String minResolution) {
		this.minResolution = minResolution;
	}

	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	public String getTipLabel() {
		return tipLabel;
	}

	public void setTipLabel(String tipLabel) {
		this.tipLabel = tipLabel;
	}

	public String getUndefinedHTML() {
		return undefinedHTML;
	}

	public void setUndefinedHTML(String undefinedHTML) {
		this.undefinedHTML = undefinedHTML;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getZoomInLabel() {
		return zoomInLabel;
	}

	public void setZoomInLabel(String zoomInLabel) {
		this.zoomInLabel = zoomInLabel;
	}

	public String getZoomInTipLabel() {
		return zoomInTipLabel;
	}

	public void setZoomInTipLabel(String zoomInTipLabel) {
		this.zoomInTipLabel = zoomInTipLabel;
	}

	public String getZoomOutLabel() {
		return zoomOutLabel;
	}

	public void setZoomOutLabel(String zoomOutLabel) {
		this.zoomOutLabel = zoomOutLabel;
	}

	public String getZoomOutTipLabel() {
		return zoomOutTipLabel;
	}

	public void setZoomOutTipLabel(String zoomOutTipLabel) {
		this.zoomOutTipLabel = zoomOutTipLabel;
	}
}
