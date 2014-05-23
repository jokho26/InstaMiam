<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<script src="ressources/js/Albumjs.js"></script>
<script>
    $(function() {
        var availableTags = [
    <c:forEach var="u" items="${listeUtilisateur}">
            {label: "${u.prenom} ${u.nom}", id: "${u.id}"},
    </c:forEach>
                        ""
                    ];
                    $("#tags").autocomplete({
                        source: availableTags,
                        select: function(event, ui) {

                            $.ajax({
                                type: 'POST',
                                url: "${pageContext.servletContext.contextPath}/Album?action=partagerAlbum",
                                data: {action: "partagerAlbum", idAlbum: "${album.id}", idUtilisateur: ui.item.id},
                                dataType: "html",
                                success: function(data, textStatus, jqXHR) {

                                    console.log("data " + data);
                                    console.log("text " + textStatus);
                                    console.log("jqXHR" + jqXHR);
                                    $("#badgesUtilisateurs").html(data);
                                    $("#tags").val("");
                                },
                                error: function(jqXHR, textStatus, errorThrown) {
                                    console.log("Something really bad happened " + textStatus);
                                    console.log("Something really bad happened again" + jqXHR.responseText);
                                },
                                beforeSend: function(jqXHR, settings) {
                                    //disable the button until we get the response
                                    $('#tags').attr("disabled", true);
                                },
                                complete: function(jqXHR, textStatus) {
                                    $('#tags').attr("disabled", false);
                                }

                            });
                        }
                    });
                    function removeFile(file) {
                        $.ajax({
                            url: "${pageContext.servletContext.contextPath}/Album?action=removeFile&idTransaction=${idTransaction}&nameFile=" + file.name
                        });
                    }

    <c:if test="${!empty album.utilisateursPartages}">
        <c:forEach var="p" items="${album.utilisateursPartages}">
                    $("#badgesUtilisateurs").append('<span class="badge pull-left badgeUtilisateur" id="${p.id}">${p.prenom}&nbsp;${p.nom}&nbsp;<span class="glyphicon glyphicon-remove supprimerPartage"></span></span>');
        </c:forEach>
    </c:if>

                });
</script>

<div class="top_div"></div>

<div class="content_div">
    <div class="row">
        <center><h1 class="ruge">${album.nomAlbum}</h1></center>
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
    <!-- Bouton pour faire apparaitre le form modal de modification de photo -->
    <div class="row">
        <div id="modifierPhoto">
            <div class="col-md-3 col-md-offset-9">
                <button class="btn btn-primary" data-toggle="modal" data-target="#myModalModification" >
                    {{tab_lang.album.modifierAlbum}}
                </button>

                <form id="modifierAlbum" action="${pageContext.servletContext.contextPath}/Album">
                    <button type="submit" name="ajouterAlbum" class="btn btn-danger">{{tab_lang.album.supprimer}}</button>
                    <input type="hidden" name="idAlbum" value="${album.id}"/>
                    <input type="hidden" name="action" value="supprimerAlbum"/>
                </form>
            </div>
        </div>
    </div>
    <br><br>

    <!-- Formulaire de test pour l'autocomplétion -->
    <div class="row" id="autoCompletion">
        <div class="col-md-3">
            <h2 class="ruge">{{tab_lang.album.partager_album}}</h2>
            <form class="ui-widget" action="">
                <input id="tags">
            </form>
        </div>
    </div>
    <div class="row" id="autoCompletion">
        <h2 class="ruge" id="titreBadge">{{tab_lang.album.badges_partage}}</h2>
        <div class="col-md-6" id="badgesUtilisateurs"></div>
    </div>
    <br><br>
    <c:set var="count" value="0"/>
    <div id="zoneGallerie">
        <c:forEach var="p" items="${album.photos}">

            <c:choose>
                <c:when test="${count%4 == 0}">
                    <c:if test="${count != 0}">
                    </div>
                </c:if>
                <div class="row">
                </c:when>
            </c:choose>

            <div class="col-md-3 col-sm-4 col-xs-6">
                <center><h2 class="ruge">${p.nom}</h2></center>
                <a href="${pageContext.servletContext.contextPath}/Photo?idPhoto=${p.id}">
                    <img class="img-responsive" src="${pageContext.servletContext.contextPath}/albums/${album.idUnique}/${p.nomFichier}" />
                </a>
                <h3 class="ruge" id="commentaire">${p.commentaires.size()} {{tab_lang.mes_albums.commentaires}}</h3>
            </div>

            <c:set var="count" value="${count+1}"/>  
        </c:forEach> 
    </div>

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
