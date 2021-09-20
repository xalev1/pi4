package com.pi4.ecommerce.repository;

import com.pi4.ecommerce.entity.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository <Produto , Long> {
    
}
