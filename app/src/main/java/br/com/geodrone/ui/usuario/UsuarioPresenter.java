package br.com.geodrone.ui.usuario;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.constantes.FlagPerfilUsuario;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.utils.PreferencesUtils;


public class UsuarioPresenter extends BasePresenter<UsuarioPresenter.View> {

    interface View {

        void onClickLogin();

        void onCadastroSucesso(String message);

        void onErrorNome(String message);

        void onErrorSobrenome(String message);

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
                view.onErrorNome(activity.getString(R.string.msg_obr_nome));
                isOk = false;
            }
            if (sobrenome == null){
                view.onErrorSobrenome(activity.getString(R.string.msg_obr_sobrenome));
                isOk = false;
            }
            if (telefone == null){
                view.onErrorTelefone(activity.getString(R.string.msg_obr_telefone));
                isOk = false;
            }
            if (senha == null){
                view.onErrorSenha(activity.getString(R.string.msg_obr_senha));
                isOk = false;
            }
            if (confirmSenha == null){
                view.onErrorConfirmSenha(activity.getString(R.string.msg_obr_confirmar_senha));
                isOk = false;
            }

            if (senha != null && confirmSenha != null){
                if (!senha.equals(confirmSenha)){
                    view.onErrorConfirmSenha(activity.getString(R.string.msg_inv_senha_dif_confirmar_senha));
                    isOk = false;

                }
            }
        }
        return isOk;
    }
    public Usuario salvar(String nome, String sobrenome, String telefone, String email, String senha, String confirmSenha) {

        try {

            boolean isOk = validarSalvar(nome, sobrenome, telefone, email, senha, confirmSenha);
            if (isOk) {
                Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
                Long idCliente = cliente != null ? cliente.getId() : null;
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setSobrenome(sobrenome);
                usuario.setEmail(email);
                usuario.setEmail(email);
                usuario.setTelefone(telefone);
                usuario.setSenha(senha);
                usuario.setFlagPerfil(FlagPerfilUsuario.ADM.value());
                usuario = usuarioService.insert(usuario);

                view.onCadastroSucesso(activity.getString(R.string.msg_operacao_sucesso));
                return usuario;
            }
        }catch (Exception ex){
            activity.onError(ex);
        }

        return null;
    }

}

