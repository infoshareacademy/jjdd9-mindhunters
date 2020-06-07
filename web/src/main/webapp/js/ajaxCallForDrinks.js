document.getElementById("phrase").addEventListener('keyup', ajaxCallForDrinks);

function ajaxCallForDrinks() {
    console.log('inside My function');

    var value = $(document.getElementById("phrase")).val();


    console.log(document.getElementById("phrase").value);
    console.log(document.getElementById("phrase").toString().length);

    if (value.length > 2) {
        console.log('inside text lenght > 2');
        arr2 = [];
        eventJsonToArrayOfNames($(document).ready(function () {
            str1 = "http://localhost:8080/api/search/drink/";
            res = str1;


            str2 = str1.concat(document.getElementById("phrase").value);
            res = str2;
            console.log(res);
            console.log('before ajax');
            $.ajax({
                url: res,
                type: "GET",
                success: function (result) {
                    console.log('Success in converting data');
                    results = result;
                    console.log('after ajax');
                    console.log(results);
                    autocomplete(document.getElementById("phrase"), results, arr2);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }));

    }
}