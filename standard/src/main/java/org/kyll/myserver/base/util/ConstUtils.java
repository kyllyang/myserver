package org.kyll.myserver.base.util;

import org.kyll.myserver.base.sys.entity.Config;
import org.kyll.myserver.base.sys.service.ConfigService;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2014-12-02 16:37
 * 包括系统常量，数据库 SYS_CONFIG 表取值
 */
public final class ConstUtils {
	/**
	 * HttpSession 保存 Session 信息的参数名称
	 */
	public static final String SESSION_NAME = "sessionVo";
	/**
	 * HttpServletRequest 请求字符串中作为查询条件的参数名前缀
	 */
	public static final String QUERY_CONDITION_PREFIX = "qc";
	/**
	 * HttpServletRequest 请求字符串中作为分页条件的参数名称 起始记录索引值
	 */
	public static final String PAGINATED_START = "start";
	/**
	 * HttpServletRequest 请求字符串中作为分页条件的参数名称 每页记录数量
	 */
	public static final String PAGINATED_LIMIT = "limit";
	/**
	 * HttpServletRequest 请求字符串中作为分页条件的参数名称 排序信息
	 */
	public static final String PAGINATED_SORT = "sort";
	/**
	 * HttpServletRequest 请求字符串中作为分页条件的参数名称 排序属性
	 */
	public static final String PAGINATED_PROPERTY = "property";
	/**
	 * HttpServletRequest 请求字符串中作为分页条件的参数名称 排序方向
	 */
	public static final String PAGINATED_DIRECTION = "direction";
	/**
	 * 常用日期时间格式 年-月-日 时:分:秒.毫秒
	 */
	public static final String DATETIME_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
	/**
	 * 常用日期时间格式 年-月-日 时:分:秒
	 */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 常用日期 年-月-日
	 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 常用时间格式 时:分:秒
	 */
	public static final String TIME_FORMAT = "HH:mm:ss";
	/**
	 * Openlayers 启用控件
	 */
	public static final String GIS_OL_CONTROL_ENABLED = "1";
	/**
	 * Openlayers 控件类名
	 */
	public static final String GIS_OL_CONTROL_ATTRIBUTION = "ol.control.Attribution";
	public static final String GIS_OL_CONTROL_FULLSCREEN = "ol.control.FullScreen";
	public static final String GIS_OL_CONTROL_MOUSEPOSITION = "ol.control.MousePosition";
	public static final String GIS_OL_CONTROL_OVERVIEWMAP = "ol.control.OverviewMap";
	public static final String GIS_OL_CONTROL_ROTATE = "ol.control.Rotate";
	public static final String GIS_OL_CONTROL_SCALELINE = "ol.control.ScaleLine";
	public static final String GIS_OL_CONTROL_ZOOM = "ol.control.Zoom";
	public static final String GIS_OL_CONTROL_ZOOMSLIDER = "ol.control.ZoomSlider";
	public static final String GIS_OL_CONTROL_ZOOMTOEXTENT = "ol.control.ZoomToExtent";
	/**
	 * Openlayers 启用交互
	 */
	public static final String GIS_OL_INTERACTION_ENABLED = "1";
	/**
	 * Openlayers 交互类名
	 */
	public static final String GIS_OL_INTERACTION_DOUBLECLICKZOOM = "ol.interaction.DoubleClickZoom";
	public static final String GIS_OL_INTERACTION_DRAGANDDROP = "ol.interaction.DragAndDrop";
	public static final String GIS_OL_INTERACTION_DRAGBOX = "ol.interaction.DragBox";
	public static final String GIS_OL_INTERACTION_DRAGPAN = "ol.interaction.DragPan";
	public static final String GIS_OL_INTERACTION_DRAGROTATE = "ol.interaction.DragRotate";
	public static final String GIS_OL_INTERACTION_DRAGROTATEANDZOOM = "ol.interaction.DragRotateAndZoom";
	public static final String GIS_OL_INTERACTION_DRAGZOOM = "ol.interaction.DragZoom";
	public static final String GIS_OL_INTERACTION_DRAW = "ol.interaction.Draw";
	public static final String GIS_OL_INTERACTION_KEYBOARDPAN = "ol.interaction.KeyboardPan";
	public static final String GIS_OL_INTERACTION_KEYBOARDZOOM = "ol.interaction.KeyboardZoom";
	public static final String GIS_OL_INTERACTION_MODIFY = "ol.interaction.Modify";
	public static final String GIS_OL_INTERACTION_MOUSEWHEELZOOM = "ol.interaction.MouseWheelZoom";
	public static final String GIS_OL_INTERACTION_PINCHROTATE = "ol.interaction.PinchRotate";
	public static final String GIS_OL_INTERACTION_PINCHZOOM = "ol.interaction.PinchZoom";
	public static final String GIS_OL_INTERACTION_SELECT = "ol.interaction.Select";
	public static final String GIS_OL_INTERACTION_SNAP = "ol.interaction.Snap";
	public static final String GIS_OL_INTERACTION_TRANSLATE = "ol.interaction.Translate";
	/**
	 * Openlayers 启用工具栏
	 */
	public static final String GIS_OL_TOOLBAR_ENABLED = "1";
	/**
	 * Openlayers 工具栏项目
	 */
	public static final String GIS_OL_TOOLBAR_SELECT = "select";
	public static final String GIS_OL_TOOLBAR_DRAW_POINT = "drawPoint";
	public static final String GIS_OL_TOOLBAR_DRAW_LINESTRING = "drawLinestring";
	public static final String GIS_OL_TOOLBAR_DRAW_POLYGON = "drawPolygon";
	public static final String GIS_OL_TOOLBAR_MODIFY = "modify";
	public static final String GIS_OL_TOOLBAR_TRANSLATE = "translate";
	public static final String GIS_OL_TOOLBAR_ERASE = "erase";
	public static final String GIS_OL_TOOLBAR_RESTORE = "restore";

	private static ApplicationContext applicationContext;
	private static boolean assignApplicationContext = false;

	private static final Map<String, String> configMap = new HashMap<>();

	/**
	 * 获取 Spring 应用上下文
	 * @return Spring ApplicationContext 对象
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 设置应用上下文， 此方法仅在系统初始化时调用一次， 以后再次调用时会抛出异常
	 * @param applicationContext Spring ApplicationContext
	 * @throws Exception 系统初始化之后， 再次调用时抛出
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) throws Exception {
		if (assignApplicationContext) {
			throw new Exception("禁止为 org.kyll.myserver.util.ConstUtils.applicationContext 赋值！");
		} else {
			ConstUtils.applicationContext = applicationContext;
			assignApplicationContext = true;
		}
	}

	/**
	 * 获取数据库 SYS_CONFIG 表中的值
	 * @param key 键
	 * @return 值
	 */
	public static String getConfig(String key) {
		if (configMap.isEmpty()) {
			ConfigService configService = applicationContext.getBean(ConfigService.class);
			List<Config> configList = configService.getAll();
			for (Config config : configList) {
				configMap.put(config.getKey(), config.getValue());
			}
		}

		return configMap.get(key);
	}

	/**
	 * 从数据库 SYS_CONFIG 表中获取附件保存路径
	 * @return 附件路径
	 */
	public static String getAttachmentPath() {
		return getConfig("ATTACHMENT_PATH");
	}
}
