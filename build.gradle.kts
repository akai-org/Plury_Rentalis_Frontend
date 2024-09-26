// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.devtools.ksp") version "2.0.10-1.0.24"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20" apply false
}

//configurations.all {
//    exclude(group = "xmlpull", module = "xmlpull")
//    exclude(group = "xpp3", module = "xpp3_min")
//}