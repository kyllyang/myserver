package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.gis.entity.OlToolbar;
import org.kyll.myserver.base.gis.service.OlToolbarService;
import org.kyll.myserver.base.gis.vo.ThematicVo;
import org.kyll.myserver.base.util.ConstUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-07 10:02
 */
@Controller
@Scope("request")
public class OlToolbarCtrl {
	@Autowired
	private OlToolbarService olToolbarService;

	@RequestMapping("/gis/toolbar/input.ctrl")
	public void input(Long mapId, HttpServletResponse response) throws Exception {
		ThematicVo olToolbarVo = new ThematicVo();

		List<OlToolbar> olToolbarList = olToolbarService.getByOlMap(mapId, null);
		for (OlToolbar olToolbar : olToolbarList) {
			String toolbarClassName = olToolbar.getToolbarClassName();
			if (ConstUtils.GIS_OL_TOOLBAR_SELECT.equals(toolbarClassName)) {
				olToolbarVo.setToolbarSelect(olToolbar.getToolbarEnabled());
			} else if (ConstUtils.GIS_OL_TOOLBAR_DRAW_POINT.equals(toolbarClassName)) {
				olToolbarVo.setToolbarDrawPoint(olToolbar.getToolbarEnabled());
				olToolbarVo.setToolbarDrawPointStyle(olToolbar.getStyle());
			} else if (ConstUtils.GIS_OL_TOOLBAR_DRAW_LINESTRING.equals(toolbarClassName)) {
				olToolbarVo.setToolbarDrawLineString(olToolbar.getToolbarEnabled());
				olToolbarVo.setToolbarDrawLineStringStyle(olToolbar.getStyle());
			} else if (ConstUtils.GIS_OL_TOOLBAR_DRAW_POLYGON.equals(toolbarClassName)) {
				olToolbarVo.setToolbarDrawPolygon(olToolbar.getToolbarEnabled());
				olToolbarVo.setToolbarDrawPolygonStyle(olToolbar.getStyle());
			} else if (ConstUtils.GIS_OL_TOOLBAR_MODIFY.equals(toolbarClassName)) {
				olToolbarVo.setToolbarModify(olToolbar.getToolbarEnabled());
			} else if (ConstUtils.GIS_OL_TOOLBAR_TRANSLATE.equals(toolbarClassName)) {
				olToolbarVo.setToolbarTranslate(olToolbar.getToolbarEnabled());
			} else if (ConstUtils.GIS_OL_TOOLBAR_ERASE.equals(toolbarClassName)) {
				olToolbarVo.setToolbarErase(olToolbar.getToolbarEnabled());
			} else if (ConstUtils.GIS_OL_TOOLBAR_RESTORE.equals(toolbarClassName)) {
				olToolbarVo.setToolbarRestore(olToolbar.getToolbarEnabled());
			}
		}

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(olToolbarVo));
	}

	@RequestMapping("/gis/toolbar/save.ctrl")
	public void save(ThematicVo entityVo, HttpServletResponse response) throws Exception {
		List<OlToolbar> olToolbarList = new ArrayList<>();

		OlToolbar olToolbarSelect = new OlToolbar();
		olToolbarSelect.setToolbarClassName(ConstUtils.GIS_OL_TOOLBAR_SELECT);
		olToolbarSelect.setToolbarEnabled(entityVo.getToolbarSelect());
		olToolbarList.add(olToolbarSelect);

		OlToolbar olToolbarDrawPoint = new OlToolbar();
		olToolbarDrawPoint.setToolbarClassName(ConstUtils.GIS_OL_TOOLBAR_DRAW_POINT);
		olToolbarDrawPoint.setToolbarEnabled(entityVo.getToolbarDrawPoint());
		olToolbarDrawPoint.setStyle(entityVo.getToolbarDrawPointStyle());
		olToolbarList.add(olToolbarDrawPoint);

		OlToolbar olToolbarDrawLineString = new OlToolbar();
		olToolbarDrawLineString.setToolbarClassName(ConstUtils.GIS_OL_TOOLBAR_DRAW_LINESTRING);
		olToolbarDrawLineString.setToolbarEnabled(entityVo.getToolbarDrawLineString());
		olToolbarDrawLineString.setStyle(entityVo.getToolbarDrawLineStringStyle());
		olToolbarList.add(olToolbarDrawLineString);

		OlToolbar olToolbarDrawPolygon = new OlToolbar();
		olToolbarDrawPolygon.setToolbarClassName(ConstUtils.GIS_OL_TOOLBAR_DRAW_POLYGON);
		olToolbarDrawPolygon.setToolbarEnabled(entityVo.getToolbarDrawPolygon());
		olToolbarDrawPolygon.setStyle(entityVo.getToolbarDrawPolygonStyle());
		olToolbarList.add(olToolbarDrawPolygon);

		OlToolbar olToolbarModify = new OlToolbar();
		olToolbarModify.setToolbarClassName(ConstUtils.GIS_OL_TOOLBAR_MODIFY);
		olToolbarModify.setToolbarEnabled(entityVo.getToolbarModify());
		olToolbarList.add(olToolbarModify);

		OlToolbar olToolbarTranslate = new OlToolbar();
		olToolbarTranslate.setToolbarClassName(ConstUtils.GIS_OL_TOOLBAR_TRANSLATE);
		olToolbarTranslate.setToolbarEnabled(entityVo.getToolbarTranslate());
		olToolbarList.add(olToolbarTranslate);

		OlToolbar olToolbarErase = new OlToolbar();
		olToolbarErase.setToolbarClassName(ConstUtils.GIS_OL_TOOLBAR_ERASE);
		olToolbarErase.setToolbarEnabled(entityVo.getToolbarErase());
		olToolbarList.add(olToolbarErase);

		OlToolbar olToolbarRestore = new OlToolbar();
		olToolbarRestore.setToolbarClassName(ConstUtils.GIS_OL_TOOLBAR_RESTORE);
		olToolbarRestore.setToolbarEnabled(entityVo.getToolbarRestore());
		olToolbarList.add(olToolbarRestore);

		olToolbarService.save(entityVo.getMapId(), olToolbarList);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
