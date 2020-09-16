## Zaitsev Zahar
# Portfolio description
While I was lerning Java, Spring and it's ecosystem I wrote a lot of pet-projects, most of them weren't big enought to be shown. 
In this portdolio I released my largest and well made projects.
### Summary:

## SOAP endpoint application
**Technologies:** Java 14, Spring (Boot, Data, WS, Security), JPA with Hibernate implementation, MySQL database, Liquibase. For SOAP endpoint I used JAXB2 plugin for WSDL and java-objects generation, XSD schemas I wrote manually.

**What's this about:** It is data access application with SOAP endpoint. In reality between SQL DB and endpoint would be a lot of business-logic, but in this case there isn't.
It is more about ability to apply a realistic data model to an old technology (SOAP) and managing it, which is widespread in legacy Java applications and applying it to realistic data model.

### Details
**Data layer:** On database layer this application has complex relational schema between a couple of objects.
DDL and initial DB-population are implemented with Liquibase.
Reletions between objects in Java code and Data access are imblemented with JPA which uses Hibernate.
Repository passes objects to transaction service layer, which can send them to somwere else, business-logic services, endpoints etc.

**Service layer:** Here I pass objects to SOAP services where it is casted to DTO objects, and AOP exception handling happens.

**Endpoint layer:**  To create a SOAP endpoit I'm useing Spring WS, manualy written XSD schema and JAXB generated DTO objects, Request/Response objects and wsdl contract. Basically, for every standart CRUD request there is a SOAP method. When DTOs are provided to endpoint they can be marshalled and sent, or an exception.

**Security layer:** There is simple whitelist ip security implementation. Full implementation in bounded REST application.

## REST endpoint application
**Techologies:** Java 14, Spring (Boot, Data, Web, Security, HAETOAS), JPA with Hibernate implementation, MySQL database, Liquibase, Redis database with Jedis connector.

**What's this about:** 
It is secure REST application which accepting requests via API, processes JWT token validation, looking for requested data in Redis cahe (hash) and receiving it if found. If not, it uses SOAP client to receive data from another application (first one in this portfolio), which will be saved in Redis and passes throught API.
### Details
**Data layer:**

**Cache layer:**

**Service layer:**

**REST controllers:**

**Security:**


