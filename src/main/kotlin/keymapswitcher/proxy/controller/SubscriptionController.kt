package keymapswitcher.proxy.controller

import keymapswitcher.proxy.service.Subscriber
import keymapswitcher.proxy.service.SubscriptionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.ValidationException

@RestController
class SubscriptionController(@Autowired val subscriptionService: SubscriptionService) {

    @GetMapping("/subscribe")
    fun list(): ResponseEntity<Set<Subscriber>> {
        return ResponseEntity.ok(subscriptionService.get())
    }

    @PostMapping("/subscribe")
    fun subscribe(url : Subscriber) : ResponseEntity<Void> {
        val valid = validateSubscriber(url)
        if (!valid) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        subscriptionService.add(url)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/unsubscribe")
    fun unsubscribe(url : Subscriber) : ResponseEntity<Void> {
        val valid = validateSubscriber(url)
        if (!valid) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        subscriptionService.remove(url)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/unsubscribe/all")
    fun unsubscribe() : ResponseEntity<Void> {

        subscriptionService.removeAll()
        return ResponseEntity(HttpStatus.OK)
    }

    private fun  validateSubscriber(url: Subscriber) : Boolean {
        if (!url.startsWith("http://")) {
            println("Subscriber '%s' doesn't start with 'http://'".format(url))
            return false
        }
        return true
    }
}
