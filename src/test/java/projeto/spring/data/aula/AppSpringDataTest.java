package projeto.spring.data.aula;

import java.util.List;
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
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class AppSpringDataTest {

	@SuppressWarnings("unused")
	@Autowired
	private InterfaceSpringDataUSer interfaceSpringDataUSer;

	@Test
	public void testeInsert() {
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("333.com");
		usuarioSpringData.setIdade(9933);
		usuarioSpringData.setLogin("33");
		usuarioSpringData.setSenha("33");
		usuarioSpringData.setNome("33");

		interfaceSpringDataUSer.save(usuarioSpringData);

		System.out.println("Usuarios cadastrados-> " + interfaceSpringDataUSer.count());
	}

	@Test
	public void testeConsulta() {

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
	public void testeConsultaTodos() {

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

	@Test
	public void testeUpdate() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUSer.findById(3L);
		UsuarioSpringData data = usuarioSpringData.get();
		data.setNome("Nome atualizado");
		data.setEmail("email atualizado");

		interfaceSpringDataUSer.save(data);
	}

	@Test
	public void testeDelete() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUSer.findById(5L);
		interfaceSpringDataUSer.delete(usuarioSpringData.get());
	}

	@Test
	public void testeConsultaNome() {
		
		List<UsuarioSpringData> list = interfaceSpringDataUSer.buscaPorNome("3");

		for (UsuarioSpringData usuarioSpringData : list) {

			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());

			System.out.println("-------------------------------------------------");
		}
	}
	
	@Test
	public void testeConsultaNomeParam() throws Exception{
		
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUSer.buscaPorNomeParam("Nome");
		
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getSenha());

			System.out.println("-------------------------------------------------");
		}
	
	@Test
	public void testedeletaPorNome(){
		interfaceSpringDataUSer.deletePorNome("33");
	}
}
