package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.Cliente;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.ClienteRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 25/03/2018.
 */
public class ClienteService extends CrudService<Cliente, Long> {
    ClienteRepository clienteRepository = null;

    public ClienteService(Context ctx){
        clienteRepository = new ClienteRepository(ctx);
    }

    public CrudRepository<Cliente, Long> getRepository(){
        return clienteRepository;
    }
}
