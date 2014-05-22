<%@page import="java.util.List"%>
<%@page import="modeles.Utilisateur"%>
<% for(Utilisateur u : (List<Utilisateur>) request.getAttribute("listeUtilisateursPartages")) {%>
    <span class="badge pull-left" id="${p.id}">${p.prenom}&nbsp;${p.nom}</span>
<% } %>