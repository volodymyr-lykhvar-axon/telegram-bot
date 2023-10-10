FROM maven:3.8.3-openjdk-17-slim

LABEL maintainer="vlykhvar@axon.dev"

# Create app directory
RUN mkdir -p /liquibase
WORKDIR /liquibase

COPY pom.xml /liquibase/pom.xml
COPY liquibase.properties /liquibase/liquibase.properties
ADD changesets.tar.gz /liquibase

# Add entrypoint script to replace properties to access database
ADD replace-liquibase-properties.sh /liquibase/replace-liquibase-properties.sh
RUN chmod +x /liquibase/replace-liquibase-properties.sh

# Add startup file
ADD startup.sh /opt/startup.sh

RUN chmod +x /opt/startup.sh

ENTRYPOINT ["/liquibase/replace-liquibase-properties.sh"]
CMD ["/opt/startup.sh"]