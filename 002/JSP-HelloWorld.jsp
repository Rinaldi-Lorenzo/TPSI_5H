<%@ page language="java" %>
<html>
<head>
  <title>Tavola Pitagorica</title>
</head>
<body>
<h1>TAVOLA PITAGORICA - Rinaldi Lorenzo 5H</h1>
<table border=1>
		<%
    	for(int i=1; i<=10; i++)
        {
    		out.println("<tr>");
        	for(int j=1; j<=10; j++)
        		out.println("<td>"+(i*j)+"</td>");
        	out.println("</tr>");
        }%>
</table>
</body>
</html>
