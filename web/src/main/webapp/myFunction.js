function myFunction() {
    if (document.getElementById("phrase").textLength > 2) {
        console.log('w metodzie get by id');
        arr2 = [];
        eventJsonToArrayOfNames($(document).ready(function () {
            str1 = "http://localhost:8080/api/search/drink/";
            res = str1;


            str2 = str1.concat(document.getElementById("phrase").value);
            res = str2;


            $.ajax({
                url: res,
                type: "GET",
                success: function (result) {
                    console.log('Success in converting data');
                    results = result;
                },
                error: function (error) {
                    console.log(error);
                }
            })
        }));


        autocomplete(document.getElementById("phrase"), results, arr2);
    }
}
