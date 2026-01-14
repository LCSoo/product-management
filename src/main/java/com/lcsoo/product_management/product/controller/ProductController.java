package com.lcsoo.product_management.product.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lcsoo.product_management.product.domain.Product;
import com.lcsoo.product_management.product.dto.AddProductRequest;
import com.lcsoo.product_management.product.dto.AddProductResponse;
import com.lcsoo.product_management.product.dto.RemoveProductRequest;
import com.lcsoo.product_management.product.dto.UpdateStockRequest;
import com.lcsoo.product_management.product.dto.UpdateStockResponse;
import com.lcsoo.product_management.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody AddProductRequest request) {
        AddProductResponse response = productService.addProduct(request);
        return new ResponseEntity<>(response.product(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<Product> products = productService.getProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateStock(@RequestBody UpdateStockRequest request) {
        UpdateStockResponse response = productService.updateStock(request);
        return new ResponseEntity<>(response.product(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> removeProduct(@RequestBody RemoveProductRequest request) {
        productService.removeProduct(request);
        return new ResponseEntity<>("삭제 완료",HttpStatus.OK);
    }
}
