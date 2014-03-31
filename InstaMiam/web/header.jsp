<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<header>        
    <div class="container" id="paddingHeader">

        <div class="row">
            <div class="col-md-5">
                <a href="${pageContext.servletContext.contextPath}"><img src="ressources/img/instamiam.png" alt="instamiam" id="logo"/></a>
            </div>
            <div class="col-md-7">
                <jsp:include page="menu.jsp"/>  
            </div>
        </div><!--//container-->
    </div>
</header>