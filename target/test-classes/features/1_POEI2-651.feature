Feature: KO : Authentification avec différents comptes

	@POEI2-651
	Scenario Outline: Authentification avec différents comptes
		Given l'utilisateur est sur la page de connexion
		When il saisit le login "<username>" et le mot de passe "<password>"
		Then le statut de la connexion devrait être "<status>"
		
		Examples:
		| username | password | status |
		| standard_user | secret_sauce | success |
		| locked_out_user | secret_sauce | failed |
		
