Ext.define('Business.ExpenseFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,
	expenseGridPanel: null,
	readOnlyForm: false,

	title: '费用信息',
	width: 600,
	height: 235,
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
				{name: 'areaId'},
				{name: 'areaName'},
				{name: 'customerId'},
				{name: 'customerCompanyName'},
				{name: 'projectId'},
				{name: 'projectName'},
				{name: 'startDate'},
				{name: 'endDate'},
				{name: 'carExpense'},
				{name: 'cityTrafficExpense'},
				{name: 'subsidyExpense'},
				{name: 'otherExpense'}
			]
		});

		var that = this;

		var areaCombobox = Ext.create('Business.AreaComboBox', {
			fieldLabel: '<span style="color: #FF0000;">*</span>区域',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'areaId',
			allowBlank: false
		});
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
		var projectPicker = Ext.create('Business.ProjectPicker', {
			fieldLabel: '<span style="color: #FF0000;">*</span>项目名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'projectName',
			allowBlank: false,
			readOnly: this.readOnlyForm,
			onDetermine: function(records) {
				var ids = [];
				var texts = [];
				for (var i = 0; i < records.length; i++) {
					ids.push(records[i].get('id'));
					texts.push(records[i].get('name'));
				}

				var form = that.getComponent('editForm').getForm();
				var projectId = form.findField('projectId');
				var projectName = form.findField('projectName');
				if (Ext.isEmpty(ids)) {
					projectId.setValue('');
					projectName.setValue('');
				} else {
					projectId.setValue(ids.join(','));
					projectName.setValue(texts.join(','));
				}
			}
		});
		var startDateDate = Ext.create('Ext.form.field.Date', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>起始日期',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'startDate',
			format: 'Y-m-d',
			value: new Date(),
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var endDateDate = Ext.create('Ext.form.field.Date', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>结束日期',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'endDate',
			format: 'Y-m-d',
			value: new Date(),
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var carExpenseNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>车费',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'carExpense',
			minValue: 0,
			maxValue: 999999999,
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var cityTrafficExpenseNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>市内交通费',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'cityTrafficExpense',
			minValue: 0,
			maxValue: 999999999,
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var subsidyExpenseNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>单位补助',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'subsidyExpense',
			minValue: 0,
			maxValue: 999999999,
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var otherExpenseNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>其他费用',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'otherExpense',
			minValue: 0,
			maxValue: 999999999,
			allowBlank: false,
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
			}, {
				xtype: 'hidden',
				name: 'projectId'
			}, areaCombobox, customerPicker, projectPicker, {
				xtype: 'container',
				layout: 'column',
				items: [startDateDate, endDateDate]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [carExpenseNumber, cityTrafficExpenseNumber]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [subsidyExpenseNumber, otherExpenseNumber]
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
				url: ctx + '/business/expense/input.ctrl',
				params: {
					id: this.entityId
				},
				waitMsg: '正在载入数据...',
				success: function(form, action) {
					form.findField('startDate').setValue(Ext.Date.parse(Ext.decode(action.response.responseText).startDate, 'Y-m-d H:i:s.u'));
					form.findField('endDate').setValue(Ext.Date.parse(Ext.decode(action.response.responseText).endDate, 'Y-m-d H:i:s.u'));
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
				url: ctx + '/business/expense/save.ctrl',
				waitMsg: '正在保存数据，请稍候...',
				success: function(form, action) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
					this.closeForm();
					this.expenseGridPanel.queryData();
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
