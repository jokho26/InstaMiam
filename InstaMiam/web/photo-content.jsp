<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  



<div class="row">
    <div  class="col-md-12">

    </div>

</div>

<div class="row">

    <div  class="col-md-12">
        <div class="top_div"></div>

        <div class="content_div">
            <center><h1 class="ruge">????</h1></center>
            <br><br>
            <!-- Partie des commentaires -->
            <ul>
                <c:forEach var="c" items="${photo.commentaires}">  
                    <li>${c.auteur.prenom} ${c.auteur.nom} : ${c.text}</li>
                </c:forEach>
            </ul>
            
            <form method="POST" action="${pageContext.servletContext.contextPath}/Photo">
                <input type="text" name="commentaire" id="commentaire" class="form-control" placeholder="{{tab_lang.album.posterCommentaire}}" required/>
                <input type="hidden" name="action" value="ajouterCommentaire"/>
                <input type="hidden" value="${idPhoto}" name="idPhoto"/>
                <input type="submit" name="submit" value="Poster" />
            </form>
            
        </div>
        <div class="bottom_div"></div>
    </div>

</div>
