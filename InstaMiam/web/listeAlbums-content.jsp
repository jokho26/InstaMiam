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
            <button type="button" class="btn btn-primary">{{tab_lang.mes_albums.creer_album}}</button>
            <button type="button" class="btn btn-primary">{{tab_lang.mes_albums.ajouter_photo}}</button><br>
            <br><br><br><br>
            
            <ul>
                <c:forEach var="a" items="${requestScope['listeAlbums']}">  
                    <li><a href="${pageContext.servletContext.contextPath}/Album?idAlbum=${a.id}">${a.nomAlbum}</a></li>
                </c:forEach>  
            </ul>
            
            <br><br><br><br>
            
            
            <!-- formulaire de test pour ajouter un album-->
            <form method="POST" action="${pageContext.servletContext.contextPath}/ListeAlbums">
            <input type="text" name="nomAlbum" id="nomAlbum" /> <br/>
            <input type="hidden" name="action" value="ajouterAlbum"/> 
            <input type="submit" value="Ajouter album" name="upload" id="upload" />
            
            <br><br><br><br><br>
            
            <!-- formulaire de test d'upload -->
            <form method="POST" action="${pageContext.servletContext.contextPath}/ListeAlbums" enctype="multipart/form-data" >
            Fichier 1 : 
            <input type="file" name="file" id="file" /> <br/>
            Fichier 2 : 
            <input type="file" name="file" id="file2" /> <br/>
            </br>
            <input type="hidden" name="action" value="upload"/> 
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>
        </div>
        
        <div class="bottom_div">

        </div>
    </div>
    
</div>
