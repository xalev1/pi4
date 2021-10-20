package com.pi4.ecommerce.repository;

import com.pi4.ecommerce.entity.FuncionarioBackOffice;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<FuncionarioBackOffice,Long>{
    FuncionarioBackOffice findById(long id_fun);
    
}
