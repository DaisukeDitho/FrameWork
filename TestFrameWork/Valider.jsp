
<%@page import = "java.util.ArrayList" %>
<%@page import = "model.Etudiant" %>

<%
	ArrayList<Etudiant> liste = (ArrayList<Etudiant>)request.getAttribute("Liste_etudiant");
%>


<% for(int i=0;i<liste.size();i++) { %>
    <p>isana instance : <% out.print(liste.get(i).gettest()); %></p>
<% } %>