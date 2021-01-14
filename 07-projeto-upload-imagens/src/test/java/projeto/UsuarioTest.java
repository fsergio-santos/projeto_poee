package projeto;

import java.util.List;

import org.junit.Test;

import com.projeto.model.models.Usuario;
import com.projeto.model.service.UsuarioService;

public class UsuarioTest {
	
	//@Test(expected = Exception.class)
	public void salvarUsuarioBancoDadosTeste() {
      
		Usuario usuario = new Usuario();
		
		//usuario.setId(2);
		usuario.setUsername("Maria Fernanda da Silva");
		usuario.setPassword("123456");
		usuario.setEmail("maria@maria.br");
		usuario.setAtivo(false);
		usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.save(usuario);
		
		System.out.println("Gravando usuário no banco de dados 1");
				
		usuario = new Usuario();
		
		//usuario.setId(2);
		usuario.setUsername("Clara Vieira");
		usuario.setPassword("123456");
		usuario.setEmail("clara@clara.br");
		usuario.setAtivo(false);
		usuario.setAdmin(false);
		
		UsuarioService usuarioService1 = new UsuarioService();
	
		usuarioService1.save(usuario);
		//assertTrue(true);
		
	}

	
	//@Test(expected = Exception.class)
	public void alterarUsuarioBancoDadosTeste() {
      
		Usuario usuario = new Usuario();
				
		usuario.setId(2);
		
		//usuario.setUsername("Roberto Carlos da Silva");
		//usuario.setPassword("123456");
		//usuario.setEmail("roberto@carlos.com.br");
		//usuario.setAtivo(false);
		//usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuario = usuarioService.findById(usuario.getId());
		
		System.out.println(usuario.toString());
		
		usuario.setEmail("joao@joao.com.br");
		
		usuarioService.update(usuario);
		
		System.out.println("Ateração usuário no banco de dados");
		
		//assertTrue(true);
		
		
	}
	
	@Test(expected = Exception.class)
	public void listarTodosUsuarioTabelaUsuario() {
		
		UsuarioService usuarioService = new UsuarioService();
		
		List<Usuario> listaUsuario = usuarioService.findAll();
		
		for (Usuario usuario : listaUsuario) {
			System.out.println(usuario.toString());
		}
		
		
		
	}
	
	//@Test
	public void excluirUsuarioDaTabela() {
		
		Usuario usuario = new Usuario();
		
		usuario.setId(6);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.delete(usuario);
		
	}
	
	
	
	
	
	
	
	
	
	
	

	

}
