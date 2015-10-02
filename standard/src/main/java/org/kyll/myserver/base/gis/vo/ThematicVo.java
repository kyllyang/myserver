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

	private String controlAttribution;
	private String controlAttributionCollapsible;
	private String controlAttributionCollapsed;
	private String controlAttributionLabel;
	private String controlAttributionCollapseLabel;
	private String controlAttributionTipLabel;
	private String controlFullScreen;
	private String controlFullScreenKeys;
	private String controlFullScreenLabel;
	private String controlFullScreenLabelActive;
	private String controlFullScreenTipLabel;
	private String controlMousePosition;
	private String controlMousePositionCoordinateFormat;
	private String controlMousePositionProjection;
	private String controlMousePositionUndefinedHTML;
	private String controlOverviewMap;
	private String controlOverviewMapCollapsible;
	private String controlOverviewMapCollapsed;
	private String controlOverviewMapLabel;
	private String controlOverviewMapCollapseLabel;
	private String controlOverviewMapTipLabel;
	private String controlRotate;
	private String controlRotateAutoHide;
	private String controlRotateDuration;
	private String controlRotateLabel;
	private String controlRotateTipLabel;
	private String controlScaleLine;
	private String controlScaleLineMinWidth;
	private String controlScaleLineUnits;
	private String controlZoom;
	private String controlZoomDuration;
	private String controlZoomZoomInLabel;
	private String controlZoomZoomOutLabel;
	private String controlZoomDelta;
	private String controlZoomZoomInTipLabel;
	private String controlZoomZoomOutTipLabel;
	private String controlZoomSlider;
	private String controlZoomSliderDuration;
	private String controlZoomSliderMaxResolution;
	private String controlZoomSliderMinResolution;
	private String controlZoomToExtent;
	private String controlZoomToExtentExtent;
	private String controlZoomToExtentLabel;
	private String controlZoomToExtentTipLabel;


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

	public String getControlAttribution() {
		return controlAttribution;
	}

	public void setControlAttribution(String controlAttribution) {
		this.controlAttribution = controlAttribution;
	}

	public String getControlAttributionCollapsible() {
		return controlAttributionCollapsible;
	}

	public void setControlAttributionCollapsible(String controlAttributionCollapsible) {
		this.controlAttributionCollapsible = controlAttributionCollapsible;
	}

	public String getControlAttributionCollapsed() {
		return controlAttributionCollapsed;
	}

	public void setControlAttributionCollapsed(String controlAttributionCollapsed) {
		this.controlAttributionCollapsed = controlAttributionCollapsed;
	}

	public String getControlAttributionLabel() {
		return controlAttributionLabel;
	}

	public void setControlAttributionLabel(String controlAttributionLabel) {
		this.controlAttributionLabel = controlAttributionLabel;
	}

	public String getControlAttributionCollapseLabel() {
		return controlAttributionCollapseLabel;
	}

	public void setControlAttributionCollapseLabel(String controlAttributionCollapseLabel) {
		this.controlAttributionCollapseLabel = controlAttributionCollapseLabel;
	}

	public String getControlAttributionTipLabel() {
		return controlAttributionTipLabel;
	}

	public void setControlAttributionTipLabel(String controlAttributionTipLabel) {
		this.controlAttributionTipLabel = controlAttributionTipLabel;
	}

	public String getControlFullScreen() {
		return controlFullScreen;
	}

	public void setControlFullScreen(String controlFullScreen) {
		this.controlFullScreen = controlFullScreen;
	}

	public String getControlFullScreenKeys() {
		return controlFullScreenKeys;
	}

	public void setControlFullScreenKeys(String controlFullScreenKeys) {
		this.controlFullScreenKeys = controlFullScreenKeys;
	}

	public String getControlFullScreenLabel() {
		return controlFullScreenLabel;
	}

	public void setControlFullScreenLabel(String controlFullScreenLabel) {
		this.controlFullScreenLabel = controlFullScreenLabel;
	}

	public String getControlFullScreenLabelActive() {
		return controlFullScreenLabelActive;
	}

	public void setControlFullScreenLabelActive(String controlFullScreenLabelActive) {
		this.controlFullScreenLabelActive = controlFullScreenLabelActive;
	}

	public String getControlFullScreenTipLabel() {
		return controlFullScreenTipLabel;
	}

	public void setControlFullScreenTipLabel(String controlFullScreenTipLabel) {
		this.controlFullScreenTipLabel = controlFullScreenTipLabel;
	}

	public String getControlMousePosition() {
		return controlMousePosition;
	}

	public void setControlMousePosition(String controlMousePosition) {
		this.controlMousePosition = controlMousePosition;
	}

	public String getControlMousePositionCoordinateFormat() {
		return controlMousePositionCoordinateFormat;
	}

	public void setControlMousePositionCoordinateFormat(String controlMousePositionCoordinateFormat) {
		this.controlMousePositionCoordinateFormat = controlMousePositionCoordinateFormat;
	}

	public String getControlMousePositionProjection() {
		return controlMousePositionProjection;
	}

	public void setControlMousePositionProjection(String controlMousePositionProjection) {
		this.controlMousePositionProjection = controlMousePositionProjection;
	}

	public String getControlMousePositionUndefinedHTML() {
		return controlMousePositionUndefinedHTML;
	}

	public void setControlMousePositionUndefinedHTML(String controlMousePositionUndefinedHTML) {
		this.controlMousePositionUndefinedHTML = controlMousePositionUndefinedHTML;
	}

	public String getControlOverviewMap() {
		return controlOverviewMap;
	}

	public void setControlOverviewMap(String controlOverviewMap) {
		this.controlOverviewMap = controlOverviewMap;
	}

	public String getControlOverviewMapCollapsible() {
		return controlOverviewMapCollapsible;
	}

	public void setControlOverviewMapCollapsible(String controlOverviewMapCollapsible) {
		this.controlOverviewMapCollapsible = controlOverviewMapCollapsible;
	}

	public String getControlOverviewMapCollapsed() {
		return controlOverviewMapCollapsed;
	}

	public void setControlOverviewMapCollapsed(String controlOverviewMapCollapsed) {
		this.controlOverviewMapCollapsed = controlOverviewMapCollapsed;
	}

	public String getControlOverviewMapLabel() {
		return controlOverviewMapLabel;
	}

	public void setControlOverviewMapLabel(String controlOverviewMapLabel) {
		this.controlOverviewMapLabel = controlOverviewMapLabel;
	}

	public String getControlOverviewMapCollapseLabel() {
		return controlOverviewMapCollapseLabel;
	}

	public void setControlOverviewMapCollapseLabel(String controlOverviewMapCollapseLabel) {
		this.controlOverviewMapCollapseLabel = controlOverviewMapCollapseLabel;
	}

	public String getControlOverviewMapTipLabel() {
		return controlOverviewMapTipLabel;
	}

	public void setControlOverviewMapTipLabel(String controlOverviewMapTipLabel) {
		this.controlOverviewMapTipLabel = controlOverviewMapTipLabel;
	}

	public String getControlRotate() {
		return controlRotate;
	}

	public void setControlRotate(String controlRotate) {
		this.controlRotate = controlRotate;
	}

	public String getControlRotateAutoHide() {
		return controlRotateAutoHide;
	}

	public void setControlRotateAutoHide(String controlRotateAutoHide) {
		this.controlRotateAutoHide = controlRotateAutoHide;
	}

	public String getControlRotateDuration() {
		return controlRotateDuration;
	}

	public void setControlRotateDuration(String controlRotateDuration) {
		this.controlRotateDuration = controlRotateDuration;
	}

	public String getControlRotateLabel() {
		return controlRotateLabel;
	}

	public void setControlRotateLabel(String controlRotateLabel) {
		this.controlRotateLabel = controlRotateLabel;
	}

	public String getControlRotateTipLabel() {
		return controlRotateTipLabel;
	}

	public void setControlRotateTipLabel(String controlRotateTipLabel) {
		this.controlRotateTipLabel = controlRotateTipLabel;
	}

	public String getControlScaleLine() {
		return controlScaleLine;
	}

	public void setControlScaleLine(String controlScaleLine) {
		this.controlScaleLine = controlScaleLine;
	}

	public String getControlScaleLineMinWidth() {
		return controlScaleLineMinWidth;
	}

	public void setControlScaleLineMinWidth(String controlScaleLineMinWidth) {
		this.controlScaleLineMinWidth = controlScaleLineMinWidth;
	}

	public String getControlScaleLineUnits() {
		return controlScaleLineUnits;
	}

	public void setControlScaleLineUnits(String controlScaleLineUnits) {
		this.controlScaleLineUnits = controlScaleLineUnits;
	}

	public String getControlZoom() {
		return controlZoom;
	}

	public void setControlZoom(String controlZoom) {
		this.controlZoom = controlZoom;
	}

	public String getControlZoomDuration() {
		return controlZoomDuration;
	}

	public void setControlZoomDuration(String controlZoomDuration) {
		this.controlZoomDuration = controlZoomDuration;
	}

	public String getControlZoomZoomInLabel() {
		return controlZoomZoomInLabel;
	}

	public void setControlZoomZoomInLabel(String controlZoomZoomInLabel) {
		this.controlZoomZoomInLabel = controlZoomZoomInLabel;
	}

	public String getControlZoomZoomOutLabel() {
		return controlZoomZoomOutLabel;
	}

	public void setControlZoomZoomOutLabel(String controlZoomZoomOutLabel) {
		this.controlZoomZoomOutLabel = controlZoomZoomOutLabel;
	}

	public String getControlZoomDelta() {
		return controlZoomDelta;
	}

	public void setControlZoomDelta(String controlZoomDelta) {
		this.controlZoomDelta = controlZoomDelta;
	}

	public String getControlZoomZoomInTipLabel() {
		return controlZoomZoomInTipLabel;
	}

	public void setControlZoomZoomInTipLabel(String controlZoomZoomInTipLabel) {
		this.controlZoomZoomInTipLabel = controlZoomZoomInTipLabel;
	}

	public String getControlZoomZoomOutTipLabel() {
		return controlZoomZoomOutTipLabel;
	}

	public void setControlZoomZoomOutTipLabel(String controlZoomZoomOutTipLabel) {
		this.controlZoomZoomOutTipLabel = controlZoomZoomOutTipLabel;
	}

	public String getControlZoomSlider() {
		return controlZoomSlider;
	}

	public void setControlZoomSlider(String controlZoomSlider) {
		this.controlZoomSlider = controlZoomSlider;
	}

	public String getControlZoomSliderDuration() {
		return controlZoomSliderDuration;
	}

	public void setControlZoomSliderDuration(String controlZoomSliderDuration) {
		this.controlZoomSliderDuration = controlZoomSliderDuration;
	}

	public String getControlZoomSliderMaxResolution() {
		return controlZoomSliderMaxResolution;
	}

	public void setControlZoomSliderMaxResolution(String controlZoomSliderMaxResolution) {
		this.controlZoomSliderMaxResolution = controlZoomSliderMaxResolution;
	}

	public String getControlZoomSliderMinResolution() {
		return controlZoomSliderMinResolution;
	}

	public void setControlZoomSliderMinResolution(String controlZoomSliderMinResolution) {
		this.controlZoomSliderMinResolution = controlZoomSliderMinResolution;
	}

	public String getControlZoomToExtent() {
		return controlZoomToExtent;
	}

	public void setControlZoomToExtent(String controlZoomToExtent) {
		this.controlZoomToExtent = controlZoomToExtent;
	}

	public String getControlZoomToExtentExtent() {
		return controlZoomToExtentExtent;
	}

	public void setControlZoomToExtentExtent(String controlZoomToExtentExtent) {
		this.controlZoomToExtentExtent = controlZoomToExtentExtent;
	}

	public String getControlZoomToExtentLabel() {
		return controlZoomToExtentLabel;
	}

	public void setControlZoomToExtentLabel(String controlZoomToExtentLabel) {
		this.controlZoomToExtentLabel = controlZoomToExtentLabel;
	}

	public String getControlZoomToExtentTipLabel() {
		return controlZoomToExtentTipLabel;
	}

	public void setControlZoomToExtentTipLabel(String controlZoomToExtentTipLabel) {
		this.controlZoomToExtentTipLabel = controlZoomToExtentTipLabel;
	}
}
