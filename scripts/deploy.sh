#!/bin/sh
echo "Running deploy script"
export USER_HOME="."
export GRADLE_PROPERTIES_FILE="${USER_HOME}/.gradle/gradle.properties"
mkdir -p "${USER_HOME}/.gradle"
echo "gradle.publish.key=${GRADLE_PUBLISH_KEY}" >> ${GRADLE_PROPERTIES_FILE}
echo "gradle.publish.secret=${GRADLE_PUBLISH_SECRET}" >> ${GRADLE_PROPERTIES_FILE}

GRADLE_OPTS="-Prelease.disableChecks"
GRADLE_OPTS="${GRADLE_OPTS} -Prelease.attachRemote=ssh://git@github.com:chauhraj/gradle-slick-plugin.git" 
GRADLE_OPTS="${GRADLE_OPTS} -Pgradle.publish.key=${GRADLE_PUBLISH_KEY}"
GRADLE_OPTS="${GRADLE_OPTS} -Pgradle.publish.secret=${GRADLE_PUBLISH_SECRET}"
GRADLE_OPTS="${GRADLE_OPTS} -Pgradle.publish.secret=${GRADLE_PUBLISH_SECRET}"
GRADLE_OPTS="${GRADLE_OPTS} -Prelease.customUsername=chauhraj"
GRADLE_OPTS="${GRADLE_OPTS} -Prelease.customPassword='automatic releases for chauhraj/gradle-slick-plugin'"
./gradlew  ${GRADLE_OPTS} --info -S release publishPlugins
