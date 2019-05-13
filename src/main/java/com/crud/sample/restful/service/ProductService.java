package com.crud.sample.restful.service;

import com.crud.sample.restful.model.Product;
import com.crud.sample.restful.repository.ProductRespository;
import com.crud.sample.restful.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRespository productRespository;

    public List<ProductVO> findAll() {
        return productRespository.findAll().stream()
                .map(p -> getProductVO(p))
                .collect(Collectors.toList())
                ;
    }

    public ProductVO save(ProductVO product) {
        return getProductVO(productRespository.save(this.getProduct(product)));
    }


    /**
     * Transform Product to ProductVO
     * @param product
     * @return
     */
    private ProductVO getProductVO(Product product) {
        return ProductVO.builder()
                .created(product.getCreated())
                .updated(product.getUpdated())
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    /**
     * Transform Product to ProductVO
     * @param product
     * @return
     */
    private Product getProduct(ProductVO product) {
        return Product.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
