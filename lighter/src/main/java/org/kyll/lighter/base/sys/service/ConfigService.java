package org.kyll.lighter.base.sys.service;

import net.sf.json.JSONArray;
import org.kyll.lighter.base.QueryCondition;
import org.kyll.lighter.base.common.BaseService;
import org.kyll.lighter.base.common.paginated.Dataset;
import org.kyll.lighter.base.common.paginated.Paginated;
import org.kyll.lighter.base.sys.entity.Config;

import java.util.List;

/**
 * User: Kyll
 * Date: 2014-12-02 16:41
 */
public interface ConfigService extends BaseService<Config, Long> {
	Dataset<Config> get(QueryCondition qc, Paginated pg);

	List<Config> getAll();

	void save(JSONArray ja);

	void delete(Long[] ids);
}
