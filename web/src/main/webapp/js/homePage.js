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
            location.pathname.replace(/^\//, "") ==
            this.pathname.replace(/^\//, "") &&
            location.hostname == this.hostname
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

})(jQuery); // End of use strict

$(document).ready(function () {

    $("#switch1").click(function () {

        $("#card2").toggle(); // znika i pojawia sie selector
    });

    $("#switch2").click(function () {
        $("#card1").find("p").css('color', 'red');
        $("#p-adddel").find("small").css('border', '1px solid black');
        $("#card1").siblings().css('border', '4px solid yellow');

    });

    $("#switch3").click(function () {
        $("#card1").find("p").remove();
    });

    $("#switch31").click(function () {
        // $('#card3').load('Switchedcard.html');
        document.getElementById("card3").innerHTML =
            $('#card3').load('../../Switchedcard.html')
    });

    $("#submitLogin").click(function () {
    var val = document.getElementById("exampleDropdownFormEmail1").value;
    if (val== "admin") {
        $("#card-deck,#services").each(function () {
            $(this).fadeOut(2200);
        }),
            $("#glasses").animate({
                'opacity': '0.3',
                'height': '790px'
            }, 2500),

            $(".btn-admin").css({visibility: "visible", opacity: 0.0}).animate({
                opacity: 1.0
            }, 800),
            $(".tell-me").css({visibility: "hide", opacity: 1.0}).animate({
                opacity: 0.0
            }, 800),
        document.getElementById("welcomeDiv").textContent = "Welcome Admin";

    };
    });
    $("#btn-admin-back").click(function () {
            $("#card-deck,#services").each(function () {
                $(this).fadeIn(2200);
            }),
                $("#glasses").animate({
                    'opacity': '1.0',
                    'height': '880'
                }, 2500),

                $(".btn-admin").css({visibility: "hide", opacity: 1.0}).animate({
                    opacity: 0.0
                }, 800),
                $(".tell-me").css({visibility: "visible", opacity: 0.0}).animate({
                    opacity: 1.0
                }, 800),
                setTimeout(()=>{window.location.href = 'http://localhost:8080/welcome'},2200);
        });

});




