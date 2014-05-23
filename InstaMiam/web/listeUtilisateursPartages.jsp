<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<c:forEach var="p" items="${listeUtilisateursPartages}">
    <span class="badge pull-left badgeUtilisateur" id="${p.id}">${p.prenom}&nbsp;<span class="glyphicon glyphicon-remove supprimerPartage" onclick="supprimerPartage(this.id)"></span></span>
</c:forEach>
