

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="cf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cs" uri="http://www.springframework.org/tags" %> 
<%@ taglib prefix="ct" uri="http://java.sun.com/jsp/jstl/core" %>

 <%@ include file="/website/roles/slider.jsp" %>
 <%@ include file="/website/roles/top-menu.jsp" %>
 <div class="row">
                            <div class="col-lg-12">
                                <a href="#menu-toggle" class="vertical-menu-position-btn" id="menu-toggle"> <i class="fa fa-bars"></i> <span class="orange-font">Welcome ${userName} </span> </a>
                            </div>
                        </div>
<div style="height:70px;"></div>
<h3 style="margin-top: 160px;
    margin-left: 435px;">
<strong>
Registeration successful !!!
</strong>
<br><br>
UserId id : ${id } and password is ${pwd}
</h3>
<div style="height:70px;"></div>
