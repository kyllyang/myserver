Ext.define('Base.ux.ExcelDialog', {
	extend: 'Ext.window.Window',
	id: 'excelDialog',
	width: 600,
	height: 300,
	modal: true,
	grid: null,// 导出的 grid
	exportRange: 0,
	getGridColumns: function() {
		var gridColumns = Ext.clone(this.grid.columns);
		var gcs = [];
		for (var i = (Ext.getClass(this.grid.selModel).getName() == "Ext.selection.RowModel" ? 0 : 1); i < gridColumns.length; i++) {
			gcs.push(gridColumns[i]);
		}
		return gcs;
	},
	getHeaderRowCount: function(columns, currLevel) {
		var maxLevel = 0;
		for (var i = 0; i < columns.length; i++) {
			var level = 1;
			if (columns[i].columns) {// has children, middle header
				level = this.getHeaderRowCount(columns[i].columns, currLevel + 1) + 1;
			} else if (columns[i].initialConfig && columns[i].initialConfig.columns) {// has children, top header
				level = this.getHeaderRowCount(columns[i].initialConfig.columns, currLevel + 1) + 1;
			}
			if (maxLevel < level) {
				maxLevel = level;
			}
			columns[i].level = currLevel;
		}
		return maxLevel;
	},
	getHeaderColspan: function(columns) {
		var sumColspan = 0;
		for (var i = 0; i < columns.length; i++) {
			var colspan = 1;
			if (columns[i].columns) {// has children, middle header
				colspan = this.getHeaderColspan(columns[i].columns);
			} else if (columns[i].initialConfig && columns[i].initialConfig.columns) {// has children, top header
				colspan = this.getHeaderColspan(columns[i].initialConfig.columns);
			}
			sumColspan += colspan;
		}
		return sumColspan;
	},
	configHeaderColspan: function(columns) {
		for (var i = 0; i < columns.length; i++) {
			columns[i].colspan = this.getHeaderColspan([columns[i]]);
			if (columns[i].columns) {// has children, middle header
				this.configHeaderColspan(columns[i].columns);
			} else if (columns[i].initialConfig && columns[i].initialConfig.columns) {// has children, top header
				this.configHeaderColspan(columns[i].initialConfig.columns);
			}
		}
	},
	getSerialColumnArray: function(columns, parent) {
		var cells = [];
		for (var i = 0; i < columns.length; i++) {
			var id = this.guid();
			cells.push({
				id: id,
				parent: parent,
				text: "rownumberer" == columns[i].xtype ? "序号" : columns[i].text,
				dataIndex: "rownumberer" == columns[i].xtype ? "s_n" : columns[i].dataIndex,
				rowspan: 1,
				colspan: columns[i].colspan,
				rowIndex: columns[i].level,
				colIndex: i,
				header: columns[i].header,
				checked: !this.isDisabledCheck(columns[i]),
				disabled: this.isDisabledCheck(columns[i])
			});
			if (columns[i].columns) {// has children, middle header
				var as = this.getSerialColumnArray(columns[i].columns, id);
				cells[cells.length - 1].columns = as;
				for (var j = 0; j < as.length; j++) {
					cells.push(as[j]);
				}
			} else if (columns[i].initialConfig && columns[i].initialConfig.columns) {// has children, top header
				var as = this.getSerialColumnArray(columns[i].initialConfig.columns, id);
				cells[cells.length - 1].columns = as;
				for (var j = 0; j < as.length; j++) {
					cells.push(as[j]);
				}
			}
		}
		return cells;
	},
	getCellsByRowIndex: function(index, columns) {
		var cells = [];
		for (var i = 0; i < columns.length; i++) {
			if (columns[i].rowIndex == index) {
				cells.push(columns[i]);
			}
		}
		return cells;
	},
	getColumnById: function(id, columns) {
		var column = null;
		for (var i = 0; i < columns.length; i++) {
			if (columns[i].id == id) {
				column = columns[i];
				break;
			}
		}
		return column;
	},
	getCellByRowAndCol: function(rowIndex, colIndex, columns) {
		var column = null;
		for (var i = 0; i < columns.length; i++) {
			if (columns[i].rowIndex == rowIndex && columns[i].colIndex == colIndex) {
				column = columns[i];
				break;
			}
		}
		return column;
	},
	getCellsByParent: function(id, columns) {
		var cells = [];
		for (var i = 0; i < columns.length; i++) {
			if (columns[i].parent == id) {
				cells.push(columns[i]);
			}
		}
		return cells;
	},
	initComponent: function() {
		var gridColumns = this.getGridColumns();
		var headerRowCount = this.getHeaderRowCount(gridColumns, 0);// 行数
		var headerColCount = this.getHeaderColspan(gridColumns);// 列数
		this.configHeaderColspan(gridColumns);// reset colspan
		var columns = this.getSerialColumnArray(gridColumns, this.guid());

		var prevCells = null;// reset rowspan
		for (var i = headerRowCount - 1; i >= 0; i--) {
			var cells = this.getCellsByRowIndex(i, columns);
			if (prevCells) {
				for (var j = 0; j < cells.length; j++) {
					if (!cells[j].columns) {
						cells[j].rowspan = prevCells[0].rowspan + 1;
					}
				}
			}
			prevCells = cells;
		}

		for (var i = 0; i < headerRowCount; i++) {// reset colIndex
			var cells = this.getCellsByRowIndex(i, columns);
			var prevCell = null;
			for (var j = 0; j < cells.length; j++) {
				if (prevCell && (prevCell.parent == cells[j].parent)) {
					cells[j].colIndex = prevCell.colIndex + prevCell.colspan;
				} else {
					var parent = this.getColumnById(cells[j].parent, columns);
					if (parent) {
						cells[j].colIndex = parent.colIndex;
					}
				}
				prevCell = cells[j];
			}
		}

		var dataRows = [];
		for (var i = 0; i < headerRowCount; i++) {
			var dataCells = [];
			for (var j = 0; j < headerColCount; j++) {
				dataCells.push({disabled: true});
			}
			dataRows.push(dataCells);
		}

		for (var i = 0; i < dataRows.length; i++) {
			for (var j = 0; j < dataRows[i].length; j++) {
				var cell = this.getCellByRowAndCol(i, j, columns);
				if (cell) {
					Ext.apply(dataRows[i][j], cell);
				}
			}
		}

		var lastRowNum = dataRows.length - 1;
		var columnNames = [];
		var columnFields = [];
		for (var i = 0; i < dataRows[lastRowNum].length; i++) {
			var cell = dataRows[lastRowNum][i];
			if (cell.disabled) {
				for (var k = lastRowNum - 1; k >= 0; k--) {
					if (!dataRows[k][i].disabled) {
						cell = dataRows[k][i];
						break;
					}
				}
			}
			columnNames.push({
				text: "",
				dataIndex: cell.dataIndex,
				hidden: cell.disabled,
				sortable: false,
				renderer : function(value, metaData, record, rowIndex, colIndex, store) {
					var html = "";
					if (value && !value.disabled) {
						html = "<input type=\"checkbox\"" + (value.checked ? (" checked=\"checked\"") : "") + " onclick=\"Ext.getCmp('excelDialog').checkHandler(" + rowIndex + ", " + colIndex + ", this.checked)\"/>&nbsp;" + value.text;
					}
					return html;
				}
			});
			columnFields.push({name: cell.dataIndex});
		}

		var columnStore = Ext.create('Ext.data.ArrayStore', {
			fields: columnFields,
			data: dataRows
		});

		var columnPanel = Ext.create('Ext.panel.Panel', {
			title: '选择导出的列',
			region: 'center',
			layout: 'border',
			border: false,
			items: [Ext.create('Ext.grid.Panel', {
				id: 'columnGrid',
				region: 'center',
				border: false,
				enableColumnMove: false,
				enableColumnHide: false,
				hideHeaders: true,
				columns: columnNames,
				store: columnStore,
				viewConfig: {
					loadMask: false
				},
				listeners: {
					beforerender: function (cmp, eOpts) {
						cmp.columns[0].setHeight(0);
					}
				}
			})]
		});

		var rangePanel = Ext.create('Ext.panel.Panel', {
			title: '选择导出范围',
			region: 'south',
			border: false,
			items: [{
				xtype: 'radiogroup',
				width: 200,
				items: [{
					boxLabel: '当前页',
					name: 'range',
					inputValue: 0,
					checked: true
				}, {
					boxLabel: '全部',
					name: 'range',
					inputValue: 1
				}],
				listeners: {
					change: function(radioGroup, newValue) {
						this.exportRange = newValue['range'];
					},
					scope: this
				}
			}]
		});

		Ext.apply(this, {
			buttons: [{
				text: '导出',
				handler: function() {
					/*var flag = false;
					 for (var i = 0; i < dataRows[lastRowNum].length; i++) {
					 if (dataRows[lastRowNum][i].checked) {
					 flag = true;
					 break;
					 }
					 }
					 if (!flag) {
					 Ext.MessageBox.alert('信息', '请至少选择一列！');
					 return;
					 }*/

					var store = Ext.getCmp('columnGrid').getStore();

					var serialDatas = [];
					var maxRowIndex = 0;
					for (var i = 0; i < headerRowCount; i++) {
						for (var j = 0; j < headerColCount; j++) {
							var cell = store.getAt(i).raw[j];
							if (cell.checked) {
								serialDatas.push({
									id: cell.id,
									parent: cell.parent,
									text: cell.text,
									header: cell.header,
									dataIndex: cell.dataIndex,
									rowIndex: i,
									rowspan: 1,
									colspan: 1
								});
								if (maxRowIndex < i) {
									maxRowIndex = i;
								}
							}
						}
					}
					var maxRowCount = maxRowIndex + 1;

					for (var i = 0; i < serialDatas.length; i++) {// init colIndex & columns
						var parentCell = this.getColumnById(serialDatas[i].parent, serialDatas);
						var childrenCells = parentCell ? this.getCellsByParent(parentCell.id,  serialDatas) : this.getCellsByParent(serialDatas[i].parent,  serialDatas);
						for (var j = 0; j < childrenCells.length; j++) {
							if (childrenCells[j].id == serialDatas[i].id) {
								serialDatas[i].colIndex = j;
								break;
							}
						}

						var underCurrentCells = this.getCellsByParent(serialDatas[i].id,  serialDatas);
						if (underCurrentCells.length > 0) {
							serialDatas[i].columns = underCurrentCells;
						}
					}

					this.configHeaderColspan(serialDatas);// reset colspan

					for (var i = 0; i < maxRowCount; i++) {// reset rowspan
						var cells = this.getCellsByRowIndex(i, serialDatas);
						for (var j = 0; j < cells.length; j++) {
							if (!cells[j].columns) {
								cells[j].rowspan = maxRowCount - i;
							}
						}
					}

					for (var i = 0; i < maxRowCount; i++) {// reset colIndex
						var cells = this.getCellsByRowIndex(i, serialDatas);
						var prevCell = null;
						for (var j = 0; j < cells.length; j++) {
							if (prevCell && (prevCell.parent == cells[j].parent)) {
								cells[j].colIndex = prevCell.colIndex + prevCell.colspan;
							} else {
								var parent = this.getColumnById(cells[j].parent, serialDatas);
								if (parent) {
									cells[j].colIndex = parent.colIndex;
								}
							}
							prevCell = cells[j];
						}
					}


					var maxColCount = 0;
					var topCells = this.getCellsByRowIndex(0, serialDatas);
					for (var i = 0; i < topCells.length; i++) {
						maxColCount += this.getHeaderColspan([topCells[i]]);
					}

					var exportDataRows = [];
					for (var i = 0; i < maxRowCount; i++) {
						var exportDataCells = [];
						for (var j = 0; j < maxColCount; j++) {
							exportDataCells.push(null);
						}
						exportDataRows.push(exportDataCells);
					}

					for (var i = 0; i < exportDataRows.length; i++) {
						for (var j = 0; j < exportDataRows[i].length; j++) {
							var cell = this.getCellByRowAndCol(i, j, serialDatas);
							if (cell) {
								exportDataRows[i][j] = {
									text: cell.text,
									header: cell.header,
									dataIndex: cell.dataIndex,
									rowIndex: cell.rowIndex,
									colIndex: cell.colIndex,
									rowspan: cell.rowspan,
									colspan: cell.colspan
								};
							}
						}
					}

					for (var i = 0; i < exportDataRows[exportDataRows.length - 1].length; i++) {
						if (!exportDataRows[exportDataRows.length - 1][i]) {
							for (var j = exportDataRows.length - 2; j >= 0; j--) {
								if (exportDataRows[j][i]) {
									exportDataRows[exportDataRows.length - 1][i] = {
										dataIndex: exportDataRows[j][i].dataIndex
									};
									break;
								}
							}
						}
					}

					var param = this.getParamsObject();

					Ext.create('Ext.form.Panel', {
						standardSubmit: true,
						timeout: 120000,
						hidden: true,
						url: ctx + "/business/export/excel.ctrl",
						items: [
							{xtype:'hidden', name: 'title', value: encodeURIComponent(this.grid.title ? this.grid.title : '')},
							{xtype:'hidden', name: 'url', value: encodeURIComponent(this.grid.store.proxy.url)},
							{xtype:'hidden', name: 'exportRange', value: this.exportRange},
							{xtype:'hidden', name: 'param', value: encodeURIComponent(Ext.encode(param))},
							{xtype:'hidden', name: 'columnInfo', value: encodeURIComponent(Ext.encode(exportDataRows))}
						]
					}).getForm().submit();
				},
				scope: this
			}, {
				text: '取消',
				handler: function() {
					this.hide();
					this.destroy();
				},
				scope: this
			}],
			layout: 'border',
			items: [columnPanel, rangePanel]
		});

		this.callParent(arguments);
	},
	checkHandler: function(rowIndex, colIndex, checked) {
		var store = Ext.getCmp('columnGrid').getStore();
		var cell = store.getAt(rowIndex).raw[colIndex];
		cell.checked = checked;

		this.checkParentHandler(cell, rowIndex, store);
		this.checkChildrenHandler(cell, rowIndex + 1, store);

		store.reload();
	},
	checkParentHandler: function(cell, rowIndex, store) {
		if (rowIndex > 0) {
			var isAllFalse = true;
			var cells = store.getAt(rowIndex).raw;
			for (var i = 0; i < cells.length; i++) {
				if (!cells[i].disabled && cell.parent == cells[i].parent && cells[i].checked) {
					isAllFalse = false;
					break;
				}
			}

			if (cell.checked || isAllFalse) {
				var raw = store.getAt(rowIndex - 1).raw;
				for (var i = 0; i < raw.length; i++) {
					if (!raw[i].disabled && cell.parent == raw[i].id) {
						raw[i].checked = cell.checked;
						this.checkParentHandler(raw[i], rowIndex - 1, store);
						break;
					}
				}
			}
		}
	},
	checkChildrenHandler: function(cell, rowIndex, store) {
		var totalCount = store.getTotalCount();
		if (rowIndex < totalCount) {
			var raw = store.getAt(rowIndex).raw;
			for (var i = 0; i < raw.length; i++) {
				if (!raw[i].disabled && cell.id == raw[i].parent) {
					raw[i].checked = cell.checked;
					this.checkChildrenHandler(raw[i], rowIndex + 1, store);
				}
			}
		}
	},
	isDisabledCheck: function(column) {
		return '操作' == column.text || '操作' == column.header || column.hidden;
	},
	getParamsObject: function () {
		var options = {
			groupers: this.grid.store.groupers.items,
			page: this.grid.store.currentPage,
			start: (this.grid.store.currentPage - 1) * this.grid.store.pageSize,
			limit: this.grid.store.pageSize,
			addRecords: false,
			action: 'read',
			filters: this.grid.store.filters.items,
			sorters: this.grid.store.getSorters()
		};
		var operation = new Ext.data.Operation(options);

		var fakeRequest = this.grid.store.getProxy().buildRequest(operation);
		var params = fakeRequest.params;

		return params;
	},
	s4: function() {
		return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
	},
	guid: function() {
		return this.s4() + this.s4() + '-' + this.s4() + '-' + this.s4() + '-' + this.s4() + '-' + this.s4() + this.s4() + this.s4();
	}
});

function allProps(obj) {
	var props = "";
	for (var p in obj) {
		if (typeof(obj[p]) == "function") {
			props += p + "();\n";
		} else {
			props += p + "=" + obj[p] + ";\n";
		}
	}
	return props;
}
