Ext.define('Base.gis.layer.LayerFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,
	readOnlyForm: false,
	opener: null,

	title: '图层信息',
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
				{name: 'brightness'},
				{name: 'contrast'},
				{name: 'hue'},
				{name: 'opacity'},
				{name: 'saturation'},
				{name: 'visible'},
				{name: 'extent'},
				{name: 'minResolution'},
				{name: 'maxResolution'},
				{name: 'sourceClassName'},
				{name: 'layerClassName'},
				{name: 'preload'},
				{name: 'useInterimTilesOnError'},
				{name: 'updateWhileAnimating'},
				{name: 'updateWhileInteracting'},
				{name: 'renderBuffer'}
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
		var layerClassNameCombobox = Ext.create('Base.ux.DictComboBox', {
			columnWidth: 0.5,
			invokeCode: 'gis_layer_class',
			fieldLabel: '<span style="color: #FF0000;">*</span>图层类型',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'layerClassName',
			allowBlank: false,
			listeners: {
				change: function(combobox, newValue, oldValue, eOpts) {
					this.switchLayerProperties(newValue);
				},
				scope: this
			}
		});
		var extentText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '范围',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'extent',
			maxLength: 100,
			qtip: 'The bounding extent for layer rendering. The layer will not be rendered outside of this extent.'
		});
		var minResolutionText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '最小分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'minResolution',
			maxLength: 100,
			qtip: 'The minimum resolution (inclusive) at which this layer will be visible.'
		});
		var maxResolutionText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '最大分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'maxResolution',
			maxLength: 100,
			qtip: 'The maximum resolution (exclusive) below which this layer will be visible.'
		});
		var visibleCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.5,
			fieldLabel: '可见',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'visible',
			inputValue: '1',
			checked: true,
			qtip: 'Visibility. Default is true (visible).'
		});
		var opacityNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '透明度',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'opacity',
			value: 1,
			minValue: 0.1,
			maxValue: 1,
			qtip: 'Opacity (0, 1). Default is 1.'
		});
		var brightnessNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '亮度',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'brightness',
			value: 0,
			minValue: 0,
			maxValue: 100,
			allowDecimals: false,
			qtip: 'Brightness. Default is 0.'
		});
		var contrastNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '对比度',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'contrast',
			value: 1,
			minValue: 1,
			maxValue: 100,
			allowDecimals: false,
			qtip: 'Contrast. Default is 1.'
		});
		var hueNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '色度',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'hue',
			value: 0,
			minValue: 0,
			maxValue: 100,
			allowDecimals: false,
			qtip: 'Hue. Default is 0.'
		});
		var saturationNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '饱和度',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'saturation',
			value: 1,
			minValue: 1,
			maxValue: 100,
			allowDecimals: false,
			qtip: 'Saturation. Default is 1.'
		});
		var preloadNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '预加载',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'preload',
			value: 0,
			minValue: 0,
			maxValue: 100,
			allowDecimals: false,
			hidden: true,
			qtip: 'Preload. Load low-resolution tiles up to preload levels. By default preload is 0, which means no preloading.'
		});
		var useInterimTilesOnErrorCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.5,
			fieldLabel: '使用临时瓦片',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'useInterimTilesOnError',
			inputValue: '1',
			checked: true,
			hidden: true,
			qtip: 'Use interim tiles on error.'
		});
		var updateWhileAnimatingCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.5,
			fieldLabel: '动画时更新',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'updateWhileAnimating',
			inputValue: '1',
			hidden: true,
			qtip: 'When set to true, feature batches will be recreated during animations. This means that no vectors will be shown clipped, but the setting will have a performance impact for large amounts of vector data. When set to false, batches will be recreated when no animation is active.'
		});
		var updateWhileInteractingCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.5,
			fieldLabel: '交互时更新',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'updateWhileInteracting',
			inputValue: '1',
			hidden: true,
			qtip: 'When set to true, feature batches will be recreated during interactions. See also updateWhileAnimating.'
		});
		var renderBufferNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '渲染缓冲',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'renderBuffer',
			value: 100,
			minValue: 1,
			maxValue: 99999,
			allowDecimals: false,
			hidden: true,
			qtip: 'The buffer around the viewport extent used by the renderer when getting features from the vector source for the rendering or hit-detection. Recommended value: the size of the largest symbol, line width or label.'
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
				name: 'mapRenderer'
			}, {
				xtype: 'hidden',
				name: 'viewResolution'
			}, {
				xtype: 'container',
				layout: 'column',
				items: [nameText, sortNumber]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [layerClassNameCombobox, extentText]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [minResolutionText, maxResolutionText]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [visibleCheckbox, opacityNumber]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [brightnessNumber, contrastNumber]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [hueNumber, saturationNumber]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [preloadNumber, useInterimTilesOnErrorCheckbox]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [updateWhileAnimatingCheckbox, updateWhileInteractingCheckbox]
			}, renderBufferNumber],
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
				url: ctx + '/gis/layer/input.ctrl',
				params: {
					id: this.entityId
				},
				waitMsg: '正在载入数据...',
				success: function(form, action) {
					this.switchLayerProperties(form.findField('layerClassName').getValue())
				},
				failure: function() {
					Ext.Msg.alert('系统提示', '无法加载信息！');
				},
				scope: this
			});
		}
	},
	switchLayerProperties: function(className) {
		var form = this.getComponent('editForm').getForm();
		if ('ol.layer.Tile' == className) {
			form.findField('preload').setVisible(true);
			form.findField('useInterimTilesOnError').setVisible(true);

			form.findField('updateWhileAnimating').setVisible(false);
			form.findField('updateWhileInteracting').setVisible(false);
			form.findField('renderBuffer').setVisible(false);
		} else if ('ol.layer.Vector' == className) {
			form.findField('preload').setVisible(false);
			form.findField('useInterimTilesOnError').setVisible(false);

			form.findField('updateWhileAnimating').setVisible(true);
			form.findField('updateWhileInteracting').setVisible(true);
			form.findField('renderBuffer').setVisible(true);
		} else {
			form.findField('preload').setVisible(false);
			form.findField('useInterimTilesOnError').setVisible(false);

			form.findField('updateWhileAnimating').setVisible(false);
			form.findField('updateWhileInteracting').setVisible(false);
			form.findField('renderBuffer').setVisible(false);
		}
	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/gis/layer/save.ctrl',
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
