<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<div class="top_div"></div>

<div class="content_div">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <center>
                <h1 class="ruge MOAR">
                    {{tab_lang.notifications.titre}}
                </h1>
            </center>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <center>
                <!-- Partie des notifications non lues -->
                <ul>
                    <c:forEach var="c" items="${listeNotificationsNonLues}">  

                        <div class="panel panel-default widget nouvelleNotif">
                            <div class="panel-body">
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <img src="${pageContext.servletContext.contextPath}/profil/${c.utilisateurNotifieur.imageProfil}" class="img-rounded img-responsive " />
                                            </div>
                                            <div class="col-md-9">
                                                <div class="comment-text">
                                                    <c:choose>
                                                        <c:when test="${c.typeNotification == 1}">
                                                            {{tab_lang.notifications.albumPartage}}
                                                        </c:when>
                                                        <c:when test="${c.typeNotification == 2}">
                                                            {{tab_lang.notifications.photoAjoute}}
                                                        </c:when>

                                                    </c:choose>
                                                    <br>
                                                    <a target="album" href="${pageContext.servletContext.contextPath}/Album?idAlbum=${c.albumCible.id}">${c.albumCible.nomAlbum}</a>
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
                    <c:forEach var="c" items="${listeNotificationsLues}">  

                        <div class="panel panel-default widget">
                            <div class="panel-body">
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <img src="${pageContext.servletContext.contextPath}/profil/${c.utilisateurNotifieur.imageProfil}" class="img-rounded img-responsive " />
                                            </div>
                                            <div class="col-md-9">
                                                <div class="comment-text">
                                                    <c:choose>
                                                        <c:when test="${c.typeNotification == 1}">
                                                            {{tab_lang.notifications.albumPartage}}
                                                        </c:when>
                                                        <c:when test="${c.typeNotification == 2}">
                                                            {{tab_lang.notifications.photoAjoute}}
                                                        </c:when>

                                                    </c:choose>
                                                    <br>
                                                    <a target="album" href="${pageContext.servletContext.contextPath}/Album?idAlbum=${c.albumCible.id}">${c.albumCible.nomAlbum}</a>
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
                </ul> 
            </center>
        </div>
    </div> 
    <br>

</div>

<div class="bottom_div"></div>
