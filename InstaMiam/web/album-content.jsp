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
            <form method="POST" action="${pageContext.servletContext.contextPath}/Album" enctype="multipart/form-data" class="dropzone" id="my-awesome-dropzone">
                  
                 <input type="submit" value="Upload" name="btnUpload" id="upload" />
                 <input type="hidden" value="${idAlbum}" name="idAlbum" id="upload" />
                 <input type="hidden" name="action" value="upload"/>
            </form>
            <br>    
        </div>

        <div class="bottom_div"></div>
    </div>

</div>
