
<%@ page language="java" contentType="text/html; charset=UTF-8"  
         pageEncoding="UTF-8"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<!DOCTYPE HTML>  
<html>  
    <head>  
        <title>${param.title}</title>  
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

        <!-- Theme cupcakery -->
        <link rel="stylesheet" href="war:///ressources/Cupcakery/css/anythingslider.css">
        <link rel="stylesheet" href="ressources/Cupcakery/css/style.css">
        
        <!-- Les glyphs des images de bootstrap -->
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
    </head>  
    <body>
        
        <header>
  <div class="container_16 clearfix">
    <!-- Replace with your logo -->
    <h1 id="logo" class="grid_8"><a href="index.html"><img src="css/images/logo.png" alt=""/></a></h1>
    
    <!-- Menu -->
    <nav class="grid_8">
      <ul class="m500">
        <li class="first"><a class="active" href="index.html">Home</a></li>
        <li><a href="gallery.html">Gallery</a></li>
        <li><a href="story.html">Story</a></li>
        <li><a href="news.html">News</a></li>
        <li><a href="contact.html">Contact</a></li>
      </ul>
    </nav>

  </div><!--//container-->
</header>

<div class="clear"></div> 

<section id="cta" class="container_16 clearfix">

  <!-- Slider -->
  <div id="slider-container" class="grid_9">
    <ul id="slider">
      <!-- 1st Slider -->
      <li><img src="images/520x220.gif" alt=""></li>
      <!-- 2nd Slider -->
      <li><img src="images/520x220.gif" alt=""></li>
      <!-- 3rd Slider -->
      <li><img src="images/520x220.gif" alt=""></li>
    </ul>
  </div>
  <!--//Slider-->

  <!-- CTA Text & Button -->
  <div class="grid_7 alpha">
    <h1 class="ruge">You'll love our <span>Cupcakes</span></h1>
    <p class="m500">Halvah apple pie cake lollipop lollipop cookie. Carrot cake bonbon toffee tootsie roll. Bear claw croissant powder tootsie roll dessert. Chocolate ice cream oat cake chocolate cake jelly. Applicake ice cream applicake sweet roll cake marzipan.</p>
    <a href="gallery.html" id="cta-btn"></a>
  </div>
  <!-- //CTA Text & Button -->

</section>

<div class="clear"></div> 

<section id="boxes" class="container_16 clearfix">

  <!-- First Box (text) --> 
  <div class="box box-1 grid_8">
    <h2 class="ruge">Made with Love, just for you</h2>
    <div class="box-top"></div>
    <div class="box-container">
      <p class="m500">Halvah apple pie cake lollipop lollipop cookie. Carrot cake bonbon toffee tootsie roll. Bear claw croissant powder tootsie roll dessert. Chocolate ice cream oat cake chocolate cake jelly. Applicake ice cream applicake sweet roll cake marzipan. Candy canes cupcake cake icing donut. Cotton candy gingerbread liquorice gummies macaroon marshmallow brownie. Ice cream faworki macaroon donut. Apple pie liquorice gummies macaroon marshmallow</p>
      <img src="css/images/monster.png" width="171" height="143" alt=""/>
      <p class="m500 p-text">Carrot cake bonbon toffee tootsie roll. Bear claw croissant powder tootsie roll dessert.</p>
      <a class="m500" href="story.html">Read More</a>
    </div>
    <div class="box-bottom"></div>
  </div>
  <!-- //First Box (text) --> 

  <!-- Gallery Preview --> 
  <div class="grid_8 gallery-preview">
    <h2 class="ruge">Straight from the Oven</h2>
    <!-- Box 1 -->
    <div class="box box-2">
      <div class="gallery-box-top"></div>
      <div class="gallery-box">
        <img src="images/207x156.gif" width="207" height="156" alt="">
        <h6 class="m500">Incredible Turtle Cookie</h6>
        <p class="m500">Carrot cake bonbon toffee tootsie</p>
      </div>
      <div class="gallery-box-bottom"></div>
    </div>
    <!-- //Box 1 -->
    <!-- Box 2 -->
    <div class="box box-3">
      <div class="gallery-box-top"></div>
      <div class="gallery-box">
        <img src="images/207x156.gif" width="207" height="156" alt="">
        <h6 class="m500">Chocolate Mud Cupcakes</h6>
        <p class="m500">Carrot cake bonbon toffee tootsie</p>
      </div>
      <div class="gallery-box-bottom"></div>
    </div>
    <!-- //Box 2 -->
  </div>
  <!-- //Gallery Preview --> 
</section>
  
<div class="clear"></div>

<div class="footer-splash"></div>
<footer>
  <div class="container_16 clearfix">
    
    <div class="open-hours grid_5">
      <h3 class="ruge">Opening Hours</h3>
      <p class="m500">Halvah apple pie cake lollipop lollipop cookie. Carrot cake bonbon toffee tootsie roll.</p>
      <ul class="m500">
        <li>MON to TUE - 10 AM to 20 PM</li>
        <li>SAT - 12 AM to 24 PM</li>
        <li>SUN - 12 AM to 20 PM</li>
      </ul>
      <p class="m500">Reservations? We are the perfect place to hold your celebrations!<br/>Give us a call 919-0987424-123</p>
    </div>

    <div class="news-pre grid_5">
      <h3 class="ruge">Latest News</h3>
      <article>
        <h6 class="m700"><a href="#">Lollipop Cookie</a></h6>
        <p class="m500">Halvah apple pie cake lollipop lollipop cookie. Carrot cake bonbon toffee tootsie roll. Bear claw croissant powder tootsie roll dessert. Chocolate ice cream oat cake chocolate cake jelly.</p>
      </article>
      <article>
        <h6 class="m700"><a href="#">Lollipop Cookie</a></h6>
        <p class="m500">Halvah apple pie cake lollipop lollipop cookie. Carrot cake bonbon toffee tootsie roll. Bear claw croissant powder tootsie roll dessert. Chocolate ice cream oat cake chocolate cake jelly.</p>
      </article>
    </div>

    <div class="social grid_5">
      <h3 class="ruge">We're social and we know it!</h3>
      <a class="m500" id="facebook" href="#">Like us on Facebook!</a>
      <a class="m500" id="twitter" href="#">Follow our Tweets!</a>
      <p class="bubble m500 tweet"></p>
    </div>

    <div class="clear"></div>
    <p class="copy grid_4 push_11 m500">&copy; Cupcakery 2012. All Rights Reserved.</p>

  </div><!--//container-->
</footer>
        
        <jsp:include page="header.jsp"/>  
        <jsp:include page="${param.content}.jsp"/>
        <jsp:include page="footer.jsp"/>  

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>  
</html>  