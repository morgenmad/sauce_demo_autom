Feature: Connexion utilisateur

  Scenario: Connexion avec identifiants valides
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "standard_user" et le mot de passe "secret_sauce"
    Then il est redirigé vers la page d'accueil

  Scenario: Tentative de connexion avec identifiants invalides
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "locked_out_user" et le mot de passe "secret_sauce"
    Then la connexion est échouée et un message s'affiche

  Scenario Outline: Authentification avec différents comptes
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "<username>" et le mot de passe "<password>"
    Then le statut de la connexion devrait être "<status>"

    Examples:
    | username | password | status |
    | standard_user | secret_sauce | success |
    | standard_user | secret_sauce | failed |


