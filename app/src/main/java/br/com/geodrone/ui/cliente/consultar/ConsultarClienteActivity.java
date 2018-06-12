package br.com.geodrone.ui.cliente.consultar;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.mensagem.usuarios.UsuariosMensagemAdapter;
import br.com.geodrone.utils.Messenger;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultarClienteActivity extends BaseActivity implements ConsultarClientePresenter.View {

    private GenericProgress mProgress;
    private ConsultarClientePresenter consultarClientePresenter;
    private ConsultarClienteAdapter consultarClienteAdapter;

    @BindView(R.id.recyclerViewCliente)
    RecyclerView recyclerViewClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);
        ButterKnife.bind(this);

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

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        consultarClientePresenter.takeView(this);
        consultarClientePresenter.findAllCliente();
    }

    @Override
    public void onClickCliente(ClienteResource clienteResource) {

    }

    @Override
    public void onClickAlterarStatusCliente(ClienteResource clienteResource) {

    }

    @Override
    public void onAlterarStatusSucesso(String message) {

    }

    @Override
    public void onError(Messenger messenger) {

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
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }
}
