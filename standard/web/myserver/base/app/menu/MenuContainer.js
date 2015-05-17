Ext.define('Base.app.menu.MenuContainer', {
	extend: 'Ext.container.Container',

	layout: 'border',

	initComponent: function() {
		this.callParent();

		var menuTreePanel = Ext.create('Base.app.menu.MenuTreePanel', {
			region: 'west',
			split: true,
			collapsible: true,
			width: myServer.getWidth() / 6
		});
		var menuForm = Ext.create('Base.app.menu.MenuForm', {
			region: 'center',
			menuTreePanel: menuTreePanel,
			hideCloseButton: true
		});
		this.add(menuTreePanel);
		this.add(menuForm);
	}
});
