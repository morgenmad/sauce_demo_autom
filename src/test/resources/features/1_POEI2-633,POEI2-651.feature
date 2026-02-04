@POEI2-715
Feature: Exécution des Tests de Plan de Test POEI2-713

	@POEI2-633 @POEI2-705
	Scenario Outline: OK : Authentification avec différents comptes
		Given l'utilisateur est sur la page de connexion
		When il saisit le login "<username>" et le mot de passe "<password>"
		Then le statut de la connexion devrait être "<status>"
		
		Examples:
		| username | password | status |
		| standard_user | secret_sauce | success |
		| locked_out_user | secret_sauce | failed |
		
	@POEI2-651 @POEI2-705
	Scenario Outline: Authentification avec différents comptes
		Given l'utilisateur est sur la page de connexion
		      When il saisit le login "<username>" et le mot de passe "<password>"
		      Then le statut de la connexion devrait être "<status>"
		
		      Examples:
		        | username | password | status |
		        | standard_user | secret_sauce | success |
		        | standard_user | secret_sauce | failed |
		
