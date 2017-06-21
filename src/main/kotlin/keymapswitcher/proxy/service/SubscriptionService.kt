package keymapswitcher.proxy.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*
import java.util.concurrent.CompletableFuture

typealias Subscriber = String

@Component
class SubscriptionService(@Autowired val restService: RestTemplate) {

    val subscribers: MutableSet<Subscriber> = HashSet();

    fun remove(subscriber: Subscriber) {
        subscribers.remove(subscriber)
    }

    fun removeAll() {
        subscribers.clear()
    }

    fun add(subscriber: Subscriber) {
        if (subscriber.isNotBlank()) {
            subscribers.add(subscriber)
        }
    }

    fun get(): Set<Subscriber> {
        return subscribers
    }

    fun notifySubscribers(keyboard: String) {
        if (subscribers.isEmpty()) {
            println("No subscribers to notify")
        } else {
            println("Notifying %d subscriber(s)".format(subscribers.size))

            subscribers.forEach(
                    { subscriber ->
                        CompletableFuture.supplyAsync({
                            notifySingleSubscriber(subscriber, keyboard)
                        })
                    })
        }
    }

    @Async
    fun notifySingleSubscriber(subscriber: Subscriber, keyboard: String) {

        val urlString = "%s/%s".format(subscriber, keyboard)
        println("started notifying " + urlString)

        try {
            val response = restService.getForEntity(urlString, Void::class.java)
            println("finished notifying '%s' with response %s".format(subscriber, response.statusCode))
        } catch (e: Exception) {
            println("Something went wrong with notifying subscriber '%s'".format(subscriber))
            e.printStackTrace()
        }
    }
}
