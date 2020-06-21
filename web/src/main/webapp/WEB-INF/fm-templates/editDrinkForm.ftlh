<html lang="en"><head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>Mindhunters</title>
    <link href="../../css/addDrinkForm.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/84fd5d585b.js" crossorigin="anonymous"></script><link href="https://kit-free.fontawesome.com/releases/latest/css/free-v4-shims.min.css" media="all" rel="stylesheet"><link href="https://kit-free.fontawesome.com/releases/latest/css/free-v4-font-face.min.css" media="all" rel="stylesheet"><link href="https://kit-free.fontawesome.com/releases/latest/css/free.min.css" media="all" rel="stylesheet">

</head>
<body id="page-top justify-content-center " style="background-color: #1b1e21;">

<!--Navigation-->

<form class="was-validated" id="form_data" >


    <div class="container-fluid form-elements " style="width: 800px">


        <div class="col-12 mb-3">

            <label>Input recipe data: </label>

            <input name="name" type="text" class="form-control integerInput md-3 " id="field" placeholder="Insert name" required="" minlength="3" value="test">
            <div class="invalid-feedback alert alert-danger col-5">Input min. 3 chars</div>

        </div>

        <div class="form-group col-12">
            <label for="exampleFormControlTextarea1">Input recipe:</label>
            <textarea name="recipe" class="form-control" id="exampleFormControlTextarea1" rows="5" required="" minlength="3">Layered in a shot glass.</textarea>
            <div class="invalid-feedback alert alert-danger col-5">Input min. 5 chars</div>
        </div>

        <div class=" my-3 col-5 justify-content-between">
            <button type="button" class="btn btn-primary" name="save" value="save" id="submit_but">
                Save drink
            </button>
        </div>


    </div>
</form>





<link href="https://fonts.googleapis.com/css2?family=Cinzel&amp;display=swap" rel="stylesheet">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/js/serializejson.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<!-- Bootstrap core JS-->

<!--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>-->
<!--&lt;!&ndash; Third party plugin JS&ndash;&gt;-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!--&lt;!&ndash;&lt;!&ndash; Core theme JS&ndash;&gt;&ndash;&gt;-->
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script src="../../js/editDrink.js"></script>

</body></html>