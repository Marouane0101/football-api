Football API
Description

API REST développée avec Spring Boot permettant la gestion d'équipes de football et de leurs joueurs.

Fonctionnalités :

- Création d'une équipe avec ou sans joueurs associés.
- Consultation des équipes avec pagination.
- Tri des équipes par nom, acronyme et budget.

Technologies
- Java 17
- Spring Boot 3.5
- Spring Data JPA / Hibernate
- H2 Database
- Lombok
- JUnit 5

Choix techniques
H2 a été utilisée afin de faciliter l'exécution du projet sans installation de base de données externe.
Spring Data JPA simplifie l'accès aux données et la gestion de la persistance.
Les DTO permettent de découpler l'API du modèle de persistance.
Bean Validation est utilisée pour valider les données entrantes.

Lancement du projet

Prérequis :

- Java 17
- Maven

Démarrage :

mvn spring-boot:run

L'application est accessible sur :

http://localhost:8080

Tests

Exécution des tests :

mvn test

Endpoints

Création d'une équipe :

POST /api/teams

Consultation des équipes :

GET /api/teams

Pagination :

GET /api/teams?page=0&size=10

Tri par nom :

GET /api/teams?page=0&size=10&sort=name,asc

Tri par budget :

GET /api/teams?page=0&size=10&sort=budget,desc
