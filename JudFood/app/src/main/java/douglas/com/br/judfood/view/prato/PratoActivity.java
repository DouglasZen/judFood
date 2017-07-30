package douglas.com.br.judfood.view.prato;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import douglas.com.br.judfood.R;
import douglas.com.br.judfood.prato.Prato;

public class PratoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prato);
        Bundle extras = getIntent().getExtras();

        Prato prato = (Prato) extras.getSerializable("prato");
        ImageView iv = (ImageView) findViewById(R.id.imageView);
        byte[] image = Base64.decode(prato.getImagem(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        iv.setImageBitmap(bitmap);
        iv.setVisibility(View.VISIBLE);
    }
}
