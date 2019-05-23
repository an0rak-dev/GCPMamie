# docker build -t gcpmamie-db .
# docker run --rm gcpmamie-db
# docker exec -it gcpmamie-db /bin/bash
#		psql -U ssd_pg -d postgres

FROM postgres:11

COPY 01_init.sql /docker-entrypoint-initdb.d/01_init.sql
COPY 02_load-products.sql /docker-entrypoint-initdb.d/02_load-products.sql
COPY 03_load-stocks.sql /docker-entrypoint-initdb.d/03_load-stocks.sql
