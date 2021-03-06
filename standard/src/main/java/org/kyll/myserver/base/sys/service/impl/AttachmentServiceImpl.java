package org.kyll.myserver.base.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kyll.myserver.base.QueryCondition;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.common.paginated.Paginated;
import org.kyll.myserver.base.sys.dao.AttachmentDao;
import org.kyll.myserver.base.sys.entity.Attachment;
import org.kyll.myserver.base.sys.service.AttachmentService;
import org.kyll.myserver.base.util.ConstUtils;
import org.kyll.myserver.base.util.HqlUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * User: Kyll
 * Date: 2014-12-02 16:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Attachment get(Long id) {
		return attachmentDao.get(id);
	}

	@Override
	public Dataset<Attachment> get(QueryCondition qc, Paginated pg) {
		StringBuilder hql = new StringBuilder("from Attachment t where 1 = 1");
		this.appendQueryCondition(hql, qc);
		HqlUtils.appendOrderBy(hql, "t", pg);
		return attachmentDao.find(hql, pg);
	}

	@Override
	public List<Attachment> get(QueryCondition qc) {
		StringBuilder hql = new StringBuilder("from Attachment t where 1 = 1");
		this.appendQueryCondition(hql, qc);
		return attachmentDao.find(hql);
	}

	@Override
	public void save(String entityName, String entityId, List<CommonsMultipartFile> commonsMultipartFileList) {
		for (CommonsMultipartFile commonsMultipartFile : commonsMultipartFileList) {
			Attachment attachment = POJOUtils.convert(commonsMultipartFile);
			attachment.setEntityName(entityName);
			attachment.setEntityId(entityId);
			attachmentDao.save(attachment);

			new Thread(() -> {
				File attachmentDir = new File(ConstUtils.getAttachmentPath());
				if ((attachmentDir.exists() && attachmentDir.isDirectory()) || attachmentDir.mkdirs()) {
					File entityDir = new File(attachmentDir.getPath() + File.separator + entityName.replace(".", File.separator));
					if ((entityDir.exists() && entityDir.isDirectory()) || entityDir.mkdirs()) {
						File file = new File(entityDir.getPath() + File.separator + attachment.getRandomFilename() + "." + attachment.getExtensionName());

						try {
							commonsMultipartFile.transferTo(file);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						System.err.println(entityDir.getPath() + " 不存在， 或者不是目录， 或者建立此目录失败！");
					}
				} else {
					System.err.println("SYS_CONFIG.ATTACHMENT_PATH 不存在， 或者不是目录， 或者建立此目录失败！");
				}
			}).start();
		}
	}

	@Override
	public void delete(String entityName, String entityId) {
		QueryCondition qc = new QueryCondition();
		qc.setEntityName(entityName);
		qc.setEntityId(entityId);
		attachmentDao.delete(this.get(qc));
	}

	@Override
	public void delete(Long[] ids) {
		attachmentDao.delete(ids);
	}

	private StringBuilder appendQueryCondition(StringBuilder hql, QueryCondition qc) {
		if (qc != null) {
			Long id = qc.getId();
			if (id != null) {
				hql.append(" and t.id = '").append(id).append("'");
			}
			String entityName = qc.getEntityName();
			if (StringUtils.isNotBlank(entityName)) {
				hql.append(" and t.entityName = '").append(entityName).append("'");
			}
			String entityId = qc.getEntityId();
			if (StringUtils.isNotBlank(entityId)) {
				hql.append(" and t.entityId = '").append(entityId).append("'");
			}
			String originalFilename = qc.getOriginalFilename();
			if (StringUtils.isNotBlank(originalFilename)) {
				hql.append(" and lower(t.originalFilename) like lower('%").append(originalFilename).append("%')");
			}
		}
		return hql;
	}
}
