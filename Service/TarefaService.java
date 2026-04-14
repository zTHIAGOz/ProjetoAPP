package service;

import java.util.ArrayList;
import java.util.List;


import model.Tarefa;
import repository.TarefaRepository;

public class TarefaService {
	
	// responsavel pelas regras de negocio do sistema 
	// define como as tarefas devem se comportar
	// intermediaria entre o a interface e o repositorio

	private TarefaRepository repository;
	
	public TarefaService(TarefaRepository repository) {
		this.repository = repository;
	}
	
	public void criarTarefa (String titulo, String descricao) {
		
		if (titulo == null || titulo.isBlank()) {
			System.out.println("Titulo nao pode ser vazio!");
			return;
		}
		
		Tarefa tarefa = new Tarefa (titulo, descricao);        
		repository.adicionar(tarefa);
	}
	
	public List<Tarefa> listarTarefas(){
		return repository.listar();
	}
	
	public List <Tarefa> listarTarefasPeloStatus(Tarefa.StatusTarefa status){
		List <Tarefa> filtradas = new ArrayList<Tarefa>();
			
		for (Tarefa t: repository.listar()) {
				if(t.getStatus() == status) {
					filtradas.add(t);
				}
			}
		return filtradas;
	}
	
	public List<Tarefa> listarOrdenadasPorId(){
		List<Tarefa> lista = new ArrayList<>(repository.listar());
		lista.sort((t1,t2) -> Integer.compare(t1.getId(), t2.getId()));
		return lista;
	}
	
	public List<Tarefa> listarOrdenadasPorTitulo(){
		List<Tarefa> lista = new ArrayList<>(repository.listar());
		lista.sort((t1,t2) -> t1.getTitulo().compareToIgnoreCase(t2.getTitulo()));
		return lista;
	}
	
	public List<Tarefa> listarOrdenadasPorStatus(){
		List<Tarefa> lista = new ArrayList<>(repository.listar());
		lista.sort((t1,t2) -> t1.getStatus().compareTo(t2.getStatus()));
		return lista;
	}
	
	public ResultadoOperacao concluirTarefa (int id) {
		Tarefa tarefa = repository.buscarPorId(id);
		
		if (tarefa == null) {
			return ResultadoOperacao.NAO_ENCONTRADA;
			}
		if (tarefa.getStatus() == Tarefa.StatusTarefa.Concluida) {
			return ResultadoOperacao.JA_CONCLUIDA;
		}
		tarefa.setStatus(Tarefa.StatusTarefa.Concluida);
		return ResultadoOperacao.SUCESSO;
	}
	
	public ResultadoOperacao removerTarefa (int id) {
		Tarefa tarefa = repository.buscarPorId(id);
		
		if (tarefa == null) {
			return ResultadoOperacao.NAO_ENCONTRADA;
		}
		
		repository.remover(tarefa);
		return ResultadoOperacao.SUCESSO;
	}
	
	
	
}
