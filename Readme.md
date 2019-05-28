# Talk "Comment la GCP m'a permis de sauver ma Prod chez Mamie"

## How to run it Locally

Here's a few steps to build this app and make it run locally. You'll need a 
running Docker instance and either NodeJS or Python3.

All the following commands are supposed to be run from this directory.

### Step 1 : Build the Database and the server image

_Note : Note that the datas will not persisted since its only expected to be run_
_locally for dev or tests purpose._

```
$ cd datas
$ docker build -t gcpmamie-db .
$ cd ../back
$ docker build -t gcpmamie-server .
```

### Step 2 : Run the backend and the database

```
$ docker-compose up
```

The backend will listen on `localhost:8081ù 

### Step 3 : Run the client 

If you have NodeJS, you can use thoses commands
```
$ cd front/
$ npm install http-server
$ http-server -p 8080 
```

If you have Python3, you can use thoses commands
```
$ cd front/
$ python3 -m http.server 8080
```

## How to run it on GCP

### Prepare your GCP project

Once you've created your GCP project, add the role `roles/cloudsql.admin` to the
Deployement Manager's service account 
(`<projectnumber>@cloudservices.gserviceaccount.com`).

### Create the infrastructure

In the cloud shell console (or if you have a `gcloud` command installed locally),
run : 

```
$ cd gcp/
$ make setup
```

or you can create the infrastructure, resource by resource using :

```
cd gcp/
$ make install-db
```

### Clean up 

If you want to clean up your project, you can either remove everything with :
```
$ cd gcp/
$ make teardown
```

or cleaning up resource by resource : 
```
$ cd gcp/
$ make destroy-db
```

## TodoList

### Mandatory 
* [X] (Front) Generate the products in javascript loop instead of hardcoded
* [X] (Front) Create the Error Page
* [X] (Front) Create a `constants.js` file which will contains local & GCP
    url to the backend.
* [X] (Front) Call the backend API to get all the products
* [X] (Front) Call the backend API to add a product to cart
* [X] (Back) Create the Springboot backend Docker image
* [X] (Back) Add an endpoint to return the list of all the products' summaries
* [X] (Back) Add an endpoint to return the json description of a specific
    product
* [X] (Back) Add an endpoint to add a product in the cart
* [X] (Back) Add an endpoint to get the cart count
* [X] (Data) Create the Database structure in a PostgreSQL instance
* [X] (Data) Fill the database with dummy products
* [X] (Batch) Create a simple dummy/mock batch application which will display
    random orders informations at each Pub/Sub message
* [ ] Create a DM script to organize the setup and teardown of the platform
* [ ] Create a makefile to easily setup/teardown and startup/shutdown the resources
* [ ] (Front) Deploy on GCS
* [ ] (Front) Store the images in GCP CDN
* [ ] (Back) Add a cloud build task to build the backend image and push it on 
    GCR
* [ ] (Back) Deploy on GKE
* [ ] (Back) Add the GCP Endpoints
* [ ] (Data) Apply the database structure & init to a GCP CloudSQL
* [ ] (Batch) Create the Pub/Sub topic
* [ ] (Batch) Deploy 2 instances of the VM batch using an instance template.
* [ ] (Batch) Prepare the way to easily replace the 2 hard provisioned VMs to an instance 
    group of 10 VMs
(* [ ] (Batch) Prepare the script which will overflow the pubsub topic and the way 
    to easily upgrade the instance group temporarly if previous step failed.)

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
