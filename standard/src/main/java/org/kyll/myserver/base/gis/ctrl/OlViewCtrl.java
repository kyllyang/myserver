package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.gis.entity.OlMap;
import org.kyll.myserver.base.gis.entity.OlView;
import org.kyll.myserver.base.gis.service.OlViewService;
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
 * Date: 2015-10-06 20:17
 */
@Controller
@Scope("request")
public class OlViewCtrl {
	@Autowired
	private OlViewService olViewService;

	@RequestMapping("/gis/view/input.ctrl")
	public void input(Long mapId, HttpServletResponse response) throws Exception {
		OlView entity = olViewService.getByOlMap(mapId);
		OlViewVo entityVo = POJOUtils.convert(entity, OlViewVo.class, (olView, olViewVo) -> {
			OlMap olMap = olView.getOlMap();
			if (olMap != null) {
				olViewVo.setMapId(olMap.getId());
			}
		});

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/gis/view/save.ctrl")
	public void save(OlViewVo entityVo, HttpServletResponse response) throws Exception {
		olViewService.save(POJOUtils.convert(entityVo, OlView.class, olViewService), entityVo.getMapId());

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
