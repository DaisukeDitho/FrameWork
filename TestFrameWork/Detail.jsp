<%@page import = "java.util.ArrayList" %>
<%@page import = "model.Emp" %>

<%
	ArrayList<Emp> liste = (ArrayList<Emp>)request.getAttribute("Liste_Employee");
%>


<table>
<thead>
	<tr>
	<th>Nom</th>
	</tr>
</thead>
<tbody>
	<%for(int i=0;i<liste.size();i++){%>
		<tr>
		<td><% out.print(liste.get(i).getname()); %></td>
		</tr>	
	<% } %>
</tbody>
</table>