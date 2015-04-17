Ext.define('Base.gis.MapContainer', {
	extend: 'Ext.container.Container',

	itemId: 'mapContainer',
	html: '<div id="mapDiv" style="width: 100%; height: 100%"></div>',

	map: null,

	initComponent: function() {
		this.callParent();
		this.on('render', this.onRenderHandler, this);
	},
	onRenderHandler: function(container, eOpts) {
		this.map = new ol.Map({
			layers: [
				new ol.layer.Tile({source: new ol.source.OSM()})
			],
			view: new ol.View({
				center: [0, 0],
				zoom: 2
			}),
			target: 'mapDiv'
		});
	}
});
