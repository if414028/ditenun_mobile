package com.example.ditenun.network;

import com.example.ditenun.model.Category;
import com.example.ditenun.model.Order;
import com.example.ditenun.model.PaymentMethod;
import com.example.ditenun.model.Product;
import com.example.ditenun.network.response.ResponseCommerceLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WooCommerceNetworkInterface {

    @POST("wp-json/jwt-auth/v1/token")
    Call<ResponseCommerceLogin> login(
            @Header("Authorization") String token,
            @Query("username") String username,
            @Query("password") String password
    );

    @GET("wp-json/wc/v3/products/categories")
    Call<List<Category>> getListCategories(
            @Query("consumer_key") String consumerKey,
            @Query("consumer_secret") String consumerSecret
    );

    @GET("wp-json/wc/v3/products")
    Call<List<Product>> getListProducts(
            @Query("page") Integer page,
            @Query("per_page") Integer pageSize,
            @Query("consumer_key") String consumerKey,
            @Query("consumer_secret") String consumerSecret
    );

    @GET("wp-json/wc/v3/products/{id}")
    Call<Product> getDetailProduct(
            @Path("id") Integer id,
            @Query("consumer_key") String consumerKey,
            @Query("consumer_secret") String consumerSecret);

    @GET("wp-json/wc/v3/payment_gateways")
    Call<List<PaymentMethod>> getListPaymentGetaways();

    @GET("wp-json/wc/v3/orders")
    Call<List<Order>> getListOrders(
            @Query("consumer_key") String consumerKey,
            @Query("consumer_secret") String consumerSecret,
            @Query("customer") Integer customerId,
            @Query("page") Integer page,
            @Query("per_page") Integer pageSize
    );

    @GET("wp-json/wc/v3/orders")
    Call<Order> getDetailOrder(@Query("id") String id);

}
