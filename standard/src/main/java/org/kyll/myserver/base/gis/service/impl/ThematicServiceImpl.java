package org.kyll.myserver.base.gis.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.gis.dao.*;
import org.kyll.myserver.base.gis.entity.*;
import org.kyll.myserver.base.gis.service.ThematicService;
import org.kyll.myserver.base.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2015-04-28 18:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ThematicServiceImpl implements ThematicService {
	@Autowired
	private ThematicDao thematicDao;
	@Autowired
	private OlMapDao olMapDao;
	@Autowired
	private OlViewDao olViewDao;
	@Autowired
	private OlLayerGroupDao olLayerGroupDao;
	@Autowired
	private OlLayerDao olLayerDao;
	@Autowired
	private OlControlDao olControlDao;
	@Autowired
	private OlInteractionDao olInteractionDao;
	@Autowired
	private OlOverlayDao olOverlayDao;

	@Override
	public Thematic get(Long id) {
		return thematicDao.get(id);
	}

	@Override
	public Dataset<Thematic> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Thematic t where 1 = 1");
		String name = qc.getName();
		if (StringUtils.isNotBlank(name)) {
			hql.append(" and t.name like '%").append(name).append("%'");
		}
		hql.append(" order by t.sort asc");
		return thematicDao.find(hql, pg);
	}

	@Override
	public List<Thematic> get(QueryCondition qc) {
		StringBuilder hql;
		Long moduleId = qc.getModuleId();
		if (moduleId == null) {
			hql = new StringBuilder("from Thematic t");
			hql.append(" order by t.sort asc");
		} else {
			hql = new StringBuilder("select distinct t.thematic from MenuApplicationThematic t where t.application.id = '" + moduleId + "'");
			hql.append(" order by t.thematic.sort asc");
		}
		return thematicDao.find(hql);
	}

	@Override
	public void save(Thematic thematic, OlMap olMap, OlView olView, String layerGroup, List<OlControl> olControlList, List<OlInteraction> olInteractionList) {
		olMapDao.save(olMap);

		olView.setOlMap(olMap);
		olViewDao.save(olView);

		thematic.setOlMap(olMap);
		thematicDao.save(thematic);

		List<OlLayerGroup> olLayerGroupList = olLayerGroupDao.find("from OlLayerGroup t where t.olMap.id = '" + olMap.getId() + "'");
		olLayerGroupDao.delete(olLayerGroupList);
		JSONArray ja = JSONObject.fromObject(layerGroup).getJSONArray("children");
		for (int i = 0; i < ja.size(); i++) {
			this.saveLayerGroup(ja.getJSONObject(i), null, olMap);
		}

		olControlDao.delete(olControlDao.find("from OlControl t where t.olMap.id = '" + olMap.getId() + "'"));
		for (OlControl olControl : olControlList) {
			olControl.setOlMap(olMap);
			olControlDao.save(olControl);
		}

		olInteractionDao.delete(olInteractionDao.find("from OlInteraction t where t.olMap.id = '" + olMap.getId() + "'"));
		for (OlInteraction olInteraction : olInteractionList) {
			olInteraction.setOlMap(olMap);
			olInteractionDao.save(olInteraction);
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

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			Thematic thematic = thematicDao.get(id);
			OlMap olMap = thematic.getOlMap();
			if (olMap != null) {
				Long mapId = olMap.getId();
				olViewDao.delete(olViewDao.find("from OlView t where t.olMap.id = '" + mapId + "'"));
				olLayerGroupDao.delete(olLayerGroupDao.find("from OlLayerGroup t where t.olMap.id = '" + mapId + "'"));
				olControlDao.delete(olControlDao.find("from OlControl t where t.olMap.id = '" + mapId + "'"));
				olInteractionDao.delete(olInteractionDao.find("from OlInteraction t where t.olMap.id = '" + mapId + "'"));
				olOverlayDao.delete(olOverlayDao.find("from OlOverlay t where t.olMap.id = '" + mapId + "'"));
				olMapDao.delete(olMap);
			}
		}

		thematicDao.delete(ids);
	}
}
