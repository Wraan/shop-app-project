package com.shop.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specifications")
public class Specification {

    @Id
    @GeneratedValue
    @Column(name = "specification_id")
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String value;

    @OneToMany(mappedBy = "specification", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ProductSpecification> productSpecification = new HashSet<>();

    public Specification(){

    }

    public Specification(@NotBlank String name, @NotBlank String value) {
        this.name = name;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<ProductSpecification> getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(Set<ProductSpecification> productSpecification) {
        this.productSpecification = productSpecification;
    }


}
