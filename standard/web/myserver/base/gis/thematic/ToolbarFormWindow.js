Ext.define('Base.gis.thematic.ToolbarFormWindow', {
	extend: 'Ext.window.Window',

	mapId: null,
	readOnlyForm: false,
	opener: null,

	title: '工具栏信息',
	width: 800,
	height: 310,
	border: false,
	modal: true,
	resizable: false,
	layout: 'fit',

	initComponent: function() {
		this.callParent();

		Ext.define('FormModel', {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'toolbarSelect'},
				{name: 'toolbarDrawPoint'},
				{name: 'toolbarDrawPointStyle'},
				{name: 'toolbarDrawLineString'},
				{name: 'toolbarDrawLineStringStyle'},
				{name: 'toolbarDrawPolygon'},
				{name: 'toolbarDrawPolygonStyle'},
				{name: 'toolbarModify'},
				{name: 'toolbarTranslate'},
				{name: 'toolbarErase'},
				{name: 'toolbarRestore'}
			]
		});

		var toolbarSelectCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '选择',
			name: 'toolbarSelect',
			inputValue: '1',
			checked: true,
			qtip: '选择一个矢量图形'
		});
		var toolbarDrawPointCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '绘制点',
			name: 'toolbarDrawPoint',
			inputValue: '1',
			checked: true,
			qtip: '绘制点'
		});
		var toolbarDrawPointStyleTrigger = Ext.create('Ext.form.field.Trigger', {
			fieldLabel: '样式',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'toolbarDrawPointStyle',
			onTriggerClick: function() {
				Ext.create('Base.gis.style.StyleWindow', {
					styleData: Ext.isEmpty(toolbarDrawPointStyleTrigger.getValue()) ? null : Ext.decode(toolbarDrawPointStyleTrigger.getValue()),
					onDetermine: function(style) {
						toolbarDrawPointStyleTrigger.setValue(Ext.encode(style));
					}
				}).show();
			},
			qtip: '点样式'
		});
		var toolbarDrawLineStringCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '绘制线',
			name: 'toolbarDrawLineString',
			inputValue: '1',
			checked: true,
			qtip: '绘制线'
		});
		var toolbarDrawLineStringStyleTrigger = Ext.create('Ext.form.field.Trigger', {
			fieldLabel: '样式',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'toolbarDrawLineStringStyle',
			onTriggerClick: function() {
				Ext.create('Base.gis.style.StyleWindow', {
					styleData: Ext.isEmpty(toolbarDrawLineStringStyleTrigger.getValue()) ? null : Ext.decode(toolbarDrawLineStringStyleTrigger.getValue()),
					onDetermine: function(style) {
						toolbarDrawLineStringStyleTrigger.setValue(Ext.encode(style));
					}
				}).show();
			},
			qtip: '线样式'
		});
		var toolbarDrawPolygonCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '绘制面',
			name: 'toolbarDrawPolygon',
			inputValue: '1',
			checked: true,
			qtip: '绘制面'
		});
		var toolbarDrawPolygonStyleTrigger = Ext.create('Ext.form.field.Trigger', {
			fieldLabel: '样式',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'toolbarDrawPolygonStyle',
			onTriggerClick: function() {
				Ext.create('Base.gis.style.StyleWindow', {
					styleData: Ext.isEmpty(toolbarDrawPolygonStyleTrigger.getValue()) ? null : Ext.decode(toolbarDrawPolygonStyleTrigger.getValue()),
					onDetermine: function(style) {
						toolbarDrawPolygonStyleTrigger.setValue(Ext.encode(style));
					}
				}).show();
			},
			qtip: '面样式'
		});
		var toolbarModifyCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '修改',
			name: 'toolbarModify',
			inputValue: '1',
			checked: true,
			qtip: '修改矢量数据'
		});
		var toolbarTranslateCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '移动',
			name: 'toolbarTranslate',
			inputValue: '1',
			checked: true,
			qtip: '移动矢量数据'
		});
		var toolbarEraseCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '擦除',
			name: 'toolbarErase',
			inputValue: '1',
			checked: true,
			qtip: '清楚默认矢量图层上的所有数据'
		});
		var toolbarRestoreCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.1,
			boxLabel: '恢复',
			name: 'toolbarRestore',
			inputValue: '1',
			checked: true,
			qtip: '恢复操作'
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
				name: 'mapId',
				value: this.mapId
			}, {
				xtype: 'container',
				layout: 'column',
				items: [toolbarSelectCheckbox, {
					columnWidth: 0.9,
					xtype: 'container'
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [toolbarDrawPointCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [toolbarDrawPointStyleTrigger, {
						xtype: 'container'
					}, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [toolbarDrawLineStringCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [toolbarDrawLineStringStyleTrigger, {
						xtype: 'container'
					}, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [toolbarDrawPolygonCheckbox, {
					columnWidth: 0.9,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [toolbarDrawPolygonStyleTrigger, {
						xtype: 'container'
					}, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [toolbarModifyCheckbox, {
					columnWidth: 0.9,
					xtype: 'container'
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [toolbarTranslateCheckbox, {
					columnWidth: 0.9,
					xtype: 'container'
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [toolbarEraseCheckbox, {
					columnWidth: 0.9,
					xtype: 'container'
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [toolbarRestoreCheckbox, {
					columnWidth: 0.9,
					xtype: 'container'
				}]
			}],
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
			}],
			plugins: [Ext.create('Base.ux.HelpQtip')]
		});
		this.add(formPanel);

		if (this.readOnlyForm) {
			formPanel.getForm().getFields().each(function(item, index, len) {
				item.setReadOnly(true);
			}, this);
		}

		if (this.mapId) {
			this.getComponent('editForm').getForm().load({
				url: ctx + '/gis/toolbar/input.ctrl',
				params: {
					mapId: this.mapId
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
				url: ctx + '/gis/toolbar/save.ctrl',
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
		}
	},
	closeForm: function() {
		this.close();
	}
});
