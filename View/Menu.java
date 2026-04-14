package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Tarefa;
import service.TarefaService;
import service.ResultadoOperacao;

public class Menu {
	
	//interface que o usuario vê no codigo
	// mostra o menu, recebe a entrada do usuario
	

	private TarefaService service; //menu chama as regras do sistema
	private Scanner scanner; // le o que o usuario digita no teclado
	
	public Menu (TarefaService service) {
		this.service = service;
		this.scanner = new Scanner (System.in);
	}
	
	public void mostrarMenu() {
	    System.out.println("\n===== LISTA DE AFAZERES =====");
	    System.out.println("1 - Criar tarefa");
	    System.out.println("2 - Listar tarefas");
	    System.out.println("3 - Concluir tarefa");
	    System.out.println("4 - Remover tarefa");
	    System.out.println("5 - Ordenar tarefas");
	    System.out.println("0 - Sair");
	    System.out.println("============================");
	    System.out.print("\n\nEscolha uma opção: \n");
	}
	
	public void iniciar () {
		int opcao = -1;
		
		while (opcao != 0) { //mantem o programa rodando enquanto for diferente de 0 a escolha do usuario
			mostrarMenu();
			opcao = scanner.nextInt();
			scanner.nextLine(); // limpar o buffer
		
		
		switch (opcao) { //decide o que sera feito a partir do comando do usuario 
			case 1:
				criarTarefa();
				break;
			case 2:
				listarTarefas();
				break;
			case 3:
				concluirTarefa();
				break;
			case 4:
				removerTarefa();
				break;
			case 5:
				ordenarTarefas();
				break;
			case 0:
				System.out.println("\nSaindo do sistema... \n");
				break;
			default:
				System.out.println("\nOpção inválida! \n");
			}
		
		}
	}
	
	private void criarTarefa () {
		
		System.out.println("\nNome da tarefa: \n");
		String titulo = scanner.nextLine();
		
		System.out.println("\nDescrição: \n");
		String descricao = scanner.nextLine();
		
		service.criarTarefa(titulo, descricao);
		System.out.println("\nTarefa criada com sucesso\n");
		
	}
	
	private void listarTarefas() {
		
		System.out.println("\n 1- Todas as tarefas");
		System.out.println("\n 2- Tarefas concluidas");
		System.out.println("\n 3- Tarefas pendentes");
		
		int opcao = scanner.nextInt();
		scanner.nextLine();
		 
		List <Tarefa> tarefas = new ArrayList<Tarefa>();
		
		switch (opcao) {
		case 1:
			tarefas = service.listarTarefas();
			break;
		case 2:
			tarefas = service.listarTarefasPeloStatus(Tarefa.StatusTarefa.Concluida);
			break;
		case 3:
			tarefas = service.listarTarefasPeloStatus(Tarefa.StatusTarefa.Pendente);
			break;
		}
		
		if(tarefas.isEmpty()) {
			System.out.println("\nNenhuma tarefa cadastrada ainda.\n");
			return;
			}
		
		for (Tarefa t : tarefas) {
			t.exibir();
		}
	}
	
	private void concluirTarefa() {
		
		System.out.println("-- TAREFAS DISPONIVEIS PARA CONCLUIR: --");
		List<Tarefa> tarefas = service.listarTarefasPeloStatus(Tarefa.StatusTarefa.Pendente);
		
		if (tarefas.isEmpty()) {
			System.out.println(("Nenhuma tarefa pendente pra ser concluida!"));
			return;
		}
		for (Tarefa t: tarefas) {
			t.exibir();
		}
		
		System.out.println("Id da tarefa para concluir: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		ResultadoOperacao resultado = service.concluirTarefa(id);
		
		switch (resultado) {
		case SUCESSO:
			System.out.println("\nTarefa concluida com sucesso!\n");
			break;
		
		case NAO_ENCONTRADA:
			System.out.println("\nTarefa não encontrada. \n");
			break;
		
		case JA_CONCLUIDA:
			System.out.println("\nTarefa já concluída.\n");
			break;
			
		default:
			System.out.println("\nNão foi possível concluir a tarefa.\n");

		}
	}
	
	private void removerTarefa() {
		System.out.println("-- TAREFAS DISPONÍVEIS PARA REMOVER: --");
		List <Tarefa> tarefas = service.listarTarefasPeloStatus (Tarefa.StatusTarefa.Pendente);
		
		if (tarefas.isEmpty()) {
			System.out.println("Nenhuma tarefa encontrada");
			return;
		}
		for (Tarefa t : tarefas) {
			t.exibir();
		}
		
		System.out.println("ID da tarefa que deseja remover: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		 
		ResultadoOperacao resultado = service.removerTarefa(id);
		
		if (resultado == ResultadoOperacao.SUCESSO) {
			System.out.println("\nTarefa Removida!\n");
			
		}else {
			System.out.println("\nNão foi possível remover a tarefa. Apenas tarefas PENDENTES pendentes podem ser removidas. \n");
		}
	}
	
	private void ordenarTarefas() {
		System.out.println("=== Ordenar Tarefas: ===");
		System.out.println("1 - Por id");
		System.out.println("2 - Por título");
		System.out.println("3 - Por Status");
		System.out.println("Qual tipo de ordenação deseja: ");
		
		int opcao = scanner.nextInt();
		scanner.nextLine();
		List <Tarefa> tarefas = new ArrayList<>();
		
		switch (opcao){
			
			case 1:
				tarefas = service.listarOrdenadasPorId();
				break;
			case 2:
				tarefas = service.listarOrdenadasPorTitulo();
				break;
			case 3: tarefas = service.listarOrdenadasPorStatus();
				break;
			default:
				System.out.println("Opção inválida");
				return;
		}
		if (tarefas.isEmpty()) {
			System.out.println("Nenhuma tarefa encontrada!");
			return;
		}
		for (Tarefa t : tarefas) {
			t.exibir();
		}
	}
}
