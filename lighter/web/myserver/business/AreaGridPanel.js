Ext.define('Business.AreaGridPanel', {
	extend: 'Base.ux.GridPanel',

	border: false,

	initComponent: function() {
		Ext.apply(this, {
			itemId: 'areaGridPanel',
			url: ctx + '/business/area/dataset.ctrl',
			sortProperty: 'id',
			sortDirection: 'asc',
			enableEditBtn: false,
			enableViewBtn: false,
			enableColumnEditBtn: false,
			enableColumnViewBtn: false,
			enableColumnDelBtn: false,
			idProperty: 'id',
			columns: [{
				dataIndex: 'id',
				hidden: true
			}, {
				text: '名称',
				dataIndex: 'name',
				field: {
					maxLength: 100,
					allowBlank: false
				},
				flex: 1
			}],
			tbar: [{
				text: '保存',
				icon: ctx + '/resource/image/icon/save.png',
				handler: this.saveGrid,
				scope: this
			}],
			listeners: {
				afterRender: function(form, eOpts) {
					this.keyNav = Ext.create('Ext.util.KeyNav', this.el, {
						enter: function() {
							this.queryData();
						},
						scope: this
					});
				},
				scope: this
			},
			plugins: [Ext.create('Ext.grid.plugin.CellEditing', {
				pluginId: 'cellEditingPlugin',
				clicksToEdit: 1
			})]
		});
		this.callParent();
	},
	doAddEvent: function() {
		var record = Ext.ModelManager.create({
			name: ''
		}, Ext.getClassName(this.getStore().model));
		this.getStore().insert(0, record);
		this.getPlugin('cellEditingPlugin').startEditByPosition({row: 0, column: 0});
	},
	doDeleteEvent: function(ids) {
		var validIds = [];
		for (var i = 0; i < ids.length; i++) {
			if (ids[i]) {
				validIds.push(ids[i]);
			}
		}

		Ext.Ajax.request({
			url: ctx + '/business/area/delete.ctrl',
			params: {
				ids: validIds
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
	saveGrid: function() {
		var records = this.getStore().getModifiedRecords();
		var datas = [];
		for (var i = 0; i < records.length; i++) {
			datas.push({
				id: records[i].get('id'),
				name: records[i].get('name')
			});
		}

		if(datas.length > 0) {
			Ext.Ajax.request({
				url: ctx + '/business/area/save.ctrl',
				params: {
					areaJson: Ext.encode(datas)
				},
				success: function(response, opts) {
					Ext.Msg.alert('系统提示', '数据保存成功！');
					this.queryData();
				},
				failure: function(response, opts) {
					Ext.Msg.alert('系统提示', '无法保存数据！');
				},
				scope: this
			});
		}
	},
	queryData: function() {
		this.getStore().load();
	}
});
