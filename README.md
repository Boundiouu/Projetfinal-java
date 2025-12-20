# Projet Final Java – Gestionnaire de Tâches

## Description
Ce projet est une application JavaFX de gestion de tâches (To-Do List).  
Elle permet à un utilisateur de créer, modifier, supprimer et suivre l’état de ses tâches.  
Les données sont sauvegardées localement afin d’être conservées entre deux exécutions de l’application.

L’objectif du projet est de mettre en pratique :
- Java orienté objet
- JavaFX
- Architecture MVC
- Persistance des données
- Travail en équipe avec Git

---

## Fonctionnalités
- Ajouter une tâche (titre, description, date limite, priorité)
- Modifier une tâche existante
- Supprimer une tâche
- Marquer une tâche comme terminée
- Affichage clair des tâches dans l’interface
- Sauvegarde automatique des tâches
- Rechargement des tâches au démarrage de l’application

---

## Technologies utilisées
- Java 17
- JavaFX
- Maven
- Git / GitHub

---

## Architecture du projet
Le projet suit une architecture **MVC (Model – View – Controller)** :

- **Model**
    - `Task`
    - `TaskManager`

- **View**
    - `main.fxml`
    - `taskForm.fxml`
    - `style.css`

- **Controller**
    - `TaskController`
    - `TaskFormController`

- **Persistance**
    - `FileManager` (sauvegarde et chargement des tâches dans un fichier)

---

## Persistance des données
Les tâches sont sauvegardées localement dans un fichier texte à l’aide de la classe `FileManager`.  
Chaque modification (ajout, suppression, modification) déclenche une sauvegarde automatique.  
Au lancement de l’application, les tâches sont rechargées depuis ce fichier.

---

## Lancer le projet

### Prérequis
- Java 17 installé
- Maven installé

### Commande
À la racine du projet :

```bash
mvn javafx:run
