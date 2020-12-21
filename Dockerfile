FROM maven:3-openjdk-15-slim AS build

MAINTAINER gachakra.kangin@notonza.dev

ENV HOME=/home/app
RUN mkdir -p $HOME

## Install Maven dependencies first to cache them
COPY pom.xml $HOME
WORKDIR $HOME
RUN mvn verify clean --fail-never

## Create jar file
COPY src $HOME/src
RUN mvn -B package

## Check MANIFEST.MF
#RUN apt-get update \
#    && apt-get -qq -y install unzip
#WORKDIR /home/app
#RUN unzip target/shinsei-bank-t-point-campaign-assign-1.0-SNAPSHOT.jar \
#    && cat META-INF/MANIFEST.MF


FROM openjdk:15-alpine AS package

COPY --from=build /home/app/target/shinsei-bank-t-point-campaign-assign-1.0-SNAPSHOT.jar /usr/local/app.jar
COPY --from=build /home/app/resources /usr/local/
COPY --from=build /root/.m2/repository /usr/local/libs

WORKDIR /usr/local/
ENTRYPOINT ["java", "-jar", "app.jar"]