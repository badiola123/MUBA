pipeline {
    agent any

    stages {
        stage ('Build') {
            steps {
              sh 'mvn clean compile install'
              sh 'mvn sonar:sonar \
  -Dsonar.host.url=http://ec2-34-195-236-81.compute-1.amazonaws.com:9000 \
  -Dsonar.login=b159cf49da251b0f93b797dfebbd619d168077ae'
              sh 'mvn jacoco:prepare-agent test jacoco:report'
              sh 'sudo cp /var/lib/jenkins/workspace/UBA_webserver_Tomcat_deploy_test/target/MUBA-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/MUBA_jenkinstest.war'
              sh 'sudo service tomcat restart'
            }
        }
    }
}
