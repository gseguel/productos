package com.gseguel.productos.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gseguel.productos.model.Mensaje;
import com.gseguel.productos.model.Product;
import com.gseguel.productos.repository.ProductsMapper;
import com.gseguel.productos.services.ProductsService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
class ProductsControllerTest {

    @InjectMocks
    private ProductsController controller;
    @Mock
    private ProductsService productService;
    @Mock
    private ProductsMapper productMapper;

    private Product prepararEntidad() {
        Product product = new Product();
        product.setSku("FAL-8406270");
        product.setName("500 zapatilla mujer urbana");
        product.setBrand("NEW BALANCE");
        product.setPrice(42990.00);
        product.setSize("37");
        product.setOtherImages("https://falabella.scene7.com/is/image/Falabella/8406270_1");
        product.setPrincipalImage(null);
        return product;
    }
    
    @Test
    void saveProduct() {
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.findSequence()).thenReturn(1000001);
        when(productMapper.save(product)).thenReturn(1);
        when(productService.save(product)).thenReturn(product);

        ResponseEntity<Mensaje> productCreated = controller.saveProduct(product);
        assertEquals(productCreated.getStatusCode(), HttpStatus.CREATED);

    }

    @Test
    void updateProduct() {
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.update(product)).thenReturn(1);
        when(productService.update(product)).thenReturn(product);

        ResponseEntity<Product> productCreated = controller.updateProduct(product);
        assertEquals(productCreated.getStatusCode(), HttpStatus.ACCEPTED);

    }

    @Test
    void deleteProduct() {
        String skuDelete = "FAL-8406270";
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.delete(skuDelete)).thenReturn(1);
        when(productService.delete(skuDelete)).thenReturn(skuDelete);

        ResponseEntity<Mensaje> productDelete = controller.deleteProduct(skuDelete);
        assertEquals(productDelete.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    void getListProduct() {
        MockitoAnnotations.initMocks(this);
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(prepararEntidad());
        Optional<List<Product>> optionalProduct = Optional.ofNullable(listProduct); 
        when(productMapper.getListProducts()).thenReturn(listProduct);
        when(productService.getListProducts()).thenReturn(optionalProduct);

        ResponseEntity<List<Product>> product = controller.getListProduct();
        log.info(product.getBody().toString());
        assertEquals(product.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    void getProduct() {
        String sku = "FAL-8406270";

        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        Optional<Product> optionalProduct = Optional.ofNullable(product); 

        when(productMapper.findById(sku)).thenReturn(product);
        when(productService.getProduct(sku)).thenReturn(optionalProduct);

        ResponseEntity<Product> producto = controller.getProduct(sku);
        assertEquals(producto.getStatusCode(), HttpStatus.ACCEPTED);
    }
}