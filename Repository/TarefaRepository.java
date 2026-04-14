package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Tarefa;




public class TarefaRepository {
	
	// responsavel por armazenar e gerenciar as tarefas
	
	
	private List<Tarefa> tarefas = new ArrayList <> (); //isso é uma lista que cresce sozinha, de forma dinamica
	
	private int proximoId = 1; //para guardar o proximo id disponivel
	
	private static final String ARQUIVO = "tarefas.txt";
	
	public TarefaRepository() {
		carregarDoArquivo();
	}
	
	public void adicionar(Tarefa tarefa) {
		tarefa.setId(proximoId);
		proximoId++;
		
		tarefas.add(tarefa);
		
		salvarNoArquivo();
	}
	
	public List <Tarefa> listar (){
		return tarefas;
	}
	
	 public Tarefa buscarPorId(int id) {
	        for (Tarefa t : tarefas) {
	            if (t.getId() == id) {
	                return t;
	            }
	        }
	        return null;
	    }
	 
	public void remover (Tarefa tarefa) {
		tarefas.remove(tarefa);
		
		salvarNoArquivo();
	}
	
	
	private void carregarDoArquivo() {
		try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))){
			
			String linha;
			
			while ((linha = reader.readLine()) != null) {
				if (linha.isBlank()) {
					continue;
				}
				
				String [] partes = linha.split(";");
				
				int id = Integer.parseInt(partes[0]);
				String titulo = partes [1];
				String descricao = partes [2];
				Tarefa.StatusTarefa status = Tarefa.StatusTarefa.valueOf(partes[3]);
				
				Tarefa tarefa = new Tarefa (titulo, descricao);
				tarefa.setId(id);
				tarefa.setStatus(status);
				
				tarefas.add(tarefa);
				
				if (id >= proximoId) {
					proximoId = id + 1;
				}
			}
		} catch (IOException e) {
			System.out.println("Arquivo de tarefas não encontrado. Será criado ao salvar");
		}
	}
	
	private void salvarNoArquivo() {
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))){
		
			for (Tarefa tarefa : tarefas) {
				String linha=
						tarefa.getId() + ";" +
						tarefa.getTitulo() + ";"+
						tarefa.getDescricao() + ";" +
						tarefa.getStatus();
						
				writer.write(linha);
				writer.newLine();
				
			}
		} catch (IOException e) {
			System.out.println("Erro ao salvar tarefas no arquivo.");
		}
	}
} 
