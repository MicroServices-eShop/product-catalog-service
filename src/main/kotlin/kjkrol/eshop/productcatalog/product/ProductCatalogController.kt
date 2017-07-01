package kjkrol.eshop.productcatalog.product

import kjkrol.eshop.productcatalog.product.create.ProductCreateRequest
import kjkrol.eshop.productcatalog.product.create.ProductCreator
import org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.data.web.SortDefault
import org.springframework.hateoas.ExposesResourceFor
import org.springframework.hateoas.Link
import org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resource
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(path = arrayOf("/product"), produces = arrayOf(HAL_JSON_VALUE))
@ExposesResourceFor(ProductDto::class)
internal class ProductCatalogController(val productCreator: ProductCreator,
                                        val productCatalogQuery: ProductCatalogQuery,
                                        val productResourceAssembler: ProductResourceAssembler) {

    @PostMapping
    fun create(@RequestBody request: ProductCreateRequest): ResponseEntity<Resource<ProductDto>> {
        val product: ProductDto = productCreator.create(request)
        val resource: Resource<ProductDto> = productResourceAssembler.toResource(product)
        return ResponseEntity(resource, HttpStatus.OK)
    }

    @GetMapping
    fun findAll(@RequestParam(required = false, defaultValue = "") ids: List<UUID>,
                @RequestParam(required = false, defaultValue = "") name: String,
                @PageableDefault(size = DEFAULT_PAGE_SIZE)
                @SortDefault.SortDefaults(
                        SortDefault(sort = arrayOf("price"), direction = Sort.Direction.DESC),
                        SortDefault(sort = arrayOf("name"), direction = Sort.Direction.ASC)
                )
                pageable: Pageable,
                pagedResourcesAssembler: PagedResourcesAssembler<ProductDto>): ResponseEntity<PagedResources<Resource<ProductDto>>> {
        val page: Page<ProductDto> = productCatalogQuery.find(name, ids, pageable)
        println("Page=${page.content}")
        when {
            page.none() -> return ResponseEntity(HttpStatus.NOT_FOUND)
            else -> return ResponseEntity(pagedResourcesAssembler.toResource(page, productResourceAssembler), HttpStatus.OK)
        }
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<Resource<ProductDto>> {
        val product: ProductDto = productCatalogQuery.findById(id)
        val resource: Resource<ProductDto> = productResourceAssembler.toResource(product)
        return ResponseEntity(resource, HttpStatus.OK)
    }

    @GetMapping("hello")
    fun hello(): ResponseEntity<Resource<ProductDto>> {
        val selfLinRel : Link = ControllerLinkBuilder.linkTo(
                ControllerLinkBuilder.methodOn(ProductCatalogController::class.java).hello())
                .withSelfRel()
        return ResponseEntity(Resource(ProductDto(UUID.randomUUID(), "tv", 0.123), selfLinRel), HttpStatus.OK)
    }

}