FROM oracle/glassfish:5.0
LABEL Maintainer="Tobi van Bronswijk <t.vanbronswijk@student.fontys.nl>"

RUN curl http://repo2.maven.org/maven2/com/microsoft/sqlserver/mssql-jdbc/6.2.2.jre8/mssql-jdbc-6.2.2.jre8.jar -o $GLASSFISH_HOME/glassfish/lib/mssql-jdbc-6.2.2.jre8.jar

COPY kwetter.war $GLASSFISH_HOME/glassfish/domains/domain1/autodeploy/

EXPOSE 4848 8080