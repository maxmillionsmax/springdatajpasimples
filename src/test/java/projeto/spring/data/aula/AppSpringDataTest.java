package projeto.spring.data.aula;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
	    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class AppSpringDataTest {

	@Test
	public void testeInsert() throws Exception{
		System.out.println("Iniciou spring com sucesso!");
	}
}
