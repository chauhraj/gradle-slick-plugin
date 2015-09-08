#!/bin/sh
echo "Running deploy script"
export USER_HOME="."
export GRADLE_PROPERTIES_FILE="${USER_HOME}/.gradle/gradle.properties"
if [ -e ${GRADLE_PROPERTIES_FILES} ]; then
   echo "Gradle Properties file ${GRADLE_PROPERTIES_FILE} exists"
else 
   mkdir -p "${USER_HOME}/.gradle"
fi
echo "gradle.publish.key=${GRADLE_PUBLISH_KEY}" >> ${GRADLE_PROPERTIES_FILE}
echo "gradle.publish.secret=${GRADLE_PUBLISH_SECRET}" >> ${GRADLE_PROPERTIES_FILE}
./gradlew -Prelease.disableChecks \
          -Prelease.attachRemote=ssh://git@github.com:chauhraj/gradle-slick-plugin.git \
          -Pgradle.publish.key=${GRADLE_PUBLISH_KEY}
          -Pgradle.publish.secret=${GRADLE_PUBLISH_SECRET}
          --info -S
          release \
          publishPlugins

