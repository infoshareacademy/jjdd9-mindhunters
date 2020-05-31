$("#myID1").trigger(onclick)
{

    var searchParams = new URLSearchParams(window.location);
/*
    for (let p of searchParams) {
        console.log(p);
    }*/
    console.log(searchParams.has("category"));
    searchParams.set("category", "shot");
    console.log(searchParams.has("category"));
    console.log(searchParams.toString()); // "q=URLUtils.searchParams&topic=More+webdev"

    let newURL = 'http://localhost:8080/list?' + searchParams.toString();
    alert(newURL)
    location.replace(newURL);

};




async function nextPage() {
    let url = new URL(window.location); // or construct from window.location
    //   this.addClass("disabled");

    let params = new URLSearchParams(url.search.slice(1));

    let pageNumber = parseInt(params.get('page'));


    let newPageNumber = pageNumber + 1;

    if (newPageNumber <= maxNumber) {
        params.set('page', (newPageNumber).toString());
        let newURL = 'http://localhost:8080/list?' + params.toString();


        location.replace(newURL);

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

function setCategory() {
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

