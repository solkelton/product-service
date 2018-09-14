pipeline {
    agent any


    stages {
       stage('Build') {
          steps {
             sh 'gradle clean compileJava'
             sh './gradlew clean build'
          }
       }
       stage('Deploy'){
                  steps{
                      sh 'cf push product-service -p ./build/libs/product-service-0.0.1-SNAPSHOT.jar'
                  }
       }
    }
}