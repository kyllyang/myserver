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
	private String viewMaxResolution;
	private String viewMinResolution;
	private String viewResolutions;
	private String viewResolution;
	private String viewMaxZoom;
	private String viewMinZoom;
	private String viewZoomFactor;
	private String viewZoom;
	private String viewEnableRotation;
	private String viewConstrainRotation;
	private String viewRotation;

	private String layerGroup;

	private String controlAttribution;
	private String controlAttributionClassName;
	private String controlAttributionCollapsible;
	private String controlAttributionCollapsed;
	private String controlAttributionLabel;
	private String controlAttributionCollapseLabel;
	private String controlAttributionTipLabel;
	private String controlFullScreen;
	private String controlFullScreenClassName;
	private String controlFullScreenKeys;
	private String controlFullScreenLabel;
	private String controlFullScreenLabelActive;
	private String controlFullScreenTipLabel;
	private String controlMousePosition;
	private String controlMousePositionClassName;
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
	private String controlRotateClassName;
	private String controlRotateAutoHide;
	private String controlRotateDuration;
	private String controlRotateLabel;
	private String controlRotateTipLabel;
	private String controlScaleLine;
	private String controlScaleLineClassName;
	private String controlScaleLineMinWidth;
	private String controlScaleLineUnits;
	private String controlZoom;
	private String controlZoomClassName;
	private String controlZoomDuration;
	private String controlZoomZoomInLabel;
	private String controlZoomZoomOutLabel;
	private String controlZoomDelta;
	private String controlZoomZoomInTipLabel;
	private String controlZoomZoomOutTipLabel;
	private String controlZoomSlider;
	private String controlZoomSliderClassName;
	private String controlZoomSliderDuration;
	private String controlZoomSliderMaxResolution;
	private String controlZoomSliderMinResolution;
	private String controlZoomToExtent;
	private String controlZoomToExtentClassName;
	private String controlZoomToExtentExtent;
	private String controlZoomToExtentLabel;
	private String controlZoomToExtentTipLabel;

	private String interactionDoubleClickZoom;
	private String interactionDoubleClickZoomDuration;
	private String interactionDoubleClickZoomDelta;
	private String interactionDragBox;
	private String interactionDragBoxCondition;
	private String interactionDragBoxStyle;
	private String interactionDragPan;
	private String interactionDragPanKineticDecay;
	private String interactionDragPanKineticMinVelocity;
	private String interactionDragPanKineticDelay;
	private String interactionDragRotate;
	private String interactionDragRotateCondition;
	private String interactionDragRotateDuration;
	private String interactionDragRotateAndZoom;
	private String interactionDragRotateAndZoomCondition;
	private String interactionDragRotateAndZoomDuration;
	private String interactionDragZoom;
	private String interactionDragZoomCondition;
	private String interactionDragZoomDuration;
	private String interactionDragZoomStyle;
	private String interactionKeyboardPan;
	private String interactionKeyboardPanDuration;
	private String interactionKeyboardPanPixelDelta;
	private String interactionKeyboardZoom;
	private String interactionKeyboardZoomDuration;
	private String interactionKeyboardZoomDelta;
	private String interactionMouseWheelZoom;
	private String interactionMouseWheelZoomDuration;
	private String interactionPinchRotate;
	private String interactionPinchRotateDuration;
	private String interactionPinchRotateThreshold;
	private String interactionPinchZoom;
	private String interactionPinchZoomDuration;

	private String toolbarSelect;
	private String toolbarDrawPoint;
	private String toolbarDrawPointStyle;
	private String toolbarDrawLineString;
	private String toolbarDrawLineStringStyle;
	private String toolbarDrawPolygon;
	private String toolbarDrawPolygonStyle;
	private String toolbarModify;
	private String toolbarTranslate;
	private String toolbarErase;
	private String toolbarRestore;

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

	public String getViewMaxResolution() {
		return viewMaxResolution;
	}

	public void setViewMaxResolution(String viewMaxResolution) {
		this.viewMaxResolution = viewMaxResolution;
	}

	public String getViewMinResolution() {
		return viewMinResolution;
	}

	public void setViewMinResolution(String viewMinResolution) {
		this.viewMinResolution = viewMinResolution;
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

	public String getViewMaxZoom() {
		return viewMaxZoom;
	}

	public void setViewMaxZoom(String viewMaxZoom) {
		this.viewMaxZoom = viewMaxZoom;
	}

	public String getViewMinZoom() {
		return viewMinZoom;
	}

	public void setViewMinZoom(String viewMinZoom) {
		this.viewMinZoom = viewMinZoom;
	}

	public String getViewZoomFactor() {
		return viewZoomFactor;
	}

	public void setViewZoomFactor(String viewZoomFactor) {
		this.viewZoomFactor = viewZoomFactor;
	}

	public String getViewZoom() {
		return viewZoom;
	}

	public void setViewZoom(String viewZoom) {
		this.viewZoom = viewZoom;
	}

	public String getViewEnableRotation() {
		return viewEnableRotation;
	}

	public void setViewEnableRotation(String viewEnableRotation) {
		this.viewEnableRotation = viewEnableRotation;
	}

	public String getViewConstrainRotation() {
		return viewConstrainRotation;
	}

	public void setViewConstrainRotation(String viewConstrainRotation) {
		this.viewConstrainRotation = viewConstrainRotation;
	}

	public String getViewRotation() {
		return viewRotation;
	}

	public void setViewRotation(String viewRotation) {
		this.viewRotation = viewRotation;
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

	public String getControlAttributionClassName() {
		return controlAttributionClassName;
	}

	public void setControlAttributionClassName(String controlAttributionClassName) {
		this.controlAttributionClassName = controlAttributionClassName;
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

	public String getControlFullScreenClassName() {
		return controlFullScreenClassName;
	}

	public void setControlFullScreenClassName(String controlFullScreenClassName) {
		this.controlFullScreenClassName = controlFullScreenClassName;
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

	public String getControlMousePositionClassName() {
		return controlMousePositionClassName;
	}

	public void setControlMousePositionClassName(String controlMousePositionClassName) {
		this.controlMousePositionClassName = controlMousePositionClassName;
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

	public String getControlRotateClassName() {
		return controlRotateClassName;
	}

	public void setControlRotateClassName(String controlRotateClassName) {
		this.controlRotateClassName = controlRotateClassName;
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

	public String getControlScaleLineClassName() {
		return controlScaleLineClassName;
	}

	public void setControlScaleLineClassName(String controlScaleLineClassName) {
		this.controlScaleLineClassName = controlScaleLineClassName;
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

	public String getControlZoomClassName() {
		return controlZoomClassName;
	}

	public void setControlZoomClassName(String controlZoomClassName) {
		this.controlZoomClassName = controlZoomClassName;
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

	public String getControlZoomSliderClassName() {
		return controlZoomSliderClassName;
	}

	public void setControlZoomSliderClassName(String controlZoomSliderClassName) {
		this.controlZoomSliderClassName = controlZoomSliderClassName;
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

	public String getControlZoomToExtentClassName() {
		return controlZoomToExtentClassName;
	}

	public void setControlZoomToExtentClassName(String controlZoomToExtentClassName) {
		this.controlZoomToExtentClassName = controlZoomToExtentClassName;
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

	public String getInteractionDoubleClickZoom() {
		return interactionDoubleClickZoom;
	}

	public void setInteractionDoubleClickZoom(String interactionDoubleClickZoom) {
		this.interactionDoubleClickZoom = interactionDoubleClickZoom;
	}

	public String getInteractionDoubleClickZoomDuration() {
		return interactionDoubleClickZoomDuration;
	}

	public void setInteractionDoubleClickZoomDuration(String interactionDoubleClickZoomDuration) {
		this.interactionDoubleClickZoomDuration = interactionDoubleClickZoomDuration;
	}

	public String getInteractionDoubleClickZoomDelta() {
		return interactionDoubleClickZoomDelta;
	}

	public void setInteractionDoubleClickZoomDelta(String interactionDoubleClickZoomDelta) {
		this.interactionDoubleClickZoomDelta = interactionDoubleClickZoomDelta;
	}

	public String getInteractionDragBox() {
		return interactionDragBox;
	}

	public void setInteractionDragBox(String interactionDragBox) {
		this.interactionDragBox = interactionDragBox;
	}

	public String getInteractionDragBoxCondition() {
		return interactionDragBoxCondition;
	}

	public void setInteractionDragBoxCondition(String interactionDragBoxCondition) {
		this.interactionDragBoxCondition = interactionDragBoxCondition;
	}

	public String getInteractionDragBoxStyle() {
		return interactionDragBoxStyle;
	}

	public void setInteractionDragBoxStyle(String interactionDragBoxStyle) {
		this.interactionDragBoxStyle = interactionDragBoxStyle;
	}

	public String getInteractionDragPan() {
		return interactionDragPan;
	}

	public void setInteractionDragPan(String interactionDragPan) {
		this.interactionDragPan = interactionDragPan;
	}

	public String getInteractionDragPanKineticDecay() {
		return interactionDragPanKineticDecay;
	}

	public void setInteractionDragPanKineticDecay(String interactionDragPanKineticDecay) {
		this.interactionDragPanKineticDecay = interactionDragPanKineticDecay;
	}

	public String getInteractionDragPanKineticMinVelocity() {
		return interactionDragPanKineticMinVelocity;
	}

	public void setInteractionDragPanKineticMinVelocity(String interactionDragPanKineticMinVelocity) {
		this.interactionDragPanKineticMinVelocity = interactionDragPanKineticMinVelocity;
	}

	public String getInteractionDragPanKineticDelay() {
		return interactionDragPanKineticDelay;
	}

	public void setInteractionDragPanKineticDelay(String interactionDragPanKineticDelay) {
		this.interactionDragPanKineticDelay = interactionDragPanKineticDelay;
	}

	public String getInteractionDragRotate() {
		return interactionDragRotate;
	}

	public void setInteractionDragRotate(String interactionDragRotate) {
		this.interactionDragRotate = interactionDragRotate;
	}

	public String getInteractionDragRotateCondition() {
		return interactionDragRotateCondition;
	}

	public void setInteractionDragRotateCondition(String interactionDragRotateCondition) {
		this.interactionDragRotateCondition = interactionDragRotateCondition;
	}

	public String getInteractionDragRotateDuration() {
		return interactionDragRotateDuration;
	}

	public void setInteractionDragRotateDuration(String interactionDragRotateDuration) {
		this.interactionDragRotateDuration = interactionDragRotateDuration;
	}

	public String getInteractionDragRotateAndZoom() {
		return interactionDragRotateAndZoom;
	}

	public void setInteractionDragRotateAndZoom(String interactionDragRotateAndZoom) {
		this.interactionDragRotateAndZoom = interactionDragRotateAndZoom;
	}

	public String getInteractionDragRotateAndZoomCondition() {
		return interactionDragRotateAndZoomCondition;
	}

	public void setInteractionDragRotateAndZoomCondition(String interactionDragRotateAndZoomCondition) {
		this.interactionDragRotateAndZoomCondition = interactionDragRotateAndZoomCondition;
	}

	public String getInteractionDragRotateAndZoomDuration() {
		return interactionDragRotateAndZoomDuration;
	}

	public void setInteractionDragRotateAndZoomDuration(String interactionDragRotateAndZoomDuration) {
		this.interactionDragRotateAndZoomDuration = interactionDragRotateAndZoomDuration;
	}

	public String getInteractionDragZoom() {
		return interactionDragZoom;
	}

	public void setInteractionDragZoom(String interactionDragZoom) {
		this.interactionDragZoom = interactionDragZoom;
	}

	public String getInteractionDragZoomCondition() {
		return interactionDragZoomCondition;
	}

	public void setInteractionDragZoomCondition(String interactionDragZoomCondition) {
		this.interactionDragZoomCondition = interactionDragZoomCondition;
	}

	public String getInteractionDragZoomDuration() {
		return interactionDragZoomDuration;
	}

	public void setInteractionDragZoomDuration(String interactionDragZoomDuration) {
		this.interactionDragZoomDuration = interactionDragZoomDuration;
	}

	public String getInteractionDragZoomStyle() {
		return interactionDragZoomStyle;
	}

	public void setInteractionDragZoomStyle(String interactionDragZoomStyle) {
		this.interactionDragZoomStyle = interactionDragZoomStyle;
	}

	public String getInteractionKeyboardPan() {
		return interactionKeyboardPan;
	}

	public void setInteractionKeyboardPan(String interactionKeyboardPan) {
		this.interactionKeyboardPan = interactionKeyboardPan;
	}

	public String getInteractionKeyboardPanDuration() {
		return interactionKeyboardPanDuration;
	}

	public void setInteractionKeyboardPanDuration(String interactionKeyboardPanDuration) {
		this.interactionKeyboardPanDuration = interactionKeyboardPanDuration;
	}

	public String getInteractionKeyboardPanPixelDelta() {
		return interactionKeyboardPanPixelDelta;
	}

	public void setInteractionKeyboardPanPixelDelta(String interactionKeyboardPanPixelDelta) {
		this.interactionKeyboardPanPixelDelta = interactionKeyboardPanPixelDelta;
	}

	public String getInteractionKeyboardZoom() {
		return interactionKeyboardZoom;
	}

	public void setInteractionKeyboardZoom(String interactionKeyboardZoom) {
		this.interactionKeyboardZoom = interactionKeyboardZoom;
	}

	public String getInteractionKeyboardZoomDuration() {
		return interactionKeyboardZoomDuration;
	}

	public void setInteractionKeyboardZoomDuration(String interactionKeyboardZoomDuration) {
		this.interactionKeyboardZoomDuration = interactionKeyboardZoomDuration;
	}

	public String getInteractionKeyboardZoomDelta() {
		return interactionKeyboardZoomDelta;
	}

	public void setInteractionKeyboardZoomDelta(String interactionKeyboardZoomDelta) {
		this.interactionKeyboardZoomDelta = interactionKeyboardZoomDelta;
	}

	public String getInteractionMouseWheelZoom() {
		return interactionMouseWheelZoom;
	}

	public void setInteractionMouseWheelZoom(String interactionMouseWheelZoom) {
		this.interactionMouseWheelZoom = interactionMouseWheelZoom;
	}

	public String getInteractionMouseWheelZoomDuration() {
		return interactionMouseWheelZoomDuration;
	}

	public void setInteractionMouseWheelZoomDuration(String interactionMouseWheelZoomDuration) {
		this.interactionMouseWheelZoomDuration = interactionMouseWheelZoomDuration;
	}

	public String getInteractionPinchRotate() {
		return interactionPinchRotate;
	}

	public void setInteractionPinchRotate(String interactionPinchRotate) {
		this.interactionPinchRotate = interactionPinchRotate;
	}

	public String getInteractionPinchRotateDuration() {
		return interactionPinchRotateDuration;
	}

	public void setInteractionPinchRotateDuration(String interactionPinchRotateDuration) {
		this.interactionPinchRotateDuration = interactionPinchRotateDuration;
	}

	public String getInteractionPinchRotateThreshold() {
		return interactionPinchRotateThreshold;
	}

	public void setInteractionPinchRotateThreshold(String interactionPinchRotateThreshold) {
		this.interactionPinchRotateThreshold = interactionPinchRotateThreshold;
	}

	public String getInteractionPinchZoom() {
		return interactionPinchZoom;
	}

	public void setInteractionPinchZoom(String interactionPinchZoom) {
		this.interactionPinchZoom = interactionPinchZoom;
	}

	public String getInteractionPinchZoomDuration() {
		return interactionPinchZoomDuration;
	}

	public void setInteractionPinchZoomDuration(String interactionPinchZoomDuration) {
		this.interactionPinchZoomDuration = interactionPinchZoomDuration;
	}

	public String getToolbarSelect() {
		return toolbarSelect;
	}

	public void setToolbarSelect(String toolbarSelect) {
		this.toolbarSelect = toolbarSelect;
	}

	public String getToolbarDrawPoint() {
		return toolbarDrawPoint;
	}

	public void setToolbarDrawPoint(String toolbarDrawPoint) {
		this.toolbarDrawPoint = toolbarDrawPoint;
	}

	public String getToolbarDrawPointStyle() {
		return toolbarDrawPointStyle;
	}

	public void setToolbarDrawPointStyle(String toolbarDrawPointStyle) {
		this.toolbarDrawPointStyle = toolbarDrawPointStyle;
	}

	public String getToolbarDrawLineString() {
		return toolbarDrawLineString;
	}

	public void setToolbarDrawLineString(String toolbarDrawLineString) {
		this.toolbarDrawLineString = toolbarDrawLineString;
	}

	public String getToolbarDrawLineStringStyle() {
		return toolbarDrawLineStringStyle;
	}

	public void setToolbarDrawLineStringStyle(String toolbarDrawLineStringStyle) {
		this.toolbarDrawLineStringStyle = toolbarDrawLineStringStyle;
	}

	public String getToolbarDrawPolygon() {
		return toolbarDrawPolygon;
	}

	public void setToolbarDrawPolygon(String toolbarDrawPolygon) {
		this.toolbarDrawPolygon = toolbarDrawPolygon;
	}

	public String getToolbarDrawPolygonStyle() {
		return toolbarDrawPolygonStyle;
	}

	public void setToolbarDrawPolygonStyle(String toolbarDrawPolygonStyle) {
		this.toolbarDrawPolygonStyle = toolbarDrawPolygonStyle;
	}

	public String getToolbarModify() {
		return toolbarModify;
	}

	public void setToolbarModify(String toolbarModify) {
		this.toolbarModify = toolbarModify;
	}

	public String getToolbarTranslate() {
		return toolbarTranslate;
	}

	public void setToolbarTranslate(String toolbarTranslate) {
		this.toolbarTranslate = toolbarTranslate;
	}

	public String getToolbarErase() {
		return toolbarErase;
	}

	public void setToolbarErase(String toolbarErase) {
		this.toolbarErase = toolbarErase;
	}

	public String getToolbarRestore() {
		return toolbarRestore;
	}

	public void setToolbarRestore(String toolbarRestore) {
		this.toolbarRestore = toolbarRestore;
	}
}
