<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<div class="row">
    <div  class="col-md-12">

    </div>

</div>

<div class="row">

    <div  class="col-md-12">
        <div class="top_div">

        </div>

        <div class="content_div">
            <center><h1 class="ruge">{{tab_lang.mes_albums.titre}}</h1></center> 
            
            <!-- Bouton pour faire apparaitre le form modal de création d'album -->
            <div id="btn_nouvel_album">
                <span class="glyphicon glyphicon-plus"></span>
                <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" >
                    {{tab_lang.mes_albums.nouvel_album}}
                </button>
            </div>
            
            <!-- test pour l'autocomplétion -->

            
            
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
                                <button class="btn btn-default" data-dismiss="modal">Close</button>
                                <span class="glyphicon glyphicon-plus"></span>
                                <button type="submit" name="ajouterAlbum" id="ajouterAlbum" class="btn btn-default">{{tab_lang.mes_albums.creer_album}}</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

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
                        <center><h2 class="ruge">${a.nomAlbum}</h2></center>
                        <a href="${pageContext.servletContext.contextPath}/Album?idAlbum=${a.id}">
                            <img class="img-responsive" src="${pageContext.servletContext.contextPath}/albums/${a.idUnique}/${a.photoDeCouverture.nomFichier}" />
                        </a>
                        <h3 class="ruge" id="commentaire">${a.commentaires.size()} {{tab_lang.mes_albums.commentaires}}</h3>
                    </div>

                    <c:set var="count" value="${count+1}"/>  
                </c:forEach> 
            </div>
        </div>


        <br><br>
    </div>

    <div class="bottom_div">

    </div>
</div>

</div>
