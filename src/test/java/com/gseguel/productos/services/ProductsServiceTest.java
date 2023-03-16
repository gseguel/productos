package com.gseguel.productos.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;

import com.gseguel.productos.exceptions.EntityNotFoundException;
import com.gseguel.productos.exceptions.OperationDBFailException;
import com.gseguel.productos.model.Product;
import com.gseguel.productos.repository.ProductsMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
class ProductsServiceTest {

    @InjectMocks
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
    void whenSaveProduct() {
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.findSequence()).thenReturn(1000001);
        when(productMapper.save(ArgumentMatchers.any())).thenReturn(1);
        Product productCreated = productService.save(product);
        log.info(productCreated.toString());
        assertThat(productCreated).isSameAs(product);
    }

    @Test
    void errorSaveThrowProduct() {
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.findSequence()).thenReturn(1000001);
        when(productMapper.save(ArgumentMatchers.any())).thenReturn(0);
        try {
            productService.save(product);
        } catch (OperationDBFailException e) {
            assertThat(e.getMessage(), is("No fue posible registrar el producto"));
        }
    }
    
    @Test
    void errorSequenceSaveThrowProduct() {
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.findSequence()).thenReturn(100000);
        try {
            productService.save(product);
        } catch (OperationDBFailException e) {
            assertThat(e.getMessage(), is("La sequencia no se encuentra dentro del rango"));
        }
    }

    @Test
    void errorSaveExceptionProduct() {
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.findSequence()).thenReturn(1000001);
        when(productMapper.save(product)).thenThrow(new DataIntegrityViolationException("...Error en DB..."));
        try {
            productService.save(product);
        } catch (DataIntegrityViolationException e) {
        }
    }

    @Test
    void wenUpdateProduct() {
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.update(ArgumentMatchers.any())).thenReturn(1);
        Product productCreated = productService.update(product);
        assertThat(productCreated).isSameAs(product);
    }

    @Test
    void errorUpdateThrowProduct() {
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.update(ArgumentMatchers.any())).thenReturn(0);
        try {
            productService.update(product);
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage(), is("Error al actualizar el producto"));
        }
    }

    @Test
    void errorUpdateExceptionProduct() {
        MockitoAnnotations.initMocks(this);
        Product product = prepararEntidad();
        when(productMapper.update(product)).thenThrow(new DataIntegrityViolationException("...Error en DB..."));
        try {
            productService.update(product);
        } catch (DataIntegrityViolationException e) {
        }
    }
    
    @Test
    void delete() {
        MockitoAnnotations.initMocks(this);
        String skuDelete = "FAL-8406270";
        when(productMapper.delete(ArgumentMatchers.any())).thenReturn(1);
        String sku = productService.delete(skuDelete);
        assertThat(sku).isSameAs(skuDelete);
    }

    @Test
    void errorDeleteProduct() {
        String skuDelete = "FAL-8406270";
        MockitoAnnotations.initMocks(this);
        when(productMapper.delete(ArgumentMatchers.any())).thenReturn(0);
        try {
            productService.delete(skuDelete);
        } catch (EntityNotFoundException e) {
            assertThat(e.getMessage(), is("No existe un producto con el id."));
        }
    }

    @Test
    void getListProducts() {
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(prepararEntidad());
        MockitoAnnotations.initMocks(this);
        when(productMapper.getListProducts()).thenReturn(listProduct);
        Optional<List<Product>> listProducts = productService.getListProducts();
        assertNotNull(listProducts.get());
    }

    @Test
    void getProduct() {
        MockitoAnnotations.initMocks(this);
        String sku = "FAL-8406270";
        when(productMapper.findById(ArgumentMatchers.any())).thenReturn(prepararEntidad());
        Optional<Product> product = productService.getProduct(sku);
        assertNotNull(product.get());
    }
}