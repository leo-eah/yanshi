<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>万达企业服务</title>
    <meta name="viewport" content="width=device-width,initial-scake=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><!-- //For-Mobile-Apps-and-Meta-Tags -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/fileinput.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/public.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/fileinput.js"></script>
    <script src="<%=request.getContextPath()%>/js/zh.js"></script>
</head>
<input type="hidden" id="proName" value=${proName}>
<body id="aa">

</body>
<script>
    $(function () {
        var url="<%=request.getContextPath()%>/lookPro/openIndex?proName="+$("#proName").val();
        $("#aa").load(url);
    })
</script>
</html>