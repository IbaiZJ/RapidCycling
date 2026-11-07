# ADR 1: Persistence with MySQL and Spring Data JPA


## Context

Rapid Cycling needs to store related data, such as users, bikes, stations, and rides. These data are connected to each other and must remain available when the application restarts.

## Decision

We will use MySQL as the relational database and Spring Data JPA to access the data. MySQL was chosen over PostgreSQL because the team has more experience with it. The database runs with Docker Compose. Java entities represent the tables, and Spring Data JPA repositories handle read and write operations.

## Alternatives considered

- **MongoDB:** not selected because the project data has clear relationships and fits well into relational tables.
- **PostgreSQL:** considered, but MySQL was preferred because the team has more experience with it.
- **Plain JDBC:** not selected because it would require writing and maintaining more queries and data access code.

## Consequences

- We can represent relationships between users, bikes, stations, and rides using keys and associations.
- Spring Data JPA reduces the amount of code needed for common operations.
- MySQL and its schema must be configured and maintained. For now, Hibernate updates the schema automatically during development (`spring.jpa.hibernate.ddl-auto=update`).
- The application depends on MySQL being available to store and retrieve data.
