Ext.define('Base.gis.thematic.ThematicFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,
	readOnlyForm: false,
	opener: null,

	title: '专题信息',
	width: 800,
	height: 600,
	border: false,
	modal: true,
	resizable: false,
	layout: 'fit',

	initComponent: function() {
		this.callParent();

		var that = this;

		Ext.define('FormModel', {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'id'},
				{name: 'name'},
				{name: 'sort'},
				{name: 'mapId'},
				{name: 'mapLogo'},
				{name: 'mapLoadTilesWhileAnimating'},
				{name: 'mapLoadTilesWhileInteracting'},
				{name: 'mapRenderer'},
				{name: 'viewId'},
				{name: 'viewProjection'},
				{name: 'viewCenter'},
				{name: 'viewExtent'},
				{name: 'viewResolutions'},
				{name: 'viewResolution'},
				{name: 'layerGroup'}
			]
		});

		var nameText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>名称',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'name',
			maxLength: 100,
			allowBlank: false
		});
		var sortNumber = Ext.create('Ext.form.field.Number', {
			columnWidth: 0.5,
			fieldLabel: '排序',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'sort',
			value: 1,
			minValue: 1,
			allowDecimals: false
		});
	//	var mapLogoFile = Ext.create('Ext.form.field.File', {
		var mapLogoFile = Ext.create('Ext.form.field.Text', {
			fieldLabel: '徽标',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mapLogo',
			buttonText: '浏览...',
			qtip: 'The map logo. A logo to be displayed on the map at all times.'
		});
		var mapLoadTilesWhileAnimatingCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.5,
			fieldLabel: '动画加载瓦片',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mapLoadTilesWhileAnimating',
			inputValue: '1',
			qtip: 'When set to true, tiles will be loaded during animations. This may improve the user experience, but can also make animations stutter on devices with slow memory.'
		});
		var mapLoadTilesWhileInteractingCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.5,
			fieldLabel: '交互加载瓦片',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'mapLoadTilesWhileInteracting',
			inputValue: '1',
			qtip: 'When set to true, tiles will be loaded while interacting with the map. This may improve the user experience, but can also make map panning and zooming choppy on devices with slow memory.'
		});
		var rendererDisplay = Ext.create('Ext.form.field.Display', {
			columnWidth: 0.13,
			xtype: 'displayfield',
			fieldLabel: '渲染机制顺序',
			labelAlign: 'right',
			labelSeparator: '：',
			qtip: 'By default, Canvas, DOM and WebGL renderers are tested for support in that order, and the first supported used. Note that at present only the Canvas renderer supports vector data.'
		});
		var projectionText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>投影',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'viewProjection',
			value: 'EPSG:3857',
			maxLength: 100,
			allowBlank: false,
			qtip: 'The projection. Default is EPSG:3857 (Spherical Mercator).'
		});
		var centerText = Ext.create('Ext.form.field.Text', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>中心点',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'viewCenter',
			maxLength: 100,
			allowBlank: false,
			qtip: 'The initial center for the view. The coordinate system for the center is specified with the projection option. Default is undefined, and layer sources will not be fetched if this is not set.'
		});
		var extentText = Ext.create('Ext.form.field.Text', {
			fieldLabel: '<span style="color: #FF0000;">*</span>范围',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'viewExtent',
			maxLength: 100,
			allowBlank: false,
			qtip: 'The extent that constrains the center, in other words, center cannot be set outside this extent. Default is undefined.'
		});
		var resolutionsTextarea = Ext.create('Ext.form.field.TextArea', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			name: 'viewResolutions',
			rows: 10,
			maxLength: 255,
			allowBlank: false,
			listeners: {
				change: function(textarea, newValue, oldValue, eOpts) {
					if (!Ext.isEmpty(newValue)) {
						var strs = newValue.split(',');
						resolutionSlider.setMaxValue(strs.length > 0 ? strs.length : 1);
						this.getComponent('editForm').getForm().findField('viewResolution').setValue(strs[strs.length - 1]);
					}
				},
				scope: this
			},
			qtip: 'Resolutions to determine the resolution constraint. If set the maxResolution, minResolution, minZoom, maxZoom, and zoomFactor options are ignored.'
		});
		var resolutionSlider = Ext.create('Ext.slider.Single', {
			columnWidth: 0.5,
			fieldLabel: '<span style="color: #FF0000;">*</span>默认分辨率',
			labelAlign: 'right',
			labelSeparator: '：',
			vertical: true,
			height: 150,
			increment: 1,
			minValue: 1,
			maxValue: 10,
			tipText: function(thumb) {
				var str = resolutionsTextarea.getValue();
				if (!Ext.isEmpty(str)) {
					var strs = str.split(',');
					that.getComponent('editForm').getForm().findField('viewResolution').setValue(strs[strs.length - thumb.value]);
					return strs[strs.length - thumb.value];
				}
			},
			qtip: 'The initial resolution for the view. The units are projection units per pixel (e.g. meters per pixel). An alternative to setting this is to set zoom. Default is undefined, and layer sources will not be fetched if neither this nor zoom are defined.'
		});
		var layerTreePanel = Ext.create('Base.gis.thematic.LayerTreePanel');

		var formPanel = Ext.create('Ext.form.Panel', {
			itemId: 'editForm',
			frame: true,
			autoHeight: true,
			autoScroll: true,
			buttonAlign: 'center',
			reader: Ext.create('Ext.data.JsonReader', {
				model: 'FormModel'
			}),
			bodyStyle : 'overflow-x:visible; overflow-y:scroll',
			layout: 'form',
			items: [{
				xtype: 'hidden',
				name: 'id'
			}, {
				xtype: 'hidden',
				name: 'mapRenderer'
			}, {
				xtype: 'hidden',
				name: 'viewResolution'
			}, {
				xtype: 'hidden',
				name: 'mapId'
			}, {
				xtype: 'hidden',
				name: 'viewId'
			}, {
				xtype: 'hidden',
				name: 'layerGroup'
			}, {
				xtype: 'container',
				layout: 'column',
				items: [nameText, sortNumber]
			}, {
				xtype: 'fieldset',
				title: '地图',
				layout: 'form',
				items: [mapLogoFile, {
					xtype: 'container',
					layout: 'column',
					items: [mapLoadTilesWhileAnimatingCheckbox, mapLoadTilesWhileInteractingCheckbox]
				}, {
					xtype: 'container',
					layout: 'column',
					items: [rendererDisplay, {
						xtype: 'container',
						itemId: 'rendererContainer',
						layout: 'hbox',
						items: [{
							xtype: 'button',
							text: 'Canvas',
							width: 80,
							margin: '0 5 0 0',
							listeners: {
								render: this.rendererDragDropButton,
								scope: this
							}
						}, {
							xtype: 'button',
							text: 'DOM',
							width: 80,
							margin: '0 5 0 0',
							listeners: {
								render: this.rendererDragDropButton,
								scope: this
							}
						}, {
							xtype: 'button',
							text: 'WebGL',
							width: 80,
							listeners: {
								render: this.rendererDragDropButton,
								scope: this
							}
						}],
						listeners: {
							render: this.rendererDragDropContainer,
							scope: this
						}
					}]
				}]
			}, {
				xtype: 'fieldset',
				title: '视图',
				layout: 'form',
				items: [{
					xtype: 'container',
					layout: 'column',
					items: [projectionText, centerText]
				}, extentText, {
					xtype: 'container',
					layout: 'column',
					items: [resolutionsTextarea, resolutionSlider]
				}]
			}, {
				xtype: 'fieldset',
				title: '图层',
				layout: 'form',
				items: [layerTreePanel]
			}, {
				xtype: 'fieldset',
				title: '控件',
				layout: 'form',
				items: [{
					xtype: 'button',
					text: 'A'
				}]
			}, {
				xtype: 'fieldset',
				title: '交互',
				layout: 'form',
				items: [{
					xtype: 'button',
					text: 'A'
				}]
			}, {
				xtype: 'fieldset',
				title: '覆盖',
				layout: 'form',
				items: [{
					xtype: 'button',
					text: 'A'
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

		if (this.entityId) {
			this.getComponent('editForm').getForm().load({
				url: ctx + '/gis/thematic/input.ctrl',
				params: {
					id: this.entityId
				},
				waitMsg: '正在载入数据...',
				success: function(form, action) {
					layerTreePanel.mapId = form.findField('mapId').getValue();
					layerTreePanel.queryData();

					var renderers = form.findField('mapRenderer').getValue().split(',');
					var rendererButtons = this.down('#rendererContainer').items;
					for (var i = 0; i < rendererButtons.length; i++) {
						rendererButtons.get(i).setText(renderers[i]);
					}

					var resolutions = form.findField('viewResolutions').getValue().split(',');
					var resolution = form.findField('viewResolution').getValue();
					for (var i = 0; i < resolutions.length; i++) {
						if (Ext.String.trim(resolutions[i]) == Ext.String.trim(resolution)) {
							resolutionSlider.setValue(resolutions.length - i);
							break;
						}
					}
				},
				failure: function() {
					Ext.Msg.alert('系统提示', '无法加载信息！');
				},
				scope: this
			});
		}
	},
	rendererDragDropContainer: function(container) {
		container.dragZone = Ext.create('Ext.dd.DragZone', container.getEl(), {
			getDragData: function(e) {
				var sourceEl = e.getTarget(container.itemSelector, 10), d;
				if (sourceEl) {
					d = sourceEl.cloneNode(true);
					d.id = Ext.id();

					var button = null;
					for (var i = 0; i < container.items.length; i++) {
						if (sourceEl.id.indexOf(container.items.get(i).getId()) == 0) {
							button = container.items.get(i);
							break;
						}
					}

					return (container.dragData = {
						sourceEl: sourceEl,
						repairXY: Ext.fly(sourceEl).getXY(),
						ddel: d,
						srcButton: button
					});
				}
			},
			getRepairXY: function() {
				return this.dragData.repairXY;
			}
		});
	},
	rendererDragDropButton: function(button) {
		button.dropZone = Ext.create('Ext.dd.DropZone', button.el, {
			getTargetFromEvent: function(e) {
				return button;
			},
			onNodeDrop : function(target, dd, e, data) {
				var srcText = data.srcButton.getText();
				var text = button.getText();
				button.setText(srcText);
				data.srcButton.setText(text);
				return true;
			}
		});
	},
	saveRenderer: function() {
		var container = this.down('#rendererContainer');
		var renderers = [];
		for (var i = 0; i < container.items.length; i++) {
			renderers.push(container.items.get(i).getText());
		}
		this.getComponent('editForm').getForm().findField('mapRenderer').setValue(renderers.join(','));
	},
	saveLayouGroup: function() {
		var layerTreePanel = this.down('#layerTreePanel');
		this.getComponent('editForm').getForm().findField('layerGroup').setValue(Ext.encode(layerTreePanel.getJsonObject(layerTreePanel.getRootNode())));
	},
	saveForm: function() {
		var form = this.getComponent('editForm').getForm();
		if (form.isValid()) {
			this.saveRenderer();
			this.saveLayouGroup();

			form.submit({
				url: ctx + '/gis/thematic/save.ctrl',
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
