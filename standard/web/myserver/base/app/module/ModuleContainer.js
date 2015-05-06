Ext.define('Base.app.module.ModuleContainer', {
	extend: 'Ext.container.Container',

	initComponent: function() {
		Ext.apply(this, {
			layout: 'border',
			items: [Ext.create('Base.app.module.ModuleTreePanel', {
				region: 'west',
				split: true,
				collapsible: true,
				width: myServer.getWidth() / 6
			}), Ext.create('Base.app.module.ModuleGridPanel', {
				region: 'center'
			})]
		});
		this.callParent();
	}
});
