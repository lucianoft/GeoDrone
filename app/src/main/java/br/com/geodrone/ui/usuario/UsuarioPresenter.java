package br.com.geodrone.ui.usuario;

import android.app.Activity;

import br.com.geodrone.Session;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.presenter.BasePresenter;
import br.com.geodrone.presenter.ProgressBarPresenter;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.utils.PreferencesUtils;


public class UsuarioPresenter extends BasePresenter<UsuarioPresenter.View> {

    interface View extends ProgressBarPresenter {

        void onClickCadastro();

        void onCadastroErro();

        void onCadastroSucesso();

        void onNomeObrigatorio();

        void onTelefoneObrigatorio();

        void onEmailObrigatorio();

        void onSenhaObrigatorio();

        void onConfirmSenhaObrigatorio();
    }

    private Activity activity;

    UsuarioService usuarioService = null;

    public UsuarioPresenter(Activity activity) {
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

