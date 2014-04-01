<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<div class="row">
    <div  class="col-md-4 col-md-offset-4">
        <div class="top_div"></div>
        
        <div class="content_div">
            <center><h1 class="ruge">Connexion</h1></center>
            <form id="form" method="POST" action="${pageContext.servletContext.contextPath}/Connexion">

            <label>{{tab_lang.inscription.login}} : </label><br/><input type="text" name="login" class="form-control" value="" /><br/>
            <label>{{tab_lang.inscription.mdp}} : </label><br/><input type="password" name="mdp" class="form-control" value=""/><br />
            <input type="hidden" name="action" value="connexion"/> 
            <div class="btn_valider">
                <input class="btn btn-default" type="submit" value="{{tab_lang.inscription.connexion}}" />
            </div>
            <br><br>
            </form>
        </div>
        <div class="bottom_div"></div>
    </div>
    
</div>