package llamarApi;

import llamarApi.infoMovimiento.Move;
import llamarApi.infoPokemon.Pokemon;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LlamaApi {

    private static LlamaApi llamaApi;
    private static String url = "https://pokeapi.co/api/v2/";
    private Retrofit retrofit;

    public static void main(String[] args) {
        llamaApi = new LlamaApi();
        llamaApi.iniciarConexion();

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.printf("Ingresar pedido:");
            String pedido = reader.readLine();
            System.out.printf("Ingresar nombre:");
            String nombre = reader.readLine();
            if(pedido.equals("pokemon"))
            {
                llamaApi.imagenYMovimientos(nombre);
            }
            else if(pedido.equals("move"))
            {
                llamaApi.listarPokemones(nombre);
            }
        }
        catch(IOException io)
        {
            System.out.println("Error input teclado");
        }
        System.exit(0);
    }


    private void iniciarConexion()
    {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void imagenYMovimientos(String nombre)
    {
        try
        {
            Api api = this.retrofit.create(Api.class);
            Call<Pokemon> p = api.infoPokemon(nombre);

            Response<Pokemon> res = p.execute();

            Pokemon info = res.body();

            if(res.isSuccessful())
            {
                System.out.println(info.getSprites().getFront_default());
                for(int i = 0; i < info.getMoves().size(); i++)
                {
                    System.out.println(info.getMoves().get(i).getMove().getName());
                }
            }
        }
        catch(IOException ioe)
        {
            System.out.println("Error IO");
        }
    }

    public void listarPokemones(String nombre_movimiento)
    {
        try
        {
            Api api = this.retrofit.create(Api.class);
            Call<Move> m = api.listadoPokemones(nombre_movimiento);

            Response<Move> res = m.execute();

            Move info = res.body();

            if(res.isSuccessful())
            {
                for(int i = 0; i < info.getLearned_by_pokemon().size(); i++)
                {
                    System.out.println(info.getLearned_by_pokemon().get(i).getName());
                }
            }
        }
        catch(IOException ioe) {
            System.out.println("Error IO");
        }
    }

}
