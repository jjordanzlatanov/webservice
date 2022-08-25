## Setup Database
sudo -i -u postgres psql  
create user webservice with createdb password 'password1234';  
create database wbservice owner webservice;

## Add tables with schema
mvn clean package  
java -jar target/webservice-1.0-SNAPSHOT.jar db migrate webservice.yaml

## Go back to Postgres
sudo -i -i postgres psql  
\c wbservice  
<Create Postgres Functions>
