 // #!groovy
 // the pipeline script used on jenkins ...
 node {
     stage('Checkout') { // for display purposes
         git 'https://github.com/lmarinkov/micro-services.git'
         // clean up git locally
         sh "git clean -f && git reset --hard origin/master"
         def pom = readMavenPom file: 'pom.xml'
         def version = pom.version.replace("-SNAPSHOT", ".${currentBuild.number}")
     }
     stage('Build') {
         //bat(/"mvn" -Dmaven.test.failure.ignore clean package/)
         bat(/"mvn" -DreleaseVersion=${version} -DdevelopmentVersion=${pom.version} -DpushChanges=false -DlocalCheckout=true -DpreparationGoals=initialize release:prepare release:perform -B"/)
     }
 }
 