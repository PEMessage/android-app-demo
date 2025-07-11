
buildscript {
    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.1.0")
        classpath(kotlin("gradle-plugin", version = "1.9.0"))
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}
