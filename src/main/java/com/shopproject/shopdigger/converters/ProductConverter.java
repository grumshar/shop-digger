package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.CartItem;
import com.shopproject.shopdigger.model.Product;

public interface ProductConverter {

    public Product convert(ProductDto productDto);

    public ProductDto convertDto(Product product);

    public CartItem convertCartItem(ProductDto productDto);
}
