pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/balu1234/NativeAndroid_MVVM.git'
            }
        }

        stage('Grant Permission') {
            steps {
                sh 'chmod +x gradlew'
            }
        }

        stage('Clean') {
            steps {
                sh './gradlew clean'
            }
        }

        stage('Unit Test') {
            steps {
                sh './gradlew testDebugUnitTest'
            }
        }

        stage('Lint') {
            steps {
                sh './gradlew lintDebug'
            }
        }

        stage('Build Debug APK') {
            steps {
                sh './gradlew assembleDebug'
            }
        }

        stage('Archive APK') {
            steps {
                archiveArtifacts(
                    artifacts: 'app/build/outputs/**/*.apk',
                    fingerprint: true
                )
            }
        }
    }

    post {

        success {

            archiveArtifacts(
                artifacts: 'app/build/outputs/**/*.apk',
                fingerprint: true
            )

            emailext(
                subject: "Android Build Success - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
    Build Successful

    Project: ${env.JOB_NAME}
    Build Number: ${env.BUILD_NUMBER}

    Check Jenkins:
    ${env.BUILD_URL}
    """,
                to: "rgukt.balu@gmail.com, balaji123.iiit@gmail.com"
            )
        }

        failure {

            emailext(
                subject: "Android Build Failed - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
    Build Failed

    Project: ${env.JOB_NAME}
    Build Number: ${env.BUILD_NUMBER}

    Check logs:
    ${env.BUILD_URL}
    """,
                to: "rgukt.balu@gmail.com, balaji123.iiit@gmail.com"
            )
        }
    }
}