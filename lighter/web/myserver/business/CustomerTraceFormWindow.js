Ext.define('Business.CustomerTraceFormWindow', {
    extend: 'Ext.window.Window',

    entityId: null,
    customerId: null,
    customerCompanyName: null,
    customerTraceGridPanel: null,
    readOnlyForm: false,

    title: '跟踪信息',
    width: 600,
    height: 250,
    border: false,
    modal: true,
    resizable: false,
    layout: 'fit',

    initComponent: function() {
        this.callParent();

        Ext.define('FormModel', {
            extend: 'Ext.data.Model',
            fields: [
                {name: 'id'},
                {name: 'customerId'},
                {name: 'customerCompanyName'},
                {name: 'visitDate'},
                {name: 'linkMan'},
                {name: 'job'},
                {name: 'phone'},
                {name: 'officePhone'},
                {name: 'email'},
                {name: 'level'},
                {name: 'visitResult'}
            ]
        });

        var visitDateDate = Ext.create('Ext.form.field.Date', {
            columnWidth: 0.5,
            fieldLabel: '<span style="color: #FF0000;">*</span>拜访日期',
            labelAlign: 'right',
            labelSeparator: '：',
            name: 'visitDate',
            format: 'Y-m-d',
            value: new Date(),
            allowBlank: false,
            readOnly: this.readOnlyForm
        });
        var companyNameText = Ext.create('Ext.form.field.Text', {
            columnWidth: 0.5,
            fieldLabel: '<span style="color: #FF0000;">*</span>单位名称',
            labelAlign: 'right',
            labelSeparator: '：',
            name: 'customerCompanyName',
            value: this.customerCompanyName,
            disabled: true
        });
        var linkManText = Ext.create('Ext.form.field.Text', {
            columnWidth: 0.5,
            fieldLabel: '<span style="color: #FF0000;">*</span>联系人',
            labelAlign: 'right',
            labelSeparator: '：',
            name: 'linkMan',
            maxLength: 50,
            allowBlank: false,
            readOnly: this.readOnlyForm
        });
        var jobText = Ext.create('Ext.form.field.Text', {
            columnWidth: 0.5,
            fieldLabel: '<span style="color: #FF0000;">*</span>职务',
            labelAlign: 'right',
            labelSeparator: '：',
            name: 'job',
            maxLength: 50,
            allowBlank: false,
            readOnly: this.readOnlyForm
        });
        var phoneText = Ext.create('Ext.form.field.Text', {
            columnWidth: 0.5,
            fieldLabel: '联系电话',
            labelAlign: 'right',
            labelSeparator: '：',
            name: 'phone',
            maxLength: 50,
            readOnly: this.readOnlyForm
        });
        var officePhoneText = Ext.create('Ext.form.field.Text', {
            columnWidth: 0.5,
            fieldLabel: '办公电话',
            labelAlign: 'right',
            labelSeparator: '：',
            name: 'officePhone',
            maxLength: 50,
            readOnly: this.readOnlyForm
        });
        var emailText = Ext.create('Ext.form.field.Text', {
            columnWidth: 0.5,
            fieldLabel: '电子邮箱',
            labelAlign: 'right',
            labelSeparator: '：',
            name: 'email',
            maxLength: 50,
            vtype: 'email',
            readOnly: this.readOnlyForm
        });
        var levelText = Ext.create('Ext.form.field.Text', {
            columnWidth: 0.5,
            fieldLabel: '等级',
            labelAlign: 'right',
            labelSeparator: '：',
            name: 'level',
            maxLength: 50,
            readOnly: this.readOnlyForm
        });
        var visitResultTextArea = Ext.create('Ext.form.field.TextArea', {
            fieldLabel: '拜访结果',
            labelAlign: 'right',
            labelSeparator: '：',
            name: 'visitResult',
            maxLength: 255,
            readOnly: this.readOnlyForm
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
                name: 'id'
            }, {
                xtype: 'hidden',
                name: 'customerId',
                value: this.customerId
            }, {
                xtype: 'container',
                layout: 'column',
                items: [visitDateDate, companyNameText]
            }, {
                xtype: 'container',
                layout: 'column',
                items: [linkManText, jobText]
            }, {
                xtype: 'container',
                layout: 'column',
                items: [phoneText, officePhoneText]
            }, {
                xtype: 'container',
                layout: 'column',
                items: [emailText, levelText]
            }, visitResultTextArea],
            buttons:[{
                xtype: 'button',
                text: '保存',
                handler: this.saveForm,
                scope:this,
                hidden: this.readOnlyForm
            }, {
                xtype: 'button',
                text: '关闭',
                handler: this.closeForm,
                scope:this
            }]
        });
        this.add(formPanel);

        if (this.readOnlyForm) {
            formPanel.getForm().getFields().each(function(item, index, len) {
                item.setReadOnly(true);
            }, this);
        }

        if (this.entityId) {
            this.getComponent('editForm').getForm().load({
                url: ctx + '/business/customertrace/input.ctrl',
                params: {
                    id: this.entityId
                },
                waitMsg: '正在载入数据...',
                success: function(form, action) {
                    form.findField('visitDate').setValue(Ext.Date.parse(Ext.decode(action.response.responseText).visitDate, 'Y-m-d H:i:s.u'));
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
                url: ctx + '/business/customertrace/save.ctrl',
                waitMsg: '正在保存数据，请稍候...',
                success: function(form, action) {
                    Ext.Msg.alert('系统提示', '数据保存成功！');
                    this.closeForm();
                    this.customerTraceGridPanel.queryData();
                },
                failure: function(form, action) {
                    Ext.Msg.alert('系统提示', '状态： ' + action.response.status + '&nbsp;' + action.response.statusText);
                },
                scope: this
            });
        }
    },
    closeForm: function() {
        this.close();
    }
});
