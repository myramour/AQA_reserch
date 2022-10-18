pipeline {
    agent any
    stages {
         stage('Build') {
             steps{
                sh '''export M2_HOME=/opt/homebrew/Cellar/maven/3.8.6/libexec
                      export PATH=$PATH:$M2_HOME/bin
                      mvn clean test -DskipTests=true'''
            }
       }
       stage('Run tests') {
             steps{
                sh '''export M2_HOME=/opt/homebrew/Cellar/maven/3.8.6/libexec
                      export PATH=$PATH:$M2_HOME/bin
                      mvn clean test -Dconfig="${CONFIG}" -DsuiteXml=${SUITE}'''
            }
       }
    }
    post{
            archiveArtifacts artifacts: 'target/logs/*', allowEmptyArchive: true
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
    }
}