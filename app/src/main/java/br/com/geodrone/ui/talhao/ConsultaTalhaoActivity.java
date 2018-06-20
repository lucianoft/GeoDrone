package br.com.geodrone.ui.talhao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.Talhao;
import br.com.geodrone.model.constantes.FlagDirecao;
import br.com.geodrone.model.constantes.FlagSimNao;
import br.com.geodrone.resource.AlterarSenhaUsuarioResourse;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultaTalhaoActivity extends BaseActivity implements ConsultaTalhaoPresenter.View{

    private ConsultaTalhaoPresenter consultaTalhaoPresenter;
    private TalhaoAdapter talhaoAdapter;
    private GenericProgress mProgress;

    @BindView(R.id.recyclerViewTalhao)
    RecyclerView recyclerViewTalhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_talhao);
        ButterKnife.bind(this);

        if (!Util.verificaConexao(this)) {
            Util.initToast(this, getString(R.string.msg_sem_conexao_internet));
            finish();
        } else {

            consultaTalhaoPresenter = new ConsultaTalhaoPresenter(this);
            mProgress = new GenericProgress(this);
            recyclerViewTalhao.setLayoutManager(new LinearLayoutManager(this));

            talhaoAdapter = new TalhaoAdapter();
            talhaoAdapter.setActivity(this);
            recyclerViewTalhao.setAdapter(talhaoAdapter);
            // Configurando um dividr entre linhas, para uma melhor visualização.
            recyclerViewTalhao.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        }
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        consultaTalhaoPresenter.takeView(this);
        consultaTalhaoPresenter.findTalhao();
    }

    @Override
    public void onSucessoFindTalhao(List<Talhao> mensagemResources) {
        talhaoAdapter.clearData();
        int i = 0;
        if (mensagemResources != null) {
            for (Talhao mensagemResource : mensagemResources) {
                ++i;
                talhaoAdapter.addData(mensagemResource);
            }
        }

        talhaoAdapter.notifyDataSetChanged();
        recyclerViewTalhao.scrollToPosition(i);
    }

    @Override
    public void onErroFindTalhao(String msg) {
        hideLoading();
        showMessage(msg);
    }

    @Override
    public void onSucessoSalvar(String msg) {
        hideLoading();
        showMessage(msg);
    }

    @Override
    public void onErroSalvar(String msg) {
        hideLoading();
        showMessage(msg);
    }

    @Override
    public void onErroSalvar(Messenger messenger) {
        hideLoading();
        showMessenger(messenger);
    }

    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }

    public void onClickTalhao(Talhao talhao) {
        cadastrarAlterarTalhao(this, talhao);
    }

    @OnClick(R.id.float_button_incluir_talhao)
    public void incluirTalhao(){
        cadastrarAlterarTalhao(this, null);
    }

    public void cadastrarAlterarTalhao(final BaseActivity activity, final Talhao talhao) {
        LayoutInflater li = activity.getLayoutInflater();
        View view = li.inflate(R.layout.dialog_cadastrar_talhao, null);
        final EditText ediTextCodigo = view.findViewById(R.id.editText_codigo);
        final EditText ediTextDescricao = view.findViewById(R.id.editText_descricao);
        final Spinner spinnerAtivo = view.findViewById(R.id.spinner_ativo);
        ArrayAdapter<FlagSimNao> spinnerAtivoAdapter = new ArrayAdapter<FlagSimNao>(this, android.R.layout.simple_spinner_item, FlagSimNao.values());
        spinnerAtivo.setAdapter(spinnerAtivoAdapter);
        spinnerAtivoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        if (talhao == null || talhao.getId() == null) {
            alert.setTitle(activity.getString(R.string.tile_dialog_cadastrar_talhao));
        }else{
            alert.setTitle(activity.getString(R.string.tile_dialog_alterar_talhao));
            ediTextCodigo.setText(talhao.getCodigo());
            ediTextDescricao.setText(talhao.getDescricao());
            //spinnerAtivo.
        }
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final GenericProgress genericProgress = new GenericProgress(activity);;
        alert.setPositiveButton(R.string.lb_confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLoading();
                FlagSimNao flagSimNao = (FlagSimNao) spinnerAtivo.getSelectedItem();
                Integer indAtivo = flagSimNao != null ? flagSimNao.getValue() : null;
                salvarTalhao(talhao, ediTextCodigo.getText().toString(), ediTextDescricao.getText().toString(),indAtivo);

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

    private void salvarTalhao(Talhao talhao, String codigo, String descricao, Integer indAtivo) {
        try{
            consultaTalhaoPresenter.salvar(talhao, codigo, descricao, indAtivo);
        }catch (Exception ex){
            onError(ex);
        }
    }
}
