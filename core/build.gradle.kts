import java.util.*

plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
}

repositories {
    mavenLocal()
}

dependencies {
    runtime(Libs.AspectJ.aspectjweaver)
    compileOnly(Libs.AspectJ.aspectjrt)
    implementation(Libs.Kotlin.std8)
    implementation(Libs.Kotlin.reflect)
    runtime(Libs.groovy)
    runtime(Libs.Logging.Logback.logback_core)
    runtime(Libs.Logging.Logback.logback_classic)
    implementation(Libs.Logging.slf4j)
    testImplementation(Libs.Testing.kotlintest)
}

tasks {
    jar {
        manifest {
            attributes(
                mapOf(
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to project.version,
                    "Class-Path" to configurations.compile.get().joinToString(" ") { it.name }
                )
            )
        }
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

val artifactName = project.name
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

val pomUrl = "https://github.com/serpro69/aspe.Kt"
val pomScmUrl = "https://github.com/serpro69/aspe.Kt"
val pomIssueUrl = "https://github.com/serpro69/aspe.Kt/issues"
val pomDesc = "https://github.com/serpro69/aspe.Kt"

val githubRepo = "serpro69/aspe.Kt"
val githubReadme = "README.md"

val pomLicenseName = "MIT"
val pomLicenseUrl = "https://opensource.org/licenses/mit-license.php"
val pomLicenseDist = "repo"

val pomDeveloperId = "serpro69"
val pomDeveloperName = "Sergii Prodanov"


publishing {
    publications {
        create<MavenPublication>("${rootProject.name}-$artifactName") {
            groupId = artifactGroup
            artifactId = "${rootProject.name}-$artifactName"
            version = artifactVersion
            from(components["java"])
            artifact(sourcesJar)

            pom.withXml {
                asNode().apply {
                    appendNode("description", pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", pomLicenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", pomDeveloperId)
                        appendNode("name", pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                    }
                }
            }
        }
    }
}

bintray {
    user = project.findProperty("bintrayUser").toString()
    key = project.findProperty("bintrayKey").toString()
    publish = true

    setPublications("${rootProject.name}-$artifactName")

    pkg.apply {
        repo = "maven"
        name = "${rootProject.name}-$artifactName"
        userOrg = "serpro69"
        githubRepo = githubRepo
        vcsUrl = pomScmUrl
        description = "A set of annotation-based utilities built with AspectJ and Kotlin"
        setLabels("kotlin", "aspectj", "aop", "aspect-oriented-programming", "utils", "utilities")
        setLicenses("MIT")
        desc = description
        websiteUrl = pomUrl
        issueTrackerUrl = pomIssueUrl
        githubReleaseNotesFile = githubReadme

        version.apply {
            name = artifactVersion
            desc = pomDesc
            released = Date().toString()
            vcsTag = artifactVersion
        }
    }
}
