package com.example.ditenun.network;

import com.example.ditenun.model.Category;
import com.example.ditenun.model.Order;
import com.example.ditenun.model.PaymentMethod;
import com.example.ditenun.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WooCommerceNetworkInterface {

    @GET("wp-json/wc/v3/products/categories")
    Call<List<Category>> getListCategories();

    @GET("wp-json/wc/v3/products")
    Call<List<Product>> getListProducts();

    @GET("wp-json/wc/v3/products/{id}")
    Call<Product> getDetailProduct(@Path("id") Integer id);

    @GET("wp-json/wc/v3/payment_gateways")
    Call<List<PaymentMethod>> getListPaymentGetaways();

    @GET("wp-json/wc/v3/orders")
    Call<List<Order>> getListOrders();

    @GET("wp-json/wc/v3/orders")
    Call<Order> getDetailOrder(@Query("id") String id);

}
