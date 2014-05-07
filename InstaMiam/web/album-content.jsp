<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  



<div class="row">
    <div  class="col-md-12">
       
    </div>
    
</div>

<div class="row">

    <div  class="col-md-12">
        <div class="top_div"></div>
        
        <div class="content_div">
            <center><h1 class="ruge">${album.nomAlbum}</h1></center>
            <br><br>
            <ul>
                <c:forEach var="p" items="${album.photos}">  
                    <li>${p.nom}</li>
                </c:forEach>
            </ul>
            <br>
            
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
                
            <br>    
        </div>
            
        <div class="bottom_div"></div>
    </div>
    
</div>
