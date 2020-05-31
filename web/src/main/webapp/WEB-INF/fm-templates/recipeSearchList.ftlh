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
<body id="page-top" style="background-color: #1b1e21;">

<!--Navigation-->
<#include "navigation.ftlh">


<!--List-->
<nav aria-label="LIST">

    <div class="container-fluid  ">

        <div class="row mx-2 my-2 justify-content-center">

            <div>
                <form method="post" action="/search">
                    <div class="list-group  list-element">
                        <div class="list-group-item sidebar-list">
                            <input type="hidden" name="search-type" value="name"/>
                            <input type="text" required minlength="2" name="drink-name" id=""
                                   placeholder="Search by name...">
                        </div>
                    </div>
                    <br>
                    <input class="rounded-input" type="submit" name="search-by-name" value="Search by name">
                    <br><br><br>
                </form>

                <form method="post" action="/search">
                    <div class="list-group  list-element add-group">

                        <div class="list-group-item sidebar-list">
                            <input type="hidden" name="search-type" value="ingredient"/>
                            <input type="text" required minlength="2" name="drink-ingredients" value=""
                                   placeholder="Search by ingredients..."/>
                            <a href="javascript:void(0);" class="add_button " id="remove_blue" title="Add field">
                                <svg class="bi bi-plus-square-fill" id="remove_blue" width="1em" height="1em"
                                     viewBox="0 0 16 16"
                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd"
                                          d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm6.5 4a.5.5 0 0 0-1 0v3.5H4a.5.5 0 0 0 0 1h3.5V12a.5.5 0 0 0 1 0V8.5H12a.5.5 0 0 0 0-1H8.5V4z"/>
                                </svg>
                            </a>
                            <br>
                        </div>

                    </div>
                    <br>
                    <input type="submit" name="search-by-ingredients" class="rounded-input"
                           value="Search by ingredients"/>
                    <br/><br><br>
                </form>
            </div>

            <div class="col-12 col-md-8 col-lg-8 col-xl-8">
                <ul class="list-unstyled  justify-content-center  ml-auto space_list ">
                    <#list drinkList as drink>

                        <li class="media ">
                            <div class="container ml-0 pl-0 mr-0 pr-0 ">
                                <div class="row list-element">
                                    <div class="col-md-auto d-flex justify-content-center " style="min-width: 205px">
                                        <img src="${drink.image}"
                                             class=" thumbnail_list justify-content-center" alt="...">
                                    </div>

                                    <div class="media-body line-clamp col-sm-8 ml-0 pl-1 mr-0 pr-0"
                                         style="color: #ffc107">

                                        <div class="row col-sm d-flex mt-4 mt-md-0 ">
                                            <h5 class="col-md-6 mx-2 my-2  font-weight-bold text-center text-md-left">
                                                ${drink.drinkName}
                                            </h5>

                                            <h5 class="col-md-5 mx-2 my-2   text-center text-md-right" style="
                                                    padding-right: 0px;
                                                    margin-right: 0px;">
                                                ${drink.categoryView.name}

                                            </h5>
                                        </div>
                                        <div class="line-clamp mx-2 my-2 mt-4 mr-4 d-none d-md-block">
                                            ${drink.recipe}
                                        </div>
                                    </div>

                                </div>

                            </div>

                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        let maxNumber = ${maxPageSize};


        async function nextPage() {
            let url = new URL(window.location); // or construct from window.location
            //   this.addClass("disabled");

            let params = new URLSearchParams(url.search.slice(1));

            let pageNumber = parseInt(params.get('page'));


            let newPageNumber = pageNumber + 1;

            if (newPageNumber <= maxNumber) {
                params.set('page', (newPageNumber).toString());
                let newURL = 'http://localhost:8080/search?' + params.toString();


                location.replace(newURL);

                if (pageNumber == 5) {

                    var element = document.getElementById("next");
                    element.classList.add("disabled");
                }

            }


        }

        async function previousPage() {
            let url = new URL(window.location); // or construct from window.location

            let params = new URLSearchParams(url.search.slice(1));

            let pageNumber = parseInt(params.get('page'));

            let newPageNumber = pageNumber - 1;
            if (newPageNumber > 0) {
                params.set('page', (newPageNumber).toString());
                let newURL = 'http://localhost:8080/search?' + params.toString();
                location.replace(newURL);

            }
        }


        async function setPage() {
            let url = new URL(window.location); // or construct from window.location

            let params = new URLSearchParams(url.search.slice(1));

            let pageNumber = parseInt(params.get('page'));

            let newPageNumber = pageNumber - 1;
            if (newPageNumber > 0) {
                params.set('page', (newPageNumber).toString());
                let newURL = 'http://localhost:8080/search?' + params.toString();
                location.replace(newURL);

            }
        }


        function toDisabled() {

            let url = new URL(window.location);
            var element = document.getElementById("next");
            element.classList.add("disabled");

            let params = new URLSearchParams(url.search.slice(1));

            let pageNumber = parseInt(params.get('page'));

            console.info(pageNumber);

            if (pageNumber == 5) {

                var element = document.getElementById("next");
                element.classList.add("disabled");
            }
        }


    </script>


    <ul class="pagination justify-content-center">

        <li class="page-item"><a class="page-link" href="${"/search?page=" + 1}">&laquo;</a></li>

        <li class="page-item ">
        </li>
        <#if (currentPage >=  2) >
        <li class="page-item" id="next">
            <a onclick="previousPage()" class="page-link" aria-hidden="true" id="previous">&lsaquo;</a>
            </#if>


            <#list currentPage-2..currentPage+2 as i>
            <#if (i > 0 && i < maxPageSize) >
        <li class="page-item"><a class="page-link" href="${"/search?page=" + i}">${i}</a></li>
        </#if>

        </#list>

        <#if (currentPage <=  (maxPageSize-1)) >
        <li class="page-item" id="next">
            <a onclick="nextPage()" class="page-link">&rsaquo;</a>
            </#if>
        </li>
        <li class="page-item"><a class="page-link" href="${"/search?page=" + maxPageSize}">&raquo;</a></li>
    </ul>
</nav>

<!--Footer-->

<#include "footer.ftlh">

<link href="https://fonts.googleapis.com/css2?family=Cinzel&display=swap" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>

<!-- Bootstrap core JS-->

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="../../js/BootstrapValidation.js"></script>
<script type="text/javascript" src="../../js/homePage.js"></script>
<script type="text/javascript" src="../../js/searchPage.js"></script>

</body>
</html>