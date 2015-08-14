package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-08-10 16:28
 */
@Entity
@Table(name = "MS_GIS_OL_CONTROL")
public class OlControl implements Serializable {
	private Long id;
	private OlMap olMap;
	private String controlClassName;// ol.control.Attribution, ol.control.FullScreen, ol.control.MousePosition, ol.control.OverviewMap, ol.control.Rotate, ol.control.ScaleLine, ol.control.Zoom, ol.control.ZoomSlider, ol.control.ZoomToExtent
	private String autoHide;
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
	private String units;// 'degrees', 'imperial', 'nautical', 'metric', 'us'
	private String zoomInLabel;
	private String zoomInTipLabel;
	private String zoomOutLabel;
	private String zoomOutTipLabel;

	public OlControl() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAP_ID_")
	public OlMap getOlMap() {
		return olMap;
	}

	public void setOlMap(OlMap olMap) {
		this.olMap = olMap;
	}

	@Column(name = "CONTROL_CLASS_NAME_")
	public String getControlClassName() {
		return controlClassName;
	}

	public void setControlClassName(String controlClassName) {
		this.controlClassName = controlClassName;
	}

	@Column(name = "AUTO_HIDE_")
	public String getAutoHide() {
		return autoHide;
	}

	public void setAutoHide(String autoHide) {
		this.autoHide = autoHide;
	}

	@Column(name = "COLLAPSED_")
	public String getCollapsed() {
		return collapsed;
	}

	public void setCollapsed(String collapsed) {
		this.collapsed = collapsed;
	}

	@Column(name = "COLLAPSE_LABEL_")
	public String getCollapseLabel() {
		return collapseLabel;
	}

	public void setCollapseLabel(String collapseLabel) {
		this.collapseLabel = collapseLabel;
	}

	@Column(name = "COLLAPSIBLE_")
	public String getCollapsible() {
		return collapsible;
	}

	public void setCollapsible(String collapsible) {
		this.collapsible = collapsible;
	}

	@Column(name = "COORDINATE_FORMAT_")
	public String getCoordinateFormat() {
		return coordinateFormat;
	}

	public void setCoordinateFormat(String coordinateFormat) {
		this.coordinateFormat = coordinateFormat;
	}

	@Column(name = "DELTA_")
	public String getDelta() {
		return delta;
	}

	public void setDelta(String delta) {
		this.delta = delta;
	}

	@Column(name = "DURATION_")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Column(name = "EXTENT_")
	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
	}

	@Column(name = "KEYS_")
	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	@Column(name = "LABEL_")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "LABEL_ACTIVE_")
	public String getLabelActive() {
		return labelActive;
	}

	public void setLabelActive(String labelActive) {
		this.labelActive = labelActive;
	}

	@Column(name = "LAYERS_")
	public String getLayers() {
		return layers;
	}

	public void setLayers(String layers) {
		this.layers = layers;
	}

	@Column(name = "MAX_RESOLUTION_")
	public String getMaxResolution() {
		return maxResolution;
	}

	public void setMaxResolution(String maxResolution) {
		this.maxResolution = maxResolution;
	}

	@Column(name = "MIN_RESOLUTION_")
	public String getMinResolution() {
		return minResolution;
	}

	public void setMinResolution(String minResolution) {
		this.minResolution = minResolution;
	}

	@Column(name = "MIN_WIDTH_")
	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	@Column(name = "PROJECTION_")
	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	@Column(name = "TIP_LABEL_")
	public String getTipLabel() {
		return tipLabel;
	}

	public void setTipLabel(String tipLabel) {
		this.tipLabel = tipLabel;
	}

	@Column(name = "UNDEFINED_HTML_")
	public String getUndefinedHTML() {
		return undefinedHTML;
	}

	public void setUndefinedHTML(String undefinedHTML) {
		this.undefinedHTML = undefinedHTML;
	}

	@Column(name = "UNITS_")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	@Column(name = "ZOOM_IN_LABEL_")
	public String getZoomInLabel() {
		return zoomInLabel;
	}

	public void setZoomInLabel(String zoomInLabel) {
		this.zoomInLabel = zoomInLabel;
	}

	@Column(name = "ZOOM_IN_TIP_LABEL_")
	public String getZoomInTipLabel() {
		return zoomInTipLabel;
	}

	public void setZoomInTipLabel(String zoomInTipLabel) {
		this.zoomInTipLabel = zoomInTipLabel;
	}

	@Column(name = "ZOOM_OUT_LABEL_")
	public String getZoomOutLabel() {
		return zoomOutLabel;
	}

	public void setZoomOutLabel(String zoomOutLabel) {
		this.zoomOutLabel = zoomOutLabel;
	}

	@Column(name = "ZOOM_OUT_TIP_LABEL_")
	public String getZoomOutTipLabel() {
		return zoomOutTipLabel;
	}

	public void setZoomOutTipLabel(String zoomOutTipLabel) {
		this.zoomOutTipLabel = zoomOutTipLabel;
	}
}
