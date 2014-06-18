Get started with ${app}
-----------------------------------
Welcome to Java Web Starter application!

This sample application demonstrates how to write a Java Web application (powered by WebSphere Liberty) and deploy it on BlueMix.

1. [Install the cf command-line tool](${doc-url}/redirect.jsp?name=cf-instructions).
2. [Download the starter application package](${ace-url}/rest/apps/${app-guid}/starter-download)
3. Extract the package and `cd` to it.
4. Connect to BlueMix:

		cf api ${api-url}

5. Log into BlueMix:

		cf login -u ${username}
		cf target -o ${org} -s ${space}
				
6. Compile the JAVA code and generate the war package using ant.
7. Deploy your app:

		cf push ${app} -p webStarterApp.war

8. Access your app: [http://${route}](http://${route})
