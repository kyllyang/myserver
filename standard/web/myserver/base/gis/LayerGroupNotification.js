Ext.define('Base.gis.LayerGroupNotification', {
	extend: 'Base.ux.Notification',

	layerGroup: null,

	title: '图层结构',
	paddingY: myServer.getHeight() * 0.15,
	autoClose: false,
	draggable: true,
	position: 'br',
	slideInDuration: '500',
	closeAction: 'hide',
	resizable: false,
	width: myServer.getWidth() * 0.1,
	border: false,

	initComponent: function() {
		this.callParent();

		Ext.define('DataModel', {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'id'},
				{name: 'text'}
			]
		});

		var treePanel = Ext.create('Ext.tree.Panel', {
			rootVisible: false,
			useArrows: true,
			border: false,
			store: Ext.create('Ext.data.TreeStore', {
				root: {
					id: null,
					expanded: true
				}
			}),
			listeners: {
				itemclick: function(treePanel, record, item, index, e, eOpts) {

				},
				scope: this
			}
		});

		var rootNode = treePanel.getRootNode();
		for (var i = 0; i < this.layerGroup.length; i++) {
			rootNode.insertChild(i, this.layerGroup[i]);
		}

		this.add(treePanel);
	}
});
