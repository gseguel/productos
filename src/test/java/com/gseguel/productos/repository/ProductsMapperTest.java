package com.gseguel.productos.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.gseguel.productos.model.Product;

@RunWith(MockitoJUnitRunner.class)
class ProductsMapperTest {


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
    void save() {
        MockitoAnnotations.initMocks(this);
        prepararEntidad();
        when(productMapper.save(ArgumentMatchers.any())).thenReturn(1);
        int saveProduct = productMapper.save(prepararEntidad());
        assertEquals(saveProduct, 1);

    }

    @Test
    void findById() {
        MockitoAnnotations.initMocks(this);
        String sku = "FAL-8406270";
        when(productMapper.findById(ArgumentMatchers.any())).thenReturn(prepararEntidad());
        Product product = productMapper.findById(sku);
        assertEquals(product, prepararEntidad());
    }

    @Test
    void getListProducts() {
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(prepararEntidad());
        MockitoAnnotations.initMocks(this);
        when(productMapper.getListProducts()).thenReturn(listProduct);
        List<Product> listProducts = productMapper.getListProducts();
        assertEquals(listProducts, listProduct);
    }

    @Test
    void update() {
        MockitoAnnotations.initMocks(this);
        prepararEntidad();
        when(productMapper.update(ArgumentMatchers.any())).thenReturn(1);
        int saveProduct = productMapper.update(prepararEntidad());
        assertEquals(saveProduct, 1);
    }

    @Test
    void delete() {
        String skuDelete = "FAL-8406270";
        MockitoAnnotations.initMocks(this);
        prepararEntidad();
        when(productMapper.delete(ArgumentMatchers.any())).thenReturn(1);
        int saveProduct = productMapper.delete(skuDelete);
        assertEquals(saveProduct, 1);
    }

    @Test
    void findSequence() {
        MockitoAnnotations.initMocks(this);
        prepararEntidad();
        when(productMapper.findSequence()).thenReturn(1000001);
        int sequence = productMapper.findSequence();
        assertEquals(sequence, 1000001);
    }
}