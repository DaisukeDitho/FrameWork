@echo off

javac -parameters -d ./WEB-INF/classes *.java

setlocal enableDelayedExpansion

jar cvf allClass.jar -C WEB-INF/classes .

copy allClass.jar ..\TestFrameWork\WEB-INF\lib\allClass.jar

echo "Les fichiers sont copier dans ../TestFrameWork/WEB-INF/lib/allClass.jar"
pause