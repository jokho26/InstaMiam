<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<div class="row">
    <div  class="col-md-4 col-md-offset-4">
        <div class="top_div"></div>
        
        <div class="content_div">
            <center><h1 class="ruge">Mon profil</h1></center>
            <br>
            <center>
                <img src="${pageContext.servletContext.contextPath}/profil/${profil.imageProfil}">
                <br><br>
                <button>{{tab_lang.profil.changerImage}}</button>
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
                <input class="btn btn-default" type="submit" value="{{tab_lang.profil.modifier}}" />
            </div>
            <br><br>
            </form>
        </div>
        <div class="bottom_div"></div>
    </div>
    
</div>