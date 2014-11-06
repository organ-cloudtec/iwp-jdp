<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="false"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber() + 1;
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());


request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>

<div class="pagination">
		 <% if (page.hasPreviousPage()){%>
               	<li><a onclick="page('1','${pageSize}')">&lt;&lt;</a></li>
                <li><a onclick="page('${current-1}','${pageSize}')">&lt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a onclick="page('${i}','${pageSize}')">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a onclick="page('${i}','${pageSize}')">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNextPage()){%>
               	<li><a onclick="page('${current+1}','${pageSize}')">&gt;</a></li>
                <li><a onclick="page('${page.totalPages}','${pageSize}')">&gt;&gt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
         <%} %>

</div>

