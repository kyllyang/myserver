Ext.define('Base.InfomationNotification', {
	extend: 'Base.ux.Notification',

	applications: [],
	applicationComboBox: null,
	thematicComboBox: null,
	windowButton: null,

	title: '系统信息',
	autoClose: false,
	draggable: true,
	position: 'tr',
	slideInDuration: '500',
	closeAction: 'hide',
	resizable: false,
	border: false,

	initComponent: function() {
		this.callParent();

		var loginUserButton = Ext.create('Ext.button.Button', {
			text: myServer.loginUser.username,
			icon: ctx + '/resource/image/icon/user.png'
		});
		this.applicationComboBox = Ext.create('Ext.form.field.ComboBox', {
			store: Ext.create('Ext.data.Store', {
				fields: ['id', 'name'],
				data: this.applications
			}),
			queryMode: 'local',
			displayField: 'name',
			valueField: 'id',
			editable: false,
			listeners: {
				change: function(comboBox, newValue, oldValue, eOpts) {
					for (var i = 0; i < this.applications.length; i++) {
						if (newValue == this.applications[i].id) {
							this.thematicComboBox.getStore().loadData(this.applications[i].thematics);
							break;
						}
					}
				},
				afterrender: function(comboBox, eOpts) {
					comboBox.select(comboBox.getStore().getAt(0));
				},
				scope: this
			}
		});
		this.thematicComboBox = Ext.create('Ext.form.field.ComboBox', {
			store: Ext.create('Ext.data.Store', {
				fields: ['id', 'name'],
				listeners: {
					datachanged: function(store, eOpts) {
						this.thematicComboBox.select(store.getAt(0));
					},
					scope: this
				}
			}),
			queryMode: 'local',
			displayField: 'name',
			valueField: 'id',
			editable: false,
			listeners: {
				change: function(comboBox, newValue, oldValue, eOpts) {
					this.loadMapAndFunction();
				},
				afterrender: function(comboBox, eOpts) {
					comboBox.select(comboBox.getStore().getAt(0));
				},
				scope: this
			}
		});
		this.windowButton = Ext.create('Ext.button.Button', {
			text: '窗口列表',
			icon: ctx + '/resource/image/icon/window.png',
			menu: Ext.create('Ext.menu.Menu')
		});
		var exitButton = Ext.create('Ext.button.Button', {
			text: '退出',
			icon: ctx + '/resource/image/icon/exit.png',
			handler: function() {
				Ext.MessageBox.confirm("系统提示", "确定要退出系统吗？", function (btn) {
					if ('yes' == btn) {
						Ext.Ajax.request({
							url: ctx + '/logout.ctrl',
							success: function(response, opts) {
								window.location = ctx + '/login.jsp';
							},
							failure: function(response, opts) {
								Ext.Msg.alert("系统提示", "退出系统失败！");
							},
							scope: this
						});
					}
				});
			},
			scope: this
		});

		var form = Ext.create('Ext.toolbar.Toolbar', {
			border: false
		});
		form.add(loginUserButton);
		form.add(this.applicationComboBox);
		form.add(this.thematicComboBox);
		form.add(this.windowButton);
		form.add(exitButton);

		this.add(form);
	},
	refreshWindowButton: function() {
		this.windowButton.menu.removeAll();

		var businessWindowMap = myServer.getBusinessWindowMap();
		businessWindowMap.each(function(key, value, length) {
			this.windowButton.menu.add(Ext.create('Ext.menu.CheckItem', {
				itemId: key,
				text: value.title,
				checked: !businessWindowMap.get(key).isHidden(),
				listeners: {
					checkchange: function(checkItem, checked, eOpts) {
						var businessWindow = businessWindowMap.get(key);
						if (checked) {
							businessWindow.show();
						} else {
							businessWindow.hide();
						}
					},
					scope: this
				}
			}))
		}, this);
	},
	setWindowCheckItemChecked: function(menuId, checked) {
		this.windowButton.menu.getComponent(menuId).setChecked(checked);
	},
	loadMapAndFunction: function(applicationId, thematicId) {
		myServer.getMapContainer().loadMap();
		myServer.setFunctionNotification(Ext.create('Base.FunctionNotification', {
			applicationId: applicationId,
			thematicId: thematicId
		}).show());
	}
});
