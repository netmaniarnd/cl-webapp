# cl-webapp
CheckLOD Maven multi-module project
===

This project has two maven modules:
- cl-web
- cl-service
- cl-domain

Command line
- $ mvn clean install -Dmaven.test.skip=true
- $ mvn exec:java -pl cl-web -Dexec.mainClass=com.checklod.web.ChecklodDashboardApplication