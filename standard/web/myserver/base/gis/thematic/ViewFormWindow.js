Ext.define('Base.gis.thematic.ViewFormWindow', {
	extend: 'Ext.window.Window',

	mapId: null,
	readOnlyForm: false,
	opener: null,

	title: '视图信息',
	width: 800,
	height: 420,
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
				{name: 'projection'},
				{name: 'center'},
				{name: 'extent'},
				{name: 'maxResolution'},
				{name: 'minResolution'},
				{name: 'resolutions'},
				{name: 'resolution'},
				{name: 'maxZoom'},
				{name: 'minZoom'},
				{name: 'zoomFactor'},
				{name: 'zoom'},
				{name: 'enableRotation'},
				{name: 'constrainRotation'},
				{name: 'rotation'}
			]
		});

		var projectionText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'projection',
			value: 'EPSG:3857',
			maxLength: 100,
			qtip: 'The projection. Default is EPSG:3857 (Spherical Mercator).'
		});
		var centerText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '中心点',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'center',
			maxLength: 100,
			qtip: 'The initial center for the view. The coordinate system for the center is specified with the projection option. Default is undefined, and layer sources will not be fetched if this is not set.'
		});
		var extentText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '范围',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'extent',
			maxLength: 100,
			qtip: 'The extent that constrains the center, in other words, center cannot be set outside this extent. Default is undefined.'
		});
		var maxResolutionNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '最大分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'maxResolution',
			qtip: 'The maximum resolution used to determine the resolution constraint. It is used together with minResolution (or maxZoom) and zoomFactor. If unspecified it is calculated in such a way that the projection\'s validity extent fits in a 256x256 px tile. If the projection is Spherical Mercator (the default) then maxResolution defaults to 40075016.68557849 / 256 = 156543.03392804097.'
		});
		var minResolutionNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '最小分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'minResolution',
			qtip: 'The minimum resolution used to determine the resolution constraint. It is used together with maxResolution (or minZoom) and zoomFactor. If unspecified it is calculated assuming 29 zoom levels (with a factor of 2). If the projection is Spherical Mercator (the default) then minResolution defaults to 40075016.68557849 / 256 / Math.pow(2, 28) = 0.0005831682455839253.'
		});
		var resolutionsTextarea = Ext.create('Ext.form.field.TextArea', {
			columnWidth: 0.5,
			fieldLabel: '分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'resolutions',
			rows: 10,
			maxLength: 255,
			listeners: {
				change: function(textarea, newValue, oldValue, eOpts) {
					var resolution = this.getComponent('editForm').getForm().findField('resolution');
					if (Ext.isEmpty(newValue)) {
						resolution.setValue('');
					} else {
						var strs = newValue.split(',');
						resolutionSlider.setMaxValue(strs.length > 0 ? strs.length : 1);
						resolution.setValue(strs[strs.length - 1]);
					}
				},
				scope: this
			},
			qtip: 'Resolutions to determine the resolution constraint. If set the maxResolution, minResolution, minZoom, maxZoom, and zoomFactor options are ignored.'
		});
		var resolutionSlider = Ext.create('Ext.slider.Single', {
			columnWidth: 0.5,
			fieldLabel: '默认分辨率',
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
					that.getComponent('editForm').getForm().findField('resolution').setValue(strs[strs.length - thumb.value]);
					return strs[strs.length - thumb.value];
				}
			},
			qtip: 'The initial resolution for the view. The units are projection units per pixel (e.g. meters per pixel). An alternative to setting this is to set zoom. Default is undefined, and layer sources will not be fetched if neither this nor zoom are defined.'
		});
		var maxZoomNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '最大缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'maxZoom',
			value: 28,
			minValue: 0,
			allowDecimals: false,
			qtip: 'The maximum zoom level used to determine the resolution constraint. It is used together with minZoom (or maxResolution) and zoomFactor. Default is 28. Note that if minResolution is also provided, it is given precedence over maxZoom.'
		});
		var minZoomNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '最小缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'minZoom',
			value: 0,
			minValue: 0,
			allowDecimals: false,
			qtip: 'The minimum zoom level used to determine the resolution constraint. It is used together with maxZoom (or minResolution) and zoomFactor. Default is 0. Note that if maxResolution is also provided, it is given precedence over minZoom.'
		});
		var zoomFactorNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '缩放系数',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoomFactor',
			value: 2,
			minValue: 0,
			allowDecimals: false,
			qtip: 'The zoom factor used to determine the resolution constraint. Default is 2.'
		});
		var zoomNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '默认缩放',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'zoom',
			minValue: 0,
			allowDecimals: false,
			qtip: 'Only used if resolution is not defined. Zoom level used to calculate the initial resolution for the view. The initial resolution is determined using the ol.View#constrainResolution method.'
		});
		var enableRotationCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.5,
			fieldLabel: '开启旋转',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'rnableRotation',
			inputValue: '1',
			checked: true,
			qtip: 'Enable rotation. Default is true. If false a rotation constraint that always sets the rotation to zero is used. The constrainRotation option has no effect if enableRotation is false.'
		});
		var constrainRotationNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '旋转约束',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'constrainRotation',
			value: 0,
			minValue: 0,
			allowDecimals: false,
			qtip: 'Rotation constraint. false means no constraint. true means no constraint, but snap to zero near zero. A number constrains the rotation to that number of values. For example, 4 will constrain the rotation to 0, 90, 180, and 270 degrees. The default is true.'
		});
		var rotationNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '默认旋转',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'rotation',
			value: 0,
			qtip: 'The initial rotation for the view in radians (positive rotation clockwise). Default is 0.'
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
				name: 'id'
			}, {
				xtype: 'hidden',
				name: 'mapId'
			}, {
				xtype: 'hidden',
				name: 'resolution'
			}, {
				xtype: 'container',
				layout: 'column',
				items: [projectionText, centerText]
			}, extentText, {
				xtype: 'container',
				layout: 'column',
				items: [maxResolutionNumber, minResolutionNumber]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [resolutionsTextarea, resolutionSlider]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [maxZoomNumber, minZoomNumber]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [zoomFactorNumber, zoomNumber]
			}, enableRotationCheckbox, {
				xtype: 'container',
				layout: 'column',
				items: [constrainRotationNumber, rotationNumber]
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
				url: ctx + '/gis/view/input.ctrl',
				params: {
					mapId: this.mapId
				},
				waitMsg: '正在载入数据...',
				success: function(form, action) {
					var resolutions = form.findField('resolutions').getValue().split(',');
					var resolution = form.findField('resolution').getValue();
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
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/gis/view/save.ctrl',
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
