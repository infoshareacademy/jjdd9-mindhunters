$(document).ready(function () {


    $( "#my-form" ).submit(function( event ) {
        event.preventDefault();
    });



    $('#btn').click(function () {
        let first_number = 0;
        let second_number = 1;

        const size = $("#field").val();
        result = $("#field");
        let output = 'Ciąg Fibbonaciego dla ' + size + ' wyrazów:' + '<br><br> 0 <br> 1';

        $.post("", {value : size});

        if ( $.isNumeric(size) && size>0 ) {
            $("#panel").slideUp({duration: 400});

            for (i = 0; i < size - 2; i++) {
                var next_number = first_number + second_number;
                output = (output + ("<br>") + next_number);
                first_number = second_number;
                second_number = next_number;
            }
            $("#result").html(output);



            document.getElementById("fibonacci").innerHTML = output;
            // w.document.body.innerHTML = output;
            $('#exampleModal').modal('toggle');



        } else {
            document.getElementById("fibonacci").innerHTML = "Wprowadź odpowiednią liczbę";

            $("#panel").slideDown({duration: 400});

        }

    });

    return false;
});