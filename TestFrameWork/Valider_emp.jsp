<%@page import = "java.util.ArrayList" %>
<%@page import = "model.Emp" %>

<%
	ArrayList<Emp> liste = (ArrayList<Emp>)request.getAttribute("Liste_emp");
%>


<% for(int i=0;i<liste.size();i++) { %>
    <p>isana instance : <% out.print(liste.get(i).gettest()); %></p>
<% } %>