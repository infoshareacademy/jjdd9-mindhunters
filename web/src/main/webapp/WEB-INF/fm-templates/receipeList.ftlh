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

<!--Navigation-->
<#include "navigation.ftlh">


<!--List-->
<nav aria-label="LIST">

    <div class="container-fluid  ">

        <div class="row mx-2 my-2 justify-content-center">

            <div class="d-none d-md-block">

                <div class="list-group sticky-scroll">
                    <#list categories as category>

                    <a href="#" class="list-group-item list-group-item-action active">${category}</a>

                    </#list>

                </div>
            </div>


            <div class="col-8 col-md-8 col-lg-8 col-xl-6">
                <ul class="list-unstyled  justify-content-center  ml-auto space_list ">
                    <#list drinkList as drink>
                        <li class="media">
                            <div class="d-none  d-sm-block">
                                <img src="${drink.imageUrl}"
                                     class="mr-3 thumbnail_list " alt="...">
                            </div>
                            <div class="media-body line-clamp">
                                <div class="row">
                                    <h5 class="mt-0 mb-1 col-sm-8 ">${drink.drinkName}</h5>
                                    <h5 class="mt-0 mb-1 col-sm-4 text-md-right">
                                        ${drink.categoryName}</h5>
                                </div>
                                ${drink.recipe}
                            </div>
                        </li>
                    </#list>
                </ul>
            </div>
        </div>
    </div>

    <ul class="pagination justify-content-center">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item active" aria-current="page">
            <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
        </li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
            <a class="page-link" href="#">Next</a>
        </li>
    </ul>
</nav>

<!--Footer-->

<#include "footer.ftlh">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>

<!-- Bootstrap core JS-->

<!--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>-->
<!--&lt;!&ndash; Third party plugin JS&ndash;&gt;-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="../../js/BootstrapValidation.js"></script>
<!--&lt;!&ndash;&lt;!&ndash; Core theme JS&ndash;&gt;&ndash;&gt;-->
<script type="text/javascript" src="../../js/homePage.js"></script>
<#--
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
-->
</body>
</html>