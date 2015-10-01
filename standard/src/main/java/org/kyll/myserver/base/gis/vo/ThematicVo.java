package org.kyll.myserver.base.gis.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-05-12 20:00
 */
public class ThematicVo implements Vo<Long> {
	private Long id;
	private String name;
	private Integer sort;

	private Long mapId;
	private String mapLogo;
	private String mapLoadTilesWhileAnimating;
	private String mapLoadTilesWhileInteracting;
	private String mapRenderer;

	private Long viewId;
	private String viewProjection;
	private String viewCenter;
	private String viewExtent;
	private String viewResolutions;
	private String viewResolution;

	private String layerGroup;

	public ThematicVo() {
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getMapId() {
		return mapId;
	}

	public void setMapId(Long mapId) {
		this.mapId = mapId;
	}

	public String getMapLogo() {
		return mapLogo;
	}

	public void setMapLogo(String mapLogo) {
		this.mapLogo = mapLogo;
	}

	public String getMapLoadTilesWhileAnimating() {
		return mapLoadTilesWhileAnimating;
	}

	public void setMapLoadTilesWhileAnimating(String mapLoadTilesWhileAnimating) {
		this.mapLoadTilesWhileAnimating = mapLoadTilesWhileAnimating;
	}

	public String getMapLoadTilesWhileInteracting() {
		return mapLoadTilesWhileInteracting;
	}

	public void setMapLoadTilesWhileInteracting(String mapLoadTilesWhileInteracting) {
		this.mapLoadTilesWhileInteracting = mapLoadTilesWhileInteracting;
	}

	public String getMapRenderer() {
		return mapRenderer;
	}

	public void setMapRenderer(String mapRenderer) {
		this.mapRenderer = mapRenderer;
	}

	public Long getViewId() {
		return viewId;
	}

	public void setViewId(Long viewId) {
		this.viewId = viewId;
	}

	public String getViewProjection() {
		return viewProjection;
	}

	public void setViewProjection(String viewProjection) {
		this.viewProjection = viewProjection;
	}

	public String getViewCenter() {
		return viewCenter;
	}

	public void setViewCenter(String viewCenter) {
		this.viewCenter = viewCenter;
	}

	public String getViewExtent() {
		return viewExtent;
	}

	public void setViewExtent(String viewExtent) {
		this.viewExtent = viewExtent;
	}

	public String getViewResolutions() {
		return viewResolutions;
	}

	public void setViewResolutions(String viewResolutions) {
		this.viewResolutions = viewResolutions;
	}

	public String getViewResolution() {
		return viewResolution;
	}

	public void setViewResolution(String viewResolution) {
		this.viewResolution = viewResolution;
	}

	public String getLayerGroup() {
		return layerGroup;
	}

	public void setLayerGroup(String layerGroup) {
		this.layerGroup = layerGroup;
	}
}
