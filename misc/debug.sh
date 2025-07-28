#!/bin/bash

# See https://source.android.com/docs/core/tests/debug/gdb?hl=zh-cn#app-startup
# 设置 > 开发者选项 > 选择调试应用，并从列表中选择您的应用，然后点击等待调试程序

# Start the application's MainActivity
adb shell force-stop org.example.application
adb shell am start 'org.example.application/.MainActivity'
sleep 1
uid=$(adb shell ps -A | grep org.example.application | awk '{print $2}')

if [ -z "$uid" ]; then
    echo "Error: Could not find running process for org.example.application"
    exit 1
fi

echo "Found application with UID: $uid"

# Setup ADB forward for JDWP
adb forward tcp:5005 jdwp:$uid

echo "ADB forward setup complete: tcp:5005 -> jdwp:$uid"
