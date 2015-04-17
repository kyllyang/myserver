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
		title: '信息提示栏',
		html: '登录用户， 当前应用（下拉）， 当前地图专题（下拉）， 最小化的业务窗口列表（下拉复选框， 选中显示， 不选中最小化）， 退出按钮'
	}).show();
	Ext.create('Base.ux.Notification', {
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
	}).show();

	// 上面的每一个通知框， 都叫做“功能区域”
	// 用户登录， 根据权限， 来展示拥有的功能区域。 功能区域内的功能可以有多种表现（按钮， 菜单）
	// 登录后默认显示“信息提示栏”， “当前应用”的“业务展示”
	// 应用-专题-模块-功能 （带有地图功能） 一个应用包含多个专题， 专题包含多个模块， 模块包含多个功能
	// 应用-模块-功能 （不带有地图功能）一个应用包含多个模块， 模块包含多个功能
	// 切换专题时， 可以选择是否切换业务功能
	// 功能菜单可以将模块配置为通知框， 通知框内树形菜单， 手风琴菜单
});
