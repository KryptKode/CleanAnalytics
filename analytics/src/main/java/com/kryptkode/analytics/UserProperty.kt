package com.kryptkode.analytics

/**
 * Created by kryptkode on 5/14/2020.
 */
interface UserProperty : Event {
    fun getKey(): String
    fun getValue(): String
}