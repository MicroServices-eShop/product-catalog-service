package kjkrol.eshop.productcatalog.product

import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.Link
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
internal class ProductResourceAssembler(val entityLinks: EntityLinks) : ResourceAssembler<ProductDto, Resource<ProductDto>> {
    override fun toResource(product: ProductDto): Resource<ProductDto> {
        val selfLink: Link = linkTo(methodOn(ProductCatalogController::class.java).findById(product.productId)).withSelfRel()
        val allProductsLink = entityLinks.linkToCollectionResource(ProductDto::class.java).withRel("all-products")
        return Resource(product, selfLink, allProductsLink)
    }
}