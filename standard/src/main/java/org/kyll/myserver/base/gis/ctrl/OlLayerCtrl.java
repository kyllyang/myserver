package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.gis.entity.OlLayer;
import org.kyll.myserver.base.gis.service.OlLayerService;
import org.kyll.myserver.base.gis.vo.OlLayerVo;
import org.kyll.myserver.base.sys.service.DictItemService;
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
 * Date: 2015-09-09 16:08
 */
@Controller
@Scope("request")
public class OlLayerCtrl {
	@Autowired
	private OlLayerService olLayerService;
	@Autowired
	private DictItemService dictItemService;

	private static Map<String, String> layerClassNameDictMap;

	@RequestMapping("/gis/layer/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<OlLayer> dataset = olLayerService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<OlLayerVo> voDataset = POJOUtils.convert(dataset, OlLayerVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/gis/layer/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		OlLayer entity = olLayerService.get(id);
		OlLayerVo entityVo = POJOUtils.convert(entity, OlLayerVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/gis/layer/save.ctrl")
	public void save(OlLayerVo entityVo, HttpServletResponse response) throws Exception {
		olLayerService.save(POJOUtils.convert(entityVo, OlLayer.class, olLayerService));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/gis/layer/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		olLayerService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private POJOUtils.VoHandler<OlLayer, OlLayerVo> voHandler = new POJOUtils.VoHandler<OlLayer, OlLayerVo>() {
		@Override
		public void handler(OlLayer olLayer, OlLayerVo olLayerVo) {
			if (layerClassNameDictMap == null) {
				layerClassNameDictMap = dictItemService.getMap("gis_layer_class");
			}

			olLayerVo.setLayerClassNameText(layerClassNameDictMap.get(String.valueOf(olLayer.getLayerClassName())));
		}
	};
}
