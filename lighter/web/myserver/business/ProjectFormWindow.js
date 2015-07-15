Ext.define('Business.ProjectFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,
	projectGridPanel: null,
	readOnlyForm: false,

	title: '项目信息',
	width: 600,
	height: 155,
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
				{name: 'customerId'},
				{name: 'customerCompanyName'},
				{name: 'name'},
				{name: 'linkMan'},
				{name: 'phone'},
				{name: 'fund'}
			]
		});

		var that = this;

		var customerPicker = Ext.create('Business.CustomerPicker', {
			fieldLabel: '<span style="color: #FF0000;">*</span>客户名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'customerCompanyName',
			allowBlank: false,
			readOnly: this.readOnlyForm,
			onDetermine: function(records) {
				var ids = [];
				var texts = [];
				for (var i = 0; i < records.length; i++) {
					ids.push(records[i].get('id'));
					texts.push(records[i].get('companyName'));
				}

				var form = that.getComponent('editForm').getForm();
				var customerId = form.findField('customerId');
				var customerCompanyName = form.findField('customerCompanyName');
				if (Ext.isEmpty(ids)) {
					customerId.setValue('');
					customerCompanyName.setValue('');
				} else {
					customerId.setValue(ids.join(','));
					customerCompanyName.setValue(texts.join(','));
				}
			}
		});
		var nameText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>项目名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'name',
			maxLength: 50,
			allowBlank: false,
			readOnly: this.readOnlyForm
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
				name: 'customerId'
			}, customerPicker, {
				xtype: 'container',
				layout: 'column',
				items: [nameText, linkManText]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [phoneText, fundText]
			}],
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
				url: ctx + '/business/project/input.ctrl',
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
	selectCustomer: function() {

	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/business/project/save.ctrl',
				waitMsg: '正在保存数据，请稍候...',
				success: function(form, action) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
					this.closeForm();
					this.projectGridPanel.queryData();
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
