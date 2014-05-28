<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<div class="top_div"></div>

<div class="content_div">
    <div class="row">
        <div class="col-md-6">
            <!-- Partie des notifications non lues -->
            <ul>
                <c:forEach var="c" items="${listeNotificationsNonLues}">  

                    <div class="panel panel-default widget">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <img src="${pageContext.servletContext.contextPath}/profil/${c.utilisateurNotifieur.imageProfil}" class="img-rounded img-responsive " />
                                        </div>
                                        <div class="col-md-4">
                                            <div class="comment-text">
                                                <c:choose>
                                                    <c:when test="${c.typeNotification == 1}">
                                                        {{tab_lang.notifications.albumPartage}}
                                                    </c:when>
                                                    <c:when test="${c.typeNotification == 2}">
                                                        {{tab_lang.notifications.photoAjoute}}
                                                        <br>
                                                        <a href=""></a>
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
            </ul> 
        </div>
    </div> 
</div>

<div class="bottom_div"></div>
