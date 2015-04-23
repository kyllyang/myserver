package org.kyll.myserver.base.app.ctrl;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.app.QueryCondition;
import org.kyll.myserver.base.app.entity.Module;
import org.kyll.myserver.base.sys.service.DictItemService;
import org.kyll.myserver.base.app.service.ModuleService;
import org.kyll.myserver.base.app.vo.ModuleVo;
import org.kyll.myserver.base.sys.vo.SessionVo;
import org.kyll.myserver.base.util.ConstUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2014-11-07 13:10
 */
@Controller
@Scope("request")
public class ModuleCtrl {
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private DictItemService dictItemService;

	private static Map<String, String> typeDictMap;
	private static Map<String, String> funcTypeDictMap;

	@RequestMapping("/app/module/application.ctrl")
	public void application(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Module> entityList = moduleService.getTopModule(RequestUtils.getSessionVo(request).getUserId());
		List<ModuleVo> voList = POJOUtils.convert(entityList, ModuleVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voList));
	}

	@RequestMapping("/app/module/leftMenu.ctrl")
	public void leftMenu(Long moduleId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONArray ja = moduleService.getLeftMenu(((SessionVo) request.getSession().getAttribute("sessionVo")).getUserId(), moduleId);

		response.setContentType("text/plain");
		response.getWriter().println(ja.toString());
	}

	@RequestMapping("/app/module/tree.ctrl")
	public void tree(Boolean checked, Boolean function, Long roleId, HttpServletResponse response) throws Exception {
		JSONArray ja = moduleService.getTreeJson(checked, function, roleId);

		response.setContentType("text/plain");
		response.getWriter().println(ja.toString());
	}

	@RequestMapping("/app/module/list.ctrl")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Module> dataset = moduleService.get(RequestUtils.get(request, ConstUtils.QUERY_CONDITION_PREFIX, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<ModuleVo> voDataset = POJOUtils.convert(dataset, ModuleVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/app/module/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Module entity = moduleService.get(id);
		ModuleVo entityVo = POJOUtils.convert(entity, ModuleVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/app/module/save.ctrl")
	public void save(ModuleVo entityVo, HttpServletResponse response) throws Exception {
		moduleService.save(POJOUtils.convert(entityVo, Module.class, moduleService), entityVo.getParentId());

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/app/module/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		moduleService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private POJOUtils.VoHandler<Module, ModuleVo> voHandler = new POJOUtils.VoHandler<Module, ModuleVo>() {
		@Override
		public void handler(Module module, ModuleVo moduleVo) {
			if (typeDictMap == null) {
				typeDictMap = dictItemService.getMap("app_module_type");
			}
			if (funcTypeDictMap == null) {
				funcTypeDictMap = dictItemService.getMap("app_module_funcType");
			}

			Module parent = module.getParent();
			if (parent != null) {
				moduleVo.setParentId(parent.getId());
				moduleVo.setParentName(parent.getName());
			}
			moduleVo.setTypeText(typeDictMap.get(String.valueOf(module.getType())));
			moduleVo.setFuncTypeText(funcTypeDictMap.get(String.valueOf(module.getFuncType())));
		}
	};
}
