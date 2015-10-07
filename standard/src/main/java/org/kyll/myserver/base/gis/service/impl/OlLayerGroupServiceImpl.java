package org.kyll.myserver.base.gis.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kyll.myserver.base.gis.dao.OlLayerDao;
import org.kyll.myserver.base.gis.dao.OlLayerGroupDao;
import org.kyll.myserver.base.gis.dao.OlMapDao;
import org.kyll.myserver.base.gis.entity.OlLayer;
import org.kyll.myserver.base.gis.entity.OlLayerGroup;
import org.kyll.myserver.base.gis.entity.OlMap;
import org.kyll.myserver.base.gis.service.OlLayerGroupService;
import org.kyll.myserver.base.sys.service.DictItemService;
import org.kyll.myserver.base.util.JsonUtils;
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
	private OlMapDao olMapDao;
	@Autowired
	private OlLayerDao olLayerDao;
	@Autowired
	private DictItemService dictItemService;

	private static Map<String, String> layerClassNameDictMap;

	@Override
	public OlLayerGroup get(Long id) {
		return olLayerGroupDao.get(id);
	}

	@Override
	public JSONArray getTreeJson(Long mapId, Boolean checked) {
		List<OlLayerGroup> list = olLayerGroupDao.find("from OlLayerGroup t where t.olMap.id = '" + mapId + "'");
		return this.recursiveTree(null, checked, list);
	}

	private JSONArray recursiveTree(Long parentId, Boolean checked, List<OlLayerGroup> list) {
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
					jo.put("id", "l_" + olLayer.getId() + "_" + id);

					if (layerClassNameDictMap == null) {
						layerClassNameDictMap = dictItemService.getMap("gis_layer_class");
					}
					jo.put("text", olLayer.getName() + "(" + layerClassNameDictMap.get(String.valueOf(olLayer.getLayerClassName())) + ")");
					jo.put("leaf", true);
				}

				if (checked != null) {
					jo.put("checked", checked);
				}

				JSONArray children = this.recursiveTree(id, checked, list);
				jo.put("children", children);

				ja.add(jo);
			}
		}
		return ja;
	}

	@Override
	public void save(Long mapId, String layerGroup) {
		OlMap olMap = olMapDao.get(mapId);

		List<OlLayerGroup> olLayerGroupList = olLayerGroupDao.find("from OlLayerGroup t where t.olMap.id = '" + olMap.getId() + "'");
		olLayerGroupDao.delete(olLayerGroupList);
		JSONArray ja = JSONObject.fromObject(layerGroup).getJSONArray("children");
		for (int i = 0; i < ja.size(); i++) {
			this.saveLayerGroup(ja.getJSONObject(i), null, olMap);
		}
	}

	private void saveLayerGroup(JSONObject jo, OlLayerGroup parent, OlMap olMap) {
		OlLayerGroup olLayerGroup = new OlLayerGroup();
		olLayerGroup.setParent(parent);
		olLayerGroup.setOlMap(olMap);

		String id = JsonUtils.getString(jo, "id");
		if (id.startsWith("g_")) {
			olLayerGroup.setName(JsonUtils.getString(jo, "name"));
			olLayerGroup.setSort(JsonUtils.getInteger(jo, "sort"));
		} else if (id.startsWith("l_")) {
			olLayerGroup.setOlLayer(olLayerDao.get(Long.parseLong(id.split("_")[1])));
		}

		olLayerGroupDao.save(olLayerGroup);

		JSONArray ja = jo.getJSONArray("children");
		for (int i = 0; i < ja.size(); i++) {
			this.saveLayerGroup(ja.getJSONObject(i), olLayerGroup, olMap);
		}
	}
}
