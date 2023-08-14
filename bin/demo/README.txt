System requirements:
- The compiled code was tested on Windows 10, with Java(TM) SE Runtime Environment (build 17.0.1+12-LTS-39).
- The .jar file is running with installed JRE 17 or above on all operating systems.


Installation:
- Install JRE 17 or above. See https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html for JDK 17. See https://www.oracle.com/de/java/technologies/downloads/ for newer version.
- In the current version the data base for ReFlowLab need to be deployed manually.
	- unzip "dominikemmel.zip".
	- copy the folder "dominikemmel" under path: C:\Users\"YOURUSERACCOUNT"\AppData\Local\dominikemmel.
	- change the path stored in the file "defaultSettings.properties" located in subfolder "dominikemmel\reflowlab\settings" to the new path of the stored database (located in /dominikemmel/reflowlab/db/reflowlabDefaultDB.mv.db).
- Run ReFlowLab
	- Double click on "ReFlowLab-1.0.0-SNAPSHOT-shaded.jar".
	
	or

	- open the console/terminal of your system in the path where the .jar file is located.
		- run the tool by entering the command "java -jar ReFlowLab-1.0.0-SNAPSHOT-shaded.jar".