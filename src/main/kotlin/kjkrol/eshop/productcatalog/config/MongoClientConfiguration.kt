package kjkrol.eshop.productcatalog.config

import com.github.fakemongo.Fongo
import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MongoClientConfiguration {

    @Bean
    internal fun mongoClient(): MongoClient {
        return Fongo("in-memory").mongo
    }

}