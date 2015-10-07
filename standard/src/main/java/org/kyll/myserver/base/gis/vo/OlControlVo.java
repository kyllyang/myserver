package org.kyll.myserver.base.gis.vo;

import org.kyll.myserver.base.common.Vo;

/**
 * User: Kyll
 * Date: 2015-10-03 12:21
 */
public class OlControlVo implements Vo<Long> {
	private Long id;
	private Long mapId;
	private String attribution;
	private String attributionClassName;
	private String attributionCollapsible;
	private String attributionCollapsed;
	private String attributionLabel;
	private String attributionCollapseLabel;
	private String attributionTipLabel;
	private String fullScreen;
	private String fullScreenClassName;
	private String fullScreenKeys;
	private String fullScreenLabel;
	private String fullScreenLabelActive;
	private String fullScreenTipLabel;
	private String mousePosition;
	private String mousePositionClassName;
	private String mousePositionCoordinateFormat;
	private String mousePositionProjection;
	private String mousePositionUndefinedHTML;
	private String overviewMap;
	private String overviewMapCollapsible;
	private String overviewMapCollapsed;
	private String overviewMapLabel;
	private String overviewMapCollapseLabel;
	private String overviewMapTipLabel;
	private String rotate;
	private String rotateClassName;
	private String rotateAutoHide;
	private String rotateDuration;
	private String rotateLabel;
	private String rotateTipLabel;
	private String scaleLine;
	private String scaleLineClassName;
	private String scaleLineMinWidth;
	private String scaleLineUnits;
	private String zoom;
	private String zoomClassName;
	private String zoomDuration;
	private String zoomZoomInLabel;
	private String zoomZoomOutLabel;
	private String zoomDelta;
	private String zoomZoomInTipLabel;
	private String zoomZoomOutTipLabel;
	private String zoomSlider;
	private String zoomSliderClassName;
	private String zoomSliderDuration;
	private String zoomSliderMaxResolution;
	private String zoomSliderMinResolution;
	private String zoomToExtent;
	private String zoomToExtentClassName;
	private String zoomToExtentExtent;
	private String zoomToExtentLabel;
	private String zoomToExtentTipLabel;

	public OlControlVo() {
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

	public String getAttribution() {
		return attribution;
	}

	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}

	public String getAttributionClassName() {
		return attributionClassName;
	}

	public void setAttributionClassName(String attributionClassName) {
		this.attributionClassName = attributionClassName;
	}

	public String getAttributionCollapsible() {
		return attributionCollapsible;
	}

	public void setAttributionCollapsible(String attributionCollapsible) {
		this.attributionCollapsible = attributionCollapsible;
	}

	public String getAttributionCollapsed() {
		return attributionCollapsed;
	}

	public void setAttributionCollapsed(String attributionCollapsed) {
		this.attributionCollapsed = attributionCollapsed;
	}

	public String getAttributionLabel() {
		return attributionLabel;
	}

	public void setAttributionLabel(String attributionLabel) {
		this.attributionLabel = attributionLabel;
	}

	public String getAttributionCollapseLabel() {
		return attributionCollapseLabel;
	}

	public void setAttributionCollapseLabel(String attributionCollapseLabel) {
		this.attributionCollapseLabel = attributionCollapseLabel;
	}

	public String getAttributionTipLabel() {
		return attributionTipLabel;
	}

	public void setAttributionTipLabel(String attributionTipLabel) {
		this.attributionTipLabel = attributionTipLabel;
	}

	public String getFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(String fullScreen) {
		this.fullScreen = fullScreen;
	}

	public String getFullScreenClassName() {
		return fullScreenClassName;
	}

	public void setFullScreenClassName(String fullScreenClassName) {
		this.fullScreenClassName = fullScreenClassName;
	}

	public String getFullScreenKeys() {
		return fullScreenKeys;
	}

	public void setFullScreenKeys(String fullScreenKeys) {
		this.fullScreenKeys = fullScreenKeys;
	}

	public String getFullScreenLabel() {
		return fullScreenLabel;
	}

	public void setFullScreenLabel(String fullScreenLabel) {
		this.fullScreenLabel = fullScreenLabel;
	}

	public String getFullScreenLabelActive() {
		return fullScreenLabelActive;
	}

