# docker build -t gcpmamie-db .
# docker run --rm gcpmamie-db
# docker exec -it gcpmamie-db /bin/bash
#		psql -U ssd_pg -d postgres

FROM postgres:11

COPY init.sql /docker-entrypoint-initdb.d/init.sql
