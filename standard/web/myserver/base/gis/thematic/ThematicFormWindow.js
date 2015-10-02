Ext.define('Base.gis.thematic.ThematicFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,
	readOnlyForm: false,
	opener: null,

	title: '专题信息',
	width: 800,
	height: 600,
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
				{name: 'id'},
				{name: 'name'},
				{name: 'sort'},
				{name: 'mapId'},
				{name: 'mapLogo'},
				{name: 'mapLoadTilesWhileAnimating'},
				{name: 'mapLoadTilesWhileInteracting'},
				{name: 'mapRenderer'},
				{name: 'viewId'},
				{name: 'viewProjection'},
				{name: 'viewCenter'},
				{name: 'viewExtent'},
				{name: 'viewResolutions'},
				{name: 'viewResolution'},
				{name: 'layerGroup'},
				{name: 'controlAttribution'},
				{name: 'controlAttributionCollapsible'},
				{name: 'controlAttributionCollapsed'},
				{name: 'controlAttributionLabel'},
				{name: 'controlAttributionCollapseLabel'},
				{name: 'controlAttributionTipLabel'},
				{name: 'controlFullScreen'},
				{name: 'controlFullScreenKeys'},
				{name: 'controlFullScreenLabel'},
				{name: 'controlFullScreenLabelActive'},
				{name: 'controlFullScreenTipLabel'},
				{name: 'controlMousePosition'},
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
				{name: 'controlRotateAutoHide'},
				{name: 'controlRotateDuration'},
				{name: 'controlRotateLabel'},
				{name: 'controlRotateTipLabel'},
				{name: 'controlScaleLine'},
				{name: 'controlScaleLineMinWidth'},
				{name: 'controlScaleLineUnits'},
				{name: 'controlZoom'},
				{name: 'controlZoomDuration'},
				{name: 'controlZoomZoomInLabel'},
				{name: 'controlZoomZoomOutLabel'},
				{name: 'controlZoomDelta'},
				{name: 'controlZoomZoomInTipLabel'},
				{name: 'controlZoomZoomOutTipLabel'},
				{name: 'controlZoomSlider'},
				{name: 'controlZoomSliderDuration'},
				{name: 'controlZoomSliderMaxResolution'},
				{name: 'controlZoomSliderMinResolution'},
				{name: 'controlZoomToExtent'},
				{name: 'controlZoomToExtentExtent'},
				{name: 'controlZoomToExtentLabel'},
				{name: 'controlZoomToExtentTipLabel'},
				{name: 'interactionDoubleClickZoom'},
				{name: 'interactionDoubleClickZoomDuration'},
				{name: 'interactionDoubleClickZoomDelta'},
				{name: 'interactionDragAndDrop'},
				{name: 'interactionDragAndDropProjection'},
				{name: 'interactionDragBox'},
				{name: 'interactionDragPan'},
				{name: 'interactionDragPanKineticDecay'},
				{name: 'interactionDragPanKineticMinVelocity'},
				{name: 'interactionDragPanKineticDelay'},
				{name: 'interactionDragRotate'},
				{name: 'interactionDragRotateDuration'},
				{name: 'interactionDragRotateAndZoom'},
				{name: 'interactionDragRotateAndZoomDuration'},
				{name: 'interactionDragZoom'},
				{name: 'interactionDragZoomDuration'},
				{name: 'interactionDraw'},
				{name: 'interactionDrawClickTolerance'},
				{name: 'interactionDrawSnapTolerance'},
				{name: 'interactionDrawWrapX'},
				{name: 'interactionKeyboardPan'},
				{name: 'interactionKeyboardPanDuration'},
				{name: 'interactionKeyboardPanPixelDelta'},
				{name: 'interactionKeyboardZoom'},
				{name: 'interactionKeyboardZoomDuration'},
				{name: 'interactionKeyboardZoomDelta'},
				{name: 'interactionModify'},
				{name: 'interactionModifyPixelTolerance'},
				{name: 'interactionModifyWrapX'},
				{name: 'interactionMouseWheelZoom'},
				{name: 'interactionMouseWheelZoomDuration'},
				{name: 'interactionPinchRotate'},
				{name: 'interactionPinchRotateDuration'},
				{name: 'interactionPinchRotateThreshold'},
				{name: 'interactionPinchZoom'},
				{name: 'interactionPinchZoomDuration'},
				{name: 'interactionSelect'},
				{name: 'interactionSelectMulti'},
				{name: 'interactionSelectWrapX'},
				{name: 'interactionSnap'},
				{name: 'interactionSnapPixelTolerance'},
				{name: 'interactionTranslate'}
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
	//	var mapLogoFile = Ext.create('Ext.form.field.File', {
		var mapLogoFile = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mapLogo',
			buttonText: '浏览...',
			qtip: 'The map logo. A logo to be displayed on the map at all times.'
		});
		var mapLoadTilesWhileAnimatingCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.5,
			fieldLabel: '动画加载瓦片',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mapLoadTilesWhileAnimating',
			inputValue: '1',
			qtip: 'When set to true, tiles will be loaded during animations. This may improve the user experience, but can also make animations stutter on devices with slow memory.'
		});
		var mapLoadTilesWhileInteractingCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.5,
			fieldLabel: '交互加载瓦片',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mapLoadTilesWhileInteracting',
			inputValue: '1',
			qtip: 'When set to true, tiles will be loaded while interacting with the map. This may improve the user experience, but can also make map panning and zooming choppy on devices with slow memory.'
		});
		var rendererDisplay = Ext.create('Ext.form.field.Display', {
			columnWidth: 0.13,
			xtype: 'displayfield',
			fieldLabel: '渲染机制顺序',
			labelAlign: 'right',
			labelSeparator: '：',
			qtip: 'By default, Canvas, DOM and WebGL renderers are tested for support in that order, and the first supported used. Note that at present only the Canvas renderer supports vector data.'
		});
		var projectionText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'viewProjection',
			value: 'EPSG:3857',
			maxLength: 100,
			allowBlank: false,
			qtip: 'The projection. Default is EPSG:3857 (Spherical Mercator).'
		});
		var centerText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>中心点',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'viewCenter',
			maxLength: 100,
			allowBlank: false,
			qtip: 'The initial center for the view. The coordinate system for the center is specified with the projection option. Default is undefined, and layer sources will not be fetched if this is not set.'
		});
		var extentText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '<span style="color: #FF0000;">*</span>范围',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'viewExtent',
			maxLength: 100,
			allowBlank: false,
			qtip: 'The extent that constrains the center, in other words, center cannot be set outside this extent. Default is undefined.'
		});
		var resolutionsTextarea = Ext.create('Ext.form.field.TextArea', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'viewResolutions',
			rows: 10,
			maxLength: 255,
			allowBlank: false,
			listeners: {
				change: function(textarea, newValue, oldValue, eOpts) {
					if (!Ext.isEmpty(newValue)) {
						var strs = newValue.split(',');
						resolutionSlider.setMaxValue(strs.length > 0 ? strs.length : 1);
						this.getComponent('editForm').getForm().findField('viewResolution').setValue(strs[strs.length - 1]);
					}
				},
				scope: this
			},
			qtip: 'Resolutions to determine the resolution constraint. If set the maxResolution, minResolution, minZoom, maxZoom, and zoomFactor options are ignored.'
		});
		var resolutionSlider = Ext.create('Ext.slider.Single', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>默认分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			vertical: true,
			height: 150,
			increment: 1,
			minValue: 1,
			maxValue: 10,
			tipText: function(thumb) {
				var str = resolutionsTextarea.getValue();
				if (!Ext.isEmpty(str)) {
					var strs = str.split(',');
					that.getComponent('editForm').getForm().findField('viewResolution').setValue(strs[strs.length - thumb.value]);
					return strs[strs.length - thumb.value];
				}
			},
			qtip: 'The initial resolution for the view. The units are projection units per pixel (e.g. meters per pixel). An alternative to setting this is to set zoom. Default is undefined, and layer sources will not be fetched if neither this nor zoom are defined.'
		});
		var layerTreePanel = Ext.create('Base.gis.thematic.LayerTreePanel');
		// control
		var controlAttributionCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '署名',
			name: 'controlAttribution',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Control to show all the attributions associated with the layer sources in the map. This control is one of the default controls included in maps. By default it will show in the bottom right portion of the map, but this can be changed by using a css selector for .ol-attribution.'
		});
		var controlAttributionCollapsibleCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '可折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionCollapsible',
			inputValue: '1',
			checked: true,
			qtip: 'Specify if attributions can be collapsed. If you use an OSM source, should be set to false — see OSM Copyright — Default is true.'
		});
		var controlAttributionCollapsedCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '默认折叠',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionCollapsed',
			inputValue: '1',
			checked: true,
			qtip: 'When set to true, tiles will be loaded while interacting with the map. This may improve the user experience, but can also make map panning and zooming choppy on devices with slow memory.'
		});
		var controlAttributionLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionLabel',
			value: 'i',
			maxLength: 100,
			qtip: 'Text label to use for the collapsed attributions button. Default is i. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlAttributionCollapseLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '折叠标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionCollapseLabel',
			value: '»',
			maxLength: 100,
			qtip: 'Text label to use for the expanded attributions button. Default is ». Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlAttributionTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlAttributionTipLabel',
			value: 'Attributions',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Attributions'
		});
		var controlFullScreenCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '全屏',
			name: 'controlFullScreen',
			inputValue: '1',
			qtip: 'Provides a button that when clicked fills up the full screen with the map. When in full screen mode, a close button is shown to exit full screen mode. The Fullscreen API is used to toggle the map in full screen mode.'
		});
		var controlFullScreenKeysCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '按键',
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
		var controlMousePositionCoordinateFormatText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '坐标格式',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlMousePositionCoordinateFormat',
			maxLength: 100,
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
			qtip: 'Text label to use for the collapsed overviewmap button. Default is ». Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlOverviewMapCollapseLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '折叠标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlOverviewMapCollapseLabel',
			value: '«',
			maxLength: 100,
			qtip: 'Text label to use for the expanded overviewmap button. Default is «. Instead of text, also a Node (e.g. a span element) can be used.'
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
			readOnly: true,
			qtip: 'A button control to reset rotation to 0. To style this control use css selector .ol-rotate. A .ol-hidden css selector is added to the button when the rotation is 0.'
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
			qtip: 'Text label to use for the rotate button. Default is ⇧. Instead of text, also a Node (e.g. a span element) can be used.'
		});
		var controlRotateTipLabelText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '提示标签',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlRotateTipLabel',
			value: 'Reset rotation',
			maxLength: 100,
			qtip: 'Text label to use for the rotate tip. Default is Reset rotation'
		});
		var controlScaleLineCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '比例尺',
			name: 'controlScaleLine',
			inputValue: '1',
			qtip: 'A control displaying rough x-axis distances, calculated for the center of the viewport. No scale line will be shown when the x-axis distance cannot be calculated in the view projection (e.g. at or beyond the poles in EPSG:4326). By default the scale line will show in the bottom left portion of the map, but this can be changed by using the css selector .ol-scale-line.'
		});
		var controlScaleLineMinWidthNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最小宽度',
			labelAlign: 'right',
			labelSeparator: '：',
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
			name: 'controlScaleLineUnits',
			value: 'metric',
			allowBlank: true
		});
		var controlZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '缩放',
			name: 'controlZoom',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'A control with 2 buttons, one for zoom in and one for zoom out. This control is one of the default controls of a map. To style this control use css selectors .ol-zoom-in and .ol-zoom-out.'
		});
		var controlZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
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
		var controlZoomSliderDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlZoomSliderDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 200.'
		});
		var controlZoomSliderMaxResolutionNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最大分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'controlZoomSliderMaxResolution',
			qtip: 'Maximum resolution.'
		});
		var controlZoomSliderMinResolutionNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最小分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
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
		var controlZoomToExtentExtentText = Ext.create('Ext.form.field.Text', {
			colspan: 2,
			width: 511,
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
			name: 'controlZoomToExtentTipLabel',
			value: 'Zoom to extent',
			maxLength: 100,
			qtip: 'Text label to use for the button tip. Default is Zoom to extent'
		});
		// interaction
		var interactionDoubleClickZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '双击缩放',
			name: 'interactionDoubleClickZoom',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Allows the user to zoom by double-clicking on the map.'
		});
		var interactionDoubleClickZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionDoubleClickZoomDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var interactionDoubleClickZoomDeltaNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '变焦',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionDoubleClickZoomDelta',
			value: 1,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The zoom delta applied on each double click, default is 1.'
		});
		var interactionDragAndDropCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '拖放',
			name: 'interactionDragAndDrop',
			inputValue: '1',
			qtip: 'Handles input of vector data by drag and drop.'
		});
		var interactionDragAndDropProjectionText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionDragAndDropProjection',
			maxLength: 100,
			qtip: 'Target projection. By default, the map\'s view\'s projection is used.'
		});
		var interactionDragBoxCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '矩形选择',
			name: 'interactionDragBox',
			inputValue: '1',
			qtip: 'Allows the user to draw a vector box by clicking and dragging on the map, normally combined with an ol.events.condition that limits it to when the shift or other key is held down. This is used, for example, for zooming to a specific area of the map (see ol.interaction.DragZoom and ol.interaction.DragRotateAndZoom).This interaction is only supported for mouse devices.'
		});
		var interactionDragPanCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '拖动平移',
			name: 'interactionDragPan',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Allows the user to pan the map by dragging the map.'
		});
		var interactionDragPanKineticDecayNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '衰变',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 222,
			name: 'interactionDragPanKineticDecay',
			maxValue: 0,
			qtip: 'Rate of decay (must be negative).'
		});
		var interactionDragPanKineticMinVelocityNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最小速度',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 222,
			name: 'interactionDragPanKineticMinVelocity',
			minValue: 1,
			allowDecimals: false,
			qtip: 'Minimum velocity (pixels/millisecond).'
		});
		var interactionDragPanKineticDelayNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '延迟',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 222,
			name: 'interactionDragPanKineticDelay',
			minValue: 1,
			allowDecimals: false,
			qtip: 'Delay to consider to calculate the kinetic initial values (milliseconds).'
		});
		var interactionDragRotateCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '拖动旋转',
			name: 'interactionDragRotate',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Allows the user to rotate the map by clicking and dragging on the map, normally combined with an ol.events.condition that limits it to when the alt and shift keys are held down.This interaction is only supported for mouse devices.'
		});
		var interactionDragRotateDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionDragRotateDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var interactionDragRotateAndZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '拖动旋转和缩放',
			name: 'interactionDragRotateAndZoom',
			inputValue: '1',
			qtip: 'Allows the user to zoom and rotate the map by clicking and dragging on the map. By default, this interaction is limited to when the shift key is held down. This interaction is only supported for mouse devices. And this interaction is not included in the default interactions.'
		});
		var interactionDragRotateAndZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionDragRotateAndZoomDuration',
			value: 400,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 400.'
		});
		var interactionDragZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '拖动缩放',
			name: 'interactionDragZoom',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Allows the user to zoom the map by clicking and dragging on the map, normally combined with an ol.events.condition that limits it to when a key, shift by default, is held down.'
		});
		var interactionDragZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionDragZoomDuration',
			value: 200,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 200.'
		});
		var interactionDrawCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '绘制',
			name: 'interactionDraw',
			inputValue: '1',
			qtip: 'Interaction for drawing feature geometries.'
		});
		var interactionDrawClickToleranceNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '点击公差',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionDrawClickTolerance',
			value: 6,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The maximum distance in pixels between "down" and "up" for a "up" event to be considered a "click" event and actually add a point/vertex to the geometry being drawn. Default is 6 pixels. That value was chosen for the draw interaction to behave correctly on mouse as well as on touch devices.'
		});
		var interactionDrawSnapToleranceNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '捕捉公差',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionDrawSnapTolerance',
			value: 12,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Pixel distance for snapping to the drawing finish. Default is 12.'
		});
		var interactionDrawWrapXCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '水平换行',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionDrawWrapX',
			inputValue: '1',
			qtip: 'Wrap the world horizontally on the sketch overlay. Default is false.'
		});
		var interactionKeyboardPanCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '键盘平移',
			name: 'interactionKeyboardPan',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Allows the user to pan the map using keyboard arrows. Note that, although this interaction is by default included in maps, the keys can only be used when browser focus is on the element to which the keyboard events are attached. By default, this is the map div, though you can change this with the keyboardEventTarget in ol.Map. document never loses focus but, for any other element, focus will have to be on, and returned to, this element if the keys are to function. See also ol.interaction.KeyboardZoom.'
		});
		var interactionKeyboardPanDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionKeyboardPanDuration',
			value: 100,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 100.'
		});
		var interactionKeyboardPanPixelDeltaNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '像素间隔',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionKeyboardPanPixelDelta',
			value: 128,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Pixel The amount to pan on each key press. Default is 128 pixels.'
		});
		var interactionKeyboardZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '键盘缩放',
			name: 'interactionKeyboardZoom',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Allows the user to zoom the map using keyboard + and -. Note that, although this interaction is by default included in maps, the keys can only be used when browser focus is on the element to which the keyboard events are attached. By default, this is the map div, though you can change this with the keyboardEventTarget in ol.Map. document never loses focus but, for any other element, focus will have to be on, and returned to, this element if the keys are to function. See also ol.interaction.KeyboardPan.'
		});
		var interactionKeyboardZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionKeyboardZoomDuration',
			value: 100,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 100.'
		});
		var interactionKeyboardZoomDeltaNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '变焦',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionKeyboardZoomDelta',
			value: 1,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The amount to zoom on each key press. Default is 1.'
		});
		var interactionModifyCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '修改',
			name: 'interactionModify',
			inputValue: '1',
			qtip: 'Interaction for modifying feature geometries.'
		});
		var interactionModifyPixelToleranceNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '像素公差',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionModifyPixelTolerance',
			value: 10,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Pixel tolerance for considering the pointer close enough to a segment or vertex for editing. Default is 10.'
		});
		var interactionModifyWrapXCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '水平换行',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionModifyWrapX',
			inputValue: '1',
			qtip: 'Wrap the world horizontally on the sketch overlay. Default is false.'
		});
		var interactionMouseWheelZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '鼠标滚轮缩放',
			name: 'interactionMouseWheelZoom',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Allows the user to zoom the map by scrolling the mouse wheel.'
		});
		var interactionMouseWheelZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionMouseWheelZoomDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var interactionPinchRotateCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '触屏旋转',
			name: 'interactionPinchRotate',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Allows the user to rotate the map by twisting with two fingers on a touch screen.'
		});
		var interactionPinchRotateDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionPinchRotateDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The duration of the animation in milliseconds. Default is 250.'
		});
		var interactionPinchRotateThresholdNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '阈值',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionPinchRotateThreshold',
			value: 0.3,
			qtip: 'Minimal angle in radians to start a rotation. Default is 0.3.'
		});
		var interactionPinchZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '触屏缩放',
			name: 'interactionPinchZoom',
			inputValue: '1',
			checked: true,
			readOnly: true,
			qtip: 'Allows the user to zoom the map by pinching with two fingers on a touch screen.'
		});
		var interactionPinchZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionPinchZoomDuration',
			value: 400,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 400.'
		});
		var interactionSelectCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '选择',
			name: 'interactionSelect',
			inputValue: '1',
			qtip: 'Interaction for selecting vector features. By default, selected features are styled differently, so this interaction can be used for visual highlighting, as well as selecting features for other actions, such as modification or output. There are three ways of controlling which features are selected: using the browser event as defined by the condition and optionally the toggle, add/remove, and multi options; a layers filter; and a further feature filter using the filter option.'
		});
		var interactionSelectMultiCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '多选',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionSelectMulti',
			inputValue: '1',
			qtip: 'A boolean that determines if the default behaviour should select only single features or all (overlapping) features at the clicked map position. Default is false i.e single select'
		});
		var interactionSelectWrapXCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '水平换行',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionSelectWrapX',
			inputValue: '1',
			checked: true,
			qtip: 'Wrap the world horizontally on the selection overlay. Default is true.'
		});
		var interactionSnapCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '捕捉',
			name: 'interactionSnap',
			inputValue: '1',
			qtip: 'Handles snapping of vector features while modifying or drawing them. The features can come from a ol.source.Vector or ol.Collection Any interaction object that allows the user to interact with the features using the mouse can benefit from the snapping, as long as it is added before.'
		});
		var interactionSnapPixelToleranceNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '像素公差',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'interactionSnapPixelTolerance',
			value: 10,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Pixel tolerance for considering the pointer close enough to a segment or vertex for snapping. Default is 10 pixels.'
		});
		var interactionTranslateCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '移动',
			name: 'interactionTranslate',
			inputValue: '1',
			qtip: 'Interaction for translating (moving) features.'
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
			bodyStyle : 'overflow-x:visible; overflow-y:scroll',
			layout: 'form',
			items: [{
				xtype: 'hidden',
				name: 'id'
			}, {
				xtype: 'hidden',
				name: 'mapRenderer'
			}, {
				xtype: 'hidden',
				name: 'viewResolution'
			}, {
				xtype: 'hidden',
				name: 'mapId'
			}, {
				xtype: 'hidden',
				name: 'viewId'
			}, {
				xtype: 'hidden',
				name: 'layerGroup'
			}, {
				xtype: 'container',
				layout: 'column',
				items: [nameText, sortNumber]
			}, {
				xtype: 'fieldset',
				title: '地图',
				layout: 'form',
				items: [mapLogoFile, {
					xtype: 'container',
					layout: 'column',
					items: [mapLoadTilesWhileAnimatingCheckbox, mapLoadTilesWhileInteractingCheckbox]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [rendererDisplay, {
						xtype: 'container',
						itemId: 'rendererContainer',
						layout: 'hbox',
						items: [{
							xtype: 'button',
							text: 'Canvas',
							width: 80,
							margin: '0 5 0 0',
							listeners: {
								render: this.rendererDragDropButton,
								scope: this
							}
						}, {
							xtype: 'button',
							text: 'DOM',
							width: 80,
							margin: '0 5 0 0',
							listeners: {
								render: this.rendererDragDropButton,
								scope: this
							}
						}, {
							xtype: 'button',
							text: 'WebGL',
							width: 80,
							listeners: {
								render: this.rendererDragDropButton,
								scope: this
							}
						}],
						listeners: {
							render: this.rendererDragDropContainer,
							scope: this
						}
					}]
				}]
			}, {
				xtype: 'fieldset',
				title: '视图',
				layout: 'form',
				items: [{
					xtype: 'container',
					layout: 'column',
					items: [projectionText, centerText]
				}, extentText, {
					xtype: 'container',
					layout: 'column',
					items: [resolutionsTextarea, resolutionSlider]
				}]
			}, {
				xtype: 'fieldset',
				title: '图层',
				layout: 'form',
				items: [layerTreePanel]
			}, {
				xtype: 'fieldset',
				title: '控件',
				layout: 'form',
				items: [{
					xtype: 'container',
					layout: 'column',
					items: [controlAttributionCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [controlAttributionCollapsibleCheckbox, controlAttributionCollapsedCheckbox, {
							xtype: 'container'
						}, controlAttributionLabelText, controlAttributionCollapseLabelText, controlAttributionTipLabelText]
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
						items: [controlFullScreenKeysCheckbox, {
							xtype: 'container'
						}, {
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
						items: [controlMousePositionCoordinateFormatText, controlMousePositionProjectionText, controlMousePositionUndefinedHTMLText]
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
						items: [controlRotateAutoHideCheckbox, controlRotateDurationNumber, {
							xtype: 'container'
						}, controlRotateLabelText, controlRotateTipLabelText, {
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
						items: [controlScaleLineMinWidthNumber, controlScaleLineUnitsCombobox, {
							xtype: 'container'
						}]
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
						items: [controlZoomDurationNumber, controlZoomZoomInLabelText, controlZoomZoomOutLabelText, controlZoomDeltaNumber, controlZoomZoomInTipLabelText, controlZoomZoomOutTipLabelText]
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
						items: [controlZoomSliderDurationNumber, {
							xtype: 'container'
						}, {
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
						items: [controlZoomToExtentExtentText, {
							xtype: 'container'
						}, controlZoomToExtentLabelText, controlZoomToExtentTipLabelText, {
							xtype: 'container'
						}]
					}]
				}]
			}, {
				xtype: 'fieldset',
				title: '交互',
				layout: 'form',
				items: [{
					xtype: 'container',
					layout: 'column',
					items: [interactionDoubleClickZoomCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionDoubleClickZoomDurationNumber, interactionDoubleClickZoomDeltaNumber, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionDragAndDropCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionDragAndDropProjectionText, {
							xtype: 'container'
						}, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionDragBoxCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						html: '实现特定方法'
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionDragPanCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionDragPanKineticDecayNumber, interactionDragPanKineticMinVelocityNumber, interactionDragPanKineticDelayNumber]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionDragRotateCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionDragRotateDurationNumber, {
							xtype: 'container',
							html: '实现特定方法'
						}, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionDragRotateAndZoomCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionDragRotateAndZoomDurationNumber, {
							xtype: 'container',
							html: '实现特定方法'
						}, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionDragZoomCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionDragZoomDurationNumber, {
							xtype: 'container',
							html: '实现特定方法'
						}, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionDrawCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionDrawClickToleranceNumber, interactionDrawSnapToleranceNumber, interactionDrawWrapXCheckbox, {
							xtype: 'container',
							html: '实现特定方法'
						}, {
							xtype: 'container'
						}, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionKeyboardPanCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionKeyboardPanDurationNumber, interactionKeyboardPanPixelDeltaNumber, {
							xtype: 'container',
							html: '实现特定方法'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionKeyboardZoomCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionKeyboardZoomDurationNumber, interactionKeyboardZoomDeltaNumber, {
							xtype: 'container',
							html: '实现特定方法'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionModifyCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionModifyPixelToleranceNumber, interactionModifyWrapXCheckbox, {
							xtype: 'container',
							html: '实现特定方法'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionMouseWheelZoomCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionMouseWheelZoomDurationNumber, {
							xtype: 'container'
						}, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionPinchRotateCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionPinchRotateDurationNumber, interactionPinchRotateThresholdNumber, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionPinchZoomCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionPinchZoomDurationNumber, {
							xtype: 'container'
						}, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionSelectCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionSelectMultiCheckbox, interactionSelectWrapXCheckbox, {
							xtype: 'container',
							html: '实现特定方法'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionSnapCheckbox, {
						columnWidth: 0.9,
						xtype: 'container',
						layout: {
							type: 'table',
							columns: 3
						},
						items: [interactionSnapPixelToleranceNumber, {
							xtype: 'container'
						}, {
							xtype: 'container'
						}]
					}]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [interactionTranslateCheckbox, {
						columnWidth: 0.9,
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'fieldset',
				title: '覆盖',
				layout: 'form',
				items: [{
					xtype: 'button',
					text: 'A'
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

		if (this.entityId) {
			this.getComponent('editForm').getForm().load({
				url: ctx + '/gis/thematic/input.ctrl',
				params: {
					id: this.entityId
				},
				waitMsg: '正在载入数据...',
				success: function(form, action) {
					layerTreePanel.mapId = form.findField('mapId').getValue();
					layerTreePanel.queryData();

					var renderers = form.findField('mapRenderer').getValue().split(',');
					var rendererButtons = this.down('#rendererContainer').items;
					for (var i = 0; i < rendererButtons.length; i++) {
						rendererButtons.get(i).setText(renderers[i]);
					}

					var resolutions = form.findField('viewResolutions').getValue().split(',');
					var resolution = form.findField('viewResolution').getValue();
					for (var i = 0; i < resolutions.length; i++) {
						if (Ext.String.trim(resolutions[i]) == Ext.String.trim(resolution)) {
							resolutionSlider.setValue(resolutions.length - i);
							break;
						}
					}
				},
				failure: function() {
					Ext.Msg.alert('系统提示', '无法加载信息！');
				},
				scope: this
			});
		}
	},
	rendererDragDropContainer: function(container) {
		container.dragZone = Ext.create('Ext.dd.DragZone', container.getEl(), {
			getDragData: function(e) {
				var sourceEl = e.getTarget(container.itemSelector, 10), d;
				if (sourceEl) {
					d = sourceEl.cloneNode(true);
					d.id = Ext.id();

					var button = null;
					for (var i = 0; i < container.items.length; i++) {
						if (sourceEl.id.indexOf(container.items.get(i).getId()) == 0) {
							button = container.items.get(i);
							break;
						}
					}

					return (container.dragData = {
						sourceEl: sourceEl,
						repairXY: Ext.fly(sourceEl).getXY(),
						ddel: d,
						srcButton: button
					});
				}
			},
			getRepairXY: function() {
				return this.dragData.repairXY;
			}
		});
	},
	rendererDragDropButton: function(button) {
		button.dropZone = Ext.create('Ext.dd.DropZone', button.el, {
			getTargetFromEvent: function(e) {
				return button;
			},
			onNodeDrop : function(target, dd, e, data) {
				var srcText = data.srcButton.getText();
				var text = button.getText();
				button.setText(srcText);
				data.srcButton.setText(text);
				return true;
			}
		});
	},
	saveRenderer: function() {
		var container = this.down('#rendererContainer');
		var renderers = [];
		for (var i = 0; i < container.items.length; i++) {
			renderers.push(container.items.get(i).getText());
		}
		this.getComponent('editForm').getForm().findField('mapRenderer').setValue(renderers.join(','));
	},
	saveLayouGroup: function() {
		var layerTreePanel = this.down('#layerTreePanel');
		this.getComponent('editForm').getForm().findField('layerGroup').setValue(Ext.encode(layerTreePanel.getJsonObject(layerTreePanel.getRootNode())));
	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			this.saveRenderer();
			this.saveLayouGroup();

			form.submit({
				url: ctx + '/gis/thematic/save.ctrl',
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
