package com.gseguel.productos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gseguel.productos.model.Mensaje;
import com.gseguel.productos.model.Product;
import com.gseguel.productos.services.ProductsService;

@Controller
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200/") 
public class ProductsController {
	@Autowired
	private ProductsService service;

	@PostMapping(value = "/save")
    @Transactional
	ResponseEntity<Mensaje> saveProduct(@RequestBody Product product){
		if(product == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		this.service.save(product);
		return new ResponseEntity<>(new Mensaje("Datos Guardados","El producto ha sido guardado correctamente"), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    @Transactional
    ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (product == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        this.service.update(product);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }
	
    @DeleteMapping(value = "/delete/{sku}")
    @Transactional
    ResponseEntity<Mensaje> deleteProduct(@PathVariable("sku") String sku) {
        if (sku == null) {
            return ResponseEntity.unprocessableEntity().build();
        }
        this.service.delete(sku);
		return new ResponseEntity<>(new Mensaje("Datos Eliminados","El producto ha sido eliminado correctamente"), HttpStatus.ACCEPTED);
    }
    
    @GetMapping(value = "/listProducts")
    ResponseEntity<List<Product>> getListProduct() {
        Optional<List<Product>> listProducts = this.service.getListProducts();
        return new ResponseEntity<>(listProducts.get(), HttpStatus.ACCEPTED);
    }
    
    @GetMapping(value = "/productById/{sku}")
    ResponseEntity<Product> getProduct(@PathVariable("sku") String sku) {
        Optional<Product> product = this.service.getProduct(sku);
        return new ResponseEntity<>(product.get(), HttpStatus.ACCEPTED);
    }
}
