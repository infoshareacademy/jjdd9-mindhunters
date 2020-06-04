$(document).ready(function () {
    var maxField = 10; //Input fields increment limitation
    var addButton = $('.add_button'); //Add button selector
    var wrapper = $('.add-group'); //Input field wrapper
    var fieldHTML = '<div class="list-group-item sidebar-list"><input type="text" minlength="2"' +
        ' name="drink-ingredients"' +
        ' value=""' +
        ' placeholder="Add ingredient"/><a' +
        ' id="remove_blue" href="javascript:void(0);"' +
        ' class="remove_button"><svg class="bi bi-trash" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">\n' +
        '  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>\n' +
        '  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>\n' +
        '</svg><br></a></div>'; //New input field html
    var x = 1; //Initial field counter is 1

    //Once add button is clicked
    $(addButton).click(function () {
        //Check maximum number of input fields
        if (x < maxField) {
            x++; //Increment field counter
            $(wrapper).append(fieldHTML); //Add field html
        }
    });

    //Once remove button is clicked
    $(wrapper).on('click', '.remove_button', function (e) {
        e.preventDefault();
        $(this).parent('div').remove(); //Remove field html
        x--; //Decrement field counter
    });
});




//do przerobienia


$(document).ready(function () {

    let content = null;

    $('#search-by-name').on('click', '*', function () {
        content = $(this).children('p').text().trim();

        let url = new URL(window.location);


        var searchParams = url.searchParams;


        searchParams.set("page", "1");

        if (!searchParams.getAll("category").includes(content)) {

            searchParams.append("category", content);

            window.location = url;
        } else {

            let searchArray = searchParams.getAll("category");

            searchArray.splice(searchArray.indexOf(content), 1);
            if (searchArray.length >= 1) {
                var newUrl = "&category=" + searchArray.join("&category=");

            } else {
                newUrl = "";
            }

            window.location = '/list?page=1' + newUrl;
        }
    });

})


async function nextPage() {
    let url = new URL(window.location); // or construct from window.location

    let params = new URLSearchParams(url.search.slice(1));

    let pageNumber = parseInt(params.get('page'));

    let newPageNumber = pageNumber + 1;
    params.set('page', (newPageNumber).toString());
    let newURL = 'http://localhost:8080/list?' + params.toString();
    location.replace(newURL);

}

async function previousPage() {
    let url = new URL(window.location); // or construct from window.location

    let params = new URLSearchParams(url.search.slice(1));

    let pageNumber = parseInt(params.get('page'));

    let newPageNumber = pageNumber - 1;
    if (newPageNumber > 0) {
        params.set('page', (newPageNumber).toString());
        let newURL = 'http://localhost:8080/list?' + params.toString();
        location.replace(newURL);

    }
}

async function setPage() {
    let url = new URL(window.location); // or construct from window.location

    let params = new URLSearchParams(url.search.slice(1));

    let pageNumber = parseInt(params.get('page'));

    let newPageNumber = pageNumber - 1;
    if (newPageNumber > 0) {
        params.set('page', (newPageNumber).toString());
        let newURL = 'http://localhost:8080/list?' + params.toString();
        location.replace(newURL);

    }
}


