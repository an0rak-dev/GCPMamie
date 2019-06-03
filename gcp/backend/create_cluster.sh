#!/bin/sh

CLUSTER_NAME=backend
ZONE=europe-west1-b

gcloud container clusters create $CLUSTER_NAME \
	--scopes=gke-default,https://www.googleapis.com/auth/cloud_debugger \
	--enable-autoupgrade \
	--machine-type=n1-standard-1 \
	--maintenance-window=02:00 \
	--node-locations=$ZONE \
	--num-nodes=3 \
	--enable-autoscaling --max-nodes=5 --min-nodes=1 \
	--zone=$ZONE \
	--disk-size=10G \
	--disk-type=pd-standard
