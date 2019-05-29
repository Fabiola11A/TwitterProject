package com.example.twitter3;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.twitter3.helper.MyPreferenceManager;
import com.example.twitter3.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

/**Clase que maneja las variables declaradas en el user_timeline_fragment.xml*/

public class UserTimelineFragment extends Fragment {

    private Context context;
    private RecyclerView userTimelineRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TweetTimelineRecyclerViewAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public UserTimelineFragment() {
    }

    public static UserTimelineFragment newInstance() {

        Bundle args = new Bundle();

        UserTimelineFragment fragment = new UserTimelineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_timeline_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpSwipeRefreshLayout(view);
        setUpRecyclerView(view);
        loadUserTimeline();
    }

    /**
     * Método que prepara el recycler view
     *parametro "view" del fragmento
     */
    private void setUpRecyclerView(@NonNull View view) {
        userTimelineRecyclerView = view.findViewById(R.id.user_timeline_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);//it should be Vertical only
        userTimelineRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * Método que nos carga el timeline sobre el recycler view
     */
    private void loadUserTimeline() {
        MyPreferenceManager myPreferenceManager = new MyPreferenceManager(context);

        //Construye el time del usuario, obtiene el id de usuario, su nombre que aparece en pantalla, respuestas a tweets y retweets. Además nos desplega 50 tweets del timeline
        UserTimeline userTimeline = new UserTimeline.Builder()
                .userId(myPreferenceManager.getUserId())//User ID of the user to show tweets for
                .screenName(myPreferenceManager.getScreenName())//screen name of the user to show tweets for
                .includeReplies(true)//Whether to include replies. Defaults to false.
                .includeRetweets(true)//Whether to include re-tweets. Defaults to true.
                .maxItemsPerRequest(50)//Max number of items to return per request
                .build();


        //Se construye el adaptador para el  recycler view
        adapter = new TweetTimelineRecyclerViewAdapter.Builder(context)
                .setTimeline(userTimeline)//setear el timeline creado
                //accion callback que escucha cuando un usuario da me gusta o no me gusta a un tweet
                .setOnActionCallback(new Callback<Tweet>() {
                    @Override
                    public void success(Result<Tweet> result) {
                        //realiza alguna accion en caso de exito
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        //realiza alguna accion en caso de que ocurra un fallo
                    }
                })
                //configuracion del estilo de vistas del tweet
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();

        //Se seteea el adaptador creado a el recycler view
        userTimelineRecyclerView.setAdapter(adapter);
    }

    /**
     * deszlizar para refrescar el layout
     * parametro view del fragmento
     */
    private void setUpSwipeRefreshLayout(View view) {

        //Encontramos el id de la accion deslizar para refrescar el layout
        swipeRefreshLayout = view.findViewById(R.id.user_swipe_refresh_layout);

        //se implementa el listener (escuchador) de la accion de refrescar
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //nos retorna el if si el adaptador es nulo
                if (adapter == null)
                    return;

                //seteo de la accion refrescar a verdadero (true)
                swipeRefreshLayout.setRefreshing(true);
                adapter.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        //si la respuesta fue exitosa la accion refrescar se hace falsa (false)
                        //Toast muestra un mensaje de tweets refrescados/actualizados
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(context, "Tweets refreshed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Toast muestra el mensaje que no se pudieron refrescar los tweets en caso de que la accion haya fallado
                        Toast.makeText(context, "Failed to refresh tweets.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
