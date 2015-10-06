package org.kyll.myserver.base.gis.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-10-03 12:19
 */
public class OlViewVo implements Vo<Long> {
	private Long id;
	private Long mapId;
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

	public OlViewVo() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMapId() {
		return mapId;
	}

	public void setMapId(Long mapId) {
		this.mapId = mapId;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getConstrainRotation() {
		return constrainRotation;
	}

	public void setConstrainRotation(String constrainRotation) {
		this.constrainRotation = constrainRotation;
	}

	public String getEnableRotation() {
		return enableRotation;
	}

	public void setEnableRotation(String enableRotation) {
		this.enableRotation = enableRotation;
	}

	public String getExtent() {
		return extent;
	}

	public void setExtent(String extent) {
		this.extent = extent;
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

	public String getMaxZoom() {
		return maxZoom;
	}

	public void setMaxZoom(String maxZoom) {
		this.maxZoom = maxZoom;
	}

	public String getMinZoom() {
		return minZoom;
	}

	public void setMinZoom(String minZoom) {
		this.minZoom = minZoom;
	}

	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getResolutions() {
		return resolutions;
	}

	public void setResolutions(String resolutions) {
		this.resolutions = resolutions;
	}

	public String getRotation() {
		return rotation;
	}

	public void setRotation(String rotation) {
		this.rotation = rotation;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public String getZoomFactor() {
		return zoomFactor;
	}

	public void setZoomFactor(String zoomFactor) {
		this.zoomFactor = zoomFactor;
	}
}
