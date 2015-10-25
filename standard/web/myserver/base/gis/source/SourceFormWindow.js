Ext.define('Base.gis.source.SourceFormWindow', {
	extend: 'Ext.window.Window',

	opener: null,

	sourceClassName: null,

	title: '数据源信息',
	width: 800,
	height: 600,
	border: false,
	modal: true,
	resizable: false,
	layout: 'fit',

	initComponent: function() {
		this.callParent();

		Ext.define('FormModel', {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'id'},
				{name: 'name'},
				{name: 'sort'},
				{name: 'sourceClassName'}
			]
		});

		var nameText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'name',
			maxLength: 100,
			allowBlank: false
		});
		var sortNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '排序',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'sort',
			value: 1,
			minValue: 1,
			allowDecimals: false
		});

		var navigationGridPanel = Ext.create('Ext.grid.Panel', {
			region: 'west',
			width: 200,
			hideHeaders: true,
			store: Ext.create('Ext.data.Store', {
				fields: ['name'],
				data: [{
					name: '基本信息'
				}, {
					name: '选择类型'
				}, {
					name: '选择实现类'
				}, {
					name: '属性参数'
				}]
			}),
			columns: [{
				dataIndex: 'name',
				flex: 1
			}],
			listeners: {
				render: function(gridPanel, eOpts) {
					gridPanel.getSelectionModel().select(0);
				},
				select: function(rowModel, record, index, eOpts) {
					navigationGridPanel.getSelectionModel().select(parseInt(this.getComponent('editForm').items.get(1).getLayout().getActiveItem().itemId.split('_')[1]));
				},
				scope: this
			}
		});

		var sourceTypeRadioGroup = Ext.create('Ext.form.RadioGroup', {
			itemId: 'sourceTypeRadioGroup',
			fieldLabel: '<span style="color: #FF0000;">*</span>类型',
			labelAlign: 'right',
			labelSeparator: '：',
			columns: 1,
			vertical: true,
			allowBlank: false,
			items: [{
				boxLabel: '图像',
				name: 'sourceType',
				inputValue: 'ol.source.Image',
				qtip: 'Abstract base class; normally only used for creating subclasses and not instantiated in apps. Base class for sources providing a single image.'
			}, {
				boxLabel: '瓦片',
				name: 'sourceType',
				inputValue: 'ol.source.Tile',
				checked: true,
				qtip: 'Abstract base class; normally only used for creating subclasses and not instantiated in apps. Base class for sources providing images divided into a tile grid.'
			}, {
				boxLabel: '矢量',
				name: 'sourceType',
				inputValue: 'ol.source.Vector',
				qtip: 'Provides a source of features for vector layers.'
			}]
		});

		// ImageCanvas
		var imageCanvasLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageCanvasLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var imageCanvasProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageCanvasProjection',
			maxLength: 255,
			qtip: 'Projection.'
		});
		var imageCanvasRatioNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '比率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageCanvasRatio',
			value: 1.5,
			minValue: 0,
			qtip: 'Ratio. 1 means canvases are the size of the map viewport, 2 means twice the width and height of the map viewport, and so on. Must be 1 or higher. Default is 1.5.'
		});
		var imageCanvasResolutionsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageCanvasResolutions',
			rows: 7,
			maxLength: 255,
			qtip: 'Resolutions. If specified, new canvases will be created for these resolutions only.'
		});
		var imageCanvasStateComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_state',
			fieldLabel: '状态',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageCanvasState',
			qtip: 'Source state.'
		});
		// ImageVector
		var imageVectorSourcePicker = Ext.create('Base.gis.source.SourcePicker', {
			fieldLabel: '数据源',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageVectorSource',
			onDetermine: function(record) {
				imageVectorSourcePicker.setValue(record.get('name'));
			}
		});
		var imageVectorStyleTrigger = Ext.create('Ext.form.field.Trigger', {
			fieldLabel: '样式',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageVectorStyle',
			onTriggerClick: function() {
				Ext.create('Base.gis.style.StyleWindow', {
					styleData: Ext.isEmpty(imageVectorStyleTrigger.getValue()) ? null : Ext.decode(imageVectorStyleTrigger.getValue()),
					onDetermine: function(style) {
						imageVectorStyleTrigger.setValue(Ext.encode(style));
					}
				}).show();
			},
			qtip: 'Style for the box.'
		});
		var imageVectorLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageVectorLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var imageVectorProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageVectorProjection',
			maxLength: 255,
			qtip: 'Projection.'
		});
		var imageVectorRatioNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '比率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageVectorRatio',
			value: 1.5,
			minValue: 0,
			qtip: 'Ratio. 1 means canvases are the size of the map viewport, 2 means twice the width and height of the map viewport, and so on. Must be 1 or higher. Default is 1.5.'
		});
		var imageVectorResolutionsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageVectorResolutions',
			rows: 7,
			maxLength: 255,
			qtip: 'Resolutions. If specified, new canvases will be created for these resolutions only.'
		});
		// ImageMapGuide
		var imageMapGuideUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageMapGuideUrl',
			maxLength: 255,
			qtip: 'The mapagent url.'
		});
		var imageMapGuideParamsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '参数',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageMapGuideParams',
			value: '{MAPDEFINITION: \'\',\r\nFORMAT: \'PNG\'}',
			rows: 7,
			maxLength: 255,
			qtip: 'Additional parameters.'
		});
		var imageMapGuideDisplayDpiNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '显示DPI',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageMapGuideDisplayDpi',
			value: 96,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The display resolution. Default is 96.'
		});
		var imageMapGuideMetersPerUnitNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '每单位米数',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageMapGuideMetersPerUnit',
			value: 1,
			minValue: 0,
			allowDecimals: false,
			qtip: 'The meters-per-unit value. Default is 1.'
		});
		var imageMapGuideHidpiCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '高分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageMapGuideHidpi',
			inputValue: '1',
			checked: true,
			qtip: 'Use the ol.Map#pixelRatio value when requesting the image from the remote server. Default is true.'
		});
		var imageMapGuideUseOverlayCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '使用覆盖图',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageMapGuideUseOverlay',
			inputValue: '1',
			checked: true,
			qtip: 'If true, will use GETDYNAMICMAPOVERLAYIMAGE.'
		});
		var imageMapGuideRatioNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '比率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageMapGuideRatio',
			value: 1,
			minValue: 0,
			qtip: 'Ratio. 1 means image requests are the size of the map viewport, 2 means twice the width and height of the map viewport, and so on. Must be 1 or higher. Default is 1.'
		});
		var imageMapGuideResolutionsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageMapGuideResolutions',
			rows: 7,
			maxLength: 255,
			qtip: 'Resolutions. If specified, requests will be made for these resolutions only.'
		});
		var imageMapGuideProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageMapGuideProjection',
			maxLength: 255,
			qtip: 'Projection.'
		});
		// ImageStatic
		var imageStaticUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageStaticUrl',
			maxLength: 255,
			qtip: 'Image URL. Required.'
		});
		var imageStaticLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageStaticLogo',
			maxLength: 255,
			qtip: 'Optional logo.'
		});
		var imageStaticProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageStaticProjection',
			maxLength: 255,
			qtip: 'Projection.'
		});
		var imageStaticImageExtentText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '图像范围',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageStaticImageExtent',
			maxLength: 255,
			qtip: 'Extent of the image in map coordinates. This is the [left, bottom, right, top] map coordinates of your image. Required.'
		});
		var imageStaticImageSizeWidthNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '图像宽',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageStaticImageSizeWidth',
			minValue: 1,
			allowDecimals: false,
			qtip: 'Size of the image in pixels.'
		});
		var imageStaticImageSizeHeightNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '图像高',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageStaticImageSizeHeight',
			minValue: 1,
			allowDecimals: false,
			qtip: 'Size of the image in pixels.'
		});
		var imageStaticCrossOriginComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_crossOrigin',
			fieldLabel: '跨域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageStaticCrossOrigin',
			qtip: 'The crossOrigin attribute for loaded images. Note that you must provide a crossOrigin value if you are using the WebGL renderer or if you want to access pixel data with the Canvas renderer. See https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more detail.'
		});
		// ImageWMS
		var imageWMSUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageWMSUrl',
			maxLength: 255,
			qtip: 'WMS service URL.'
		});
		var imageWMSLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageWMSLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var imageWMSProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageWMSProjection',
			maxLength: 255,
			qtip: 'Projection.'
		});
		var imageWMSParamsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '参数',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageWMSParams',
			value: '{LAYERS: \'\'}',
			rows: 7,
			maxLength: 255,
			qtip: 'WMS request parameters. At least a LAYERS param is required. STYLES is \'\' by default. VERSION is 1.3.0 by default. WIDTH, HEIGHT, BBOX and CRS (SRS for WMS version < 1.3.0) will be set dynamically. Required.'
		});
		var imageWMSHidpiCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '高分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageWMSHidpi',
			inputValue: '1',
			checked: true,
			qtip: 'Use the ol.Map#pixelRatio value when requesting the image from the remote server. Default is true.'
		});
		var imageWMSServerTypeComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_sserverType',
			fieldLabel: '服务类型',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageWMSServerType',
			maxLength: 255,
			qtip: 'The type of the remote WMS server: mapserver, geoserver or qgis. Only needed if hidpi is true. Default is undefined.'
		});
		var imageWMSRatioNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '比率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageWMSRatio',
			value: 1.5,
			minValue: 0,
			qtip: 'Ratio. 1 means image requests are the size of the map viewport, 2 means twice the width and height of the map viewport, and so on. Must be 1 or higher. Default is 1.5.'
		});
		var imageWMSResolutionsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageWMSResolutions',
			rows: 7,
			maxLength: 255,
			qtip: 'Resolutions. If specified, requests will be made for these resolutions only.'
		});
		var imageWMSCrossOriginComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_crossOrigin',
			fieldLabel: '跨域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'imageWMSCrossOrigin',
			qtip: 'The crossOrigin attribute for loaded images. Note that you must provide a crossOrigin value if you are using the WebGL renderer or if you want to access pixel data with the Canvas renderer. See https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more detail.'
		});
		// Raster
		var rasterSourcesPicker = Ext.create('Base.gis.source.SourcePicker', {
			single: false,
			fieldLabel: '数据源',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'rasterSources',
			onDetermine: function(records) {
				var str = '';
				for (var i = 0; i < records.length; i++) {
					str += records[i].get('name') + ',';
				}
				if (str.length > 0) {
					rasterSourcesPicker.setValue(str.substring(0, str.length - 1));
				}
			}
		});
		var rasterThreadsNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '线程数',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'rasterThreads',
			value: 0,
			minValue: 0,
			allowDecimals: false,
			qtip: 'By default, operations will be run in a single worker thread. To avoid using workers altogether, set threads: 0. For pixel operations, operations can be run in multiple worker threads. Note that there is additional overhead in transferring data to multiple workers, and that depending on the user\'s system, it may not be possible to parallelize the work.'
		});
		var rasterOperationTypeComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_operationType',
			fieldLabel: '操作类型',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'rasterOperationType',
			value: 'pixel',
			qtip: 'Operation type. Supported values are \'pixel\' and \'image\'. By default, \'pixel\' operations are assumed, and operations will be called with an array of pixels from input sources. If set to \'image\', operations will be called with an array of ImageData objects from input sources.'
		});
		// BingMaps
		var bingMapsKeyText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'KEY',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'bingMapsKey',
			maxLength: 255,
			qtip: 'Bing Maps API key. Get yours at http://bingmapsportal.com/. Required.'
		});
		var bingMapsImagerySetText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '影像集',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'bingMapsImagerySet',
			maxLength: 255,
			qtip: 'Type of imagery. Required.'
		});
		var bingMapsCultureText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '文化',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'bingMapsCulture',
			value: 'en-us',
			maxLength: 255,
			qtip: 'Culture code. Default is en-us.'
		});
		var bingMapsMaxZoomNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最大缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'bingMapsMaxZoom',
			value: 21,
			minValue: 0,
			allowDecimals: false,
			qtip: 'Max zoom. Default is what\'s advertized by the BingMaps service (21 currently).'
		});
		// TileArcGISRest
		var tileArcGISRestUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileArcGISRestUrl',
			maxLength: 255,
			qtip: 'ArcGIS Rest service URL for a Map Service or Image Service. The url should include /MapServer or /ImageServer.'
		});
		var tileArcGISRestUrlsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: 'URLs',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileArcGISRestUrls',
			rows: 7,
			maxLength: 255,
			qtip: 'ArcGIS Rest service urls. Use this instead of url when the ArcGIS Service supports multiple urls for export requests.'
		});
		var tileArcGISRestLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileArcGISRestLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var tileArcGISRestProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileArcGISRestProjection',
			maxLength: 255,
			qtip: 'Projection.'
		});
		var tileArcGISRestCrossOriginComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_crossOrigin',
			fieldLabel: '跨域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileArcGISRestCrossOrigin',
			qtip: 'The crossOrigin attribute for loaded images. Note that you must provide a crossOrigin value if you are using the WebGL renderer or if you want to access pixel data with the Canvas renderer. See https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more detail.'
		});
		// TileJSON
		var tileJSONUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileJSONUrl',
			maxLength: 255,
			qtip: 'URL to the TileJSON file. Required.'
		});
		var tileJSONCrossOriginComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_crossOrigin',
			fieldLabel: '跨域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileJSONCrossOrigin',
			qtip: 'The crossOrigin attribute for loaded images. Note that you must provide a crossOrigin value if you are using the WebGL renderer or if you want to access pixel data with the Canvas renderer. See https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more detail.'
		});
		// TileWMS
		var tileWMSUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSUrl',
			maxLength: 255,
			qtip: 'WMS service URL.'
		});
		var tileWMSUrlsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: 'URLs',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSUrls',
			rows: 7,
			maxLength: 255,
			qtip: 'WMS service urls. Use this instead of url when the WMS supports multiple urls for GetMap requests.'
		});
		var tileWMSLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var tileWMSProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSProjection',
			maxLength: 255,
			qtip: 'Projection.'
		});
		var tileWMSParamsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '参数',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSParams',
			value: '{LAYERS: \'\',\r\nSTYLES: \'\',\r\nVERSION: \'1.3.0\'}',
			rows: 7,
			maxLength: 255,
			qtip: 'WMS request parameters. At least a LAYERS param is required. STYLES is \'\' by default. VERSION is 1.3.0 by default. WIDTH, HEIGHT, BBOX and CRS (SRS for WMS version < 1.3.0) will be set dynamically. Required.'
		});
		var tileWMSHidpiCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '高分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSHidpi',
			inputValue: '1',
			checked: true,
			qtip: 'Use the ol.Map#pixelRatio value when requesting the image from the remote server. Default is true.'
		});
		var tileWMSServerTypeComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_sserverType',
			fieldLabel: '服务类型',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSServerType',
			maxLength: 255,
			qtip: 'The type of the remote WMS server. Currently only used when hidpi is true. Default is undefined.'
		});
		var tileWMSGutterNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '瓦片周围像素',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSGutter',
			value: 0,
			minValue: 0,
			allowDecimals: false,
			qtip: 'The size in pixels of the gutter around image tiles to ignore. By setting this property to a non-zero value, images will be requested that are wider and taller than the tile size by a value of 2 x gutter. Defaults to zero. Using a non-zero value allows artifacts of rendering at tile edges to be ignored. If you control the WMS service it is recommended to address "artifacts at tile edges" issues by properly configuring the WMS service. For example, MapServer has a tile_map_edge_buffer configuration parameter for this. See http://mapserver.org/output/tile_mode.html.'
		});
		var tileWMSMaxZoomNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最大缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSMaxZoom',
			minValue: 0,
			allowDecimals: false,
			qtip: 'Maximum zoom.'
		});
		var tileWMSCrossOriginComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_crossOrigin',
			fieldLabel: '跨域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileWMSCrossOrigin',
			qtip: 'The crossOrigin attribute for loaded images. Note that you must provide a crossOrigin value if you are using the WebGL renderer or if you want to access pixel data with the Canvas renderer. See https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more detail.'
		});
		// WMTS
		var wmtsUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsUrl',
			maxLength: 255,
			qtip: 'A URL for the service. For the RESTful request encoding, this is a URL template. For KVP encoding, it is normal URL. A {?-?} template pattern, for example subdomain{a-f}.domain.com, may be used instead of defining each one separately in the urls option.'
		});
		var wmtsUrlsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: 'URLs',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsUrls',
			rows: 7,
			maxLength: 255,
			qtip: 'An array of URLs. Requests will be distributed among the URLs in this array.'
		});
		var wmtsLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var wmtsProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsProjection',
			maxLength: 255,
			qtip: 'Projection.'
		});
		var wmtsRequestEncodingComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_requestEncoding',
			fieldLabel: '请求编码',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsRequestEncoding',
			value: 'KVP',
			qtip: 'Request encoding. Default is KVP.'
		});
		var wmtsLayerText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '图层',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsLayer',
			maxLength: 255,
			qtip: 'Layer name as advertised in the WMTS capabilities. Required.'
		});
		var wmtsStyleText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsStyle',
			maxLength: 255,
			qtip: 'Style name as advertised in the WMTS capabilities. Required.'
		});
		var wmtsMatrixSetText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '矩阵集',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsMatrixSet',
			maxLength: 255,
			qtip: 'Matrix set. Required.'
		});
		var wmtsTilePixelRatioNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '瓦片像素比率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsTilePixelRatio',
			value: 1,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The pixel ratio used by the tile service. For example, if the tile service advertizes 256px by 256px tiles but actually sends 512px by 512px images (for retina/hidpi devices) then tilePixelRatio should be set to 2. Default is 1.'
		});
		var wmtsVersionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '版本',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsVersion',
			value: '1.0.0',
			maxLength: 255,
			qtip: 'WMTS version. Default is 1.0.0.'
		});
		var wmtsFormatText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '格式',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsFormat',
			value: 'image/jpeg',
			maxLength: 255,
			qtip: 'Image format. Default is image/jpeg.'
		});
		var wmtsDimensionsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '规模',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsDimensions',
			rows: 7,
			maxLength: 255,
			qtip: 'Additional "dimensions" for tile requests. This is an object with properties named like the advertised WMTS dimensions.'
		});
		var wmtsMaxZoomNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最大缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsMaxZoom',
			minValue: 0,
			allowDecimals: false,
			qtip: 'Maximum zoom.'
		});
		var wmtsCrossOriginComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_crossOrigin',
			fieldLabel: '跨域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'wmtsCrossOrigin',
			qtip: 'The crossOrigin attribute for loaded images. Note that you must provide a crossOrigin value if you are using the WebGL renderer or if you want to access pixel data with the Canvas renderer. See https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more detail.'
		});
		// MapQuest
		var mapQuestUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mapQuestUrl',
			maxLength: 255,
			qtip: 'URL template. Must include {x}, {y} or {-y}, and {z} placeholders.'
		});
		var mapQuestLayerText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '图层',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mapQuestLayer',
			maxLength: 255,
			qtip: 'Layer. Possible values are osm, sat, and hyb. Required.'
		});
		// OSM
		var osmUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'osmUrl',
			maxLength: 255,
			qtip: 'URL template. Must include {x}, {y} or {-y}, and {z} placeholders. Default is //{a-c}.tile.openstreetmap.org/{z}/{x}/{y}.png.'
		});
		var osmMaxZoomNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最大缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'osmMaxZoom',
			value: 19,
			minValue: 0,
			allowDecimals: false,
			qtip: 'Max zoom. Default is 19.'
		});
		var osmCrossOriginComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_crossOrigin',
			fieldLabel: '跨域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'osmCrossOrigin',
			value: 'anonymous',
			qtip: 'The crossOrigin attribute for loaded images. Note that you must provide a crossOrigin value if you are using the WebGL renderer or if you want to access pixel data with the Canvas renderer. See https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more detail. Default is anonymous.'
		});
		// Stamen
		var stamenUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'stamenUrl',
			maxLength: 255,
			qtip: 'URL template. Must include {x}, {y} or {-y}, and {z} placeholders.'
		});
		var stamenLayerText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '图层',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'stamenLayer',
			maxLength: 255,
			qtip: 'Layer. Required.'
		});
		var stamenMinZoomNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最小缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'stamenMinZoom',
			minValue: 0,
			allowDecimals: false,
			qtip: 'Minimum zoom.'
		});
		var stamenMaxZoomNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最大缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'stamenMaxZoom',
			minValue: 0,
			allowDecimals: false,
			qtip: 'Maximum zoom.'
		});
		var stamenOpaqueCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '不透明',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'stamenOpaque',
			inputValue: '1',
			qtip: 'Whether the layer is opaque.'
		});
		// XYZ
		var xyzUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'xyzUrl',
			maxLength: 255,
			qtip: 'URL template. Must include {x}, {y} or {-y}, and {z} placeholders. A {?-?} template pattern, for example subdomain{a-f}.domain.com, may be used instead of defining each one separately in the urls option.'
		});
		var xyzUrlsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: 'URLs',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'xyzUrls',
			rows: 7,
			maxLength: 255,
			qtip: 'An array of URL templates.'
		});
		var xyzLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'xyzLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var xyzProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'xyzProjection',
			value: 'EPSG:3857',
			maxLength: 255,
			qtip: 'Projection. Default is EPSG:3857.'
		});
		var xyzTilePixelRatioNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '瓦片像素比率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'xyzTilePixelRatio',
			value: 1,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The pixel ratio used by the tile service. For example, if the tile service advertizes 256px by 256px tiles but actually sends 512px by 512px images (for retina/hidpi devices) then tilePixelRatio should be set to 2. Default is 1.'
		});
		var xyzTileSizeComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_tileSize',
			fieldLabel: '瓦片尺寸',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'xyzTileSize',
			value: '256',
			qtip: 'The tile size used by the tile service. Default is [256, 256] pixels.'
		});
		var xyzMaxZoomNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最大缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'xyzMaxZoom',
			value: 18,
			minValue: 0,
			allowDecimals: false,
			qtip: 'Optional max zoom level. Default is 18.'
		});
		var xyzCrossOriginComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_crossOrigin',
			fieldLabel: '跨域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'xyzCrossOrigin',
			qtip: 'The crossOrigin attribute for loaded images. Note that you must provide a crossOrigin value if you are using the WebGL renderer or if you want to access pixel data with the Canvas renderer. See https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more detail.'
		});
		// Zoomify
		var zoomifyUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomifyUrl',
			maxLength: 255,
			qtip: 'Prefix of URL template. Required.'
		});
		var zoomifyLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomifyLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var zoomifyTierSizeCalculationComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_tierSizeCalculation',
			fieldLabel: '瓦片计算方法',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomifyTierSizeCalculation',
			maxLength: 255,
			qtip: 'Tier size calculation method: default or truncated.'
		});
		var zoomifySizeWidthNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '宽',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomifySizeWidth',
			minValue: 1,
			allowDecimals: false,
			qtip: 'Size of the image. Required.'
		});
		var zoomifySizeHeightNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '高',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomifySizeHeight',
			minValue: 1,
			allowDecimals: false,
			qtip: 'Size of the image. Required.'
		});
		var zoomifyCrossOriginComboBox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_source_crossOrigin',
			fieldLabel: '跨域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomifyCrossOrigin',
			qtip: 'The crossOrigin attribute for loaded images. Note that you must provide a crossOrigin value if you are using the WebGL renderer or if you want to access pixel data with the Canvas renderer. See https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more detail.'
		});
		// TileUTFGrid
		var tileUTFGridUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileUTFGridUrl',
			maxLength: 255,
			qtip: 'Required.'
		});
		var tileUTFGridPreemptiveCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '优先可见',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileUTFGridPreemptive',
			inputValue: '1',
			checked: true,
			qtip: 'If true the TileUTFGrid source loads the tiles based on their "visibility". This improves the speed of response, but increases traffic. Note that if set to false, you need to pass true as opt_request to the forDataAtCoordinateAndResolution method otherwise no data will ever be loaded. Default is true.'
		});
		// Vector

		// Cluster
		var clusterSourcePicker = Ext.create('Base.gis.source.SourcePicker', {
			fieldLabel: '数据源',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'clusterSource',
			onDetermine: function(record) {
				clusterSourcePicker.setValue(record.get('name'));
			}
		});
		var clusterLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'clusterLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var clusterProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'clusterProjection',
			maxLength: 255,
			qtip: 'Projection.'
		});
		var clusterDistanceNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '距离',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'clusterDistance',
			value: 20,
			minValue: 0,
			allowDecimals: false,
			qtip: 'Minimum distance in pixels between clusters. Default is 20.'
		});
		var clusterExtentText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '范围',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'clusterExtent',
			maxLength: 255,
			qtip: 'Extent.'
		});
		var clusterFormatTrigger = Ext.create('Ext.form.field.Trigger', {
			fieldLabel: '格式',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'clusterFormat',
			onTriggerClick: function() {
				Ext.create('Base.gis.style.StyleWindow', {
					styleData: Ext.isEmpty(clusterFormatTrigger.getValue()) ? null : Ext.decode(clusterFormatTrigger.getValue()),
					onDetermine: function(format) {
						clusterFormatTrigger.setValue(Ext.encode(format));
					}
				}).show();
			},
			qtip: 'Style for the box.'
		});
		// TileVector
		var tileVectorUrlText = Ext.create('Ext.form.field.Text', {
			fieldLabel: 'URL',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileVectorUrl',
			maxLength: 255,
			qtip: 'URL template. Must include {x}, {y} or {-y}, and {z} placeholders.'
		});
		var tileVectorUrlsTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: 'URLs',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileVectorUrls',
			rows: 7,
			maxLength: 255,
			qtip: 'An array of URL templates.'
		});
		var tileVectorLogoText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileVectorLogo',
			maxLength: 255,
			qtip: 'Logo.'
		});
		var tileVectorFormatTrigger = Ext.create('Ext.form.field.Trigger', {
			fieldLabel: '格式',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'tileVectorFormat',
			onTriggerClick: function() {
				Ext.create('Base.gis.style.StyleWindow', {
					styleData: Ext.isEmpty(tileVectorFormatTrigger.getValue()) ? null : Ext.decode(tileVectorFormatTrigger.getValue()),
					onDetermine: function(format) {
						tileVectorFormatTrigger.setValue(Ext.encode(format));
					}
				}).show();
			},
			qtip: 'Style for the box.'
		});

		var formPanel = Ext.create('Ext.form.Panel', {
			itemId: 'editForm',
			frame: true,
			autoHeight: true,
			autoScroll: true,
			buttonAlign: 'center',
			reader: Ext.create('Ext.data.JsonReader', {
				model: 'FormModel'
			}),
			layout: 'border',
			items: [navigationGridPanel, {
				xtype: 'container',
				region: 'center',
				layout: 'card',
				items: [{
					itemId: 'form_0',
					xtype: 'container',
					layout: 'form',
					items: [nameText, sortNumber]
				}, {
					itemId: 'form_1',
					xtype: 'container',
					layout: 'fit',
					items: [sourceTypeRadioGroup]
				}, {
					itemId: 'form_2',
					xtype: 'container',
					layout: 'fit',
					items: [{
						xtype: 'container'
					}]
				}, {
					itemId: 'form_3_ImageCanvas',
					xtype: 'container',
					layout: 'form',
					items: [imageCanvasLogoText, imageCanvasProjectionText, imageCanvasRatioNumber, imageCanvasResolutionsTextArea, imageCanvasStateComboBox]
				}, {
					itemId: 'form_3_ImageVector',
					xtype: 'container',
					layout: 'form',
					items: [imageVectorSourcePicker, imageVectorStyleTrigger, imageVectorLogoText, imageVectorProjectionText, imageVectorRatioNumber, imageVectorResolutionsTextArea, imageMapGuideRatioNumber, imageMapGuideResolutionsTextArea, imageMapGuideProjectionText]
				}, {
					itemId: 'form_3_ImageMapGuide',
					xtype: 'container',
					layout: 'form',
					items: [imageMapGuideUrlText, imageMapGuideParamsTextArea, imageMapGuideDisplayDpiNumber, imageMapGuideMetersPerUnitNumber, imageMapGuideHidpiCheckbox, imageMapGuideUseOverlayCheckbox, imageMapGuideRatioNumber, imageMapGuideResolutionsTextArea, imageMapGuideProjectionText]
				}, {
					itemId: 'form_3_ImageStatic',
					xtype: 'container',
					layout: 'form',
					items: [imageStaticUrlText, imageStaticLogoText, imageStaticProjectionText, imageStaticImageExtentText, {
						xtype: 'container',
						layout: 'column',
						items: [imageStaticImageSizeWidthNumber, imageStaticImageSizeHeightNumber]
					}, imageStaticCrossOriginComboBox]
				}, {
					itemId: 'form_3_ImageWMS',
					xtype: 'container',
					layout: 'form',
					items: [imageWMSUrlText, imageWMSLogoText, imageWMSProjectionText, imageWMSParamsTextArea, imageWMSHidpiCheckbox, imageWMSServerTypeComboBox, imageWMSRatioNumber, imageWMSResolutionsTextArea, imageWMSCrossOriginComboBox]
				}, {
					itemId: 'form_3_Raster',
					xtype: 'container',
					layout: 'form',
					items: [rasterSourcesPicker, rasterThreadsNumber, rasterOperationTypeComboBox]
				}, {
					itemId: 'form_3_BingMaps',
					xtype: 'container',
					layout: 'form',
					items: [bingMapsKeyText, bingMapsImagerySetText, bingMapsCultureText, bingMapsMaxZoomNumber]
				}, {
					itemId: 'form_3_TileArcGISRest',
					xtype: 'container',
					layout: 'form',
					items: [tileArcGISRestUrlText, tileArcGISRestUrlsTextArea, tileArcGISRestLogoText, tileArcGISRestProjectionText, tileArcGISRestCrossOriginComboBox]
				}, {
					itemId: 'form_3_TileJSON',
					xtype: 'container',
					layout: 'form',
					items: [tileJSONUrlText, tileJSONCrossOriginComboBox]
				}, {
					itemId: 'form_3_TileWMS',
					xtype: 'container',
					layout: 'form',
					items: [tileWMSUrlText, tileWMSUrlsTextArea, tileWMSLogoText, tileWMSProjectionText, tileWMSParamsTextArea, tileWMSHidpiCheckbox, tileWMSServerTypeComboBox, tileWMSGutterNumber, tileWMSMaxZoomNumber, tileWMSCrossOriginComboBox]
				}, {
					itemId: 'form_3_WMTS',
					xtype: 'container',
					layout: 'form',
					items: [wmtsUrlText, wmtsUrlsTextArea, wmtsLogoText, wmtsProjectionText, wmtsRequestEncodingComboBox, wmtsLayerText, wmtsStyleText, wmtsMatrixSetText, wmtsTilePixelRatioNumber, wmtsVersionText, wmtsFormatText, wmtsDimensionsTextArea, wmtsMaxZoomNumber, wmtsCrossOriginComboBox]
				}, {
					itemId: 'form_3_MapQuest',
					xtype: 'container',
					layout: 'form',
					items: [mapQuestUrlText, mapQuestLayerText]
				}, {
					itemId: 'form_3_OSM',
					xtype: 'container',
					layout: 'form',
					items: [osmUrlText, osmMaxZoomNumber, osmCrossOriginComboBox]
				}, {
					itemId: 'form_3_Stamen',
					xtype: 'container',
					layout: 'form',
					items: [stamenUrlText, stamenLayerText, stamenMinZoomNumber, stamenMaxZoomNumber, stamenOpaqueCheckbox]
				}, {
					itemId: 'form_3_XYZ',
					xtype: 'container',
					layout: 'form',
					items: [xyzUrlText, xyzUrlsTextArea, xyzLogoText, xyzProjectionText, xyzTilePixelRatioNumber, xyzTileSizeComboBox, xyzMaxZoomNumber, xyzCrossOriginComboBox]
				}, {
					itemId: 'form_3_Zoomify',
					xtype: 'container',
					layout: 'form',
					items: [zoomifyUrlText, zoomifyLogoText, zoomifyTierSizeCalculationComboBox, {
						xtype: 'container',
						layout: 'column',
						items: [zoomifySizeWidthNumber, zoomifySizeHeightNumber]
					}, zoomifyCrossOriginComboBox]
				}, {
					itemId: 'form_3_TileUTFGrid',
					xtype: 'container',
					layout: 'form',
					items: [tileUTFGridUrlText, tileUTFGridPreemptiveCheckbox]
				}, {
					itemId: 'form_3_Vector',
					xtype: 'container',
					html: 'form_3_Vector'
				}, {
					itemId: 'form_3_Cluster',
					xtype: 'container',
					layout: 'form',
					items: [clusterSourcePicker, clusterLogoText, clusterProjectionText, clusterDistanceNumber, clusterExtentText, clusterFormatTrigger]
				}, {
					itemId: 'form_3_TileVector',
					xtype: 'container',
					layout: 'form',
					items: [tileVectorUrlText, tileVectorUrlsTextArea, tileVectorLogoText, tileVectorFormatTrigger]
				}]
			}],
			buttons:[{
				itemId: 'prevButton',
				xtype: 'button',
				text: '上一步',
				handler: function(button, e) {
					this.navigationForm(-1);
				},
				scope: this,
				hidden: true
			}, {
				itemId: 'nextButton',
				xtype: 'button',
				text: '下一步',
				handler: function(button, e) {
					this.navigationForm(1);
				},
				scope: this
			}, {
				itemId: 'saveButton',
				xtype: 'button',
				text: '保存',
				handler: this.saveForm,
				scope: this,
				hidden: true
			}, {
				xtype: 'button',
				text: '关闭',
				handler: this.closeForm,
				scope: this
			}],
			plugins: [Ext.create('Base.ux.HelpQtip')]
		});
		this.add(formPanel);
	},
	navigationForm: function(incr) {
		var formPanel = this.getComponent('editForm');
		var layout = formPanel.items.get(1).getLayout();
		var index = parseInt(layout.getActiveItem().itemId.split('_')[1]) + incr;
		var isValid = incr < 0;
		if (index == 0) {
			this.down('#prevButton').setVisible(false);
			this.down('#nextButton').setVisible(true);
			this.down('#saveButton').setVisible(false);
		} else if (index == 1) {
			if (incr > 0) {
				isValid = formPanel.getForm().findField('name').isValid();
			}
			if (isValid) {
				this.down('#prevButton').setVisible(true);
				this.down('#nextButton').setVisible(true);
				this.down('#saveButton').setVisible(false);
			}
		} else if (index == 2) {
			if (incr > 0) {
				isValid = formPanel.down('#sourceTypeRadioGroup').isValid();
			}
			if (isValid) {
				this.down('#prevButton').setVisible(true);
				this.down('#nextButton').setVisible(true);
				this.down('#saveButton').setVisible(false);
			}
		} else if (index == 3) {
			if (incr > 0) {
				isValid = formPanel.down('#sourceClassNameRadioGroup').isValid();
			}
			if (isValid) {
				this.down('#prevButton').setVisible(true);
				this.down('#nextButton').setVisible(false);
				this.down('#saveButton').setVisible(true);
			}
		}
		if (isValid) {
			var appendIndex = '';
			if (index == 2) {
				var container = this.down('#form_2');
				container.removeAll();

				var sourceType = formPanel.down('#sourceTypeRadioGroup').getValue().sourceType;
				if ('ol.source.Image' == sourceType) {
					var sourceClassNameRadioGroup = Ext.create('Ext.form.RadioGroup', {
						itemId: 'sourceClassNameRadioGroup',
						fieldLabel: '<span style="color: #FF0000;">*</span>实现类',
						labelAlign: 'right',
						labelSeparator: '：',
						columns: 1,
						vertical: true,
						allowBlank: false,
						items: [{
							boxLabel: '画布',
							name: 'sourceClassName',
							inputValue: 'ol.source.ImageCanvas',
							checked: true,
							qtip: 'Base class for image sources where a canvas element is the image.'
						}, {
							boxLabel: '矢量',
							name: 'sourceClassName',
							inputValue: 'ol.source.ImageVector',
							qtip: 'An image source whose images are canvas elements into which vector features read from a vector source (ol.source.Vector) are drawn. An ol.source.ImageVector object is to be used as the source of an image layer (ol.layer.Image). Image layers are rotated, scaled, and translated, as opposed to being re-rendered, during animations and interactions. So, like any other image layer, an image layer configured with an ol.source.ImageVector will exhibit this behaviour. This is in contrast to a vector layer, where vector features are re-drawn during animations and interactions.'
						}, {
							boxLabel: 'Map Guide 服务',
							name: 'sourceClassName',
							inputValue: 'ol.source.ImageMapGuide',
							qtip: 'Source for images from Mapguide servers'
						}, {
							boxLabel: '静态',
							name: 'sourceClassName',
							inputValue: 'ol.source.ImageStatic',
							qtip: 'A layer source for displaying a single, static image.'
						}, {
							boxLabel: 'WMS',
							name: 'sourceClassName',
							inputValue: 'ol.source.ImageWMS',
							qtip: 'Source for WMS servers providing single, untiled images.'
						}, {
							boxLabel: '光栅',
							name: 'sourceClassName',
							inputValue: 'ol.source.Raster',
							qtip: 'A source that transforms data from any number of input sources using an array of ol.raster.Operation functions to transform input pixel values into output pixel values.'
						}]
					});
					if (!Ext.isEmpty(this.sourceClassName)) {
						sourceClassNameRadioGroup.setValue({sourceClassName: this.sourceClassName});
					}
					container.add(sourceClassNameRadioGroup);
				} else if ('ol.source.Tile' == sourceType) {
					var sourceClassNameRadioGroup = Ext.create('Ext.form.RadioGroup', {
						itemId: 'sourceClassNameRadioGroup',
						fieldLabel: '<span style="color: #FF0000;">*</span>实现类',
						labelAlign: 'right',
						labelSeparator: '：',
						columns: 1,
						vertical: true,
						allowBlank: false,
						items: [{
							boxLabel: '必应地图',
							name: 'sourceClassName',
							inputValue: 'ol.source.BingMaps',
							qtip: 'Layer source for Bing Maps tile data.'
						}, {
							boxLabel: 'ArcGIS Rest',
							name: 'sourceClassName',
							inputValue: 'ol.source.TileArcGISRest',
							checked: true,
							qtip: 'Layer source for tile data from ArcGIS Rest services. Map and Image Services are supported.For cached ArcGIS services, better performance is available using the ol.source.XYZ data source.'
						}, {
							boxLabel: 'JSON',
							name: 'sourceClassName',
							inputValue: 'ol.source.TileJSON',
							qtip: 'Layer source for tile data in TileJSON format.'
						}, {
							boxLabel: 'WMS',
							name: 'sourceClassName',
							inputValue: 'ol.source.TileWMS',
							qtip: 'Layer source for tile data from WMS servers.'
						}, {
							boxLabel: 'WMTS',
							name: 'sourceClassName',
							inputValue: 'ol.source.WMTS',
							qtip: 'Layer source for tile data from WMTS servers.'
						}, {
							boxLabel: 'Map Quest',
							name: 'sourceClassName',
							inputValue: 'ol.source.MapQuest',
							qtip: 'Layer source for the MapQuest tile server.'
						}, {
							boxLabel: 'Open Street Map',
							name: 'sourceClassName',
							inputValue: 'ol.source.OSM',
							qtip: 'Layer source for the OpenStreetMap tile server.'
						}, {
							boxLabel: 'Stamen',
							name: 'sourceClassName',
							inputValue: 'ol.source.Stamen',
							qtip: 'Layer source for the Stamen tile server.'
						}, {
							boxLabel: '瓦片模板',
							name: 'sourceClassName',
							inputValue: 'ol.source.XYZ',
							qtip: 'Layer source for tile data with URLs in a set XYZ format that are defined in a URL template. By default, this follows the widely-used Google grid where x 0 and y 0 are in the top left. Grids like TMS where x 0 and y 0 are in the bottom left can be used by using the {-y} placeholder in the URL template, so long as the source does not have a custom tile grid. In this case, ol.source.TileImage can be used with a tileUrlFunction such as:tileUrlFunction: function(coordinate) { return \'http://mapserver.com/\' + coordinate[0] + \'/\' + coordinate[1] + \'/\' + coordinate[2] + \'.png\'; }'
						}, {
							boxLabel: 'Zoomify',
							name: 'sourceClassName',
							inputValue: 'ol.source.Zoomify',
							qtip: 'Layer source for tile data in Zoomify format.'
						}, {
							boxLabel: 'UTF Grid',
							name: 'sourceClassName',
							inputValue: 'ol.source.TileUTFGrid',
							qtip: 'Layer source for UTFGrid interaction data loaded from TileJSON format.'
						}]
					});
					if (!Ext.isEmpty(this.sourceClassName)) {
						sourceClassNameRadioGroup.setValue({sourceClassName: this.sourceClassName});
					}
					container.add(sourceClassNameRadioGroup);
				} else if ('ol.source.Vector' == sourceType) {
					var sourceClassNameRadioGroup = Ext.create('Ext.form.RadioGroup', {
						itemId: 'sourceClassNameRadioGroup',
						fieldLabel: '<span style="color: #FF0000;">*</span>实现类',
						labelAlign: 'right',
						labelSeparator: '：',
						columns: 1,
						vertical: true,
						allowBlank: false,
						items: [{
							boxLabel: '矢量',
							name: 'sourceClassName',
							inputValue: 'ol.source.Vector',
							checked: true,
							qtip: 'Provides a source of features for vector layers.'
						}, {
							boxLabel: '集群',
							name: 'sourceClassName',
							inputValue: 'ol.source.Cluster',
							qtip: 'Layer source to cluster vector data.'
						}, {
							boxLabel: '瓦片矢量',
							name: 'sourceClassName',
							inputValue: 'ol.source.TileVector',
							qtip: 'A vector source in one of the supported formats, where the data is divided into tiles in a fixed grid pattern.'
						}]
					});
					if (!Ext.isEmpty(this.sourceClassName)) {
						sourceClassNameRadioGroup.setValue({sourceClassName: this.sourceClassName});
					}
					container.add(sourceClassNameRadioGroup);
				}
			} else if (index == 3) {
				this.sourceClassName = formPanel.down('#sourceClassNameRadioGroup').getValue().sourceClassName;
				var sourceClassNames = this.sourceClassName.split('.');
				appendIndex = '_' + sourceClassNames[sourceClassNames.length - 1];
			}
			layout.setActiveItem('form_' + index + appendIndex);
			formPanel.items.get(0).getSelectionModel().select(index);
		}
	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/gis/source/save.ctrl',
				waitMsg: '正在保存数据，请稍候...',
				success: function(form, action) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
					this.closeForm();

					this.opener.queryData();
				},
				failure: function(form, action) {
					Ext.Msg.alert('系统提示', '无法保存数据！');
				},
				scope: this
			});
		}
	},
	closeForm: function() {
		this.close();
	}
});
