// 面板风格
style = Ext.extend(Ext.Button, {
	text : "面板风格",
	pressed : false,
	menu : [{
		text : "默认风格",
		icon : 'resources/images/vista.jpg',
		handler : function() {
			changeSkin('ext-all')
		}
	}, {
		text : "银白风格",
		icon : 'resources/images/gray.jpg',
		handler : function() {
			changeSkin('xtheme-gray')
		}
	}, {
		text : "深蓝风格",
		icon : 'resources/images/slate.jpg',
		handler : function() {
			changeSkin('xtheme-slate')
		}
	}, {
		text : "紫色风格",
		icon : 'resources/images/purple.jpg',
		handler : function() {
			changeSkin('xtheme-purple')
		}
	}, {
		text : "绿色风格",
		icon : 'resources/images/green.jpg',
		handler : function() {
			changeSkin('xtheme-olive')
		}
	}, {
		text : "灰色风格",
		icon : 'resources/images/darkgray.jpg',
		handler : function() {
			changeSkin('xtheme-darkgray')
		}
	}, {
		text : "黑色风格",
		icon : 'resources/images/black.jpg',
		handler : function() {
			changeSkin('xtheme-slickness')
		}
	}]
})
// 改变面板风格函数
changeSkin = function(value) {
	Ext.util.CSS.swapStyleSheet('window', '/Proj/resources/ext/resources/css/' + value
			+ '.css');
};
var isSubmit = true;
var oldP = new Ext.form.TextField({
	name : 'oldPwd',
	id : 'oldPwd',
	inputType : 'password',
	allowBlank : false,
	blankText : '请输入旧密码',
	fieldLabel : '旧密码',
	// 检查用户名是否存在
	listeners : {
		'blur' : function() {
			Ext.Ajax.request({
				url : 'login.do?actionType=doValidPwd&oldPwd='
						+ Ext.get('oldPwd').getValue(),
				method : 'POST',
				success : function(request) {
					if (request.responseText == "输入的旧密码不正确") {
						this.oldP.markInvalid('输入的旧密码不正确');
						isSubmit = false;
					} else {
						isSubmit = true;
					}
				}
			});
		}
	}
});
modifyWin = Ext.extend(Ext.Window, {
	title : '修改密码',
	width : 300,
	autoHeight : true,
	frame : true,
	resizable : false,
	modal : true,
	closeAction : 'hide',
	buttonAlign : 'center',
	createModifyPanel : function() {
		return new Ext.form.FormPanel({
			frame : true,
			defaultType : 'textfield',
			items : [oldP/*{
				fieldLabel : '旧密码',
				name : 'oldPwd',
				inputType : 'password',
				allowBlank : false,
				blankText : '旧密码不能为空'
			}*/, {
				fieldLabel : '新密码',
				name : 'newPwd',
				inputType : 'password',
				allowBlank : false,
				blankText : '请输入新密码'
			}, {
				fieldLabel : '确认新密码',
				name : 'reNewPwd',
				allowBlank : false,
				inputType : 'password',
				blankText : '请确认新密码'
			}]
		});
	},
	modify : function() {
		// if (isSubmit == false) {
		// return;
		// }
		if (this.fp.form.isValid()) {
			if (this.fp.form.findField("newPwd").getValue() != this.fp.form
					.findField("reNewPwd").getValue()) {
				Ext.Msg.alert('警告', '两次密码输入不一致');
				return;
			}
			this.fp.form.submit({
				waitTitle : '请稍候',
				waitMsg : '正在保存......',
				url : 'login.do?actionType=doUpdatePwd',
				method : 'POST',
				success : function(form, action) {
					Ext.Msg.alert('系统消息', action.result.msg, function() {
							// closeModifyWin();
						});
					// var closeWin = new modifyWin();
					// closeWin.close();
				},
				failure : function(form, action) {
					Ext.Msg.alert('错误', action.result.msg);
				}
			});
		}
	},
	initComponent : function() {
		modifyWin.superclass.initComponent.call(this);
		this.fp = this.createModifyPanel();
		this.add(this.fp);
		this.addButton('确定', this.modify, this);
		this.addButton('重置', function() {
			this.fp.form.reset();
		}, this);
	}
});
// 营销管理
sellTree = function() {
	sellTree.superclass.constructor.call(this, {
		border : false,
		autoHeight : true,
		rootVisible : false,
		root : new Ext.tree.TreeNode({
			id : 'root',
			text : '营销管理',
			draggable : false,
			expanded : true
		})
	});

	// 添加第一个节点
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'saleMan',
		text : '销售机会管理',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event, saleChancePanel)
			}
		}

	}));
	// 添加第二个节点
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstPlan',
		text : '客户开发计划',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event, custdevelPlanPanel)
			}
		}
	}));
}
Ext.extend(sellTree, Ext.tree.TreePanel);

