
<%@ page language="java" contentType="text/html; charset=UTF-8"  
         pageEncoding="UTF-8"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html ng-app="InstaMiam">
    <head>

        <meta charset="utf-8">


        <title>${param.title}</title>

        <!-- TODO favincon -->
        <link rel="shortcut icon" href="ressources/Cupcakery/css/images/favicon.ico">

        <!-- JS et css liés au theme Cupcakery -->
        <link href='http://fonts.googleapis.com/css?family=Ruge+Boogie' rel='stylesheet' type='text/css'>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

        <!-- CSS perso -->
        <link rel="stylesheet" href="ressources/style.css">

        <link rel="stylesheet" href="ressources/dropzone.css">

        <!-- Lib angular et controlers -->
        <script src="ressources/js/angular.min.js" type="text/javascript"></script>
        <script src="ressources/js/InstaMiamCtrl.js" type="text/javascript"></script>
        <script src="ressources/js/angular-sanitize.min.js"></script>
        <script src="ressources/js/dropzone.js"></script>

        <!-- Jquery -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

    </head>

    <body ng-controller="InstaMiamCtrl">

        <jsp:include page="header.jsp"/>  
        <div class="container">

            <c:if test="${!empty requestScope['message']}">         
                <div class="alert alert-info">${requestScope['message']}</div>
            </c:if>
            <c:if test="${!empty requestScope['messageErreur']}">         
                <div class="alert alert-danger">${requestScope['messageErreur']}</div>
            </c:if>  

            <jsp:include page="${param.content}.jsp"/>

        </div>

        <jsp:include page="footer.jsp"/>  



        <!-- Latest compiled and minified JavaScript -->
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="ressources/js/jquery.blockUI.js"></script>
        <script src="ressources/js/jQueryRotate.js"></script>

    </body>
</html>