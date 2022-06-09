# Kotlin DSL와 buildSrc로 Dependency 관리하기



![image-20220608223955945](/Users/limsaehyun/Library/Application Support/typora-user-images/image-20220608223955945.png)



gradle 파일, 개발자라면 많이 보았을 향상 우리 곁에있는 친구입니다.

하지만 이 groovy gradle을



![image-20220608224118731](/Users/limsaehyun/Library/Application Support/typora-user-images/image-20220608224118731.png)

우리에게 익숙한 kotlin로 바꿀 수 있을 뿐더러 발표에서 설명해드릴 여러가지 장점을 가질 수 있는 방법을 소개하겠습니다.



## Kotlin DSL



### Kotlin DSL이란?

* DSL은 Domain Specific Language의 약어로 특정 분야에 특화된 프로그래밍 언어를 뜻합니다.
* 즉 Kotlin DSL은 코틀린에서 언어적 특성을 살려 Gardle(빌드 배포 도구) 스크립트 작성하는 것을 목적으로 하는 DSL인 것입니다.



### 왜 써야하나요?

* 기존의 익숙하지 않아 이해가 어려웠던 Groovy 대신 Kotlin을 사용할 수 있습니다.

* IDE의 지원 환경

  * 자동완성, 코드 탐색, 오류 코드 강조, 리펙토링 등 여러 기능들을 사용할 수 있습니다.

* 여러 명이 같이 공유해 작업하는 빌드 환경에서는 자유로운 표현 방식보다는 약간의 제약을 가하는 표현방식을 사용하는 것이 좋습니다.

  

## buildSrc 설정하기



### 1. root project에 buildSrc 디렉토리 생성

 

### 2. 'build.gradle.kts' 생성

* 생성한 buildSrc 디렉터리 안에 'build.gradle.kts'안에 아래 코드를 적어줍니다.

```kotlin
plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}
```



### 3. src > main > java 폴더 생성 후 그 안에 파일 생성하기

* 폴더는 자유롭게 만들어도 좋습니다. 이번에 사용한 예제의 경우 **Version**과 **Depndency**로 분리해 관리해주었습니다.

```kotlin
object Dependency {
    object AndroidX {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    }

    object KTX {
        const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
    }

    object Test {
        const val JUNIT = "androidx.test.ext:junit:${Versions.JUNIT}"
    }

    object AndroidTest {
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    }
}
```

```kotlin
object Versions {
    const val COMPILE_SDK = 31
    const val BUILD_TOOLS_VERSION = "30.0.2"
    const val MIN_SDK = 24
    const val TARGET_SDK = 31
    const val VERSION_CODE = 1
    const val VERSION_NAME = "0.0.1"
    const val APPLICATION_ID = "com.example.buildsrcexample"

    // AndroidX
    const val APP_COMPAT = "1.4.1"
    const val MATERIAL = "1.5.0"
    const val CONSTRAINT_LAYOUT = "2.1.3"

    // KTX
    const val CORE = "1.7.0"

    // TEST
    const val JUNIT = "1.1.3"

    // Android Test
    const val ESPRESSO_CORE = "3.4.0"
}

```



### 4 build.gradle -> build.gradle.kts로 변경



![image-20220608231153311](/Users/limsaehyun/Library/Application Support/typora-user-images/image-20220608231153311.png)

* 파일 이름을 위와 같이 변경해줍니다.
* 변경 후 kotlin 스타일로 [Groovy에서 KTS로 빌드 구성 이전](https://developer.android.com/studio/build/migrate-to-kts?hl=ko)을 참조해 변경해줍니다.



**build.gradle.kts(:app)**

```kotlin
plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Versions.COMPILE_SDK
    buildToolsVersion = Versions.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = Versions.APPLICATION_ID
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // AndroidX
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.AndroidX.MATERIAL)
    implementation(Dependency.AndroidX.CONSTRAINT_LAYOUT)

    // KTX
    implementation(Dependency.KTX.CORE)

    // TEST
    testImplementation(Dependency.Test.JUNIT)

    // AndroidTest
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)
}
```



**build.gradle.kts (project 수준)**

```kotlin
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.4")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
```



**settings.gradle.kts**

```kotlin
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "BuildSrcExample"
include (":app")

```



## 마치며..

그동한 불편했던 gradle 관리를 buildSrc를 활용하니 훨신 편리하고 여러 장점도 있는 것 같습니다. 멀티 모듈의 프로젝트를 진행하며 의존성 관리에 대한 문제를 해결할 수 있을 것 같아 좋았던 것 같습니다 :D