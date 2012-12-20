@echo off
REM if a suitable repo would be found, this step could be done easier to use that directly
REM see instructions at http://stackoverflow.com/questions/4157170/maven-command-to-install-remote-dependency-locally

set GEMINI_VERSION=1.0.2

mvn install:install-file -Dfile=gemini-blueprint-core-%GEMINI_VERSION%.RELEASE.jar -DgroupId=org.eclipse.gemini -DartifactId=org.eclipse.gemini.blueprint.core -Dversion=%GEMINI_VERSION%.RELEASE -Dpackaging=jar

mvn install:install-file -Dfile=gemini-blueprint-io-%GEMINI_VERSION%.RELEASE.jar -DgroupId=org.eclipse.gemini -DartifactId=org.eclipse.gemini.blueprint.io -Dversion=%GEMINI_VERSION%.RELEASE -Dpackaging=jar

mvn install:install-file -Dfile=gemini-blueprint-extender-%GEMINI_VERSION%.RELEASE.jar -DgroupId=org.eclipse.gemini -DartifactId=org.eclipse.gemini.blueprint.extender -Dversion=%GEMINI_VERSION%.RELEASE -Dpackaging=jar