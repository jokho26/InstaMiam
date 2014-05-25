<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  



<div class="top_div"></div>

<div class="content_div">

    <!-- Modal de modification d'information de la photo -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">{{tab_lang.photo.modifierPhoto}}</h4>
                </div>
                <div class="modal-body">

                    <!-- formulaire de test pour ajouter un album-->
                    <form method="POST" action="${pageContext.servletContext.contextPath}/Photo">
                        <input type="text" name="nomPhoto" id="nomPhoto" class="form-control" value="${photo.nom}" required/>
                        <br>
                        <textarea class="form-control" name="description">${photo.description}</textarea>
                        <input type="hidden" name="idPhoto" value="${photo.id}"/>
                        <input type="hidden" name="action" value="modifierPhoto"/>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" name="ajouterAlbum" class="btn btn-default">{{tab_lang.photo.valider_modification}}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row decale">
        <center><h1 class="ruge">${photo.nom}&nbsp;<c:if test="${!empty photo.albumCouvert}">{{tab_lang.photo.couverture}}</c:if></h1>
            <br><h3>${photo.description}</h3></center><br>
    </div>

    <div class="row decale">
        <div class="col-md-6 col-md-offset-3">
            <!-- Bouton pour faire apparaitre le form modal de modification de photo -->
            <div id="modifierPhoto">
                <center>
                    <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" >
                        {{tab_lang.photo.modifierPhoto}}
                    </button>

                    <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/Photo?action=definirCouverture&idPhoto=${photo.id}">
                        {{tab_lang.photo.definirPhotoCouverture}}
                    </a>

                    <form id="modifierPhoto" action="${pageContext.servletContext.contextPath}/Photo">
                        <button type="submit" name="ajouterAlbum" class="btn btn-danger">{{tab_lang.photo.supprimer}}</button>
                        <input type="hidden" name="idPhoto" value="${photo.id}"/>
                        <input type="hidden" name="action" value="supprimerPhoto"/>
                    </form>
                </center>
            </div>
        </div>
    </div>

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
                                                    By: <a href="#">${c.auteur.prenom} ${c.auteur.nom}</a>
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
                <input type="submit" name="submit" value="Poster" id="posterCommentaire"/>
            </form>
        </div>
    </div>

</div>
<div class="bottom_div"></div>


