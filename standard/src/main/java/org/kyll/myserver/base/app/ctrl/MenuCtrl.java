package org.kyll.myserver.base.app.ctrl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kyll.myserver.base.app.entity.Menu;
import org.kyll.myserver.base.app.entity.MenuApplicationThematic;
import org.kyll.myserver.base.app.entity.Module;
import org.kyll.myserver.base.app.service.MenuApplicationThematicService;
import org.kyll.myserver.base.app.service.MenuService;
import org.kyll.myserver.base.app.vo.MenuVo;
import org.kyll.myserver.base.gis.entity.Thematic;
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
 * Date: 2015-04-20 18:53
 */
@Controller
@Scope("request")
public class MenuCtrl {
	@Autowired
	private MenuService menuService;
	@Autowired
	private MenuApplicationThematicService menuApplicationThematicService;

	@RequestMapping("/app/menu/function.ctrl")
	public void function(Long applicationId, Long thematicId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONArray ja = menuService.getFunctionJson(applicationId, thematicId, RequestUtils.getSessionVo(request).getUserId());

		response.setContentType("text/plain");
		response.getWriter().println(ja.toString());
	}

	@RequestMapping("/app/menu/tree.ctrl")
	public void tree(Long parentId, HttpServletResponse response) throws Exception {
		JSONArray ja = menuService.getTreeJson(parentId);

		response.setContentType("text/plain");
		response.getWriter().println(ja.toString());
	}

	@RequestMapping("/app/menu/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Menu entity = menuService.get(id);
		MenuVo entityVo = POJOUtils.convert(entity, MenuVo.class, (menu, menuVo) -> {
			Menu parent = menu.getParent();
			if (parent != null) {
				menuVo.setParentId(parent.getId());
				menuVo.setParentName(parent.getName());
			}
			Module function = menu.getFunction();
			if (function != null) {
				menuVo.setFunctionId(function.getId());
				menuVo.setFunctionName(function.getName());
			}
			JSONArray ja = new JSONArray();
			for (MenuApplicationThematic menuApplicationThematic : menuApplicationThematicService.getByMenu(menu.getId())) {
				Module application = menuApplicationThematic.getApplication();
				Thematic thematic = menuApplicationThematic.getThematic();

				JSONObject jo = new JSONObject();
				jo.put("id", menuApplicationThematic.getId());
				if (application != null) {
					jo.put("moduleId", application.getId());
				}
				if (thematic != null) {
					jo.put("thematicId", thematic.getId());
				}
				ja.add(jo);
			}
			menuVo.setMats(ja.toString());
		});

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/app/menu/save.ctrl")
	public void save(MenuVo entityVo, HttpServletResponse response) throws Exception {
		menuService.save(POJOUtils.convert(entityVo, Menu.class, menuService), entityVo.getParentId(), entityVo.getFunctionId(), entityVo.getMats());

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/app/menu/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		menuService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
