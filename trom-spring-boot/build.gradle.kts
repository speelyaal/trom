import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.0.M4"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.71"
	kotlin("plugin.spring") version "1.3.71"
}

group = "com.speelyaal"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://dl.bintray.com/s1m0nw1/KtsRunner")}
}

//val seleniumVersion="4.0.0-alpha-5"
val seleniumVersion="3.141.59"
val extentReportVersion = "4.1.5"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	//Logging
	//implementation("org.springframework.boot:spring-boot-starter-log4j2")

	//Reporting
	implementation(group="com.aventstack",name="extentreports", version= extentReportVersion )


	//Selenium
	implementation(group= "org.seleniumhq.selenium", name= "selenium-java", version= seleniumVersion)
	implementation(group= "org.seleniumhq.selenium", name= "selenium-firefox-driver", version= seleniumVersion)
	//implementation(group= "org.seleniumhq.selenium", name= "selenium-chrome-driver", version= seleniumVersion)
	//implementation(group= "org.seleniumhq.selenium", name= "selenium-safari-driver", version= seleniumVersion)
	//implementation(group= "org.seleniumhq.selenium", name= "selenium-opera-driver", version= seleniumVersion)

	implementation(kotlin("script-runtime"))
	//implementation(kotlin("script-util"))
	//implementation(kotlin("compiler-embeddable"))


	//FIXME: try to have own script runner
	implementation(group="de.swirtz", name="ktsRunner", version = "0.0.8")


	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
