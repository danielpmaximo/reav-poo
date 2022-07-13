import java.time.LocalDateTime;
import java.util.LinkedList;

public class Projeto {
	
	public static final int STATUS_CRIACAO = 1;
	public static final int STATUS_INICIADO = 2;
	public static final int STATUS_ANDAMENTO = 3;
	public static final int STATUS_CONCLUIDO = 4;
	
	private int id;
	private String descricao;
	private LocalDateTime inicio;
	private LocalDateTime termino;
	private Usuario coordenador;
	private LinkedList<Usuario> profissionais;
	private LinkedList<Atividade> atividades;
	private int status;
	
	// Construtor
	public Projeto(int id) {
		this.id = id;
		status = Projeto.STATUS_CRIACAO;
		descricao = null;
		coordenador = null;
		inicio = null;
		termino = null;
		profissionais = new LinkedList<>();
		atividades = new LinkedList<>();
	}
	
	// Métodos da Classe
	
	// Valida se um usuário pode ser coordenador
	public static boolean validaCoordenador(Usuario u) {
		if (u.getClass() == Pesquisador.class || u.getClass() == Professor.class) {
			return true;
		} else {
			return false;
		}
	}
	
	// Insere profissional
	public void inserirProfissional(Usuario u) {
		if(!profissionais.contains(u)) {
			profissionais.add(u);
		}
	}
	
	// Insere atividade
	public void inserirAtividade(Atividade a) {
		if(!atividades.contains(a)) {
			atividades.add(a);
		}
	}
	
	// Remove profissional
	public boolean removerProfissional(Usuario u) {
		return profissionais.remove(u);
	}
	
	// Remove atividade
	public boolean removerAtividade(Atividade a) {
		return atividades.remove(a);
	}
	
	// Imprime atividade
	public void print(String antes, String depois, boolean imprimeId) {
		System.out.print(antes);
		if(imprimeId) {
			System.out.println("Id: " + id);	
		}
		
		System.out.println("Status: " + getStatusTexto());
		
		if(coordenador == null) {
			System.out.println("Coordenador: Não definido");
		} else {
			System.out.println("Coordenador: " + coordenador.getNome());
		}
		
		if(descricao == null) {
			System.out.println("Descrição: Não definido");
		} else {
			System.out.println("Descrição: " + descricao);
		}
		
		if(inicio == null) {
			System.out.println("Data de Início: Não definido");
		} else {
			System.out.println("Data de Início: " + inicio.toString());
		}
		
		if(termino == null) {
			System.out.println("Data de Término: Não definido");
		} else {
			System.out.println("Data de Término: " + termino.toString());
		}
		
		System.out.println("Profissionais envolvidos no projeto:");
		for(Usuario u : profissionais) {
			u.print(" -> ", "");
		}
		
		System.out.println("Atividades do projeto:");
		for(Atividade a : atividades) {
			a.print(" -> ", "", false);
		}
		
		System.out.print(depois);
	}
	
	// Getters e Setters	
	
	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public LocalDateTime getTermino() {
		return termino;
	}

	public Usuario getCoordenador() {
		return coordenador;
	}

	public LinkedList<Usuario> getProfissionais() {
		return profissionais;
	}

	public LinkedList<Atividade> getAtividades() {
		return atividades;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getStatusTexto() {
		switch(status) {
		case Projeto.STATUS_CRIACAO:
			return "Em processo de criação";
		case Projeto.STATUS_INICIADO:
			return "Iniciado";
		case Projeto.STATUS_ANDAMENTO:
			return "Em andamento";			
		case Projeto.STATUS_CONCLUIDO:
			return "Concluído";
		}
		
		return null;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDescricao(String descrição) {
		this.descricao = descrição;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public void setTermino(LocalDateTime termino) {
		this.termino = termino;
	}

	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}
	
	
	
}
