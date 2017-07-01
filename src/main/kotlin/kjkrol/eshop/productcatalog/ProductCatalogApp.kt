package kjkrol.eshop.productcatalog

import kjkrol.eshop.productcatalog.product.create.ProductCreateRequest
import kjkrol.eshop.productcatalog.product.create.ProductCreator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import springfox.documentation.swagger2.annotations.EnableSwagger2


fun main(args: Array<String>) {
    SpringApplication.run(ProductCatalogApp::class.java, *args)
}

@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication
open class ProductCatalogApp {

    companion object {
        val log: Logger = LoggerFactory.getLogger(ProductCatalogApp::class.java)
    }

    @Bean
    internal fun init(productCreator: ProductCreator): CommandLineRunner {
        log.info("populate db with initial data")
        return CommandLineRunner {

            productCreator.create(ProductCreateRequest(name = "laptop", price = 120.99))
            productCreator.create(ProductCreateRequest(name = "eBook", price = 5.59))
            productCreator.create(ProductCreateRequest(name = "eInk", price = 99.99))
            productCreator.create(ProductCreateRequest(name = "book", price = 10.00))
            productCreator.create(ProductCreateRequest(name = "screen", price = 260.99))
            productCreator.create(ProductCreateRequest(name = "microphone", price = 20.0))
            productCreator.create(ProductCreateRequest(name = "smart phone", price = 17.50))
            productCreator.create(ProductCreateRequest(name = "keyboard", price = 45.48))
            productCreator.create(ProductCreateRequest(name = "mouse", price = 32.99))
            productCreator.create(ProductCreateRequest(name = "gadget", price = 77.0))

            for (x in 1..100) {
                productCreator.create(ProductCreateRequest(name = "gadget$x", price = 70.0 + x))
            }

        }
    }

}




