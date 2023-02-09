plugins {
    groovy
    checkstyle
    jacoco
    signing
    `java-library`
    `java-library-distribution`
    `maven-publish`
    id("com.github.spotbugs") version "5.0.9"
    id("com.diffplug.spotless") version "6.9.0"
    id("com.palantir.git-version") version "0.13.0"
    id("com.github.kt3k.coveralls") version "2.12.0"
    id("org.sonatype.gradle.plugins.scan") version "2.3.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

// calculate version string from git tag, hash and commit distance
fun getVersionDetails(): com.palantir.gradle.gitversion.VersionDetails =
    (extra["versionDetails"] as groovy.lang.Closure<*>)() as com.palantir.gradle.gitversion.VersionDetails
if (getVersionDetails().isCleanTag) {
    version = getVersionDetails().lastTag.substring(1)
} else {
    version = "%s-%s-%s-SNAPSHOT".format(
            getVersionDetails().lastTag.substring(1),
            getVersionDetails().commitDistance,
            getVersionDetails().gitHash
        )
}

group = "tokyo.northside"
java {
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.httpcomponents.client5:httpclient5:5.1.3")
    implementation("com.fasterxml.jackson.core:jackson-core:2.13.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.codehaus.groovy:groovy-all:3.0.15")
    testImplementation("commons-io:commons-io:2.11.0")
    testImplementation("org.apache.commons:commons-lang3:3.12.0")
    testImplementation("org.slf4j:slf4j-simple:1.7.36")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    onlyIf {
        project.hasProperty("oxfordKey") && project.hasProperty("oxfordId")
    }
    systemProperty("oxfordKey", project.properties["oxfordKey"].toString())
    systemProperty("oxfordId", project.properties["oxfordId"].toString())
}

spotbugs {
    excludeFilter.set(project.file("config/spotbugs/exclude.xml"))
    tasks.spotbugsMain {
        reports.create("html") {
            enabled = true
        }
    }
    tasks.spotbugsTest {
        reports.create("html") {
            enabled = true
        }
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

coveralls {
    jacocoReportPath = "build/reports/jacoco/test/jacocoTestReport.xml"
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:deprecation")
    options.compilerArgs.add("-Xlint:unchecked")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("Java Oxford Dictionaries")
                description.set("Java client for Oxford Dictionaries API")
                url.set("https://github.com/miurahr/java-oxford-dictionaries")
                licenses {
                    license {
                        name.set("Apache License version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("miurahr")
                        name.set("Hiroshi Miura")
                        email.set("miurahr@linux.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/miurahr/java-oxford-dictionaries.git")
                    developerConnection.set("scm:git:git://github.com/miurahr/java-oxford-dictionaries.git")
                    url.set("https://github.com/miurahr/java-oxford-dictionaries")
                }
            }
        }
    }
}

signing {
    if (project.hasProperty("signingKey")) {
        val signingKey: String? by project
        val signingPassword: String? by project
        useInMemoryPgpKeys(signingKey, signingPassword)
    } else {
        useGpgCmd()
    }
    sign(publishing.publications["mavenJava"])
}
tasks.withType<Sign> {
    val hasKey = project.hasProperty("signingKey") || project.hasProperty("signing.gnupg.keyName")
    onlyIf { hasKey && getVersionDetails().isCleanTag }
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(System.getenv("SONATYPE_USER"))
            password.set(System.getenv("SONATYPE_PASS"))
        }
    }
}
