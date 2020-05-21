# .tusom backend service

Simple Sppring backend service for storing and manipulating locations 
of anonymous users.

To run project, postgres with postgis installed must be running. 
Docker-compose in this repo can be user. Supposing you are in root folder, 
to start postgres you have to run:

```
docker-compose up
```

To run project:
```aidl
./mvnw spring-boot:run
```

### Problem to psql in postgis container


https://stackoverflow.com/questions/18664074/getting-error-peer-authentication-failed-for-user-postgres-when-trying-to-ge


```
apt-get install vim
vim /etc/postgresql/12/main/pg_hba.conf 
/etc/init.d/postgresql reload
```