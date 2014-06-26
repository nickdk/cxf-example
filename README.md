cxf-example
===========

Small example for CXF REST services deployed on Heroku with Jetty runner

- Spring 4
- CXF 3
- Jetty 9
- Servlet 3.1
- Jackson
- SLF4J 1.7.7
- Jedis 2.5.1 (Redis client)
- Logback 1.1.2

If you've already set-up heroku tool belt on your machine:

git push heroku master

Example paths:

GET http://{heroku-instance}.herokuapp.com/country/1

GET http://{heroku-instance}.herokuapp.com/dealer/1

GET http://{heroku-instance}.herokuapp.com/redis/params/someKey

PUT http://{heroku-instance}.herokuapp.com/redis/params/someKey/someValue
