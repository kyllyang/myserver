package org.kyll.myserver.base.gis.ctrl;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.gis.service.OlLayerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-10-01 11:59
 */
@Controller
@Scope("request")
public class OlLayerGroupCtrl {
	@Autowired
	private OlLayerGroupService olLayerGroupService;

	@RequestMapping("/gis/layergroup/tree.ctrl")
	public void tree(Long mapId, HttpServletResponse response) throws Exception {
		JSONArray ja = olLayerGroupService.getTreeJson(mapId);

		response.setContentType("text/plain");
		response.getWriter().println(ja.toString());
	}
}
