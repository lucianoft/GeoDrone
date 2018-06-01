package br.com.geodrone.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.exception.BusinessException;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.ClienteUsuario;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.repository.ClienteUsuarioRepository;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.ClienteRepository;
import br.com.geodrone.service.util.CrudService;
import br.com.geodrone.service.util.OperacaoCrud;

/**
 * Created by fernandes on 25/03/2018.
 */
public class ClienteService extends CrudService<Cliente, Long> {
    ClienteRepository clienteRepository = null;
    ClienteUsuarioRepository clienteUsuarioRepository = null;

    public ClienteService(Context ctx){
        clienteRepository = new ClienteRepository(ctx);
        clienteUsuarioRepository = new ClienteUsuarioRepository(ctx);
    }

    public CrudRepository<Cliente, Long> getRepository(){
        return clienteRepository;
    }

    @Override
    protected void validateModel(Cliente object, OperacaoCrud operacaoCrud) throws BusinessException {
        super.validateModel(object, operacaoCrud);
    }

    public List<Cliente> findAllByUsuario(){
        Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
        List<ClienteUsuario> clienteUsuarios = clienteUsuarioRepository.findAllByUsuario(usuario.getId());
        List<Long> idClientes = getIdCliente(clienteUsuarios);
        return clienteRepository.findAllByIds(idClientes);
    }

    private List<Long> getIdCliente(List<ClienteUsuario> clienteUsuarios){
        List<Long> idClientes = new ArrayList<>();
        for (ClienteUsuario clienteUsuario : clienteUsuarios) {
            idClientes.add(clienteUsuario.getIdCliente());
        }
        return idClientes;
    }

}
