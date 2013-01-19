<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- @ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" --%>
<%-- @ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

	<jsp:include page="../layout/header.jsp"></jsp:include>
	<body class="bodypage">
		<jsp:include page="../layout/subHeader.jsp"></jsp:include>
		<jsp:include page="../script.jsp"></jsp:include>
		<div id="box-inside-white">
			<div class="wrapper">
				<div class="row">
					<jsp:include page="../layout/leftSide.jsp"></jsp:include>
					<div class="divdx">
						<div id="header-sub-title"><fmt:message key="utente_list_titolo"/></div>
						<div id="header-sub-action">
							<ul>
								<li><a href="showUtente.do"><img style="padding-right: 5px; padding-left: 3px;vertical-align: middle;" src="${pageContext.request.contextPath}/resources/icon/raster/gray_light/plus_8x8.png" alt="<fmt:message key="add"/>" border="0"/><fmt:message key="add"/></a></li>
								<%--
								<li><a href="showUtente.do">Aggiungi</a></li>
								<li><a href="showUtente.do">Aggiungi</a></li>
								<li><a href="showUtente.do">Aggiungi</a></li>
								<li class="last"><a href="showUtente.do">Aggiungi</a></li>
								--%>
							</ul>
						</div>
						<c:if test="${not empty ok_action}">
							<c:if test="${ok_action}">
								<div class="success"><fmt:message key="ok_action"/></div></c:if>
							<c:if test="${not ok_action}">
								<div class="warning"><fmt:message key="ko_action"/></div></c:if>
						</c:if>
						<c:if test="${not empty msg_info}"><div class="info">${msg_info}</div></c:if>
						<c:if test="${not empty msg_ok}"><div class="success">${msg_ok}</div></c:if>
						<c:if test="${not empty msg_warning}"><div class="warning">${msg_warning}</div></c:if>
						<c:if test="${not empty msg_error}"><div class="error">${msg_error}</div></c:if>
						<table class="stiletab" style="width: 95%;">
							<thead>
								<tr><th>User Name</th><th>Password</th><th>Cognome</th><th>Nome</th>
								<th style="width:20px; border-right:0;">&nbsp;</th></tr>
							</thead>
							<tbody>
								<c:forEach items="${lista}" var="item">
									<tr>
										<td><a href="showUtente.do?id=${item.id}">${item.userName}</a></td>
										<td>${item.password}</td>
										<td>${item.cognome}</td>
										<td>${item.nome}</td>
										<td style="vertical-align: middle;"><img style="padding-right: 5px; padding-left: 3px;vertical-align: middle; cursor: pointer;" border="0"
												onclick="javascript:goAction('deleteUtente.do?id=${utente.id}','<fmt:message key="msg_del"/>')"
												src="${pageContext.request.contextPath}/resources/icon/raster/gray_light/x_7x7.png" alt="<fmt:message key="del"/>" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>