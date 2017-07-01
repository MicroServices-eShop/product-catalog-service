package kjkrol.eshop.productcatalog.config

import com.google.common.base.Predicates.not
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors.regex
import springfox.documentation.builders.RequestHandlerSelectors.any
import springfox.documentation.spi.DocumentationType.SWAGGER_2
import springfox.documentation.spring.web.plugins.Docket


@Configuration
class SwaggerConfiguration {

    @Value("\${management.contextPath}.*")
    private val actuatorPath: String? = null

    @Bean
    internal fun api(): Docket {
        return Docket(SWAGGER_2)
                .groupName("all-api")
                .enableUrlTemplating(false)
                .select()
                .apis(any())
                .paths(not<String>(regex("/error.*")))
                .paths(not<String>(regex(actuatorPath)))
                .build()
    }

}