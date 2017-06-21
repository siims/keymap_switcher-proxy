package keymapswitcher.proxy.controller

import keymapswitcher.proxy.service.SubscriptionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class KeyboardController(@Autowired val subscriptionService: SubscriptionService) {

    var activeKeyboard: String = "";

    @GetMapping("/keyboard/{keyboardIdentifier}")
    fun setKeyboard(@PathVariable keyboardIdentifier: String): ResponseEntity<Void> {
        println("New keyboard event. Active keyboard: " + keyboardIdentifier)

        if (!activeKeyboard.equals(keyboardIdentifier)) {
            activeKeyboard = keyboardIdentifier
            subscriptionService.notifySubscribers(activeKeyboard)
        }
        return ResponseEntity(HttpStatus.OK);
    }
}
