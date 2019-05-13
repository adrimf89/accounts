package com.crud.sample.restful.api;

import com.crud.sample.restful.service.ProductService;
import com.crud.sample.restful.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductAPI {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductVO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ProductVO product) {
        return ResponseEntity.ok(productService.save(product));
    }
}
