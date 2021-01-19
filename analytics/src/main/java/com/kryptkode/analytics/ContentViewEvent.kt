package com.kryptkode.analytics

/**
 * Created by kryptkode on 5/14/2020.
 */
abstract class ContentViewEvent : Event {
    abstract fun getName(): String
    open fun getClassName(): String {
        val resolvedClassName = ClassNameHelper.getClassName()
        return resolvedClassName ?: getName()
    }
}