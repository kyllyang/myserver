package org.kyll.myserver.base.sys.ctrl;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.sys.entity.Config;
import org.kyll.myserver.base.sys.service.ConfigService;
import org.kyll.myserver.base.sys.vo.ConfigVo;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-05-23 12:48
 */
@Controller
@Scope("request")
public class ConfigCtrl {
	@Autowired
	private ConfigService configService;

	@RequestMapping("/sys/config/list.ctrl")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Config> dataset = configService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<ConfigVo> voDataset = POJOUtils.convert(dataset, ConfigVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/sys/config/save.ctrl")
	public void save(String configJson, HttpServletResponse response) throws Exception {
		configService.save(JSONArray.fromObject(configJson));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/sys/config/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		configService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
