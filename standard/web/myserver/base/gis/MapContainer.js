Ext.define('Base.gis.MapContainer', {
	extend: 'Ext.container.Container',

	mapDivId: 'mapDiv_' + myServer.uuid(),
	map: null,
	mapConfig: null,

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
	getMapConfig: function() {
		return this.mapConfig;
	},
	loadMap: function(thematicId) {
		Ext.Ajax.request({
			url: ctx + '/gis/map/config.ctrl',
			params: {
				thematicId: thematicId
			},
			success: function(response, opts) {
				this.mapConfig = Ext.decode(response.responseText);
				if (this.mapConfig.result.success) {
					this.processMap();
				} else {
					this.mapConfig = null;
					Ext.Msg.alert("系统提示", "缺少地图配置数据！");
				}
			},
			failure: function(response, opts) {
				Ext.Msg.alert("系统提示", "加载地图数据失败！");
			},
			scope: this
		});
	},
	processMap: function() {
		var map = this.mapConfig.map;
		var view = this.mapConfig.view;
		var layerGroup = this.mapConfig.layerGroup;
		var controls = this.mapConfig.controls;
		var interactions = this.mapConfig.interactions;

		var viewConfig = {
			projection: view.projection,
			center: this._getCoordinate(view.center),
			extent: Ext.isEmpty(view.extent) ? undefined : view.extent,
			resolutions: this._getResolutions(view.resolutions),
			resolution: this._getParseFloat(view.resolution),
			maxZoom: this._getParseInt(view.maxZoom),
			minZoom: this._getParseInt(view.minZoom),
			zoomFactor: this._getParseInt(view.zoomFactor),
			zoom: this._getParseInt(view.zoom),
			enableRotation: this._getParseBoolean(view.enableRotation),
			constrainRotation: '0' == view.constrainRotation ? true : parseInt(view.constrainRotation),
			rotation: parseFloat(view.rotation)
		};
		if (!Ext.isEmpty(view.maxResolution)) {
			Ext.apply(viewConfig, {
				maxResolution: parseFloat(view.maxResolution)
			});
		}
		if (!Ext.isEmpty(view.minResolution)) {
			Ext.apply(viewConfig, {
				minResolution: parseFloat(view.minResolution)
			});
		}

		this.map = new ol.Map({
			target: this.mapDivId,
			loadTilesWhileAnimating: this._getParseBoolean(map.loadTilesWhileAnimating),
			loadTilesWhileInteracting: this._getParseBoolean(map.loadTilesWhileInteracting),
			logo: Ext.isEmpty(map.logo) ? false : map.logo,
			renderer: this._getRendererTypes(map.renderer),
			view: new ol.View(viewConfig),
			layers: [
				new ol.layer.Tile({source: new ol.source.OSM()})
			],
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
	},
	_getRendererTypes: function(renderer) {
		if (Ext.isEmpty(renderer)) {
			return undefined;
		}

		var renderers = [];
		var rendererTemps = renderer.split(',');
		for (var i = 0; i < rendererTemps.length; i++) {
			renderers.push(rendererTemps[i].toLowerCase());
		}
		return renderers;
	},
	_getCoordinate: function(coordinate) {
		if (Ext.isEmpty(coordinate)) {
			return undefined;
		}

		var coordinates = [];
		var coordinateTemps = coordinate.split(',');
		for (var i = 0; i < coordinateTemps.length; i++) {
			coordinates.push(parseFloat(Ext.String.trim(coordinateTemps[i])));
		}
		return coordinates;
	},
	_getResolutions: function(resolutions) {
		if (Ext.isEmpty(resolutions)) {
			return undefined;
		}

		var resolutionss = [];
		var resolutionsTemps = resolutions.split(',');
		for (var i = 0; i < resolutionsTemps.length; i++) {
			resolutionss.push(parseFloat(Ext.String.trim(resolutionsTemps[i])));
		}
		return resolutionss;
	},
	_getParseInt: function(value) {
		return Ext.isEmpty(value) ? undefined : parseInt(value);
	},
	_getParseFloat: function(value) {
		return Ext.isEmpty(value) ? undefined : parseFloat(value);
	},
	_getParseBoolean: function(value) {
		return '1' == value;
	}
});
