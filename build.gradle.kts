
buildscript {
    val coroutinesVersion by extra("1.7.1")
    val retrofitVersion by extra("2.9.0")
    val okhttpVersion by extra("5.0.0-alpha.2")
    val coroutineLifecycleScopesVersion by extra( "2.6.2")

}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.2.2" apply false
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}