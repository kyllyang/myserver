package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.gis.entity.OlControl;
import org.kyll.myserver.base.gis.service.OlControlService;
import org.kyll.myserver.base.gis.vo.OlControlVo;
import org.kyll.myserver.base.gis.vo.ThematicVo;
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
		ThematicVo olControlVo = new ThematicVo();

		List<OlControl> olControlList = olControlService.getByOlMap(mapId, null);
		for (OlControl olControl : olControlList) {
			String controlClassName = olControl.getControlClassName();
			if (ConstUtils.GIS_OL_CONTROL_ATTRIBUTION.equals(controlClassName)) {
				olControlVo.setControlAttribution(olControl.getControlEnabled());
				olControlVo.setControlAttributionClassName(olControl.getClassName());
				olControlVo.setControlAttributionCollapsed(olControl.getCollapsed());
				olControlVo.setControlAttributionCollapseLabel(olControl.getCollapseLabel());
				olControlVo.setControlAttributionCollapsible(olControl.getCollapsible());
				olControlVo.setControlAttributionLabel(olControl.getLabel());
				olControlVo.setControlAttributionTipLabel(olControl.getTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_FULLSCREEN.equals(controlClassName)) {
				olControlVo.setControlFullScreen(olControl.getControlEnabled());
				olControlVo.setControlFullScreenClassName(olControl.getClassName());
				olControlVo.setControlFullScreenKeys(olControl.getKeys());
				olControlVo.setControlFullScreenLabel(olControl.getLabel());
				olControlVo.setControlFullScreenLabelActive(olControl.getLabelActive());
				olControlVo.setControlFullScreenTipLabel(olControl.getTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_MOUSEPOSITION.equals(controlClassName)) {
				olControlVo.setControlMousePosition(olControl.getControlEnabled());
				olControlVo.setControlMousePositionClassName(olControl.getClassName());
				olControlVo.setControlMousePositionCoordinateFormat(olControl.getCoordinateFormat());
				olControlVo.setControlMousePositionProjection(olControl.getProjection());
				olControlVo.setControlMousePositionUndefinedHTML(olControl.getUndefinedHTML());
			} else if (ConstUtils.GIS_OL_CONTROL_OVERVIEWMAP.equals(controlClassName)) {
				olControlVo.setControlOverviewMap(olControl.getControlEnabled());
				olControlVo.setControlOverviewMapCollapsed(olControl.getCollapsed());
				olControlVo.setControlOverviewMapCollapseLabel(olControl.getCollapseLabel());
				olControlVo.setControlOverviewMapCollapsible(olControl.getCollapsible());
				olControlVo.setControlOverviewMapLabel(olControl.getLabel());
				olControlVo.setControlOverviewMapTipLabel(olControl.getTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_ROTATE.equals(controlClassName)) {
				olControlVo.setControlRotate(olControl.getControlEnabled());
				olControlVo.setControlRotateClassName(olControl.getClassName());
				olControlVo.setControlRotateAutoHide(olControl.getAutoHide());
				olControlVo.setControlRotateDuration(olControl.getDuration());
				olControlVo.setControlRotateLabel(olControl.getLabel());
				olControlVo.setControlRotateTipLabel(olControl.getTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_SCALELINE.equals(controlClassName)) {
				olControlVo.setControlScaleLine(olControl.getControlEnabled());
				olControlVo.setControlScaleLineClassName(olControl.getClassName());
				olControlVo.setControlScaleLineMinWidth(olControl.getMinWidth());
				olControlVo.setControlScaleLineUnits(olControl.getUnits());
			} else if (ConstUtils.GIS_OL_CONTROL_ZOOM.equals(controlClassName)) {
				olControlVo.setControlZoom(olControl.getControlEnabled());
				olControlVo.setControlZoomClassName(olControl.getClassName());
				olControlVo.setControlZoomDelta(olControl.getDelta());
				olControlVo.setControlZoomZoomInLabel(olControl.getZoomInLabel());
				olControlVo.setControlZoomZoomOutLabel(olControl.getZoomOutLabel());
				olControlVo.setControlZoomDuration(olControl.getDuration());
				olControlVo.setControlZoomZoomInTipLabel(olControl.getZoomInTipLabel());
				olControlVo.setControlZoomZoomOutTipLabel(olControl.getZoomOutTipLabel());
			} else if (ConstUtils.GIS_OL_CONTROL_ZOOMSLIDER.equals(controlClassName)) {
				olControlVo.setControlZoomSlider(olControl.getControlEnabled());
				olControlVo.setControlZoomSliderClassName(olControl.getClassName());
				olControlVo.setControlZoomSliderDuration(olControl.getDuration());
				olControlVo.setControlZoomSliderMaxResolution(olControl.getMaxResolution());
				olControlVo.setControlZoomSliderMinResolution(olControl.getMinResolution());
			} else if (ConstUtils.GIS_OL_CONTROL_ZOOMTOEXTENT.equals(controlClassName)) {
				olControlVo.setControlZoomToExtent(olControl.getControlEnabled());
				olControlVo.setControlZoomToExtentClassName(olControl.getClassName());
				olControlVo.setControlZoomToExtentExtent(olControl.getExtent());
				olControlVo.setControlZoomToExtentLabel(olControl.getLabel());
				olControlVo.setControlZoomToExtentTipLabel(olControl.getTipLabel());
			}
		}

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(olControlVo));
	}

	@RequestMapping("/gis/control/save.ctrl")
	public void save(ThematicVo entityVo, HttpServletResponse response) throws Exception {
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

		olControlService.save(entityVo.getMapId(), olControlList);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
