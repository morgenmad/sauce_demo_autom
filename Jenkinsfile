pipeline {
    agent any

    environment {
        XRAY_CLIENT_ID     = "E6DF0E1B2CD34F7EAA7CFAAC062E98DC"
        XRAY_CLIENT_SECRET = "95ca35b6c8cabffe5d0c5ec1653d3566a75de04b811e28a54850316c362f2be7"
    }

    stages {

        stage('Checkout') {
            steps {
                echo'Projet local - pas de checkout Git'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Execution des tests Cucumber via Maven...'
                bat 'cd SauceLabCucumber && mvn clean test'


            }
        }

        stage('Export to Xray') {
            steps {
                echo 'Envoi du rapport JSON vers Xray...'

                // 1. Générer le token
                bat '
                    curl -H "Content-Type: application/json" ^
                         -X POST ^
                         -d "{\"client_id\": \"%XRAY_CLIENT_ID%\", \"client_secret\": \"%XRAY_CLIENT_SECRET%\"}" ^
                         https://xray.cloud.getxray.app/api/v2/authenticate ^
                         -o token.txt
               '

                // Lire le token
                bat 'set /p XRAY_TOKEN=<token.txt'

                // 2. Envoyer le JSON Cucumber
                bat '
                    curl -H "Authorization: Bearer %XRAY_TOKEN%" ^
                         -H "Content-Type: application/json" ^
                         -X POST ^
                         --data-binary @SauceLabCucumber/target/cucumber.json ^
                         https://xray.cloud.getxray.app/api/v2/import/execution/cucumber
                '
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminé'
        }
    }
}