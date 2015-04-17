var myServer = {
	loginUser: {
		id: null,
		username: null
	},
	width: 0,
	height: 0,

	infomationNotification: null,// 系统信息通知窗口
	viewport: null,// 视窗
	mapContainer: null,// 地图容器

	mainContent: null,

	/**
	 * 获取系统信息通知窗口
	 * @returns Base.ux.Notification
	 */
	getInfomationNotification: function() {
		return this.infomationNotification;
	},
	/**
	 * 获取地图容器
	 * @returns Base.gis.MapContainer
	 */
	getMapContainer: function() {
		return this.mapContainer ? this.mapContainer : this.viewport.getComponent("mapContainer");
	},
	/**
	 * 获取 OpenLayers 3 地图对象
	 * @returns ol.Map
	 */
	getMapObject: function() {
		return this.getMapContainer().map;
	},
	/**
	 * 显示系统信息通知窗口
	 */
	showInfomationNotification: function() {
		this.infomationNotification.show();
	},

	setMenuTreeContent: function(moduleId) {
		this.getMenuTreePanel().loadTree(moduleId);
	},
	setMainContent: function(component) {
		var mc = this.getMainContainer();
		if (mc) {
			mc.removeAll();
			mc.add(component);
			this.mainContent = component;
		}
	},
	getMainContent: function() {
		return this.mainContent;
	}
};

Ext.onReady(function() {
	myServer.width = document.body.clientWidth;
	myServer.height = document.body.clientHeight;

	myServer.infomationNotification = Ext.create('Base.InfomationNotification').show();

	myServer.viewport = Ext.create('Ext.container.Viewport', {
		renderTo: Ext.getBody(),
		layout: 'fit',
		items: [
			Ext.create('Base.gis.MapContainer')
		]
	});


	/*Ext.create('Base.ux.Notification', {
		title: '地图维护',
		html: 'Map, View, Source, Layer 配置， 专题配置， 专题权限（精确到部门， 职位， 用户, 角色）'
	}).show();
	Ext.create('Base.ux.Notification', {
		title: '应用维护',
		html: '应用与地图专题关联， 应用与模块关联， 模块与功能关联， 功能与菜单关联， 业务功能菜单'
	}).show();
	Ext.create('Base.ux.Notification', {
		title: '系统维护',
		html: '组织机构（部门管理， 职位管理， 用户管理， 角色分配）， 角色管理（角色建立， 与功能关联）， 数据字典， 系统常量配置'
	}).show();
	Ext.create('Base.ux.Notification', {
		title: '业务展示',
		html: '业务功能1， 业务功能2， 业务功能3'
	}).show();*/

	// 上面的每一个通知框， 都叫做“功能区域”
	// 用户登录， 根据权限， 来展示拥有的功能区域。 功能区域内的功能可以有多种表现（按钮， 菜单）
	// 登录后默认显示“信息提示栏”， “当前应用”的“业务展示”
	// 应用-专题-模块-功能 （带有地图功能） 一个应用包含多个专题， 专题包含多个模块， 模块包含多个功能
	// 应用-模块-功能 （不带有地图功能）一个应用包含多个模块， 模块包含多个功能
	// 切换专题时， 可以选择是否切换业务功能
	// 功能菜单可以将模块配置为通知框， 通知框内树形菜单， 手风琴菜单
});
