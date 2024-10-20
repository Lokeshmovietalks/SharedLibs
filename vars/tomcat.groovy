def call(credID,filename,userID,PrivateIP){
  sshagent(["${credID}"]){
	sh "scp -o StrictHostKeyChecking=no target/${filename}.war ${userID}@${PrivateIP}:/opt/tomcat9/webapps/"
	sh "ssh ${userID}@${PrivateIP} /opt/tomcat9/bin/shutdown.sh"
	sh "ssh ${userID}@${PrivateIP} /opt/tomcat9/bin/startup.sh"
  }
}
def nexus(artifactId,creds){
	script{
                    def pomfile=readMavenPom file: 'pom.xml'
                    version=pomfile.version
                    nexusArtifactUploader artifacts: [[artifactId: '${"artifactId"}', classifier: '', file: "target/myweb-${version}.war", type: 'war']], credentialsId: '${"creds"}', groupId: 'in.javahome', nexusUrl: '43.205.236.218:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'Myweb', version: "${version}"
                }
}
