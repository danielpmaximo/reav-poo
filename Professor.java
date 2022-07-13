

public class Professor extends Usuario{

	public Professor(int ra, String nome) {
		super(ra, nome);
	}

	public void print(String antes, String depois) {
		System.out.print(antes);
		System.out.println("RA: " + ra);
		System.out.println("Nome: " + nome);
		System.out.println("Categoria: Professor");
		System.out.print(depois);
		
	}
	
	public String getCategoria() {
		return "Professor";
	}
	
	
}
