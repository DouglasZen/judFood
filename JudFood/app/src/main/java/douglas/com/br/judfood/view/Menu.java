package douglas.com.br.judfood.view;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.favorito.FavoritoActivity;
import douglas.com.br.judfood.view.home.HomeActivity;
import douglas.com.br.judfood.view.login.LoginActivity;
import douglas.com.br.judfood.view.promocao.PromocaoActivity;
import douglas.com.br.judfood.view.restaurante.RestauranteActivity;


public class Menu extends Fragment {
    public boolean abrir = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view = inflater.inflate(R.layout.fragment_menu, container, false);
        setRetainInstance(true);
        view.findViewById(R.id.btMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu(view);
            }
        });
        view.findViewById(R.id.btSair).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sair(v);
            }
        });

        view .findViewById(R.id.btFavorito).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                favoritos(v);
            }
        });

        view.findViewById(R.id.btRestaurante).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                restaurante();
            }
        });

        view.findViewById(R.id.btPromocao).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                promocao();
            }
        });
        view.findViewById(R.id.btHome).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                home();
            }
        });
        return view;
    }

    public void menu(View view){
        ImageButton bmenu = (ImageButton) view.findViewById(R.id.btMenu);
        ImageButton bsair = (ImageButton) view.findViewById(R.id.btSair);
        ImageButton bfav = (ImageButton) view.findViewById(R.id.btFavorito);
        ImageButton brest = (ImageButton) view.findViewById(R.id.btRestaurante);
        ImageButton bpromo = (ImageButton) view.findViewById(R.id.btPromocao);
        ImageButton bhome = (ImageButton) view.findViewById(R.id.btHome);
        AnimatorSet lista = new AnimatorSet();
        ObjectAnimator obj;

        if(!abrir){
            obj = ObjectAnimator.ofFloat(bmenu, "rotation", 0, -90);
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bhome, "x", bfav.getX(), bmenu.getX() + (bmenu.getWidth()));
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bfav, "x", bfav.getX(), bmenu.getX() + (bmenu.getWidth() + bhome.getWidth()));
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(brest, "x", brest.getX(), bmenu.getX() + (bmenu.getWidth() + bfav.getWidth() + bhome.getWidth()));
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bpromo, "x", bpromo.getX(), bmenu.getX() + (bmenu.getWidth() + bfav.getWidth() + bpromo.getWidth() + bhome.getWidth()));
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bsair, "x", bsair.getX(), bmenu.getX() + (bmenu.getWidth() + bfav.getWidth()) + brest.getWidth() + bpromo.getWidth() + bhome.getWidth());
            lista.playSequentially(obj);

            abrir = true;
        }else{
            obj = ObjectAnimator.ofFloat(bhome, "x", bhome.getX(), bmenu.getX());
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bfav, "x", bfav.getX(), bmenu.getX() - (bmenu.getWidth() - bhome.getWidth()));
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(brest, "x", brest.getX(), bmenu.getX() - (bmenu.getWidth() - bhome.getWidth()));
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bpromo, "x", bpromo.getX(), bmenu.getX() - (bmenu.getWidth() - bhome.getWidth()));
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bsair, "x", bsair.getX(), bmenu.getX() - (bmenu.getWidth() - bhome.getWidth()));
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bmenu, "rotation", 0, 0);
            lista.playSequentially(obj);
            abrir = false;
        }
        lista.setDuration(300);
        lista.start();

    }

    public void sair(View view){
        boolean logado = Prefs.getLogado(view.getContext(), "login");
        if(AccessToken.getCurrentAccessToken() != null){
            LoginManager.getInstance().logOut();
        }

        if(logado){
            Prefs.setLogado(view.getContext(), "login", false);
        }

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void favoritos(View view){
        Intent intent = new Intent(getActivity(), FavoritoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void restaurante(){
        Intent intent = new Intent(getActivity(), RestauranteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void promocao(){
        Intent intent = new Intent(getActivity(), PromocaoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void home(){
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
