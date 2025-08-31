#!/bin/bash

project_name="$(basename $1)"

# adb shell am start -n 'org.example.application/.MainActivity' -e "args" "123" ;
./gradlew :$project_name:installDebug && adb shell am start -n 'org.example.application/.MainActivity' && adb logcat -c && adb logcat
