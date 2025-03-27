import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("multiplatform")
    id("com.vanniktech.maven.publish") version "0.28.0"
    id("signing")
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation("com.squareup:javapoet:1.13.0")
                implementation(libs.ksp)
                api(project(":compilot:common"))
            }
            kotlin.srcDir("src/main/kotlin")
            resources.srcDir("src/main/resources")
        }
    }
}

group = "io.github.mahmoudafarideh.compilot.kmp"
version = libs.versions.compilotVersion.get()

mavenPublishing {
    coordinates(
        groupId = group as String,
        artifactId = "compiler",
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