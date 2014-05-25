<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<c:forEach var="p" items="${listeUtilisateursPartages}">
    <span class="badge pull-left badgeUtilisateur" id="${p.id}">${p.prenom}&nbsp;${p.nom}&nbsp;<span class="glyphicon glyphicon-remove supprimerPartage" onclick="supprimerPartage(this)"></span></span>
</c:forEach>
