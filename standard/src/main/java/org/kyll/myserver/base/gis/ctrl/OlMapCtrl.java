package org.kyll.myserver.base.gis.ctrl;

import net.sf.json.JSONObject;
import org.kyll.myserver.base.gis.entity.OlMap;
import org.kyll.myserver.base.gis.entity.Thematic;
import org.kyll.myserver.base.gis.service.*;
import org.kyll.myserver.base.gis.vo.OlControlVo;
import org.kyll.myserver.base.gis.vo.OlInteractionVo;
import org.kyll.myserver.base.gis.vo.OlMapVo;
import org.kyll.myserver.base.gis.vo.OlViewVo;
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
	private OlViewService olViewService;
	@Autowired
	private OlControlService olControlService;
	@Autowired
	private OlInteractionService olInteractionService;
	@Autowired
	private OlLayerGroupService olLayerGroupService;

	@RequestMapping("/gis/map/config.ctrl")
	public void input(Long thematicId, HttpServletResponse response) throws Exception {
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
			jo.put("controls", JsonUtils.convert(POJOUtils.convert(olControlService.getByOlMap(mapId), OlControlVo.class)));
			jo.put("interactions", JsonUtils.convert(POJOUtils.convert(olInteractionService.getByOlMap(mapId), OlInteractionVo.class)));
		}
		jo.put("result", resultJo);

		response.setContentType("text/plain");
		response.getWriter().println(jo.toString());
	}
}
