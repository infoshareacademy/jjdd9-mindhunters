$(document).ready(function () {
    $(document).on('click', "#star",
    function() {
        $(this).toggleClass('bi-star-fill bi-star');
    });
});