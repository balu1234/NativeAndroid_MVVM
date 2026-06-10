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

        stage('Build Signed Release APK') {
            steps {
                sh './gradlew assembleRelease'
            }
        }

        stage('Build Signed AAB') {
            steps {
                sh './gradlew bundleRelease'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts(
                    artifacts: '''
                        app/build/outputs/apk/release/*.apk,
                        app/build/outputs/bundle/release/*.aab
                    ''',
                    fingerprint: true
                )
            }
        }

       stage('Upload APK to Firebase App Distribution') {
           steps {
               withCredentials([file(credentialsId: 'firebase-sa', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                   sh '''
                       /usr/local/bin/firebase --version

                       /usr/local/bin/firebase appdistribution:distribute \
                       app/build/outputs/apk/release/*.apk \
                       --app mvvmtestapp-7cb07 \
                       --groups "testers" \
                       --release-notes "Automated Jenkins build - APK"
                   '''
               }
           }
       }
       stage('Upload AAB to Firebase App Distribution') {
           steps {
               withCredentials([file(credentialsId: 'firebase-sa', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                   sh '''
                       /usr/local/bin/firebase --version

                       /usr/local/bin/firebase appdistribution:distribute \
                       app/build/outputs/bundle/release/*.aab \
                       --app mvvmtestapp-7cb07 \
                       --groups "testers" \
                       --release-notes "Automated Jenkins build - AAB"
                   '''
               }
           }
       }

    }

    post {

        success {

            emailext(
                subject: "SIGNED BUILD SUCCESS - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                Signed Android Build Completed Successfully

                Project: ${env.JOB_NAME}
                Build Number: ${env.BUILD_NUMBER}

                APK and AAB are generated and uploaded to Firebase App Distribution.

                Check Jenkins:
                ${env.BUILD_URL}
                """,
                to: "rgukt.balu@gmail.com, balaji123.iiit@gmail.com"
            )
        }

        failure {

            emailext(
                subject: "BUILD FAILED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",
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