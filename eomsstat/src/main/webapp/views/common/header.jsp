<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>





<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/themes.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-select.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jquery.contextMenu.css" rel="stylesheet">

<!-- css 目录 -->
<c:set var="cssPath" value="${pageContext.request.contextPath}/resources/css" />  
<!-- js 目录 -->
<c:set var="jsPath" value="${pageContext.request.contextPath}/resources/js" />  
<!-- image 目录 -->
<c:set var="imagePath" value="${pageContext.request.contextPath}/resources/images" />  


<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js"></script>
<script  src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script  src="${pageContext.request.contextPath}/resources/js/bootstrap-paginator.js"></script>
<script  src="${pageContext.request.contextPath}/resources/js/jquery-leanModal.min.js"></script>
<script  src="${pageContext.request.contextPath}/resources/js/jquery.validate.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>



<script  src="${pageContext.request.contextPath}/resources/js/eoms-statistic-pageComponents.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/eoms-statistic-pageComponents-commonRep.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/eoms-statistic-pageComponents-myReport.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/eoms-statistic-pageComponents-dataList.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/eoms-statistic-pageComponents-share.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/eoms-statistic-pageComponents-myShare.js" charset="utf-8"></script>


<script  src="${pageContext.request.contextPath}/resources/js/eoms-statistic-dialog.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/eoms-statistic-common.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/eoms-statistic-groupType.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/bootstrap-treeview.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/jquery.contextMenu.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/js/FusionCharts.js" charset="utf-8"></script>
<script  src="${pageContext.request.contextPath}/resources/component/laydate/laydate.js" charset="utf-8"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=edge" />
<meta name='renderer' content='webkit' /> 
