package main;

import repository.TarefaRepository;
import service.TarefaService;
import view.Menu;

public class Main {
	
	public static void main (String[] args) {
		
		TarefaRepository repository = new TarefaRepository(); // cria o lugar onde as tarefas vao ficar guardadas
		TarefaService service = new TarefaService (repository); // entrega o repositorio para o service (normas)
		Menu menu = new Menu (service); //  entrega o service para o menu (o que sera visto pelo usuario)
		
		menu.iniciar(); //começa o programa
	}

	// arquitetura de camadas do codigo
	// menu -> tarefaService -> tarefaRepository -> Tarefa
	
	
	/*Sistema: Lista de afazeres
	 * Autor: Thiago de Figueiredo
	 * Data: 2026
	 * Descrição do projeto: Projeto inicial
	 * com foco na organização de tarefas diárias e
	 * melhor produtividade.
	 * Proposta de evolução: tornar o projeto um app que 
	 * além de organizar as tarefas organize a vida financeira
	 * e de dicas de investimentos e gastos do usuário. Tornando
	 * o programa completo pra organização e melhorando a qualidade
	 * de vida de quem usar.  
	 */
}