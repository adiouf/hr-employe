# hr-employe
l'application a été fait dans le cadre d'une auto-formation sur spring/spring boot 
particuliérement la mise en place d'une api rest et d'un web-app
1. Le module api fournit un ensemble de end-point permettant les actions CRUD depuis une BD H2. Les données sont retournées en Json.
2. Le module front : présente les ecrans 
   - home: liste des employés sous forme de tableau
   - formulaire nouveau employé : permet d'ajouter un nouveau employé
   - formulaire modification fiche employé : modifier la fiche d'un employé
   Remarque: la suppression et l'ajout d'un nouveau employé sont réservé au role admin
3. Dockerisation: On peut lancer l'application avec docker et docker compose
* se mettre dans hr-employee/api et lancer la commande mvn spring-boot:build-image -DskipTests
* se mettre dans hr-employee/front et lancer la même commande : mvn spring-boot:build-image -DskipTests
* lancer ensuite docker compose up
* pour arreter les containers docker comppose down
4. OAuth2 avec Github fonctionnel avc mon github

Connexion avec :
- url de connexion :  localhost:8080
- user/user pour un role user
- admin/admin pour un role admin