Ext.define('Business.CustomerTraceGridPanel', {
    extend: 'Base.ux.GridPanel',

    customerId: null,
    customerCompanyName: null,

    initComponent: function() {
        Ext.apply(this, {
            itemId: 'customerTraceGridPanel',
            url: ctx + '/business/customertrace/dataset.ctrl',
            sortProperty: 'id',
            sortDirection: 'asc',
            idProperty: 'id',
            columns: [{
                dataIndex: 'id',
                hidden: true
            }, {
                dataIndex: 'customerId',
                hidden: true
            }, {
                text: '拜访日期',
                dataIndex: 'visitDate',
                renderer: function(value, metaData, record, rowIndex, colIndex, store, view) {
                    return Ext.Date.format(Ext.Date.parse(value, 'Y-m-d H:i:s.u'), 'Y-m-d');
                }
            }, {
                text: '单位名称',
                dataIndex: 'customerCompanyName'
            }, {
                text: '联系人',
                dataIndex: 'linkMan'
            }, {
                text: '职务',
                dataIndex: 'job'
            }, {
                text: '联系电话',
                dataIndex: 'phone'
            }, {
                text: '办公电话',
                dataIndex: 'officePhone'
            }, {
                text: '邮箱',
                dataIndex: 'email'
            }, {
                text: '等级',
                dataIndex: 'level'
            }, {
                text: '拜访成果',
                dataIndex: 'visitResult'
            }],
            listeners: {
                afterRender: function(form, eOpts){
                    this.keyNav = Ext.create('Ext.util.KeyNav', this.el, {
                        enter: function() {
                            this.queryData();
                        },
                        scope: this
                    });
                },
                scope: this
            }
        });
        this.callParent();

        this.on('itemdblclick', function(grid, record, item, index, e, eOpts){
            this.viewEventHandler();
        });

        this.store.on('beforeload', function(store, operation, eOpts) {
            this.store.proxy.actionMethods = {read: 'POST'};
            Ext.apply(this.store.proxy.extraParams, {
                'qc.customerId': this.customerId
            });
        }, this);

        this.queryData();
    },
    doAddEvent: function() {
        Ext.create('Business.CustomerTraceFormWindow', {
            customerId: this.customerId,
            customerCompanyName: this.customerCompanyName,
            customerTraceGridPanel: this
        }).show();
    },
    doEditEvent: function(id) {
        Ext.create('Business.CustomerTraceFormWindow', {
            entityId: id,
            customerTraceGridPanel: this
        }).show();
    },
    doViewEvent: function(id) {
        Ext.create('Business.CustomerTraceFormWindow', {
            entityId: id,
            customerTraceGridPanel: this,
            readOnlyForm: true
        }).show();
    },
    doDeleteEvent: function(ids) {
        Ext.Ajax.request({
            url: ctx + '/business/customertrace/delete.ctrl',
            params: {
                ids: ids
            },
            success: function(response, opts) {
                Ext.Msg.alert("系统提示", "数据删除成功！");
                this.queryData();
            },
            failure: function(response, opts) {
                Ext.Msg.alert("系统提示", "数据删除失败！");
            },
            scope: this
        });
    },
    queryData: function() {
        this.getStore().load();
    }
});
