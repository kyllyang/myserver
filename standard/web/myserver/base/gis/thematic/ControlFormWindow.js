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
				{name: 'controlAttribution'},
				{name: 'controlAttributionClassName'},
				{name: 'controlAttributionCollapsible'},
				{name: 'controlAttributionCollapsed'},
				{name: 'controlAttributionLabel'},
				{name: 'controlAttributionCollapseLabel'},
				{name: 'controlAttributionTipLabel'},
				{name: 'controlFullScreen'},
				{name: 'controlFullScreenClassName'},
				{name: 'controlFullScreenKeys'},
				{name: 'controlFullScreenLabel'},
				{name: 'controlFullScreenLabelActive'},
				{name: 'controlFullScreenTipLabel'},
				{name: 'controlMousePosition'},
				{name: 'controlMousePositionClassName'},
				{name: 'controlMousePositionCoordinateFormat'},
				{name: 'controlMousePositionProjection'},
				{name: 'controlMousePositionUndefinedHTML'},
				{name: 'controlOverviewMap'},
				{name: 'controlOverviewMapCollapsible'},
				{name: 'controlOverviewMapCollapsed'},
				{name: 'controlOverviewMapLabel'},
				{name: 'controlOverviewMapCollapseLabel'},
				{name: 'controlOverviewMapTipLabel'},
				{name: 'controlRotate'},
				{name: 'controlRotateClassName'},
				{name: 'controlRotateAutoHide'},
				{name: 'controlRotateDuration'},
				{name: 'controlRotateLabel'},
				{name: 'controlRotateTipLabel'},
				{name: 'controlScaleLine'},
				{name: 'controlScaleLineClassName'},
				{name: 'controlScaleLineMinWidth'},
				{name: 'controlScaleLineUnits'},
				{name: 'controlZoom'},
				{name: 'controlZoomClassName'},
				{name: 'controlZoomDuration'},
				{name: 'controlZoomZoomInLabel'},
				{name: 'controlZoomZoomOutLabel'},
				{name: 'controlZoomDelta'},
				{name: 'controlZoomZoomInTipLabel'},
				{name: 'controlZoomZoomOutTipLabel'},
				{name: 'controlZoomSlider'},
				{name: 'controlZoomSliderClassName'},
				{name: 'controlZoomSliderDuration'},
				{name: 'controlZoomSliderMaxResolution'},
				{name: 'controlZoomSliderMinResolution'},
				{name: 'controlZoomToExtent'},
				{name: 'controlZoomToExtentClassName'},
				{name: 'controlZoomToExtentExtent'},
				{name: 'controlZoomToExtentLabel'},
				{name: 'controlZoomToExtentTipLabel'}
			]
		});

		var controlAttributionCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '署名',
			name: 'controlAttribution',
			inputValue: '1',
			checked: true,
			qtip: 'Control to show all the controlAttributions associated with the layer sources in the map. This control is one of the default controls included in maps. By default it will show in the bottom right portion of the map, but this can be changed by using a css selector for .ol-controlAttribution.'
		});
		var controlAttributionClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionClassName',
			value: 'ol-controlAttribution',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-controlAttribution.'
		});
		var controlAttributionCollapsibleCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '可折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionCollapsible',
			inputValue: '1',
			checked: true,
			qtip: 'Specify if controlAttributions can be collapsed. If you use an OSM source, should be set to false — see OSM Copyright — Default is true.'
		});
		var controlAttributionCollapsedCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '默认折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionCollapsed',
			inputValue: '1',
			checked: true,
			qtip: 'When set to true, tiles will be loaded while interacting with the map. This may improve the user experience, but can also make map panning and controlZooming choppy on devices with slow memory.'
		});
		var controlAttributionLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionLabel',
			value: 'i',
			maxLength: 100,
			qtip: 'Text label to use for the collapsed controlAttributions button. Default is i. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlAttributionCollapseLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '折叠标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionCollapseLabel',
			value: '»',
			maxLength: 100,
			qtip: 'Text label to use for the expanded controlAttributions button. Default is ». Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlAttributionTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionTipLabel',
			value: 'controlAttributions',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is controlAttributions'
		});
		var controlFullScreenCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '全屏',
			name: 'controlFullScreen',
			inputValue: '1',
			qtip: 'Provides a button that when clicked fills up the full screen with the map. When in full screen mode, a close button is shown to exit full screen mode. The controlFullScreen API is used to toggle the map in full screen mode.'
		});
		var controlFullScreenClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlFullScreenClassName',
			value: 'ol-full-screen',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-full-screen.'
		});
		var controlFullScreenKeysCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '按键控制',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlFullScreenKeys',
			inputValue: '1',
			qtip: 'Full keyboard access.'
		});
		var controlFullScreenLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlFullScreenLabel',
			value: '\u2194',
			maxLength: 100,
			qtip: 'Text label to use for the button. Default is \u2194 (an arrow). Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlFullScreenLabelActiveText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '激活标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlFullScreenLabelActive',
			value: '\u00d7',
			maxLength: 100,
			qtip: 'Text label to use for the button when full-screen is active. Default is \u00d7 (a cross). Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlFullScreenTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlFullScreenTipLabel',
			value: 'Toggle full-screen',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Toggle full-screen'
		});
		var controlMousePositionCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '鼠标位置',
			name: 'controlMousePosition',
			inputValue: '1',
			qtip: 'A control to show the 2D coordinates of the mouse cursor. By default, these are in the view projection, but can be in any supported projection. By default the control is shown in the top right corner of the map, but this can be changed by using the css selector .ol-mouse-position.'
		});
		var controlMousePositionClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlMousePositionClassName',
			value: 'ol-mouse-position',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-mouse-position.'
		});
		var controlMousePositionCoordinateFormatNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '坐标格式',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlMousePositionCoordinateFormat',
			value: 9,
			minValue: 0,
			allowDecimals: false,
			qtip: 'Coordinate format.'
		});
		var controlMousePositionProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlMousePositionProjection',
			maxLength: 100,
			qtip: 'Projection.'
		});
		var controlMousePositionUndefinedHTMLText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '未定义HTML',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlMousePositionUndefinedHTML',
			maxLength: 100,
			qtip: 'Markup for undefined coordinates. Default is `` (empty string).'
		});
		var controlOverviewMapCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '鹰眼',
			name: 'controlOverviewMap',
			inputValue: '1',
			qtip: 'Create a new control with a map acting as an overview map for an other defined map.'
		});
		var controlOverviewMapCollapsibleCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '可折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlOverviewMapCollapsible',
			inputValue: '1',
			checked: true,
			qtip: 'Whether the control can be collapsed or not. Default to true.'
		});
		var controlOverviewMapCollapsedCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '默认折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlOverviewMapCollapsed',
			inputValue: '1',
			checked: true,
			qtip: 'Whether the control should start collapsed or not (expanded). Default to true.'
		});
		var controlOverviewMapLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlOverviewMapLabel',
			value: '»',
			maxLength: 100,
			qtip: 'Text label to use for the collapsed controlOverviewMap button. Default is ». Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlOverviewMapCollapseLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '折叠标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlOverviewMapCollapseLabel',
			value: '«',
			maxLength: 100,
			qtip: 'Text label to use for the expanded controlOverviewMap button. Default is «. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlOverviewMapTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlOverviewMapTipLabel',
			value: 'Overview map',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Overview map'
		});
		var controlRotateCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '旋转',
			name: 'controlRotate',
			inputValue: '1',
			checked: true,
			qtip: 'A button control to reset rotation to 0. To style this control use css selector .ol-controlRotate. A .ol-hidden css selector is added to the button when the rotation is 0.'
		});
		var controlRotateClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlRotateClassName',
			value: 'ol-controlRotate',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-controlRotate.'
		});
		var controlRotateAutoHideCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '自动隐藏',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlRotateAutoHide',
			inputValue: '1',
			checked: true,
			qtip: 'Hide the control when rotation is 0. Default is true.'
		});
		var controlRotateDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlRotateDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var controlRotateLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlRotateLabel',
			value: '⇧',
			maxLength: 100,
			qtip: 'Text label to use for the controlRotate button. Default is ⇧. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlRotateTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlRotateTipLabel',
			value: 'Reset rotation',
			maxLength: 100,
			qtip: 'Text label to use for the controlRotate tip. Default is Reset rotation'
		});
		var controlScaleLineCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '比例尺',
			name: 'controlScaleLine',
			inputValue: '1',
			qtip: 'A control displaying rough x-axis distances, calculated for the center of the viewport. No scale line will be shown when the x-axis distance cannot be calculated in the view projection (e.g. at or beyond the poles in EPSG:4326). By default the scale line will show in the bottom left portion of the map, but this can be changed by using the css selector .ol-scale-line.'
		});
		var controlScaleLineClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlScaleLineClassName',
			value: 'ol-scale-line',
			maxLength: 100,
			qtip: 'CSS Class name. Default is ol-scale-line.'
		});
		var controlScaleLineMinWidthNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最小宽度',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlScaleLineMinWidth',
			value: 64,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Minimum width in pixels. Default is 64.'
		});
		var controlScaleLineUnitsCombobox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_control_unit',
			fieldLabel: '单位',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlScaleLineUnits',
			value: 'metric',
			qtip: 'Units. Default is metric.'
		});
		var controlZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '缩放',
			name: 'controlZoom',
			inputValue: '1',
			checked: true,
			qtip: 'A control with 2 buttons, one for zoom in and one for zoom out. This control is one of the default controls of a map. To style this control use css selectors .ol-zoom-in and .ol-zoom-out.'
		});
		var controlZoomClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlZoomClassName',
			value: 'ol-zoom',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-zoom.'
		});
		var controlZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlZoomDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var controlZoomZoomInLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '放大标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlZoomZoomInLabel',
			value: '+',
			maxLength: 100,
			qtip: 'Text label to use for the zoom-in button. Default is +. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlZoomZoomOutLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '缩小标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlZoomZoomOutLabel',
			value: '-',
			maxLength: 100,
			qtip: 'Text label to use for the zoom-out button. Default is -. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlZoomDeltaNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '变焦',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlZoomDelta',
			minValue: 1,
			allowDecimals: false,
			qtip: 'The zoom delta applied on each click.'
		});
		var controlZoomZoomInTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '放大提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlZoomZoomInTipLabel',
			value: 'Zoom in',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Zoom in'
		});
		var controlZoomZoomOutTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '缩小提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlZoomZoomOutTipLabel',
			value: 'Zoom out',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Zoom out'
		});
		var controlZoomSliderCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '缩放滑块',
			name: 'controlZoomSlider',
			inputValue: '1',
			qtip: 'A slider type of control for zooming.'
		});
		var controlZoomSliderClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlZoomSliderClassName',
			value: 'ol-zoomslider',
			maxLength: 100,
			qtip: 'CSS class name. Default is ol-zoomslider'
		});
		var controlZoomSliderDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlZoomSliderDuration',
			value: 200,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 200.'
		});
		var controlZoomSliderMaxResolutionNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最大分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlZoomSliderMaxResolution',
			qtip: 'Maximum resolution.'
		});
		var controlZoomSliderMinResolutionNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最小分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlZoomSliderMinResolution',
			qtip: 'Minimum resolution.'
		});
		var controlZoomToExtentCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '缩放范围',
			name: 'controlZoomToExtent',
			inputValue: '1',
			qtip: 'A button control which, when pressed, changes the map view to a specific extent. To style this control use the css selector .ol-zoom-extent.'
		});
		var controlZoomToExtentClassNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '样式类名',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlZoomToExtentClassName',
			value: 'ol-zoom-extent',
			maxLength: 100,
			qtip: 'Class name. Default is ol-zoom-extent.'
		});
		var controlZoomToExtentExtentText = Ext.create('Ext.form.field.Text', {
			colspan: 2,
			width: 468,
			fieldLabel: '范围',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlZoomToExtentExtent',
			maxLength: 100,
			qtip: 'The extent to zoom to. If undefined the validity extent of the view projection is used.'
		});
		var controlZoomToExtentLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlZoomToExtentLabel',
			value: 'E',
			maxLength: 100,
			qtip: 'Text label to use for the button. Default is E. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlZoomToExtentTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 234,
			name: 'controlZoomToExtentTipLabel',
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
				items: [controlAttributionCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [controlAttributionClassNameText, controlAttributionCollapsibleCheckbox, controlAttributionCollapsedCheckbox, controlAttributionLabelText, controlAttributionCollapseLabelText, controlAttributionTipLabelText]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [controlFullScreenCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [controlFullScreenClassNameText, controlFullScreenKeysCheckbox, {
						xtype: 'container'
					}, controlFullScreenLabelText, controlFullScreenLabelActiveText, controlFullScreenTipLabelText]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [controlMousePositionCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [controlMousePositionClassNameText, controlMousePositionCoordinateFormatNumber, controlMousePositionProjectionText, controlMousePositionUndefinedHTMLText, {
						xtype: 'container'
					}, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [controlOverviewMapCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [controlOverviewMapCollapsibleCheckbox, controlOverviewMapCollapsedCheckbox, {
						xtype: 'container'
					}, controlOverviewMapLabelText, controlOverviewMapCollapseLabelText, controlOverviewMapTipLabelText]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [controlRotateCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [controlRotateClassNameText, controlRotateAutoHideCheckbox, controlRotateDurationNumber, controlRotateLabelText, controlRotateTipLabelText, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [controlScaleLineCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [controlScaleLineClassNameText, controlScaleLineMinWidthNumber, controlScaleLineUnitsCombobox]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [controlZoomCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [controlZoomClassNameText, {
						xtype: 'container'
					}, {
						xtype: 'container'
					}, controlZoomDurationNumber, controlZoomZoomInLabelText, controlZoomZoomOutLabelText, controlZoomDeltaNumber, controlZoomZoomInTipLabelText, controlZoomZoomOutTipLabelText]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [controlZoomSliderCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [controlZoomSliderClassNameText, controlZoomSliderDurationNumber, {
						xtype: 'container'
					}, controlZoomSliderMaxResolutionNumber, controlZoomSliderMinResolutionNumber, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [controlZoomToExtentCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [controlZoomToExtentClassNameText, controlZoomToExtentExtentText, controlZoomToExtentLabelText, controlZoomToExtentTipLabelText, {
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