// 客户管理
customerTree = function() {
	customerTree.superclass.constructor.call(this, {
		border : false,
		autoHeight : false,
		rootVisible : false,// 不显示根节点
		root : new Ext.tree.TreeNode({
			id : 'root',
			draggable : false,
			expanded : true
		})
	});

	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstInfoMan',
		text : '客户信息管理',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event, custInfoPanel)
			}
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstLostMan',
		text : '客户流失管理',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event, custLostPanel)
			}
		}
	}));
}
Ext.extend(customerTree, Ext.tree.TreePanel);

// 服务管理
serviceTree = function() {
	serviceTree.superclass.constructor.call(this, {
		border : false,
		autoHeight : false,
		rootVisible : false,// 不显示根节点
		animate : true,
		lines : false,
		root : new Ext.tree.TreeNode({
			id : 'root',
			draggable : true,
			expanded : true
		})
	});
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstServiceCj',
		text : '服务创建',
		id : 'manage',
		iconCls : 'node',
		animate : true,
		listeners : {
			'click' : function() {
				if (currentRole == '3') {
					var win = new serviceCreateWin();
					win.show('manage');
				}

			},
			scope : this
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstServiceFp',
		text : '服务分配',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				if (currentRole == '2') {
					main.openTab(node, event, serviceAllotPanel);
				}
			}
		}
	}));

	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstServiceCl',
		text : '服务处理',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				if (currentRole == '3') {
					main.openTab(node, event, serviceDisposePanel);
				}
			}
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstServiceFk',
		text : '服务反馈',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				if (currentRole == '3') {
					main.openTab(node, event, serviceFeedbackPanel);
				}
			}
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstServiceGd',
		text : '服务归档',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event, servicePigeonholePanel)
			}
		}
	}));
}
Ext.extend(serviceTree, Ext.tree.TreePanel);

// 统计报表
reportTree = function() {
	reportTree.superclass.constructor.call(this, {
		border : false,
		autoHeight : false,
		rootVisible : false,// 不显示根节点
		animate : true,
		lines : false,
		root : new Ext.tree.TreeNode({
			id : 'root',
			draggable : true,
			expanded : true
		})
	});
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstProffer',
		text : '客户贡献分析',
		iconCls : 'node',
		animate : true,
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event,custProfferPanel)
			}
		}

	}));
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstStructure',
		text : '客户构成分析',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event, custLevelPanel)
			}
		}
	}));

	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstService',
		text : '客户服务分析',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event,custServicePanel)
			}
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'cstLost',
		text : '客户流失分析',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event, custLosePanel)
			}
		}
	}));
}
Ext.extend(reportTree, Ext.tree.TreePanel);

// 数据
baseDataTree = function() {
	baseDataTree.superclass.constructor.call(this, {
		border : false,
		autoHeight : false,
		rootVisible : false,// 不显示根节点
		lines : false,
		root : new Ext.tree.TreeNode({
			id : 'root',
			draggable : false,
			expanded : true
		})
	});
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'basdict',
		text : '数据字典管理',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event,dataManagePanel)
			}
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'prduct',
		text : '查询产品信息',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event,selectProductPanel)
			}
		}
	}));

	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'storage',
		text : '查询库存',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event,selectStoragePanel)
			}
		}
	}));
}
Ext.extend(baseDataTree, Ext.tree.TreePanel);


