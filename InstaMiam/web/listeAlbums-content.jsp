<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<div class="top_div"></div>

<div class="content_div">

    <c:choose>
        <c:when test="${idUtilisateurAAfficher == null || sessionScope.utilisateurConnecte == idUtilisateurAAfficher}">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <center><h1 class="ruge MOAR">{{tab_lang.mes_albums.titre}}</h1></center> 
                </div>
                <div class="col-md-4">
                    <!-- Bouton pour faire apparaitre le form modal de création d'album -->
                    <div id="btn_nouvel_album" class="groupeBouton">
                        <span class="glyphicon glyphicon-plus violet"></span>
                        <button class="btn boutonVert" data-toggle="modal" data-target="#myModal" >
                            {{tab_lang.mes_albums.nouvel_album}}
                        </button>
                    </div>
                </div>
            </div>
            <br><br><br>

            <!-- Modal de création d'album -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">{{tab_lang.mes_albums.ajouter_nouvel_album}}</h4>
                        </div>
                        <div class="modal-body">

                            <!-- formulaire de test pour ajouter un album-->
                            <form method="POST" action="${pageContext.servletContext.contextPath}/ListeAlbums" id="formAjoutAlbum">
                                <input type="text" name="nomAlbum" id="nomAlbum" class="form-control" placeholder="{{tab_lang.mes_albums.nomAlbum}}" required/>
                                <br>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="typePartage" id="optionsRadios1" value="private" checked>
                                        {{tab_lang.mes_albums.albumPrive}}
                                    </label>
                                </div>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="typePartage" id="optionsRadios2" value="public">
                                        {{tab_lang.mes_albums.albumPublique}}
                                    </label>
                                </div>
                                <input type="hidden" name="action" value="ajouterAlbum"/>
                        </div>

                        <div class="modal-footer">
                            <button class="btn boutonVert" data-dismiss="modal">Close</button>
                            <span class="glyphicon glyphicon-plus vert"></span>
                            <button type="submit" name="ajouterAlbum" id="ajouterAlbum" class="btn boutonViolet">{{tab_lang.mes_albums.creer_album}}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <center><h1 class="ruge MOAR">{{tab_lang.mes_albums.albumsDe}} : ${nomUtilisateurAAfficher}</h1></center>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

    <!-- Mosaique de photos de l'album -->
    <c:set var="count" value="0"/>
    <div id="zoneGallerie">
        <c:forEach var="a" items="${requestScope['listeAlbums']}">
            <c:choose>
                <c:when test="${count%4 == 0}">
                    <c:if test="${count != 0}">
                    </div>
                </c:if>
                <div class="row">
                </c:when>
            </c:choose>

            <div class="col-md-3 col-sm-4 col-xs-6">
                <a href="${pageContext.servletContext.contextPath}/Album?idAlbum=${a.id}">
                    <c:choose>
                        <c:when test="${!empty a.photoDeCouverture}">
                            <img class="img-responsive imageMosaique" src="${pageContext.servletContext.contextPath}/albums/${a.idUnique}/${a.photoDeCouverture.nomFichier}" rel="popover" data-html="true" data-placement="top" data-content="<h3 class='ruge'>${a.commentaires.size()} {{tab_lang.mes_albums.commentaires}}</h3>" data-trigger="hover" data-original-title="<h2 class='ruge'>${a.nomAlbum}</h2>"/>
                        </c:when>
                        <c:otherwise>
                            <img class="img-responsive imageMosaique" src="${pageContext.servletContext.contextPath}/profil/default.jpg" rel="popover" data-html="true" data-placement="top" data-content="<h3 class='ruge'>${a.commentaires.size()} {{tab_lang.mes_albums.commentaires}}</h3>" data-trigger="hover" data-original-title="<h2 class='ruge'>${a.nomAlbum}</h2>"/>
                        </c:otherwise>
                    </c:choose>
                </a>
            </div>

            <c:set var="count" value="${count+1}"/>  
        </c:forEach> 
        <c:if test="${count != 0}"></div></c:if>

</div>

</div>

<div class="bottom_div"></div>
