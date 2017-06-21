package keymapswitcher.proxy

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate


@SpringBootApplication
class KeymapSwitcherProxyApplication {

    @Bean
    fun restService() : RestTemplate {
        return RestTemplate()
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(KeymapSwitcherProxyApplication::class.java, *args)
}
