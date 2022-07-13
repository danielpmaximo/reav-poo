

import java.util.LinkedList;

public abstract class Usuario {
	
	public static final int CAT_ALUNO_GRADUCAO = 1; 	// Usuário da categoria aluno de graduação 
	public static final int CAT_ALUNO_MESTRADO = 2; 	// Usuário da categoria aluno de mestrado
	public static final int CAT_ALUNO_DOUTORADO = 3; 	// Usuário da categoria aluno de doutorado
	public static final int CAT_PROFESSSOR= 4;		// Usuário da categoria professor
	public static final int CAT_PESQUISADOR = 5;		// Usuário da categoria pesquisador
	public static final int CAT_PROFISSIONAL = 6; 	// Usuário da categoria profissional (desenvolvedor, testador e analista)
	public static final int CAT_TECNICO = 7;		// Usuário da categoria técnico

	protected int ra;
	protected String nome;
	
	// Construtor
	
	public Usuario(int ra, String nome) {
		this.ra = ra;
		this.nome = nome;
	}

	// Métodos abstratos
	
	public abstract void print(String antes, String depois);
	
	public abstract String getCategoria();
	
	// Getters e Setters
	
	public int getRA() {
		return ra;
	}

	public String getNome() {
		return nome;
	}

	public void setRA(int codUsuario) {
		this.ra = codUsuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static int indiceEmLista(LinkedList<Usuario> lista, int ra) {
		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getRA() == ra) {
				return i;
			}
		}
		
		return -1;
	}
}
