package org.kyll.myserver.base.gis.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kyll.myserver.base.gis.dao.OlLayerGroupDao;
import org.kyll.myserver.base.gis.entity.OlLayer;
import org.kyll.myserver.base.gis.entity.OlLayerGroup;
import org.kyll.myserver.base.gis.service.OlLayerGroupService;
import org.kyll.myserver.base.sys.service.DictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * User: Kyll
 * Date: 2015-10-01 11:59
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OlLayerGroupServiceImpl implements OlLayerGroupService {
	@Autowired
	private OlLayerGroupDao olLayerGroupDao;
	@Autowired
	private DictItemService dictItemService;

	private static Map<String, String> layerClassNameDictMap;

	@Override
	public OlLayerGroup get(Long id) {
		return olLayerGroupDao.get(id);
	}

	@Override
	public JSONArray getTreeJson(Long mapId) {
		List<OlLayerGroup> list = olLayerGroupDao.find("from OlLayerGroup t where t.olMap.id = '" + mapId + "'");
		return this.recursiveTree(null, list);
	}

	private JSONArray recursiveTree(Long parentId, List<OlLayerGroup> list) {
		JSONArray ja = new JSONArray();

		for (OlLayerGroup olLayerGroup : list) {
			OlLayerGroup parent = olLayerGroup.getParent();
			if (parentId == null ? parent == null : parent != null && Objects.equals(parentId, parent.getId())) {
				Long id = olLayerGroup.getId();

				JSONObject jo = new JSONObject();

				OlLayer olLayer = olLayerGroup.getOlLayer();
				if (olLayer == null) {
					jo.put("id", "g_" + id);
					jo.put("text", olLayerGroup.getName());
					jo.put("sort", olLayerGroup.getSort());
					jo.put("leaf", false);
				} else {
					jo.put("id", "l_" + id);

					if (layerClassNameDictMap == null) {
						layerClassNameDictMap = dictItemService.getMap("gis_layer_class");
					}
					jo.put("text", olLayer.getName() + "(" + layerClassNameDictMap.get(String.valueOf(olLayer.getLayerClassName())) + ")");
					jo.put("leaf", true);
				}

				JSONArray children = this.recursiveTree(id, list);
				jo.put("children", children);

				ja.add(jo);
			}
		}
		return ja;
	}
}
