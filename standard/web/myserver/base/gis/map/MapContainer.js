Ext.define('Base.gis.map.MapContainer', {
	extend: 'Ext.container.Container',

	layout: 'fit',

	initComponent: function() {
		Ext.apply(this, {
			items: [Ext.create('Base.gis.map.MapGridPanel')]
		});
		this.callParent();
	}
});
