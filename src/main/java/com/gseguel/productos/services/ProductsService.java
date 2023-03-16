package com.gseguel.productos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gseguel.productos.exceptions.EntityNotFoundException;
import com.gseguel.productos.exceptions.OperationDBFailException;
import com.gseguel.productos.model.Product;
import com.gseguel.productos.repository.ProductsMapper;
import com.gseguel.productos.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductsService {
    private static final String err_save = "Error al crear el producto";
    private static final String err_update = "Error al actualizar el producto";
    private static final String err_delete = "Error al aliminar el producto";
    private static final String err_seq = "Error de sequencia";

    private static final String errMsgSave = "No fue posible registrar el producto";
    private static final String errMsgSeq = "La sequencia no se encuentra dentro del rango";

    private static final String errMsgUpdateDelete = "No existe un producto con el id.";
    private static final String errMsgSaveData = "Ocurrió un error de PostgreSQL al registrar el producto";
    private static final String errMsgUpdateData = "Ocurrió un error de PostgreSQL al actualizar el producto";
    private static final String fal = "FAL-";
    @Autowired
    ProductsMapper productsMapper;

    public Product save(Product product) {
        try {
            int sequence = getSequence();
            if (!Utils.ranges(1000000, 99999999).contains(sequence)) {
                log.error(err_seq);
                throw new OperationDBFailException(errMsgSeq);
            }
            product.setSku(fal.concat(String.valueOf(sequence)));
            if (this.productsMapper.save(product) == 0) {
                log.error(String.format(err_save, errMsgSave));
                throw new OperationDBFailException(errMsgSave);
            }
        } catch (DataIntegrityViolationException e) {
            log.error(String.format(err_save, errMsgSaveData, e.toString()));
        }
        return product;
    }

    public Product update(Product product) {
        try {
            if (this.productsMapper.update(product) == 0) {
                String errMsg = String.format(errMsgUpdateDelete, product.getSku());
                log.error(String.format(err_update, errMsg));
                throw new EntityNotFoundException(err_update);
            }
        } catch (DataIntegrityViolationException e) {
            log.error(String.format(err_update, errMsgUpdateData, e.toString()));
        }
        return product;
    }

    public String delete(String sku) {
        if (this.productsMapper.delete(sku) == 0) {
            String errMsg = String.format(errMsgUpdateDelete, sku);
            log.error(String.format(err_delete, errMsg));
            throw new EntityNotFoundException(errMsgUpdateDelete);
        }
        return sku;
    }

    public Optional<List<Product>> getListProducts() {
        Optional<List<Product>> listProducts = Optional.ofNullable(this.productsMapper.getListProducts());
        return listProducts;
    }

    public Optional<Product> getProduct(String sku) {
        Optional<Product> prodOptional = Optional.ofNullable(this.productsMapper.findById(sku));
        return prodOptional;
    }

    private int getSequence() {
        int sequence = this.productsMapper.findSequence();
        return sequence;
    }

}
