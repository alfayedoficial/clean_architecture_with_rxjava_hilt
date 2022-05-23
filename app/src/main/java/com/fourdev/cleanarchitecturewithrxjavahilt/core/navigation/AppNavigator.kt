package com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 8:58 PM
 */
interface AppNavigator {

    fun navigateTo(screen: Screen)
}

enum class Screen {
    Map,
   DETAIL
}