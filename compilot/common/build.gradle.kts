@file:OptIn(ExperimentalWasmDsl::class)

import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.vanniktech.maven.publish") version "0.28.0"
    id("signing")
}

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        browser() // or nodejs() if you're doing server-side JS
        binaries.executable()
    }
    wasmJs {
        browser() // or nodejs() if you're doing server-side JS
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "m.a.compilot.common"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}


group = "io.github.mahmoudafarideh.compilot.kmp"
version = libs.versions.compilotVersion.get()

mavenPublishing {
    coordinates(
        groupId = group as String,
        artifactId = "common",
        version = version as String
    )

    pom {
        name = "ComPilot KMP"
        description = "Type-safe navigation for jetpack compose!"
        url = "https://github.com/mahmoudafarideh/compilot-kmp"

        licenses {
            license {
                name = "MIT License"
                url = "https://github.com/mahmoudafarideh/compilot/blob/main/LICENSE"
            }
        }

        developers {
            developer {
                id = "mahmoudafarideh"
                name = "Mahmoud A."
                email = "mahmoudafarideh@gmail.com"
                url = "https://github.io/mahmoudafarideh"
            }
        }

        scm {
            url = "https://github.com/mahmoudafarideh/compilot-kmp"
            connection = "scm:git:https://github.com/mahmoudafarideh/compilot-kmp.git"
            developerConnection = "scm:git:git@github.com:mahmoudafarideh/compilot-kmp.git"
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}

signing {
    useInMemoryPgpKeys(
        "4FEC3FCA",
        """
            -----BEGIN PGP PRIVATE KEY BLOCK-----

            lIYEZzEGbRYJKwYBBAHaRw8BAQdAKUq7Iosl1/I3EkgVGDOhVg8ycx/x/JjjLZ03
            gwsJIxP+BwMCwMJs3ZpIJYD/6hZUPXWcnpHLix9+0aBFyTiJACznC+SYevaCkZEl
            7XNbnOCO873X+gMF/aEfVXfgUoU59IVZLxod7cYG57ezjuS89cgrl7QsTWFobW91
            ZCBBZmFyaWRlaCA8bWFobW91ZGFmYXJpZGVoQGdtYWlsLmNvbT6IkwQTFgoAOwIb
            AwULCQgHAgIiAgYVCgkICwIEFgIDAQIeBwIXgBYhBDX2/IUJBnzGW1SDSAMZ4Z1P
            7D/KBQJnMQbkAAoJEAMZ4Z1P7D/Kf7EBAKFsQZ+y2gWvdQgmpmBRpx9rVukO0Yl1
            jALUIUgO1WvRAP4snKIR/ZoxgoRQMhtiJR4jrcnUVHEcSqoZtleurTj4DIiZBBMW
            CgBBFiEENfb8hQkGfMZbVINIAxnhnU/sP8oFAmcxBm0CGwMFCQWjAxsFCwkIBwIC
            IgIGFQoJCAsCBBYCAwECHgcCF4AACgkQAxnhnU/sP8riYgD/ai/ShYaiqTOEaq24
            vNLJS4Dz/oOcxOUNp4N4TjIZ6CcBAIpbd/YK2B1OK2cbCxreRGs/qp3QoNkXbQhe
            qAzQoGQLnIsEZzEGbRIKKwYBBAGXVQEFAQEHQGCuKZUoavBegWfJOXzv8E8RPfel
            LY8mIN8N14IsGS4SAwEIB/4HAwKyZbA+YIkeaf950jc0v9nVORvTIMQ+dLCXtXT/
            XmgD12L0uJjajLRpUQyZUmbC6hNwrvutfMBFVf4FK9OsWccjDrxDX0n5uf/sNVuS
            ISwJiH4EGBYKACYWIQQ19vyFCQZ8xltUg0gDGeGdT+w/ygUCZzEGbQIbDAUJBaMD
            GwAKCRADGeGdT+w/yrZSAQD3G7idzP21YIn08r+yHfFz8uwcMkKjSZhEgksByyE0
            xwD+JPNFxHlufcd/VloFXqZW4fpxNsoStKY6xYikCGnLQAE=
            =IGkt
            -----END PGP PRIVATE KEY BLOCK-----
        """.trimIndent(),
        "Kqg6L\$uJ&7p",
    )
    sign(publishing.publications)
}
