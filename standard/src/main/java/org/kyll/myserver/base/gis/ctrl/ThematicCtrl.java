package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.util.JsonUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("/gis/thematic/list.ctrl")
	public void list(HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("id", 1);
		map.put("name", "专题1");
		list.add(map);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(list));
	}
}
