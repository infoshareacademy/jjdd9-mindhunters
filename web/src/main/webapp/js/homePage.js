// /*!
//     * Start Bootstrap - Agency v6.0.0 (https://startbootstrap.com/template-overviews/agency)
//     * Copyright 2013-2020 Start Bootstrap
//     * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-agency/blob/master/LICENSE)
//     */
(function ($) {
    "use strict"; // Start of use strict

    // Smooth scrolling using jQuery easing
    $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function () {
        if (
            location.pathname.replace(/^\//, "") ===
            this.pathname.replace(/^\//, "") &&
            location.hostname === this.hostname
        ) {
            var target = $(this.hash);
            target = target.length
                ? target
                : $("[name=" + this.hash.slice(1) + "]");
            if (target.length) {
                $("html, body").animate(
                    {
                        scrollTop: target.offset().top - 72,
                    },
                    1000,
                    "easeInOutExpo"
                );
                return false;
            }
        }
    });
//
//     // Closes responsive menu when a scroll trigger link is clicked
    $(".js-scroll-trigger").click(function () {
        $(".navbar-collapse").collapse("hide");
    });

    // Activate scrollspy to add active class to navbar items on scroll
    $("body").scrollspy({
        target: "#mainNav",
        offset: 74,
    });

//     // Collapse Navbar
    var navbarCollapse = function () {
        if ($("#mainNav").offset().top > 100) {
            $("#mainNav").addClass("navbar-shrink");
        } else {
            $("#mainNav").removeClass("navbar-shrink");
        }
    };
    $('.navbar-nav>li>a').on('click', function () {
        $('.navbar-collapse').collapse('hide');
    });
//     // Collapse now if page is not at top
    navbarCollapse();
    // Collapse the navbar when page is scrolled
    $(window).scroll(navbarCollapse);


    // var myFunction =  function(){
    //     // var element = document.getElementById("card1");
    //     // element.classList.toggle("text warning");
    //     $(".card-body1").load("Switchedcard.html")
    //     $('.switchCard').click(myFunction);
    // }
    // var myFunction = function () {
    //     fetch("./Switchedcard.html")
    //         .then(response => {
    //             return response.text()
    //         })
    //         .then(data => {
    //             document.querySelector("card-body1").innerHTML = data;
    //         });
    // }
    //
    // $('.switchCard').click(myFunction());

    // var div = document.getElementById("card1")
    // $('#switch1').click(function () {
    //     $("#div1").load("demo_text.txt");
    // });
    //
    // $('#switch2').click(function () {
    //     $(div).innerHTML += '<p>12</p>';
    // });
    // $('#switch3').click(function () {
    //     $("<p>:last").remove();
    //
    //
    // });


})(jQuery); // End of use strict


