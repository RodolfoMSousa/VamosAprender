package tcc.faculdade.com.vamosaprender.app.servicos;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tcc.faculdade.com.vamosaprender.app.entidades.Aluno;
import tcc.faculdade.com.vamosaprender.app.entidades.Login;
import tcc.faculdade.com.vamosaprender.app.entidades.Usuario;

public interface LoginService {

    @POST("aluno/login/")
    Call<Aluno> getLogin(@Body RequestBody json);


    @POST("aluno/")
    Call<Aluno> setLogin(@Body RequestBody json);
//
//    @POST("login/")
//    Call<Login> setLogin(@Body RequestBody json);
//
//    @GET("userWS/{id}")
//    Call<Usuario> getUsuario(@Path("id")int id);

}
