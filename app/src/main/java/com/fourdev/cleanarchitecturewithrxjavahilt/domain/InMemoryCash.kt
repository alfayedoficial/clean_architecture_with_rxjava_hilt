package com.fourdev.cleanarchitecturewithrxjavahilt.domain

import com.fourdev.cleanarchitecturewithrxjavahilt.domain.entity.Restaurant

object InMemoryCash {

    val cash = ArrayList<Restaurant>()

    fun get() = cash

    fun add(restaurants: List<Restaurant>) {
        cash.addAll(restaurants)
    }
}