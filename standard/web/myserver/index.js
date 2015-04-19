var myServer = {
	loginUser: null,// 登录用户信息 {id, username}

	width: 0,// 浏览器窗口的内部宽度， 不包括工具栏和滚动条
	height: 0,// 浏览器窗口的内部高度， 不包括工具栏和滚动条

	viewport: null,// 视窗
	mapContainer: null,// 地图容器
	infomationNotification: null,// 系统信息通知窗口
	functionNotification: null,// 功能模块通知窗口
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
	setFunctionNotification: function(value) {
		this.functionNotification = value;
	},
	getFunctionNotification: function() {
		return this.functionNotification;
	},
	showFunctionNotification: function() {
		this.getFunctionNotification().show();
	},
	getBusinessWindowMap: function() {
		return this.businessWindowMap;
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

	// 通过 ajax 获取 application JSON
	myServer.setInfomationNotification(Ext.create('Base.InfomationNotification', {
		applications: [{
			id: '1',
			name: '应用1',
			thematics: [{
				id: '1',
				name: '专题1'
			}, {
				id: '2',
				name: '专题2'
			}]
		}, {
			id: '2',
			name: '应用2',
			thematics: [{
				id: '2',
				name: '专题2'
			}, {
				id: '3',
				name: '专题3'
			}]
		}]
	}).show());




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
