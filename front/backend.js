const PROD=false;

const BACK = (PROD) ? "TODO" : "http://localhost:8081/api/v1";

function doGet(url) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

function doPost(url, body) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", url, false);
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.send(body);
    return xmlHttp.status;
}

function getProducts() {
    return JSON.parse(doGet(BACK + "/product"));
}

function getOneProduct(id) {    
    return JSON.parse(doGet(BACK + "/product/" + id));
}

function addToCart(code, qty) {
    var body = {};
    body.code = code;
    body.qty = qty;
    var status = doPost(BACK + "/cart", JSON.stringify(body));
    if (status != 200) {
        window.location = '/error.html?code=' + status;
    }
}
