package kjkrol.eshop.productcatalog.product

import org.springframework.hateoas.core.Relation
import java.util.UUID

@Relation(collectionRelation = "products")
internal data class ProductDto(val productId: UUID, val name: String, val price: Double)