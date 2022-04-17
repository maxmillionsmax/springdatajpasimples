package projeto.spring.data.aula;

import java.util.Iterator;
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
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class AppSpringDataTest {

	@SuppressWarnings("unused")
	@Autowired
	private InterfaceSpringDataUSer interfaceSpringDataUSer;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;

	@Test
	public void testeInsert() {
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("10011.com");
		usuarioSpringData.setIdade(10011);
		usuarioSpringData.setLogin("011");
		usuarioSpringData.setSenha("101");
		usuarioSpringData.setNome("11001");

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
		System.out.println("------------------------------------------");
		
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getId());
			System.out.println(telefone.getUsuarioSpringData().getNome());
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
			System.out.println("------------------------------------------");
			
		}
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
	
	@Test
	public void testeupdatePorNome(){
		interfaceSpringDataUSer.updateEmailPorNome("testeupdate@email.com", "Nome");
	}
	
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUSer.findById(2L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("trabalho");
		telefone.setNumero("2452345345234");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
	}
}
