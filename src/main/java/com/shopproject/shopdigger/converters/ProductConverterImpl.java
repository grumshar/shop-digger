package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.CartItem;
import com.shopproject.shopdigger.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductConverterImpl implements productConverter {


    @Override
    public Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setUnit(productDto.getUnit());
        product.setUnitAmount(productDto.getUnitAmount());
        product.setDescription(productDto.getDescription());
        product.setEanCode(productDto.getEanCode());
        return product;
    }

    @Override
    public ProductDto convertDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setUnit(product.getUnit());
        productDto.setUnitAmount(product.getUnitAmount());
        productDto.setDescription(product.getDescription());
        productDto.setEanCode(productDto.getEanCode());
        return productDto;
    }

    @Override
    public CartItem convertCartItem(ProductDto productDto) {

        CartItem cartItem = new CartItem();
        cartItem.setId(productDto.getId());
        cartItem.setName(productDto.getName());
        cartItem.setPrice(productDto.getPrice());
        cartItem.setUnit(productDto.getUnit());
        cartItem.setUnitAmount(productDto.getUnitAmount());

        return cartItem ;
    }


}
