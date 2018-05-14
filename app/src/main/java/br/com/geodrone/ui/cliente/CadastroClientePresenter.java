package br.com.geodrone.ui.cliente;

import android.util.Log;

import br.com.geodrone.R;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.NumberUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernandes on 04/05/2018.
 */

public class CadastroClientePresenter  extends BasePresenter<CadastroClientePresenter.View> {

    private static String TAG = CadastroClientePresenter.class.getName();

    interface View {

        void onClickLogin();

        void onCadastroSucesso(String message);

        void onCadastroError(String message);

        void onCadastroError(Messenger messenger);

        void onErrorNome(String message);

        void onErrorTelefone(String message);

        void onErrorEmail(String message);

    }

    private BaseActivity activity;

    ClienteService clienteService = null;
    ConfiguracaoService configuracaoService = null;

    public CadastroClientePresenter(BaseActivity activity) {
        this.activity = activity;
        this.clienteService = new ClienteService(activity);
        this.configuracaoService = new ConfiguracaoService(activity);
    }


    public boolean validarSalvar(String nome,
                                 String cpf,
                                 String email,
                                 String segmento,
                                 String telefone,
                                 String celular,
                                 String logradouro,
                                 String bairro,
                                 String localidade,
                                 String numero,
                                 String complemento,
                                 String cep) {
        boolean isOk = true;
        if (hasView()) {
            if (nome == null){
                view.onErrorNome(activity.getString(R.string.msg_obr_nome));
                isOk = false;
            }

            if (telefone == null){
                view.onErrorTelefone(activity.getString(R.string.msg_obr_telefone));
                isOk = false;
            }

            if (email == null){
                view.onErrorEmail(activity.getString(R.string.msg_obr_email));
                isOk = false;
            }

        }
        return isOk;
    }

    private ClienteResource criarCliente(String nome,
                       String cpf,
                       String email,
                       String segmento,
                       String telefone,
                       String celular,
                       String logradouro,
                       String bairro,
                       String localidade,
                       String numero,
                       String complemento,
                       String cep) {
        NumberUtils numberUtils = new NumberUtils();
        ClienteResource cliente = new ClienteResource();

        cliente.setNomeRazaoSocial(nome);
        cliente.setCpf(numberUtils.parseLongOnlyNumber(cpf));
        cliente.setIndPessoaFisica(1);
        cliente.setCnpj(null);
        cliente.setEmail(email);
        cliente.setSegmento(segmento);
        cliente.setTelefone(telefone);
        cliente.setCelular(celular);
        cliente.setLogradouro(logradouro);
        cliente.setBairro(bairro);
        cliente.setCidade(localidade);
        cliente.setNumero(numero);
        cliente.setComplemento(complemento);
        cliente.setCep(numberUtils.parseLongOnlyNumber(cep));

        return cliente;

    }

    public void salvar(final String url,
                       String nome,
                       String cpf,
                       String email,
                       String segmento,
                       String telefone,
                       String celular,
                       String logradouro,
                       String bairro,
                       String localidade,
                       String numero,
                       String complemento,
                       String cep){

        try{
            boolean isOk = validarSalvar(nome,
                    cpf,
                    email,
                    segmento,
                    telefone, celular,
                    logradouro, bairro, localidade,
                    numero, complemento, cep);

            if (isOk) {
                ClienteResource clienteResource = criarCliente(nome, cpf, email, segmento, telefone, celular, logradouro, bairro, localidade, numero, complemento, cep);
                Configuracao configuracao = configuracaoService.getOneConfiguracao();
                String URL_BASE = configuracao != null ? configuracao.getUrl() : url;
                if (URL_BASE == null) {
                    URL_BASE = Constantes.API_LOGIN_URL;
                }
                APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithoutAuth(APIClient.class);
                Call<ClienteResource> call = client.cadastrarCliente(clienteResource);
                final String finalURL_BASE = URL_BASE;
                call.enqueue(new Callback<ClienteResource>() {
                    @Override
                    public void onResponse(Call<ClienteResource> call, Response<ClienteResource> response) {
                        int statusCode = response.code();
                        if (statusCode == 200) {
                            ClienteResource sincronizacaoResource = response.body();
                            view.onCadastroSucesso(activity.getString(R.string.msg_operacao_sucesso));
                        } else {
                            Messenger messenger = ErrorUtils.parseError(response, finalURL_BASE);
                            view.onCadastroError(messenger);
                        }
                    }

                    @Override
                    public void onFailure(Call<ClienteResource> call, Throwable t) {
                        view.onCadastroError(t.getMessage());
                        Log.e(TAG, t.toString(), t);
                    }
                });
            }
        }catch (Exception ex){
            view.onCadastroError(ex.getMessage());
            Log.e(TAG, ex.toString(), ex);
        }
    }

}