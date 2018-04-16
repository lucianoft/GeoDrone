package br.com.geodrone.ui.registroimagem;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.geodrone.BuildConfig;
import br.com.geodrone.R;
import br.com.geodrone.activity.utils.Constantes;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.model.constantes.FlagDirecao;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class RegistroImagemActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
                                                                    RegistroImagemPresenter.View {

    @BindView(R.id.bottom_nav_view_cad_imagem)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.imgCamera)
    ImageView imageView;

    @BindView(R.id.layout_cad_imagem_salvar)
    RelativeLayout layoutSalvar;

    @BindView(R.id.edit_text_obs_reg_imagem)
    EditText editTextObservacao;

    @BindView(R.id.spinner_direcao_reg_imagem)
    Spinner spinnerDirecao;

    private RegistroImagemPresenter registroImagemPresenter;

    private String flagDirecao = null;
    private File file = null;

    private GenericProgress mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_imagem);
        ButterKnife.bind(this);

        mProgress = new GenericProgress(this);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        //getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        registroImagemPresenter = new RegistroImagemPresenter(this);

        ArrayAdapter<FlagDirecao> spinnerDirecaoAdapter = new ArrayAdapter<FlagDirecao>(this, android.R.layout.simple_spinner_item, FlagDirecao.values());
        spinnerDirecao.setAdapter(spinnerDirecaoAdapter);
        spinnerDirecaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        layoutSalvar.setVisibility(View.INVISIBLE);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getPermissions();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        registroImagemPresenter.takeView(this);
        hideLoading();
    }

    private void getPermissions() {

        if (!hasPermission(Manifest.permission.CAMERA)
                || !hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || !hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            requestPermissionsSafely(new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       getPermissions();
       switch (item.getItemId()) {
            case R.id.action_camera:
                dispatchTakePictureIntent();
                break;
            case R.id.action_galeria:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                photoPickerIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(photoPickerIntent, "Selecione uma imagem"), Constantes.ACTIVITY_CODE_PICK_IMAGE);
                break;
            default:
                break;
        }
        return true;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = registroImagemPresenter.createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, Constantes.ACTIVITY_REQUEST_TAKE_PHOTO);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            file = registroImagemPresenter.getFileOnActivityResult(requestCode, resultCode, data);
            if (file != null) {
                InputStream ims = new FileInputStream(file);
                imageView.setImageBitmap(BitmapFactory.decodeStream(ims));
                layoutSalvar.setVisibility(View.VISIBLE);
           }
        }catch (Exception ex){
            this.onError(ex);
        }
    }

    private void setPic(String mCurrentPhotoPath) {
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    @OnClick(R.id.float_button_salvar_imagem)
    public void salvarImagem(){
        showLoading();
        Location location = null;
        FlagDirecao flagDirecao = (FlagDirecao) spinnerDirecao.getSelectedItem();
        registroImagemPresenter.salvar(file, flagDirecao,  editTextObservacao.getText().toString(), location);
        //imageView.setImageResource(-1);
    }

    @Override
    public void onRegitroImagemSucesso(String message) {
        imageView.setImageBitmap(null);
        imageView.destroyDrawingCache();
        hideLoading();
        file = null;
        layoutSalvar.setVisibility(View.INVISIBLE);
        showMessage(message);
    }

    @Override
    public void onRegitroImagemError(String message) {

    }

    /*@OnItemSelected(R.id.spinner_direcao_reg_imagem)
    public void onChanceDirecao(int position) {
        String flagDirecao = FlagDirecao.getValueByIndice(position);
    }*/

    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }
}
