Ext.define('Base.app.menu.MenuForm', {
	extend: 'Ext.form.Panel',

	menuTreePanel: null,

	itemId: 'editForm',
	frame: true,
	autoHeight: true,
	autoScroll: true,
	layout: 'form',
	buttonAlign: 'center',

	initComponent: function() {
		Ext.define('FormModel', {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'id'},
				{name: 'parentId'},
				{name: 'parentName'},
				{name: 'name'},
				{name: 'description'},
				{name: 'sort'},
				{name: 'functionId'},
				{name: 'functionName'}
			]
		});

		Ext.apply(this, {
			reader: Ext.create('Ext.data.JsonReader', {
				model: 'FormModel'
			}),
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
			}]
		});
		this.callParent();

		var record = this.menuTreePanel.getSelectedRecord();

		var parentNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '上级菜单',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'parentName',
			value: Ext.isEmpty(record.get('id')) ? null : record.get('name'),
			disabled: true
		});
		var nameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '<span style="color: #FF0000;">*</span>名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'name',
			maxLength: 100,
			allowBlank: false
		});
		var functionPicker = Ext.create('Base.ux.FunctionPicker', {
			fieldLabel: '<span style="color: #FF0000;">*</span>功能',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'functionId',
			allowBlank: false
		});
		var descriptionTextarea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '描述',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'description',
			maxLength: 100
		});
		var sortNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '排序',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'sort',
			value: 1,
			minValue: 1,
			allowDecimals: false,
			readOnly: this.readOnlyForm
		});

		this.add([{
			xtype: 'hidden',
			name: 'id'
		}, {
			xtype: 'hidden',
			name: 'parentId',
			value: Ext.isEmpty(record.get('id')) ? null : record.get('id')
		},
			parentNameText,
			nameText,
			functionPicker,
			descriptionTextarea,
			sortNumber
		]);
	}
});
