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
					<jsp:include page="layout/leftSide.jsp"></jsp:include>
					<div class="divdx">
						<div id="header-sub-title">DATA ACCESS FAILURE</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>