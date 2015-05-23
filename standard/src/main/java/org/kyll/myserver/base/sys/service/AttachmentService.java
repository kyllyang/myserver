package org.kyll.myserver.base.sys.service;

import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.sys.entity.Attachment;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * User: Kyll
 * Date: 2014-12-02 16:12
 */
public interface AttachmentService extends BaseService<Attachment, Long> {
	Dataset<Attachment> get(QueryCondition qc, Paginated paginated);

	List<Attachment> get(QueryCondition qc);

	void save(String entityName, String entityId, List<CommonsMultipartFile> commonsMultipartFileList);

	void delete(String entityName, String entityId);

	void delete(Long[] ids);
}
