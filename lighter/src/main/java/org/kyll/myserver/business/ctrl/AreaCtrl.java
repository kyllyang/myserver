package org.kyll.myserver.business.ctrl;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Area;
import org.kyll.myserver.business.service.AreaService;
import org.kyll.myserver.business.vo.AreaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-07-15 13:53
 */
@Controller
@Scope("request")
public class AreaCtrl {
	@Autowired
	private AreaService areaService;

	@RequestMapping("/business/area/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Area> dataset = areaService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<AreaVo> voDataset = POJOUtils.convert(dataset, AreaVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/business/area/save.ctrl")
	public void save(String areaJson, HttpServletResponse response) throws Exception {
		areaService.save(JSONArray.fromObject(areaJson));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/business/area/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		areaService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
