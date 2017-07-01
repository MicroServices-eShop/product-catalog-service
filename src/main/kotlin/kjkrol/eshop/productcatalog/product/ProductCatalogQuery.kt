package kjkrol.eshop.productcatalog.product

import kjkrol.eshop.productcatalog.product.util.toDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.NoSuchElementException
import java.util.Optional.ofNullable
import java.util.UUID

@Service
internal open class ProductCatalogQuery(val productRepository: ProductRepository) {

    @Transactional(readOnly = true)
    fun findById(id: UUID): ProductDto = ofNullable(productRepository
            .findOne(id)).orElseThrow { NoSuchElementException() }
            .toDto()

    @Transactional(readOnly = true)
    fun find(name: String, ids: List<UUID>, pageable: Pageable): Page<ProductDto> = when {
        ids.isNotEmpty() -> findByIdIn(ids, pageable)
        name.isNotBlank() -> findByName(name, pageable)
        else -> findAll(pageable)
    }

    private fun findAll(pageable: Pageable): Page<ProductDto> = productRepository
            .findAll(pageable)
            .map { it.toDto() }

    private fun findByName(name: String, pageable: Pageable): Page<ProductDto> = productRepository
            .findProductByName(name, pageable)
            .map { it.toDto() }

    private fun findByIdIn(ids: List<UUID>, pageable: Pageable): Page<ProductDto> = productRepository
            .findByIdIn(ids, pageable)
            .map { it.toDto() }

}