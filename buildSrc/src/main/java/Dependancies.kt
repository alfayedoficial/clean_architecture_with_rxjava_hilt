/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 6:38 PM
 */
object Versions {
    const val KOTLIN = "1.3.72"
    const val COMPILE_SDK = 32
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 32
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val RXJAVA_VERSION = "2.2.13"
    const val RX_ANDROID = "2.1.1"
    const val GSON_VERSION = "2.8.7"
    const val LIFE_CYCLE_VERSION = "2.2.0"
    const val CONSTRAINT_LAYOUT = "2.1.3"
    const val ANDROID_GRADLE_VERSION = "4.0.1"
    const val APP_COMPAT_VERSION = "1.4.1"
    const val CORE_KTX = "1.7.0"
    const val ARCH_CORE_TESTING_VER = "2.0.0"
    const val TEST_RUNNER_VER = "1.1.0"
    const val RULES_VER = "1.1.0"
    const val TRUTH_VER = "1.1.0"
    const val JUNIT_EXT_VER = "1.1.3"
    const val MATERIAL_VERSION = "1.6.0"
    const val MOCKITO = "3.11.2"
    const val MULTIDEX = "2.0.1"
    const val JACOCO = "0.16.0"
    const val HILT = "2.37"
    const val FRAGMENT_KTX = "1.2.5"
    const val RX_RETROFIT_ADAPTER = "2.6.1"
    const val RETROFIT_VERSION = "2.7.1"
    const val OKHTTP_LOGGING_INTERCEPTOR_VERSION = "3.12.1"
    const val GSON_CONVERTER = "2.6.1"
    const val ESPRESSO = "3.4.0"
    const val GOOGLE_MAP = "18.0.2"
    const val LOCATION_SERVICES = "15.0.1"
    const val TIMBER  = "4.7.1"
    const val PERMISSION_DISPATCHER = "4.8.0"
}

object BuildPlugins {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_VERSION}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val jacocoPlugin =  "com.vanniktech:gradle-android-junit-jacoco-plugin:${Versions.JACOCO}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"
}

object Android {
    const val minSDK = Versions.MIN_SDK_VERSION
    const val targetSDK = Versions.TARGET_SDK_VERSION
    const val versionCode = Versions.VERSION_CODE
    const val versionName = Versions.VERSION_NAME
    const val compileSDK = Versions.COMPILE_SDK
    const val applicationId = "com.fourdev.cleanarchitecturewithrxjavahilt"
}

object Libs {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val rxVersion = "io.reactivex.rxjava2:rxjava:${Versions.RXJAVA_VERSION}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.RX_ANDROID}"
    const val material = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"
    const val gson = "com.google.code.gson:gson:${Versions.GSON_VERSION}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val liveData = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFE_CYCLE_VERSION}"
    const val viewModel = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFE_CYCLE_VERSION}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.APP_COMPAT_VERSION}"
    const val coreExt = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val multidex = "androidx.multidex:multidex:${Versions.MULTIDEX}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    const val fragmentKtx =  "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"
    const val rxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RX_RETROFIT_ADAPTER}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_LOGGING_INTERCEPTOR_VERSION}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
    const val googleMap = "com.google.android.gms:play-services-maps:${Versions.GOOGLE_MAP}"
    const val locationServices = "com.google.android.gms:play-services-location:${Versions.LOCATION_SERVICES}"
    const val timber =  "com.jakewharton.timber:timber:${Versions.TIMBER}"
    const val permissionsDispatcher ="com.github.permissions-dispatcher:permissionsdispatcher:${Versions.PERMISSION_DISPATCHER}"
    const val permissionsDispatcherProcessor ="com.github.permissions-dispatcher:permissionsdispatcher-processor:${Versions.PERMISSION_DISPATCHER}"
}

object TestLibs {
    const val junit = "junit:junit:4.13.2"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.ARCH_CORE_TESTING_VER}"
    const val testRunner = "androidx.test:runner:${Versions.TEST_RUNNER_VER}"
    const val rules = "androidx.test:rules:${Versions.RULES_VER}"
    const val truth = "androidx.test.ext:truth:${Versions.TRUTH_VER}"
    const val junitExt = "androidx.test.ext:junit:${Versions.JUNIT_EXT_VER}"
    const val mockito = "org.mockito:mockito-core:${Versions.MOCKITO}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.MOCKITO}"
     // For instrumentation tests
    const val hiltAndroidTest =  "com.google.dagger:hilt-android-testing:${Versions.HILT}"
}