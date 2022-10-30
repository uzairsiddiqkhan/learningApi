package com.example.apilearning;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiInterface {

    // type of request (end point of response)
    @GET("readall.php")
    //response when call
    Call<responseApi> getpoetry();

    // FOR DELETE API
    @FormUrlEncoded // because we send data through form_data therefore it will take it as form
    @POST("deletepoetry.php")
    Call<deleteresponse> deletecall(@Field("poetry_id") String poetry_id);

    // for add poetry
    @FormUrlEncoded
    @POST("addpoetry.php")
    // because the response has two methods 1- status and 2_ message which is in deleteResponse class
    Call<deleteresponse> addCall(@Field("poetry") String poetry,
                                 @Field("poet_name") String poet_name);

    @FormUrlEncoded
    @POST("updatepoetry.php")
    Call<deleteresponse> updateCall
            (@Field("poetry_update") String poetryUpdate, @Field("poet_update") String poetUpdate, @Field("id") String id);
}
