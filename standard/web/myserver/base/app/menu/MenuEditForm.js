Ext.define('Base.app.menu.MenuEditForm', {
	extend: 'Ext.form.Panel',

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
				itemId: 'saveButton',
				text: '保存',
				disabled: true,
				handler: this.saveForm,
				scope: this,
				hidden: this.readOnlyForm
			}]
		});
		this.callParent();

		var parentNameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '上级菜单',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'parentName',
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
			name: 'functionName',
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
					form.findField('functionName').setValue(texts.join(','));
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

		this.add([{
			xtype: 'hidden',
			name: 'id'
		}, {
			xtype: 'hidden',
			name: 'parentId'
		}, {
			xtype: 'hidden',
			name: 'functionId'
		},
			parentNameText,
			nameText,
			functionPicker,
			descriptionTextarea,
			sortNumber
		]);
	},
	loadForm: function() {
		var record = this.ownerCt.getComponent('menuTreePanel').getSelectedRecord();
		if (Ext.isEmpty(record)) {
			this.resetForm();
		} else {
			var id = record.get('id');
			if (Ext.isEmpty(id)) {
				this.resetForm();
			} else {
				this.getForm().load({
					url: ctx + '/app/menu/input.ctrl',
					params: {
						id: id
					},
					waitMsg: '正在载入数据...',
					success: function(form, action) {
						this.down('#saveButton').setDisabled(false);
					},
					failure: function() {
						Ext.Msg.alert('系统提示', '无法加载信息！');
					},
					scope: this
				});
			}
		}
	},
	resetForm: function() {
		var form = this.getForm();
		form.findField('id').setValue('');
		form.findField('parentId').setValue('');
		form.findField('functionId').setValue('');
		form.findField('parentName').setValue('');
		form.findField('functionName').setValue('');
		form.findField('name').setValue('');
		form.findField('description').setValue('');
		form.findField('sort').setValue(1);
		this.down('#saveButton').setDisabled(true);
	},
	saveForm: function() {
		var form = this.getForm();
		if (form.isValid()) {
			form.submit({
				url: ctx + '/app/menu/save.ctrl',
				waitMsg: '正在保存数据，请稍候...',
				success: function(form, action) {
					Ext.Msg.alert('系统提示', '数据保存成功！');

					var record = this.ownerCt.getComponent('menuTreePanel').getSelectedRecord();
					this.ownerCt.getComponent('menuTreePanel').loadTreeNode(record.parentNode && record.parentNode.get('id') ? record.parentNode.get('id') : null);
				},
				failure: function(form, action) {
					Ext.Msg.alert('系统提示', '无法保存数据！');
				},
				scope: this
			});
		}
	}
});
