Ext.define('Base.gis.MapContainer', {
	extend: 'Ext.container.Container',

	mapDivId: 'mapDiv_' + Ext.data.IdGenerator.get('uuid').generate().replace(/-/g, ''),
	map: null,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'mapContainer',
			html: '<div id="' + this.mapDivId + '" style="width: 100%; height: 100%"></div>'
		});
		this.callParent();
	},
	getMap: function() {
		return this.map;
	},
	loadMap: function(moduleId, thematicId) {
		this.map = new ol.Map({
			target: this.mapDivId,
			layers: [
				new ol.layer.Tile({source: new ol.source.OSM()})
			],
			view: new ol.View({
				center: [0, 0],
				zoom: 2
			}),
			controls: ol.control.defaults().extend([
				new ol.control.FullScreen(),
				new ol.control.MousePosition(),
				new ol.control.OverviewMap(),
				new ol.control.ScaleLine(),
				new ol.control.ZoomSlider(),
				new ol.control.ZoomToExtent()
			])/*,
			interactions: [
				new ol.interaction.DoubleClickZoom(),
				new ol.interaction.DragAndDrop(),
				new ol.interaction.KeyboardPan(),
				new ol.interaction.KeyboardZoom(),
				new ol.interaction.MouseWheelZoom(),
				new ol.interaction.Pointer(),
				new ol.interaction.Select()
			]*/
		});
	}
});
