const PROD=true;

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
    xmlHttp.send(body);
    return req.status;
}

function getProducts() {
    return doGet(BACK + "/product");
}

function getOneProduct(id) {
    return doGet(BACK + "/product/" + id);
}

function addToCart(code, qty) {
    var body = {};
    var product = {};
    product.code = code;
    product.qty = qty;
    body.product = product;
    var status = doPost(BACK + "/cart", body);
    if (status != 200) {
        window.location = '/error.html?code=' + status;
    }
}