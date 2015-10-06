Ext.define('Base.gis.style.ColorPicker', {
	extend: 'Ext.form.field.Picker',

	onDetermine: null,
	editable: false,
	matchFieldWidth: false,

	initComponent: function() {
		this.callParent();
	},
	createPicker: function() {
		var value = this.getValue();
		var color = Ext.isEmpty(value) ? null : value.split(',')[0];
		var alpha = Ext.isEmpty(value) ? '1' : value.split(',')[1];

		var rgbColor = Ext.create('Ext.picker.Color', {
			value: color,
			listeners: {
				select: function(picker, color, eOpts) {
					colorText.setValue(color);
				},
				scope: this
			}
		});
		var colorText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			xtype: 'textfield',
			fieldLabel: 'RGB值',
			labelAlign: 'left',
			labelSeparator: '：',
			labelWidth: 50,
			name: 'color',
			value: color
		});
		var alphaNumber = Ext.create('Ext.form.field.Number', {
			xtype: 'numberfield',
			fieldLabel: '透明度',
			labelAlign: 'left',
			labelSeparator: '：',
			labelWidth: 50,
			name: 'alpha',
			value:  Ext.isEmpty(value) ? '1' : value.split(',')[1],
			minValue: 0,
			maxValue: 1,
			step: 0.1
		});
		return Ext.create('Ext.form.Panel', {
			floating: true,
			border: false,
			frame: true,
			autoHeight: true,
			autoScroll: true,
			width: 155,
			layout: 'form',
			buttonAlign: 'center',
			items: [rgbColor, colorText, alphaNumber],
			buttons:[{
				xtype: 'button',
				text: '确定',
				handler: function() {
					var formPanel = this.getPicker();

					this.onDetermine(colorText.getValue(), alphaNumber.getValue());

					formPanel.hide();
				},
				scope: this
			}]
		});
	}
});
