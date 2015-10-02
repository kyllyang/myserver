package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.gis.entity.OlControl;
import org.kyll.myserver.base.gis.entity.OlMap;
import org.kyll.myserver.base.gis.entity.OlView;
import org.kyll.myserver.base.gis.entity.Thematic;
import org.kyll.myserver.base.gis.service.OlControlService;
import org.kyll.myserver.base.gis.service.OlMapService;
import org.kyll.myserver.base.gis.service.OlViewService;
import org.kyll.myserver.base.gis.service.ThematicService;
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
		olView.setResolutions(entityVo.getViewResolutions());
		olView.setResolution(entityVo.getViewResolution());

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

		thematicService.save(POJOUtils.convert(entityVo, Thematic.class, thematicService), olMap, olView, entityVo.getLayerGroup(), olControlList);

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
				thematicVo.setViewResolutions(olView.getResolutions());
				thematicVo.setViewResolution(olView.getResolution());
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
		}
	};
}
