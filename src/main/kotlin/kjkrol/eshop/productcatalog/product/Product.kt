package kjkrol.eshop.productcatalog.product

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID
import java.util.UUID.randomUUID

@Document(collection = "products")
data class Product(@Id val id: UUID = randomUUID(),
                   val name: String,
                   val price: Double)

@Repository
interface ProductRepository : MongoRepository<Product, UUID> {
    fun findProductByName(@Param("name") name: String, pageable: Pageable): Page<Product>
    fun findByIdIn(@Param("ids") ids: Collection<UUID>, pageable: Pageable): Page<Product>
}