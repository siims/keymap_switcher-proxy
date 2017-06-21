package keymapswitcher.proxy.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class TestSubscribersController {

    @GetMapping("/testSubscriber/{id}/{keyboardId}")
    fun testSubscriberReceiveMessage(@PathVariable id: String, @PathVariable(required = false) keyboardId: String): ResponseEntity<Void> {
        println("got a request to subscriber " + id + " with keyboardId " + keyboardId)
        return ResponseEntity(HttpStatus.OK);
    }
}
