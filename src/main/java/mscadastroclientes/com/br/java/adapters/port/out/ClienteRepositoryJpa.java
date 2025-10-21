package mscadastroclientes.com.br.java.adapters.port.out;

import mscadastroclientes.com.br.java.adapters.database.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, Long> {

}
