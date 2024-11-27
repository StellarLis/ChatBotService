plugins {
    id("java")
}

group = "ru.andrew"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.5")
    implementation("org.springframework.boot:spring-boot-starter:3.3.5")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.3.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.5")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.3.5")
    implementation("org.springframework.boot:spring-boot-starter-security:3.3.5")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    implementation("com.auth0:java-jwt:4.4.0")
}

tasks.test {
    useJUnitPlatform()
}