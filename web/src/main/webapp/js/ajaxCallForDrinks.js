$('#input-name').keyup(function () {
    console.log('inside js');
    if (this.value.length < 3) return;
    var substring = $(this).val();
    $.ajax({
        url: '/api/search/drink/' + substring,
        type: 'GET',
        success: function (data) {
            console.log(data);
            listOfNames = data;
            let result = data.map(r => r.drinkName);
            $('#input-name').autocomplete({
                source: result
            });
        }
    });
});    console.log('after js');