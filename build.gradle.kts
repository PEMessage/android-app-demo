


// Old Best partice(DeepSeek):
//  集中管理插件版本：可以在顶层文件中统一管理所有子模块使用的插件版本，避免各个子模块重复指定或版本不一致。
// // Top-level `build.gradle.kts` file
// plugins {
//    id("com.android.application") version "7.4.1" apply false
// }
// // Module-level `build.gradle.kts` file
// plugins {
//    id("com.android.application")
// }

// Top-level build.gradle.kts
// In order to compat with old `id("com.android.application")` usage, we reamin top-level build.gradle.kts
plugins {
    // id("com.android.application") version "8.1.0" apply false
    alias(libs.plugins.android.application) apply false
}

