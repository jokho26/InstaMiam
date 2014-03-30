
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
      <link rel="stylesheet" href="ressources/Cupcakery/css/anythingslider.css">
      <link rel="stylesheet" href="ressources/Cupcakery/css/style.css">
      <script src="ressources/Cupcakery/js/modernizr-1.7.min.js"></script>
      
      <!-- Lib angular et controlers -->
      <script src="ressources/js/angular.min.js" type="text/javascript"></script>
      <script src="ressources/js/InstaMiamCtrl.js" type="text/javascript"></script>
      <script src="ressources/js/angular-sanitize.min.js"></script>

      
        <!-- CSS perso -->
      <link rel="stylesheet" href="ressources/style.css">

    </head>

    <body ng-controller="InstaMiamCtrl">
        <jsp:include page="header.jsp"/>  

        <div class="clear"></div> 

        <jsp:include page="${param.content}.jsp"/>
        
        
        <div class="clear"></div>
        
        <jsp:include page="footer.jsp"/>  


        <!-- Jquery -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

        <!-- JS du theme Cupcakery -->
        <script src="ressources/Cupcakery/js/jquery.anythingslider.min.js"></script>
        <script src="ressources/Cupcakery/js/functions.js"></script>
        
        <!-- TODO à virer ? -->
        <!--<script src="ressources/Cupcakery/js/jquery.easing.1.2.js"></script>-->
        <!--<script src="ressources/Cupcakery/js/jquery.tweet.js"></script>-->

    </body>
</html>