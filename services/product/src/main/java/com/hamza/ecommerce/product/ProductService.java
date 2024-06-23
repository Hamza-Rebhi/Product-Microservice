package com.hamza.ecommerce.product;

import com.hamza.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
        public Integer createProduct(ProductRequest productRequest){
            return productRepository.save(productMapper.toProduct(productRequest)).getId();
        }
    public ProductResponse findById(Integer id) {
            return productRepository.findById(id)
                    .map(productMapper::toProductResponse)
                    .orElseThrow(()->new EntityNotFoundException("Product not found with ID::"+id));
    }
    public List<ProductResponse> findAll() {
            return productRepository.findAll()
                    .stream()
                    .map(productMapper::toProductResponse)
                    .collect(Collectors.toList());
    }
    @Transactional(rollbackOn = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> request
    ) {
            var productsIds=request
                    .stream()
                    .map(ProductPurchaseRequest::productId)
                    .toList();
            var storedProducts=productRepository.findAllByIdInOrderById(productsIds);
            if(productsIds.size() != storedProducts.size()){
                throw new ProductPurchaseException("One or more products does not exist");
            }
            var sortedRequest= request.stream()
                    .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                    .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
            for(int i=0;i< storedProducts.size();i++){
                var product=storedProducts.get(i);
                var productRequest=sortedRequest.get(i);
                if(product.getAvailableQuantity()<productRequest.quantity()){
                    throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " +productRequest.productId());
                }
                product.setAvailableQuantity(product.getAvailableQuantity()-productRequest.quantity());
                productRepository.save(product);
                purchasedProducts.add(productMapper.toproductPurchaseResponse(product,productRequest.quantity()));
            }
        return purchasedProducts;

    }

    }
