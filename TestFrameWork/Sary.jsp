<@page import="java.util.ArrayList" %>
<@page import="model.Emp" %>
<%
    ArrayList<Emp> listes=(ArrayList<Emp>)request.getAttribute("Liste_personne");
%>

<body>
    <% for(int i=0;i<listes.size();i++) { %>
        <h1>Vous avez importe : </h1>
        <p><% out.print(listes.get(i).getsary().getname()); %></p>
        <p><% out.print(listes.get(i).getsary().getbinaire().length); %>kb</p>
    <% } %>
</body>