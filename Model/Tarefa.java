package model;

public class Tarefa {
	
	// essa classe representa a tarefa do sistema
	// ela guarda os dados da tarefa e define o estado dela
	
	// private é que só a classe pode mexer 
	
	private int id; 		// identificador da tarefa
	
	private String titulo; //nome curto da tarefa
	
	private String descricao; //descição da tarefa
	
	private StatusTarefa status; //  se foi concluida ou nao com true ou false
	
	public Tarefa (String titulo, String descricao) {
		
		this.titulo = titulo;
		this.descricao = descricao;
		this.status = StatusTarefa.Pendente;
		}
	
	public enum StatusTarefa{
		Pendente,
		EmAndamento,
		Concluida
	}
	

    public int getId() {
        return id;
    }

	public void setId(int id) {
	    this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public StatusTarefa getStatus() {
		return status;
	}
	
	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	public void exibir () {
		System.out.println("-------------------------");
		System.out.println("Id: " + id);
		System.out.println("Título: " + titulo);
		System.out.println("Descrição: " + descricao);
		System.out.println("Status: " + status);
		System.out.println("-------------------------");
	}
	
}
