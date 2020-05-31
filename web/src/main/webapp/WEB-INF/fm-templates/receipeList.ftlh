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

            <div class="d-none d-md-block">

                <div class="list-group  list-element sticky-scroll sidebar-list p-0 " id="myID2">

                    <#list categories as category>

                        <a class="list-group-item sidebar-list">
                            ${category}
                        </a>

                    </#list>
                </div>
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


    <script>
        let maxNumber = ${maxPageSize!};
    </script>



    <ul class="pagination justify-content-center">

        <li class="page-item"><a class="page-link" href="${"/list?page=" + 1 + "&&" +  queryName!}">&laquo;</a></li>

        <li class="page-item "></li>
        <#if (currentPage >=  2) >
        <li class="page-item" id="next">
            <a onclick="previousPage()" class="page-link" aria-hidden="true" id="previous">&lsaquo;</a>
            </#if>


            <#list currentPage-2..currentPage+2 as i>

            <#if (i > 0 && i <= maxPageSize) >
        <li class="page-item <#if i = currentPage> active </#if>">
            <a class="page-link"
               href="${"/list?page=" + i + "&&" +  queryName!}">${i}</a>
        </li>
        </#if>

        </#list>

        <#if (currentPage <=  (maxPageSize-1)) >
        <li class="page-item" id="next">
            <a onclick="nextPage()" class="page-link">&rsaquo;</a>
            </#if>
        </li>
        <li class="page-item">
            <a class="page-link" href="${"/list?page=" + maxPageSize + "&&" +  queryName!}">&raquo;</a>
        </li>
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

<!--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>-->
<!--&lt;!&ndash; Third party plugin JS&ndash;&gt;-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="../../js/BootstrapValidation.js"></script>
<!--&lt;!&ndash;&lt;!&ndash; Core theme JS&ndash;&gt;&ndash;&gt;-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.0.js"></script>
-->
<script src="../../js/receipeList.js"></script>
<#--
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
-->
</body>
</html>