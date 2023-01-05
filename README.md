# Spring Boot Developer Tools not compatible with Hibernate ORM

## The issue

Spring Boot Developer Tools breaks saving Java entities with Hibernate ORM by
causing the following exception:

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

We use H2 database here but the issue is reproduced also with PostgresSQL.

There's also a [similar issue about MySQL][1] but for some reason it has been
closed without fixing the actual issue.

## Steps for reproducing the issue

1. `git clone git@github.com:haba713/spring-boot-hibernate-issue.git`
2. `cd spring-boot-hibernate-issue/`
3. `./gradlew bootRun`
4. See the exception stack trace.
5. Comment the line
   ```
   // developmentOnly 'org.springframework.boot:spring-boot-devtools:3.0.1'
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