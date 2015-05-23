package org.kyll.myserver.base.sys.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.sys.dao.ConfigDao;
import org.kyll.myserver.base.sys.entity.Config;
import org.kyll.myserver.base.sys.service.ConfigService;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2014-12-02 16:42
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private ConfigDao configDao;

	@Override
	public Config get(Long id) {
		return configDao.get(id);
	}

	@Override
	public Dataset<Config> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Config t where 1 = 1");
		if (qc != null) {
			String key = qc.getKey();
			if (StringUtils.isNotBlank(key)) {
				hql.append(" and lower(t.key) like lower('%").append(key).append("%')");
			}
			String value = qc.getValue();
			if (StringUtils.isNotBlank(value)) {
				hql.append(" and lower(t.value) like lower('%").append(value).append("%')");
			}
		}
		HqlUtils.appendOrderBy(hql, "t", pg);
		return configDao.find(hql, pg);
	}

	@Override
	public List<Config> getAll() {
		return configDao.getAll();
	}

	@Override
	public void save(JSONArray ja) {
		for (int i = 0, size = ja.size(); i < size; i++) {
			JSONObject jo = ja.getJSONObject(i);

			Long id = JsonUtils.getLong(jo, "id");
			Config config;
			if (id == null) {
				config = new Config();
			} else {
				config = this.get(id);
			}

			config.setKey(JsonUtils.getString(jo, "key"));
			config.setValue(JsonUtils.getString(jo, "value"));
			config.setSort(JsonUtils.getInteger(jo, "sort"));

			configDao.save(config);
		}
	}

	@Override
	public void delete(Long[] ids) {
		configDao.delete(ids);
	}
}
