# Trews Service

Backend for [Trews][1] app, written with Play Framework and Postgres database.

## Requirements

 - [PostgreSQL][2]
 - [Play][3]
 
## Run

After installing PostgreSQL and Play successfully, it's time to run the service on your machine. 

Here are commands to run on MacOS (because I use MacOS 😅). If you want to run service on another OS, try the following steps with suitable commands. It won't be too complicated.
 
Run Postgres.

    pg_ctl -D /usr/local/var/postgres start
    
Create a database with name "trews" if you didn't.

    createdb trews
    
Run Trews Service under `localhost:9000`
  
    sbt run
    
Now, the service is ready to receive requests from [Trews][1] app.
 
 [1]: https://github.com/trungducc/trews-ios
 [2]: https://www.postgresql.org
 [3]: https://www.playframework.com