	public void setFullScreenLabelActive(String fullScreenLabelActive) {
		this.fullScreenLabelActive = fullScreenLabelActive;
	}

	public String getFullScreenTipLabel() {
		return fullScreenTipLabel;
	}

	public void setFullScreenTipLabel(String fullScreenTipLabel) {
		this.fullScreenTipLabel = fullScreenTipLabel;
	}

	public String getMousePosition() {
		return mousePosition;
	}

	public void setMousePosition(String mousePosition) {
		this.mousePosition = mousePosition;
	}

	public String getMousePositionClassName() {
		return mousePositionClassName;
	}

	public void setMousePositionClassName(String mousePositionClassName) {
		this.mousePositionClassName = mousePositionClassName;
	}

	public String getMousePositionCoordinateFormat() {
		return mousePositionCoordinateFormat;
	}

	public void setMousePositionCoordinateFormat(String mousePositionCoordinateFormat) {
		this.mousePositionCoordinateFormat = mousePositionCoordinateFormat;
	}

	public String getMousePositionProjection() {
		return mousePositionProjection;
	}

	public void setMousePositionProjection(String mousePositionProjection) {
		this.mousePositionProjection = mousePositionProjection;
	}

	public String getMousePositionUndefinedHTML() {
		return mousePositionUndefinedHTML;
	}

	public void setMousePositionUndefinedHTML(String mousePositionUndefinedHTML) {
		this.mousePositionUndefinedHTML = mousePositionUndefinedHTML;
	}

	public String getOverviewMap() {
		return overviewMap;
	}

	public void setOverviewMap(String overviewMap) {
		this.overviewMap = overviewMap;
	}

	public String getOverviewMapCollapsible() {
		return overviewMapCollapsible;
	}

	public void setOverviewMapCollapsible(String overviewMapCollapsible) {
		this.overviewMapCollapsible = overviewMapCollapsible;
	}

	public String getOverviewMapCollapsed() {
		return overviewMapCollapsed;
	}

	public void setOverviewMapCollapsed(String overviewMapCollapsed) {
		this.overviewMapCollapsed = overviewMapCollapsed;
	}

	public String getOverviewMapLabel() {
		return overviewMapLabel;
	}

	public void setOverviewMapLabel(String overviewMapLabel) {
		this.overviewMapLabel = overviewMapLabel;
	}

	public String getOverviewMapCollapseLabel() {
		return overviewMapCollapseLabel;
	}

	public void setOverviewMapCollapseLabel(String overviewMapCollapseLabel) {
		this.overviewMapCollapseLabel = overviewMapCollapseLabel;
	}

	public String getOverviewMapTipLabel() {
		return overviewMapTipLabel;
	}

	public void setOverviewMapTipLabel(String overviewMapTipLabel) {
		this.overviewMapTipLabel = overviewMapTipLabel;
	}

	public String getRotate() {
		return rotate;
	}

	public void setRotate(String rotate) {
		this.rotate = rotate;
	}

	public String getRotateClassName() {
		return rotateClassName;
	}

	public void setRotateClassName(String rotateClassName) {
		this.rotateClassName = rotateClassName;
	}

	public String getRotateAutoHide() {
		return rotateAutoHide;
	}

	public void setRotateAutoHide(String rotateAutoHide) {
		this.rotateAutoHide = rotateAutoHide;
	}

	public String getRotateDuration() {
		return rotateDuration;
	}

	public void setRotateDuration(String rotateDuration) {
		this.rotateDuration = rotateDuration;
	}

	public String getRotateLabel() {
		return rotateLabel;
	}

	public void setRotateLabel(String rotateLabel) {
		this.rotateLabel = rotateLabel;
	}

	public String getRotateTipLabel() {
		return rotateTipLabel;
	}

	public void setRotateTipLabel(String rotateTipLabel) {
		this.rotateTipLabel = rotateTipLabel;
	}

	public String getScaleLine() {
		return scaleLine;
	}

	public void setScaleLine(String scaleLine) {
		this.scaleLine = scaleLine;
	}

	public String getScaleLineClassName() {
		return scaleLineClassName;
	}

	public void setScaleLineClassName(String scaleLineClassName) {
		this.scaleLineClassName = scaleLineClassName;
	}

