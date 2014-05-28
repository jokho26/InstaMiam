<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<script>

    var offset = 0;

    function chargerNotifications() {
        $.ajax({
            type: 'POST',
            url: "${pageContext.servletContext.contextPath}/Notifications",
            data: {action: "chargerPlus", offset: offset},
            dataType: "html",
            success: function(data, textStatus, jqXHR) {
                $("#listeNotifications").append(data);
                majTitres();
                offset += 3;
            }
        });
    }


    function majTitres() {
        $(".titrePartageAlbum").each(function(index) {
            $(this).html($(".titrePartageAlbumSource").text());
        });
        $(".titrePhotoAjout").each(function(index) {
            $(this).html($(".titrePhotoAjoutSource").text());
        });
    }

</script> 

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
            <ul id="listeNotifications">
                <c:forEach var="c" items="${listeNotificationsNonLues}">  
                    <script>offset += 1;</script>
                    <div class="panel panel-default widget nouvelleNotif">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <a href="${pageContext.servletContext.contextPath}/ListeAlbums?idUtilisateurAAfficher=${c.utilisateurNotifieur.id}"><img src="${pageContext.servletContext.contextPath}/profil/${c.utilisateurNotifieur.imageProfil}" class="img-rounded img-responsive " /></a>
                                        </div>
                                        <div class="col-md-9">
                                            <div class="comment-text">
                                                <h3 class="ruge pull"><a target="album" href="${pageContext.servletContext.contextPath}/Album?idAlbum=${c.albumCible.id}">${c.albumCible.nomAlbum}</a></h3>
                                                    <c:choose>
                                                        <c:when test="${c.typeNotification == 1}">
                                                        {{tab_lang.notifications.albumPartage}}
                                                    </c:when>
                                                    <c:when test="${c.typeNotification == 2}">
                                                        {{tab_lang.notifications.photoAjoute}}
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
                <c:forEach var="c" items="${listeNotificationsLues}">  
                    <script>offset += 1;</script>
                    <div class="panel panel-default widget">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <a href="${pageContext.servletContext.contextPath}/ListeAlbums?idUtilisateurAAfficher=${c.utilisateurNotifieur.id}">
                                                <img src="${pageContext.servletContext.contextPath}/profil/${c.utilisateurNotifieur.imageProfil}" class="img-rounded img-responsive " />
                                            </a>
                                        </div>
                                        <div class="col-md-9">
                                            <div class="comment-text">
                                                <h3 class="ruge pull"><a target="album" href="${pageContext.servletContext.contextPath}/Album?idAlbum=${c.albumCible.id}">${c.albumCible.nomAlbum}</a></h3>
                                                    <c:choose>
                                                        <c:when test="${c.typeNotification == 1}">
                                                        {{tab_lang.notifications.albumPartage}}
                                                    </c:when>
                                                    <c:when test="${c.typeNotification == 2}">
                                                        {{tab_lang.notifications.photoAjoute}}
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

    <hr>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <center>
                <button class="btn boutonVert" onClick="chargerNotifications()">{{tab_lang.notifications.chargerPlus}}</button>
            </center>
        </div>
    </div>
    <br>

</div>

<div class="bottom_div"></div>

<div class="titrePartageAlbumSource" style="display: none;">{{tab_lang.notifications.albumPartage}}</div>
<div class="titrePhotoAjoutSource" style="display: none;">{{tab_lang.notifications.photoAjoute}}</div>

