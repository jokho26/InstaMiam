<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<script src="ressources/js/Albumjs.js"></script>
<script>
    $(function() {
        var availableTags = [
    <c:forEach var="u" items="${listeUtilisateur}">
            "${u.prenom} ${u.nom}",
    </c:forEach>
                        ""
                    ];
                    $("#tags").autocomplete({
                        source: availableTags
                    });
                });

                function removeFile(file) {
                    $.ajax({
                        url: "${pageContext.servletContext.contextPath}/Album?action=removeFile&idTransaction=${idTransaction}&nameFile=" + file.name
                    });
                }
                ;

</script>

<div class="top_div"></div>

<div class="content_div">
    <center><h1 class="ruge">${album.nomAlbum}</h1></center>
    
    <!-- Bouton pour faire apparaitre le form modal de modification de photo -->
    <div id="modifierPhoto">
        <button class="btn btn-primary" data-toggle="modal" data-target="#myModalModification" >
            {{tab_lang.album.modifierAlbum}}
        </button>
        <form id="modifierAlbum" action="${pageContext.servletContext.contextPath}/Album">
            <button type="submit" name="ajouterAlbum" class="btn btn-danger">{{tab_lang.album.supprimer}}</button>
            <input type="hidden" name="idAlbum" value="${album.id}"/>
            <input type="hidden" name="action" value="supprimerAlbum"/>
        </form>
    </div>
            
    <!-- Modal de modification d'information de l'album -->
    <div class="modal fade" id="myModalModification" tabindex="-1" role="dialog" aria-labelledby="myModalModification" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">{{tab_lang.album.modifierAlbum}}</h4>
                </div>
                <div class="modal-body">

                    <!-- formulaire de test pour ajouter un album-->
                    <form method="POST" action="${pageContext.servletContext.contextPath}/Album">
                        <input type="text" name="nomAlbum" id="nomAlbum" class="form-control" value="${album.nomAlbum}" required/>
                        <br>
                        <input type="hidden" name="idAlbum" value="${album.id}"/>
                        <input type="hidden" name="action" value="modifierAlbum"/>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" name="ajouterAlbum" class="btn btn-default">{{tab_lang.album.valider_modification}}</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <br><br>

    <!-- Formulaire de test pour l'autocomplétion -->
    <form class="ui-widget" action="">
        <input id="tags">
        <input type="button" name="submit" value="Ajouter" onClick="ajouterUtilisateurAlbum();"/>
    </form>

    <ul>
        <c:forEach var="p" items="${album.photos}">  
            <li><a href="${pageContext.servletContext.contextPath}/Photo?idPhoto=${p.id}">${p.nom}</a></li>
            </c:forEach>
    </ul>
    <br>

    <!-- formulaire de test d'upload -->
    <form method="POST" action="${pageContext.servletContext.contextPath}/Album?action=uploadFile&idTransaction=${idTransaction}" 
          enctype="multipart/form-data" class="dropzone" id="my-awesome-dropzone">
    </form>

    <!-- Formulaire de validation -->
    <form method="POST" action="${pageContext.servletContext.contextPath}/Album">
        <input type="submit" value="Upload" name="btnUpload" id="upload" />
        <input type="hidden" value="${idAlbum}" name="idAlbum" id="upload" />
        <input type="hidden" name="action" value="validUpload"/>
        <input type="hidden" name="idTransaction" value="${idTransaction}"/>
    </form>
    <br>

    <!-- Partie des commentaires -->
    <ul>
        <c:forEach var="c" items="${album.commentaires}">  
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

    <form method="POST" action="${pageContext.servletContext.contextPath}/Album">
        <input type="text" name="commentaire" id="commentaire" class="form-control" placeholder="{{tab_lang.album.posterCommentaire}}" required/>
        <input type="hidden" name="action" value="ajouterCommentaire"/>
        <input type="hidden" value="${idAlbum}" name="idAlbum"/>
        <input type="submit" name="submit" value="Poster" />
    </form>


</div>
<div class="bottom_div"></div>
