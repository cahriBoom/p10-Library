# OC Library

Avec l’objectif de moderniser la gestion de ces bibliothèques.
À ce titre, plusieurs axes d’amélioration ont été identifiés dont la gestion des emprunt de livres via une application web et une base de donnée.  

## Présentation

Ce projet est un site web de gestion d'une bibliothèque il est divisé en 3 parties :
- Le BackEnd pour l'administrateur (Project7-Back)
- le FrontEnd pour les utilisateurs (Project7-Front)
- le Batch pour envoyer les rappels de retours de livres (Project7-Batch)


Ce projet est conçu sur **Windows**, en utilisant **Eclipse** comme outil de développement et **MySql** comme système de gestion de base de données.
**Apache Maven** est utilisé pour la gestion et l'automatisation de production des projets logiciels Java. Et le framework **Spring boot** est utilisé afin de facilité le développement de l'application.

### Pré-requis

- Windows 2010
- Java jdk version 
- MySQL version 
- un serveur Tomcat version XXX
- un gestionnaire de base de données SQL (MySQL Workbench, ...)


### Déploiement du projet
- Télécharger la version la plus récente d'Apache Tomcat.
- Telecharger p10-Library dans votre environnement (ex : D:\p10-Library)
- Extraire les dossiers de l'archive : p10-library 

### Déploiement Tomcat
- Copier votre dossier Tomcat dans votre environnement


### Déploiement des fichiers WAR
- Importer le projet dans votre outil de développement (Eclipse, InteliJ, ...) en fichier **.war**
- Copier les fichiers .war dans **/webapps/** du sous-dossier tomcat


### Démarrage des serveurs Tomcat
- Lancer une 'Invite de commandes' et naviguer jusqu'au dossier de l'instance ou faites le manuellement.
- Lancer l'instance tomcat en exécutant le fichier **startup.bat**


### Création et Initilisation de la Base de données
- Ouvrir votre outil de gestion de base de données. (MySQL WorkBench,...)
- Créer la base de données à l'aide du fichier "LibraryDump.sql".
- Importer le jeu de données fournit dans "LibraryData.sql".


## Démarrage

Une fois l'installation terminée.

Lancez un navigateur web, et entrez l'adresse:
> [localhost:8081/libraryFront/accueil](http://localhost:8081/libraryFront/accueil)

## Développé avec

* [Eclipse] - Environement de développement
* [Tomcat] - Serveur Web
* [MySQL] - Base de données
* [MySQL Workbench] - Gestionnaire de base de données


## Auteurs
* **Maxime Cathala** _alias_ [@cahriBoom](https://github.com/cahriBoom)
# p10-Library
