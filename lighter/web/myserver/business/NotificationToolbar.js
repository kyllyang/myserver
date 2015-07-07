Ext.define('Business.NotificationToolbar', {
	extend: 'Ext.toolbar.Toolbar',

	border: false,

	initComponent: function() {
		this.callParent();

		var loginUserDisplay = Ext.create('Ext.form.field.Display', {
			value: '欢迎&nbsp;' + myServer.loginUser.username
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

		this.add(loginUserDisplay);
		this.add(Ext.create('Ext.toolbar.Fill'));
		this.add(exitButton);
	}
});
