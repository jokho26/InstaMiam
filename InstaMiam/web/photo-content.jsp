<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  



<div class="top_div"></div>

<div class="content_div">
    <center><h1 class="ruge">????</h1></center>
    <br>

    <img src="${pageContext.servletContext.contextPath}/albums/${photo.album.idUnique}/${photo.nomFichier}">

    <br>
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


