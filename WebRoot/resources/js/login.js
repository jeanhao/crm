Ext.onReady(function() {
	var loginForm = new Ext.form.FormPanel({
		bodyStyle : 'padding-top:6px',
		defaultType : 'textfield',
		labelAlign : 'right',
		labelWidth : 55,
		labelPad : 0,
		frame : true,
		defaults : {
			allowBlank : false,
			width : 158
		},
		items : [ {
			cls : 'user',
			name : 'j_username',
			fieldLabel : '帐号',
			blankText : '帐号不能为空'
		}, {
			cls : 'key',
			name : 'j_password',
			fieldLabel : '密码',
			blankText : '密码不能为空',
			inputType : 'password'
		}, {
			id : "randCode",
			name : "j_randCode",
			width : 70,
			fieldLabel : "验证码",
			allowBlank : false,
			blankText : "验证码必须输入"
		} ]
	});
	var regForm = new Ext.form.FormPanel({
		defaultType : 'textfield',
		id:"regForm",
		labelAlign : 'right',
		labelWidth : 55,
		labelPad : 0,
		frame : true,
		defaults : {
			allowBlank : false,
			width : 158
		},
		items : [ {
			name : 'userName',
			fieldLabel : '帐号',
			blankText : '帐号不能为空'
		}, {
			name : 'usrPassword',
			fieldLabel : '密码',
			blankText : '密码不能为空',
			inputType : 'password'
		}, {
			name : "RePassword",
			fieldLabel : "确认密码",
			blankText : "两次密码不一致"
		},{
			name : 'email',
			fieldLabel : '邮箱',
			blankText : '邮箱不能为空'
		} ]
	});
	var findPassForm = new Ext.form.FormPanel({
		defaultType : 'textfield',
		id:"findPassForm",
		labelAlign : 'right',
		labelWidth : 55,
		labelPad : 0,
		frame : true,
		defaults : {
			allowBlank : false,
			width : 158
		},
		items : [ {
			name : 'userName',
			fieldLabel : '帐号',
			blankText : '帐号不能为空'
		}, {
			name : 'email',
			fieldLabel : '邮箱',
			blankText : '邮箱不能为空',
			inputType : 'password'
		}]
	});
	function addSubmitButton(formName,texts,url){
		var btnSubmit = new Ext.Button({
			text : texts,
			handler:function(){
				if(formName.getForm().isValid()){
					formName.getForm().submit()({
						waitTitle : '请稍后',
						waitMsg : "正在"+texts+"......",
						url:url,
						success:function(form,action){
							form.reset();
							Ext.Msg.alert("提示","成功"+texts);
							tabs.setActiveTab("LoginTab");
						},
						failure : function(form,action){
							Ext.Msg.alert("提示",action.result.msg);
						}
					});
				}
			}
		});
		formName.addButton(btnSubmit);
	}
	
	function addResetButton(formName){
		var btnreset = new Ext.Button({
			text : "重置",
			handler :function(){
				formName.getForm().reset();
			}
		});
		formName.addButton(btnreset);
	}
	var loginSubmit = new Ext.Button({
		text : "登陆",
		handler : function() {
			if (loginForm.getForm().isValid()) {
				loginForm.getForm().submit({
					waitTitle : '请稍候',
					waitMsg : '正在登录......',
					url : 'login',
					success : function(form, action) {
						Ext.Msg.alert("提示", "登陆成功!");
						window.location.href = 'toMain';
					},
					failure : function(form, action) {
						var error;
						switch (action.result.status) {
						case 1:
							error = "用户名为空";
							break;
						case 2:
							error = "密码为空";
							break;
						case 3:
							error = "验证码为空";
							break;
						case 4:
							error = "验证码输入错误";
							break;
						case 5:
							error = "用户名或密码错误";
							break;
						default:
							error = "数据有误";
							break;
						}
						form.reset();
						Ext.Msg.alert('警告', error);
					}
				});
			}
			;
		}
	});
	loginForm.addButton(loginSubmit);
	addResetButton(loginForm);
	addSubmitButton(regForm, "注册","register");
	addResetButton(regForm);
	addSubmitButton(findPassForm,"找回密码","findPassword");
	addResetButton(findPassForm);
	
	var tabs = new Ext.TabPanel({
		animate : true,
		frame:true,
		height : "200px",
		activeTab : 0,// 初始显示第几个Tab页
//		deferredRender : true,// 是否在显示每个标签的时候再渲染标签中的内容.默认true
		tabPosition : 'top'// 表示TabPanel头显示的位置,只有两个值top和bottom.默认是top.
	});
	tabs.add({
		id : 'LoginTab',
		title : '用户登录',
		listeners : {
			'beforeshow' : function(){
				if(LoginWin != undefined){
					LoginWin.setHeight(200);
				}
			}
		}
	});
	tabs.add({
		id : "RegTab",
		title : "用户注册",
		listeners : {
			'beforeshow' : function() {
				LoginWin.setHeight(220);
				tabs.getItem('RegTab').add(regForm);
				tabs.doLayout();
			}
		}
	});
	tabs.add({
		id : "findPassTab",
		title : "找回密码",
		listeners : {
			'beforeshow' : function() {
				LoginWin.setHeight(165);
				tabs.getItem('findPassTab').add(findPassForm);
				tabs.doLayout();
			}
		}
	});
	var LoginWin = new Ext.Window({
		renderTo : 'loginWin',
		title : '系统登录',
		id : "loginWindow",
		width : 300,
		height : 200,
		layout:'fit',
		closable : false,
		draggable : false,
		resizable : false,
		buttonAlign : 'center',
		items:tabs
	});
	// 初始化标签中的Ext:Qtip属性。
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	LoginWin.show();
	tabs.getItem('LoginTab').add(loginForm);
	 tabs.doLayout();
	var rc = Ext.getDom("randCode");
	 var rcp = Ext.get(rc.parentNode);
	 rcp.createChild({id:"randCo",tag: 'img', src: 'image.jsp',align:'absbottom'});
	
	//tabs.getItem("LoginTab").add(loginForm);
	//tabs.doLayout();
	 
});
