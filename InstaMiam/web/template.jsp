
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

        <!-- Lib angular et controlers -->
        <script src="ressources/js/angular.min.js" type="text/javascript"></script>
        <script src="ressources/js/InstaMiamCtrl.js" type="text/javascript"></script>
        <script src="ressources/js/angular-sanitize.min.js"></script>
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


        <!-- Jquery -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

        <!-- Latest compiled and minified JavaScript -->
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

        <!-- TODO à virer ? -->
        <!--<script src="ressources/Cupcakery/js/jquery.easing.1.2.js"></script>-->
        <!--<script src="ressources/Cupcakery/js/jquery.tweet.js"></script>-->
    </body>
</html>