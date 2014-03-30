// remap jQuery to $
(function($){})(window.jQuery);


$(document).ready(function (){

/*-----------------------------------------------------------------------------------*/
/*  CTA SLIDER
/*-----------------------------------------------------------------------------------*/
  $('#slider').anythingSlider({
    easing              : 'easeInOutBack',
    buildArrows         : false,
    buildNavigation     : true, 
    buildStartStop      : false, 
    hashTags            : false
  });


/*-----------------------------------------------------------------------------------*/
/*  GALLERY PAGE
/*-----------------------------------------------------------------------------------*/

  //first box of each row has no left margin 
  //so we add this class to every first box and clear the margin in CSS
  $("#gallery .box:nth-child(4n+1)").addClass("box-first"); 

  // Clear elements if one box breaks the layout because of it's height
  $("#gallery .box:nth-child(4n+1)").before("<div class='clear'></div>");

  //IE8 can't understand the ":first-of-type" CSS property so we fix it via jQuery
  $('.ie8 #news aside .box:first').css("margin-top", "0px");


/*-----------------------------------------------------------------------------------*/
/*  CONTACT FORM
/*-----------------------------------------------------------------------------------*/
  $("#ajax-contact-form").submit(function() {
    var str = $(this).serialize();

    $.ajax({
      type: "POST",
      url: "includes/contact-process.php",
      data: str,
      success: function(msg) {
          // Message Sent? Show the 'Thank You' message
          if(msg == 'OK') {
            result = '<div class="notification_ok">Your message has been sent. Thank you!</div>';
            //$("#fields").hide();
          } else {
            result = msg;
          }
          $('#note').html(result);
      }
    });
    return false;
  });


/*-----------------------------------------------------------------------------------*/
/*  GET TWEETS
/*-----------------------------------------------------------------------------------*/
  $(".tweet").tweet({
    username: "jalberto13", //change this to your twitter username
    join_text: "auto",
    avatar_size: 0, //show avatar?
    count: 1,       //number of tweets to display
    auto_join_text_default: "",
    auto_join_text_ed: "",
    auto_join_text_ing: "",
    auto_join_text_reply: "",
    auto_join_text_url: "",
    loading_text: "loading tweets..." //you can change this to a preloader image
  })


});//DOC READY