<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  



<div class="top_div"></div>

<div class="content_div">
    
    
    <!-- Bouton pour faire apparaitre le form modal de modification de photo -->
    <div id="modifierPhoto">
        <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" >
            {{tab_lang.photo.modifierPhoto}}
        </button>
    </div>
    
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
                    <form method="POST" action="${pageContext.servletContext.contextPath}/Photo" id="formAjoutAlbum">
                        <input type="text" name="nomPhoto" id="nomPhoto" class="form-control" value="${photo.nom}" required/>
                        <br>
                        <textarea class="form-control">${photo.description}</textarea>
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
    <center><h1 class="ruge">${photo.nom}</h1></center>
    <br>${photo.description}<br><br>
    
    <img src="${pageContext.servletContext.contextPath}/albums/${photo.album.idUnique}/${photo.nomFichier}">

    <br><br><br>
    
    <!-- Partie des commentaires -->
    <ul>
        <c:forEach var="c" items="${photo.commentaires}">  
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default widget">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-2">
                                            <img src="http://placehold.it/80" class="img-circle img-responsive" alt="" />
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
                </div>
            </div>
        </c:forEach>
    </ul>

    <form method="POST" action="${pageContext.servletContext.contextPath}/Photo?idPhoto=${param.idPhoto}">
        <input type="text" name="commentaire" id="commentaire" class="form-control" placeholder="{{tab_lang.album.posterCommentaire}}" required/>
        <input type="hidden" name="action" value="ajouterCommentaire"/>
        <input type="hidden" value="${idPhoto}" name="idPhoto"/>
        <input type="submit" name="submit" value="Poster" />
    </form>

</div>
<div class="bottom_div"></div>


