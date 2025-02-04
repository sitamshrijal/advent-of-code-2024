plugins {
    kotlin("jvm") version "2.1.10"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.12.1"
    }
}
