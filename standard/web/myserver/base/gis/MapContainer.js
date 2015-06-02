Ext.define('Base.gis.MapContainer', {
	extend: 'Ext.container.Container',

	naoDivId: 'mapDiv_' + Ext.data.IdGenerator.get('uuid').generate().replace(/-/g, ''),
	map: null,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'mapContainer',
			html: '<div id="' + this.naoDivId + '" style="width: 100%; height: 100%"></div>'
		});
		this.callParent();
	},
	getMap: function() {
		return this.map;
	},
	loadMap: function(moduleId, thematicId) {
		this.map = new ol.Map({
			target: this.naoDivId,
			layers: [
				new ol.layer.Tile({source: new ol.source.OSM()})
			],
			view: new ol.View({
				center: [0, 0],
				zoom: 2
			}),
			controls: ol.control.defaults().extend([
				new ol.control.ScaleLine()
			])
		});
	}
});
