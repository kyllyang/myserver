Ext.define('Business.ProductFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,
	productGridPanel: null,
	readOnlyForm: false,

	title: '产品信息',
	width: 600,
	height: 290,
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
				{name: 'vendorId'},
				{name: 'vendorName'},
				{name: 'customerId'},
				{name: 'customerCompanyName'},
				{name: 'projectId'},
				{name: 'projectName'},
				{name: 'name'},
				{name: 'model'},
				{name: 'unit'},
				{name: 'amount'},
				{name: 'inTaxPrice'},
				{name: 'inTaxTotal'},
				{name: 'outTaxPrice'},
				{name: 'outTaxTotal'},
				{name: 'grossMargin'}
			]
		});

		var that = this;

		var vendorCombobox = Ext.create('Business.VendorComboBox', {
			fieldLabel: '<span style="color: #FF0000;">*</span>厂商',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'vendorId',
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
		var nameText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>产品名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'name',
			maxLength: 50,
			allowBlank: false,
			readOnly: this.readOnlyForm
		});
		var modelText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '型号',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'model',
			maxLength: 50,
			readOnly: this.readOnlyForm
		});
		var unitText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '单位',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'unit',
			maxLength: 50,
			readOnly: this.readOnlyForm
		});
		var amountNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '数量',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'amount',
			minValue: 0,
			maxValue: 999999999,
			readOnly: this.readOnlyForm
		});
		var inTaxPriceNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '进货含税单价',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'inTaxPrice',
			minValue: 0,
			maxValue: 999999999,
			readOnly: this.readOnlyForm
		});
		var inTaxTotalNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '进货含税合计',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'inTaxTotal',
			minValue: 0,
			maxValue: 999999999,
			readOnly: this.readOnlyForm
		});
		var outTaxPriceNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '出货含税单价',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'outTaxPrice',
			minValue: 0,
			maxValue: 999999999,
			readOnly: this.readOnlyForm
		});
		var outTaxTotalNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '出货含税合计',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'outTaxTotal',
			minValue: 0,
			maxValue: 999999999,
			readOnly: this.readOnlyForm
		});
		var grossMarginNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '毛利',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'grossMargin',
			minValue: 0,
			maxValue: 999999999,
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
			}, vendorCombobox, customerPicker, projectPicker, {
				xtype: 'container',
				layout: 'column',
				items: [nameText, modelText]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [unitText, amountNumber]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [inTaxPriceNumber, inTaxTotalNumber]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [outTaxPriceNumber, outTaxTotalNumber]
			}, grossMarginNumber],
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
				url: ctx + '/business/product/input.ctrl',
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
				url: ctx + '/business/product/save.ctrl',
				waitMsg: '正在保存数据，请稍候...',
				success: function(form, action) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
					this.closeForm();
					this.productGridPanel.queryData();
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
