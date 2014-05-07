<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<div class="row">
    <div  class="col-md-4 col-md-offset-4">
        <div class="top_div"></div>
        
        <div class="content_div">
            <center><h1 class="ruge">Inscription</h1></center>
            <form id="form" method="POST" action="${pageContext.servletContext.contextPath}/Inscription">

            <label>{{tab_lang.inscription.login}} : </label><br/><input type="text" name="login" class="form-control" value="" required><br/>
            <label>{{tab_lang.inscription.nom}} : </label><br/><input type="text" name="nom" class="form-control" value="" required><br />
            <label>{{tab_lang.inscription.prenom}} : </label><br/><input type="text" name="prenom" class="form-control" value="" required><br />
            <label>{{tab_lang.inscription.email}} : </label><br/><input type="email" name="email" class="form-control" value="" required><br />
            <label>{{tab_lang.inscription.mdp}} : </label><br/><input type="password" name="mdp" class="form-control" value="" required><br />
            <input type="hidden" name="action" value="creerUtilisateur"/> 
            <div class="btn_valider">
                <input class="btn btn-default" type="submit" value="{{tab_lang.inscription.valider}}" />
            </div>
            <br><br>
            </form>
        </div>
        <div class="bottom_div"></div>
    </div>
    
</div>