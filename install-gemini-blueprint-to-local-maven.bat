@echo off
REM if a suitable repo would be found, this step could be done easier to use that directly
REM see instructions at http://stackoverflow.com/questions/4157170/maven-command-to-install-remote-dependency-locally
REM enterprise repo used here has completely wrong path structure
REM they have http://repository.springsource.com/ivy/bundles/external/org.eclipse.gemini/org.eclipse.gemini.blueprint.core/1.0.2.RELEASE/org.eclipse.gemini.blueprint.core-1.0.2.RELEASE.jar

setlocal EnableDelayedExpansion
set GEMINI_VERSION=1.0.2.RELEASE

REM :: pre-step: get gemini blueprint parent if it's not there:
REM wget --no-check-certificate http://raw.github.com/glyn/Gemini-Blueprint/master/pom.xml -O blueprintparentpom.xml
REM :: change pom version from 1.0.1.BUILD-SNAPSHOT to 1.0.2.RELEASE
REM mvn -N --file blueprintparentpom.xml install
REM del blueprintparentpom.xml

REM and the actual files
call:installMavenDependency ^
  "http://ebr.springsource.com/repository/app/bundle/version/download?name=org.eclipse.gemini.blueprint.core&version=!GEMINI_VERSION!&type=binary" ^
  gemini.blueprint.core-!GEMINI_VERSION!.jar ^
  META-INF/maven/org.eclipse.gemini.blueprint/gemini-blueprint-core/pom.xml

call:installMavenDependency ^
  "http://ebr.springsource.com/repository/app/bundle/version/download?name=org.eclipse.gemini.blueprint.io&version=!GEMINI_VERSION!&type=binary" ^
  gemini.blueprint.io-!GEMINI_VERSION!.jar ^
  META-INF/maven/org.eclipse.gemini.blueprint/gemini-blueprint-io/pom.xml
call:installMavenDependency ^
  "http://ebr.springsource.com/repository/app/bundle/version/download?name=org.eclipse.gemini.blueprint.extender&version=!GEMINI_VERSION!&type=binary" ^
  gemini.blueprint.extender-!GEMINI_VERSION!.jar ^
  META-INF/maven/org.eclipse.gemini.blueprint/gemini-blueprint-extender/pom.xml

goto:eof

:installMavenDependency
set ARTIFACTFILENAME=%~2
set POMFILEPATH=%~3
wget "%~1" -O "!ARTIFACTFILENAME!"
if exist "!ARTIFACTFILENAME!" (
  jar xf "!ARTIFACTFILENAME!" !POMFILEPATH!
  call mvn install:install-file "-Dfile=!ARTIFACTFILENAME!" "-DpomFile=!POMFILEPATH!"
  del "!ARTIFACTFILENAME!"
  rd /s /q META-INF
) else (
  echo Couldn't download !ARTIFACTFILENAME!, wget missing?
  echo ...
)
goto:eof