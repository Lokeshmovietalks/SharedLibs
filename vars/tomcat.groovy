def call(credID,filename,userID,PrivateIP){
  sshagent(["${credID}"]){
	sh "scp -o StrictHostKeyChecking=no target/${filename}.war ${userID}@${PrivateIP}:/opt/tomcat9/webapps/"
	sh "ssh ${userID}@${PrivateIP} /opt/tomcat9/bin/shutdown.sh"
	sh "ssh ${userID}@${PrivateIP} /opt/tomcat9/bin/startup.sh"
  }
}
