 #!groovy
 // the pipeline script used on jenkins ...
 node {
     stage('Preparation') { // for display purposes
         git 'https://github.com/lmarinkov/micro-services.git'
     }
     stage('Build') {
         bat(/"mvn" -Dmaven.test.failure.ignore clean package/)
     }
 }
 