package org.kyll.myserver.base.gis.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2015-08-10 16:32
 */
@Entity
@Table(name = "MS_GIS_OL_VIEW")
public class OlView implements Serializable {
	private Long id;
	private OlMap olMap;
	private String center;
	private String constrainRotation;
	private String enableRotation;
	private String extent;
	private String maxResolution;
	private String minResolution;
	private String maxZoom;
	private String minZoom;
	private String projection;
	private String resolution;
	private String resolutions;
	private String rotation;
	private String zoom;
	private String zoomFactor;

	public OlView() {
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

	@Column(name = "CENTER_")
	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	@Column(name = "CONSTRAIN_ROTATION_")
	public String getConstrainRotation() {
		return constrainRotation;
	}

	public void setConstrainRotation(String constrainRotation) {
		this.constrainRotation = constrainRotation;
	}

	@Column(name = "ENABLE_ROTATION_")
	public String getEnableRotation() {
		return enableRotation;
	}

	public void setEnableRotation(String enableRotation) {
		this.enableRotation = enableRotation;
	}

	@Column(name = "EXTENT_")
	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
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

	@Column(name = "MAX_ZOOM_")
	public String getMaxZoom() {
		return maxZoom;
	}

	public void setMaxZoom(String maxZoom) {
		this.maxZoom = maxZoom;
	}

	@Column(name = "MIN_ZOOM_")
	public String getMinZoom() {
		return minZoom;
	}

	public void setMinZoom(String minZoom) {
		this.minZoom = minZoom;
	}

	@Column(name = "PROJECTION_")
	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	@Column(name = "RESOLUTION_")
	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	@Column(name = "RESOLUTIONS_")
	public String getResolutions() {
		return resolutions;
	}

	public void setResolutions(String resolutions) {
		this.resolutions = resolutions;
	}

	@Column(name = "ROTATION_")
	public String getRotation() {
		return rotation;
	}

	public void setRotation(String rotation) {
		this.rotation = rotation;
	}

	@Column(name = "ZOOM_")
	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	@Column(name = "ZOOM_FACTOR_")
	public String getZoomFactor() {
		return zoomFactor;
	}

	public void setZoomFactor(String zoomFactor) {
		this.zoomFactor = zoomFactor;
	}
}
