Ext.define('Base.FunctionNotification', {
	extend: 'Base.ux.Notification',

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

		var tree = Ext.create('Ext.tree.Panel', {
			root: {
				expanded: true,
				children: [{
					text: '功能1',
					leaf: true
				}, {
					text: '功能2',
					leaf: true
				}, {
					text: '功能3',
					leaf: true
				}]
			},
			useArrows: true,
			rootVisible: false,
			border: false
		});
		tree.on('itemclick', function(treePanel, record, item, index, e, eOpts) {alert(index);
			/*var funcCode = record.get('funcCode');
			if (funcCode && funcCode.className) {
				myServer.setMainContent(Ext.create(funcCode.className, funcCode.config));
			}*/
		}, this);

		this.add(tree);
	}
});
