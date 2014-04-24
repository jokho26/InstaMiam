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
            <center><h1 class="ruge">${album.nomAlbum}</h1></center>
            <br><br>
            <ul>
            <c:forEach var="p" items="${album.photos}">  
                <li>${p.nom}</li>
            </c:forEach>  
        </ul>
        </div>
        
        
        
        <div class="bottom_div">

        </div>
    </div>
    
</div>
