* On your local device, authorize the docker deamon to push on GCR 
	`gcloud auth configure-docker`
* Create the docker image with the GCR tag:
	`docker build -t eu.gcr.io/$PROJECT_ID/$IMG_ID:latest`
* Push the image:
	`docker push`
* Use create_cluster.sh to create the K8S cluster
* Create a Service account which will have access to CloudSQL:
	`gcloud iam service-accounts create $SERVICE_ACCOUNT_NAME --display-name "Whatever you want"`
* Add the client SQL role to the SA :
	`gcloud projects add-iam-policy-bindings $PROJECT_ID --member="serviceAccount:$SERVICE_ACCOUNT_NAME@$PROJECT_ID.iam.gserviceaccount.com" --role="roles/cloudsql.client"`
* Export the SA in a JSON:
	`gcloud iam service-accounts keys create --iam-account "$SERVICE_ACCOUNT_NAME@$PROJECT_ID.iam.gserviceaccount.com" service-account.json`
* Authorize & link kubectl to your cluster : 
	`gcloud container clusters get-credentials back --zone=$CLUSTER_ZONE`
* Create the service account secret in K8S: 
	`kubectl create secret generic cloudsql-sa-json --from-file=credentials.json=service-account.json`
* Apply the pods descriptor to the k8s cluster :
	`kubectl apply -f backend-k8s-deployment.yaml`


