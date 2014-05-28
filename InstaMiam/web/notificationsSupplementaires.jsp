<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<c:forEach var="c" items="${listeNotificationsLues}">  

    <div class="panel panel-default widget">
        <div class="panel-body">
            <ul class="list-group">
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-md-3">
                            <a href="${pageContext.servletContext.contextPath}/ListeAlbums?idUtilisateurAAfficher=${c.utilisateurNotifieur.id}">
                                <img src="${pageContext.servletContext.contextPath}/profil/${c.utilisateurNotifieur.imageProfil}" class="img-rounded img-responsive " />
                            </a>
                        </div>
                        <div class="col-md-9">
                            <div class="comment-text">
                                <h3 class="ruge pull"><a target="album" href="${pageContext.servletContext.contextPath}/Album?idAlbum=${c.albumCible.id}">${c.albumCible.nomAlbum}</a></h3>
                                    <c:choose>
                                        <c:when test="${c.typeNotification == 1}">
                                        <span class="titrePartageAlbum"></span>
                                    </c:when>
                                    <c:when test="${c.typeNotification == 2}">
                                        <span class="titrePhotoAjout"></span>
                                    </c:when>

                                </c:choose>
                            </div>
                            <div>
                                <div class="mic-info">
                                    By: <a href="${pageContext.servletContext.contextPath}/ListeAlbums?idUtilisateurAAfficher=${c.utilisateurNotifieur.id}">${c.utilisateurNotifieur.prenom} ${c.utilisateurNotifieur.nom}</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>

            </ul>
        </div>
    </div>
</c:forEach>