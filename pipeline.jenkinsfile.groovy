// #!groovy
// the pipeline script used on jenkins ...
node {
    def tag
    def version
    stage('Checkout') { // for display purposes
        //git credentialsId: '7f208d5a-2436-426d-9479-748160abc493', 'https://github.com/lmarinkov/micro-services.git'
        //git credentialsId: '7f208d5a-2436-426d-9479-748160abc493', url: 'https://github.com/lmarinkov/micro-services.git'
        git url: 'https://github.com/lmarinkov/micro-services.git'

        // clean up git locally
        //bat (/"git clean -f && git reset --hard origin/master"/)
        def pom = readMavenPom file: 'service/pom.xml'
        version = pom.version.replace("-SNAPSHOT", ".${currentBuild.number}")
        echo pom.toString()
        echo version.toString()
        echo currentBuild.toString()
        tag = pom.artifactId + "-" + version
    }
    stage('Release:'+ version.toString()) {
        // Change the version of the project using the current build number
        bat(/"mvn" -f service\pom.xml versions:set -DnewVersion=${version}/)
    }
    stage('Build') {
        // Check all tests
        bat(/"mvn" -f service\pom.xml clean install/)
    }
    stage('Run Firewall Tests') {
        bat(/"mvn" -f consumer\pom.xml clean install/)
    }
    stage('Tag the SCM') {
        bat(/"mvn" deploy scm:tag -Dtag=${tag}/)
    }
}
 
