package org.kyll.myserver.business.ctrl;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * User: Kyll
 * Date: 2015-07-23 9:50
 */
@Controller
@Scope("request")
public class ExportCtrl {
	@RequestMapping("/business/export/excel.ctrl")
	public void excel(String title, String url, String exportRange, String param, String columnInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String titleUtf8 = URLDecoder.decode(title, "utf-8");
		String urlUtf8 = URLDecoder.decode(url, "utf-8");
		String paramUtf8 = URLDecoder.decode(param, "utf-8");
		String columnInfoUtf8 = URLDecoder.decode(columnInfo, "utf-8");

		JSONObject paramJO = JSONObject.fromObject(paramUtf8);
		int start = paramJO.getInt("start");
		int limit = paramJO.getInt("limit");
		String params = "?start=" + ("0".equals(exportRange) ? start : 0) + "&limit=" + ("0".equals(exportRange) ? limit : 65535);
		if (paramJO.has("sort")) {
			String sort = paramJO.getString("sort");
			if (sort.startsWith("[")) {
				JSONObject sortJO = JSONArray.fromObject(sort).getJSONObject(0);
				params += "&property=" + sortJO.getString("property");
				params += "&direction=" + (sortJO.has("direction") ? sortJO.getString("direction") : "ASC");
				params += "&sort=" + sort;
			} else {// support old version
				params += "&sort=" + sort;
				params += "&dir=" + (paramJO.has("dir") ? paramJO.getString("dir") : "ASC");
			}
		}
		Iterator it = paramJO.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			if (!"start".equals(key) && !"limit".equals(key) && !"sort".equals(key) && !"dir".equals(key) && !"page".equals(key)) {
				params += "&" + key + "=" + this.toStringValue(paramJO, key);
			}
		}

		HttpURLConnection connection = (HttpURLConnection) new URL(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + urlUtf8).openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		Enumeration enu = request.getHeaderNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			connection.setRequestProperty(key, request.getHeader(key));
		}
		connection.connect();
		DataOutputStream paramOut = new DataOutputStream(connection.getOutputStream());
		paramOut.writeBytes(params.substring(1));
		paramOut.flush();
		paramOut.close();

		/* HTTP GET
		HttpURLConnection connection = (HttpURLConnection) new URL(BaseAction.getRootPath() + this.url + params).openConnection();
		HttpServletRequest request = BaseAction.getRequest();
		Enumeration enu = request.getHeaderNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			connection.setRequestProperty(key, request.getHeader(key));
		}
		connection.connect();*/

		BufferedReader in = null;
		StringBuilder sb = new StringBuilder();
		try {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
			int cp;
			while ((cp = in.read()) != -1) {
				sb.append((char) cp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}
		connection.disconnect();

		String fileName = titleUtf8;
		if ("".equals(fileName)) {
			fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		}

		Workbook workbook = new HSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerStyle.setBorderTop(CellStyle.BORDER_THIN);
		headerStyle.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headerStyle.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headerStyle.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setBorderRight(CellStyle.BORDER_THIN);
		headerStyle.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerStyle.setFont(font);

		Sheet sheet = workbook.createSheet(fileName);

		JSONArray rowJA = JSONArray.fromObject(columnInfoUtf8);// init header
		for (int i = 0; i < rowJA.size(); i++) {
			JSONArray cellJA = rowJA.getJSONArray(i);
			Row row = sheet.createRow(i);
			for (int j = 0; j < cellJA.size(); j++) {
				JSONObject cellJO = cellJA.getJSONObject(i);
				String text = cellJO.has("text") ? cellJO.getString("text") : (cellJO.has("header") ? cellJO.getString("header") : "");
				if (!"&#160;".equals(text)) {
					row.createCell(j).setCellStyle(headerStyle);
				}
			}
		}
		for (int i = 0; i < rowJA.size(); i++) {// config header
			JSONArray cellJA = rowJA.getJSONArray(i);
			for (int j = 0; j < cellJA.size(); j++) {
				JSONObject cellJO = cellJA.getJSONObject(j);

				if (!cellJO.isNullObject() && cellJO.has("rowIndex") && cellJO.has("colIndex") && cellJO.has("rowspan") && cellJO.has("colspan")) {
					int rowIndex = cellJO.getInt("rowIndex");
					int colIndex = cellJO.getInt("colIndex");
					Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
					String text = cellJO.has("text") ? cellJO.getString("text") : (cellJO.has("header") ? cellJO.getString("header") : "");
					if (!"&#160;".equals(text)) {
						cell.setCellValue(createHelper.createRichTextString(text));
					}

					int rowspan = cellJO.getInt("rowspan");
					int colspan = cellJO.getInt("colspan");
					if (rowspan > 1 || colspan > 1) {
						sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex + (rowspan - 1), colIndex, colIndex + (colspan - 1)));
					}
				}
			}
		}

		int dataStartIndex = sheet.getLastRowNum() + 1;
		JSONArray dataIndexJA = rowJA.getJSONArray(rowJA.size() - 1);

		JSONObject dataJO = JSONObject.fromObject(sb.toString());
		JSONArray resultJA = dataJO.getJSONArray("dataList");
		for (int i = 0; i < resultJA.size(); i++) {
			JSONObject recordJO = resultJA.getJSONObject(i);
			Row row = sheet.createRow(i + dataStartIndex);
			for (int j = 0; j < dataIndexJA.size(); j++) {
				String dataIndex = dataIndexJA.getJSONObject(j).getString("dataIndex");
				Cell cell = row.createCell(j);
				RichTextString rts;
				if ("s_n".equals(dataIndex)) {
					rts = createHelper.createRichTextString(String.valueOf(i + 1));
				} else if (recordJO.has(dataIndex)) {
					rts = createHelper.createRichTextString(recordJO.getString(dataIndex));
				} else {
					rts = createHelper.createRichTextString("");
				}
				cell.setCellValue(rts);
			}
		}

		response.setHeader("Connection", "close");
		response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
		OutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}

	private String toStringValue(JSONObject jo, String key) {
		String value = "";

		Object object = jo.get(key);
		if (object instanceof JSON) {
			JSONObject vjo = jo.getJSONObject(key);
			if (!vjo.isNullObject() && !vjo.isEmpty()) {
				value = jo.getString(key);
			}
		} else {
			value = jo.getString(key);
		}

		try {
			value = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
}
