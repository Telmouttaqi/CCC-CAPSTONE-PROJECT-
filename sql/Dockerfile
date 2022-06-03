FROM mariadb:10.7.3


COPY *.sql /docker-entrypoint-initdb.d/

ENV MARIADB_ROOT_PASSWORD=ConnectThoiBaCon

EXPOSE 3306/tcp
