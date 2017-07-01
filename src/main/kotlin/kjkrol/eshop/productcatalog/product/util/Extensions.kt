package kjkrol.eshop.productcatalog.product.util

import kjkrol.eshop.productcatalog.product.Product
import kjkrol.eshop.productcatalog.product.ProductDto
import kjkrol.eshop.productcatalog.product.create.ProductCreateRequest

internal fun Product.toDto(): ProductDto = ProductDto(productId = this.id, name = this.name, price = this.price)

internal fun ProductCreateRequest.toEntity(): Product = Product(name = this.name, price = this.price)
