package com.kryptkode.analytics

/**
 * Created by kryptkode on 5/14/2020.
 */
abstract class CustomEvent : Event {
    abstract fun getEventName(): String
    open fun getParameters(): MutableMap<String, Any> {
        return hashMapOf()
    }
}