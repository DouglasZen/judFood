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


import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.util.Prefs;
import douglas.com.br.judfood.view.login.LoginActivity;


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
        return view;
    }

    public void menu(View view){
        ImageButton bmenu = (ImageButton) view.findViewById(R.id.btMenu);
        ImageButton bsair = (ImageButton) view.findViewById(R.id.btSair);
        AnimatorSet lista = new AnimatorSet();
        ObjectAnimator obj;

        if(!abrir){
            obj = ObjectAnimator.ofFloat(bmenu, "rotation", 0, -90);
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bsair, "x", bsair.getX(), bmenu.getX() + (bmenu.getWidth() - 2));
            lista.playSequentially(obj);

            abrir = true;
        }else{
            obj = ObjectAnimator.ofFloat(bsair, "x", bsair.getX(), bmenu.getX() + 2);
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

}
