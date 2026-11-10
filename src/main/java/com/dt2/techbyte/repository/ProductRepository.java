package com.dt2.techbyte.repository;

import com.dt2.techbyte.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {


}
