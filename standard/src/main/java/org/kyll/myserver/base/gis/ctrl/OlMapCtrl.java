package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.gis.entity.OlMap;
import org.kyll.myserver.base.gis.service.OlMapService;
import org.kyll.myserver.base.gis.vo.OlMapVo;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2015-08-11 16:45
 */
@Controller
@Scope("request")
public class OlMapCtrl {
	@Autowired
	private OlMapService olMapService;

	@RequestMapping("/gis/map/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<OlMap> dataset = olMapService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<OlMapVo> voDataset = POJOUtils.convert(dataset, OlMapVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
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

	@RequestMapping("/gis/map/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		olMapService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
