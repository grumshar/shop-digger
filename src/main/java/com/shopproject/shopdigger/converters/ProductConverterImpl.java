package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.CartItem;
import com.shopproject.shopdigger.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverterImpl implements ProductConverter {


    @Override
    public Product convert(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        product.setUnit(productDto.getUnit());
        product.setUnitAmount(productDto.getUnitAmount());
        product.setDescription(productDto.getDescription());
        product.setEanCode(productDto.getEanCode());
        product.setHighlighted(productDto.isHighlighted());
        product.setImgUrl(productDto.getImgUrl());
        return product;
    }

    @Override
    public ProductDto convertDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory());
        productDto.setUnit(product.getUnit());
        productDto.setUnitAmount(product.getUnitAmount());
        productDto.setDescription(product.getDescription());
        productDto.setEanCode(productDto.getEanCode());
        productDto.setHighlighted(product.isHighlighted());
        productDto.setImgUrl(product.getImgUrl());
        return productDto;
    }

    @Override
    public CartItem convertCartItem(ProductDto productDto) {

        CartItem cartItem = new CartItem();
        cartItem.setId(productDto.getId());
        cartItem.setName(productDto.getName());
        cartItem.setUnit(productDto.getUnit());
        cartItem.setUnitAmount(productDto.getUnitAmount());
        cartItem.setPrice(productDto.getPrice());

        return cartItem ;
    }


}
