package kjkrol.eshop.productcatalog.product.create

import kjkrol.eshop.productcatalog.product.Product
import kjkrol.eshop.productcatalog.product.ProductDto
import kjkrol.eshop.productcatalog.product.ProductRepository
import kjkrol.eshop.productcatalog.product.util.toDto
import kjkrol.eshop.productcatalog.product.util.toEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal open class ProductCreator(val productRepository: ProductRepository) {

    @Transactional
    fun create(productCreateRequest: ProductCreateRequest): ProductDto {
        val product: Product = productRepository.save(productCreateRequest.toEntity())
        return product.toDto()
    }

}