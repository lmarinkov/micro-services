// #!groovy
// the pipeline script used on jenkins ...
node {
    def tag
    def version
    stage('Checkout') { // for display purposes
        scm {
            git {
                remote {
                    github('lmarinkov/https://github.com/lmarinkov/micro-services.git', 'https')
                    credentials('7f208d5a-2436-426d-9479-748160abc493')
                }
            }
        }
        git 'https://github.com/lmarinkov/micro-services.git'
        // clean up git locally
        //bat (/"git clean -f && git reset --hard origin/master"/)
        def pom = readMavenPom file: 'pom.xml'
        version = pom.version.replace("-SNAPSHOT", ".${currentBuild.number}")
        echo pom.toString()
        echo version.toString()
        echo currentBuild.toString()
        tag = pom.artifactId + "-" + version
    }
    stage('Release:'+ version.toString()) {
        bat(/"mvn" -f service\pom.xml versions:set -DnewVersion=${version}/)
    }
    stage('Build') {
        bat(/"mvn" -f service\pom.xml clean install/)
    }
    stage('Run Firewall Tests') {
        bat(/"mvn" -f consumer\pom.xml clean install/)
    }
    stage('Tag the SCM') {
        bat(/"mvn" deploy scm:tag -Dtag=${tag}/)
    }
}
 
