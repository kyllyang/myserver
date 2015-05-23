var myServer = {
	loginUser: null,// 登录用户信息 {id, username}

	width: 0,// 浏览器窗口的内部宽度， 不包括工具栏和滚动条
	height: 0,// 浏览器窗口的内部高度， 不包括工具栏和滚动条

	viewport: null,// 视窗
	mapContainer: null,// 地图容器
	infomationNotification: null,// 系统信息通知窗口
	menuNotification: null,// 业务菜单通知窗口
	businessWindowMap: null,// 业务窗口集合

	setLoginUser: function(value) {
		this.loginUser = value;
	},
	getLoginUser: function() {
		return this.loginUser;
	},
	setWidth: function(value) {
		this.width = value;
	},
	getWidth: function() {
		return this.width;
	},
	setHeight: function(value) {
		this.height = value;
	},
	getHeight: function() {
		return this.height;
	},
	setViewport: function(value) {
		this.viewport = value;
	},
	getViewport: function() {
		return this.viewport;
	},
	getMapContainer: function() {
		return this.mapContainer ? this.mapContainer : this.viewport.getComponent("mapContainer");
	},
	getMapObject: function() {
		return this.getMapContainer().getMap();
	},
	setInfomationNotification: function(value) {
		this.infomationNotification = value;
	},
	getInfomationNotification: function() {
		return this.infomationNotification;
	},
	showInfomationNotification: function() {
		this.getInfomationNotification().show();
	},
	setMenuNotification: function(value) {
		this.menuNotification = value;
	},
	getMenuNotification: function() {
		return this.menuNotification;
	},
	showFunctionNotification: function() {
		this.getMenuNotification().show();
	},
	getBusinessWindowMap: function() {
		return this.businessWindowMap;
	},
	encryptSHA: function(value) {
		return new jsSHA(value, 'TEXT').getHash('SHA-1', 'HEX', 1);
	}
};

Ext.onReady(function() {
	myServer.setWidth(window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth);
	myServer.setHeight(window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight);

	myServer.businessWindowMap = new Ext.util.HashMap();

	myServer.setViewport(Ext.create('Ext.container.Viewport', {
		renderTo: Ext.getBody(),
		layout: 'fit',
		items: [
			Ext.create('Base.gis.MapContainer')
		]
	}));

	myServer.setInfomationNotification(Ext.create('Base.InfomationNotification').show());




	/*Ext.create('Base.ux.Notification', {
		title: '地图维护',
		html: 'Map, View, Source, Layer 配置， 专题配置， 专题权限（精确到部门， 职位， 用户, 角色）'
	}).show();*/

	// 上面的每一个通知框， 都叫做“功能区域”
	// 用户登录， 根据权限， 来展示拥有的功能区域。 功能区域内的功能可以有多种表现（按钮， 菜单）
	// 登录后默认显示“信息提示栏”， “当前应用”的“业务展示”
	// 应用-专题-模块-功能 （带有地图功能） 一个应用包含多个专题， 专题包含多个模块， 模块包含多个功能
	// 应用-模块-功能 （不带有地图功能）一个应用包含多个模块， 模块包含多个功能
	// 切换专题时， 可以选择是否切换业务功能
	// 功能菜单可以将模块配置为通知框， 通知框内树形菜单， 手风琴菜单
});
