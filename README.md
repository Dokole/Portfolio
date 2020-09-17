## Zaitsev Zahar
# Portfolio description
While I was learning Java, Spring and it's ecosystem I wrote a lot of pet-projects, most of them weren't big enough to be shown. 
In this portfolio I released my well-made projects.
### Summary:

## SOAP endpoint application
**Technologies:** Java 14, Spring (Boot, Data, WS, Security), JPA with Hibernate implementation, MySQL database, Liquibase. For SOAP endpoint I used JAXB2 plugin for WSDL and java-objects generation, XSD schema I wrote manually.

**What's this about:** It is data access application with SOAP endpoint. In reality between SQL DB and endpoint would be a lot of business-logic, but in this case there isn't.
It is more about ability to apply a realistic data model to an old technology (SOAP) and managing it, which is widespread in legacy Java applications and applying it to realistic data model.

### Details
**Data layer:** On database layer this application has complex relational schema between a couple of objects.
DDL and initial DB-population are implemented with Liquibase.
Relations between objects in Java code and Data access are implemented with JPA which uses Hibernate.
Repository passes objects to transaction service layer, which can send them to somewhere else, business-logic services, endpoints etc.

**Service layer:** Here I pass objects to SOAP services where it is transformed to DTO objects, and AOP exception handling happens.

**Endpoint layer:**  To create a SOAP endpoint I'm using Spring WS, manually written XSD schema and JAXB generated DTO objects, Request/Response objects and WSDL contract. Basically, for every standard CRUD request there is a SOAP method. When DTOs are provided to endpoint they can be marshalled and sent, or some exception happens and it writes problem in fault part of SOAP message.

**Security layer:** There is simple whitelist IP security implementation. Full implementation in bounded REST application.

## REST endpoint application
**Technologies:** Java 14, Spring (Boot, Data, Web, Security, HAETOAS), JPA with Hibernate implementation, MySQL database, Liquibase, Redis database with Jedis connector.

**What's this about:** 
It is secure REST application which accepting requests via API, processes JWT token validation, looking for requested data in Redis cache and receiving it, if found. If not, it uses SOAP client to receive data from another application (first one in this portfolio), which will be saved in Redis and passes through API.
### Details
**Data layer:** This application receives data objects from SOAP client. For that it connects to WSDL contract URI and generates DTO and request/response classes. After receiving it transforms objects from Dto to inner types and passes to services.
Also, since it secure application it connects to MySQL database for user authorization and registration. This layer similar to SOAP app - JPA with Hibernate and Liquibase for DDL admin user creation and roles.

**Cache layer:** Caching here implemented with Redis and Spring @Cacheble AOP annotations. 
It caches responses from SOAP repository by method+arguments string keys and TTL (time to live) eviction policy.
@CacheEvict invalidates caches related to create, update and delete operations.

**Cache layer in additional versions of REST application:** This portfolio contains **addOn** folder with 2 additional versions of REST service. The difference is - implementation of cache layer. Both of the gives a lot more flexibility than @Cacheble version, but I we don't need it here. 

*First - Caching with JPA CrudRepository.* It gives a lot more possibilities if we need make caching more flexible. Also it does same thing, but takes more time to write.

*Second - Caching with own universal implementation of repository class.* Even more flexibility, frankly it's bad cause uses Redis hashes, which not supports TTL. But I saved it, because it can be reimplemented to make a NoSQL shell around SQL based database. Not sure is it worth yet.

**Service layer:** This is where all transformation happens before data reaches a REST endpoint. Incoming request trying to find a hit in cache, if not goes to SOAP client and saves data in cache after receiving an answer. Before it reaches endpoint data is being improved by HAETOAS model assembler which adds to response object related links to itself, CRUD operations and other objects, which bound to this one. 

I need to point on object creation API. In this application, when we create a new object, which have relation with others, I do not require in request a full scale dependent objects . Only it's id. Service layer, in Dto transformation, replaces it with full object as SOAP client requires it. I think it saves some traffic and time between server and client. For this application provides request which contains objects with Id and name information only, so it's lightweight.

*AddOn* - Service layer, in additional implementations of this app, does the same thing, but designed with more independent layers for better isolation.

**REST controllers:** This layer I kept simple, most of the work took service layer. It is only for receiving requests, parameters and responding.

**Security:** Security implemented with JWT token validation through REST API. It provides user registration, which can be done only by registered user. User with ADMIN role can register a USER or MANAGER role, user with MANAGER role can register only with USER role, USERs can't register anybody. Also there is a login URI which answers with a token. Token needs to be sent with Authorization header for every request. 

**That's it for now.**
