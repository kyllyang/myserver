<%--
  User: Kyll
  Date: 2014-10-23 8:48
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>我的服务器</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/resource/component/ext-4.2.1.883/resources/css/ext-all.css"/>
	<script type="text/javascript" src="${ctx}/resource/component/ext-4.2.1.883/ext-all.js"></script>
	<script type="text/javascript" src="${ctx}/resource/component/ext-4.2.1.883/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/myserver/index.js"></script>
	<script type="text/javascript">
		var ctx = '${ctx}';

		myServer.loginUser = {
			id: '${sessionScope.sessionVo.userId}',
			name: '${sessionScope.sessionVo.name}',
			username: '${sessionScope.sessionVo.username}',
			roles: []
		};

		<c:forEach items="${sessionScope.sessionVo.roleSet}" var="role">
		myServer.loginUser.roles.push({
			id: '${role.id}',
			code: '${role.code}',
			name: '${role.name}'
		});
		</c:forEach>

		Ext.Loader.setConfig({
			enabled: true,
			paths: {
				Base: ctx + '/myserver/base',
				Business: ctx + '/myserver/business'
			}
		});

		Ext.EventManager.on(Ext.isIE ? document : window, 'keydown', function(e, t) {
			var key = e.getKey();
			if (key == e.BACKSPACE && (t.disabled || t.readOnly)) {
				e.stopEvent();
			}
		});

		Ext.tip.QuickTipManager.init();
	</script>
</head>
<body>

</body>
</html>
