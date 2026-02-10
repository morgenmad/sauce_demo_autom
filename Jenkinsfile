pipeline {
    agent any

    environment {
        TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiNmNhZGQwNS1lMzQxLTNmMTctYjU1Zi00OTM0MTI4MWQ4MmEiLCJhY2NvdW50SWQiOiI3MDEyMToyNTFlNzRkOC05M2E4LTQyNWItYTk3NC02NTBiMjg3YTI0NmQiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTc3MDcyNTQzNSwiZXhwIjoxNzcwODExODM1LCJhdWQiOiJFNkRGMEUxQjJDRDM0RjdFQUE3Q0ZBQUMwNjJFOThEQyIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IkU2REYwRTFCMkNEMzRGN0VBQTdDRkFBQzA2MkU5OERDIn0.t5wLzidX4pSdoKplFn__0NCFmZ4DTFUyOCay2-JsLgs"
    }

    parameters {
        string(name: 'SELENIUM_BROWSER', defaultValue: 'CHROME')
        string(name: 'TEST_PLAN', defaultValue: 'POEI2-713')
    }
    triggers {
        cron('30 14 * * 2')
    }

    stages {

        stage('Export features') {
            steps {
                echo 'Exportation des features depuis Xray...'
                bat 'curl -H "Content-Type: application/json" -X GET -H "Authorization: Bearer %TOKEN%"  "https://xray.cloud.getxray.app/api/v1/export/cucumber?keys=%TEST_PLAN%" --output features.zip'
                bat 'if not exist "src/test/resources/features" mkdir "src/test/resources/features"'
                bat 'tar -xf features.zip -C src/test/resources/features'
                bat 'del features.zip'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Execution des tests Cucumber via Maven...'
                bat 'mvn clean test'
            }
        }

    }

    post {
        always {
            echo 'Importation des résultats d\'exécution vers Xray...'
            bat 'curl -H "Content-Type: application/json" -X POST -H "Authorization: Bearer %TOKEN%"  --data @"target/cucumber.json" https://xray.cloud.getxray.app/api/v1/import/execution/cucumber'
        }

        success {
            echo 'Tests exécutés avec succès 🎉'
        }

        failure {
            echo 'Des tests ont échoué ❌'
        }
    }
}