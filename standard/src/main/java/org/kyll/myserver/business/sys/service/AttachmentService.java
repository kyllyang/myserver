package org.kyll.myserver.business.sys.service;

import org.kyll.myserver.base.BaseService;
import org.kyll.myserver.business.sys.QueryCondition;
import org.kyll.myserver.business.sys.entity.Attachment;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * User: Kyll
 * Date: 2014-12-02 16:12
 */
public interface AttachmentService extends BaseService<Attachment, Long> {
	List<Attachment> get(QueryCondition qc);

	void save(String entityName, String entityId, List<CommonsMultipartFile> commonsMultipartFileList);

	void delete(String entityName, String entityId);
}
