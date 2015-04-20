Ext.define('Base.app.module.ModuleContainer', {
	extend: 'Ext.container.Container',

	layout: 'border',

	initComponent: function() {
		this.callParent();

		this.add(Ext.create('Base.app.module.ModuleTreePanel', {
			region: 'west',
			split: true,
			collapsible: true,
			width: myServer.getWidth() / 6
		}));
		this.add(Ext.create('Base.app.module.ModuleGridPanel', {
			region: 'center'
		}));
	}
});
