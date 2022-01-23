	### TO-DO PROJECT WITH SPRING BOOT AND SPRING BOOT COUCHBASE ###
	
After downloading the project you should first install maven.
Before installing maven; if you want to change,you can find couchbase server config settings under config package.
After Maven is installed, you will see that the jar of the project is created under the target folder.
Thus, you will install the necessary dependencies and create a docker image.
Now all you have to do is open the command prompt and run the command below.
Finally you will be able to visit "http://localhost:8080/swagger-ui.html" and see the project running with swagger implementation.


command:"docker run -d --name case-study -p 8080:8080 -p 11210:11210 case-study"