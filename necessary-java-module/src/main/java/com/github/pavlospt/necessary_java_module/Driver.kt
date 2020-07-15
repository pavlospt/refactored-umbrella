package com.github.pavlospt.necessary_java_module

import com.github.pavlospt.Box
import com.github.pavlospt.Subscription

object Driver {
    @JvmStatic
    fun main(args: Array<String>) {
        val subscription = Subscription(d = true)
        val subscriptionEncoded = subscription.encode()
        val subscriptionDecoded = Subscription.ADAPTER.decode(subscriptionEncoded)
        println("SUBSCRIPTION : ${subscriptionDecoded.d}")

        val box = Box(subscription = subscription)
        println("original box: $box")
        val boxEncoded = box.encode()
        val boxDecoded = Box.ADAPTER.decode(boxEncoded)
        println("decoded box: $boxDecoded")
    }
}
