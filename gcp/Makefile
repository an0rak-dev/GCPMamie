.PHONY: setup teardown install-db destroy-db stop-all start-all

DM=gcloud deployment-manager deployments

# Database related
DM_DB_CONF=deployment-db.yaml
DM_DB_NAME=gcpmamie-ssd-db

setup: install-db

teardown: destroy-db

install-db:
	#$(DM) create $(DM_DB_NAME) --config $(DM_DB_CONF)

destroy-db:
	#$(DM) delete $(DM_DB_NAME) -q

stop-all:
	gcloud sql instances patch gcpmamie-prod-9 --activation-policy NEVER
	gcloud container clusters resize backend --node-pool default-pool \
    --num-nodes 0 --zone europe-west1-b --quiet

start-all:	
	gcloud sql instances patch gcpmamie-prod-9 --activation-policy ALWAYS
	gcloud container clusters resize backend --node-pool default-pool \
    --num-nodes 3 --zone europe-west1-b --quiet

redeploy-back:
	gcloud container clusters resize backend --node-pool default-pool \
	 --num-nodes 0 --zone europe-west1-b --quiet
	gcloud container clusters resize backend --node-pool default-pool \
	 --num-nodes 3 --zone europe-west1-b --quiet

create-group:
	gcloud compute instance-groups managed create fulf-group  \
    --base-instance-name=fulf  \
    --size 1  \
    --template fulf-template  \
    --zone europe-west1-b

scale-group:
	gcloud beta compute instance-groups managed set-autoscaling fulf-group  \
    --zone europe-west1-b  \
    --min-num-replicas=0 --max-num-replicas=8  \
    --update-stackdriver-metric=pubsub.googleapis.com/subscription/num_undelivered_messages  \
    --stackdriver-metric-filter="resource.type=pubsub_subscription AND resource.label.subscription_id=nextOrder"  \
    --stackdriver-metric-single-instance-assignment=1










