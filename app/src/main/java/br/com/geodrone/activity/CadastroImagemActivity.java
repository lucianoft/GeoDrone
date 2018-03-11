package br.com.geodrone.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.Constantes;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CadastroImagemActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

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
                startActivityForResult(i, Constantes.ACTIVITY_CODE_TIRAR_FOTO);
                break;
            case R.id.action_galeria:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                photoPickerIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(photoPickerIntent, "Selecione uma imagem"), Constantes.ACTIVITY_CODE_PICK_IMAGE);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (resultCode != Activity.RESULT_CANCELED) {
                if (requestCode == Constantes.ACTIVITY_CODE_PICK_IMAGE) {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imageView.setImageBitmap(selectedImage);
                    Toast.makeText(getApplicationContext(), imageUri.toString(), Toast.LENGTH_SHORT).show();
                } else if (requestCode == Constantes.ACTIVITY_CODE_TIRAR_FOTO) {
                    String campinhoImagem = data.getExtras().getString(Constantes.PARAM_CAMINHO_ARQUIVO);
                    Toast.makeText(getApplicationContext(), campinhoImagem, Toast.LENGTH_SHORT).show();

                }
            }
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.toString() , Toast.LENGTH_SHORT).show();

            ex.printStackTrace();
        }
    }
}
