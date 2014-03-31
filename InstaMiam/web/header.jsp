<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<header>        
    <div class="container">

        <div class="row">
            <!-- Replace with your logo -->
            <h1 id="logo" class="grid_8"><a href="${pageContext.servletContext.contextPath}"><img src="ressources/img/instamiam.png" alt="instamiam" id="logo"/></a></h1>

            <jsp:include page="menu.jsp"/>  

        </div><!--//container-->
    </div>
</header>