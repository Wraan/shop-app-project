package com.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.dto.ProductDto;
import com.shop.dto.SpecificationDto;
import com.shop.model.Image;
import com.shop.model.Product;
import com.shop.model.ProductSpecification;
import com.shop.model.Specification;
import com.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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



}
