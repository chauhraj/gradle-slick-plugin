#!/bin/sh
echo "Running deploy script"
export USER_HOME="."
export GRADLE_PROPERTIES_FILE="${USER_HOME}/.gradle/gradle.properties"
mkdir -p "${USER_HOME}/.gradle"
echo "gradle.publish.key=${GRADLE_PUBLISH_KEY}" >> ${GRADLE_PROPERTIES_FILE}
echo "gradle.publish.secret=${GRADLE_PUBLISH_SECRET}" >> ${GRADLE_PROPERTIES_FILE}

GRADLE_OPTS="-Prelease.disableChecks"
GRADLE_OPTS="${GRADLE_OPTS} -Prelease.customUsername=${GITHUB_ACCESS_TOKEN}"

git fetch --unshallow && echo "Repository shouldn't be any more in detached state"

./gradlew  ${GRADLE_OPTS} --info -S release publishPlugins
