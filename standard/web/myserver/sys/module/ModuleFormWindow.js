Ext.define('Sys.module.ModuleFormWindow', {
	extend: 'Ext.window.Window',

	moduleTreePanel: null,
	moduleGridPanel: null,
	entityId: null,
	parentEntity: null,
	readOnlyForm: false,

	title: '模块信息',
	width: 600,
	height: 330,
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
				{name: 'parentId'},
				{name: 'parentName'},
				{name: 'name'},
				{name: 'type', type: 'string'},
				{name: 'funcType', type: 'string'},
				{name: 'funcCode'},
				{name: 'description'},
				{name: 'sort'}
			]
		});

		var parentNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '上级模块',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'parentName',
			value: this.parentEntity.get('text'),
			maxLength: 50,
			disabled: true
		});
		var nameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '<span style="color: #FF0000;">*</span>模块名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'name',
			maxLength: 50,
			allowBlank: false
		});
		var typeCombobox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'sys_module_type',
			fieldLabel: '<span style="color: #FF0000;">*</span>类型',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'type',
			allowBlank: false,
			listeners: {
				change: this.typeComboboxChangeEvent,
				scope: this
			}
		});
		var funcTypeCombobox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'sys_module_funcType',
			fieldLabel: '<span style="color: #FF0000;">*</span>功能类型',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'funcType',
			hidden: true,
			allowBlank: true
		});
		var funcCodeTextarea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '<span style="color: #FF0000;">*</span>功能代码',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'funcCode',
			maxLength: 20000,
			hidden: true,
			allowBlank: true
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
			allowDecimals: false
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
				name: 'parentId',
				value: this.parentEntity.get('id')
			},
				parentNameText,
				nameText,
				typeCombobox,
				funcTypeCombobox,
				funcCodeTextarea,
				descriptionTextarea,
				sortNumber
			],
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
		this.add(formPanel);

		if (this.readOnlyForm) {
			formPanel.getForm().getFields().each(function(item, index, len) {
				item.setReadOnly(true);
			}, this);
		}

		if (this.entityId) {
			this.getComponent('editForm').getForm().load({
				url: ctx + '/sys/module/input.ctrl',
				params: {
					id: this.entityId
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
	typeComboboxChangeEvent: function(combobox, newValue, oldValue, eOpts) {
		var form = this.getComponent('editForm').getForm();
		var funcTypeCombobox = form.findField('funcType');
		var funcCodeTextarea = form.findField('funcCode');
		if ('2' == newValue) {
			funcTypeCombobox.show();
			funcCodeTextarea.show();
			funcTypeCombobox.setDisabled(false);
			funcCodeTextarea.setDisabled(false);
		} else {
			funcTypeCombobox.hide();
			funcCodeTextarea.hide();
			funcTypeCombobox.setDisabled(true);
			funcCodeTextarea.setDisabled(true);
		}
	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/sys/module/save.ctrl',
				waitMsg: '正在保存数据，请稍候...',
				success: function(form, action) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
					this.closeForm();

					this.moduleGridPanel.queryData(this.parentEntity.get('id'));
					this.moduleTreePanel.loadTreeNode(this.parentEntity.get('id'));
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
