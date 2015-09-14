Ext.define('Base.gis.layer.LayerContainer', {
	extend: 'Ext.container.Container',

	layerClassName: null,
	layout: 'fit',

	initComponent: function() {
		Ext.apply(this, {
			items: [this.layerClassName == 'ol.layer.Vector' ? Ext.create('Base.gis.layer.VectorLayerGridPanel') : Ext.create('Base.gis.layer.TileLayerGridPanel')]
		});
		this.callParent();
	}
});
