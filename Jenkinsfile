pipeline
{
    agent any
    stages
    {
        stage('Build Backend')
        {
        	steps
        	{
        		bat 'mvn clean package -DskipTests=true'    
        	}
        }
		stage('Unit Tests')
        {
        	steps
        	{
        		bat 'mvn test'    
        	}
        }
		stage('Sonar Analysis')
        {
        	environment {
	            scannerHome = tool 'SONAR_SCANNER'
	        }
        	steps
        	{
        		withSonarQubeEnv('SONAR_LOCAL')
        		{
	        		bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://192.168.99.100:9000 -Dsonar.login=b64715c9397eda3413b9849576be504687b7b3a1 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"           		    
        		}
        	}
        }
    }
}



