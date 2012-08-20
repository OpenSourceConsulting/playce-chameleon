<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/taglib/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="packageName" value="com.isacts.jeeip"/>
<c:set var="datePattern"><fmt:message key="date.format"></fmt:message></c:set>
<tiles:insertAttribute name="content" />