.PHONY: setup teardown install-db destroy-db

DM=gcloud deployment-manager deployments

# Database related
DM_DB_CONF=deployment-db.yaml
DM_DB_NAME=gcpmamie-ssd-db

setup: install-db

teardown: destroy-db

install-db:
	$(DM) create $(DM_DB_NAME) --config $(DM_DB_CONF)

destroy-db:
	$(DM) delete $(DM_DB_NAME) -q
