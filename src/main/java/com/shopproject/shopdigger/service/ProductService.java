package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Optional<Product> getProductById(Long productId);
    boolean saveProduct(Product product);
    Page<Product> findPaginated(Pageable pageable);
    boolean deleteProduct(Product product);
    List<ProductDto> getHighlightedProducts();
    boolean setHighlighted(Long id, boolean choice);
    List<ProductDto> generateIndexProducts();
    List<Product> getAllProducts();

}
