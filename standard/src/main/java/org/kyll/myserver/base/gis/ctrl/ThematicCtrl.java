package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.gis.entity.OlMap;
import org.kyll.myserver.base.gis.entity.OlView;
import org.kyll.myserver.base.gis.entity.Thematic;
import org.kyll.myserver.base.gis.service.OlMapService;
import org.kyll.myserver.base.gis.service.OlViewService;
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
import java.util.List;

/**
 * User: Kyll
 * Date: 2015-04-20 17:53
 */
@Controller
@Scope("request")
public class ThematicCtrl {
	@Autowired
	private ThematicService thematicService;
	@Autowired
	private OlMapService olMapService;
	@Autowired
	private OlViewService olViewService;

	@RequestMapping("/gis/thematic/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Thematic> dataset = thematicService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<ThematicVo> voDataset = POJOUtils.convert(dataset, ThematicVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/gis/thematic/list.ctrl")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Thematic> list = thematicService.get(RequestUtils.getQueryCondition(request, QueryCondition.class));
		List<ThematicVo> voList = POJOUtils.convert(list, ThematicVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voList));
	}

	@RequestMapping("/gis/thematic/input.ctrl")
	public void input(Long id, HttpServletResponse response) throws Exception {
		Thematic entity = thematicService.get(id);
		ThematicVo entityVo = POJOUtils.convert(entity, ThematicVo.class, voHandler);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(entityVo));
	}

	@RequestMapping("/gis/thematic/save.ctrl")
	public void save(ThematicVo entityVo, HttpServletResponse response) throws Exception {
		Long mapId = entityVo.getMapId();
		OlMap olMap;
		if (mapId == null) {
			olMap = new OlMap();
		} else {
			olMap = olMapService.get(mapId);
		}
		olMap.setLogo(entityVo.getMapLogo());
		olMap.setLoadTilesWhileAnimating(entityVo.getMapLoadTilesWhileAnimating());
		olMap.setLoadTilesWhileInteracting(entityVo.getMapLoadTilesWhileInteracting());
		olMap.setRenderer(entityVo.getMapRenderer());

		Long viewId = entityVo.getViewId();
		OlView olView;
		if (viewId == null) {
			olView = new OlView();
		} else {
			olView = olViewService.get(viewId);
		}
		olView.setProjection(entityVo.getViewProjection());
		olView.setCenter(entityVo.getViewCenter());
		olView.setExtent(entityVo.getViewExtent());
		olView.setResolutions(entityVo.getViewResolutions());
		olView.setResolution(entityVo.getViewResolution());

		thematicService.save(POJOUtils.convert(entityVo, Thematic.class, thematicService), olMap, olView, entityVo.getLayerGroup());

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/gis/thematic/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		thematicService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	private POJOUtils.VoHandler<Thematic, ThematicVo> voHandler = (thematic, thematicVo) -> {
		OlMap olMap = thematic.getOlMap();
		if (olMap != null) {
			thematicVo.setMapId(olMap.getId());
			thematicVo.setMapLogo(olMap.getLogo());
			thematicVo.setMapLoadTilesWhileAnimating(olMap.getLoadTilesWhileAnimating());
			thematicVo.setMapLoadTilesWhileInteracting(olMap.getLoadTilesWhileInteracting());
			thematicVo.setMapRenderer(olMap.getRenderer());

			OlView olView = olViewService.getByOlMap(olMap.getId());
			if (olView != null) {
				thematicVo.setViewId(olView.getId());
				thematicVo.setViewProjection(olView.getProjection());
				thematicVo.setViewCenter(olView.getCenter());
				thematicVo.setViewExtent(olView.getExtent());
				thematicVo.setViewResolutions(olView.getResolutions());
				thematicVo.setViewResolution(olView.getResolution());
			}
		}
	};
}
