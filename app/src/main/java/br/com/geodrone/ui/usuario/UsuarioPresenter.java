package br.com.geodrone.ui.usuario;

import br.com.geodrone.Session;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.utils.PreferencesUtils;


public class UsuarioPresenter extends BasePresenter<UsuarioPresenter.View> {

    interface View {

        void onClickLogin(String message);

        void onCadastroSucesso(String message);

        void onErrorNome(String message);

        void onErrorTelefone(String message);

        void onErrorEmail(String message);

        void onErrorSenha(String message);

        void onErrorConfirmSenha(String message);
    }

    private BaseActivity activity;

    UsuarioService usuarioService = null;

    public UsuarioPresenter(BaseActivity activity) {
        this.activity = activity;
        this.usuarioService = new UsuarioService(activity);
    }

    public Usuario findByEmail(String email, String senha){
        this.usuarioService.findAll();
        return this.usuarioService.findByEmail(email);
    }

    public boolean validarSalvar(String nome, String sobrenome, String telefone, String email, String senha, String confirmSenha) {
        boolean isOk = true;
        if (hasView()) {
            if (nome == null){
                view.onNomeObrigatorio();
            }
            if (telefone == null){
                view.onTelefoneObrigatorio();
            }
            if (email == null){
                view.onEmailObrigatorio();
            }
            if (email == null){
                view.onEmailObrigatorio();
            }
            if (senha == null){
                view.onSenhaObrigatorio();
            }
            if (confirmSenha == null){
                view.onConfirmSenhaObrigatorio();
            }
        }
        return isOk;
    }
    public Usuario salvar(String nome, String sobrenome, String telefone, String email, String senha, String confirmSenha) {

        try {

            boolean isOk = validarSalvar(nome, sobrenome, telefone, email, senha, confirmSenha);
            if (!isOk) {
            }
            Cliente cliente = Session.getAttribute(PreferencesUtils.CHAVE_CLIENTE);
            Long idCliente = cliente != null ? cliente.getIdClienteRef() : null;
            Usuario usuario = new Usuario(null, nome, sobrenome, email, telefone, senha, idCliente);
            usuario = usuarioService.insert(usuario);

            view.onCadastroSucesso();
            return usuario;
        }catch (Exception ex){
            view.onCadastroErro();
        }

        return null;
    }

}

