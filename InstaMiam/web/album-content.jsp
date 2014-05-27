<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<c:choose>
    <c:when test="${sessionScope.utilisateurConnecte == album.utilisateur.id}">
        <script src="ressources/js/Albumjs.js"></script>

        <script>

            function supprimerPartage(elm) {
                $.ajax({
                    type: 'POST',
                    url: "${pageContext.servletContext.contextPath}/Album",
                    data: {action: "supprimerPartage", idAlbum: "${album.id}", idUtilisateur: elm.parentNode.id},
                    dataType: "html",
                    success: function(data, textStatus, jqXHR) {
                        $("#badgesUtilisateurs").html(data);
                    },
                    beforeSend: function(jqXHR, settings) {
                        $('#tags').attr("disabled", true);
                    },
                    complete: function(jqXHR, textStatus) {
                        $('#tags').attr("disabled", false);
                    }

                });
            }
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
                                        url: "${pageContext.servletContext.contextPath}/Album",
                                        data: {action: "partagerAlbum", idAlbum: "${album.id}", idUtilisateur: ui.item.id},
                                        dataType: "html",
                                        success: function(data, textStatus, jqXHR) {
                                            $("#badgesUtilisateurs").html(data);
                                            $("#tags").val("");
                                        },
                                        error: function(jqXHR, textStatus, errorThrown) {
                                            //console.log("Something really bad happened " + textStatus);
                                            //console.log("Something really bad happened again " + jqXHR.responseText);
                                            $("#tags").val("");
                                            //$('.popOverMiam').popover('show')

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
                            $("#badgesUtilisateurs").append('<span class="badge pull-left badgeUtilisateur" id="${p.id}">${p.prenom}&nbsp;${p.nom}&nbsp;<span class="glyphicon glyphicon-remove supprimerPartage" onclick="supprimerPartage(this)"></span></span>');
                </c:forEach>
            </c:if>

                        });


        </script>

    </c:when>
</c:choose>

<div class="top_div"></div>

<div class="content_div">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <center><h1 class="ruge MOAR">${album.nomAlbum}</h1></center>
        </div>
        <c:choose>
            <c:when test="${sessionScope.utilisateurConnecte == album.utilisateur.id}">
                <div class="col-md-4">
                    <div class="btn-group groupeBouton">
                        <button type="button" data-toggle="modal" data-target="#myModalModification" class="btn btn-sm boutonVert">{{tab_lang.album.modifierAlbum}}</button>
                        <a type="button" href="${pageContext.servletContext.contextPath}/Album?action=supprimerAlbum&idAlbum=${album.id}" class="btn btn-sm boutonViolet">{{tab_lang.album.supprimer}}</a>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </div>


    <c:choose>
        <c:when test="${sessionScope.utilisateurConnecte == album.utilisateur.id}">

            <!-- Modal de modification d'information de l'album -->
            <div class="modal fade" id="myModalModification" tabindex="-1" role="dialog" aria-labelledby="myModalModification" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h2 class="modal-title ruge" id="myModalLabel">{{tab_lang.album.modifierAlbum}}</h2>
                        </div>
                        <div class="modal-body">

                            <!-- formulaire de test pour ajouter un album-->
                            <form method="POST" action="${pageContext.servletContext.contextPath}/Album">
                                <h2 class="ruge">{{tab_lang.album.nomAlbum}}</h2>
                                <input type="text" name="nomAlbum" id="nomAlbum" class="form-control" value="${album.nomAlbum}" required/>
                                <input type="hidden" name="idAlbum" value="${album.id}"/>
                                <input type="hidden" name="action" value="modifierAlbum"/>
                        </div>
                        <!-- Formulaire de test pour l'autocomplétion -->
                        <div class="row decale">
                            <div class="col-md-12">
                                <h2 class="ruge">{{tab_lang.album.partager_album}}</h2>
                                <input id="tags">
                            </div>
                        </div>
                        <div class="row decale">
                            <h2 class="ruge" id="titreBadge">{{tab_lang.album.badges_partage}}</h2>
                            <div class="col-md-12" id="badgesUtilisateurs"></div>
                            <br><br>
                        </div>

                        <div class="modal-footer">
                            <div class="btn-group">
                                <button class="btn boutonVert" data-dismiss="modal">{{tab_lang.album.fermerModale}}</button>
                                <button type="submit" name="ajouterAlbum" class="btn boutonViolet">{{tab_lang.album.valider_modification}}</button>
                            </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <br><br>



            <br><br>


        </c:when>
    </c:choose>
    <!-- Mosaique de photos de l'album -->
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
                <center><h2 class="ruge"></h2></center>
                <a href="${pageContext.servletContext.contextPath}/Photo?idPhoto=${p.id}">
                    <img class="img-responsive imageMosaique" src="${pageContext.servletContext.contextPath}/albums/${album.idUnique}/${p.nomFichier}" rel="popover" data-html="true" data-placement="top" data-content="<h3 class='ruge'>Description :</h3>${p.description}<br><h4 class='ruge'>${p.commentaires.size()} {{tab_lang.mes_albums.commentaires}}<c:if test="${p.commentaires.size() != 1}">s</c:if></h4>" data-trigger="hover" data-original-title="<h2 class='ruge'>${p.nom}</h2>"/>
                </a>
            </div>

            <c:set var="count" value="${count+1}"/>  
        </c:forEach> 
        <c:if test="${count != 0}"></div></c:if>

    </div>

    <br><br><br><br><br>
<c:choose>
    <c:when test="${sessionScope.utilisateurConnecte == album.utilisateur.id}">
        <!-- formulaire d'upload -->
        <form method="POST" action="${pageContext.servletContext.contextPath}/Album?action=uploadFile&idTransaction=${idTransaction}" 
              enctype="multipart/form-data" class="dropzone" id="my-awesome-dropzone">
        </form>

        <form method='post' action="${pageContext.servletContext.contextPath}/Album?idAlbum=${idAlbum}&action=validUpload" id='dummyForm'>
            <input type='hidden' value='${idTransaction}' name="idTransaction">
            <input type="submit" class="btn boutonViolet" name="btnUpload" id="upload" value="{{tab_lang.album.upload}}">
        </form>

        <!-- Formulaire de validation -->


        <br><br><br>
    </c:when>
</c:choose>

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
                                        <img src="${pageContext.servletContext.contextPath}/profil/${c.auteur.imageProfil}" class="img-rounded img-responsive " alt="" />
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
            </div>
        </div>
    </c:forEach>
</ul>
<div class="row decale">
    <div class="col-md-6">
        <form method="POST" action="${pageContext.servletContext.contextPath}/Album">
            <input type="text" name="commentaire" id="commentaire" class="form-control" placeholder="{{tab_lang.album.posterCommentaire}}" required/>
            <input type="hidden" name="action" value="ajouterCommentaire"/>
            <input type="hidden" value="${idAlbum}" name="idAlbum"/>
            <input type="submit" class="btn boutonVert" name="submit" value="Poster" id="posterCommentaire"/>
        </form>
    </div>
</div>
</div>


<div class="bottom_div"></div>
<br>


<img id="displayBox" src="ressources/img/default.png" width="200" height="200" style="display:none" />