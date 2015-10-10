var myServer = {
	loginUser: null,// 登录用户信息 {id, username}
	config: null,// 系统配置 Ext.util.HashMap

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
	showLayerGroupNotification: function() {
		var layerGroupNotification = this.getMapContainer().getLayerGroupNotification();
		if (!Ext.isEmpty(layerGroupNotification)) {
			layerGroupNotification.show();
		}
	},
	getBusinessWindowMap: function() {
		return this.businessWindowMap;
	},
	uuid: function() {
		return  Ext.data.IdGenerator.get('uuid').generate().replace(/-/g, '');
	},
	getAllProperties: function(obj) {
		var props = "";
		for (var p in obj) {
			if (typeof(obj[p]) == "function") {
				props += p + "()" + ";\n";
			} else {
				props += p + "=" + obj[p] + ";\n";
			}
		}
		return props;
	}
};
