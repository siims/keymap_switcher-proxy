package keymapswitcher.proxy

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun swagger(): Docket {
        return Docket(DocumentationType.SPRING_WEB)
                .select()
                .build()
                .apiInfo(ApiInfoBuilder()
                        .title("Keymap Switcher")
                        .version("1")
                        .build())
    }
}
