package org.kyll.myserver.base.app.ctrl;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.app.entity.Menu;
import org.kyll.myserver.base.app.entity.Module;
import org.kyll.myserver.base.app.service.MenuService;
import org.kyll.myserver.base.app.vo.MenuVo;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Date: 2015-04-20 18:53
 */
@Controller
@Scope("request")
public class MenuCtrl {
	@Autowired
	private MenuService menuService;

	@RequestMapping("/app/menu/function.ctrl")
	public void function(HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map1 = new HashMap<>();
		map1.put("id", 1);
		map1.put("text", "数据字典");
		map1.put("funcType", "2");
		Map<String, Object> funcCodeMap1 = new HashMap<>();
		funcCodeMap1.put("className", "Base.sys.dict.DictContainer");
		map1.put("funcCode", funcCodeMap1);
		map1.put("leaf", true);

		Map<String, Object> map2 = new HashMap<>();
		map2.put("id", 2);
		map2.put("text", "模块功能");
		map2.put("funcType", "2");
		Map<String, Object> funcCodeMap2 = new HashMap<>();
		funcCodeMap2.put("className", "Base.app.module.ModuleContainer");
		map2.put("funcCode", funcCodeMap2);
		map2.put("leaf", true);

		Map<String, Object> map3 = new HashMap<>();
		map3.put("id", 3);
		map3.put("text", "角色管理");
		map3.put("funcType", "2");
		Map<String, Object> funcCodeMap3 = new HashMap<>();
		funcCodeMap3.put("className", "Base.sys.role.RoleContainer");
		map3.put("funcCode", funcCodeMap3);
		map3.put("leaf", true);

		Map<String, Object> map4 = new HashMap<>();
		map4.put("id", 4);
		map4.put("text", "菜单管理");
		map4.put("funcType", "2");
		Map<String, Object> funcCodeMap4 = new HashMap<>();
		funcCodeMap4.put("className", "Base.app.menu.MenuContainer");
		map4.put("funcCode", funcCodeMap4);
		map4.put("leaf", true);

		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(list));
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
		});

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/app/menu/save.ctrl")
	public void save(MenuVo entityVo, HttpServletResponse response) throws Exception {
		menuService.save(POJOUtils.convert(entityVo, Menu.class, menuService), entityVo.getParentId());

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
