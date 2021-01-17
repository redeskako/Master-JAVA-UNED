@echo off
set JAVA_HOME="C:\Program Files\Java\jre7"
cd ..
%JAVA_HOME%\bin\java.exe -Dlog4j.configurationFile="conf/log4j.xml" -cp "lib/*" uned.java2016.apitwitter.clinical.crawler.Launcher  