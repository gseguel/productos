package com.gseguel.productos.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gseguel.productos.model.Product;

@Mapper
public interface ProductsMapper {

    @Insert("INSERT INTO products (sku, name, brand, size, price, principal_image, other_images) "
            + "VALUES (#{product.sku},#{product.name},#{product.brand},#{product.size},#{product.price},"
            + "#{product.principalImage},#{product.otherImages})")
    public abstract int save(@Param("product") Product product);

    @Select("SELECT * FROM products WHERE sku = #{id}")
    @Results(value = { 
            @Result(column = "sku", property = "sku"), 
            @Result(column = "name", property = "name"),
            @Result(column = "brand", property = "brand"), 
            @Result(column = "size", property = "size"),
            @Result(column = "price", property = "price"),
            @Result(column = "principal_image", property = "principalImage"),
            @Result(column = "other_images", property = "otherImages") 
            }
    )
    public abstract Product findById(@Param("id") String sku);

    @Select("SELECT * FROM products")
    @Results(value = { 
            @Result(column = "sku", property = "sku"), 
            @Result(column = "name", property = "name"),
            @Result(column = "brand", property = "brand"), 
            @Result(column = "size", property = "size"),
            @Result(column = "price", property = "price"),
            @Result(column = "principal_image", property = "principalImage"),
            @Result(column = "other_images", property = "otherImages") 
            }
    )
    public abstract List<Product> getListProducts();

    @Update("UPDATE products SET name = #{product.name}, brand = #{product.brand}, "
            + "size = #{product.size}, price = #{product.price}, "
            + "principal_image = #{product.principalImage}, other_images = #{product.otherImages}"
            + " WHERE sku = #{product.sku}")
    public abstract int update(@Param("product") Product product);

    @Delete("DELETE FROM products WHERE sku = #{id}")
    public abstract int delete(@Param("id") String sku);
    
    @Select("SELECT nextval('sku_seq')")
    public abstract int findSequence();
}
