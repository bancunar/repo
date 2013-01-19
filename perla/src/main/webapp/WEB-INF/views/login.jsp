<%@page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>
<%@page import="org.springframework.security.core.AuthenticationException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- @ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" --%>
<%-- @ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

	<jsp:include page="layout/header.jsp"></jsp:include>
	<body class="bodypage">
		<jsp:include page="layout/subHeader.jsp"></jsp:include>
		<div id="box-inside-white">
			<div class="wrapper">
				<div class="row">
					<div class="third centered">
						<div class="box-inner">
							<div id="header-sub-title" style="margin-bottom: 10px;">
								<img style="padding-right: 5px; padding-left: 3px;vertical-align: middle;" src="${pageContext.request.contextPath}/resources/icon/raster/gray_light/lock_stroke_9x12.png" alt="Login" border="0"/>Login</div>
							<c:if test="${not empty error}">
								<c:if test="${not error}">
									<div class="warning"><fmt:message key="login_error"/><br><%= ((AuthenticationException) session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %></div></c:if>
							</c:if>
							<form id="loginFormPlace" action="${pageContext.request.contextPath}/j_spring_security_check" method="post" class="forms end" style="width: 80%; margin: auto;">
								<ul>
									<li>
										<label>Username</label>
										<input type="text" name="j_username" id="j_username" class="width-100" />
									</li>
									<li>
										<label>Password</label>
										<input type="password" name="j_password" id="j_password" class="width-100" />
									</li>
									<li>
				    					<button name="send" id="login-place-btn" class="btn">Accedi</button>
									</li>					
									<li>
										<a href="/password-recovery/" class="small gray">Hai deimenticato la password?</a>
									</li>
									<li>
										<p class="small gray">Per problemi con l'accesso al sistema contattaci supporto@perla.com</p>
									</li>
								</ul>
							</form>
						</div>		
					</div>
				</div>
			</div>
		</div>
	</body>
</html>