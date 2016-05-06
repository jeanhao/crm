// 权限管理
Ext.namespace("CRM.rightManage");
// 编辑。。。。
function showEditWin() {
	var panel = new rightManagePanel();
	panel.edit();
}
// 存储器。。。
var rightStore = new Ext.data.JsonStore({
	url : 'SysRight_toList',
	root : 'entity', // 数据源。。
	totalProperty : 'rowCount', // 总行数。。。
	remoteSort : true,
	fields : [ "id","text","name", "rightTip"]
});
var rightManageColm = new Ext.grid.ColumnModel({columns:[
	new Ext.grid.CheckboxSelectionModel(),
	new Ext.grid.RowNumberer({header:'编号',width:32})
, {
	dataIndex : 'id',
	hidden : true
},{
	header : '权限名称',
	sortable : true,
	dataIndex : 'text',
	width : 220
}, {
	header : '权限代码',
	sortable : true,
	dataIndex : 'name',
	width : 240
}]});
var rightManageGrid = new Ext.grid.GridPanel({
	store : rightStore,
	cm : rightManageColm,
	sm : new Ext.grid.CheckboxSelectionModel(),
	height : 300,
	stripeRows : true,
	pageSize : pageSize,
	tbar : [
		{
		xtype:'button',
		text : '添加',
		iconCls : 'add',
		handler : function() {
			var panel = new rightManagePanel();
			panel.create();
		}
	},{
		xtype:'button',
		text : '删除',
		iconCls : 'del',
		handler : function(){
			var row = rightManageGrid.getSelectionModel().getSelections();
			if(row.length < 1){
				Ext.Msg.alert("提示","请至少选中一条信息!");
				return false;
			}else{
				Ext.Msg.confirm("提示","真的要删除吗？",function(btn){
					if (btn == "yes"){
						var entityIds = [];
		            for (var i = 0; i < row.length; i++) {
		            	entityIds.push(row[i].get('id'));
		            }
		            $.ajax({
		            	url:'SysRight_delete',
		            	data:{
		            		entityIds:entityIds
		            	},
		            	dataType:'json',
		            	traditional:true,
		            	type:'POST',
		            	success: function(data){
		            		Ext.Msg.alert("提示",data.msg);
		            		rightStore.reload();
		            	},error:function(){
		            		Ext.Msg.alert("提示","与后台数据交互失败！");
          		  		}
					});
					}
				});
			}
		}
	},{
		xtype:'button',
		text : ' 修改',
		iconCls : 'modify',
		handler : function(){
			var row = rightManageGrid.getSelectionModel().getSelections();
			if(row.length != 1){
				Ext.Msg.alert("提示","只能选中一条信息进行修改!");
				return false;
			}else{
				showEditWin();
			}
		}
	
	}],
	bbar : new Ext.PagingToolbar({
		pageSize : pageSize,
		store : rightStore,
		grid : rightManageGrid,
		displayInfo : true,
		displayMsg : '当前显示 {0} - {1}条记录&nbsp;&nbsp;共有 {2} 条记录',
		emptyMsg : "没有记录"
	})
});
CRM.rightManage.right = Ext.extend(Ext.Panel, {
	closable : true,
	autoScroll : true,
	layout : 'fit',
	maskDisabled : false,
	girdViewConfig : {
		animate : true
	},
	initWin : function(width, title) {
		var win = new Ext.Window({
			width : width,
			autoHeight : true,
			buttonAlign : "center",
			title : title,
			modal : true,
			closeAction : "hide",
			resizable : false,
			plain : true,
			items : [this.fp],
			buttons : [{
				text : "保存",
				handler : this.save,
				tooltip : '点击该按钮将执行确认操作',
				scope : this
			}, {
				text : "清空",
				handler : this.reset,
				scope : this
			}, {
				text : "取消",
				id : 'cancel',
				handler : function() {
					this.closeWin();
				},
				scope : this
			}]
		});
		return win;
	},
	// 显示窗体。。。
	showWin : function() {
		if (!this.win) {
			if (!this.fp) {
				this.fp = this.createForm();
			}
			this.win = this.createWin();
			this.win.on("close", function() {
				this.win = null;
				this.fp = null;
			}, this);
		}
		this.win.show();
	},
	// 新建
	create : function() {
		this.showWin();
		this.reset();
	},
	// 重置
	reset : function() {
		if (this.win)
			this.fp.form.reset();
	},
	// 关闭窗体操作。。。
	closeWin : function() {
		if (this.win)
			this.win.close();
		this.win = null;
	},
	// 编辑
	edit : function() {
		var record = rightManageGrid.getSelectionModel().getSelected();
		if (!record) {
			Ext.Msg.alert("提示", "请选择要编辑的行!");
			return;
		}
		this.showWin();
		this.fp.form.loadRecord(record);
	},
	// 保存。。
	save : function() {
		if (this.fp.form.isValid()) {
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : 'SysRight_saveorUpdate',
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert("系统消息", action.result.msg);
					this.closeWin();
					rightStore.reload();
				},
				failure : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg);
				},
				scope : this
			});
		}
	},
	initComponent : function() {
		CRM.rightManage.right.superclass.initComponent.call(this);
		if(!this.flag){
			CRM.rightManage.right.prototype.flag = 0;
		}
		if(this.flag == 0){
			rightStore.load({
				params : {
					start : 0,
					limit : pageSize
				}
			});
			CRM.rightManage.right.prototype.flag = 1;
		}
		this.add(rightManageGrid);
	}
});
// 权限面板。。。
rightManagePanel = new Ext.extend(CRM.rightManage.right, {
	id : 'right',
	createForm : function() {
		var formPanel = new Ext.form.FormPanel({
			labelWidth : 80,
			frame : true,
			autoHeight : true,
			resizable : false,
			labelAlign : 'right',
			defaultType : 'textfield',
			items : [{
				xtype : 'fieldset',
				title : '  ',
				autoHeight : true,
				items : [{
					layout : 'form',
					border : false,
					defaultType : 'textfield',
					columnWidth : .5,
					items : [{
						xtype : 'hidden',
						name : 'id'
					},{
						name : 'text',
						fieldLabel : '权限名称',
						allowBlank : false,
						blankText : '权限不能为空'
					}, {
						name : 'name',
						fieldLabel : '权限代码'
					}]
				}]
			}]
		});
		return formPanel;
	},
	createWin : function() {
		return this.initWin(300, '权限管理');
	},
	initComponent : function() {
		rightManagePanel.superclass.initComponent.call(this);
	}
});