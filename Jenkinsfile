pipeline {
    agent any

    tools {
        jdk 'JDK17'
    }

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

        stage('Build Release APK') {
            steps {
                sh './gradlew assembleRelease'
            }
        }

        stage('Build AAB') {
            steps {
                sh './gradlew bundleRelease'
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: '''
            app/build/outputs/apk/debug/*.apk,
            app/build/outputs/apk/release/*.apk,
            app/build/outputs/bundle/release/*.aab
            '''
        }
    }
}