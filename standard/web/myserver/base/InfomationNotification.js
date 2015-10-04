Ext.define('Base.InfomationNotification', {
	extend: 'Base.ux.Notification',

	moduleComboBox: null,
	thematicComboBox: null,
	windowButton: null,

	title: '系统信息',
	paddingY: 35,
	autoClose: false,
	draggable: true,
	position: 'tr',
	slideInDuration: '500',
	closeAction: 'hide',
	resizable: false,
	border: false,

	initComponent: function() {
		this.callParent();

		Ext.define("DataModel", {
			extend: 'Ext.data.Model',
			fields: [
				{name: 'id'},
				{name: 'name'}
			]
		});

		var loginUserButton = Ext.create('Ext.button.Button', {
			text: myServer.loginUser.username,
			icon: ctx + '/resource/image/icon/user.png'
		});
		this.moduleComboBox = Ext.create('Ext.form.field.ComboBox', {
			store: Ext.create('Ext.data.Store', {
				model: 'DataModel',
				proxy: {
					type: 'ajax',
					url: ctx + '/app/module/application.ctrl',
					reader: {
						type: 'json'
					}
				},
				autoLoad: true,
				listeners: {
					load: function(store, records, successful, eOpts) {
						this.moduleComboBox.select(records[0]);
					},
					scope: this
				}
			}),
			queryMode: 'remote',
			displayField: 'name',
			valueField: 'id',
			editable: false,
			listeners: {
				change: function(comboBox, newValue, oldValue, eOpts) {
					this.thematicComboBox.clearValue();
					var store = this.thematicComboBox.getStore();
					store.proxy.actionMethods = {read: 'POST'};
					Ext.apply(store.proxy.extraParams, {
						'qc.moduleId': newValue
					});
					store.load();
				},
				scope: this
			}
		});
		this.thematicComboBox = Ext.create('Ext.form.field.ComboBox', {
			store: Ext.create('Ext.data.Store', {
				model: 'DataModel',
				proxy: {
					type: 'ajax',
					url: ctx + '/gis/thematic/list.ctrl',
					reader: {
						type: 'json'
					}
				},
				autoLoad: false,
				listeners: {
					load: function(store, records, successful, eOpts) {
						this.thematicComboBox.select(records[0]);
					},
					scope: this
				}
			}),
			queryMode: 'remote',
			displayField: 'name',
			valueField: 'id',
			editable: false,
			listeners: {
				change: function(comboBox, newValue, oldValue, eOpts) {
					this.loadMapAndFunction(this.moduleComboBox.getValue(), newValue);
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

		var toolbar = Ext.create('Ext.toolbar.Toolbar', {
			border: false
		});
		toolbar.add(loginUserButton);
		toolbar.add(this.moduleComboBox);
		toolbar.add(this.thematicComboBox);
		toolbar.add(this.windowButton);
		toolbar.add(exitButton);

		this.add(toolbar);
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
		if (!Ext.isEmpty(thematicId)) {
			myServer.getMapContainer().loadMap(thematicId);

			var menuNotification = myServer.getMenuNotification();
			if (!Ext.isEmpty(menuNotification)) {
				menuNotification.close();
			}

			myServer.setMenuNotification(Ext.create('Base.MenuNotification', {
				applicationId: applicationId,
				thematicId: thematicId
			}).show());
		}
	}
});
