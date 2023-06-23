package apiclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface RecordsInterface {
    @GET("records/")
    Call<List<Records>> getRecords();

    @FormUrlEncoded
    @POST("records/")
    Call<Records> postRecords(@Field("name")String name, @Field("price")int price, @Field("quantity")int quantity);

    @DELETE("records/")
    Call<Records> delRecords(@Query("id") int id);
}
