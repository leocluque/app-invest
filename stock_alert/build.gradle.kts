plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("jacoco")
}

android {
    namespace = "com.example.stock_alert"
    compileSdk = 34

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    testCoverage {
        jacocoVersion = "0.8.5"
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
    viewBinding {
        enable = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.test:core-ktx:1.5.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.test:runner:1.5.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.fragment:fragment-testing:1.6.2")
    implementation("androidx.fragment:fragment-ktx:1.5.3")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation ("org.mockito:mockito-core:5.11.0")
    androidTestImplementation ("org.mockito:mockito-core:5.11.0")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation("androidx.fragment:fragment-testing:1.6.2")
    testImplementation ("org.robolectric:robolectric:4.12.1")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")


    testImplementation("io.mockk:mockk:1.10.6")
    androidTestImplementation("io.mockk:mockk:1.10.6")

    androidTestImplementation("androidx.test:core-ktx:1.4.0")

    //netWork
    implementation(project(":network"))
}