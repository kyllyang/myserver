Ext.define('Base.gis.thematic.InteractionFormWindow', {
	extend: 'Ext.window.Window',

	entityId: null,
	readOnlyForm: false,
	opener: null,

	title: '交互信息',
	width: 800,
	height: 435,
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
				{name: 'interactionDoubleClickZoom'},
				{name: 'interactionDoubleClickZoomDuration'},
				{name: 'interactionDoubleClickZoomDelta'},
				{name: 'interactionDragBox'},
				{name: 'interactionDragBoxCondition'},
				{name: 'interactionDragBoxStyle'},
				{name: 'interactionDragPan'},
				{name: 'interactionDragPanKineticDecay'},
				{name: 'interactionDragPanKineticMinVelocity'},
				{name: 'interactionDragPanKineticDelay'},
				{name: 'interactionDragRotate'},
				{name: 'interactionDragRotateCondition'},
				{name: 'interactionDragRotateDuration'},
				{name: 'interactionDragRotateAndZoom'},
				{name: 'interactionDragRotateAndZoomCondition'},
				{name: 'interactionDragRotateAndZoomDuration'},
				{name: 'interactionDragZoom'},
				{name: 'interactionDragZoomCondition'},
				{name: 'interactionDragZoomStyle'},
				{name: 'interactionDragZoomDuration'},
				{name: 'interactionKeyboardPan'},
				{name: 'interactionKeyboardPanDuration'},
				{name: 'interactionKeyboardPanPixelDelta'},
				{name: 'interactionKeyboardZoom'},
				{name: 'interactionKeyboardZoomDuration'},
				{name: 'interactionKeyboardZoomDelta'},
				{name: 'interactionMouseWheelZoom'},
				{name: 'interactionMouseWheelZoomDuration'},
				{name: 'interactionPinchRotate'},
				{name: 'interactionPinchRotateDuration'},
				{name: 'interactionPinchRotateThreshold'},
				{name: 'interactionPinchZoom'},
				{name: 'interactionPinchZoomDuration'}
			]
		});

		var interactionDoubleClickZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '双击缩放',
			name: 'interactionDoubleClickZoom',
			inputValue: '1',
			checked: true,
			qtip: 'Allows the user to zoom by double-clicking on the map.'
		});
		var interactionDoubleClickZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDoubleClickZoomDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var interactionDoubleClickZoomDeltaNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '变焦',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDoubleClickZoomDelta',
			value: 1,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The zoom delta applied on each double click, default is 1.'
		});
		var interactionDragBoxCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '矩形选择',
			name: 'interactionDragBox',
			inputValue: '1',
			qtip: 'Allows the user to draw a vector box by clicking and dragging on the map, normally combined with an ol.events.condition that limits it to when the shift or other key is held down. This is used, for example, for zooming to a specific area of the map (see ol.interaction.interactionDragZoom and ol.interaction.interactionDragRotateAndZoom).This interaction is only supported for mouse devices.'
		});
		var interactionDragBoxConditionCombobox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_interaction_condition',
			fieldLabel: '触发条件',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragBoxCondition',
			value: 'ol.events.condition.shiftKeyOnly',
			qtip: 'A function that takes an ol.MapBrowserEvent and returns a boolean to indicate whether that event should be handled. Default is ol.events.condition.always.'
		});
		var interactionDragBoxStyleTrigger = Ext.create('Ext.form.field.Trigger', {
			fieldLabel: '样式',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragBoxStyle',
			onTriggerClick: function() {
				Ext.create('Base.gis.style.StyleWindow', {
					styleData: Ext.isEmpty(interactionDragBoxStyleTrigger.getValue()) ? null : Ext.decode(interactionDragBoxStyleTrigger.getValue()),
					onDetermine: function(style) {
						interactionDragBoxStyleTrigger.setValue(Ext.encode(style));
					}
				}).show();
			},
			qtip: 'Style for the box.'
		});
		var interactionDragPanCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '拖动平移',
			name: 'interactionDragPan',
			inputValue: '1',
			checked: true,
			qtip: 'Allows the user to pan the map by dragging the map.'
		});
		var interactionDragPanKineticDecayNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '衰变',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragPanKineticDecay',
			maxValue: 0,
			qtip: 'Rate of decay (must be negative).'
		});
		var interactionDragPanKineticMinVelocityNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '最小速度',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragPanKineticMinVelocity',
			minValue: 1,
			allowDecimals: false,
			qtip: 'Minimum velocity (pixels/millisecond).'
		});
		var interactionDragPanKineticDelayNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '延迟',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragPanKineticDelay',
			minValue: 1,
			allowDecimals: false,
			qtip: 'Delay to consider to calculate the kinetic initial values (milliseconds).'
		});
		var interactionDragRotateCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '拖动旋转',
			name: 'interactionDragRotate',
			inputValue: '1',
			checked: true,
			qtip: 'Allows the user to rotate the map by clicking and dragging on the map, normally combined with an ol.events.condition that limits it to when the alt and shift keys are held down.This interaction is only supported for mouse devices.'
		});
		var interactionDragRotateConditionCombobox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_interaction_condition',
			fieldLabel: '触发条件',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragRotateCondition',
			value: 'ol.events.condition.altShiftKeysOnly',
			qtip: 'A function that takes an ol.MapBrowserEvent and returns a boolean to indicate whether that event should be handled. Default is ol.events.condition.altShiftKeysOnly.'
		});
		var interactionDragRotateDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragRotateDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var interactionDragRotateAndZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '拖动旋转和缩放',
			name: 'interactionDragRotateAndZoom',
			inputValue: '1',
			qtip: 'Allows the user to zoom and rotate the map by clicking and dragging on the map. By default, this interaction is limited to when the shift key is held down. This interaction is only supported for mouse devices. And this interaction is not included in the default interactions.'
		});
		var interactionDragRotateAndZoomConditionCombobox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_interaction_condition',
			fieldLabel: '触发条件',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragRotateAndZoomCondition',
			value: 'ol.events.condition.shiftKeyOnly',
			qtip: 'A function that takes an ol.MapBrowserEvent and returns a boolean to indicate whether that event should be handled. Default is ol.events.condition.shiftKeyOnly.'
		});
		var interactionDragRotateAndZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragRotateAndZoomDuration',
			value: 400,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 400.'
		});
		var interactionDragZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '拖动缩放',
			name: 'interactionDragZoom',
			inputValue: '1',
			checked: true,
			qtip: 'Allows the user to zoom the map by clicking and dragging on the map, normally combined with an ol.events.condition that limits it to when a key, shift by default, is held down.'
		});
		var interactionDragZoomConditionCombobox = Ext.create('Base.ux.DictComboBox', {
			invokeCode: 'gis_interaction_condition',
			fieldLabel: '触发条件',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragZoomCondition',
			value: 'ol.events.condition.shiftKeyOnly',
			qtip: 'A function that takes an ol.MapBrowserEvent and returns a boolean to indicate whether that event should be handled. Default is ol.events.condition.shiftKeyOnly.'
		});
		var interactionDragZoomStyleTrigger = Ext.create('Ext.form.field.Trigger', {
			fieldLabel: '样式',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragZoomStyle',
			onTriggerClick: function() {
				Ext.create('Base.gis.style.StyleWindow', {
					styleData: Ext.isEmpty(interactionDragZoomStyleTrigger.getValue()) ? null : Ext.decode(interactionDragZoomStyleTrigger.getValue()),
					onDetermine: function(style) {
						interactionDragZoomStyleTrigger.setValue(Ext.encode(style));
					}
				}).show();
			},
			qtip: 'Style for the box.'
		});
		var interactionDragZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionDragZoomDuration',
			value: 200,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 200.'
		});
		var interactionKeyboardPanCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '键盘平移',
			name: 'interactionKeyboardPan',
			inputValue: '1',
			checked: true,
			qtip: 'Allows the user to pan the map using keyboard arrows. Note that, although this interaction is by default included in maps, the keys can only be used when browser focus is on the element to which the keyboard events are attached. By default, this is the map div, though you can change this with the keyboardEventTarget in ol.Map. document never loses focus but, for any other element, focus will have to be on, and returned to, this element if the keys are to function. See also ol.interaction.interactionKeyboardZoom.'
		});
		var interactionKeyboardPanDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionKeyboardPanDuration',
			value: 100,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 100.'
		});
		var interactionKeyboardPanPixelDeltaNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '像素间隔',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionKeyboardPanPixelDelta',
			value: 128,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Pixel The amount to pan on each key press. Default is 128 pixels.'
		});
		var interactionKeyboardZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '键盘缩放',
			name: 'interactionKeyboardZoom',
			inputValue: '1',
			checked: true,
			qtip: 'Allows the user to zoom the map using keyboard + and -. Note that, although this interaction is by default included in maps, the keys can only be used when browser focus is on the element to which the keyboard events are attached. By default, this is the map div, though you can change this with the keyboardEventTarget in ol.Map. document never loses focus but, for any other element, focus will have to be on, and returned to, this element if the keys are to function. See also ol.interaction.interactionKeyboardPan.'
		});
		var interactionKeyboardZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionKeyboardZoomDuration',
			value: 100,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 100.'
		});
		var interactionKeyboardZoomDeltaNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '变焦',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionKeyboardZoomDelta',
			value: 1,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The amount to zoom on each key press. Default is 1.'
		});
		var interactionMouseWheelZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '鼠标滚轮缩放',
			name: 'interactionMouseWheelZoom',
			inputValue: '1',
			checked: true,
			qtip: 'Allows the user to zoom the map by scrolling the mouse wheel.'
		});
		var interactionMouseWheelZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionMouseWheelZoomDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 250.'
		});
		var interactionPinchRotateCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '触屏旋转',
			name: 'interactionPinchRotate',
			inputValue: '1',
			checked: true,
			qtip: 'Allows the user to rotate the map by twisting with two fingers on a touch screen.'
		});
		var interactionPinchRotateDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionPinchRotateDuration',
			value: 250,
			minValue: 1,
			allowDecimals: false,
			qtip: 'The duration of the animation in milliseconds. Default is 250.'
		});
		var interactionPinchRotateThresholdNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '阈值',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionPinchRotateThreshold',
			value: 0.3,
			qtip: 'Minimal angle in radians to start a rotation. Default is 0.3.'
		});
		var interactionPinchZoomCheckbox = Ext.create('Ext.form.field.Checkbox', {
			columnWidth: 0.15,
			boxLabel: '触屏缩放',
			name: 'interactionPinchZoom',
			inputValue: '1',
			checked: true,
			qtip: 'Allows the user to zoom the map by pinching with two fingers on a touch screen.'
		});
		var interactionPinchZoomDurationNumber = Ext.create('Ext.form.field.Number', {
			fieldLabel: '持续时间',
			labelAlign: 'right',
			labelSeparator: '：',
			width: 221,
			name: 'interactionPinchZoomDuration',
			value: 400,
			minValue: 1,
			allowDecimals: false,
			qtip: 'Animation duration in milliseconds. Default is 400.'
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
				items: [interactionDoubleClickZoomCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionDoubleClickZoomDurationNumber, interactionDoubleClickZoomDeltaNumber, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionDragBoxCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionDragBoxConditionCombobox, interactionDragBoxStyleTrigger, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionDragPanCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionDragPanKineticDecayNumber, interactionDragPanKineticMinVelocityNumber, interactionDragPanKineticDelayNumber]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionDragRotateCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionDragRotateConditionCombobox, interactionDragRotateDurationNumber, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionDragRotateAndZoomCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionDragRotateAndZoomConditionCombobox, interactionDragRotateAndZoomDurationNumber, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionDragZoomCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionDragZoomConditionCombobox, interactionDragZoomStyleTrigger, interactionDragZoomDurationNumber]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionKeyboardPanCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionKeyboardPanDurationNumber, interactionKeyboardPanPixelDeltaNumber, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionKeyboardZoomCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionKeyboardZoomDurationNumber, interactionKeyboardZoomDeltaNumber, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionMouseWheelZoomCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionMouseWheelZoomDurationNumber, {
						xtype: 'container'
					}, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionPinchRotateCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionPinchRotateDurationNumber, interactionPinchRotateThresholdNumber, {
						xtype: 'container'
					}]
				}]
			}, {
				xtype: 'container',
				layout: 'column',
				items: [interactionPinchZoomCheckbox, {
					columnWidth: 0.85,
					xtype: 'container',
					layout: {
						type: 'table',
						columns: 3
					},
					items: [interactionPinchZoomDurationNumber, {
						xtype: 'container'
					}, {
						xtype: 'container'
					}]
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
				url: ctx + '/gis/interaction/input.ctrl',
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
				url: ctx + '/gis/interaction/save.ctrl',
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
