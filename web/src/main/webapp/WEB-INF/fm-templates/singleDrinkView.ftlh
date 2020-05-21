<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="../../css/style.css" rel="stylesheet">

    <script src="https://kit.fontawesome.com/84fd5d585b.js" crossorigin="anonymous"></script>

    <title>Drink ${drink.drinkName}</title>

</head>
<body id="page-top">

<!--Navigation-->
<nav class="navbar navbar-expand-lg" id="mainNav" style="background-color: #1b1e21">
    <div class="container-fluid">
        <a class="navbar-brand " href="#page-top">
            <img src="../../img/logomindhunters.jpg">
        </a>
        <div class="navbar-toggler  text-warning" type="button"
             data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive"
             aria-expanded="false" aria-label="Toggle navigation" style="border-color:#fed136">
            <a> Menu
                <svg class="bi bi-justify" width="1.5em" height="1.4em" viewBox="0 0 16 16" fill="currentColor"
                     xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd"
                          d="M2 12.5a.5.5 0 01.5-.5h11a.5.5 0 010 1h-11a.5.5 0 01-.5-.5zm0-3a.5.5 0 01.5-.5h11a.5.5 0
                          010 1h-11a.5.5 0 01-.5-.5zm0-3a.5.5 0 01.5-.5h11a.5.5 0 010 1h-11a.5.5 0 01-.5-.5zm0-3a.5.5
                          0 01.5-.5h11a.5.5 0 010 1h-11a.5.5 0 01-.5-.5z" clip-rule="evenodd"/>
                </svg>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="#part1 top">Home</a></li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="#recipePage">Recipe</a>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="#bottom-ft">Search</a>
                </li>
                <li class="nav-item"><a class="nav-link js-scroll-trigger text-warning" href="#settingPage">Setting</a>
                </li>
                <div class="btn-group">
                    <button type="button" class="btn dropdown-toggle btn-outline-warning btn-background"
                            data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                        Login
                    </button>
                    <div class="dropdown-menu dropdown-menu-right">
                        <form class="px-4 py-3">
                            <div class="form-group">
                                <label for="exampleDropdownFormEmail1">Email address</label>
                                <input type="email" class="form-control" id="exampleDropdownFormEmail1"
                                       placeholder="email@example.com">
                            </div>
                            <div class="form-group">
                                <label for="exampleDropdownFormPassword1">Password</label>
                                <input type="password" class="form-control" id="exampleDropdownFormPassword1"
                                       placeholder="Password">
                            </div>
                            <div class="form-group">
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="dropdownCheck">
                                    <label class="form-check-label" for="dropdownCheck">
                                        Remember me
                                    </label>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-outline-warning">Sign in</button>
                        </form>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">New around here? Sign up</a>
                        <a class="dropdown-item" href="#">Forgot password?</a>
                    </div>
                </div>
            </ul>
        </div>
    </div>
</nav>
<div class="body part1 top" id="body part1 top" style="background-color: #1b1e21">
    <div class="container-fluid" id="part1 top">
        <div style="position: relative">
            <img style="width: 100%;height: auto" src="../../img/mindhunters.jpg">
            <div class="over-photo1" style="position:absolute;left: 71%;bottom:20%">
                <header class="masthead">
                    <div class="container">
                        <div class="masthead-subheading text-warning glow" style=font-size:30px;text-align:center>
                            Welcome ${name}
                        </div>
                        <a class="btn btn-background btn-xl text-uppercase js-scroll-trigger text-warning"
                           href="#services">Tell Me More</a>
                    </div>
                </header>
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
                <span class="fa-stack fa-4x"><i class="fas fa-circle fa-stack-2x"></i><i
                        class="fas fa-shopping-cart fa-stack-1x fa-inverse  fa-spin text-warning"></i></span>
                <h4 class="my-3">Full stock of alcohols, but no clue, what to do?</h4>
                <p class="text-muted">You can select available bottles of alcohol and ingredients
                    and our software will find for you best possible recipes.</p>
            </div>
            <div class="col-md-4">
                <span class="fa-stack fa-4x"><i class="fas fa-circle fa-stack-2x"></i><i
                        class="fas fa-laptop fa-stack-1x fa-inverse fa-spin text-warning"></i></span>
                <h4 class="my-3">Search options</h4>
                <p class="text-muted">In your disposal you will get fast, customized and intuitive search engine
                    of our reach of drinks database. We are meeting our client's desire for alcoholic and also
                    non-alcoholic
                    beverages.</p>
            </div>
            <div class="col-md-4">
                <span class="fa-stack fa-4x"><i class="fas fa-circle fa-stack-2x"></i><i
                        class="fas fa-lock fa-stack-1x fa-inverse fa-spin text-warning"></i></span>
                <h4 class="my-3">Personalization</h4>
                <p class="text-muted">At your disposal, there several options, which help to set up your
                    preferences of drinks, viewing list and recipes and many more. We are still trying to improve and
                    provide you more options to meet most of requirements of modern World.</p>
            </div>
        </div>
    </div>
</section>

<div class="card-deck">
    <div class="card">
        <img src="..." class="card-img-top" alt="...">
        <div class="card-body1"  id="card1">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional
                content. This content is a little bit longer.</p>
            <p class="card-text " id="p-adddel"><small class="text-muted">Last updated 3 mins ago</small></p>
        </div>
        <button class="switchCard" id="switch1" onclick="loadContent()"> switch 1 card</button>
        <button class="switchCard2" id="switch2"> <!--onclick="loadContent()"-->  add 2 card</button>
        <button class="switchCard3" id="switch3"> <!--onclick="loadContent()"-->  remove 2 card</button>
    </div>
    <div class="card">
        <img src="..." class="card-img-top" alt="...">
        <div class="card-body2">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
            <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
        </div>
    </div>
    <div class="card">
        <img src="..." class="card-img-top" alt="...">
        <div class="card-body3">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional
                content. This card has even longer content than the first to show that equal height action.</p>
            <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
        </div>
    </div>
</div>


<#include "footer.ftlh">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="../../js/BootstrapValidation.js"></script>
<script type="text/javascript" src="../../js/homePage.js"></script>
</body>
</html>