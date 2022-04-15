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
		usuarioSpringData.setEmail("qqqcoiil@email.com");
		usuarioSpringData.setIdade(99);
		usuarioSpringData.setLogin("Loginqq 3");
		usuarioSpringData.setSenha("senhaqq 3");
		usuarioSpringData.setNome("Nomeqq 3");
		
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
	
	@Test
	public void testeConsultaTodos(){
		
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUSer.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());
			
			System.out.println("-------------------------------------------------");
		}
		
	}
}
