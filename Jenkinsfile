pipeline {
    agent any
    stages {
	
		stage ('Build') {
            steps {
			  sh 'mvn clean compile install'
            }
        }
        stage ('Test') {
            steps {
			  sh 'mvn jacoco:prepare-agent test jacoco:report'
              sh 'mvn sonar:sonar \
  -Dsonar.host.url=http://ec2-34-195-236-81.compute-1.amazonaws.com:9000 \
  -Dsonar.login=b159cf49da251b0f93b797dfebbd619d168077ae'
            }
        }
		stage ('Generate documentation') {
            steps {
              sh 'mvn site'
            }
        }
		stage ('Deploy') {
            steps {
              sh 'sudo cp /var/lib/jenkins/workspace/MUBA_development/target/MUBA-0.0.1-SNAPSHOT.war /opt/tomcat/webapps/MUBA_development.war'
              sh 'sudo service tomcat restart'
            }
        }
    }
}