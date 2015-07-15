package org.kyll.myserver.business.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.dao.AreaDao;
import org.kyll.myserver.business.entity.Area;
import org.kyll.myserver.business.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2015-07-15 13:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;

	@Override
	public Area get(Long id) {
		return areaDao.get(id);
	}

	@Override
	public Dataset<Area> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Area t where 1 = 1");
		this.appendQueryCondition(hql, qc);
		HqlUtils.appendOrderBy(hql, "t", pg);
		return areaDao.find(hql, pg);
	}

	@Override
	public void save(JSONArray jsonArray) {
		for (int i = 0, size = jsonArray.size(); i < size; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Long id = JsonUtils.getLong(jsonObject, "id");
			Area area;
			if (id == null) {
				area = new Area();
			} else {
				area = this.get(id);
			}

			area.setName(JsonUtils.getString(jsonObject, "name"));

			areaDao.save(area);
		}
	}

	@Override
	public void delete(Long[] ids) {
		areaDao.delete(ids);
	}

	private StringBuilder appendQueryCondition(StringBuilder hql, QueryCondition qc) {
		if (qc != null) {
		}
		return hql;
	}
}
