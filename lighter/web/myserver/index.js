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
	hasPrivilege: function(roleCode) {
		var result = false;
		for (var i = 0; i < this.loginUser.roles.length; i++) {
			if (this.loginUser.roles[i].code == roleCode) {
				result = true;
				break;
			}
		}
		return result;
	}
};

Ext.onReady(function() {
	myServer.setWidth(window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth);
	myServer.setHeight(window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight);

	myServer.setViewport(Ext.create('Ext.container.Viewport', {
		renderTo: Ext.getBody(),
		layout: 'fit',
		items: [{
			xtype: 'container',
			layout: 'border',
			items: [Ext.create('Business.NotificationToolbar', {
				region: 'north'
			}), Ext.create('Business.EmployeeAreaTreePanel', {
				region: 'west',
				width: myServer.getWidth() * 0.25
			}), Ext.create('Ext.tab.Panel', {
				region: 'center',
				border: false,
				items: [{
					title: '客户',
					layout: 'fit',
					border: false,
					hidden: !myServer.hasPrivilege('CUSTOMER'),
					items: [Ext.create('Business.CustomerGridPanel')]
				}, {
					title: '项目',
					layout: 'fit',
					border: false,
					hidden: !myServer.hasPrivilege('CUSTOMER'),
					items: [Ext.create('Business.ProjectGridPanel')]
				}, {
					title: '商务',
					layout: 'fit',
					border: false,
					hidden: !myServer.hasPrivilege('CUSTOMER'),
					items: [Ext.create('Business.ExpenseTabPanel')]
				}, {
					title: '产品',
					layout: 'fit',
					border: false,
					hidden: !myServer.hasPrivilege('PRODUCT'),
					items: [Ext.create('Business.ProductTabPanel')]
				}, {
					title: '用户',
					layout: 'fit',
					border: false,
					hidden: !myServer.hasPrivilege('USER'),
					items: [Ext.create('Business.EmployeeGridPanel')]
				}]
			})]
		}]
	}));
});
