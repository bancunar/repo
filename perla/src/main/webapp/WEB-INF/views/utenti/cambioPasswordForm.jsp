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
						<div id="header-sub-title"><fmt:message key="utente_form_cambio_pass_titolo"/></div>
						<div id="header-sub-action">
							<ul>
								<li><a href="showListaUtenti.do"><img style="padding-right: 5px; padding-left: 3px;vertical-align: middle;" src="${pageContext.request.contextPath}/resources/icon/raster/gray_light/list_8x7.png" alt="<fmt:message key="back_list"/>" border="0"/><fmt:message key="back_list"/></a></li>
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
						<spring:bind path="utente.*">
  							<c:if test="${status.errors.errorCount gt 0}">
  								<div class="warning" style="margin-top: -10px;">
    							<ul style="list-style-type: none;">
    								<c:forEach var="error" items="${status.errors.allErrors}">
    									<li><spring:message message="${error}"></spring:message></li>
    								</c:forEach>
    							</ul>
    							</div>
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
						            	<spring:bind path="utente.id">
											<input id="id_${status.expression}" type="hidden" name="${status.expression}" value="${status.value}" size="40"/>
										</spring:bind>
						            	<spring:bind path="utente.userName">
											<input id="id_${status.expression}" readonly="readonly" type="hidden" name="${status.expression}" value="${status.value}" size="40"/>
										</spring:bind>
										<label for="idl_userName" class="bold">User Name:</label><span class="big gray">${utente.userName}</span>
						            </li>
						            <li>
						            	<label for="idl_vecchiaPass" class="bold">Pass. attuale:</label>
										<input id="id_vecchiaPass" type="text" name="vecchiaPass" value="" size="40"/>
						            </li>
						            <li>
						            	<label for="idl_nuovaPass" class="bold">Pass. nuova:</label>
										<input id="id_nuovaPass" type="text" name="nuovaPass" value="" size="40"/>
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