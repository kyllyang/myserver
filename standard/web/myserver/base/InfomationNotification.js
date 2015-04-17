Ext.define('Base.InfomationNotification', {
	extend: 'Base.ux.Notification',

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
		var applicationComboBox = Ext.create('Ext.form.field.ComboBox', {
			store: Ext.create('Ext.data.Store', {
				fields: ['id', 'name'],
				data : [
					{id: '1', name: '应用1'},
					{id: '2', name: '应用2'},
					{id: '3', name: '应用3'}
				]
			}),
			queryMode: 'local',
			displayField: 'name',
			valueField: 'id',
			editable: false,
			listeners: {
				afterrender: function(comboBox, eOpts) {
					comboBox.select(comboBox.getStore().getAt(0));
				}
			}
		});
		var thematicComboBox = Ext.create('Ext.form.field.ComboBox', {
			store: Ext.create('Ext.data.Store', {
				fields: ['id', 'name'],
				data : [
					{id: '1', name: '专题1'},
					{id: '2', name: '专题2'},
					{id: '3', name: '专题3'}
				]
			}),
			queryMode: 'local',
			displayField: 'name',
			valueField: 'id',
			editable: false,
			listeners: {
				afterrender: function(comboBox, eOpts) {
					comboBox.select(comboBox.getStore().getAt(0));
				}
			}
		});
		var windowButton = Ext.create('Ext.button.Button', {
			text: '窗口列表',
			icon: ctx + '/resource/image/icon/window.png',
			menu: Ext.create('Ext.menu.Menu', {
				items: [Ext.create('Ext.menu.CheckItem', {
					text: '窗口1'
				})]
			})
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
		form.add(applicationComboBox);
		form.add(thematicComboBox);
		form.add(windowButton);
		form.add(exitButton);

		this.add(form);

		myServer.getMapContainer().loadMap();
		myServer.setFunctionNotification(Ext.create('Base.FunctionNotification').show());
	}
});
