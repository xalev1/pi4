package com.pi4.ecommerce.service;

import com.pi4.ecommerce.entity.Produto;
import java.util.List;

public interface ProdutoService {
    
    List<Produto> getAllProducts();
    void saveProduct (Produto produto);
    Produto getProductById(long id_produto);
    
}
