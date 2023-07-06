
<%@page import = "java.util.ArrayList" %>
<%@page import = "model.Emp" %>

<%
	ArrayList<Emp> liste = (ArrayList<Emp>)request.getAttribute("liste");
%>



<table>
<thead>
	<tr>
	<th>Nom</th>
	<th></th>
	</tr>
</thead>
<tbody>
	<%for(int i=0;i<liste.size();i++){
	out.print(liste.size());%>
		<tr>
		<td><% out.print(liste.get(i).getname()); %></td>
		<td><a href="detail?id=<% out.print(liste.get(i).getid()); %>" >Detail</a></td>
		</tr>	
	<% } %>
</tbody>
</table>
