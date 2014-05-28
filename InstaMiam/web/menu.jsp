<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<ul class="menu">
    <c:choose>
        <c:when test="${sessionScope.utilisateurConnecte == null}">
            <li><a <c:if test="${requestScope['javax.servlet.forward.request_uri'] == pageContext.servletContext.contextPath.concat('/Inscription')}">class="active"</c:if> href="${pageContext.servletContext.contextPath}/Inscription">{{tab_lang.nav_bar.inscription}}</a></li>
            <li><a <c:if test="${requestScope['javax.servlet.forward.request_uri'] == pageContext.servletContext.contextPath.concat('/Connexion')}">class="active"</c:if> href="${pageContext.servletContext.contextPath}/Connexion">{{tab_lang.nav_bar.connexion}}</a></li>
            </c:when>
            <c:otherwise>
            <li><a <c:if test="${requestScope['javax.servlet.forward.request_uri'] == pageContext.servletContext.contextPath.concat('/Notifications')}">class="active"</c:if> href="${pageContext.servletContext.contextPath}/Notifications">{{tab_lang.nav_bar.actu}}
                    <c:if test="${listeNotificationsSize > 0}">
                        <span class="badge notification">${listeNotificationsSize}</span>
                    </c:if>
                </a></li>
            <li><a <c:if test="${requestScope['javax.servlet.forward.request_uri'] == pageContext.servletContext.contextPath.concat('/ListeAlbums')}">class="active"</c:if> href="${pageContext.servletContext.contextPath}/ListeAlbums">{{tab_lang.nav_bar.albums}}</a></li>
            <li><a <c:if test="${requestScope['javax.servlet.forward.request_uri'] == pageContext.servletContext.contextPath.concat('/Profil')}">class="active"</c:if> href="${pageContext.servletContext.contextPath}/Profil">{{tab_lang.nav_bar.profil}}</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/Connexion?action=deconnexion">{{tab_lang.nav_bar.deconnexion}}</a></li>
            </c:otherwise>
        </c:choose>
</ul>
