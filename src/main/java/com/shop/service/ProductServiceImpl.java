package com.shop.service;

import com.shop.dto.ProductDto;
import com.shop.dto.SpecificationDto;
import com.shop.model.*;
import com.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private DtoService dtoService;
    private SpecificationService specificationService;

    public ProductServiceImpl(ProductRepository productRepository, DtoService dtoService, SpecificationService specificationService){
        this.productRepository = productRepository;
        this.dtoService = dtoService;
        this.specificationService = specificationService;
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product save(ProductDto productDto) {
        Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getAmount(), productDto.getType(),
                productDto.getProducer(), productDto.getDescription(), Calendar.getInstance());
        for(String image : productDto.getImages()){
            if(!image.equals(""))
                product.getImages().add(new Image(image, product));
        }
        List<SpecificationDto> specificationDtoList = dtoService.getListSpecificationDtoFromJson(productDto.getJsonSpecifications());
        List<Specification> specifications = dtoService.convertListSpecificationDtoToListSpecification(specificationDtoList);
        specifications = specificationService.updateSpecificationsInDatabase(specifications);
        for(Specification spec : specifications){
            product.getProductSpecifications().add(new ProductSpecification(product, spec, specifications.indexOf(spec) + 1));
        }
        return save(product);
    }

    @Override
    public ProductObservation findProductSubscriptionByUserAndProduct(User user, Product product) {
        return productRepository.findProductObservationByUserAndProduct(user, product);
    }

    @Override
    public List<Product> findFollowedProductsByUser(User user) {
        List<ProductObservation> productObservations = productRepository.findProductObservationsByUser(user);
        List<Product> products = new ArrayList<>();
        for(ProductObservation po : productObservations){
            products.add(po.getProduct());
        }
        return products;
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
