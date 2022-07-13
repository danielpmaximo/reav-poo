

public class Profissional extends Usuario{

	public Profissional(int ra, String nome) {
		super(ra, nome);
	}

	public void print(String antes, String depois) {
		System.out.print(antes);
		System.out.println("RA: " + ra);
		System.out.println("Nome: " + nome);
		System.out.println("Categoria: Profissional");
		System.out.print(depois);
		
	}
	
	public String getCategoria() {
		return "Profissional";
	}
	
}
