package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.gis.entity.Thematic;
import org.kyll.myserver.base.gis.service.ThematicService;
import org.kyll.myserver.base.gis.vo.ThematicVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2015-04-20 17:53
 */
@Controller
@Scope("request")
public class ThematicCtrl {
	@Autowired
	private ThematicService thematicService;

	@RequestMapping("/gis/thematic/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Thematic> dataset = thematicService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<ThematicVo> voDataset = POJOUtils.convert(dataset, ThematicVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/gis/thematic/list.ctrl")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Thematic> list = thematicService.get(RequestUtils.getQueryCondition(request, QueryCondition.class));
		List<ThematicVo> voList = POJOUtils.convert(list, ThematicVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voList));
	}

	@RequestMapping("/gis/thematic/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Thematic entity = thematicService.get(id);
		ThematicVo entityVo = POJOUtils.convert(entity, ThematicVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/gis/thematic/save.ctrl")
	public void save(ThematicVo entityVo, HttpServletResponse response) throws Exception {
		thematicService.save(POJOUtils.convert(entityVo, Thematic.class, thematicService));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/gis/thematic/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		thematicService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
