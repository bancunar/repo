<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<header id="header">
			<div id="logo"><a href="/"></a></div>
			<a href="#" id="twitter-link">Follow us on Twitter</a>		
			<ul id="auth-area">
				<%-- 
				<li><span>Client office</span></li>
				<li><a href="${pageContext.request.contextPath}/login.do" id="login-link">Log in</a></li>
				<li><a href="#" id="signup-link">Sign up</a></li>
				--%>
				<c:if test="${not empty pageContext.request.userPrincipal.name}">
					<li><a href="cambioPassword.do?user=${pageContext.request.userPrincipal.name}"><img style="padding-right: 5px; padding-left: 3px;vertical-align: middle;" src="${pageContext.request.contextPath}/resources/icon/raster/gray_light/key_stroke_12x12.png"/>${pageContext.request.userPrincipal.name}</a><li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/logout.do" id="login-link">Log out</a><li>
			</ul>
		</header>
		<%--
		<div class="wrapper">
			<nav id="nav">
				<ul><li><a href="/redactor/">Redactor</a></li><li><a href="/kube/">Kube</a></li><li><a href="/about/">About</a></li><li class="last"><a href="/support/">Support</a></li></ul>
			</nav>
			<header id="header-sub">
				<h2><a href="/kube/">Kube</a></h2>
				<ul><li><a href="/kube/">Download</a></li><li><a href="/kube/typo/">Typography</a></li><li><a href="/kube/grid/">Grid</a></li><li><span>Forms</span></li><li><a href="/kube/tables/">Tables</a></li><li><a href="/kube/goodies/">Goodies</a></li><li><a href="/kube/buttons/">Buttons</a></li></ul>
			</header>
		</div>
		--%>
