package br.com.geodrone.ui.registroimagem;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.Constantes;
import br.com.geodrone.activity.utils.Gps;
import br.com.geodrone.model.RegistroImagem;
import br.com.geodrone.model.constantes.FlagDirecao;
import br.com.geodrone.service.RegistroImagemService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.FileUtils;

/**
 * Created by fernandes on 01/04/2018.
 */

public class RegistroImagemPresenter extends BasePresenter<RegistroImagemPresenter.View> {

    private static  String TAG = RegistroImagemPresenter.class.getName();

    interface View {
        void onRegitroImagemSucesso(String message);
        void onRegitroImagemError(String message);

    }

    private String mCurrentPhotoPath;
    private File file;

    private RegistroImagemService registroImagemService;

    private BaseActivity activity;

    public RegistroImagemPresenter(BaseActivity activity){
        this.activity = activity;
        this.registroImagemService = new RegistroImagemService(activity);
    }

    public File createImageFile()  {
        try {
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = this.activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );

            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = image.getAbsolutePath();
            return image;
        }catch (Exception ex){}
        return null;
    }


    public File getFileOnActivityResult(int requestCode, int resultCode, Intent data){
        try {
            if (resultCode != Activity.RESULT_CANCELED) {

                if (requestCode == Constantes.ACTIVITY_REQUEST_TAKE_PHOTO) {
                    Uri imageUri = Uri.parse(mCurrentPhotoPath);
                    file = new File(imageUri.getPath());
                }else if (requestCode == Constantes.ACTIVITY_CODE_PICK_IMAGE) {
                    // This gets the URI of the image the user selected:
                    Uri selectedFileURI = data.getData();
                    file = new File(FileUtils.getRealPathFromURI(this.activity, selectedFileURI));
                    mCurrentPhotoPath = file.getPath();
                    /*final Uri imageUri = data.getData();


                    mCurrentPhotoPath = imageUri.getPath();
                    final File imageStream = this.activity.getBaseContext().getFileStreamPath(mCurrentPhotoPath);
                    mCurrentPhotoPath = imageUri.getPath();*/
                } else if (requestCode == Constantes.ACTIVITY_CODE_TIRAR_FOTO) {
                    mCurrentPhotoPath = data.getExtras().getString(Constantes.PARAM_CAMINHO_ARQUIVO);
                    file = new File(mCurrentPhotoPath);
                }
            }
        }catch (Exception ex){
            this.activity.onError(ex);
        }
        return file;
    }

    public void salvar(File file, FlagDirecao flagDirecao, String observacao, Location location) {
        try{
            Gps gps = new Gps(this.activity);
            location = gps.getLocation();
            boolean isOk = validar(file, flagDirecao, observacao, location);

            if (isOk) {
                RegistroImagem registroImagem = new RegistroImagem();
                registroImagem.setLatitude(location != null ? location.getLatitude() : null);
                registroImagem.setLongitude(location != null ? location.getLongitude() : null);
                registroImagem.setObservacao(observacao);
                byte[] imagem = FileUtils.convertFileToByteArray(file);
                registroImagem.setImagem(imagem);
                this.registroImagemService.insert(registroImagem);

                view.onRegitroImagemSucesso(activity.getString(R.string.msg_operacao_sucesso));
            }
        }catch (Exception ex){
            Log.e(TAG, ex.toString(), ex);
            this.activity.onError(ex);
        }
    }

    private boolean validar(File file, FlagDirecao flagDirecao, String observacao, Location location) {
        return true;
    }


}
