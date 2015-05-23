Ext.define('Base.sys.attachment.AttachmentGridPanel', {
	extend: 'Base.ux.GridPanel',

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'attachmentGridPanel',
			url: ctx + '/sys/attachment/dataset.ctrl',
			enableAddBtn: false,
			enableEditBtn: false,
			enableColumnEditBtn: false,
			sortProperty: 'id',
			sortDirection: 'asc',
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '实体名称',
				dataIndex: 'entityName'
			}, {
				text: '实体Id',
				dataIndex: 'entityId'
			}, {
				text: '文件类型',
				dataIndex: 'contentType'
			}, {
				text: '原始文件名称',
				dataIndex: 'originalFilename'
			}, {
				text: '存储文件名称',
				dataIndex: 'randomFilename'
			}, {
				text: '文件扩展名',
				dataIndex: 'extensionName'
			}, {
				text: '文件大小',
				dataIndex: 'fileSize'
			}],
			dockedItems: [Ext.create('Ext.form.Panel', {
				itemId: 'queryForm',
				dock: 'top',
				autoHeight: true,
				bodyPadding: 5,
				layout: 'hbox',
				defaults: {
					labelAlign: 'right',
					labelWidth: 80,
					labelSeparator: '：',
					width: 250
				},
				items: [{
					xtype: 'textfield',
					fieldLabel: '实体名称',
					name: 'entityName'
				}, {
					xtype: 'textfield',
					fieldLabel: '实体Id',
					name: 'entityId'
				}, {
					xtype: 'textfield',
					fieldLabel: '文件名称',
					name: 'originalFilename'
				}, {
					xtype: 'button',
					text: '查询',
					width: 80,
					margin: '0 0 0 10',
					handler: this.queryData,
					scope: this
				}, {
					xtype: 'button',
					text: '清空',
					width: 80,
					margin: '0 0 0 10',
					handler: this.clear,
					scope: this
				}]
			})],
			listeners: {
				afterRender: function(form, eOpts){
					this.keyNav = Ext.create('Ext.util.KeyNav', this.el, {
						enter: this.queryData,
						scope: this
					});
				},
				scope: this
			}
		});
		this.callParent();

		this.on('itemdblclick', function(grid, record, item, index, e, eOpts){
			this.viewEventHandler();
		});

		this.store.on('beforeload', function(store, operation, eOpts) {
			var form = this.getComponent('queryForm').getForm();

			this.store.proxy.actionMethods = {read: 'POST'};
			Ext.apply(this.store.proxy.extraParams, {
				'qc.entityName': form.findField('entityName').getValue(),
				'qc.entityId': form.findField('entityId').getValue(),
				'qc.originalFilename': form.findField('originalFilename').getValue()
			});
		}, this);

		this.queryData();
	},
	doViewEvent: function(id) {
		Ext.create('Base.sys.attachment.AttachmentViewWindow', {entityId: id}).show();
	},
	doDeleteEvent: function(ids) {
		Ext.Ajax.request({
			url: ctx + '/sys/attachment/delete.ctrl',
			params: {
				ids: ids
			},
			success: function(response, opts) {
				Ext.Msg.alert("系统提示", "数据删除成功！");
				this.queryData();
			},
			failure: function(response, opts) {
				Ext.Msg.alert("系统提示", "数据删除失败！");
			},
			scope: this
		});
	},
	queryData: function() {
		this.getStore().load();
	},
	clear: function() {
		var form = this.getComponent('queryForm').getForm();
		form.findField('entityName').setValue('');
		form.findField('entityId').setValue('');
		form.findField('originalFilename').setValue('');
		this.queryData();
	}
});
