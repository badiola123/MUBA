pipeline {
    agent any

    stages {
        stage ('Build') {
            steps {
              sh 'mvn clean'

              sh 'mvn sonar:sonar \
  -Dsonar.host.url=http://ec2-34-195-236-81.compute-1.amazonaws.com:9000 \
  -Dsonar.login=b159cf49da251b0f93b797dfebbd619d168077ae'
 
              sh 'sudo cp /var/lib/jenkins/workspace/MUBA_development/MUBA.war /opt/tomcat/webapps/'
              sh 'sudo systemctl restart tomcat'
            }
        }
    }
}
