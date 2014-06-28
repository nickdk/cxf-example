cxf-example
===========

Small example for CXF/JAX-RS REST services. 

Deployed on Heroku with Jetty runner and the Heroku Postgres addon as a backing RDBMS.

- Spring 4
- CXF 3
- Jackson
- JPA 2.1
- Hibernate 4
- Jetty 9
- Servlet 3.1
- SLF4J 1.7.7
- Jedis 2.5.1 (Redis client)
- Logback 1.1.2
- Guava

Set-up heroku tool belt and follow the basic instructions to deploy a java app:

heroku create
git push heroku master

Example paths:

POST http://{heroku-instance}.herokuapp.com/countries
{"name":"Belgium"}

GET http://{heroku-instance}.herokuapp.com/countries

GET http://{heroku-instance}.herokuapp.com/countries/7502f59f-4991-46db-8f2d-0c7adb72c06d

DELETE http://{heroku-instance}.herokuapp.com/countries/7502f59f-4991-46db-8f2d-0c7adb72c06d

Redis caching tests

GET http://{heroku-instance}.herokuapp.com/redis/params/someKey

PUT http://{heroku-instance}.herokuapp.com/redis/params/someKey/someValue
