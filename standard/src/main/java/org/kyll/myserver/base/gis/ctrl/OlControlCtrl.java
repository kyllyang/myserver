package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.gis.entity.OlControl;
import org.kyll.myserver.base.gis.service.OlControlService;
import org.kyll.myserver.base.gis.vo.OlControlVo;
import org.kyll.myserver.base.util.ConstUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-07 8:14
 */
@Controller
@Scope("request")
public class OlControlCtrl {
	@Autowired
	private OlControlService olControlService;

	@RequestMapping("/gis/control/input.ctrl")
	public void input(Long mapId, HttpServletResponse response) throws Exception {
		OlControlVo olControlVo = new OlControlVo();

		List<OlControl> olControlList = olControlService.getByOlMap(mapId, null);
		for (OlControl olControl : olControlList) {
			String controlClassName = olControl.getControlClassName();
			if (ConstUtils.GIS_OL_CONTROL_ATTRIBUTION.equals(controlClassName)) {
				olControlVo.setAttribution(olControl.getControlEnabled());
				olControlVo.setAttributionClassName(olControl.getClassName());
				olControlVo.setAttributionCollapsed(olControl.getCollapsed());
				olControlVo.setAttributionCollapseLabel(olControl.getCollapseLabel());
				olControlVo.setAttributionCollapsible(olControl.getCollapsible());
				olControlVo.setAttributionLabel(olControl.getLabel());
				olControlVo.setAttributionTipLabel(olControl.getTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_FULLSCREEN.equals(controlClassName)) {
				olControlVo.setFullScreen(olControl.getControlEnabled());
				olControlVo.setFullScreenClassName(olControl.getClassName());
				olControlVo.setFullScreenKeys(olControl.getKeys());
				olControlVo.setFullScreenLabel(olControl.getLabel());
				olControlVo.setFullScreenLabelActive(olControl.getLabelActive());
				olControlVo.setFullScreenTipLabel(olControl.getTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_MOUSEPOSITION.equals(controlClassName)) {
				olControlVo.setMousePosition(olControl.getControlEnabled());
				olControlVo.setMousePositionClassName(olControl.getClassName());
				olControlVo.setMousePositionCoordinateFormat(olControl.getCoordinateFormat());
				olControlVo.setMousePositionProjection(olControl.getProjection());
				olControlVo.setMousePositionUndefinedHTML(olControl.getUndefinedHTML());
			} else if (ConstUtils.GIS_OL_CONTROL_OVERVIEWMAP.equals(controlClassName)) {
				olControlVo.setOverviewMap(olControl.getControlEnabled());
				olControlVo.setOverviewMapCollapsed(olControl.getCollapsed());
				olControlVo.setOverviewMapCollapseLabel(olControl.getCollapseLabel());
				olControlVo.setOverviewMapCollapsible(olControl.getCollapsible());
				olControlVo.setOverviewMapLabel(olControl.getLabel());
				olControlVo.setOverviewMapTipLabel(olControl.getTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_ROTATE.equals(controlClassName)) {
				olControlVo.setRotate(olControl.getControlEnabled());
				olControlVo.setRotateClassName(olControl.getClassName());
				olControlVo.setRotateAutoHide(olControl.getAutoHide());
				olControlVo.setRotateDuration(olControl.getDuration());
				olControlVo.setRotateLabel(olControl.getLabel());
				olControlVo.setRotateTipLabel(olControl.getTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_SCALELINE.equals(controlClassName)) {
				olControlVo.setScaleLine(olControl.getControlEnabled());
				olControlVo.setScaleLineClassName(olControl.getClassName());
				olControlVo.setScaleLineMinWidth(olControl.getMinWidth());
				olControlVo.setScaleLineUnits(olControl.getUnits());
			} else if (ConstUtils.GIS_OL_CONTROL_ZOOM.equals(controlClassName)) {
				olControlVo.setZoom(olControl.getControlEnabled());
				olControlVo.setZoomClassName(olControl.getClassName());
				olControlVo.setZoomDelta(olControl.getDelta());
				olControlVo.setZoomZoomInLabel(olControl.getZoomInLabel());
				olControlVo.setZoomZoomOutLabel(olControl.getZoomOutLabel());
				olControlVo.setZoomDuration(olControl.getDuration());
				olControlVo.setZoomZoomInTipLabel(olControl.getZoomInTipLabel());
				olControlVo.setZoomZoomOutTipLabel(olControl.getZoomOutTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_ZOOMSLIDER.equals(controlClassName)) {
				olControlVo.setZoomSlider(olControl.getControlEnabled());
				olControlVo.setZoomSliderClassName(olControl.getClassName());
				olControlVo.setZoomSliderDuration(olControl.getDuration());
				olControlVo.setZoomSliderMaxResolution(olControl.getMaxResolution());
				olControlVo.setZoomSliderMinResolution(olControl.getMinResolution());
			} else if (ConstUtils.GIS_OL_CONTROL_ZOOMTOEXTENT.equals(controlClassName)) {
				olControlVo.setZoomToExtent(olControl.getControlEnabled());
				olControlVo.setZoomToExtentClassName(olControl.getClassName());
				olControlVo.setZoomToExtentExtent(olControl.getExtent());
				olControlVo.setZoomToExtentLabel(olControl.getLabel());
				olControlVo.setZoomToExtentTipLabel(olControl.getTipLabel());
			}
		}

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(olControlVo));
	}

	@RequestMapping("/gis/control/save.ctrl")
	public void save(OlControlVo entityVo, HttpServletResponse response) throws Exception {
		List<OlControl> olControlList = new ArrayList<>();

		OlControl olControlAttribution = new OlControl();
		olControlAttribution.setControlEnabled(entityVo.getAttribution());
		olControlAttribution.setControlClassName(ConstUtils.GIS_OL_CONTROL_ATTRIBUTION);
		olControlAttribution.setClassName(entityVo.getAttributionClassName());
		olControlAttribution.setCollapsed(entityVo.getAttributionCollapsed());
		olControlAttribution.setCollapseLabel(entityVo.getAttributionCollapseLabel());
		olControlAttribution.setCollapsible(entityVo.getAttributionCollapsible());
		olControlAttribution.setLabel(entityVo.getAttributionLabel());
		olControlAttribution.setTipLabel(entityVo.getAttributionTipLabel());
		olControlList.add(olControlAttribution);

		OlControl olControlFullScreen = new OlControl();
		olControlFullScreen.setControlClassName(ConstUtils.GIS_OL_CONTROL_FULLSCREEN);
		olControlFullScreen.setControlEnabled(entityVo.getFullScreen());
		olControlFullScreen.setClassName(entityVo.getFullScreenClassName());
		olControlFullScreen.setKeys(entityVo.getFullScreenKeys());
		olControlFullScreen.setLabel(entityVo.getFullScreenLabel());
		olControlFullScreen.setLabelActive(entityVo.getFullScreenLabelActive());
		olControlFullScreen.setTipLabel(entityVo.getFullScreenTipLabel());
		olControlList.add(olControlFullScreen);

		OlControl olControlMousePosition = new OlControl();
		olControlMousePosition.setControlClassName(ConstUtils.GIS_OL_CONTROL_MOUSEPOSITION);
		olControlMousePosition.setControlEnabled(entityVo.getMousePosition());
		olControlMousePosition.setClassName(entityVo.getMousePositionClassName());
		olControlMousePosition.setCoordinateFormat(entityVo.getMousePositionCoordinateFormat());
		olControlMousePosition.setProjection(entityVo.getMousePositionProjection());
		olControlMousePosition.setUndefinedHTML(entityVo.getMousePositionUndefinedHTML());
		olControlList.add(olControlMousePosition);

		OlControl olControlOverviewMap = new OlControl();
		olControlOverviewMap.setControlClassName(ConstUtils.GIS_OL_CONTROL_OVERVIEWMAP);
		olControlOverviewMap.setControlEnabled(entityVo.getOverviewMap());
		olControlOverviewMap.setCollapsed(entityVo.getOverviewMapCollapsed());
		olControlOverviewMap.setCollapseLabel(entityVo.getOverviewMapCollapseLabel());
		olControlOverviewMap.setCollapsible(entityVo.getOverviewMapCollapsible());
		olControlOverviewMap.setLabel(entityVo.getOverviewMapLabel());
		olControlOverviewMap.setTipLabel(entityVo.getOverviewMapTipLabel());
		olControlList.add(olControlOverviewMap);

		OlControl olControlRotate = new OlControl();
		olControlRotate.setControlClassName(ConstUtils.GIS_OL_CONTROL_ROTATE);
		olControlRotate.setControlEnabled(entityVo.getRotate());
		olControlRotate.setClassName(entityVo.getRotateClassName());
		olControlRotate.setAutoHide(entityVo.getRotateAutoHide());
		olControlRotate.setDuration(entityVo.getRotateDuration());
		olControlRotate.setLabel(entityVo.getRotateLabel());
		olControlRotate.setTipLabel(entityVo.getRotateTipLabel());
		olControlList.add(olControlRotate);

		OlControl olControlScaleLine = new OlControl();
		olControlScaleLine.setControlClassName(ConstUtils.GIS_OL_CONTROL_SCALELINE);
		olControlScaleLine.setControlEnabled(entityVo.getScaleLine());
		olControlScaleLine.setClassName(entityVo.getScaleLineClassName());
		olControlScaleLine.setMinWidth(entityVo.getScaleLineMinWidth());
		olControlScaleLine.setUnits(entityVo.getScaleLineUnits());
		olControlList.add(olControlScaleLine);

		OlControl olControlZoom = new OlControl();
		olControlZoom.setControlClassName(ConstUtils.GIS_OL_CONTROL_ZOOM);
		olControlZoom.setControlEnabled(entityVo.getZoom());
		olControlZoom.setClassName(entityVo.getZoomClassName());
		olControlZoom.setDelta(entityVo.getZoomDelta());
		olControlZoom.setZoomInLabel(entityVo.getZoomZoomInLabel());
		olControlZoom.setZoomOutLabel(entityVo.getZoomZoomOutLabel());
		olControlZoom.setDuration(entityVo.getZoomDuration());
		olControlZoom.setZoomInTipLabel(entityVo.getZoomZoomInTipLabel());
		olControlZoom.setZoomOutTipLabel(entityVo.getZoomZoomOutTipLabel());
		olControlList.add(olControlZoom);

		OlControl olControlZoomSlider = new OlControl();
		olControlZoomSlider.setControlClassName(ConstUtils.GIS_OL_CONTROL_ZOOMSLIDER);
		olControlZoomSlider.setControlEnabled(entityVo.getZoomSlider());
		olControlZoomSlider.setClassName(entityVo.getZoomSliderClassName());
		olControlZoomSlider.setDuration(entityVo.getZoomSliderDuration());
		olControlZoomSlider.setMaxResolution(entityVo.getZoomSliderMaxResolution());
		olControlZoomSlider.setMinResolution(entityVo.getZoomSliderMinResolution());
		olControlList.add(olControlZoomSlider);

		OlControl olControlZoomToExtent = new OlControl();
		olControlZoomToExtent.setControlClassName(ConstUtils.GIS_OL_CONTROL_ZOOMTOEXTENT);
		olControlZoomToExtent.setControlEnabled(entityVo.getZoomToExtent());
		olControlZoomToExtent.setClassName(entityVo.getZoomToExtentClassName());
		olControlZoomToExtent.setExtent(entityVo.getZoomToExtentExtent());
		olControlZoomToExtent.setLabel(entityVo.getZoomToExtentLabel());
		olControlZoomToExtent.setTipLabel(entityVo.getZoomToExtentTipLabel());
		olControlList.add(olControlZoomToExtent);

		olControlService.save(entityVo.getMapId(), olControlList);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
