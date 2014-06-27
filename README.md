cxf-example
===========

Small example for CXF REST services deployed on Heroku with Jetty runner

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

If you've already set-up heroku tool belt on your machine:

git push heroku master

Example paths:

GET http://{heroku-instance}.herokuapp.com/countries

POST http://{heroku-instance}.herokuapp.com/countries
{"name":"Belgium"}

GET http://{heroku-instance}.herokuapp.com/countries/1

GET http://{heroku-instance}.herokuapp.com/redis/params/someKey

PUT http://{heroku-instance}.herokuapp.com/redis/params/someKey/someValue
