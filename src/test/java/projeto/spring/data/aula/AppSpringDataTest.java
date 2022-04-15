package projeto.spring.data.aula;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import projeto.spring.data.aula.dao.InterfaceSpringDataUSer;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
	    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class AppSpringDataTest {

	@SuppressWarnings("unused")
	@Autowired
	private InterfaceSpringDataUSer interfaceSpringDataUSer;
	
	@Test
	public void testeInsert(){
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("coiil@email.com");
		usuarioSpringData.setIdade(999);
		usuarioSpringData.setLogin("Login 333");
		usuarioSpringData.setSenha("senha 333");
		usuarioSpringData.setNome("Nome 333");
		
		interfaceSpringDataUSer.save(usuarioSpringData);
		
		System.out.println("Usuarios cadastrados-> "+interfaceSpringDataUSer.count());
	}
	
	@Test
	public void testeConsulta(){
		
		@SuppressWarnings("unused")
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUSer.findById(1L);
		
		System.out.println(usuarioSpringData.get().getId());
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		
	}
}
