

public class Tecnico extends Usuario{

	public Tecnico(int ra, String nome) {
		super(ra, nome);
	}

	public void print(String antes, String depois) {
		System.out.print(antes);
		System.out.println("RA: " + super.ra);
		System.out.println("Nome: " + super.nome);
		System.out.println("Categoria: Técnico");
		System.out.print(depois);
		
	}
	
	public String getCategoria() {
		return "Técnico";
	}
	
	
}
