<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  



<div class="top_div"></div>

<div class="content_div">

    <div class="row decale">
        <div class="col-md-4">
            <c:choose>
                <c:when test="${sessionScope.utilisateurConnecte == photo.album.utilisateur.id}">
                    <c:if test="${empty photo.albumCouvert}"><a type="button" href="${pageContext.servletContext.contextPath}/Photo?action=definirCouverture&idPhoto=${photo.id}" class="btn btn-sm boutonVert">{{tab_lang.photo.definirPhotoCouverture}}</a></c:if>
                </c:when>
            </c:choose>
        </div>
        <div class="col-md-4">
            <center><h1 class="ruge MOAR">${photo.nom}&nbsp;<c:if test="${!empty photo.albumCouvert}">{{tab_lang.photo.couverture}}</c:if></h1></center>
            </div>
        <c:choose>
            <c:when test="${sessionScope.utilisateurConnecte == photo.album.utilisateur.id}">
                <div class="col-md-4">
                    <div class="btn-group groupeBouton">
                        <button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-sm boutonVert">{{tab_lang.photo.modifierPhoto}}</button>
                        <a type="button" href="${pageContext.servletContext.contextPath}/Photo?action=supprimerPhoto&idPhoto=${photo.id}" class="btn btn-sm boutonViolet">{{tab_lang.photo.supprimer}}</a>
                    </div> 
                </div>
            </c:when>
        </c:choose>

    </div>

    <div class="row decale">
        <div class="col-md-12">
            <center><h3>${photo.description}</h3></center>
        </div>
    </div>

    <c:choose>
        <c:when test="${sessionScope.utilisateurConnecte == photo.album.utilisateur.id}">
            <!-- Modal de modification d'information de la photo -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">

                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h2 class="modal-title ruge" id="myModalLabel">{{tab_lang.photo.modifierPhoto}}</h2>
                        </div>
                        <div class="modal-body">

                            <!-- formulaire de test pour ajouter un album-->
                            <form method="POST" action="${pageContext.servletContext.contextPath}/Photo">
                                <h2 class="ruge">{{tab_lang.photo.nomPhoto}}</h2>
                                <input type="text" name="nomPhoto" id="nomPhoto" class="form-control" value="${photo.nom}" required/>
                                <br>
                                <h2 class="ruge">{{tab_lang.photo.descriptionPhoto}}</h2>
                                <textarea class="form-control" name="description">${photo.description}</textarea>
                                <input type="hidden" name="idPhoto" value="${photo.id}"/>
                                <input type="hidden" name="action" value="modifierPhoto"/>
                        </div>

                        <div class="modal-footer">
                            <div class="btn-group">
                                <button class="btn boutonVert" data-dismiss="modal">Close</button>
                                <button type="submit" name="ajouterAlbum" class="btn boutonViolet">{{tab_lang.photo.valider_modification}}</button>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


        </c:when>
    </c:choose>

    <br><br><br>
    <div class="row">
        <div class="col-md-12">
            <center><img src="${pageContext.servletContext.contextPath}/albums/${photo.album.idUnique}/${photo.nomFichier}" class="imageMiam img-rounded"></center>
        </div>
    </div>
    <br><br><br>

    <div class="row">
        <div class="col-md-6">
            <!-- Partie des commentaires -->
            <ul>
                <c:forEach var="c" items="${photo.commentaires}">  

                    <div class="panel panel-default widget">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <img src="${pageContext.servletContext.contextPath}/profil/${c.auteur.imageProfil}" class="img-rounded img-responsive " />
                                        </div>
                                        <div class="col-md-4">
                                            <div class="comment-text">
                                                ${c.text}
                                            </div>
                                            <div>
                                                <div class="mic-info">
                                                    By: <a href="${pageContext.servletContext.contextPath}/ListeAlbums?idUtilisateurAAfficher=${c.auteur.id}">${c.auteur.prenom} ${c.auteur.nom}</a>
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
    <div class="row decale">
        <div class="col-md-6">
            <form method="POST" action="${pageContext.servletContext.contextPath}/Photo?idPhoto=${param.idPhoto}">
                <input type="text" name="commentaire" id="commentaire" class="form-control" placeholder="{{tab_lang.album.posterCommentaire}}" required/>
                <input type="hidden" name="action" value="ajouterCommentaire"/>
                <input type="hidden" value="${idPhoto}" name="idPhoto"/>
                <input type="submit" class="btn boutonVert" name="submit" value="Poster" id="posterCommentaire"/>
            </form>
        </div>
    </div>

</div>
<div class="bottom_div"></div>


