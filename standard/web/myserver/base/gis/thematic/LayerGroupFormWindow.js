Ext.define('Base.gis.thematic.LayerGroupFormWindow', {
	extend: 'Ext.window.Window',

	layerTreePanel: null,
	name: null,
	sort: null,

	title: '图层分组信息',
	width: 600,
	height: 130,
	border: false,
	modal: true,
	resizable: false,
	layout: 'fit',

	initComponent: function() {
		this.callParent();

		var nameText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '<span style="color: #FF0000;">*</span>名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'name',
			value: this.name,
			maxLength: 100,
			allowBlank: false
		});
		var sortNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '排序',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'sort',
			value: Ext.isEmpty(this.sort) ? 1 : this.sort,
			minValue: 1,
			allowDecimals: false
		});

		this.add(Ext.create('Ext.form.Panel', {
			itemId: 'editForm',
			frame: true,
			autoHeight: true,
			autoScroll: true,
			buttonAlign: 'center',
			layout: 'form',
			items: [nameText, sortNumber],
			buttons:[{
				xtype: 'button',
				text: '保存',
				handler: this.saveForm,
				scope: this
			}, {
				xtype: 'button',
				text: '关闭',
				handler: this.closeForm,
				scope: this
			}]
		}));
	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			var node = this.layerTreePanel.getSelectedRecord();
			if (Ext.isEmpty(this.name)) {
				node.insertChild(node.childNodes.length, {
					id: 'g_' + myServer.uuid(),
					text: form.findField('name').getValue(),
					sort: form.findField('sort').getValue(),
					leaf: false,
					children: []
				});
			} else {
				node.set('text', form.findField('name').getValue());
				node.set('sort', form.findField('sort').getValue());
			}
			this.closeForm();
		}
	},
	closeForm: function() {
		this.close();
	}
});
