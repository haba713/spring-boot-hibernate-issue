# Using Spring Boot Developer Tools causes IllegalArgumentException when saving an entity with Hibernate ORM

## The issue

Using Spring Boot Developer Tools causes IllegalArgumentException when saving an entity with Hibernate ORM. The following exception is thrown:

```
java.lang.IllegalArgumentException: object is not an instance of declaring class
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
at org.hibernate.property.access.spi.GetterMethodImpl.get(GetterMethodImpl.java:44) ~[hibernate-core-6.1.6.Final.jar:6.1.6.Final]
... 23 common frames omitted
```

## Sample code

The sample code follows the current [Hibernate ORM Getting Started Guide][1].

We use H2 database with Hibernate 6.1 here but the issue is reproduced also
using PostgresSQL with Hibernate 5.6. 

There's also quite a [similar issue with MySQL][2].

## Steps for reproducing the issue

1. `git clone git@github.com:haba713/spring-boot-hibernate-issue.git`
2. `cd spring-boot-hibernate-issue/`
3. `./gradlew bootRun`
4. See the exception stack trace.
5. Comment out the line
   ```
   developmentOnly 'org.springframework.boot:spring-boot-devtools:3.0.1'
   ```
   in [build.gradle](build.gradle).
6. `./gradlew bootRun`
7. The application works fine.

## Environment

- OS: Debian Bullseye, kernel 5.10.0-10-amd64
- JRE: OpenJDK 17.0.4+8-Debian-1deb11u1
- Java libraries: See [build.gradle](build.gradle) → dependencies or
  `./gradlew -q dependencies --configuration compileClasspath`

[1]: https://docs.jboss.org/hibernate/orm/current/quickstart/html_single/
[2]: https://github.com/spring-projects/spring-boot/issues/7906
