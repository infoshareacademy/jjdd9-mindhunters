<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>Mindhunters</title>
    <link href="../../css/style.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/84fd5d585b.js" crossorigin="anonymous"></script>
</head>
<body id="page-top">


    <#include "navigation.ftlh">

    <div class="body part1-top" id="body part1-top" style="background-color: #1b1e21">
        <div class="container-fluid" id="part1-top">
            <div style="position: relative">
                <img class="backGround" src="../../img/mindhunters.jpg" alt="background" id="glasses">
                <div class="over-photo1" style="position:absolute;left: 71%;bottom:20%">
                    <header class="masthead">
                        <div class="container welcome-settings">
                            <div class="masthead-subheading  text-warning glow " id="welcomeDiv"
                                 style=font-size:30px;text-align:center>
                                Welcome ${name}
                            </div>
                            <a class="btn btn-background  js-scroll-trigger text-uppercase  text-warning tell-me"
                               href="#card-deck">Tell Me More</a>
                        </div>
                    </header>
                </div>
                <div class="over-photo2" style="position:absolute;left: 14%;bottom:42%">
                <span class="admin-btn-block vis-of-btn">
                    <a class="btn btn-background  text-uppercase  text-warning glow  btn-admin" id="btn-admin1"
                    >USERS</a><br>
                    <a class="btn btn-background  text-uppercase  text-warning glow  btn-admin offlineDb"
                       id="btn-admin2">RECIPES</a>
                    <form method="post" action="/welcome" enctype="multipart/form-data" class="vis-of-btn">

                        <label for="myfile">Select files:</label>
                        <input type="file" id="jsonFile" name="jsonFile" multiple><br><br>
                         <input
<#--                                 class="btn btn-background  text-uppercase  text-warning glow  btn-admin"-->
                                type="submit">

                    </form>

                    <br>
                    <a class="btn btn-background  text-uppercase  text-warning glow  btn-admin" id="btn-admin3"
                    >SETTINGS</a><br>
                    <a class="btn btn-background  text-uppercase  text-warning glow  btn-admin" id="btn-admin-back"
                    >BACK</a>
                </span>
                </div>
            </div>
        </div>
    </div>
    <section class="page-section" id="services">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase" style=font-size:70px>Virtual "Bartender"</h2>
                <h3 class="section-subheading text-muted">There we go, those are the most interesting features of our
                    software:</h3>
            </div>
            <div class="row text-center">
                <div class="col-md-4">
                <span class="fa-stack fa-4x"><em class="fas fa-circle fa-stack-2x"></em><span
                            class="fas fa-shopping-cart fa-stack-1x fa-inverse  fa-spin text-warning"></span></span>
                    <h4 class="my-3">Full stock of alcohols, but no clue, what to do?</h4>
                    <p class="text-muted">You can select available bottles of alcohol and ingredients
                        and our software will find for you best possible recipes.</p>
                </div>
                <div class="col-md-4">
                <span class="fa-stack fa-4x"><em class="fas fa-circle fa-stack-2x"></em><span
                            class="fas fa-laptop fa-stack-1x fa-inverse fa-spin text-warning"></span></span>
                    <h4 class="my-3">Search options</h4>
                    <p class="text-muted">In your disposal you will get fast, customized and intuitive search engine
                        of our reach of drinks database. We are meeting our client's desire for alcoholic and also
                        non-alcoholic
                        beverages.</p>
                </div>
                <div class="col-md-4">
                <span class="fa-stack fa-4x"><em class="fas fa-circle fa-stack-2x"></em><span
                            class="fas fa-lock fa-stack-1x fa-inverse fa-spin text-warning"></span></span>
                    <h4 class="my-3">Personalization</h4>
                    <p class="text-muted">At your disposal, there several options, which help to set up your
                        preferences of drinks, viewing list and recipes and many more. We are still trying to improve
                        and
                        provide you more options to meet most of requirements of modern World.</p>
                </div>
            </div>
        </div>
    </section>
    <div class="card-deck card-deck-high" id="card-deck">
        <div class="card card-body">
            <img src="../../img/fullstock.jpg" class="card-img-top" alt="fullStock">
            <div class="card" id="card1">

            </div>
        </div>
        <div class="card card-body">
            <img src="../../img/search.jpg" class="card-img-top" alt="search">
            <div class="card" id="card2">

            </div>
        </div>
        <div class="card card-body">
            <img src="../../img/settings.png" class="card-img-top" alt="settings">
            <div class="card" id="card3">

            </div>
        </div>
    </div>

<#include "footer.ftlh">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymneiw iem jak wsatawilem u mnie footer to mi namiesal z ustawieniemous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="../../js/BootstrapValidation.js"></script>
<script src="../../js/homePage.js"></script>
</body>
</html>
