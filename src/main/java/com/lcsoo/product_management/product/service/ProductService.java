package com.lcsoo.product_management.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcsoo.product_management.product.domain.Product;
import com.lcsoo.product_management.product.dto.AddProductRequest;
import com.lcsoo.product_management.product.dto.AddProductResponse;
import com.lcsoo.product_management.product.dto.RemoveProductRequest;
import com.lcsoo.product_management.product.dto.StockType;
import com.lcsoo.product_management.product.dto.UpdatePriceRequest;
import com.lcsoo.product_management.product.dto.UpdatePriceResponse;
import com.lcsoo.product_management.product.dto.UpdateStockRequest;
import com.lcsoo.product_management.product.dto.UpdateStockResponse;
import com.lcsoo.product_management.product.repository.ProductRepository;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /* 제품 추가 */
	public AddProductResponse addProduct(AddProductRequest request) {
        Product savedProduct = productRepository.save(request.toProduct());
        return new AddProductResponse(savedProduct);
    }

    /* 제품 목록 조회 */
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /* 제품 수량 추가 */
    public UpdateStockResponse updateStock(UpdateStockRequest request) {
        Product product = productRepository.findByName(request.productName()).get();

        if(request.stockType() == StockType.ADD) {
            product.addStock(request.stock());
        } else {
            product.removeStock(request.stock());
        }

        Product updateProduct = productRepository.save(product);

        return new UpdateStockResponse(updateProduct);
    }
    
    /* 제품 가격 수정 */
    public UpdatePriceResponse updatePrice(UpdatePriceRequest request) {
        Product product = productRepository.findByName(request.productName()).get();
        product.setPrice(request.price());

        Product savedProduct = productRepository.save(product);

        return new UpdatePriceResponse(savedProduct);
    }

    /* 제품 삭제 */
    public void removeProduct(RemoveProductRequest request) {
        Product product = productRepository.findByName(request.productName()).get();
        productRepository.delete(product);
    }
  

     
}
