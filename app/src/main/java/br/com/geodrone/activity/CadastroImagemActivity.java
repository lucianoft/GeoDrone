package br.com.geodrone.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.geodrone.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CadastroImagemActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final int PICK_IMAGE = 1234;

    @BindView(R.id.bottom_nav_view_cad_imagem)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.imgCamera)
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_imagem);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_camera:
                Intent i = new Intent(this,CameraFotoActivity.class);
                startActivity(i);
                break;
            case R.id.action_galeria:
                i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), PICK_IMAGE);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_CANCELED){
            if(requestCode == PICK_IMAGE){
                Uri imagemSelecionada = data.getData();
                Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(imagemSelecionada));
                Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 1080, 1000, true);
                imageView.setImageBitmap(bitmapReduzido);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Toast.makeText(getApplicationContext(), imagemSelecionada.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
