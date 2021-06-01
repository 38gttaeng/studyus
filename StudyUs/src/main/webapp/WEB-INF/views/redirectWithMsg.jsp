<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script>
    var msg = "<c:out value='${msg}'/>";
    var redirectUrl = "<c:out value='${redirectUrl}'/>";
    
    alert(msg);
    location.href = redirectUrl;
</script>