import java.time.LocalDateTime;
import java.util.LinkedList;

public class Atividade {
	
	private int id;
	private String descricao;
	LocalDateTime inicio;
	LocalDateTime termino;
	Usuario responsavel;
	
	// Profissionais e tarefas compartilham indice
	LinkedList<Usuario> profissionais;
	LinkedList<String> tarefas;
	
	public Atividade(int id, String descricao, LocalDateTime inicio, LocalDateTime termino, Usuario responsavel) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.inicio = inicio;
		this.termino = termino;
		this.responsavel = responsavel;
		profissionais = new LinkedList<>();
		tarefas = new LinkedList<>();
	}
	
	public void adicionarProfissional(Usuario usuario, String tarefa) {
		profissionais.add(usuario);
		tarefas.add(tarefa);
	}
	
	public void removerProfissional(int index) {
		profissionais.remove(index);
		tarefas.remove(index);
	}
		
	public void print(String antes, String depois, boolean printID) {
		System.out.print(antes);
		if(printID) {
			System.out.println("ID: " + id);
		}
		System.out.println("Descrição: " + descricao);
		System.out.println("Responsável: " + responsavel.getNome());
		System.out.println("Data de Início: " + inicio.toString());
		System.out.println("Data de Término: " + termino.toString());
		
		if(profissionais.size() > 0) {
			printProfissionais();
		}
		
		System.out.print(depois);
	}

	public void printProfissionais() {
		System.out.println("Profissionais envolvidos:");
		for(int i = 0; i < profissionais.size(); i++) {
			System.out.println("\t -> " + profissionais.get(i).getNome() + " - " + profissionais.get(i).getRA());	
			System.out.println("\t    Tarefas: " + tarefas.get(i));	
		}
	}
	
	// Getters e Setters
	
	public int getId() {
		return id;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public LinkedList<Usuario> getProfissionais() {
		return profissionais;
	}
	
	public LinkedList<String> getTarefas() {
		return tarefas;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getTermino() {
		return termino;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public void setTermino(LocalDateTime termino) {
		this.termino = termino;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	
	
	
}
