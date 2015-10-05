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

		var controlConfigs = [];
		for (var i = 0; i < controls.length; i++) {
			var control = controls[i];
			if ('ol.control.Attribution' == control.controlClassName) {
				controlConfigs.push(new ol.control.Attribution({
					className: control.className,
					collapsible: this._getParseBoolean(control.collapsible),
					collapsed: this._getParseBoolean(control.collapsed),
					tipLabel: control.tipLabel,
					label: control.label,
					collapseLabel: control.collapseLabel
				}));
			} else if ('ol.control.FullScreen' == control.controlClassName) {
				controlConfigs.push(new ol.control.FullScreen({
					className: control.className,
					label: control.label,
					labelActive: control.labelActive,
					tipLabel: control.tipLabel,
					keys: this._getParseBoolean(control.keys)
				}));
			} else if ('ol.control.MousePosition' == control.controlClassName) {
				controlConfigs.push(new ol.control.MousePosition({
					className: control.className,
					coordinateFormat: ol.coordinate.createStringXY(this._getParseInt(control.coordinateFormat)),
					projection: Ext.isEmpty(control.projection) ? undefined : control.projection,
					undefinedHTML: control.undefinedHTML
				}));
			} else if ('ol.control.OverviewMap' == control.controlClassName) {
				controlConfigs.push(new ol.control.OverviewMap({
					collapsed: this._getParseBoolean(control.collapsed),
					collapseLabel: control.collapseLabel,
					collapsible: this._getParseBoolean(control.collapsible),
					label: control.label,
					tipLabel: control.tipLabel
				}));
			} else if ('ol.control.Rotate' == control.controlClassName) {
				controlConfigs.push(new ol.control.Rotate({
					className: control.className,
					label: control.label,
					tipLabel: control.tipLabel,
					duration: this._getParseInt(control.duration),
					autoHide: this._getParseBoolean(control.autoHide)
				}));
			} else if ('ol.control.ScaleLine' == control.controlClassName) {
				controlConfigs.push(new ol.control.ScaleLine({
					className: control.className,
					minWidth: this._getParseInt(control.minWidth),
					units: control.units
				}));
			} else if ('ol.control.Zoom' == control.controlClassName) {
				controlConfigs.push(new ol.control.Zoom({
					className: control.className,
					duration: this._getParseInt(control.duration),
					zoomInLabel: control.zoomInLabel,
					zoomOutLabel: control.zoomOutLabel,
					zoomInTipLabel: control.zoomInTipLabel,
					zoomOutTipLabel: control.zoomOutTipLabel,
					delta: this._getParseInt(control.delta)
				}));
			} else if ('ol.control.ZoomSlider' == control.controlClassName) {
				controlConfigs.push(new ol.control.ZoomSlider({
					className: control.className,
					duration: this._getParseInt(control.duration),
					maxResolution: this._getParseFloat(control.maxResolution),
					minResolution: this._getParseFloat(control.minResolution)
				}));
			} else if ('ol.control.ZoomToExtent' == control.controlClassName) {
				controlConfigs.push(new ol.control.ZoomToExtent({
					className: control.className,
					label: control.label,
					tipLabel: control.tipLabel,
					extent: this._getExtent(control.extent)
				}));
			}
		}

		var interactionConfigs = [];
		for (var i = 0; i < interactions.length; i++) {
			var interaction = interactions[i];
			if ('ol.interaction.DoubleClickZoom' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.DoubleClickZoom({
					duration: this._getParseInt(interaction.duration),
					delta: this._getParseInt(interaction.delta)
				}));
			} else if ('ol.interaction.DragAndDrop' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.DragAndDrop({
					projection: Ext.isEmpty(control.projection) ? undefined : interaction.projection
				}));
			} else if ('ol.interaction.DragBox' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.DragBox({
					condition: ol.events.condition.shiftKeyOnly,
					style: new ol.style.Style({
						stroke: new ol.style.Stroke({
							color: [0, 0, 255, 1]
						})
					})
				}));
			} else if ('ol.interaction.DragPan' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.DragPan({
				}));
			} else if ('ol.interaction.DragRotate' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.DragRotate({
				}));
			} else if ('ol.interaction.DragRotateAndZoom' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.DragRotateAndZoom({
				}));
			} else if ('ol.interaction.DragZoom' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.DragZoom({
				}));
			} else if ('ol.interaction.Draw' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.Draw({
				}));
			} else if ('ol.interaction.KeyboardPan' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.KeyboardPan({
				}));
			} else if ('ol.interaction.KeyboardZoom' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.KeyboardZoom({
				}));
			} else if ('ol.interaction.Modify' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.Modify({
				}));
			} else if ('ol.interaction.MouseWheelZoom' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.MouseWheelZoom({
				}));
			} else if ('ol.interaction.PinchRotate' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.PinchRotate({
				}));
			} else if ('ol.interaction.PinchZoom' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.PinchZoom({
				}));
			} else if ('ol.interaction.Select' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.Select({
				}));
			} else if ('ol.interaction.Snap' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.Snap({
				}));
			} else if ('ol.interaction.Translate' == interaction.interactionClassName) {
				interactionConfigs.push(new ol.interaction.Translate({
				}));
			}
		}

		this.map = new ol.Map({
			target: this.mapDivId,
			loadTilesWhileAnimating: this._getParseBoolean(map.loadTilesWhileAnimating),
			loadTilesWhileInteracting: this._getParseBoolean(map.loadTilesWhileInteracting),
			logo: Ext.isEmpty(map.logo) ? false : map.logo,
			renderer: this._getRendererTypes(map.renderer),
			view: new ol.View(viewConfig),
			controls: controlConfigs,
			interactions: interactionConfigs,
			layers: [
				new ol.layer.Tile({source: new ol.source.OSM()})
			]
		});

		/*this.map.on('moveend', function() {
			console.log(this.map.getView().calculateExtent(this.map.getSize()));
		}, this);*/

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
	_getExtent: function(extent) {
		if (Ext.isEmpty(extent)) {
			return undefined;
		}

		var extents = [];
		var extentTemps = extent.split(',');
		for (var i = 0; i < extentTemps.length; i++) {
			extents.push(parseFloat(Ext.String.trim(extentTemps[i])));
		}
		return extents;
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
