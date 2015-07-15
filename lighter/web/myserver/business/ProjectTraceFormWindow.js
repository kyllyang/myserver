Ext.define('Business.ProjectTraceFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,
	projectId: null,
	projectName: null,
	projectTraceGridPanel: null,
	readOnlyForm: false,

	title: '跟踪信息',
	width: 600,
	height: 260,
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
				{name: 'projectId'},
				{name: 'projectName'},
				{name: 'linkMan'},
				{name: 'phone'},
				{name: 'fund'},
				{name: 'result'},
				{name: 'successRate'}
			]
		});

		var projectNameText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>项目名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'projectName',
			value: this.projectName,
			disabled: true
		});
		var linkManText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>项目联系人',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'linkMan',
			maxLength: 50,
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var phoneText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>联系电话',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'phone',
			maxLength: 50,
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var fundText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '资金情况',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'fund',
			readOnly: this.readOnlyForm
		});
		var resultTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '跟踪成果',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'result',
			maxLength: 255,
			readOnly: this.readOnlyForm
		});
		var successRateTextArea = Ext.create('Ext.form.field.TextArea', {
			fieldLabel: '项目成功率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'successRate',
			maxLength: 255,
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
			}, {
				xtype: 'hidden',
				name: 'projectId',
				value: this.projectId
			}, {
				xtype: 'container',
				layout: 'column',
				items: [projectNameText, linkManText]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [phoneText, fundText]
			}, resultTextArea, successRateTextArea],
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

		if (this.entityId) {
			this.getComponent('editForm').getForm().load({
				url: ctx + '/business/projecttrace/input.ctrl',
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
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/business/projecttrace/save.ctrl',
				waitMsg: '正在保存数据，请稍候...',
				success: function(form, action) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
					this.closeForm();
					this.projectTraceGridPanel.queryData();
				},
				failure: function(form, action) {
					Ext.Msg.alert('系统提示', '状态： ' + action.response.status + '&nbsp;' + action.response.statusText);
				},
				scope: this
			});
		}
	},
	closeForm: function() {
		this.close();
	}
});
