plugins {
	id("java")
	id("maven-publish")
}

group = "de.greenman999"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
	mavenLocal()
}

dependencies {
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = "de.greenman999"
			artifactId = "layr-api"
			version = project.version as String?

			from(components["java"])
		}
	}
}

tasks.test {
	useJUnitPlatform()
}

java {
	withSourcesJar()
	withJavadocJar()
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}
