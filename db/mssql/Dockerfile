FROM microsoft/mssql-server-linux:latest
LABEL maintainer="Tobi van Bronswijk <t.vanbronswijk@student.fontys.nl>"

COPY . /
RUN chmod +x seed.sh

CMD /bin/bash ./entrypoint.sh