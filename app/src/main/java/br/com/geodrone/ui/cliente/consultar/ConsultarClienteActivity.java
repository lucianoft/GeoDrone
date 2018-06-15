package br.com.geodrone.ui.cliente.consultar;

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

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.constantes.FlagStatusCliente;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.MicroRegiaoResource;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.mensagem.usuarios.UsuariosMensagemAdapter;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.NumberUtils;
import br.com.geodrone.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultarClienteActivity extends BaseActivity implements ConsultarClientePresenter.View {

    private GenericProgress mProgress;
    private ConsultarClientePresenter consultarClientePresenter;
    private ConsultarClienteAdapter consultarClienteAdapter;

    @BindView(R.id.recyclerViewCliente)
    RecyclerView recyclerViewClientes;
    private List<MicroRegiaoResource> microRegiaoResources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);
        ButterKnife.bind(this);

        if (!Util.verificaConexao(this)) {
            Util.initToast(this, getString(R.string.msg_sem_conexao_internet));
            finish();
        } else {

            mProgress = new GenericProgress(this);
            consultarClientePresenter = new ConsultarClientePresenter(this);
            showLoading();

            recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));

            consultarClienteAdapter = new ConsultarClienteAdapter();
            consultarClienteAdapter.setActivity(this);
            recyclerViewClientes.setAdapter(consultarClienteAdapter);
            // Configurando um dividr entre linhas, para uma melhor visualização.
            recyclerViewClientes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        consultarClientePresenter.takeView(this);
        consultarClientePresenter.findAllCliente();
        consultarClientePresenter.findAllMicroRegiao();
    }

    @Override
    public void onClickCliente(final ClienteResource clienteResource) {
        LayoutInflater li = this.getLayoutInflater();
        View view = li.inflate(R.layout.dialog_alterar_cliente, null);
        final EditText editTextNome = view.findViewById(R.id.editText_nome);
        final EditText editTextEmail = view.findViewById(R.id.editText_email);

        final Spinner spMicroregiao = view.findViewById(R.id.spinner_microregiao_cliente);
        ArrayAdapter<MicroRegiaoResource> adapterMicroregiao = new ArrayAdapter<MicroRegiaoResource>(this, android.R.layout.simple_spinner_item, microRegiaoResources);
        spMicroregiao.setAdapter(adapterMicroregiao);
        adapterMicroregiao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spStatus = view.findViewById(R.id.spinner_status_cliente);
        ArrayAdapter<FlagStatusCliente> adapterStatus = new ArrayAdapter<FlagStatusCliente>(this, android.R.layout.simple_spinner_item, FlagStatusCliente.values());
        spStatus.setAdapter(adapterStatus);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editTextNome.setText(clienteResource.getNomeRazaoSocial());
        editTextEmail.setText(clienteResource.getEmail());
        int positionMicroRegiao = getPositionMicroRegiao(clienteResource.getIdMicroRegiao());
        if (positionMicroRegiao > -1) {
            spMicroregiao.setSelection(positionMicroRegiao);
        }
        spStatus.setSelection(getIndexByString(spStatus, clienteResource.getFlagStatus()));

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(this.getString(R.string.lb_alterar_cliente));
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final GenericProgress genericProgress = new GenericProgress(this);
        alert.setPositiveButton(R.string.lb_confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLoading();
                MicroRegiaoResource microRegiaoResource = (MicroRegiaoResource) spMicroregiao.getSelectedItem();
                FlagStatusCliente flagStatusCliente = (FlagStatusCliente) spStatus.getSelectedItem();
                consultarClientePresenter.alterarCliente(clienteResource, microRegiaoResource, flagStatusCliente != null ? flagStatusCliente.getValue() : null );
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }



    @Override
    public void onAlterarStatusSucesso(String message) {
        showMessage(message);
        hideLoading();
    }

    @Override
    public void onError(Messenger messenger) {
        showMessenger(messenger);
        hideLoading();
    }

    @Override
    public void onListCliente(List<ClienteResource> clienteResources) {
        consultarClienteAdapter.clearData();

        int i = 0;
        if (clienteResources != null) {
            for (ClienteResource clienteResource : clienteResources) {
                ++i;
                consultarClienteAdapter.addData(clienteResource);
            }
        }
        consultarClienteAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public void onListMicroRegiao(List<MicroRegiaoResource> microRegiaoResources) {
        this.microRegiaoResources = microRegiaoResources;
    }


    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }

    private int getPositionMicroRegiao(Long idMicroRegiao){
        if (microRegiaoResources != null && idMicroRegiao != null){
            NumberUtils numberUtils = new NumberUtils();
            for (int i = 0; i < microRegiaoResources.size(); i++) {   //listaPedidos é um ArrayList comum
                if (numberUtils.isEqualsLong(idMicroRegiao, microRegiaoResources.get(i).getId())){
                    return i;
                }
            }
        }
        return -1;
    }

    private int getIndexByString(Spinner spinner, String string) {
        int index = 0;

        FlagStatusCliente flagStatusCliente = FlagStatusCliente.getInstance(string);
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(flagStatusCliente)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
