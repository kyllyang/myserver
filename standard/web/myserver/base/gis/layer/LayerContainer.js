Ext.define('Base.gis.layer.LayerContainer', {
	extend: 'Ext.container.Container',

	layout: 'fit',

	initComponent: function() {
		Ext.apply(this, {
			items: [Ext.create('Base.gis.layer.LayerGridPanel')]
		});
		this.callParent();
	}
});