// 用户管理
userinfoTree = function() {
	userinfoTree.superclass.constructor.call(this, {
		border : false,
		autoHeight : true,
		rootVisible : false,
		root : new Ext.tree.TreeNode({
			id : 'root',
			text : '',
			draggable : false,
			expanded : true
		})
	});
	// 添加第一个节点
	this.root.appendChild(new Ext.tree.TreeNode({
		text : '用户管理',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event,  userinfoUserPanel);
			}
		}

	}));
	// 添加第二个节点
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'roleLook',
		text : '角色管理',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event,rolePanel);
			}
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode({
		 id : 'right',
		text : '权限管理',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event, rightManagePanel);
			}
		}
	}));
	this.root.appendChild(new Ext.tree.TreeNode({
		id : 'myRight',
		text : '我的权限',
		iconCls : 'node',
		listeners : {
			'click' : function(node, event) {
				main.openTab(node, event, myRightPanel);
			}
		}
	}));
}
Ext.extend(userinfoTree, Ext.tree.TreePanel);

// 信息菜单
bookPanel = function() {
	bookPanel.superclass.constructor.call(this, {
		id : 'menu',
		title : '菜单',
		region : 'west',
		width : 150,
		collapsible : true,
		minSize : 150,
		split : true,
		margins : '0 3 3 0',
		layout : 'accordion',
		layoutConfig : {
			animate : true
		},
		items : [{
			title : '营销管理',
			items : [new sellTree()]

		}, {
			title : '客户管理',
			items : [new customerTree()]

		}, {
			title : '服务管理',
			items : [new serviceTree()]
		}, {
			title : '统计报表',
			items : [new reportTree()]
		}, {
			title : '基础数据',
			items : [new baseDataTree()]
		}, {
			title : '系统管理',
			items : [new userinfoTree()]
		}]
	});
}
Ext.extend(bookPanel, Ext.Panel);

// 版权
copyrightPanel = function() {
	bookPanel.superclass.constructor.call(this, {
		region : 'south',
		width : 150,
		height : 30,
		frame : true,
		autoShow : true,
		collapsible : true,
		margins : '0 3 3 0',
		items : [{
			contentEl : 'copyright'
		}]
	});
}
Ext.extend(copyrightPanel, Ext.Panel);

// 内容面板
tabContent = function() {
	this.openTab = function(node, event, panel) {
		// this.el.mask('Loading...', 'x-mask-loading');
		event.stopEvent();
		var n = main.getComponent(node.id);
		if (!n) {
			var p = new panel();
			p.id = node.id;
			p.title = node.text;
			n = main.add(p);
		}
		main.setActiveTab(n);

	};

	this.closeTab = function(panel, id) {
		var o = (typeof panel == "string" ? panel : id || panel.id);
		var tab = this.getComponent(o);
		if (tab) {
			this.remove(tab);
		}
	};

	tabContent.superclass.constructor.call(this, {
		id : 'main',
		region : 'center',
		height : 20,
		margins : '0 3 3 0',
		autoTabs : true,
		enableTabScroll : true,
		activeTab : 0,
		items : {
			title : '系统介绍',
			closable : true,
			html : '<div><img src="resources/images/welcome.jpg"/></div>'
		}
	});
		// main.on('tabchange', this, custLinkmanPanel);
}
Ext.extend(tabContent, Ext.TabPanel);

var header, main, menu, copyright;

Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget='qtip';
	var btnModify = new Ext.Button({
		text : '修改密码',
		iconCls : 'modifyPwd',
		id : 'modify',
		handler : function() {
			showModifyWin('modify');
		}
	});
	var btnLogout = new Ext.Button({
		text : '注销用户',
		iconCls : 'logout',
		handler : function() {
			window.location.href = "logout";
		}
	});
	var userBar = new Ext.Toolbar({
		width : 220,
		items : [btnLogout,btnModify, new style]
	});
	header = new Ext.Panel({
		border : false,
		region : 'north',
		height : 80,
		frame : true,
		items : [{
			layout : "column",
			defaults : {
				border : false,
				bodyStyle : 'padding-top:6px'
			},
			items : [ {
				columnWidth : .60,
				html : '<img src="resources/images/title.jpg"/>'
			}, {
				columnWidth : .40,
				items : [userBar]
			}]
		}]
	});
	
	main = new tabContent();
	menu = new bookPanel();
	copyright = new copyrightPanel();
	
	var body = new Ext.Viewport({
		enableTabScroll:true,
		layout:'border',
		modal:true,
		items : [header, main, menu, copyright]
	});
});

// 显示修改密码面板
function showModifyWin(id) {
	var showWin = new modifyWin();
	showWin.show(id);
}
// 关闭修改密码面板
function closeModifyWin() {
	var closeWin = new modifyWin();
	closeWin.close();
}