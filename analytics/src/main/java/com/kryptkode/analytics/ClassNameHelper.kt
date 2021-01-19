package com.kryptkode.analytics

import java.util.regex.Pattern

object ClassNameHelper {
    private val classNamesToIgnore = listOf(
            ClassNameHelper::class.java.name,
            ContentViewEvent::class.java.name,
            AnalyticsClient::class.java.name,
            AppAnalytics::class.java.name,
    )

    private val anonymousClassPattern =
            Pattern.compile("(\\$\\w+)+$")

    private fun createStackElementClassName(className: String): String? {
        var tag = className
        val m = anonymousClassPattern.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        tag = tag.substring(tag.lastIndexOf('.') + 1)
        return tag
    }

    fun getClassName(): String? {
        return Throwable().stackTrace
                .map {
                    var tag = it.className
                    val m = anonymousClassPattern.matcher(tag)
                    if (m.find()) {
                        tag = m.replaceAll("")
                    }
                    tag
                }
                .first { it !in classNamesToIgnore }
                .let(::createStackElementClassName)
    }
}