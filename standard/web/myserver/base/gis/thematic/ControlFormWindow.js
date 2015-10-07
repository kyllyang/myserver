Ext.define('Base.gis.thematic.ControlFormWindow', {
	extend: 'Ext.window.Window',

	mapId: null,
	readOnlyForm: false,
	opener: null,

	title: '控件信息',
	width: 800,
	height: 615,
	border: false,
	modal: true,
	resizable: false,
	layout: 'fit',

	initComponent: function() {
		this.callParent();

		var that = this;

		Ext.define('FormModel', {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'attribution'},
				{name: 'attributionClassName'},
				{name: 'attributionCollapsible'},
				{name: 'attributionCollapsed'},
				{name: 'attributionLabel'},
				{name: 'attributionCollapseLabel'},
				{name: 'attributionTipLabel'},
				{name: 'fullScreen'},
				{name: 'fullScreenClassName'},
				{name: 'fullScreenKeys'},
				{name: 'fullScreenLabel'},
				{name: 'fullScreenLabelActive'},
				{name: 'fullScreenTipLabel'},
				{name: 'mousePosition'},
				{name: 'mousePositionClassName'},
				{name: 'mousePositionCoordinateFormat'},
				{name: 'mousePositionProjection'},
				{name: 'mousePositionUndefinedHTML'},
				{name: 'overviewMap'},
				{name: 'overviewMapCollapsible'},
				{name: 'overviewMapCollapsed'},
				{name: 'overviewMapLabel'},
				{name: 'overviewMapCollapseLabel'},
				{name: 'overviewMapTipLabel'},
				{name: 'rotate'},
				{name: 'rotateClassName'},
				{name: 'rotateAutoHide'},
				{name: 'rotateDuration'},
				{name: 'rotateLabel'},
				{name: 'rotateTipLabel'},
				{name: 'scaleLine'},
				{name: 'scaleLineClassName'},
				{name: 'scaleLineMinWidth'},
				{name: 'scaleLineUnits'},
				{name: 'zoom'},
				{name: 'zoomClassName'},
				{name: 'zoomDuration'},
				{name: 'zoomZoomInLabel'},
				{name: 'zoomZoomOutLabel'},
				{name: 'zoomDelta'},
				{name: 'zoomZoomInTipLabel'},
				{name: 'zoomZoomOutTipLabel'},
				{name: 'zoomSlider'},
				{name: 'zoomSliderClassName'},
				{name: 'zoomSliderDuration'},
				{name: 'zoomSliderMaxResolution'},
				{name: 'zoomSliderMinResolution'},
				{name: 'zoomToExtent'},
				{name: 'zoomToExtentClassName'},
				{name: 'zoomToExtentExtent'},
				{name: 'zoomToExtentLabel'},
				{name: 'zoomToExtentTipLabel'}
			]
		});

		var attributionCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '署名',
			name: 'attribution',
			inputValue: '1',
			checked: true,
			qtip: 'Control to show all the attributions associated with the layer sources in the map. This control is one of the default controls included in maps. By default it will show in the bottom right portion of the map, but this can be changed by using a css selector for .ol-attribution.'
		});
		var attributionClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'attributionClassName',
			value: 'ol-attribution',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-attribution.'
		});
		var attributionCollapsibleCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '可折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'attributionCollapsible',
			inputValue: '1',
			checked: true,
			qtip: 'Specify if attributions can be collapsed. If you use an OSM source, should be set to false — see OSM Copyright — Default is true.'
		});
		var attributionCollapsedCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '默认折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'attributionCollapsed',
			inputValue: '1',
			checked: true,
			qtip: 'When set to true, tiles will be loaded while interacting with the map. This may improve the user experience, but can also make map panning and zooming choppy on devices with slow memory.'
		});
		var attributionLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'attributionLabel',
			value: 'i',
			maxLength: 100,
			qtip: 'Text label to use for the collapsed attributions button. Default is i. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var attributionCollapseLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '折叠标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'attributionCollapseLabel',
			value: '»',
			maxLength: 100,
			qtip: 'Text label to use for the expanded attributions button. Default is ». Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var attributionTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'attributionTipLabel',
			value: 'Attributions',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Attributions'
		});
		var fullScreenCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '全屏',
			name: 'fullScreen',
			inputValue: '1',
			qtip: 'Provides a button that when clicked fills up the full screen with the map. When in full screen mode, a close button is shown to exit full screen mode. The Fullscreen API is used to toggle the map in full screen mode.'
		});
		var fullScreenClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'fullScreenClassName',
			value: 'ol-full-screen',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-full-screen.'
		});
		var fullScreenKeysCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '按键控制',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'fullScreenKeys',
			inputValue: '1',
			qtip: 'Full keyboard access.'
		});
		var fullScreenLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'fullScreenLabel',
			value: '\u2194',
			maxLength: 100,
			qtip: 'Text label to use for the button. Default is \u2194 (an arrow). Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var fullScreenLabelActiveText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '激活标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'fullScreenLabelActive',
			value: '\u00d7',
			maxLength: 100,
			qtip: 'Text label to use for the button when full-screen is active. Default is \u00d7 (a cross). Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var fullScreenTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'fullScreenTipLabel',
			value: 'Toggle full-screen',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Toggle full-screen'
		});
		var mousePositionCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '鼠标位置',
			name: 'mousePosition',
			inputValue: '1',
			qtip: 'A control to show the 2D coordinates of the mouse cursor. By default, these are in the view projection, but can be in any supported projection. By default the control is shown in the top right corner of the map, but this can be changed by using the css selector .ol-mouse-position.'
		});
		var mousePositionClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mousePositionClassName',
			value: 'ol-mouse-position',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-mouse-position.'
		});
		var mousePositionCoordinateFormatNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '坐标格式',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'mousePositionCoordinateFormat',
			value: 9,
			minValue: 0,
			allowDecimals: false,
			qtip: 'Coordinate format.'
		});
		var mousePositionProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mousePositionProjection',
			maxLength: 100,
			qtip: 'Projection.'
		});
		var mousePositionUndefinedHTMLText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '未定义HTML',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mousePositionUndefinedHTML',
			maxLength: 100,
			qtip: 'Markup for undefined coordinates. Default is `` (empty string).'
		});
		var overviewMapCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '鹰眼',
			name: 'overviewMap',
			inputValue: '1',
			qtip: 'Create a new control with a map acting as an overview map for an other defined map.'
		});
		var overviewMapCollapsibleCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '可折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'overviewMapCollapsible',
			inputValue: '1',
			checked: true,
			qtip: 'Whether the control can be collapsed or not. Default to true.'
		});
		var overviewMapCollapsedCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '默认折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'overviewMapCollapsed',
			inputValue: '1',
			checked: true,
			qtip: 'Whether the control should start collapsed or not (expanded). Default to true.'
		});
		var overviewMapLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'overviewMapLabel',
			value: '»',
			maxLength: 100,
			qtip: 'Text label to use for the collapsed overviewmap button. Default is ». Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var overviewMapCollapseLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '折叠标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'overviewMapCollapseLabel',
			value: '«',
			maxLength: 100,
			qtip: 'Text label to use for the expanded overviewmap button. Default is «. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var overviewMapTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'overviewMapTipLabel',
			value: 'Overview map',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Overview map'
		});
		var rotateCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '旋转',
			name: 'rotate',
			inputValue: '1',
			checked: true,
			qtip: 'A button control to reset rotation to 0. To style this control use css selector .ol-rotate. A .ol-hidden css selector is added to the button when the rotation is 0.'
		});
		var rotateClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'rotateClassName',
			value: 'ol-rotate',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-rotate.'
		});
		var rotateAutoHideCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '自动隐藏',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'rotateAutoHide',
			inputValue: '1',
			checked: true,
			qtip: 'Hide the control when rotation is 0. Default is true.'
		});
		var rotateDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'rotateDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var rotateLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'rotateLabel',
			value: '⇧',
			maxLength: 100,
			qtip: 'Text label to use for the rotate button. Default is ⇧. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var rotateTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'rotateTipLabel',
			value: 'Reset rotation',
			maxLength: 100,
			qtip: 'Text label to use for the rotate tip. Default is Reset rotation'
		});
		var scaleLineCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '比例尺',
			name: 'scaleLine',
			inputValue: '1',
			qtip: 'A control displaying rough x-axis distances, calculated for the center of the viewport. No scale line will be shown when the x-axis distance cannot be calculated in the view projection (e.g. at or beyond the poles in EPSG:4326). By default the scale line will show in the bottom left portion of the map, but this can be changed by using the css selector .ol-scale-line.'
		});
		var scaleLineClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'scaleLineClassName',
			value: 'ol-scale-line',
			maxLength: 100,
			qtip: 'CSS Class name. Default is ol-scale-line.'
		});
		var scaleLineMinWidthNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最小宽度',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'scaleLineMinWidth',
			value: 64,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Minimum width in pixels. Default is 64.'
		});
		var scaleLineUnitsCombobox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_control_unit',
			fieldLabel: '单位',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'scaleLineUnits',
			value: 'metric',
			qtip: 'Units. Default is metric.'
		});
		var zoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '缩放',
			name: 'zoom',
			inputValue: '1',
			checked: true,
			qtip: 'A control with 2 buttons, one for zoom in and one for zoom out. This control is one of the default controls of a map. To style this control use css selectors .ol-zoom-in and .ol-zoom-out.'
		});
		var zoomClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomClassName',
			value: 'ol-zoom',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-zoom.'
		});
		var zoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var zoomZoomInLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '放大标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomZoomInLabel',
			value: '+',
			maxLength: 100,
			qtip: 'Text label to use for the zoom-in button. Default is +. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var zoomZoomOutLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '缩小标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomZoomOutLabel',
			value: '-',
			maxLength: 100,
			qtip: 'Text label to use for the zoom-out button. Default is -. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var zoomDeltaNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '变焦',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomDelta',
			minValue: 1,
			allowDecimals: false,
			qtip: 'The zoom delta applied on each click.'
		});
		var zoomZoomInTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '放大提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomZoomInTipLabel',
			value: 'Zoom in',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Zoom in'
		});
		var zoomZoomOutTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '缩小提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomZoomOutTipLabel',
			value: 'Zoom out',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Zoom out'
		});
		var zoomSliderCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '缩放滑块',
			name: 'zoomSlider',
			inputValue: '1',
			qtip: 'A slider type of control for zooming.'
		});
		var zoomSliderClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomSliderClassName',
			value: 'ol-zoomslider',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-zoomslider'
		});
		var zoomSliderDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomSliderDuration',
			value: 200,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 200.'
		});
		var zoomSliderMaxResolutionNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最大分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomSliderMaxResolution',
			qtip: 'Maximum resolution.'
		});
		var zoomSliderMinResolutionNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最小分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomSliderMinResolution',
			qtip: 'Minimum resolution.'
		});
		var zoomToExtentCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '缩放到范围',
			name: 'zoomToExtent',
			inputValue: '1',
			qtip: 'A button control which, when pressed, changes the map view to a specific extent. To style this control use the css selector .ol-zoom-extent.'
		});
		var zoomToExtentClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomToExtentClassName',
			value: 'ol-zoom-extent',
			maxLength: 100,
			qtip: 'Class name. Default is ol-zoom-extent.'
		});
		var zoomToExtentExtentText = Ext.create('Ext.form.field.Text', {
			colspan: 2,
			width: 468,
			fieldLabel: '范围',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomToExtentExtent',
			maxLength: 100,
			qtip: 'The extent to zoom to. If undefined the validity extent of the view projection is used.'
		});
		var zoomToExtentLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomToExtentLabel',
			value: 'E',
			maxLength: 100,
			qtip: 'Text label to use for the button. Default is E. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var zoomToExtentTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'zoomToExtentTipLabel',
			value: 'Zoom to extent',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Zoom to extent'
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
			layout: 'form',
			items: [{
				xtype: 'hidden',
				name: 'mapId',
				value: this.mapId
			}, {
				xtype: 'container',
				layout: 'column',
				items: [attributionCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [attributionClassNameText, attributionCollapsibleCheckbox, attributionCollapsedCheckbox, attributionLabelText, attributionCollapseLabelText, attributionTipLabelText]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [fullScreenCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [fullScreenClassNameText, fullScreenKeysCheckbox, {
						xtype: 'container'
					}, fullScreenLabelText, fullScreenLabelActiveText, fullScreenTipLabelText]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [mousePositionCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [mousePositionClassNameText, mousePositionCoordinateFormatNumber, mousePositionProjectionText, mousePositionUndefinedHTMLText, {
						xtype: 'container'
					}, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [overviewMapCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [overviewMapCollapsibleCheckbox, overviewMapCollapsedCheckbox, {
						xtype: 'container'
					}, overviewMapLabelText, overviewMapCollapseLabelText, overviewMapTipLabelText]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [rotateCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [rotateClassNameText, rotateAutoHideCheckbox, rotateDurationNumber, rotateLabelText, rotateTipLabelText, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [scaleLineCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [scaleLineClassNameText, scaleLineMinWidthNumber, scaleLineUnitsCombobox]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [zoomCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [zoomClassNameText, {
						xtype: 'container'
					}, {
						xtype: 'container'
					}, zoomDurationNumber, zoomZoomInLabelText, zoomZoomOutLabelText, zoomDeltaNumber, zoomZoomInTipLabelText, zoomZoomOutTipLabelText]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [zoomSliderCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [zoomSliderClassNameText, zoomSliderDurationNumber, {
						xtype: 'container'
					}, zoomSliderMaxResolutionNumber, zoomSliderMinResolutionNumber, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [zoomToExtentCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [zoomToExtentClassNameText, zoomToExtentExtentText, zoomToExtentLabelText, zoomToExtentTipLabelText, {
						xtype: 'container'
					}]
				}]
			}],
			buttons:[{
				xtype: 'button',
				text: '保存',
				handler: this.saveForm,
				scope: this,
				hidden: this.readOnlyForm
			}, {
				xtype: 'button',
				text: '关闭',
				handler: this.closeForm,
				scope: this
			}],
			plugins: [Ext.create('Base.ux.HelpQtip')]
		});
		this.add(formPanel);

		if (this.readOnlyForm) {
			formPanel.getForm().getFields().each(function(item, index, len) {
				item.setReadOnly(true);
			}, this);
		}

		if (this.mapId) {
			this.getComponent('editForm').getForm().load({
				url: ctx + '/gis/control/input.ctrl',
				params: {
					mapId: this.mapId
				},
				waitMsg: '正在载入数据...',
				success: function(form, action) {
				},
				failure: function() {
					Ext.Msg.alert('系统提示', '无法加载信息！');
				},
				scope: this
			});
		}
	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/gis/control/save.ctrl',
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
