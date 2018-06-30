package tcc.faculdade.com.vamosaprender.app.retrofit;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.faculdade.com.vamosaprender.app.servicos.LoginService;
import tcc.faculdade.com.vamosaprender.app.servicos.ScoreService;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(loggingInterceptor);

        this.retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl("http://api-vamos-aprender.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public LoginService getLoginService() {
        return this.retrofit.create(LoginService.class);
    }

    public ScoreService setScoreService(){return this.retrofit.create(ScoreService.class);}

    public ScoreService setScoreListService(){return this.retrofit.create(ScoreService.class);}


}
