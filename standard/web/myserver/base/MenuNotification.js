Ext.define('Base.MenuNotification', {
	extend: 'Base.ux.Notification',

	moduleId: null,
	thematicId: null,

	title: '业务菜单',
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
			store: Ext.create('Ext.data.TreeStore', {
				model: 'DataModel',
				proxy: {
					type: 'ajax',
					url: ctx + '/app/menu/function.ctrl',
					extraParams: {
						moduleId: this.moduleId
					}
				},
				reader: {
					type: 'json'
				},
				root: {
					id: null,
					expanded: true
				}
			}),
			rootVisible: false,
			useArrows: true,
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
