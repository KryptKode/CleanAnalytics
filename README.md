# Clean Analytics

A simple library to prevent coupling your codebase to a particular analytic SDK. It also adds support for multiple analytics clients.

Possible things to be tracked by analytics can be grouped into three events. An event `tag` is a unique string that identifies the event.

- **Content View Events**

    Content view events are those events are logged each time a user visits a particular screen. A screen here can be an activity, fragment, dialog, etc. These events like all other events have a unique `tag`. They also have another property, the name of the (Java or Kotlin) class associated with that screen. The base class for events of this type is the  `[ContentView](https://github.com/KryptKode/CleanAnalytics/blob/master/analytics/src/main/java/com/kryptkode/analytics/ContentViewEvent.kt)` class.
    The `[ContentView](https://github.com/KryptKode/CleanAnalytics/blob/master/analytics/src/main/java/com/kryptkode/analytics/ContentViewEvent.kt)` class has two public methods:

    - `getName`  - which should return a *`String`* of the event tag
    - `getClassName` - which should return a *`String`* of the class name associated with the screen in question

    An example of the content view event for an e-commerce app would be to view the cart screen. Let's give it  a tag of  `view_cart`

    ```kotlin
    class ViewSendMoneyEvent  : ContentViewEvent() {
        override fun getName(): String {
            return "view_cart"  //the tag of the view event
        }
    }
    ```

    **Note**
    The `getClassName`. method is open and we don't need to override for every subclass because we have a helper class[, `ClassNameHelper`](https://github.com/KryptKode/CleanAnalytics/blob/master/analytics/src/main/java/com/kryptkode/analytics/ClassNameHelper.kt)  that detects the name of the class where the event is logged automatically

- **Custom Events**

    Custom events are just any other events that are not `ContentView` events. They could be interactions like clicking a button or performing an action in the app like logging in, completing a transaction, etc. These events like all other events have a unique `tag`. Some may go with some extra information. In the case of performing a transaction, such extra information could be the amount of the transaction. The extra information associated with a custom event are the `parameters` The base class for the event type is the `[CustomEvent](https://github.com/KryptKode/CleanAnalytics/blob/master/analytics/src/main/java/com/kryptkode/analytics/CustomEvent.kt)` class.
    The `[CustomEvent](https://github.com/KryptKode/CleanAnalytics/blob/master/analytics/src/main/java/com/kryptkode/analytics/CustomEvent.kt)` class has two public methods:

    - `getEventName` -  which should return a *`String`* of the event tag.
    - `getParameters` - which should return a *`Map<String, Any>`* of the parameters associated with the event.

    An example of a custom event for an e-commerce app would be to add an item to the cart. Let's give it a tag of `add_to_cart` and two parameters, `item_id` and `item_amount`

    ```kotlin
    class AddToCartEvent(private val itemId: String,
                                private val itemAmount: String) : CustomEvent() {
        override fun getEventName(): String {
            return "add_to_cart" //the tag of the event
        }

        override fun getParameters(): MutableMap<String, Any> {
            return hashMapOf(
                    Pair("item_id", itemId),
                    Pair("item_amount", itemAmount)
            )
        }
    }
    ```

- **User properties**

    User properties are just data that you want to track that are specific to a user. For example, their user ID, etc.  It's recommended to track generic data like user ID and not data that's sensitive.  The base class for the event type is the `[UserProperty](https://github.com/KryptKode/CleanAnalytics/blob/master/analytics/src/main/java/com/kryptkode/analytics/UserProperty.kt)` class.

    An example of a user property event for an e-commerce app would be to track the user ID. This can be defined as follows:

    ```kotlin
    class UserIdProperty(private val userId: String) : UserProperty {
        override fun getKey(): String {
            return "user_id"
        }

        override fun getValue(): String {
            return userId
        }
    }
    ```
