

public class Pesquisador extends Usuario{

	public Pesquisador(int ra, String nome) {
		super(ra, nome);
	}

	public void print(String antes, String depois) {
		System.out.print(antes);
		System.out.println("RA: " + ra);
		System.out.println("Nome: " + nome);
		System.out.println("Categoria: Pesquisador");
		System.out.print(depois);
		
	}
	
	public String getCategoria() {
		return "Pesquisador";
	}
	
}
