Ext.define('Base.gis.style.StyleWindow', {
	extend: 'Ext.window.Window',

	styleData: null,
	onDetermine: null,

	title: '样式信息',
	width: 400,
	height: 265,
	border: false,
	modal: true,
	resizable: false,
	layout: 'fit',

	initComponent: function() {
		this.callParent();

		var strokeColor = '';
		try {
			strokeColor = this.styleData.stroke.color;
		} catch (e) {
		}
		var strokeWidth = '1';
		try {
			strokeWidth = this.styleData.stroke.width;
		} catch (e) {
		}
		var strokeLineCap = 'round';
		try {
			strokeLineCap = this.styleData.stroke.lineCap;
		} catch (e) {
		}
		var strokeLineJoin = 'round';
		try {
			strokeLineJoin = this.styleData.stroke.lineJoin;
		} catch (e) {
		}
		var strokeLineDash = '';
		try {
			strokeLineDash = this.styleData.stroke.lineDash;
		} catch (e) {
		}
		var strokeMiterLimit = '10';
		try {
			strokeMiterLimit = this.styleData.stroke.miterLimit;
		} catch (e) {
		}
		var fillColor = '';
		try {
			fillColor = this.styleData.fill.color;
		} catch (e) {
		}

		var strokeColorPicker = Ext.create('Base.gis.style.ColorPicker', {
			columnWidth: 0.5,
			xtype: 'textfield',
			fieldLabel: '颜色',
			labelAlign: 'right',
			labelSeparator: '：',
			labelWidth: 80,
			name: 'strokeColor',
			value: strokeColor,
			onDetermine: function(color, alpha) {
				strokeColorPicker.setValue(color + ',' + alpha);
			}
		});
		var strokeWidthNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			xtype: 'numberfield',
			fieldLabel: '线宽',
			labelAlign: 'right',
			labelSeparator: '：',
			labelWidth: 80,
			name: 'strokeWidth',
			value: strokeWidth,
			minValue: 1,
			allowDecimals: false
		});
		var strokeLineCapComboBox = Ext.create('Base.ux.DictComboBox', {
			columnWidth: 0.5,
			invokeCode: 'gis_style_linecap',
			fieldLabel: '线帽',
			labelAlign: 'right',
			labelSeparator: '：',
			labelWidth: 80,
			name: 'strokeLineCap',
			value: strokeLineCap
		});
		var strokeLineJoinComboBox = Ext.create('Base.ux.DictComboBox', {
			columnWidth: 0.5,
			invokeCode: 'gis_style_linejoin',
			fieldLabel: '线段交点',
			labelAlign: 'right',
			labelSeparator: '：',
			labelWidth: 80,
			name: 'strokeLineJoin',
			value: strokeLineJoin
		});
		var strokeLineDashText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			xtype: 'textfield',
			fieldLabel: '虚线',
			labelAlign: 'right',
			labelSeparator: '：',
			labelWidth: 80,
			name: 'strokeLineDash',
			value: strokeLineDash
		});
		var strokeMiterLimitNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			xtype: 'numberfield',
			fieldLabel: '斜接限制',
			labelAlign: 'right',
			labelSeparator: '：',
			labelWidth: 80,
			name: 'strokeMiterLimit',
			value: strokeMiterLimit,
			minValue: 1,
			allowDecimals: false
		});
		var fillColorPicker = Ext.create('Base.gis.style.ColorPicker', {
			columnWidth: 0.5,
			xtype: 'textfield',
			fieldLabel: '颜色',
			labelAlign: 'right',
			labelSeparator: '：',
			labelWidth: 80,
			name: 'fillColor',
			value: fillColor,
			onDetermine: function(color, alpha) {
				fillColorPicker.setValue(color + ',' + alpha);
			}
		});

		this.add(Ext.create('Ext.form.Panel', {
			itemId: 'editForm',
			frame: true,
			autoHeight: true,
			autoScroll: true,
			buttonAlign: 'center',
			layout: 'form',
			items: [{
				xtype: 'fieldset',
				title: '线条',
				layout: 'form',
				items: [{
					xtype: 'container',
					layout: 'column',
					items: [strokeColorPicker, strokeWidthNumber]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [strokeLineCapComboBox, strokeLineJoinComboBox]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [strokeLineDashText, strokeMiterLimitNumber]
				}]
			}, {
				xtype: 'fieldset',
				title: '填充',
				layout: 'form',
				items: [{
					xtype: 'container',
					layout: 'column',
					items: [fillColorPicker]
				}]
			}],
			buttons:[{
				xtype: 'button',
				text: '保存',
				handler: this.saveForm,
				scope: this
			}, {
				xtype: 'button',
				text: '关闭',
				handler: this.closeForm,
				scope: this
			}]
		}));
	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			var style = {
				stroke: {
					color: form.findField('strokeColor').getValue(),
					lineCap: form.findField('strokeLineCap').getValue(),
					lineJoin: form.findField('strokeLineJoin').getValue(),
					lineDash: form.findField('strokeLineDash').getValue(),
					miterLimit: form.findField('strokeMiterLimit').getValue(),
					width: form.findField('strokeWidth').getValue()
				},
				fill: {
					color: form.findField('fillColor').getValue()
				}
			};
			this.onDetermine(style);

			this.closeForm();
		}
	},
	closeForm: function() {
		this.close();
	}
});
