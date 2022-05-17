apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))

    // Coroutine Lifecycle Scopes
    "implementation" ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    "implementation" ("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")

    // Navigation Components
    "implementation" ("androidx.navigation:navigation-fragment-ktx:2.2.1")
    "implementation" ("androidx.navigation:navigation-ui-ktx:2.2.1")
}