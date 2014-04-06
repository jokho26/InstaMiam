<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<ul class="menu">
    <li><a class="active" href="${pageContext.servletContext.contextPath}">{{tab_lang.nav_bar.actu}}</a></li>
    <c:choose>
        <c:when test="${sessionScope.utilisateurConnecte == null}">
            <li><a href="${pageContext.servletContext.contextPath}/Inscription">{{tab_lang.nav_bar.inscription}}</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/Connexion">{{tab_lang.nav_bar.connexion}}</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="gallery.html">{{tab_lang.nav_bar.mur}}</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/Albums">{{tab_lang.nav_bar.albums}}</a></li>
            <li><a href="story.html">{{tab_lang.nav_bar.albums}}</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/Connexion?action=deconnexion">{{tab_lang.nav_bar.deconnexion}}</a></li>
        </c:otherwise>
    </c:choose>
  </ul>
