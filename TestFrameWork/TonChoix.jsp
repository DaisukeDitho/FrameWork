<%@page import = "java.util.ArrayList" %>
<%@page import = "model.Emp" %>
<%
    ArrayList<Emp> listes=(ArrayList<Emp>)request.getAttribute("Liste_personne");
%>

<body>
    <% for(int i=0;i<listes.size();i++) { %>
        <h1>Vous avez choisi : </h1>
        <% for(int a=0;a<listes.get(i).getoption().length;a++) { %>
            <p><% out.print(listes.get(i).getoption()[a]); %></p>
        <% }
    } %>
</body>