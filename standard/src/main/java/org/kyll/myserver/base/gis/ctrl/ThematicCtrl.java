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
		if ("1".equals(entityVo.getControlAttribution())) {
			OlControl olControl = new OlControl();
			olControl.setControlClassName(ConstUtils.GIS_OL_CONTROL_ATTRIBUTION);
			olControl.setCollapsed(entityVo.getControlAttributionCollapsed());
			olControl.setCollapseLabel(entityVo.getControlAttributionCollapseLabel());
			olControl.setCollapsible(entityVo.getControlAttributionCollapsible());
			olControl.setLabel(entityVo.getControlAttributionLabel());
			olControl.setTipLabel(entityVo.getControlAttributionTipLabel());
			olControlList.add(olControl);
		}
		if ("1".equals(entityVo.getControlFullScreen())) {
			OlControl olControl = new OlControl();
			olControl.setControlClassName(ConstUtils.GIS_OL_CONTROL_FULLSCREEN);
			olControl.setKeys(entityVo.getControlFullScreenKeys());
			olControl.setLabel(entityVo.getControlFullScreenLabel());
			olControl.setLabelActive(entityVo.getControlFullScreenLabelActive());
			olControl.setTipLabel(entityVo.getControlFullScreenTipLabel());
			olControlList.add(olControl);
		}
		if ("1".equals(entityVo.getControlMousePosition())) {
			OlControl olControl = new OlControl();
			olControl.setControlClassName(ConstUtils.GIS_OL_CONTROL_MOUSEPOSITION);
			olControl.setCoordinateFormat(entityVo.getControlMousePositionCoordinateFormat());
			olControl.setProjection(entityVo.getControlMousePositionProjection());
			olControl.setUndefinedHTML(entityVo.getControlMousePositionUndefinedHTML());
			olControlList.add(olControl);
		}
		if ("1".equals(entityVo.getControlOverviewMap())) {
			OlControl olControl = new OlControl();
			olControl.setControlClassName(ConstUtils.GIS_OL_CONTROL_OVERVIEWMAP);
			olControl.setCollapsed(entityVo.getControlOverviewMapCollapsed());
			olControl.setCollapseLabel(entityVo.getControlOverviewMapCollapseLabel());
			olControl.setCollapsible(entityVo.getControlOverviewMapCollapsible());
			olControl.setLabel(entityVo.getControlOverviewMapLabel());
			olControl.setTipLabel(entityVo.getControlOverviewMapTipLabel());
			olControlList.add(olControl);
		}
		if ("1".equals(entityVo.getControlRotate())) {
			OlControl olControl = new OlControl();
			olControl.setControlClassName(ConstUtils.GIS_OL_CONTROL_ROTATE);
			olControl.setAutoHide(entityVo.getControlRotateAutoHide());
			olControl.setDuration(entityVo.getControlRotateDuration());
			olControl.setLabel(entityVo.getControlRotateLabel());
			olControl.setTipLabel(entityVo.getControlRotateTipLabel());
			olControlList.add(olControl);
		}
		if ("1".equals(entityVo.getControlScaleLine())) {
			OlControl olControl = new OlControl();
			olControl.setControlClassName(ConstUtils.GIS_OL_CONTROL_SCALELINE);
			olControl.setMinWidth(entityVo.getControlScaleLineMinWidth());
			olControl.setUnits(entityVo.getControlScaleLineUnits());
			olControlList.add(olControl);
		}
		if ("1".equals(entityVo.getControlZoom())) {
			OlControl olControl = new OlControl();
			olControl.setControlClassName(ConstUtils.GIS_OL_CONTROL_ZOOM);
			olControl.setDelta(entityVo.getControlZoomDelta());
			olControl.setZoomInLabel(entityVo.getControlZoomZoomInLabel());
			olControl.setZoomOutLabel(entityVo.getControlZoomZoomOutLabel());
			olControl.setDuration(entityVo.getControlZoomDuration());
			olControl.setZoomInTipLabel(entityVo.getControlZoomZoomInTipLabel());
			olControl.setZoomOutTipLabel(entityVo.getControlZoomZoomOutTipLabel());
			olControlList.add(olControl);
		}
		if ("1".equals(entityVo.getControlZoomSlider())) {
			OlControl olControl = new OlControl();
			olControl.setControlClassName(ConstUtils.GIS_OL_CONTROL_ZOOMSLIDER);
			olControl.setDuration(entityVo.getControlZoomSliderDuration());
			olControl.setMaxResolution(entityVo.getControlZoomSliderMaxResolution());
			olControl.setMinResolution(entityVo.getControlZoomSliderMinResolution());
			olControlList.add(olControl);
		}
		if ("1".equals(entityVo.getControlZoomToExtent())) {
			OlControl olControl = new OlControl();
			olControl.setControlClassName(ConstUtils.GIS_OL_CONTROL_ZOOMTOEXTENT);
			olControl.setExtent(entityVo.getControlZoomToExtentExtent());
			olControl.setLabel(entityVo.getControlZoomToExtentLabel());
			olControl.setTipLabel(entityVo.getControlZoomToExtentTipLabel());
			olControlList.add(olControl);
		}

		List<OlInteraction> olInteractionList = new ArrayList<>();
		if ("1".equals(entityVo.getInteractionDoubleClickZoom())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DOUBLECLICKZOOM);
			olInteraction.setDelta(entityVo.getInteractionDoubleClickZoomDelta());
			olInteraction.setDuration(entityVo.getInteractionDoubleClickZoomDuration());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionDragAndDrop())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGANDDROP);
			olInteraction.setProjection(entityVo.getInteractionDragAndDropProjection());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionDragBox())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGBOX);
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionDragPan())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGPAN);
			olInteraction.setKineticDecay(entityVo.getInteractionDragPanKineticDecay());
			olInteraction.setKineticDelay(entityVo.getInteractionDragPanKineticDelay());
			olInteraction.setKineticMinVelocity(entityVo.getInteractionDragPanKineticMinVelocity());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionDragRotate())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGROTATE);
			olInteraction.setDuration(entityVo.getInteractionDragRotateDuration());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionDragRotateAndZoom())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGROTATEANDZOOM);
			olInteraction.setDuration(entityVo.getInteractionDragRotateAndZoomDuration());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionDragZoom())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGZOOM);
			olInteraction.setDuration(entityVo.getInteractionDragZoomDuration());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionDraw())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAW);
			olInteraction.setClickTolerance(entityVo.getInteractionDrawClickTolerance());
			olInteraction.setSnapTolerance(entityVo.getInteractionDrawSnapTolerance());
			olInteraction.setWrapX(entityVo.getInteractionDrawWrapX());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionKeyboardPan())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_KEYBOARDPAN);
			olInteraction.setDuration(entityVo.getInteractionKeyboardPanDuration());
			olInteraction.setPixelDelta(entityVo.getInteractionKeyboardPanPixelDelta());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionKeyboardZoom())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_KEYBOARDZOOM);
			olInteraction.setDuration(entityVo.getInteractionKeyboardZoomDuration());
			olInteraction.setDelta(entityVo.getInteractionKeyboardZoomDelta());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionModify())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_MODIFY);
			olInteraction.setPixelTolerance(entityVo.getInteractionModifyPixelTolerance());
			olInteraction.setWrapX(entityVo.getInteractionModifyWrapX());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionMouseWheelZoom())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_MOUSEWHEELZOOM);
			olInteraction.setDuration(entityVo.getInteractionMouseWheelZoomDuration());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionPinchRotate())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_PINCHROTATE);
			olInteraction.setDuration(entityVo.getInteractionPinchRotateDuration());
			olInteraction.setThreshold(entityVo.getInteractionPinchRotateThreshold());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionPinchZoom())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_PINCHZOOM);
			olInteraction.setDuration(entityVo.getInteractionPinchZoomDuration());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionSelect())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_SELECT);
			olInteraction.setMulti(entityVo.getInteractionSelectMulti());
			olInteraction.setWrapX(entityVo.getInteractionSelectWrapX());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionSnap())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_SNAP);
			olInteraction.setPixelTolerance(entityVo.getInteractionSnapPixelTolerance());
			olInteractionList.add(olInteraction);
		}
		if ("1".equals(entityVo.getInteractionTranslate())) {
			OlInteraction olInteraction = new OlInteraction();
			olInteraction.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_TRANSLATE);
			olInteractionList.add(olInteraction);
		}

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

			List<OlControl> olControlList = olControlService.getByOlMap(olMap.getId());
			for (OlControl olControl : olControlList) {
				String controlClassName = olControl.getControlClassName();
				if (ConstUtils.GIS_OL_CONTROL_ATTRIBUTION.equals(controlClassName)) {
					thematicVo.setControlAttribution("1");
					thematicVo.setControlAttributionCollapsed(olControl.getCollapsed());
					thematicVo.setControlAttributionCollapseLabel(olControl.getCollapseLabel());
					thematicVo.setControlAttributionCollapsible(olControl.getCollapsible());
					thematicVo.setControlAttributionLabel(olControl.getLabel());
					thematicVo.setControlAttributionTipLabel(olControl.getTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_FULLSCREEN.equals(controlClassName)) {
					thematicVo.setControlFullScreen("1");
					thematicVo.setControlFullScreenKeys(olControl.getKeys());
					thematicVo.setControlFullScreenLabel(olControl.getLabel());
					thematicVo.setControlFullScreenLabelActive(olControl.getLabelActive());
					thematicVo.setControlFullScreenTipLabel(olControl.getTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_MOUSEPOSITION.equals(controlClassName)) {
					thematicVo.setControlMousePosition("1");
					thematicVo.setControlMousePositionCoordinateFormat(olControl.getCoordinateFormat());
					thematicVo.setControlMousePositionProjection(olControl.getProjection());
					thematicVo.setControlMousePositionUndefinedHTML(olControl.getUndefinedHTML());
				} else if (ConstUtils.GIS_OL_CONTROL_OVERVIEWMAP.equals(controlClassName)) {
					thematicVo.setControlOverviewMap("1");
					thematicVo.setControlOverviewMapCollapsed(olControl.getCollapsed());
					thematicVo.setControlOverviewMapCollapseLabel(olControl.getCollapseLabel());
					thematicVo.setControlOverviewMapCollapsible(olControl.getCollapsible());
					thematicVo.setControlOverviewMapLabel(olControl.getLabel());
					thematicVo.setControlOverviewMapTipLabel(olControl.getTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_ROTATE.equals(controlClassName)) {
					thematicVo.setControlRotate("1");
					thematicVo.setControlRotateAutoHide(olControl.getAutoHide());
					thematicVo.setControlRotateDuration(olControl.getDuration());
					thematicVo.setControlRotateLabel(olControl.getLabel());
					thematicVo.setControlRotateTipLabel(olControl.getTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_SCALELINE.equals(controlClassName)) {
					thematicVo.setControlScaleLine("1");
					thematicVo.setControlScaleLineMinWidth(olControl.getMinWidth());
					thematicVo.setControlScaleLineUnits(olControl.getUnits());
				} else if (ConstUtils.GIS_OL_CONTROL_ZOOM.equals(controlClassName)) {
					thematicVo.setControlZoom("1");
					thematicVo.setControlZoomDelta(olControl.getDelta());
					thematicVo.setControlZoomZoomInLabel(olControl.getZoomInLabel());
					thematicVo.setControlZoomZoomOutLabel(olControl.getZoomOutLabel());
					thematicVo.setControlZoomDuration(olControl.getDuration());
					thematicVo.setControlZoomZoomInTipLabel(olControl.getZoomInTipLabel());
					thematicVo.setControlZoomZoomOutTipLabel(olControl.getZoomOutTipLabel());
				} else if (ConstUtils.GIS_OL_CONTROL_ZOOMSLIDER.equals(controlClassName)) {
					thematicVo.setControlZoomSlider("1");
					thematicVo.setControlZoomSliderDuration(olControl.getDuration());
					thematicVo.setControlZoomSliderMaxResolution(olControl.getMaxResolution());
					thematicVo.setControlZoomSliderMinResolution(olControl.getMinResolution());
				} else if (ConstUtils.GIS_OL_CONTROL_ZOOMTOEXTENT.equals(controlClassName)) {
					thematicVo.setControlZoomToExtent("1");
					thematicVo.setControlZoomToExtentExtent(olControl.getExtent());
					thematicVo.setControlZoomToExtentLabel(olControl.getLabel());
					thematicVo.setControlZoomToExtentTipLabel(olControl.getTipLabel());
				}
			}

			List<OlInteraction> olInteractionList = olInteractionService.getByOlMap(olMap.getId());
			for (OlInteraction olInteraction : olInteractionList) {
				String interactionClassName = olInteraction.getInteractionClassName();
				if (ConstUtils.GIS_OL_INTERACTION_DOUBLECLICKZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionDoubleClickZoom("1");
					thematicVo.setInteractionDoubleClickZoomDelta(olInteraction.getDelta());
					thematicVo.setInteractionDoubleClickZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGANDDROP.equals(interactionClassName)) {
					thematicVo.setInteractionDragAndDrop("1");
					thematicVo.setInteractionDragAndDropProjection(olInteraction.getProjection());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGBOX.equals(interactionClassName)) {
					thematicVo.setInteractionDragBox("1");
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGPAN.equals(interactionClassName)) {
					thematicVo.setInteractionDragPan("1");
					thematicVo.setInteractionDragPanKineticDecay(olInteraction.getKineticDecay());
					thematicVo.setInteractionDragPanKineticDelay(olInteraction.getKineticDelay());
					thematicVo.setInteractionDragPanKineticMinVelocity(olInteraction.getKineticMinVelocity());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGROTATE.equals(interactionClassName)) {
					thematicVo.setInteractionDragRotate("1");
					thematicVo.setInteractionDragRotateDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGROTATEANDZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionDragRotateAndZoom("1");
					thematicVo.setInteractionDragRotateAndZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAGZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionDragZoom("1");
					thematicVo.setInteractionDragZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_DRAW.equals(interactionClassName)) {
					thematicVo.setInteractionDraw("1");
					thematicVo.setInteractionDrawClickTolerance(olInteraction.getClickTolerance());
					thematicVo.setInteractionDrawSnapTolerance(olInteraction.getSnapTolerance());
					thematicVo.setInteractionDrawWrapX(olInteraction.getWrapX());
				} else if (ConstUtils.GIS_OL_INTERACTION_KEYBOARDPAN.equals(interactionClassName)) {
					thematicVo.setInteractionKeyboardPan("1");
					thematicVo.setInteractionKeyboardPanDuration(olInteraction.getDuration());
					thematicVo.setInteractionKeyboardPanPixelDelta(olInteraction.getPixelDelta());
				} else if (ConstUtils.GIS_OL_INTERACTION_KEYBOARDZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionKeyboardZoom("1");
					thematicVo.setInteractionKeyboardZoomDelta(olInteraction.getDelta());
					thematicVo.setInteractionKeyboardZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_MODIFY.equals(interactionClassName)) {
					thematicVo.setInteractionModify("1");
					thematicVo.setInteractionModifyPixelTolerance(olInteraction.getPixelTolerance());
					thematicVo.setInteractionModifyWrapX(olInteraction.getWrapX());
				} else if (ConstUtils.GIS_OL_INTERACTION_MOUSEWHEELZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionMouseWheelZoom("1");
					thematicVo.setInteractionMouseWheelZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_PINCHROTATE.equals(interactionClassName)) {
					thematicVo.setInteractionPinchRotate("1");
					thematicVo.setInteractionPinchRotateDuration(olInteraction.getDuration());
					thematicVo.setInteractionPinchRotateThreshold(olInteraction.getThreshold());
				} else if (ConstUtils.GIS_OL_INTERACTION_PINCHZOOM.equals(interactionClassName)) {
					thematicVo.setInteractionPinchZoom("1");
					thematicVo.setInteractionPinchZoomDuration(olInteraction.getDuration());
				} else if (ConstUtils.GIS_OL_INTERACTION_SELECT.equals(interactionClassName)) {
					thematicVo.setInteractionSelect("1");
					thematicVo.setInteractionSelectMulti(olInteraction.getMulti());
					thematicVo.setInteractionSelectWrapX(olInteraction.getWrapX());
				} else if (ConstUtils.GIS_OL_INTERACTION_SNAP.equals(interactionClassName)) {
					thematicVo.setInteractionSnap("1");
					thematicVo.setInteractionSnapPixelTolerance(olInteraction.getPixelTolerance());
				} else if (ConstUtils.GIS_OL_INTERACTION_TRANSLATE.equals(interactionClassName)) {
					thematicVo.setInteractionTranslate("1");
				}
			}
		}
	};
}
