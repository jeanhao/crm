<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>客户关系管理系统</title>
<!-- 资源包 -->
<!-- ext导入开始 -->
<link href="resources/ext/resources/css/ext-all.css" rel="stylesheet">
<script type="text/javascript"
	src="resources/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="resources/ext/ext-all.js"></script>
<script type="text/javascript"
	src="resources/ext/build/locale/ext-lang-zh_CN.js"></script>
<!-- extjs导入结束 -->
<!-- jquery导入 -->
<script type="text/javascript" src="resources/js/jquery-1.7.1.js"></script>
<!-- 自定义js导入 -->
<script type="text/javascript" src="resources/js/main.js"></script>
<!-- 自定义css导入 -->
<link href="resources/css/main.css" rel="stylesheet">

<!-- js通用变量配置-->
<script type="text/javascript">
//页数
var pageSize = 5;
</script>
</head>

<body>
<!-- 营销管理 -->
		<%--<script type="text/javascript" src="/Proj/resources/js/sale/saleChance.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/sale/custDevelPlan.js"></script>
		--%><!-- 客户管理 -->
		<%--<script type="text/javascript" src="/Proj/resources/js/cust/custInfo.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/cust/custLinkman.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/cust/custActivity.js"></script>
		<script type="text/javascript" src="/Proj/resources/js/cust/custOrders.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/cust/custOrdersDetail.js"></script>
		<script type="text/javascript" src="/Proj/resources/js/cust/custLost.js"></script>
		--%><!-- 服务管理 -->
		<%--<script type="text/javascript"
			src="/Proj/resources/js/service/serviceCreate.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/service/serviceAllot.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/service/serviceDispose.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/service/serviceFeedback.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/service/servicePigeonhole.js"></script>
		--%><!-- 统计报表 -->
		<%--<script type="text/javascript"
			src="/Proj/resources/js/rept/custProffer.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/rept/custStructure.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/rept/custService.js"></script>
		<script type="text/javascript" src="/Proj/resources/js/rept/custLose.js"></script>
		--%><!-- 基础数据 -->
		<%--<script type="text/javascript" src="/Proj/resources/js/basd/dataManage.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/basd/selectProduct.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/basd/selectStorage.js"></script>
		--%><!-- 权限管理-->
		<script type="text/javascript"
			src="/Proj/resources/js/system/userinfo.js"></script>
		<script type="text/javascript" src="/Proj/resources/js/system/role.js"></script>
		<script type="text/javascript"
			src="/Proj/resources/js/system/rightManage.js"></script>
		<script type="text/javascript" src="/Proj/resources/js/system/myRight.js"></script>
</body>
</html>
