Ext.define('Business.EmployeeFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,
	employeeGridPanel: null,
	readOnlyForm: false,

	title: '员工信息',
	width: 600,
	height: 400,
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
				{name: 'username'},
				{name: 'email'},
				{name: 'freeze'},
				{name: 'sort'},
				{name: 'departmentId'},
				{name: 'departmentName'}
			]
		});

		var nameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '<span style="color: #FF0000;">*</span>员工姓名',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'name',
			maxLength: 50,
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var usernameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '<span style="color: #FF0000;">*</span>用户名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'username',
			maxLength: 50,
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var passwordText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.9,
			fieldLabel: '<span style="color: #FF0000;">*</span>密码',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'password',
			value: Ext.isEmpty(this.entityId) ? '123' : '**********',
			maxLength: 50,
			allowBlank: false,
			disabled: !Ext.isEmpty(this.entityId),
			readOnly: this.readOnlyForm
		});
		var passwordCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			name: 'passwordReset',
			boxLabel: '重置',
			inputValue: '1',
			uncheckedValue: '0',
			checked: Ext.isEmpty(this.entityId),
			readOnly: Ext.isEmpty(this.entityId) ? true : this.readOnlyForm,
			listeners: {
				change: function(checkbox, newValue, oldValue, eOpts) {
					if (checkbox.getValue()) {
						passwordText.setDisabled(false);
						passwordText.setValue('');
					} else {
						passwordText.setDisabled(true);
					}
				},
				scope: this
			}
		});
		var emailText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '电子邮箱',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'email',
			maxLength: 50,
			vtype: 'email',
			readOnly: this.readOnlyForm
		});
		var areaCheckboxGroup = Ext.create('Ext.form.CheckboxGroup', {
			itemId: 'areaCheckboxGroup',
			fieldLabel: '负责区域',
			labelAlign: 'right',
			labelSeparator: '：'
		});
		var roleCheckboxGroup = Ext.create('Ext.form.CheckboxGroup', {
			itemId: 'roleCheckboxGroup',
			fieldLabel: '角色',
			labelAlign: 'right',
			labelSeparator: '：'
		});
		var freezeCheckbox = Ext.create('Ext.form.field.Checkbox', {
			fieldLabel: '冻结',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'freeze',
			inputValue: '1',
			uncheckedValue: '0',
			readOnly: this.readOnlyForm
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
			},
				nameText,
				usernameText, {
					xtype: 'container',
					layout: 'column',
					items: [passwordText, passwordCheckbox]
				},
				emailText,
				areaCheckboxGroup,
				roleCheckboxGroup,
				freezeCheckbox,
				sortNumber
			],
			buttons:[{
				xtype: 'button',
				text: '保存',
				handler: this.saveForm,
				scope:this,
				hidden: this.readOnlyForm
			}, {
				xtype: 'button',
				text: '关闭',
				handler: this.closeForm,
				scope:this
			}]
		});
		this.add(formPanel);

		if (this.readOnlyForm) {
			formPanel.getForm().getFields().each(function(item, index, len) {
				item.setReadOnly(true);
			}, this);
		}

		this.getComponent('editForm').getForm().load({
			url: ctx + '/business/employee/input.ctrl',
			params: {
				id: this.entityId
			},
			waitMsg: '正在载入数据...',
			success: function(form, action) {
				var data = Ext.decode(action.response.responseText);
				this.addCheckbox('area', data.areaIds, data.areaList);
				this.addCheckbox('role', data.roleIds, data.roleList);
			},
			failure: function() {
				Ext.Msg.alert('系统提示', '无法加载信息！');
			},
			scope: this
		});
	},
	addCheckbox: function(label, ids, list) {
		var checkboxGroup = this.down('#' + label + 'CheckboxGroup');

		for (var i = 0; i < list.length; i++) {
			var checked = false;
			for (var j = 0; j < ids.length; j++) {
				if (list[i].id == ids[j]) {
					checked = true;
					break;
				}
			}

			checkboxGroup.add(Ext.create('Ext.form.field.Checkbox', {
				xtype: 'checkboxfield',
				boxLabel: list[i].name,
				name: label + 'Ids',
				inputValue: list[i].id,
				checked: checked,
				readOnly: this.readOnlyForm
			}));
		}
	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/business/employee/save.ctrl',
				waitMsg: '正在保存数据，请稍候...',
				success: function(form, action) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
					this.closeForm();
					this.employeeGridPanel.queryData();
				},
				failure: function(form, action) {
					Ext.Msg.alert('系统提示', '无法保存数据！ 请检查用户名称是否重复。');
				},
				scope: this
			});
		}
	},
	closeForm: function() {
		this.close();
	}
});
