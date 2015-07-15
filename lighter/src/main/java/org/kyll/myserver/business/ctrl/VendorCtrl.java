package org.kyll.myserver.business.ctrl;

import net.sf.json.JSONArray;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.util.JsonUtils;
import org.kyll.myserver.base.util.POJOUtils;
import org.kyll.myserver.base.util.RequestUtils;
import org.kyll.myserver.business.QueryCondition;
import org.kyll.myserver.business.entity.Vendor;
import org.kyll.myserver.business.service.VendorService;
import org.kyll.myserver.business.vo.VendorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2015-07-15 20:12
 */
@Controller
@Scope("request")
public class VendorCtrl {
	@Autowired
	private VendorService vendorService;

	@RequestMapping("/business/vendor/dataset.ctrl")
	public void dataset(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Dataset<Vendor> dataset = vendorService.get(RequestUtils.getQueryCondition(request, QueryCondition.class), RequestUtils.getPaginated(request));
		Dataset<VendorVo> voDataset = POJOUtils.convert(dataset, VendorVo.class);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(voDataset));
	}

	@RequestMapping("/business/vendor/save.ctrl")
	public void save(String vendorJson, HttpServletResponse response) throws Exception {
		vendorService.save(JSONArray.fromObject(vendorJson));

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}

	@RequestMapping("/business/vendor/delete.ctrl")
	public void delete(Long[] ids, HttpServletResponse response) throws Exception {
		vendorService.delete(ids);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
