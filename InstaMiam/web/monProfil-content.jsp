<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<div class="row">
    <div  class="col-md-6 col-md-offset-3">
        <div class="top_div"></div>
        
        <div class="content_div">
            <center><h1 class="ruge">{{tab_lang.profil.titre}}</h1></center>
            <br>
            <center>
                <img src="${pageContext.servletContext.contextPath}/profil/${profil.imageProfil}" class="img-rounded img-responsive imageProfil">
                <br><br>
                <form class="form-inline" method="POST" action="${pageContext.servletContext.contextPath}/Profil?action=changerImage" enctype="multipart/form-data">
                    <input type="file" name="file" required/>
                    <br>
                    <input class="btn boutonViolet" type="submit" value="{{tab_lang.profil.changerImage}}">
                </form>
            </center>
            <br><br>
            <form id="form" method="POST" action="${pageContext.servletContext.contextPath}/Profil">

                <label>{{tab_lang.profil.login}} :  <b>${profil.login}<b></label><br><br>
                <label>{{tab_lang.profil.mdp}} : </label><input type="password" name="mdp" class="form-control" value="${profil.motDePasse}" required/><br>
                <label>{{tab_lang.profil.prenom}} : </label><input type="text" name="prenom" class="form-control" value="${profil.prenom}" required/><br>
                <label>{{tab_lang.profil.nom}} : </label><input type="text" name="nom" class="form-control" value="${profil.nom}" required/><br>
                <label>{{tab_lang.profil.email}} : </label><input type="email" name="email" class="form-control" value="${profil.email}" required/><br>


                <input type="hidden" name="action" value="modifierProfil"/> 
                <div class="btn_valider">
                    <input class="btn boutonVert" type="submit" value="{{tab_lang.profil.modifier}}" />
                </div>
               
            </form>
            <br><br>
        </div>
        <div class="bottom_div"></div>
    </div>
    
</div>