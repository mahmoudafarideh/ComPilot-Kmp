enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.myket.ir")
        }
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.myket.ir")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "ComPilotKmp"
include(":shared")
include(":compilot:common")
include(":compilot:compiler")
include(":compilot:navigation")
include(":compilot:runtime")