	public String getScaleLineMinWidth() {
		return scaleLineMinWidth;
	}

	public void setScaleLineMinWidth(String scaleLineMinWidth) {
		this.scaleLineMinWidth = scaleLineMinWidth;
	}

	public String getScaleLineUnits() {
		return scaleLineUnits;
	}

	public void setScaleLineUnits(String scaleLineUnits) {
		this.scaleLineUnits = scaleLineUnits;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public String getZoomClassName() {
		return zoomClassName;
	}

	public void setZoomClassName(String zoomClassName) {
		this.zoomClassName = zoomClassName;
	}

	public String getZoomDuration() {
		return zoomDuration;
	}

	public void setZoomDuration(String zoomDuration) {
		this.zoomDuration = zoomDuration;
	}

	public String getZoomZoomInLabel() {
		return zoomZoomInLabel;
	}

	public void setZoomZoomInLabel(String zoomZoomInLabel) {
		this.zoomZoomInLabel = zoomZoomInLabel;
	}

	public String getZoomZoomOutLabel() {
		return zoomZoomOutLabel;
	}

	public void setZoomZoomOutLabel(String zoomZoomOutLabel) {
		this.zoomZoomOutLabel = zoomZoomOutLabel;
	}

	public String getZoomDelta() {
		return zoomDelta;
	}

	public void setZoomDelta(String zoomDelta) {
		this.zoomDelta = zoomDelta;
	}

	public String getZoomZoomInTipLabel() {
		return zoomZoomInTipLabel;
	}

	public void setZoomZoomInTipLabel(String zoomZoomInTipLabel) {
		this.zoomZoomInTipLabel = zoomZoomInTipLabel;
	}

	public String getZoomZoomOutTipLabel() {
		return zoomZoomOutTipLabel;
	}

	public void setZoomZoomOutTipLabel(String zoomZoomOutTipLabel) {
		this.zoomZoomOutTipLabel = zoomZoomOutTipLabel;
	}

	public String getZoomSlider() {
		return zoomSlider;
	}

	public void setZoomSlider(String zoomSlider) {
		this.zoomSlider = zoomSlider;
	}

	public String getZoomSliderClassName() {
		return zoomSliderClassName;
	}

	public void setZoomSliderClassName(String zoomSliderClassName) {
		this.zoomSliderClassName = zoomSliderClassName;
	}

	public String getZoomSliderDuration() {
		return zoomSliderDuration;
	}

	public void setZoomSliderDuration(String zoomSliderDuration) {
		this.zoomSliderDuration = zoomSliderDuration;
	}

	public String getZoomSliderMaxResolution() {
		return zoomSliderMaxResolution;
	}

	public void setZoomSliderMaxResolution(String zoomSliderMaxResolution) {
		this.zoomSliderMaxResolution = zoomSliderMaxResolution;
	}

	public String getZoomSliderMinResolution() {
		return zoomSliderMinResolution;
	}

	public void setZoomSliderMinResolution(String zoomSliderMinResolution) {
		this.zoomSliderMinResolution = zoomSliderMinResolution;
	}

	public String getZoomToExtent() {
		return zoomToExtent;
	}

	public void setZoomToExtent(String zoomToExtent) {
		this.zoomToExtent = zoomToExtent;
	}

	public String getZoomToExtentClassName() {
		return zoomToExtentClassName;
	}

	public void setZoomToExtentClassName(String zoomToExtentClassName) {
		this.zoomToExtentClassName = zoomToExtentClassName;
	}

	public String getZoomToExtentExtent() {
		return zoomToExtentExtent;
	}

	public void setZoomToExtentExtent(String zoomToExtentExtent) {
		this.zoomToExtentExtent = zoomToExtentExtent;
	}

	public String getZoomToExtentLabel() {
		return zoomToExtentLabel;
	}

	public void setZoomToExtentLabel(String zoomToExtentLabel) {
		this.zoomToExtentLabel = zoomToExtentLabel;
	}

	public String getZoomToExtentTipLabel() {
		return zoomToExtentTipLabel;
	}

	public void setZoomToExtentTipLabel(String zoomToExtentTipLabel) {
		this.zoomToExtentTipLabel = zoomToExtentTipLabel;
	}
}
