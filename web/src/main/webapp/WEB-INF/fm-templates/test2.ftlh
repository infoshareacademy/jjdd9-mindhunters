<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>Mindhunters</title>
    <link href="../../css/styleSingleViewSearch.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/84fd5d585b.js" crossorigin="anonymous"></script>

</head>

<body>

<!--<div class="container-box">
    <img src="https:\/\/www.thecocktaildb.com\/images\/media\/drink\/sbffau1504389764.jpg" alt="Snow"
         style="height: 200px">
    <svg class="top-left" width="20px" height="20px" viewBox="0 0 16 16" fill="yellow"
         xmlns="http://www.w3.org/2000/svg">
        <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.283.95l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
    </svg>
    <div class="bottom-left">Bottom Left</div>
    <div class="top-left">Top Left</div>
    <div class="top-right">Top Right</div>
    <div class="bottom-right">Bottom Right</div>
    <div class="centered">Centered</div>
</div>-->
<#list drinkList as drink>

    <li class="media">
        <div class="container">
            <div class="row">
                <div class="col-sm-auto d-flex justify-content-center " style="min-width: 205px">
                    <img src="${drink.imageUrl}"
                         class=" thumbnail_list justify-content-center" alt="...">
                </div>

                <div class="media-body line-clamp col-sm-8">

                    <div class="row col-sm col-md-8 d-flex  ">
                        <h5 class="col-md-6 ">${drink.drinkName}</h5>
                        <h5 class="col-md-6">
                            ${drink.categoryName}</h5>
                    </div>
                    <div class="col-md-8">
                        ${drink.recipe}
                    </div>
                </div>

            </div>

        </div>

    </li>
</#list>


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