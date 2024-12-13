# soap postfi-ebill-client

# what

1. it is not trivial to use the Postfinance ebill client with SOAP. You must fine tune the maven dependencies in POM.  
   Soap Docs are old and not great documented.  
   In this demo, it is shown how to config SOAP in Spring.
2. if you want to develop a library for others, there are some ways. One is normal maven lib and one is Spring
   starter.  
   if you choose normal maven lib, the client must config your lib in their projects. with Spring starter everything is
   configured in the starter for Spring app, the client can direct use the configured bean in their project.

technically done in this project:

1. jaxb classes are generated with wsdl and xsd files of postfi ebill api.
1. apache cxf is in using.
1. soap security is configured.
1. slf4j logging of cxf is activated (see META-INF/cxf/org.apache.cxf.Logger).
1. cxf logging level is set in application.yml

# how

## postfinance config

see sop-client

## spring starter

in pom:
'''

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web-services</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

'''
in sop-client-starter/src/main/resources/META-INF/spring.factories  
add your autoConfig class:   
org.springframework.boot.autoconfigure.EnableAutoConfiguration=sop.postfinance.ebill.starter.config.AutoConfiguration

# note

it is important to use the flatten-maven-plugin to flatten the pom, otherwise it will cause an error of a usage project.