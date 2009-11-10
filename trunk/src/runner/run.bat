@echo off
SET CLASSPATH=.
rem echo %CLASSPATH%

FOR %%f IN (lib\*.jar) DO CALL CP.bat %%f

rem echo %CLASSPATH%	

echo **********************************************************************
echo **                                                                  **
echo **                       GAMEMASTER v.3                             **
echo **                                                                  **
echo **                                                                  **
echo ** The sourcecode can be downloaded from CVS from the project cvs   **
echo ** at www.sourceforge.org/nplayer                                   **
echo **                                                                  **
echo ** Please contribute with bugreports, wishes or constructive input. **
echo **                                                                  **
echo ** This code is not meant to harm, destory or otherwise cause       **
echo ** damage to computers, files or anything else. It is meant as an   **
echo ** aid to Role Playing Game Dungeon Masters.                        **
echo **                                                                  **
echo ** This software is delivered with some JAR libraries from other    **
echo ** software distributions for the convenience of users. These       **
echo ** libraries are part of the brilliant following packs:             **
echo **                                                                  **
echo **                                                                  **
echo ** JAXB and WSDP shared libraries from Java Web Services Pack,      **
echo ** downloadable from http://java.sun.com/webservices/index.jsp      **
echo **                                                                  **
echo ** Kunststoff Look and Feel, a great open source GUI look project,  **
echo ** found at www.incors.org, sadly ended.                            **
echo **                                                                  **                    
echo **                                                                  **
echo **                                                                  **
echo ** Thanks to Sourceforge for such a great place for developers!     **
echo **                                                                  **
echo **                                                                  **
echo **********************************************************************
echo.
echo. 
java -splash:images/splashScreen.png -classpath ;%CLASSPATH% rpg.v4.server.gamemaster.Main