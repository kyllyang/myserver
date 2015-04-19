package org.kyll.myserver.business.sys.ctrl;

import net.sf.json.JSONArray;
import org.kyll.myserver.business.sys.entity.Dict;
import org.kyll.myserver.business.sys.service.DictService;
import org.kyll.myserver.business.sys.vo.DictVo;
import org.kyll.myserver.util.EntityUtils;
import org.kyll.myserver.util.JsonUtils;
import org.kyll.myserver.util.VoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2014-11-09 06:49
 */
@Controller
@Scope("request")
public class DictCtrl {
	@Autowired
	private DictService dictService;

	@RequestMapping("/sys/dict/tree.ctrl")
	public void tree(Long parentId, HttpServletResponse response) throws Exception {
		JSONArray ja = dictService.getTreeJson(parentId);

		response.setContentType("text/plain");
		response.getWriter().println(ja.toString());
	}

	@RequestMapping("/sys/dict/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Dict entity = dictService.get(id);
		DictVo entityVo = VoUtils.convert(entity, DictVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/sys/dict/save.ctrl")
	public void save(DictVo entityVo, HttpServletResponse response) throws Exception {
		dictService.save(EntityUtils.convert(entityVo, Dict.class, dictService), entityVo.getParentId());

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/sys/dict/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		dictService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private VoUtils.VoHandler<Dict, DictVo> voHandler = (dict, dictVo) -> {
		Dict parent = dict.getParent();
		if (parent != null) {
			dictVo.setParentId(parent.getId());
			dictVo.setParentName(parent.getName());
		}
	};
}
