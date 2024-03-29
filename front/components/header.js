const headerTemplateContent = `
<link rel="stylesheet" 
        href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" 
        integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" 
        crossorigin="anonymous">
<link rel="import" href="components/main-menu.html">
<style>                 
    .fas {
        font-size: 20px;
    }

    #header {
        text-align: center;
        margin-left: auto;
        margin-right: auto;
        font-size: 14px;
        font-style: italic;
        padding-top: 10px;
        padding-bottom: 10px;
    }

    #header > div {
        float: left;
        width: 33%;
    }

    #header .fas:hover {
        cursor: pointer;
    }

    #header-menu {
        width: 130px;
        margin-left: auto;
        margin-right: 0;
        text-align: right;
    }

    #header-menu > i {
        margin: 5px;
    }        

    .main {
        font-size: 24px;
        font-weight: bold;
        font-style: normal;
    }

    #front-text {
        color: #444444;
    }

        #front-text:hover {
        cursor:pointer;
    }

</style>
<div id="header">
        <ssd-main-menu></ssd-main-menu>
        <div id="front-text">
            <div class="main">La Salle sur Demande</div>
            <div>Vendeur de rêve depuis 1999</div>
        </div>
        <div id="header-menu">
            <i class="fas fa-shopping-cart front-text"></i>
            <span id="cartCount"></span>
            <i class="fas fa-user-circle front-text" style="visibility: hidden"></i>
            <i class="fas fa-search front-text" style="visibility:hidden"></i>
        </div>
</div>
`;

var parentDoc = document.currentScript.ownerDocument;
window.customElements.define('ssd-header', class extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({mode: 'open'});
        this.template = document.createElement('template');
        this.template.innerHTML = headerTemplateContent;
        this.refresh = function() { 
            var spanCounter = this.shadowRoot.querySelector("#cartCount");
            spanCounter.innerHTML = getCartCount();    
        }
    }
    connectedCallback() {
        var clone = document.importNode(this.template.content, true);
        var frontText = clone.querySelector("#front-text");
        var spanCounter = clone.querySelector("#cartCount");
        spanCounter.innerHTML = getCartCount();
        frontText.addEventListener('click', () => {
            window.location = '/salle-sur-demande/index.html'
        });
        this.shadowRoot.appendChild(clone);
    }
});
