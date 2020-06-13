$(document).ready(function () {
    $('#ID03').on('click', '#ID04', function () {
        let drinkId = $(this).siblings('p').text().trim().toString();

        let url = new URL(window.location);

        let newURL = url.origin + '/list?page=1';

        $.post(newURL, {drinkId: drinkId});

        $(this).toggleClass("color_toggle_on");
    });
});