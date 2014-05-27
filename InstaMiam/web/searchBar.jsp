<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="ressources/js/SearchBar.js"></script>

<script>
    $(function() {
        var availableTagsRecherche = [
    <c:forEach var="u" items="${listeUtilisateur}">
            {label: "${u.prenom} ${u.nom}", id: "${u.id}"},
    </c:forEach>
            ""
        ];

        $("#tagsRecherche").autocomplete({
            source: availableTagsRecherche,
            select: function(event, ui) {

                window.location="${pageContext.servletContext.contextPath}/ListeAlbums?idUtilisateurAAfficher=" + ui.item.id;
            }
        });
    });
</script>
<form>
<input id="tagsRecherche" class="form-control barreRecherche" placeholder="{{tab_lang.recherche.recherche}}">
</form>