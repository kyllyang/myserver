Ext.define('Base.app.menu.MenuContainer', {
	extend: 'Ext.container.Container',

	layout: 'border',

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Base.app.menu.DictTreePanel', {
			region: 'west',
			split: true,
			collapsible: true,
			width: myServer.getWidth() / 6
		}));
		this.add(Ext.create('Base.app.menu.DictItemGridPanel', {
			region: 'center'
		}));
	}
});
