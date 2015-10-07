package org.kyll.myserver.base.gis.ctrl;

import net.sf.json.JSONObject;
import org.kyll.myserver.base.gis.entity.OlMap;
import org.kyll.myserver.base.gis.entity.Thematic;
import org.kyll.myserver.base.gis.service.*;
import org.kyll.myserver.base.gis.vo.*;
import org.kyll.myserver.base.util.ConstUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-10-03 10:36
 */
@Controller
@Scope("request")
public class OlMapCtrl {
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
	@Autowired
	private OlLayerGroupService olLayerGroupService;
	@Autowired
	private OlToolbarService olToolbarService;

	@RequestMapping("/gis/map/config.ctrl")
	public void config(Long thematicId, HttpServletResponse response) throws Exception {
		Thematic thematic = thematicService.get(thematicId);
		OlMap olMap = thematic.getOlMap();

		JSONObject jo = new JSONObject();
		JSONObject resultJo = new JSONObject();
		if (olMap == null) {
			resultJo.put("success", false);
		} else {
			resultJo.put("success", true);

			Long mapId = olMap.getId();
			jo.put("map", JsonUtils.convert(POJOUtils.convert(olMap, OlMapVo.class)));
			jo.put("view", JsonUtils.convert(POJOUtils.convert(olViewService.getByOlMap(mapId), OlViewVo.class)));
			jo.put("layerGroup", olLayerGroupService.getTreeJson(mapId));
			jo.put("controls", JsonUtils.convert(POJOUtils.convert(olControlService.getByOlMap(mapId, ConstUtils.GIS_OL_CONTROL_ENABLED), OlControlVo.class)));
			jo.put("interactions", JsonUtils.convert(POJOUtils.convert(olInteractionService.getByOlMap(mapId, ConstUtils.GIS_OL_INTERACTION_ENABLED), OlInteractionVo.class)));
			jo.put("toolbars", JsonUtils.convert(POJOUtils.convert(olToolbarService.getByOlMap(mapId, ConstUtils.GIS_OL_TOOLBAR_ENABLED), OlToolbarVo.class)));
		}
		jo.put("result", resultJo);

		response.setContentType("text/plain");
		response.getWriter().println(jo.toString());
	}

	@RequestMapping("/gis/map/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		OlMap entity = olMapService.get(id);
		OlMapVo entityVo = POJOUtils.convert(entity, OlMapVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/gis/map/save.ctrl")
	public void save(OlMapVo entityVo, HttpServletResponse response) throws Exception {
		olMapService.save(POJOUtils.convert(entityVo, OlMap.class, olMapService));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
