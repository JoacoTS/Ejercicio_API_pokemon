package llamarApi;

import llamarApi.infoMovimiento.Move;
import llamarApi.infoPokemon.Pokemon;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface Api {

    @GET("pokemon/{pokemon}")
    Call<Pokemon> infoPokemon(@Path("pokemon") String pokemon);

    @GET("move/{move}")
    Call<Move> listadoPokemones(@Path("move") String move);

}
