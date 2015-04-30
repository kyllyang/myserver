Ext.define('Base.app.menu.MenuCreateForm', {
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

		var that = this;

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
			value: Ext.isEmpty(record.get('id')) ? null : record.get('text'),
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
			fieldLabel: '功能',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'functionText',
			onDetermine: function(nodes) {
				var ids = [];
				var texts = [];
				for (var i = 0; i < nodes.length; i++) {
					ids.push(nodes[i].get('id'));
					texts.push(nodes[i].get('text'));
				}

				if (!Ext.isEmpty(ids)) {
					var form = that.getForm();
					form.findField('functionId').setValue(ids.join(','));
					form.findField('functionText').setValue(texts.join(','));
				}
			},
			listeners: {
				change: function(picker, newValue, oldValue, eOpts) {
					if (Ext.isEmpty(newValue)) {
						that.getForm().findField('functionId').setValue('');
					}
				},
				scope: this
			}
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
		var menuModuleThematicGridPanel = Ext.create('Base.app.menu.MenuModuleThematicGridPanel');

		this.add([{
			xtype: 'hidden',
			name: 'id'
		}, {
			xtype: 'hidden',
			name: 'parentId',
			value: Ext.isEmpty(record.get('id')) ? null : record.get('id')
		}, {
			xtype: 'hidden',
			name: 'functionId'
		},
			parentNameText,
			nameText,
			functionPicker,
			descriptionTextarea,
			sortNumber,
			menuModuleThematicGridPanel
		]);
	},
	saveForm: function() {
		var form = this.getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/app/menu/save.ctrl',
				waitMsg: '正在保存数据，请稍候...',
				success: function(form, action) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
					this.closeForm();

					var id = this.menuTreePanel.getSelectedRecord().get('id');
					this.menuTreePanel.loadTreeNode(Ext.isEmpty(id) ? null : id);
				},
				failure: function(form, action) {
					Ext.Msg.alert('系统提示', '无法保存数据！');
				},
				scope: this
			});
		}
	},
	closeForm: function() {
		this.ownerCt.close();
	}
});
