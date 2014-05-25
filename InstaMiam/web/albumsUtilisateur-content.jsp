<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<script src="ressources/js/ListeAlbums.js"></script>
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
        $("#tags").autocomplete("option", "appendTo", ".myModal");
</script>

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
            
           
            <c:set var="count" value="0"/>
            <div id="zoneGallerie">
                <c:forEach var="a" items="${requestScope['listeAlbumsVisibles']}">

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
                            <img class="img-responsive" src="http://2.bp.blogspot.com/-H6MAoWN-UIE/TuRwLbHRSWI/AAAAAAAABBk/89iiEulVsyg/s400/Free%2BNature%2BPhoto.jpg" />
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
