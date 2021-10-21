package com.pi4.ecommerce.repository;

import com.pi4.ecommerce.entity.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto , Long> {
    
    @Query("select p from Produto p where p.nome LIKE %?1%")
    List<Produto> search(String keyword);

    
}
