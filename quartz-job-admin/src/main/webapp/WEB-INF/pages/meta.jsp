<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="defaultPageSize" value="10"></c:set>
<c:set var="defaultPageList" value="[10,20,30]"></c:set>
<!--  全局 -->
<script>
var G_CTX_PATH = "${ctx}";
var G_CTX_FILE_PATH = "${ctx}/file";
</script>

