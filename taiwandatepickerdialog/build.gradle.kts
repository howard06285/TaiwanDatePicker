plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.howard06285.taiwandatepicker"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                
                groupId = "com.github.howard06285"
                artifactId = "taiwandatepicker"
                version = "1.0.4"
                
                pom {
                    name.set("Taiwan Date Picker")
                    description.set("A custom date picker dialog for selecting dates in the Taiwanese calendar format (民國年)")
                    url.set("https://github.com/howard06285/TaiwanDatePicker")
                    
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    
                    developers {
                        developer {
                            id.set("howard06285")
                            name.set("Howard")
                            email.set("howard06285@gmail.com")
                        }
                    }
                    
                    scm {
                        connection.set("scm:git:git://github.com/howard06285/TaiwanDatePicker.git")
                        developerConnection.set("scm:git:ssh://github.com:howard06285/TaiwanDatePicker.git")
                        url.set("https://github.com/howard06285/TaiwanDatePicker")
                    }
                }
            }
        }
    }
}