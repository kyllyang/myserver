Ext.define('Base.FunctionNotification', {
	extend: 'Base.ux.Notification',

	applicationId: null,
	thematicId: null,

	title: '功能模块',
	autoClose: false,
	draggable: true,
	position: 'l',
	slideInDuration: '500',
	closeAction: 'hide',
	resizable: false,
	width: myServer.getWidth() * 0.15,
	border: false,

	initComponent: function() {
		this.callParent();

		Ext.define('DataModel', {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'id'},
				{name: 'text'},
				{name: 'funcType'},
				{name: 'funcCode'}
			]
		});

		this.add(Ext.create('Ext.tree.Panel', {
			model: 'DataModel',
			root: {
				expanded: true,
				children: [{
					text: '功能1',
					leaf: true
				}, {
					text: '应用管理',
					children: [{
						id: '2',
						text: '模块功能',
						leaf: true,
						funcCode: {
							className: 'Sys.module.ModuleContainer'
						}
					}]
				}, {
					text: '系统管理',
					children: [{
						id: '1',
						text: '数据字典',
						leaf: true,
						funcCode: {
							className: 'Sys.dict.DictContainer'
						}
					}]
				}]
			},
			useArrows: true,
			rootVisible: false,
			border: false,
			listeners: {
				itemclick: function(treePanel, record, item, index, e, eOpts) {
					var funcCode = record.get('funcCode');
					if (!Ext.isEmpty(funcCode)) {
						var menuId = record.get('id');
						var businessWindow = myServer.getBusinessWindowMap().get(menuId);
						if (Ext.isEmpty(businessWindow)) {
							businessWindow = Ext.create('Base.BusinessWindow', {
								title: record.get('text'),
								menuId: menuId,
								funcCode: funcCode
							});
						}
						businessWindow.show();
					}
				},
				scope: this
			}
		}));
	}
});
