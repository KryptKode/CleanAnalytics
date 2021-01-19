package com.kryptkode.analytics

/**
 * Created by kryptkode on 5/14/2020.
 */
class AppAnalytics(private vararg val analyticsClient: AnalyticsClient) {

    fun logEvent(vararg events: Event) {
        events.forEach {
            try {
                analyticsClient.forEach { client ->
                    client.logEvent(it)
                }
            } catch (e: Exception) {

            }
        }
    }
}