import org.jetbrains.kotlin.org.fusesource.jansi.AnsiRenderer.test

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("jacoco")
}

android {
    namespace = "com.example.network"
    compileSdk = 34

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    testCoverage {
        jacocoVersion = "0.8.6"
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
    }
//    sourceSets.getByName("debug") {
//        res.srcDirs("src/test/java")
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // default
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.test:core-ktx:1.5.0")
    testImplementation("junit:junit:4.13.2")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation ("org.robolectric:robolectric:4.12.1")
    testImplementation("io.mockk:mockk:1.10.6")
    androidTestImplementation("io.mockk:mockk:1.10.6")
    testImplementation ("org.mockito:mockito-core:5.11.0")
    androidTestImplementation ("org.mockito:mockito-core:5.11.0")

    // Retrofit
    api("com.squareup.retrofit2:retrofit:${rootProject.extra.get("retrofitVersion")}")
    api("com.squareup.retrofit2:converter-gson:${rootProject.extra.get("retrofitVersion")}")
    api("com.squareup.okhttp3:okhttp:${rootProject.extra.get("okhttpVersion")}")
    api("com.squareup.okhttp3:logging-interceptor:${rootProject.extra.get("okhttpVersion")}")

    // Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.extra.get("coroutinesVersion")}")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${rootProject.extra.get("coroutinesVersion")}")

    // Coroutine Lifecycle Scopes
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:${rootProject.extra.get("coroutineLifecycleScopesVersion")}")
    api("androidx.lifecycle:lifecycle-runtime-ktx:${rootProject.extra.get("coroutineLifecycleScopesVersion")}")


}