

Feature: Authentification

	@POEI2-633 @POEI2-705 @POEI2-720
	Scenario Outline: OK : Authentification avec différents comptes
		Given l'utilisateur est sur la page de connexion
		    When il saisit le login "<login>"
		    And il saisit le mot de passe "<password>"
		    And il clique sur le bouton de connexion
		    Then le résultat de la connexion est "<resultat>"
		
		    Examples:
		      | login                     | password        | resultat |
		      | standard_user             | secret_sauce    | succes   |
		      | locked_out_user           | secret_sauce    | erreur   |
		      | problem_user              | secret_sauce    | succes   |
		      | performance_glitch_user   | secret_sauce    | succes   |
		      | standard_user             | wrong_password  | erreur   |
		
	@POEI2-651 @POEI2-705
	Scenario Outline: Authentification avec différents comptes
		Given l'utilisateur est sur la page de connexion
		      When il saisit le login "<username>" et le mot de passe "<password>"
		      Then le statut de la connexion devrait être "<status>"
		
		      Examples:
		        | username | password | status |
		        | standard_user | secret_sauce | success |
		        | locked_out_user | secret_sauce | failed |
		        
		
