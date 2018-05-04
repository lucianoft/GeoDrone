package br.com.geodrone.service.util;

import android.location.Location;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.exception.BusinessException;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.ClientModel;
import br.com.geodrone.model.api.DateRegistryModel;
import br.com.geodrone.model.api.DeviceModel;
import br.com.geodrone.model.api.LocationModel;
import br.com.geodrone.model.api.UserModel;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.GeoodroneVersion;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.PreferencesUtils;


/**
 * Classe que define os métodos crud de um service.
 * 
 * <p> Utilize-a para garantir a padronização da implementação dos métodos crud do service.
 *
 * @param <T> Model/Entity deste service.
 * @param <ID> Identificador do Model/Entity.
 */
public abstract class CrudService<T, ID extends Serializable> extends GenericService {

	public abstract CrudRepository<T, ID> getRepository();

	public final T insert(T entity) {
		config(entity, OperacaoCrud.INSERT);
		return getRepository().insert(entity);
	}

	public final T update(T entity) {
		config(entity, OperacaoCrud.UPDATE);
		return getRepository().update(entity);
	}

	private void config(T entity, OperacaoCrud operacaoCrud) throws BusinessException {
		Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
		Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
		Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);
		Location location = SessionGeooDrone.getAttribute(PreferencesUtils.CHAVE_LOCALIZACAO_ATUAL);

		configAuditModel(entity, operacaoCrud);
		configUserModel(entity, usuario);
		configClienteModel(entity, cliente);
		configDeviceModelModel(entity, location);
		configDeviceModelModel(entity, dispositivo);
        configDateRegistryModel(entity);
		preValidateModel(entity, operacaoCrud);
		validateModel(entity, operacaoCrud);
		posValidateModel(entity, operacaoCrud);
	}

	public void delete(T entity) {
		if (entity != null) {
			getRepository().delete(entity);
		}
	}

	public void delete(ID id) {
		T entity = null;
		if (id != null) {
			entity = getRepository().findById(id);
		}
		delete(entity);
	}

	public T findById(ID id) {
		T domain = findById(id, false);
		return domain;
	}

	public T findById(ID id, boolean isThrowException) {
		T domain = null;
		if (id != null) {
			domain = getRepository().findById(id);
		}
		if (domain == null && isThrowException) {
			throw new RuntimeException("Not Found");
		}
		return domain;
	}

	public List<T> findAll() {
		return getRepository().findAll();
	}

	protected void validateModel(T object, OperacaoCrud operacaoCrud) throws BusinessException {
		Messenger messenger = new Messenger();

		if (messenger.isError()) {
			throw new BusinessException(messenger);
		}
	}

	protected void configAuditModel(T entity, OperacaoCrud operacaoCrud) {
		if (entity instanceof AuditModel) {
			AuditModel auditModel = (AuditModel) entity;
			Date now = new DateUtils().now();

			if (OperacaoCrud.INSERT.equals(operacaoCrud)) {
				auditModel.setDtInclusao(now);
			}
			auditModel.setDtAlteracao(now);
			auditModel.setVersaoSistema(GeoodroneVersion.build);
		}
	}

	protected void configUserModel(T entity, Usuario usuario) {
		if (entity instanceof UserModel) {
			UserModel userModel = (UserModel) entity;
			userModel.setIdUsuarioReg(usuario.getId());
		}
	}

	protected void configClienteModel(T entity, Cliente cliente) {
		if (entity instanceof ClientModel) {
			ClientModel empresaModel = (ClientModel) entity;
			if (empresaModel.getIdCliente() == null) {
				empresaModel.setIdCliente(cliente.getId());
			}
		}
	}

	private void configDeviceModelModel(T entity, @NonNull Location location) {
		if (entity instanceof LocationModel && location != null) {
			LocationModel locationModel = (LocationModel) entity;
            if (locationModel.getLatitude() == null || locationModel.getLongitude() == null) {
                locationModel.setLatitude(location.getLatitude());
                locationModel.setLongitude(location.getLongitude());
            }
		}
	}

	private void configDeviceModelModel(T entity, @NonNull Dispositivo dispositivo) {
		if (entity instanceof DeviceModel) {
			DeviceModel deviceModel = (DeviceModel) entity;
			deviceModel.setIdDispositivo(dispositivo != null ? dispositivo.getId() : null);
		}
	}

    private void configDateRegistryModel(T entity) {
        if (entity instanceof DateRegistryModel) {
            DateRegistryModel dateRegistryModel = (DateRegistryModel) entity;
            if (dateRegistryModel != null && dateRegistryModel.getDtRegistro() == null){
                dateRegistryModel.setDtRegistro(new DateUtils().now());
            }
        }
    }

	protected void preValidateModel(T entity, OperacaoCrud operacaoCrud) {
		// Sobrescrever este método para implementar regras adicionais.
	}

	protected void posValidateModel(T entity, OperacaoCrud operacaoCrud) {
		// Sobrescrever este método para implementar regras adicionais.
	}

}