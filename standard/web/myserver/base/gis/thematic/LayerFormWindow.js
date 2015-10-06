Ext.define('Base.gis.thematic.LayerFormWindow', {
	extend: 'Ext.window.Window',

	mapId: null,
	readOnlyForm: false,
	opener: null,

	title: '图层信息',
	width: 600,
	height: 400,
	border: false,
	modal: true,
	resizable: false,
	buttonAlign: 'center',
	layout: 'fit',

	initComponent: function() {
		Ext.apply(this, {
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

		var layerTreePanel = Ext.create('Base.gis.thematic.LayerTreePanel');
		this.add(layerTreePanel);

		if (this.mapId) {
			layerTreePanel.mapId = this.mapId;
			layerTreePanel.queryData();
		}
	},
	saveLayouGroup: function() {
		var layerTreePanel = this.down('#layerTreePanel');
		return Ext.encode(layerTreePanel.getJsonObject(layerTreePanel.getRootNode()));
	},
	saveForm: function() {
		Ext.create('Ext.form.Panel', {
			items: [{
				xtype: 'hidden',
				name: 'mapId',
				value: this.mapId
			}, {
				xtype: 'hidden',
				name: 'layerGroup',
				value: this.saveLayouGroup()
			}]
		}).getForm().submit({
			url: ctx + '/gis/layergroup/save.ctrl',
			waitMsg: '正在保存数据，请稍候...',
			success: function(form, action) {
				Ext.Msg.alert('系统提示', '数据保存成功！');
				this.closeForm();

				this.opener.queryData();
			},
			failure: function(form, action) {
				Ext.Msg.alert('系统提示', '无法保存数据！');
			},
			scope: this
		});
	},
	closeForm: function() {
		this.close();
	}
});
