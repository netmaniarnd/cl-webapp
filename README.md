# cl-webapp
CheckLOD Maven multi-module project
===

This project has two maven modules:
- cl-web
- cl-service
- cl-domain

Command line
- $ mvn clean install -Dfile.encoding=UTF-8 -Dmaven.test.skip=true
- $ mvn exec:java -pl cl-web -Dfile.encoding=UTF-8 -Dexec.mainClass=com.checklod.web.ChecklodDashboardApplication