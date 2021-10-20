package com.pi4.ecommerce.service;

import com.pi4.ecommerce.entity.Produto;
import com.pi4.ecommerce.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl {
    //implements ProdutoService

    @Autowired
    private ProdutoRepository repository;
    
    //@Override
    public List<Produto> getAllProducts() {
       return repository.findAll();   
    }

    //@Override
    public void saveProduct(Produto produto) {
        this.repository.save(produto);
    }

    //@Override
    public Produto getProductById(long id_produto) {
        Optional<Produto> optional = repository.findById(id_produto);
        Produto produto = null;
        if(optional.isPresent()){
            produto = optional.get();
        } else{
             throw new RuntimeException("Produto não encontrado pelo ID: " + id_produto);
        }
        return produto;     
    } 

    //@Override
    public Page<Produto> findPaginated(int pageNo, int pageSize) {
       Pageable pageable = PageRequest.of(pageNo -1, pageSize);
       return this.repository.findAll(pageable);
    }
    
    public List<Produto> listAll(String keyword){
         if (keyword != null) {
            return repository.search(keyword);
        }
        return repository.findAll();
    }
    
}
