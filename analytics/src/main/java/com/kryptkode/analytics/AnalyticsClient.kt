package com.kryptkode.analytics

/**
 * Created by kryptkode on 5/14/2020.
 */
interface AnalyticsClient {

    fun logEvent(event: Event) {
        if (event is CustomEvent) {
            logCustomEvent(event)
        }

        if (event is ContentViewEvent) {
            logContentViewEvent(event)
        }

        if (event is UserProperty) {
            updateUserProperty(event)
        }
    }

    fun logCustomEvent(event: CustomEvent)
    fun updateUserProperty(property: UserProperty)
    fun logContentViewEvent(event: ContentViewEvent)
}