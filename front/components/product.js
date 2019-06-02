const productTemplateContent = `
<style>                
    #product {
        width: 230px;
        margin: 5px;
        float: left;
        padding: 5px 10px;
        font-size: 13px;
        border: solid thin #c0c0c0;
        border-radius: 4px;
    }

    #product:hover {
        cursor: pointer;
    }

    #product div {
        text-align: center;
        width: 100%;
        margin: 0;
    }

    #product img {
        margin-left: auto;
        margin-right: auto;
        display: block;
    }
    #product .desc {
        margin-left: auto;
        margin-right: auto;
        display:none;
        width: 200px;
        height: 150px;
        font-size: 10px;
    }

    #price {
        font-weight: bold;
        color: black;
        margin-top: 10px;
        font-size: 18px;
    }
</style>


<div id="product">
    <div>
        <img id="img"
            height="150"
            width = "200"
            src="" />
        <div id="desc" style="display:none">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus quam sapien, 
            porta et odio at, iaculis luctus dui. Integer laoreet dolor turpis, nec aliquet 
            eros mattis faucibus. Quisque turpis duis.
        </div>
    </div>
    <div id="name"></div>
    <div id="price"></div>
</div>
`;

var parentDoc = document.currentScript.ownerDocument;
window.customElements.define('ssd-product', class extends HTMLElement {
    get name() {
        return this.hasAttribute('name')
    }

    set name(val) {
        this.setAttribute('name', val)
    }

    get price() {
        return this.hasAttribute('price')
    }

    set price(val) {
        this.setAttribute('price', val)
    }

    get imgLink() {
        return this.hasAttribute('imgLink')
    }

    set imgLink(val) {
        this.setAttribute('imgLink', val)
    }
    
    constructor() {
        super()
        this.addEventListener('click', () => {
            var code = this.getAttribute('code');
            window.location = "/salle-sur-demande/product_page.html?ref=" + code;
        });
        this.attachShadow({mode: 'open'});
        this.template = document.createElement('template');
        this.template.innerHTML = productTemplateContent;
    }
    connectedCallback() { 
            var clone = document.importNode(this.template.content, true);
            
            var name = this.getAttribute("name");
            var imgLink = this.getAttribute("imgLink");
            if (imgLink === undefined || imgLink == null) {
                imgLink = "https://www.novovelo.com/wp-content/themes/claue/assets/images/placeholder.png";
            }
            var price = this.getAttribute("price");

            clone.querySelector("#name").innerHTML = name;
            clone.querySelector("#img").src = imgLink;
            clone.querySelector("#price").innerHTML = price;

            this.shadowRoot.appendChild(clone);
    }
});
