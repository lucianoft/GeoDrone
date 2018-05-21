package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.ClienteUsuario;
import br.com.geodrone.repository.ClienteUsuarioRepository;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 19/05/2018.
 */
public class ClienteUsuarioService extends CrudService<ClienteUsuario, Long> {
    ClienteUsuarioRepository clienteUsuarioRepository = null;

    public ClienteUsuarioService(Context ctx){
        clienteUsuarioRepository = new ClienteUsuarioRepository(ctx);
    }

    public CrudRepository<ClienteUsuario, Long> getRepository(){
        return clienteUsuarioRepository;
    }
}
