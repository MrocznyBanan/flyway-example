FROM airhacks/wildfly
MAINTAINER Mroczny Banan, mrocznybanan.eu
COPY ./target/flyway.war ${DEPLOYMENT_DIR}