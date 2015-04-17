var myServer = {
	loninUser: {
		id: null,
		username: null
	},
	width: 0,
	height: 0,
	viewport: null,

	mapContainer: null,
	mainContent: null,

	getMapContainer: function() {
		return this.mapContainer ? this.mapContainer : this.viewport.getComponent("mapContainer");
	},
	getMapObject: function() {
		return this.getMapContainer().map;
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
	},
	allProps: function(obj) {
		var props = "";
		for (var p in obj) {
			if (typeof(obj[p]) == "function") {
				props += p + "()" + ";\n";
			} else {
				props += p + "=" + obj[p] + ";\n";
			}
		}
		alert(props);
	}
};

Ext.onReady(function() {
	myServer.width = document.body.clientWidth;
	myServer.height = document.body.clientHeight;

	myServer.viewport = Ext.create('Ext.container.Viewport', {
		renderTo: Ext.getBody(),
		layout: 'fit',
		items: [
			Ext.create('Base.gis.MapContainer')
		]
	});

	Ext.create('Base.ux.Notification', {
		title: '地图设置',
		html: 'Map, View, Source, Layer 配置， 专题配置'
	}).show();
	Ext.create('Base.ux.Notification', {
		title: '应用配置',
		html: '应用与地图专题关联， 应用与模块关联， 模块与功能关联， 功能与菜单关联， 业务功能菜单'
	}).show();
	Ext.create('Base.ux.Notification', {
		title: '系统维护',
		html: '组织机构（部门管理， 职位管理， 用户管理， 角色分配）， 角色管理（角色建立， 与功能关联）， 数据字典， 系统常量配置'
	}).show();
	Ext.create('Base.ux.Notification', {
		title: '业务功能菜单',
		html: '业务功能1， 业务功能2， 业务功能3'
	}).show();
});
