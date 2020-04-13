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
        
        stage('Quality Gate')
        {
    		steps
    		{
    			sleep(10)
				timeout(time: 1, unit: 'MINUTES')
				{
	    		    waitForQualityGate abortPipeline: true    			                           
              	}
    		}
		}
		
		stage('Deploy Backend')
		{
    		steps
    		{
    		    deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001/')], contextPath: 'asks-backend', war: 'target/tasks-backend.war'
    		}
		}
		
		stage('API Tests')
		{
    		steps
    		{
    		    git credentialsId: 'github_login', url: 'https://github.com/rduarte-atomic/tasks-restAssured'
    		    bat 'mvn test'
    		}
		}
    }
}



