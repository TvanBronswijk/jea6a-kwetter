FROM payara/server-full:5.181
LABEL Maintainer="Tobi van Bronswijk <t.vanbronswijk@student.fontys.nl>"

RUN curl http://repo2.maven.org/maven2/com/microsoft/sqlserver/mssql-jdbc/6.2.2.jre8/mssql-jdbc-6.2.2.jre8.jar -o $PAYARA_PATH/glassfish/lib/mssql-jdbc-6.2.2.jre8.jar

COPY kwetter.war $PAYARA_PATH/glassfish/domains/domain1/autodeploy/

EXPOSE 4848 8080