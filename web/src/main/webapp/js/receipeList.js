$("#myID2").click(function myFunction() {

/*
    var searchParams = new URLSearchParams(window.location);
*/
/*

    for (let p of searchParams) {
        console.log(p);
    }*/

    let url = new URL(window.location); // or construct from window.location

    let content = $(event.target).text().trim();

  //  console.log(content);
    var searchParams = url.searchParams;

    searchParams.set("page", "1")

    if (!searchParams.getAll("category").includes(content)){


        searchParams.append("category", content);
        window.location =  url;

        $(event.target).classList.add('active');


    } else {

        let searchArray = searchParams.getAll("category");


        searchArray.splice(searchArray.indexOf(content),1);

        var newUrl = "&category=" + searchArray.join("&category=");


        console.log(newUrl);

        window.location =   '/list?' + newUrl;
    }


});




async function nextPage() {
    let url = new URL(window.location); // or construct from window.location
    //   this.addClass("disabled");

    let params = new URLSearchParams(url.search.slice(1));

    let pageNumber = parseInt(params.get('page'));


    let newPageNumber = pageNumber + 1;

    if (newPageNumber <= maxNumber) {
        params.set('page', (newPageNumber).toString());
        let newURL = 'http://localhost:8080/list?' + params.toString();


        window.location(newURL);

        if (pageNumber == 5) {

            var element = document.getElementById("next");
            element.classList.add("disabled");
        }

    }


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


function toDisabled() {

    let url = new URL(window.location);
    var element = document.getElementById("next");
    element.classList.add("disabled");

    let params = new URLSearchParams(url.search.slice(1));

    let pageNumber = parseInt(params.get('page'));

    console.info(pageNumber);

    if (pageNumber == 5) {

        var element = document.getElementById("next");
        element.classList.add("disabled");
    }
}


