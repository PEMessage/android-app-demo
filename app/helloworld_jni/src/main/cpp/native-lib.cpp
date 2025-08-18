// native-lib.cpp
#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_org_example_application_NativeWrapper_getMessageFromNative(JNIEnv* env, jclass clazz) {
    std::string hello = "Hello from Native C++!";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jint JNICALL
Java_org_example_application_NativeWrapper_addNumbers(JNIEnv* env, jclass clazz, jint a, jint b) {
    return a + b;
}
