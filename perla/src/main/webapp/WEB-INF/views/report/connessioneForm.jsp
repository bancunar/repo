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
		<div id="box-inside-white">
			<div class="wrapper">
				<div class="row">
					<jsp:include page="../layout/leftSide.jsp"></jsp:include>
					<div class="divdx">
						<div id="header-sub-title"><fmt:message key="conn_form_titolo"/></div>
						<div id="header-sub-action">
							<ul>
								<li><a href="showListaConnessioni.do"><img style="padding-right: 5px; padding-left: 3px;vertical-align: middle;" src="${pageContext.request.contextPath}/resources/icon/raster/gray_light/list_8x7.png" alt="<fmt:message key="back_list"/>" border="0"/><fmt:message key="back_list"/></a></li>
								<li><a href="showConnessione.do"><img style="padding-right: 5px; padding-left: 3px;vertical-align: middle;" src="${pageContext.request.contextPath}/resources/icon/raster/gray_light/plus_8x8.png" alt="<fmt:message key="add"/>" border="0"/><fmt:message key="add"/></a></li>
								<li><a href="deleteConnessione.do?id=${connessione.id}"><img style="padding-right: 5px; padding-left: 3px;vertical-align: middle;" src="${pageContext.request.contextPath}/resources/icon/raster/gray_light/x_7x7.png" alt="<fmt:message key="del"/>" border="0"/><fmt:message key="del"/></a></li>
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
						<spring:bind path="connessione.*">
  							<c:if test="${status.errors.errorCount gt 0}">
    							<ul>
    								<c:forEach var="error" items="${status.errors.allErrors}">
    									<li><spring:message message="${error}"></spring:message></li>
    								</c:forEach>
    							</ul>
  							</c:if>
						</spring:bind>
						<c:if test="${not empty msg_info}"><div class="info">${msg_info}</div></c:if>
						<c:if test="${not empty msg_ok}"><div class="success">${msg_ok}</div></c:if>
						<c:if test="${not empty msg_warning}"><div class="warning">${msg_warning}</div></c:if>
						<c:if test="${not empty msg_error}"><div class="error">${msg_error}</div></c:if>
						<form id="id_formsubmit" name="formsubmit" method="post" class="forms columnar">
						    <fieldset>
						        <ul>
						            <li>
						            	<spring:bind path="connessione.nome">
						                	<label for="idl_${status.expression}" class="bold">Nome:</label>
											<input id="id_${status.expression}" name="${status.expression}" value="${status.value}" size="40"/>
										</spring:bind>
						            </li>
						            <li>
						            	<spring:bind path="connessione.driver">
						                	<label for="idl_${status.expression}" class="bold">Driver:</label>
											<input id="id_${status.expression}" name="${status.expression}" value="${status.value}" size="40"/>
										</spring:bind>
						            </li>
						            <li>
						            	<spring:bind path="connessione.username">
						                	<label for="idl_${status.expression}" class="bold">Username:</label>
											<input id="id_${status.expression}" name="${status.expression}" value="${status.value}" size="40"/>
										</spring:bind>
						            </li>
						            <li>
						            	<spring:bind path="connessione.password">
						                	<label for="idl_${status.expression}" class="bold">Password:</label>
											<input id="id_${status.expression}" name="${status.expression}" value="${status.value}" size="40"/>
										</spring:bind>
						            </li>
						            <li>
						            	<spring:bind path="connessione.tipo">
						                	<label for="idl_${status.expression}" class="bold">Tipo:</label>
											<input id="id_${status.expression}" name="${status.expression}" value="${status.value}" size="40"/>
										</spring:bind>
						            </li>
						            <li>
						            	<spring:bind path="connessione.dbParam">
						                	<label for="idl_${status.expression}" class="bold">DB Param:</label>
											<input id="id_${status.expression}" name="${status.expression}" value="${status.value}" size="40"/>
										</spring:bind>
						            </li>
						            <li class="push">
						                <input type="submit" name="send" class="btn" value="<fmt:message key="submit_action"/>" />
						            </li>		        		
						        </ul>
						    </fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>