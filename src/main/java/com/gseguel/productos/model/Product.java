package com.gseguel.productos.model;

import lombok.Data;

@Data
public class Product {

	private String sku;
	private String name;
	private String brand;
	private String size;
	private Double price;
	private String principalImage;
	private String otherImages;
}
