
buildscript {
    val coroutinesVersion by extra("1.7.1")
    val retrofitVersion by extra("2.9.0")
    val okhttpVersion by extra("5.0.0-alpha.2")
    val coroutineLifecycleScopesVersion by extra( "2.6.2")

}

plugins {
    id("com.android.application") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.android.library") version "7.4.2" apply false
    id("jacoco")
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}

apply(from = "${project.rootDir}/tools/jacoco.gradle")
