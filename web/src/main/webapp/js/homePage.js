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
        if (val == "admin") {
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

        }
        ;
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
            setTimeout(() => {
                window.location.href = 'http://localhost:8080/welcome'
            }, 2200);
    });

    // var pageCounter = a;
    // var jsonContainer = document.getElementById("animal-info");
    // var btn = document.getElementById("btn-admin3");
    // btn.addEventListener("click", function () {
    //     var ourRequest = new XMLHttpRequest();
    //     ourRequest.open('GET', 'https://www.thecocktaildb.com/api/json/v1/1/search.php?f='
    //         + pageCounter);
    //     ourRequest.onload = function () {
    //         if (ourRequest.status >= 200 && ourRequest.status < 400) {
    //             var ourData = JSON.parse(ourRequest.responseText);
    //             renderHTML(ourData);
    //             // console.log(ourRequest.responseText);
    //         } else {
    //             console.log("We connect to the server, but it return an error")
    //         }
    //     };
    //     ourRequest.onerror = function () {
    //         console.log("Connection error");
    //     };
    //     ourRequest.send();
    //     // pageCounter++;
    //     // if (pageCounter > 3) {
    //     //     btn.classList.add("hide-me");
    //     // }
    // });
    //
    // function renderHTML(data) {
    //     var htmlString = "";
    //     for (i = 0; i < data.length; i++) {
    //         htmlString += "<p>" + data[i].name + " is a " + data[i].species + " that likes ";
    //         for (ii = 0; ii < data[i].foods.likes.length; ii++) {
    //             if (ii == 0) {
    //                 htmlString += data[i].foods.likes[ii];
    //             } else {
    //                 htmlString += " and " + data[i].foods.likes[ii];
    //             }
    //         }
    //         htmlString += ' and dislikes ';
    //         for (ii = 0; ii < data[i].foods.dislikes.length; ii++) {
    //             if (ii == 0) {
    //                 htmlString += data[i].foods.dislikes[ii];
    //             } else {
    //                 htmlString += " and " + data[i].foods.dislikes[ii];
    //             }
    //         }
    //         htmlString += ' .</p>';
    //     }
    //     animalContainer.insertAdjacentHTML('beforeend', htmlString);
    // }



})
;




