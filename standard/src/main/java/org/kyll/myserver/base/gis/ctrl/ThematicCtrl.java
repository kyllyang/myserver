package org.kyll.myserver.base.gis.ctrl;

import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.gis.entity.*;
import org.kyll.myserver.base.gis.service.*;
import org.kyll.myserver.base.gis.vo.ThematicVo;
import org.kyll.myserver.base.util.ConstUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2015-04-20 17:53
 */
@Controller
@Scope("request")
public class ThematicCtrl {
	@Autowired
	private ThematicService thematicService;
	@Autowired
	private OlMapService olMapService;
	@Autowired
	private OlViewService olViewService;
	@Autowired
	private OlControlService olControlService;
	@Autowired
	private OlInteractionService olInteractionService;

	@RequestMapping("/gis/thematic/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Thematic> dataset = thematicService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<ThematicVo> voDataset = POJOUtils.convert(dataset, ThematicVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/gis/thematic/list.ctrl")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Thematic> list = thematicService.get(RequestUtils.getQueryCondition(request, QueryCondition.class));
		List<ThematicVo> voList = POJOUtils.convert(list, ThematicVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voList));
	}

	@RequestMapping("/gis/thematic/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Thematic entity = thematicService.get(id);
		ThematicVo entityVo = POJOUtils.convert(entity, ThematicVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/gis/thematic/save.ctrl")
	public void save(ThematicVo entityVo, HttpServletResponse response) throws Exception {
		Long mapId = entityVo.getMapId();
		OlMap olMap;
		if (mapId == null) {
			olMap = new OlMap();
		} else {
			olMap = olMapService.get(mapId);
		}
		olMap.setLogo(entityVo.getMapLogo());
		olMap.setLoadTilesWhileAnimating(entityVo.getMapLoadTilesWhileAnimating());
		olMap.setLoadTilesWhileInteracting(entityVo.getMapLoadTilesWhileInteracting());
		olMap.setRenderer(entityVo.getMapRenderer());

		Long viewId = entityVo.getViewId();
		OlView olView;
		if (viewId == null) {
			olView = new OlView();
		} else {
			olView = olViewService.get(viewId);
		}
		olView.setProjection(entityVo.getViewProjection());
		olView.setCenter(entityVo.getViewCenter());
		olView.setExtent(entityVo.getViewExtent());
		olView.setMaxResolution(entityVo.getViewMaxResolution());
		olView.setMinResolution(entityVo.getViewMinResolution());
		String resolutions = entityVo.getViewResolutions();
		olView.setResolutions(resolutions);
		olView.setResolution(StringUtils.isBlank(resolutions) ? null : entityVo.getViewResolution());
		olView.setMaxZoom(entityVo.getViewMaxZoom());
		olView.setMinZoom(entityVo.getViewMinZoom());
		olView.setZoomFactor(entityVo.getViewZoomFactor());
		olView.setZoom(entityVo.getViewZoom());
		olView.setEnableRotation(entityVo.getViewEnableRotation());
		olView.setConstrainRotation(entityVo.getViewConstrainRotation());
		olView.setRotation(entityVo.getViewRotation());

		List<OlControl> olControlList = new ArrayList<>();
		OlControl olControlAttribution = new OlControl();
		olControlAttribution.setControlEnabled(entityVo.getControlAttribution());
		olControlAttribution.setControlClassName(ConstUtils.GIS_OL_CONTROL_ATTRIBUTION);
		olControlAttribution.setClassName(entityVo.getControlAttributionClassName());
		olControlAttribution.setCollapsed(entityVo.getControlAttributionCollapsed());
		olControlAttribution.setCollapseLabel(entityVo.getControlAttributionCollapseLabel());
		olControlAttribution.setCollapsible(entityVo.getControlAttributionCollapsible());
		olControlAttribution.setLabel(entityVo.getControlAttributionLabel());
		olControlAttribution.setTipLabel(entityVo.getControlAttributionTipLabel());
		olControlList.add(olControlAttribution);

		OlControl olControlFullScreen = new OlControl();
		olControlFullScreen.setControlClassName(ConstUtils.GIS_OL_CONTROL_FULLSCREEN);
		olControlFullScreen.setControlEnabled(entityVo.getControlFullScreen());
		olControlFullScreen.setClassName(entityVo.getControlFullScreenClassName());
		olControlFullScreen.setKeys(entityVo.getControlFullScreenKeys());
		olControlFullScreen.setLabel(entityVo.getControlFullScreenLabel());
		olControlFullScreen.setLabelActive(entityVo.getControlFullScreenLabelActive());
		olControlFullScreen.setTipLabel(entityVo.getControlFullScreenTipLabel());
		olControlList.add(olControlFullScreen);

		OlControl olControlMousePosition = new OlControl();
		olControlMousePosition.setControlClassName(ConstUtils.GIS_OL_CONTROL_MOUSEPOSITION);
		olControlMousePosition.setControlEnabled(entityVo.getControlMousePosition());
		olControlMousePosition.setClassName(entityVo.getControlMousePositionClassName());
		olControlMousePosition.setCoordinateFormat(entityVo.getControlMousePositionCoordinateFormat());
		olControlMousePosition.setProjection(entityVo.getControlMousePositionProjection());
		olControlMousePosition.setUndefinedHTML(entityVo.getControlMousePositionUndefinedHTML());
		olControlList.add(olControlMousePosition);

		OlControl olControlOverviewMap = new OlControl();
		olControlOverviewMap.setControlClassName(ConstUtils.GIS_OL_CONTROL_OVERVIEWMAP);
		olControlOverviewMap.setControlEnabled(entityVo.getControlOverviewMap());
		olControlOverviewMap.setCollapsed(entityVo.getControlOverviewMapCollapsed());
		olControlOverviewMap.setCollapseLabel(entityVo.getControlOverviewMapCollapseLabel());
		olControlOverviewMap.setCollapsible(entityVo.getControlOverviewMapCollapsible());
		olControlOverviewMap.setLabel(entityVo.getControlOverviewMapLabel());
		olControlOverviewMap.setTipLabel(entityVo.getControlOverviewMapTipLabel());
		olControlList.add(olControlOverviewMap);

		OlControl olControlRotate = new OlControl();
		olControlRotate.setControlClassName(ConstUtils.GIS_OL_CONTROL_ROTATE);
		olControlRotate.setControlEnabled(entityVo.getControlRotate());
		olControlRotate.setClassName(entityVo.getControlRotateClassName());
		olControlRotate.setAutoHide(entityVo.getControlRotateAutoHide());
		olControlRotate.setDuration(entityVo.getControlRotateDuration());
		olControlRotate.setLabel(entityVo.getControlRotateLabel());
		olControlRotate.setTipLabel(entityVo.getControlRotateTipLabel());
		olControlList.add(olControlRotate);

		OlControl olControlScaleLine = new OlControl();
		olControlScaleLine.setControlClassName(ConstUtils.GIS_OL_CONTROL_SCALELINE);
		olControlScaleLine.setControlEnabled(entityVo.getControlScaleLine());
		olControlScaleLine.setClassName(entityVo.getControlScaleLineClassName());
		olControlScaleLine.setMinWidth(entityVo.getControlScaleLineMinWidth());
		olControlScaleLine.setUnits(entityVo.getControlScaleLineUnits());
		olControlList.add(olControlScaleLine);

		OlControl olControlZoom = new OlControl();
		olControlZoom.setControlClassName(ConstUtils.GIS_OL_CONTROL_ZOOM);
		olControlZoom.setControlEnabled(entityVo.getControlZoom());
		olControlZoom.setClassName(entityVo.getControlZoomClassName());
		olControlZoom.setDelta(entityVo.getControlZoomDelta());
		olControlZoom.setZoomInLabel(entityVo.getControlZoomZoomInLabel());
		olControlZoom.setZoomOutLabel(entityVo.getControlZoomZoomOutLabel());
		olControlZoom.setDuration(entityVo.getControlZoomDuration());
		olControlZoom.setZoomInTipLabel(entityVo.getControlZoomZoomInTipLabel());
		olControlZoom.setZoomOutTipLabel(entityVo.getControlZoomZoomOutTipLabel());
		olControlList.add(olControlZoom);

		OlControl olControlZoomSlider = new OlControl();
		olControlZoomSlider.setControlClassName(ConstUtils.GIS_OL_CONTROL_ZOOMSLIDER);
		olControlZoomSlider.setControlEnabled(entityVo.getControlZoomSlider());
		olControlZoomSlider.setClassName(entityVo.getControlZoomSliderClassName());
		olControlZoomSlider.setDuration(entityVo.getControlZoomSliderDuration());
		olControlZoomSlider.setMaxResolution(entityVo.getControlZoomSliderMaxResolution());
		olControlZoomSlider.setMinResolution(entityVo.getControlZoomSliderMinResolution());
		olControlList.add(olControlZoomSlider);

		OlControl olControlZoomToExtent = new OlControl();
		olControlZoomToExtent.setControlClassName(ConstUtils.GIS_OL_CONTROL_ZOOMTOEXTENT);
		olControlZoomToExtent.setControlEnabled(entityVo.getControlZoomToExtent());
		olControlZoomToExtent.setClassName(entityVo.getControlZoomToExtentClassName());
		olControlZoomToExtent.setExtent(entityVo.getControlZoomToExtentExtent());
		olControlZoomToExtent.setLabel(entityVo.getControlZoomToExtentLabel());
		olControlZoomToExtent.setTipLabel(entityVo.getControlZoomToExtentTipLabel());
		olControlList.add(olControlZoomToExtent);

		List<OlInteraction> olInteractionList = new ArrayList<>();
		OlInteraction olInteractionDoubleClickZoom = new OlInteraction();
		olInteractionDoubleClickZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DOUBLECLICKZOOM);
		olInteractionDoubleClickZoom.setInteractionEnabled(entityVo.getInteractionDoubleClickZoom());
		olInteractionDoubleClickZoom.setDelta(entityVo.getInteractionDoubleClickZoomDelta());
		olInteractionDoubleClickZoom.setDuration(entityVo.getInteractionDoubleClickZoomDuration());
		olInteractionList.add(olInteractionDoubleClickZoom);

		OlInteraction olInteractionDragAndDrop = new OlInteraction();
		olInteractionDragAndDrop.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGANDDROP);
		olInteractionDragAndDrop.setInteractionEnabled(entityVo.getInteractionDragAndDrop());
		olInteractionDragAndDrop.setProjection(entityVo.getInteractionDragAndDropProjection());
		olInteractionList.add(olInteractionDragAndDrop);

		OlInteraction olInteractionDragBox = new OlInteraction();
		olInteractionDragBox.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGBOX);
		olInteractionDragBox.setInteractionEnabled(entityVo.getInteractionDragBox());
		olInteractionDragBox.setCondition(entityVo.getInteractionDragBoxCondition());
		olInteractionDragBox.setStyle(entityVo.getInteractionDragBoxStyle());
		olInteractionList.add(olInteractionDragBox);

		OlInteraction olInteractionDragPan = new OlInteraction();
		olInteractionDragPan.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGPAN);
		olInteractionDragPan.setInteractionEnabled(entityVo.getInteractionDragPan());
		olInteractionDragPan.setKineticDecay(entityVo.getInteractionDragPanKineticDecay());
		olInteractionDragPan.setKineticDelay(entityVo.getInteractionDragPanKineticDelay());
		olInteractionDragPan.setKineticMinVelocity(entityVo.getInteractionDragPanKineticMinVelocity());
		olInteractionList.add(olInteractionDragPan);

		OlInteraction olInteractionDragRotate = new OlInteraction();
		olInteractionDragRotate.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGROTATE);
		olInteractionDragRotate.setInteractionEnabled(entityVo.getInteractionDragRotate());
		olInteractionDragRotate.setCondition(entityVo.getInteractionDragRotateCondition());
		olInteractionDragRotate.setDuration(entityVo.getInteractionDragRotateDuration());
		olInteractionList.add(olInteractionDragRotate);

		OlInteraction olInteractionDragRotateAndZoom = new OlInteraction();
		olInteractionDragRotateAndZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGROTATEANDZOOM);
		olInteractionDragRotateAndZoom.setInteractionEnabled(entityVo.getInteractionDragRotateAndZoom());
		olInteractionDragRotateAndZoom.setCondition(entityVo.getInteractionDragRotateAndZoomCondition());
		olInteractionDragRotateAndZoom.setDuration(entityVo.getInteractionDragRotateAndZoomDuration());
		olInteractionList.add(olInteractionDragRotateAndZoom);

		OlInteraction olInteractionDragZoom = new OlInteraction();
		olInteractionDragZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGZOOM);
		olInteractionDragZoom.setInteractionEnabled(entityVo.getInteractionDragZoom());
		olInteractionDragZoom.setCondition(entityVo.getInteractionDragZoomCondition());
		olInteractionDragZoom.setDuration(entityVo.getInteractionDragZoomDuration());
		olInteractionDragZoom.setStyle(entityVo.getInteractionDragZoomStyle());
		olInteractionList.add(olInteractionDragZoom);

		OlInteraction olInteractionDraw = new OlInteraction();
		olInteractionDraw.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAW);
		olInteractionDraw.setInteractionEnabled(entityVo.getInteractionDraw());
		olInteractionDraw.setClickTolerance(entityVo.getInteractionDrawClickTolerance());
		olInteractionDraw.setSnapTolerance(entityVo.getInteractionDrawSnapTolerance());
		olInteractionDraw.setWrapX(entityVo.getInteractionDrawWrapX());
		olInteractionList.add(olInteractionDraw);

		OlInteraction olInteractionKeyboardPan = new OlInteraction();
		olInteractionKeyboardPan.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_KEYBOARDPAN);
		olInteractionKeyboardPan.setInteractionEnabled(entityVo.getInteractionKeyboardPan());
		olInteractionKeyboardPan.setDuration(entityVo.getInteractionKeyboardPanDuration());
		olInteractionKeyboardPan.setPixelDelta(entityVo.getInteractionKeyboardPanPixelDelta());
		olInteractionList.add(olInteractionKeyboardPan);

		OlInteraction olInteractionKeyboardZoom = new OlInteraction();
		olInteractionKeyboardZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_KEYBOARDZOOM);
		olInteractionKeyboardZoom.setInteractionEnabled(entityVo.getInteractionKeyboardZoom());
		olInteractionKeyboardZoom.setDuration(entityVo.getInteractionKeyboardZoomDuration());
		olInteractionKeyboardZoom.setDelta(entityVo.getInteractionKeyboardZoomDelta());
		olInteractionList.add(olInteractionKeyboardZoom);

		OlInteraction olInteractionModify = new OlInteraction();
		olInteractionModify.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_MODIFY);
		olInteractionModify.setInteractionEnabled(entityVo.getInteractionModify());
		olInteractionModify.setPixelTolerance(entityVo.getInteractionModifyPixelTolerance());
		olInteractionModify.setWrapX(entityVo.getInteractionModifyWrapX());
		olInteractionList.add(olInteractionModify);

		OlInteraction olInteractionMouseWheelZoom = new OlInteraction();
		olInteractionMouseWheelZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_MOUSEWHEELZOOM);
		olInteractionMouseWheelZoom.setInteractionEnabled(entityVo.getInteractionMouseWheelZoom());
		olInteractionMouseWheelZoom.setDuration(entityVo.getInteractionMouseWheelZoomDuration());
		olInteractionList.add(olInteractionMouseWheelZoom);

		OlInteraction olInteractionPinchRotate = new OlInteraction();
		olInteractionPinchRotate.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_PINCHROTATE);
		olInteractionPinchRotate.setInteractionEnabled(entityVo.getInteractionPinchRotate());
		olInteractionPinchRotate.setDuration(entityVo.getInteractionPinchRotateDuration());
		olInteractionPinchRotate.setThreshold(entityVo.getInteractionPinchRotateThreshold());
		olInteractionList.add(olInteractionPinchRotate);

		OlInteraction olInteractionPinchZoom = new OlInteraction();
		olInteractionPinchZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_PINCHZOOM);
		olInteractionPinchZoom.setInteractionEnabled(entityVo.getInteractionPinchZoom());
		olInteractionPinchZoom.setDuration(entityVo.getInteractionPinchZoomDuration());
		olInteractionList.add(olInteractionPinchZoom);

		OlInteraction olInteractionSelect = new OlInteraction();
		olInteractionSelect.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_SELECT);
		olInteractionSelect.setInteractionEnabled(entityVo.getInteractionSelect());
		olInteractionSelect.setMulti(entityVo.getInteractionSelectMulti());
		olInteractionSelect.setWrapX(entityVo.getInteractionSelectWrapX());
		olInteractionList.add(olInteractionSelect);

		OlInteraction olInteractionSnap = new OlInteraction();
		olInteractionSnap.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_SNAP);
		olInteractionSnap.setInteractionEnabled(entityVo.getInteractionSnap());
		olInteractionSnap.setPixelTolerance(entityVo.getInteractionSnapPixelTolerance());
		olInteractionList.add(olInteractionSnap);

		OlInteraction olInteractionTranslate = new OlInteraction();
		olInteractionTranslate.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_TRANSLATE);
		olInteractionTranslate.setInteractionEnabled(entityVo.getInteractionTranslate());
		olInteractionList.add(olInteractionTranslate);

		thematicService.save(POJOUtils.convert(entityVo, Thematic.class, thematicService), olMap, olView, entityVo.getLayerGroup(), olControlList, olInteractionList);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/gis/thematic/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		thematicService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private POJOUtils.VoHandler<Thematic, ThematicVo> voHandler = (thematic, thematicVo) -> {
		OlMap olMap = thematic.getOlMap();
		if (olMap != null) {
			thematicVo.setMapId(olMap.getId());
			thematicVo.setMapLogo(olMap.getLogo());
			thematicVo.setMapLoadTilesWhileAnimating(olMap.getLoadTilesWhileAnimating());
			thematicVo.setMapLoadTilesWhileInteracting(olMap.getLoadTilesWhileInteracting());
			thematicVo.setMapRenderer(olMap.getRenderer());

			OlView olView = olViewService.getByOlMap(olMap.getId());
			if (olView != null) {
				thematicVo.setViewId(olView.getId());
				thematicVo.setViewProjection(olView.getProjection());
				thematicVo.setViewCenter(olView.getCenter());
				thematicVo.setViewExtent(olView.getExtent());
				thematicVo.setViewMaxResolution(olView.getMaxResolution());
				thematicVo.setViewMinResolution(olView.getMinResolution());
				thematicVo.setViewResolutions(olView.getResolutions());
				thematicVo.setViewResolution(olView.getResolution());
				thematicVo.setViewMaxZoom(olView.getMaxZoom());
				thematicVo.setViewMinZoom(olView.getMinZoom());
				thematicVo.setViewZoomFactor(olView.getZoomFactor());
				thematicVo.setViewZoom(olView.getZoom());
				thematicVo.setViewEnableRotation(olView.getEnableRotation());
				thematicVo.setViewConstrainRotation(olView.getConstrainRotation());
				thematicVo.setViewRotation(olView.getRotation());
			}

			List<OlControl> olControlList = olControlService.getByOlMap(olMap.getId(), null);
			for (OlControl olControl : olControlList) {
				String controlClassName = olControl.getControlClassName();
				if (ConstUtils.GIS_OL_CONTROL_ATTRIBUTION.equals(controlClassName)) {
					thematicVo.setControlAttribution(olControl.getControlEnabled());
					thematicVo.setControlAttributionClassName(olControl.getClassName());
					thematicVo.setControlAttributionCollapsed(olControl.getCollapsed());
					thematicVo.setControlAttributionCollapseLabel(olControl.getCollapseLabel());
					thematicVo.setControlAttributionCollapsible(olControl.getCollapsible());
					thematicVo.setControlAttributionLabel(olControl.getLabel());
					thematicVo.setControlAttributionTipLabel(olControl.getTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_FULLSCREEN.equals(controlClassName)) {
					thematicVo.setControlFullScreen(olControl.getControlEnabled());
					thematicVo.setControlFullScreenClassName(olControl.getClassName());
					thematicVo.setControlFullScreenKeys(olControl.getKeys());
					thematicVo.setControlFullScreenLabel(olControl.getLabel());
					thematicVo.setControlFullScreenLabelActive(olControl.getLabelActive());
					thematicVo.setControlFullScreenTipLabel(olControl.getTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_MOUSEPOSITION.equals(controlClassName)) {
					thematicVo.setControlMousePosition(olControl.getControlEnabled());
					thematicVo.setControlMousePositionClassName(olControl.getClassName());
					thematicVo.setControlMousePositionCoordinateFormat(olControl.getCoordinateFormat());
					thematicVo.setControlMousePositionProjection(olControl.getProjection());
					thematicVo.setControlMousePositionUndefinedHTML(olControl.getUndefinedHTML());
				} else if (ConstUtils.GIS_OL_CONTROL_OVERVIEWMAP.equals(controlClassName)) {
					thematicVo.setControlOverviewMap(olControl.getControlEnabled());
					thematicVo.setControlOverviewMapCollapsed(olControl.getCollapsed());
					thematicVo.setControlOverviewMapCollapseLabel(olControl.getCollapseLabel());
					thematicVo.setControlOverviewMapCollapsible(olControl.getCollapsible());
					thematicVo.setControlOverviewMapLabel(olControl.getLabel());
					thematicVo.setControlOverviewMapTipLabel(olControl.getTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_ROTATE.equals(controlClassName)) {
					thematicVo.setControlRotate(olControl.getControlEnabled());
					thematicVo.setControlRotateClassName(olControl.getClassName());
					thematicVo.setControlRotateAutoHide(olControl.getAutoHide());
					thematicVo.setControlRotateDuration(olControl.getDuration());
					thematicVo.setControlRotateLabel(olControl.getLabel());
					thematicVo.setControlRotateTipLabel(olControl.getTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_SCALELINE.equals(controlClassName)) {
					thematicVo.setControlScaleLine(olControl.getControlEnabled());
					thematicVo.setControlScaleLineClassName(olControl.getClassName());
					thematicVo.setControlScaleLineMinWidth(olControl.getMinWidth());
					thematicVo.setControlScaleLineUnits(olControl.getUnits());
				} else if (ConstUtils.GIS_OL_CONTROL_ZOOM.equals(controlClassName)) {
					thematicVo.setControlZoom(olControl.getControlEnabled());
					thematicVo.setControlZoomClassName(olControl.getClassName());
					thematicVo.setControlZoomDelta(olControl.getDelta());
					thematicVo.setControlZoomZoomInLabel(olControl.getZoomInLabel());
					thematicVo.setControlZoomZoomOutLabel(olControl.getZoomOutLabel());
					thematicVo.setControlZoomDuration(olControl.getDuration());
					thematicVo.setControlZoomZoomInTipLabel(olControl.getZoomInTipLabel());
					thematicVo.setControlZoomZoomOutTipLabel(olControl.getZoomOutTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_ZOOMSLIDER.equals(controlClassName)) {
					thematicVo.setControlZoomSlider(olControl.getControlEnabled());
					thematicVo.setControlZoomSliderClassName(olControl.getClassName());
					thematicVo.setControlZoomSliderDuration(olControl.getDuration());
					thematicVo.setControlZoomSliderMaxResolution(olControl.getMaxResolution());
					thematicVo.setControlZoomSliderMinResolution(olControl.getMinResolution());
				} else if (ConstUtils.GIS_OL_CONTROL_ZOOMTOEXTENT.equals(controlClassName)) {
					thematicVo.setControlZoomToExtent(olControl.getControlEnabled());
					thematicVo.setControlZoomToExtentClassName(olControl.getClassName());
					thematicVo.setControlZoomToExtentExtent(olControl.getExtent());
					thematicVo.setControlZoomToExtentLabel(olControl.getLabel());
					thematicVo.setControlZoomToExtentTipLabel(olControl.getTipLabel());
				}
			}

			List<OlInteraction> olInteractionList = olInteractionService.getByOlMap(olMap.getId(), null);
			for (OlInteraction olInteraction : olInteractionList) {
				String interactionClassName = olInteraction.getInteractionClassName();
				if (ConstUtils.GIS_OL_INTERACTION_DOUBLECLICKZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionDoubleClickZoom(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionDoubleClickZoomDelta(olInteraction.getDelta());
					thematicVo.setInteractionDoubleClickZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGANDDROP.equals(interactionClassName)) {
					thematicVo.setInteractionDragAndDrop(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionDragAndDropProjection(olInteraction.getProjection());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGBOX.equals(interactionClassName)) {
					thematicVo.setInteractionDragBox(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionDragBoxCondition(olInteraction.getCondition());
					thematicVo.setInteractionDragBoxStyle(olInteraction.getStyle());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGPAN.equals(interactionClassName)) {
					thematicVo.setInteractionDragPan(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionDragPanKineticDecay(olInteraction.getKineticDecay());
					thematicVo.setInteractionDragPanKineticDelay(olInteraction.getKineticDelay());
					thematicVo.setInteractionDragPanKineticMinVelocity(olInteraction.getKineticMinVelocity());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGROTATE.equals(interactionClassName)) {
					thematicVo.setInteractionDragRotate(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionDragRotateCondition(olInteraction.getCondition());
					thematicVo.setInteractionDragRotateDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGROTATEANDZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionDragRotateAndZoom(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionDragRotateAndZoomCondition(olInteraction.getCondition());
					thematicVo.setInteractionDragRotateAndZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionDragZoom(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionDragZoomCondition(olInteraction.getCondition());
					thematicVo.setInteractionDragZoomDuration(olInteraction.getDuration());
					thematicVo.setInteractionDragZoomStyle(olInteraction.getStyle());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAW.equals(interactionClassName)) {
					thematicVo.setInteractionDraw(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionDrawClickTolerance(olInteraction.getClickTolerance());
					thematicVo.setInteractionDrawSnapTolerance(olInteraction.getSnapTolerance());
					thematicVo.setInteractionDrawWrapX(olInteraction.getWrapX());
				} else if (ConstUtils.GIS_OL_INTERACTION_KEYBOARDPAN.equals(interactionClassName)) {
					thematicVo.setInteractionKeyboardPan(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionKeyboardPanDuration(olInteraction.getDuration());
					thematicVo.setInteractionKeyboardPanPixelDelta(olInteraction.getPixelDelta());
				} else if (ConstUtils.GIS_OL_INTERACTION_KEYBOARDZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionKeyboardZoom(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionKeyboardZoomDelta(olInteraction.getDelta());
					thematicVo.setInteractionKeyboardZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_MODIFY.equals(interactionClassName)) {
					thematicVo.setInteractionModify(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionModifyPixelTolerance(olInteraction.getPixelTolerance());
					thematicVo.setInteractionModifyWrapX(olInteraction.getWrapX());
				} else if (ConstUtils.GIS_OL_INTERACTION_MOUSEWHEELZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionMouseWheelZoom(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionMouseWheelZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_PINCHROTATE.equals(interactionClassName)) {
					thematicVo.setInteractionPinchRotate(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionPinchRotateDuration(olInteraction.getDuration());
					thematicVo.setInteractionPinchRotateThreshold(olInteraction.getThreshold());
				} else if (ConstUtils.GIS_OL_INTERACTION_PINCHZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionPinchZoom(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionPinchZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_SELECT.equals(interactionClassName)) {
					thematicVo.setInteractionSelect(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionSelectMulti(olInteraction.getMulti());
					thematicVo.setInteractionSelectWrapX(olInteraction.getWrapX());
				} else if (ConstUtils.GIS_OL_INTERACTION_SNAP.equals(interactionClassName)) {
					thematicVo.setInteractionSnap(olInteraction.getInteractionEnabled());
					thematicVo.setInteractionSnapPixelTolerance(olInteraction.getPixelTolerance());
				} else if (ConstUtils.GIS_OL_INTERACTION_TRANSLATE.equals(interactionClassName)) {
					thematicVo.setInteractionTranslate(olInteraction.getInteractionEnabled());
				}
			}
		}
	};
}
