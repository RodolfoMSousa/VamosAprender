package tcc.faculdade.com.vamosaprender.app.servicos;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import tcc.faculdade.com.vamosaprender.app.entidades.Score;

public interface ScoreService {

    @POST("score/")
    Call<Score> setScore(@Body RequestBody json);

    @POST("score/list")
    Call<List<Score>> setScoreList(@Body RequestBody json);
}
