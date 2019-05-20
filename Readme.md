# Talk "Comment la GCP m'a permis de sauver ma Prod chez Mamie"

## TodoList

### Mandatory 
* [ ] (Front) Generate the products in javascript loop instead of hardcoded
* [ ] (Front) Create the Error Page
* [ ] (Front) Create a `constants.js` file which will contains local & GCP
    url to the backend.
* [ ] (Front) Call the backend API to get all the products
* [ ] (Front) Call the backend API to add a product to cart
* [ ] (Front) Deploy on GCS
* [ ] (Back) Create the Springboot backend Docker image
* [ ] (Back) Add an endpoint to return the list of all the products' summaries
* [ ] (Back) Add an endpoint to return the json description of a specific
    product
* [ ] (Back) Add an endpoint to add a product in the cart (use dummy user 
    with a singleton)
* [ ] (Back) Add an endpoint to get the cart count
* [ ] Makefile to organize the deployment/start _AND_ the shutdown/teardown.
* [ ] (Back) Deploy on GKE
* [ ] (Back) Add the GCP Endpoints
* [ ] (Data) Create the Database structure in a PostgreSQL instance
* [ ] (Data) Fill the database with dummy products
* [ ] (Back) Store the image in GCP CDN
* [ ] (Data) Apply the database structure & init to a GCP CloudSQL
* [ ] (Batch) Create the Pub/Sub topic
* [ ] (Batch) Create a simple dummy/mock batch application which will display
    random orders informations at each Pub/Sub message
* [ ] (Batch) Prepare the scalable infrastructure as a DM script on 
    PrivateCatalog

### If there is more time
* [ ] (Front) Add the GCP LB in front of the GCS
* [ ] (Front) Finish up the `main-menu` component (with an apparition only on 
    hover) and the menu items in it (Log In, About...)
* [ ] (Front) Create the `my-account` component to display info of a dummy user.
* [ ] (Front) Create a `search` component which do a simple search on the 
    products.
* [ ] (Front) Implements the `Reset Filter` button & add the connection to the 
    backend for the `Apply Filter` one.
* [ ] (Front) Display the description instead of the image when the product 
    is hovered
* [ ] (Front) Implement the behavior of the `-` and `+` button on the Product 
    page
* [ ] (Front) Create the cart popup when hovering the icon which will displays
    the cart's content, the quantity, the qty modifier, and the cart total price.
* [ ] (Front) Create the Checkout Page