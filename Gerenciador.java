import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class Gerenciador {
	
	private static LinkedList<Projeto> projetos = new LinkedList<>();
	private static LinkedList<Usuario> usuarios = new LinkedList<>();
	private static LinkedList<Atividade> atividades = new LinkedList<>();
	private static int proj_id_seq = 0;
	private static int ativ_id_seq = 0;
	
	///////////////////////////////////////////////////
	// Métodos auxiliares
	//////////////////////////////////////////////////
	
	// Le uma linha da entrada padrão
	private static String leLinha() {
		// Inicializa o leitor da entrada padrão
		BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));

		try {
			return leitor.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// Recebe um conjunto de opções disponíveis e retorna uma opção lida
	// que esteja entre as opções disponíveis
	private static int leOpcao(int [] opcoes) {
		int op;
		
		// Repete para validar a entrada
		while(true) {
			// Imprime mensagem
			System.out.print("Opcao >> ");
			
			// Tenta ler a linha da entrada padrão e converter para inteiro
			try {
				op = Integer.parseInt(leLinha());
			
			// Se não foi possível converter para inteiro
			} catch (NumberFormatException e) {
				// Imprime mensagem de opção invalida e volta pro inicio do loop
				System.out.println("\nOpcao invalida!\n");
				continue;
			}
			
			// Para cada opção possível
			for(int o : opcoes) {
				// Verifica se a opção lida é igual a uma delas
				if(op == o) {
					// Se for, retorna a opção lida
					return op;
				}
			}
			
			// Caso contrário, imprime mensagem de opção inválida
			System.out.println("\nOpcao invalida!\n");
		}
	}
	
	// Recebe um conjunto de opções disponíveis e retorna uma opção lida
	// que esteja entre as opções disponíveis
	private static int leInt(int min, int max) {
		int num;
		
		// Repete para validar a entrada
		while(true) {
			
			// Tenta ler a linha da entrada padrão e converter para inteiro
			try {
				num = Integer.parseInt(leLinha());
			
			// Se não foi possível converter para inteiro
			} catch (NumberFormatException e) {
				// Retorna o anterior ao mínimo
				return min-1;
			}
			
			// Se o numero não está no intervalo
			if(num < min || num > max) {
				// Retorna o sucessor do máximo
				return max+1;
			}
			
			// Caso contrário, retorna o numero
			return num;
		}
	}
	
	private static int leID(String label) {
		int ra;
		
		// Loop para validar
		while(true) {
			// Le RA
			System.out.print(label + ": ");
			
			// Tenta converter entrada para inteiro
			try {
				ra = Integer.parseInt(leLinha());			
				
			// Se não for possível
			} catch (NumberFormatException e) {
				// Imprime RA inválido
				System.out.println("\n" + label + " invalido!\n");
				
				// E volta ao inicio do loop
				continue;
			}
			
			// Se o número foi convertido, retorna o ra lido
			return ra;
		}	
	}

	// Le uma data e hora da entrada padrão
	private static LocalDateTime leData() {	
		LocalDate data;
		LocalTime horario;
		int dia, mes, ano, hora, min;
		
		// Valida a data
		while (true) {
			
			// Valida o dia
			while(true) {
				System.out.print("Dia: ");
				dia = leInt(1,31);
				
				if(dia == 0 || dia == 32) {
					System.out.print("\nDia inválido! Insira um número entre 1 e 31.\n");
				} else {
					break;
				}
			}
			
			
			// Valida o mês
			while(true) {
				System.out.print("Mês: ");
				mes = leInt(1,12);
				
				if(mes == 0 || mes == 13) {
					System.out.print("\nMês inválido! Insira um número entre 1 e 12.\n");
				} else {
					break;
				}
			}
			
			// Valida o ano
			while(true) {
				System.out.print("Ano: ");
				ano = leInt(2000,2100);
				
				if(ano == 1999 || ano == 2101) {
					System.out.print("\nAno inválido!\n");
				} else {
					break;
				}
			}
			
			// Estabele a data a partir do ano, mês e dia lidos
			try { 
				// Tenta criar a data
				data = LocalDate.of(ano, mes, dia);
				
				// Se der certo, quebra o loop
				break;
			// Se a data for inválida, haverá uma exception
			} catch (DateTimeException e) {
				System.out.println("\nData inválida!\n");
				continue;
			}
		}
		
		// Valida o horario
		while(true) {
			// Se a data está validada, agora le a hora
			// Valida a hora
			while(true) {
				System.out.print("Hora: ");
				hora = leInt(0,23);
				
				if(hora == -1 | hora == 24) {
					System.out.print("\nHora inválida! Insira um número entre 0 e 23.\n");
				} else {
					break;
				}
			}
			
			// Valida os minutos
			while(true) {
				System.out.print("Minuto: ");
				min = leInt(0,59);
				
				if(min == -1 || min == 60) {
					System.out.print("\nMinuto inválido! Insira um número entre 0 e 59.\n");
				} else {
					break;
				}
			}
			
			
			// Tenta criar a hora corretamente
			try {
				horario = LocalTime.of(hora, min);			
				break;
			} catch (DateTimeException e) {
				System.out.println("\nData inválida!\n");
				continue;
			}
		}

		// Finalmente, podemos retornar a dada
		return LocalDateTime.of(data, horario);
	}
	
	//////////////////////////////////////////////
	// Menu Principal
	///////////////////////////////////////////////
	
	// Imprime menu principal e retorna a opção lida
	private static int menuPrincipal() {
		// Imprime mensagens do menu
		System.out.println("== Sistema de Gerenciamento de Projetos ==");
		System.out.println("(1) Projetos");
		System.out.println("(2) Usuários");
		System.out.println("(3) Atividades");
		System.out.println("(4) Relatórios");
		System.out.println("(5) Sair");
						
		return leOpcao(new int[]{1,2,3,4,5});
	}
	
	///////////////////////////////////////////////
	// Telas de Projetos e Subtelas
	///////////////////////////////////////////////
	
	// Submenu de projetos
	// Exibe o submenu de projetos e executa de acordo com a opção lida
	private static void telaProjetos() {
		while(true) {
			// Imprime mensagens do menu
			System.out.println("\n== Projetos ==");
			System.out.println("(1) Novo");
			System.out.println("(2) Modificar");
			System.out.println("(3) Consultar");
			System.out.println("(4) Voltar");
							
			switch(leOpcao(new int[]{1,2,3,4})) {
			case 1:
				novoProjeto();
				break;
			case 2:
				modificarProjeto();
				break;
			case 3:
				consultarProjetos();
				break;
			case 4:
				System.out.println("");
				// Encerra o método
				return;
			}
		}
		
	}

	// Cria novo projeto
	private static void novoProjeto() {
		Projeto p = new Projeto(proj_id_seq++);
		
		while(true) {
			// Imprime mensagens do menu
			System.out.println("\n== Novo projeto ==");
			System.out.println("(1) Descrição");
			System.out.println("(2) Coordenador");
			System.out.println("(3) Profissionais");
			System.out.println("(4) Atividades");
			System.out.println("(5) Início");
			System.out.println("(6) Término");
			System.out.println("(7) Status");
			System.out.println("(8) Criar projeto e voltar");
			System.out.println("(9) Cancelar");
			switch(leOpcao(new int[]{1,2,3,4,5,6,7,8,9})) {
			case 1:
				modificarDescricaoProjeto(p);
				break;
			case 2:
				modificarCoordenador(p);
				break;
			case 3:
				submenuProfissionaisProjeto(p);
				break;
			case 4:
				submenuAtividadesProjeto(p);
				break;
			case 5:
				modificarInicioProjeto(p);
				break;
			case 6:
				modificarTerminoProjeto(p);
				break;
			case 7:
				modificarStatus(p);
				break;
			case 8:
				// Insere projeto na lista de projetos
				projetos.add(p);
				// Quebra a linha
				System.out.println("Projeto criado com sucesso!");
				// Encerra o método
				return;
			case 9:
				// Quebra a linha
				System.out.println("Operação cancelada!");
				// Encerra o método
				return;
			}
			
		}
		
		
	}
	
	private static void modificarProjeto() {
		if(projetos.size() == 0) {
			System.out.println("Não há projetos para modificar!\n");
			return;
		}
		
		System.out.println("\n== Modificar projeto ==");
		
		System.out.println("\nProjetos cadastrados:");
		System.out.println("----------------------------------------------");
		for(Projeto p : projetos) {
			p.print("", "----------------------------------------------\n", true);
		}
		
		// Valida escolha do projeto
		while(true) {
			int id = leID("Id do projeto");
			Projeto proj = null;
			boolean encontrado = false;
			
			for(Projeto p : projetos) {
				if (p.getId() == id) {
					encontrado = true;
					proj = p;
					break;
				}
			}
			
			if(encontrado) {
				proj.print("\n----------------------------------------------\n",
						"----------------------------------------------\n", true);
				
				while(true) {
					// Imprime mensagens do menu
					System.out.println("\n== Modificar projeto ==");
					System.out.println("(1) Descrição");
					System.out.println("(2) Coordenador");
					System.out.println("(3) Profissionais");
					System.out.println("(4) Atividades");
					System.out.println("(5) Início");
					System.out.println("(6) Término");
					System.out.println("(7) Status");
					System.out.println("(8) Cancelar");
					switch(leOpcao(new int[]{1,2,3,4,5,6,7,8})) {
					case 1:
						modificarDescricaoProjeto(proj);
						break;
					case 2:
						modificarCoordenador(proj);
						break;
					case 3:
						submenuProfissionaisProjeto(proj);
						break;
					case 4:
						submenuAtividadesProjeto(proj);
						break;
					case 5:
						modificarInicioProjeto(proj);
						break;
					case 6:
						modificarTerminoProjeto(proj);
						break;
					case 7:
						modificarStatus(proj);
						break;
					case 8:
						// Quebra a linha
						System.out.println("Operação cancelada!");
						// Encerra o método
						return;
					}
				}
			} else {
				System.out.println("\nProjeto não encontrado!");
			}
		}
	}
	
	private static void consultarProjetos() {
		if(projetos.size() == 0) {
			System.out.println("Não há projetos para consultar!\n");
			return;
		}
				
		// Imprime mensagens do menu
		System.out.println("\n== Consultar projetos ==");
		
		System.out.println("(1) Lista de Projetos");
		System.out.println("(2) Buscar por ID");
		
		// Le a opção e executa de acordo
		switch(leOpcao(new int[] {1,2})) {
		case 1:
			System.out.println("\n== Lista de Projetos ==");
			System.out.println("----------------------------------------------");
			
			for(Projeto p : projetos) {
				p.print("", "----------------------------------------------\n", false);
			}
			
			return;
		case 2:
			System.out.println("\n== Buscar por ID ==");
			// Le RA
			int id = leID("ID");
			
			// Procura usuario com ra lido
			for(Projeto p : projetos) {
				
				// Se o id foi encontrado na lista de atividades
				if(p.getId() == id) {
					// Imprime informações da atividade
					p.print("\n----------------------------------------------\n",
							"----------------------------------------------\n", false);
					return;
				}				
			}
			
			// Se não encontramos o id;
			System.out.println("\nProjeto não encontrado!");
			
			return;
		}
		
	}
	
	///////////////////////////////////////////////////////////////
	// Métodos auxiliares dos métodos de Projetos
	///////////////////////////////////////////////////////////////
	
	private static void submenuProfissionaisProjeto(Projeto p) {
		while(true) {
			// Imprime mensagens do menu
			System.out.println("\n== Profissionais do projeto ==");
			System.out.println("(1) Adicionar");
			System.out.println("(2) Remover");
			System.out.println("(3) Listar");
			System.out.println("(4) Voltar");
			switch(leOpcao(new int[]{1,2,3,4})) {
			case 1:
				adicionarProfissionalProjeto(p);
				break;
			case 2:
				removerProfissionalProjeto(p);
				break;
			case 3:
				listarProfissionalProjeto(p);
				break;
			case 4:
				// Encerra o método
				return;
			}
			
		}
	}
	
	private static void submenuAtividadesProjeto(Projeto p) {
		while(true) {
			// Imprime mensagens do menu
			System.out.println("\n== Atividades do projeto ==");
			System.out.println("(1) Adicionar");
			System.out.println("(2) Remover");
			System.out.println("(3) Listar");
			System.out.println("(4) Voltar");
			switch(leOpcao(new int[]{1,2,3,4})) {
			case 1:
				adicionarAtividadesProjeto(p);
				break;
			case 2:
				removerAtividadesProjeto(p);
				break;
			case 3:
				listarAtividadesProjeto(p);
				break;
			case 4:
				// Encerra o método
				return;
			}
			
		}
	}
	
	private static void modificarDescricaoProjeto(Projeto p) {
		String descricao;
		
		if(p.getDescricao() == null) {
			// Imprime mensagens do menu
			System.out.println("\n== Descrição do Projeto ==");
			
			// Le nova descrição para o projeto
			System.out.println("Descrição: ");
			descricao = leLinha();
			
			p.setDescricao(descricao);
			System.out.println("\nDescrição incluída com sucesso!");
			return;
						
		} else {
			// Imprime mensagens do menu
			System.out.println("\n== Alterar Descrição do Projeto ==");
			
			// Le nova descrição para o projeto
			System.out.println("Nova descrição: ");
			descricao = leLinha();
			
			// Confirma
			System.out.println("\nDeseja substituir a descrição?");
			
			System.out.println("\nDescrição antiga: " + p.getDescricao());
			
			System.out.println("Nova descrição: " + descricao);
			
			System.out.println("\n(1) Confirmar e substituir");
			System.out.println("(2) Cancelar e voltar");
			
			// Le a opção e executa de acordo
			switch(leOpcao(new int[] {1,2})) {
			case 1:
				p.setDescricao(descricao);
				System.out.println("\nDescrição alterada com sucesso!");
				return;
			case 2:
				System.out.println("\nOperação cancelada!");
				return;
			}
		}
	}
	
	private static void modificarCoordenador(Projeto p) {
		
		if(p.getCoordenador() == null) {
			// Imprime mensagens do menu
			System.out.println("\n== Coordenador de Projeto ==");
			
			// Repete para validar o RA
			while(true) {
				int ra = leID("RA do Coordenador");
				Usuario user = null;
				boolean encontrado = false;
				
				// Procura por ra
				for(Usuario u : usuarios) {
					if(u.getRA() == ra) {
						encontrado = true;
						user = u;
						break;
					}
				}
				
				// Se encontrado
				if(encontrado) {
					// Imprime o usuario
					user.print("\n----------------------------------------------\n",
							"----------------------------------------------\n");
					
					// Verifica se pode ser coordenador
					if(Projeto.validaCoordenador(user)) {
						// Se puder, adiciona como coordenador
						System.out.println("\nConfirma este usuário como coordenador do projeto?");
						
						System.out.println("\n(1) Confirmar");
						System.out.println("(2) Inserir outro RA");
						System.out.println("(3) Cancelar e voltar");
						
						switch(leOpcao(new int[] {1,2,3})) {
						case 1:
							p.setCoordenador(user);
							System.out.println("\nCoordenador incluído com sucesso!");
							return;
						case 2:
							continue;
						case 3:
							System.out.println("\nOperação cancelada!");
							return;
						}					
					} else {
						System.out.println("\nO usuário encontrado não pode ser coordenador, pois não é Professor nem Pesquisador!");
						
						System.out.println("\nDeseja cadastrar um novo usuário?");
						
						System.out.println("\n(1) Cadastrar novo usuario");
						System.out.println("(2) Inserir outro RA");
						System.out.println("(3) Cancelar e voltar");
						
						switch(leOpcao(new int[] {1,2,3})) {
						case 1:
							cadastrarUsuario();
							continue;
						case 2:
							continue;
						case 3:
							System.out.println("\nOperação cancelada!");
							return;
						}
					}					
				} else {
					System.out.println("\nRA do Coordenador não encontrado!");
					
					System.out.println("\nDeseja cadastrar um novo usuário?");
					
					System.out.println("(1) Cadastrar novo usuario");
					System.out.println("(2) Inserir outro RA");
					System.out.println("(3) Cancelar e voltar");
					
					switch(leOpcao(new int[] {1,2,3})) {
					case 1:
						cadastrarUsuario();
						continue;
					case 2:
						continue;
					case 3:
						System.out.println("\nOperação cancelada!");
						return;
					}
				}
			}						
		} else {
			// Imprime mensagens do menu
			System.out.println("\n== Alterar Coordenador de Projeto ==");
			
			// Repete para validar o RA
			while(true) {
				int ra = leID("RA do Novo Coordenador");
				Usuario user = null;
				boolean encontrado = false;
				
				// Procura por ra
				for(Usuario u : usuarios) {
					if(u.getRA() == ra) {
						encontrado = true;
						user = u;
						break;
					}
				}
				
				// Se encontrado
				if(encontrado) {
					// Imprime o antigo coordenador
					p.getCoordenador().print("\n----------------------------------------------\nAntigo Coordenador:\n",
							"----------------------------------------------\n");
					
					// Imprime o novo coordenador
					user.print("Novo Coordenador:\n",
							"----------------------------------------------\n");
					
					// Verifica se pode ser coordenador
					if(Projeto.validaCoordenador(user)) {
						// Se puder, adiciona como coordenador
						System.out.println("\nConfirmar a substituição do coordenador do projeto?");
						
						System.out.println("\n(1) Confirmar");
						System.out.println("(2) Inserir outro RA");
						System.out.println("(3) Cancelar e voltar");
						
						switch(leOpcao(new int[] {1,2,3})) {
						case 1:
							p.setCoordenador(user);
							System.out.println("\nCoordenador substituido com sucesso!");
							return;
						case 2:
							continue;
						case 3:
							System.out.println("\nOperação cancelada!");
							return;
						}					
					} else {
						System.out.println("\nO usuário encontrado não pode ser coordenador, pois não é Professor nem Pesquisador!");
						
						System.out.println("\nDeseja cadastrar um novo usuário?");
						
						System.out.println("\n(1) Cadastrar novo usuario");
						System.out.println("(2) Inserir outro RA");
						System.out.println("(3) Cancelar e voltar");
						
						switch(leOpcao(new int[] {1,2,3})) {
						case 1:
							cadastrarUsuario();
							continue;
						case 2:
							continue;
						case 3:
							System.out.println("\nOperação cancelada!");
							return;
						}
					}					
				} else {
					System.out.println("RA do Novo Coordenador não encontrado!");
					
					System.out.println("\nDeseja cadastrar um novo usuário?");
					
					System.out.println("\n(1) Cadastrar novo usuario");
					System.out.println("(2) Inserir outro RA");
					System.out.println("(3) Cancelar e voltar");
					
					switch(leOpcao(new int[] {1,2,3})) {
					case 1:
						cadastrarUsuario();
						continue;
					case 2:
						continue;
					case 3:
						System.out.println("\nOperação cancelada!");
						return;
					}
				}
			}	
		}
	}
	
	private static void modificarInicioProjeto(Projeto p) {
		LocalDateTime inicio;
		
		if(p.getInicio() == null) {
			// Imprime mensagens do menu
			System.out.println("\n== Data de Início do Projeto ==");
			
			// Le nova descrição para o projeto
			System.out.println("Data de início: ");
			inicio = leData();
			
			p.setInicio(inicio);
			System.out.println("\nData de início incluída com sucesso!");
			return;
						
		} else {
			// Imprime mensagens do menu
			System.out.println("\n== Alterar Data de Início de Projeto ==");

			// Le nova data de Inicío			
			System.out.println("Nova data de início:");
			inicio = leData();
			
			// Confirma a substituição
			System.out.println("\nDeseja substituir a data de início?");
			
			System.out.println("\nData de início antigo: " + p.getInicio().toString());
			
			System.out.println("Nova data de início: " + inicio.toString());
			
			System.out.println("\n(1) Confirmar e substituir");
			System.out.println("(2) Cancelar e voltar");
			
			// Le opção e executa de acordo
			switch(leOpcao(new int[] {1,2})) {
			case 1:
				p.setInicio(inicio);
				System.out.println("\nData de início alterada com sucesso!");
				return;
			case 2:
				System.out.println("\nOperação cancelada!");
				return;
			}
		}		
	}
	
	private static void modificarTerminoProjeto(Projeto p) {
		LocalDateTime termino;
		
		if(p.getTermino() == null) {
			// Imprime mensagens do menu
			System.out.println("\n== Data de Término do Projeto ==");
			
			// Le nova descrição para o projeto
			System.out.println("Data de término: ");
			termino = leData();
			
			p.setTermino(termino);
			System.out.println("\nData de término incluída com sucesso!");
			return;
						
		} else {
			// Imprime mensagens do menu
			System.out.println("\n== Alterar Data de Término de Projeto ==");

			// Le nova data de Inicío			
			System.out.println("Nova data de término:");
			termino = leData();
			
			// Confirma a substituição
			System.out.println("\nDeseja substituir a data de término?");
			
			System.out.println("\nData de término antigo: " + p.getTermino().toString());
			
			System.out.println("Nova data de término: " + termino.toString());
			
			System.out.println("\n(1) Confirmar e substituir");
			System.out.println("(2) Cancelar e voltar");
			
			// Le opção e executa de acordo
			switch(leOpcao(new int[] {1,2})) {
			case 1:
				p.setTermino(termino);
				System.out.println("\nData de término alterada com sucesso!");
				return;
			case 2:
				System.out.println("\nOperação cancelada!");
				return;
			}
		}		
	}
	
	private static void adicionarProfissionalProjeto(Projeto p) {
		// Repete para validar o RA
		while(true) {
			// Imprime mensagens do menu
			System.out.println("\n== Adicionar Profissional ao Projeto ==");
			
			int ra = leID("RA");
			Usuario user = null;
			boolean encontrado = false;
			
			// Procura por ra
			for(Usuario u : usuarios) {
				if(u.getRA() == ra) {
					encontrado = true;
					user = u;
					break;
				}
			}
			
			// Se encontrado
			if(encontrado) {
				// Imprime o usuario
				user.print("\n----------------------------------------------\n",
						"----------------------------------------------\n");
				
				// Se puder, adiciona como coordenador
				System.out.println("\nConfirma inclusão deste usuário ao projeto?");
				
				System.out.println("\n(1) Confirmar");
				System.out.println("(2) Inserir outro RA");
				System.out.println("(3) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2,3})) {
				case 1:
					p.inserirProfissional(user);
					System.out.println("\nProfissional inserido ao projeto com sucesso!");
					return;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nRA não encontrado!");
				
				System.out.println("\nDeseja cadastrar um novo usuário?");
				
				System.out.println("\n(1) Cadastrar novo usuario");
				System.out.println("(2) Inserir outro RA");
				System.out.println("(3) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2,3})) {
				case 1:
					cadastrarUsuario();
					continue;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}
	}
	
	private static void removerProfissionalProjeto(Projeto p) {
		// Imprime mensagens do menu
		System.out.println("\n== Remover Profissional ao Projeto ==");
		
		// Repete para validar o RA
		while(true) {
			int ra = leID("RA");
			Usuario user = null;
			boolean encontrado = false;
			
			// Procura por ra
			for(Usuario u : usuarios) {
				if(u.getRA() == ra) {
					encontrado = true;
					user = u;
					break;
				}
			}
			
			// Se encontrado
			if(encontrado) {
				// Imprime o usuario
				user.print("\n----------------------------------------------\n",
						"----------------------------------------------\n");
				
				// Se puder, adiciona como coordenador
				System.out.println("\nConfirma exclusão deste usuário ao projeto?");
				
				System.out.println("\n(1) Confirmar exclusão");
				System.out.println("(2) Inserir outro RA");
				System.out.println("(3) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2,3})) {
				case 1:
					p.removerProfissional(user);
					System.out.println("\nProfissional removido com sucesso!");
					return;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("RA não encontrado!");
				
				System.out.println("\n(1) Inserir outro RA");
				System.out.println("(2) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2})) {
				case 1:
					continue;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}
	}
	
	private static void listarProfissionalProjeto(Projeto p) {
		System.out.println("\n== Lista de Profissionais do projeto ==");
		
		System.out.println("----------------------------------------------");
		for(Usuario u : p.getProfissionais()) {
			u.print("","----------------------------------------------\n");
		}
		
		return;
	}
	
	private static void adicionarAtividadesProjeto(Projeto p) {
		// Repete para validar o RA
		while(true) {
			// Imprime mensagens do menu
			System.out.println("\n== Adicionar Atividade ao Projeto ==");
			
			int id = leID("ID da Atividade");
			Atividade ativ = null;
			boolean encontrado = false;
			
			// Procura por ra
			for(Atividade a : atividades) {
				if(a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}
			
			// Se encontrado
			if(encontrado) {
				// Imprime o usuario
				ativ.print("\n----------------------------------------------\n",
						"----------------------------------------------\n",true);
				
				// Se puder, adiciona como coordenador
				System.out.println("\nConfirma inclusão desta atividade ao projeto?");
				
				System.out.println("\n(1) Confirmar");
				System.out.println("(2) Inserir outra ID");
				System.out.println("(3) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2,3})) {
				case 1:
					p.inserirAtividade(ativ);
					System.out.println("\nAtividade inserida ao projeto com sucesso!");
					return;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nID de atividade não encontrada!");
				
				System.out.println("\nDeseja adicionar uma nova atividade ao sistema?");
				
				System.out.println("\n(1) Cadastrar nova atividade");
				System.out.println("(2) Inserir outra ID");
				System.out.println("(3) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2,3})) {
				case 1:
					adicionarAtividade();
					continue;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}
	}
	
	private static void removerAtividadesProjeto(Projeto p) {
		// Imprime mensagens do menu
		System.out.println("\n== Remover Atividade ao Projeto ==");
		
		// Repete para validar o RA
		while(true) {
			int id = leID("ID");
			Atividade ativ = null;
			boolean encontrado = false;
			
			// Procura por ra
			for(Atividade a : atividades) {
				if(a.getId() == id) {
					encontrado = true;
					ativ = a;
					break;
				}
			}
			
			// Se encontrado
			if(encontrado) {
				// Imprime o usuario
				ativ.print("\n----------------------------------------------\n",
						"----------------------------------------------\n", true);
				
				// Se puder, adiciona como coordenador
				System.out.println("\nConfirma exclusão desta atividade do projeto?");
				
				System.out.println("\n(1) Confirmar exclusão");
				System.out.println("(2) Inserir outro ID de atividade");
				System.out.println("(3) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2,3})) {
				case 1:
					p.removerAtividade(ativ);
					continue;
				case 2:
					continue;
				case 3:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("ID de atividade não encontrado!");
				
				System.out.println("\n(1) Inserir outro ID de atividade");
				System.out.println("(2) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2})) {
				case 1:
					continue;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}	
	}
	
	private static void listarAtividadesProjeto(Projeto p) {
		System.out.println("\n== Lista de Atividades do projeto ==");
		
		System.out.println("----------------------------------------------");
		for(Atividade a : p.getAtividades()) {
			a.print("","----------------------------------------------\n", false);
		}
		
		return;
	}
	
	
	
	private static void modificarStatus(Projeto p) {
		// Imprime mensagens do menu
		System.out.println("\n== Alterar Status de Projeto ==");
		System.out.println("Status atual: " + p.getStatusTexto());		
		
		switch(p.getStatus()) {
		case Projeto.STATUS_CRIACAO:
			boolean podeAlterarStatus = true;
			
			// Verifica se possui requisitos pra alterar status;
			if(p.getCoordenador() == null) {
				podeAlterarStatus = false;
				System.out.println("Coordenador do projeto não definido.");
			}
		
			if(p.getDescricao() == null) {
				podeAlterarStatus = false;
				System.out.println("Descrição do projeto não definida.");
			}
			
			if(p.getInicio() == null) {
				podeAlterarStatus = false;
				System.out.println("Data de início do projeto não definida.");
			}
			
			if(p.getTermino() == null) {
				podeAlterarStatus = false;
				System.out.println("Data de término do projeto não definida.");
			}
			
			if(p.getProfissionais().size() == 0) {
				podeAlterarStatus = false;
				System.out.println("Projeto não possui profissionais associados.");
			}
		
			if(p.getAtividades().size() == 0) {
				podeAlterarStatus = false;
				System.out.println("Projeto não possui atividades associadas.");
			}
			
			if(podeAlterarStatus) {
				System.out.println("\nDeseja alterar o status do projeto para INICIADO?.");
				
				System.out.println("\n(1) Sim");
				System.out.println("(2) Não");
				
				switch(leOpcao(new int[] {1,2})) {
				case 1:
					if(p.getInicio().isBefore(LocalDateTime.now())) {
						p.setStatus(Projeto.STATUS_INICIADO);
						
						System.out.println("\nStatus alterado com sucesso!");
						return;
					} else {
						System.out.println("\nImpossível alterar status! Data de início anterior a data de hoje.");
					}
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nO status do projeto não pode ser alterado até que todas as pendências sejam resolvidas.");
				return;
			}
		case Projeto.STATUS_INICIADO:
			if(p.getInicio().isBefore(LocalDateTime.now())) {
				p.setStatus(Projeto.STATUS_ANDAMENTO);
				
				System.out.println("\nStatus alterado automaticamente para EM ANDAMENTO!");
				break;
			} else {
				System.out.println("\nAguarde a data de início do projeto. O sistema irá alterar o status automaticamente.");
			}
			return;
		case Projeto.STATUS_ANDAMENTO:
			System.out.println("\nDeseja alterar o status do projeto para CONCLUÍDO?.");
			
			System.out.println("\n(1) Sim");
			System.out.println("(2) Não");
			
			switch(leOpcao(new int[] {1,2})) {
			case 1:
				p.setStatus(Projeto.STATUS_CONCLUIDO);
				System.out.println("\nStatus alterado com sucesso!");
				break;
			case 2:
				System.out.println("\nOperação cancelada!");
			}
			
			return;
		case Projeto.STATUS_CONCLUIDO:
			System.out.println("\nO projeto já foi concluído. Impossível alterar status.");
			return;
		}
		
		
		
		
		
		
	}
		
	///////////////////////////////////////////////
	// Telas de Usuários e Subtelas
	///////////////////////////////////////////////
	
	// Submenu de usuário
	// Exibe o submenu de usuário e executa de acordo com a opção lida
	private static void telaUsuarios() {
		while(true) {
			// Imprime mensagens do menu
			System.out.println("\n== Usuarios ==");
			System.out.println("(1) Cadastrar");
			System.out.println("(2) Remover");
			System.out.println("(3) Consultar");
			System.out.println("(4) Voltar");
							
			switch(leOpcao(new int[]{1,2,3,4})) {
			case 1:
				cadastrarUsuario();
				break;
			case 2:
				removerUsuario();
				break;
			case 3:
				consultarUsuarios();
				break;
			case 4:
				System.out.println("");
				// Encerra o método
				return;
			}
		}
	}
	
	
	// Cadastra um usuário no sistema
	private static void cadastrarUsuario() {
		Usuario novoUsuario = null;
		String nome;
		int ra;
		
		// Imprime mensagens do menu
		System.out.println("\n== Cadastrar usuário ==");
		
		// Le RA
		ra = leID("RA(Número de Matrícula)");

		// Le nome
		System.out.print("Nome: ");
		nome = leLinha();
		
		// Le categoria
		System.out.println("Categoria:");
		System.out.println("(1) Aluno de Graduação");
		System.out.println("(2) Aluno de Mestrado");
		System.out.println("(3) Aluno de Doutorado");
		System.out.println("(4) Professor");
		System.out.println("(5) Pesquisador");
		System.out.println("(6) Profissional");
		System.out.println("(7) Técnico");
		
		// Le a opção da categoria para criar o usuario com a classe correta
		switch(leOpcao(new int[] {1,2,3,4,5,6,7})) {
		case Usuario.CAT_ALUNO_GRADUCAO:
			novoUsuario = new AlunoG(ra, nome);
			break;
		case Usuario.CAT_ALUNO_MESTRADO:
			novoUsuario = new AlunoM(ra, nome);
			break;
		case Usuario.CAT_ALUNO_DOUTORADO:
			novoUsuario = new AlunoD(ra, nome);
			break;
		case Usuario.CAT_PROFESSSOR:
			novoUsuario = new Professor(ra, nome);
			break;
		case Usuario.CAT_PESQUISADOR:
			novoUsuario = new Pesquisador(ra, nome);
			break;
		case Usuario.CAT_PROFISSIONAL:
			novoUsuario = new Profissional(ra, nome);
			break;
		case Usuario.CAT_TECNICO:
			novoUsuario = new Tecnico(ra, nome);
			break;
		}
					
		// Confirma com o usuario para prosseguir ou cancela e volta
		System.out.println("\nAs informações estao corretas?");
		
		// Imprime informações para confirmação
		novoUsuario.print("\n----------------------------------------------\n",
				"----------------------------------------------\n\n");
		
		// Exibe mensagem de confirmação
		System.out.println("(1) Confirmar e cadastrastar ");
		System.out.println("(2) Cancelar e voltar");
		
		// 
		switch(leOpcao(new int[] {1,2})) {
		case 1:
			// Verifica se o RA já foi cadastrado
			for(Usuario u : usuarios) {
				if(u.getRA() == ra) {
					System.out.println("\nRA já cadastrado! Operação cancelada!");
					return;
				}
			}
			
			// Caso não tenhamos encontrado o RA já cadastrado
			// Adiciona usuario na lista de usuários
			usuarios.add(novoUsuario);
			
			// Imprime mensagem
			System.out.println("\nUsuário cadastrado com sucesso!");
		case 2:
			// Encerra a função
			return;
		}
		
	}
	
	
	// Busca por RA e remove usuario
	private static void removerUsuario() {
		if(usuarios.size() == 0) {
			System.out.println("Não há usuários para remover!\n");
			return;
		}	
		
		int ra;
		
		// Imprime mensagens do menu
		System.out.println("\n== Remover usuário ==");

		// Le RA
		ra = leID("RA");
		
		int index = Usuario.indiceEmLista(usuarios, ra);
					
		// Se o ra foi encontrado na lista de usuarios
		if(index != -1) {
			// Imprime informações do usuario e confirma a remoção
			System.out.println("\nDeseja remover o usuário?");
	
			usuarios.get(index).print("\n----------------------------------------------\n",
					"----------------------------------------------\n\n");
			
			System.out.println("(1) Remover e voltar");
			System.out.println("(2) Cancelar e voltar");
			
			// Le a opção e remove o usuario ou cancela, dependendo do caso
			switch(leOpcao(new int[] {1,2})) {
			case 1:
				usuarios.remove(index);
				System.out.println("\nUsuário removido com sucesso!");
				return;
			case 2:
				System.out.println("\nOperação cancelada!");
				return;
			}
		} 

		// Se chegamos aqui, não encontramos o usuario, então exibe mensagem e retorna
		System.out.println("\nUsuário não encontrado!");
		return;
			
	}
	
	
	// Exibe lista de usuários ou busca usuario específico por RA
	private static void consultarUsuarios() {
		if(usuarios.size() == 0) {
			System.out.println("Não há usuários para consultar!\n");
			return;
		}
		
		// Imprime mensagens do menu
		System.out.println("\n== Consultar usuários ==");
		
		System.out.println("(1) Lista de Usuários");
		System.out.println("(2) Buscar por RA");
		
		// Le a opção e executa de acordo
		switch(leOpcao(new int[] {1,2})) {
		case 1:
			System.out.println("\n== Lista de Usuários ==");
			System.out.println("----------------------------------------------");
			
			for(Usuario u : usuarios) {
				u.print("", "----------------------------------------------\n");
			}
			
			return;
		case 2:
			System.out.println("\n== Buscar por RA ==");
			// Le RA
			int ra = leID("RA");
			
			// Procura usuario com ra lido
			for(Usuario u : usuarios) {
				// Se o ra foi encontrado na lista de usuarios
				if(u.getRA() == ra) {
					// Imprime informações do usario
					u.print("\n----------------------------------------------\n",
							"----------------------------------------------\n");
					return;
				}
			}
			
			// Se não encontramos o ra;
			System.out.println("\nUsuário não encontrado!");
			
			return;
		}
	}

	///////////////////////////////////////////////
	
	///////////////////////////////////////////////
	// Telas de Atividades e Subtelas
	
	///////////////////////////////////////////////
	
	// Submenu de atividades
	// Exibe o submenu de atividades e executa de acordo com a opção lida
	private static void telaAtividades() {
		while(true) {
			// Imprime mensagens do menu
			System.out.println("\n== Atividades ==");
			System.out.println("(1) Adicionar");
			System.out.println("(2) Remover");
			System.out.println("(3) Modificar");
			System.out.println("(4) Consultar");
			System.out.println("(5) Voltar");
							
			switch(leOpcao(new int[]{1,2,3,4,5})) {
			case 1:
				adicionarAtividade();
				break;
			case 2:
				removerAtividade();
				break;
			case 3:
				modificarAtividade();
				break;
			case 4:
				consultarAtividades();
				break;
			case 5:
				System.out.println("");
				// Encerra o método
				return;
			}
		}	
	}
	
	
	// Cria e adiciona uma nova atividade
	private static void adicionarAtividade() {
		Usuario responsavel = null;
		String descricao;
		LocalDateTime inicio;
		LocalDateTime termino;
		int ra;
		boolean continuar = true;
		boolean encontrado;
		
		System.out.println("\n== Adicionar Atividade ==");
		
		// Procura por ra e valida como responsável
		while(continuar) {
			ra = leID("RA do Responsável");
			
			// Supõe que não encontramos o usuário
			encontrado = false;
			
			// Procura na lista de usuários
			for(Usuario u : usuarios) {
				if (u.getRA() == ra) {
					// Usuario encontrado
					encontrado = true;
					
					// Confirma o responsavel
					System.out.println("\nConfirma responsável pela atividade?");
					u.print("\n----------------------------------------------\n",
							"----------------------------------------------\n");
					
					System.out.println("\n(1) Sim");
					System.out.println("(2) Não");
					System.out.println("(3) Cancelar e voltar");
					
					// Le opções e trata de acordo
					switch(leOpcao(new int[] {1,2,3})) {
					case 1:
						// Define o responsável
						responsavel = u;
						// Continua
						continuar = false;
						break;
					case 2:
						System.out.println("");
						break;
					case 3:
						System.out.println("\nOperação cancelada!");
						return;
					}
					
					// Podemos parar a procura por novos usuarios
					break;
				}
			}
			
			// Se não foi encontrado
			if(continuar && !encontrado) {
				System.out.println("\nUsuário não encontrado!\n");
				System.out.println("(1) Ler outro RA");
				System.out.println("(2) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2})) {
				case 1:
					break;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}
		}
		
		System.out.println("\nDescrição da atividade: ");
		descricao = leLinha();
		
		// Valida se termino é depois do inicio
		while(true) {
			System.out.println("Data de inicio:");
			inicio = leData();
			
			System.out.println("Data de término:");
			termino = leData();
			
			// Verifica se a data de termino é posterior à de inicio
			if(termino.isAfter(inicio)) {
				// Quebra o loop
				break;
			// Caso contrário, comenta erro
			} else {
				System.out.println("\nInício e término inválidos! A data de início é posterior à data de término.\n");
				continue;
			}
		}
		
		// Cria a atividade
		Atividade a = new Atividade(ativ_id_seq++, descricao, inicio, termino, responsavel);
		
		// Imprime informações da atividade
		System.out.println("\nOs dados da atividade estão corretos?");
		
		a.print("\n----------------------------------------------\n",
				"----------------------------------------------\n", false);
		
		System.out.println("\n(1) Confirmar e adicionar");
		System.out.println("(2) Cancelar e voltar");
		
		// Le a opção e remove o usuario ou cancela, dependendo do caso
		switch(leOpcao(new int[] {1,2})) {
		case 1:
			atividades.add(a);
			System.out.println("\nAtividade adicionada com sucesso!\n");
			
			break;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
		
		// Verifica se quer adicionar profissionais envolvidos
		while(true) {
			System.out.println("Deseja adicionar profissionais envolvidos na atividade?");
			System.out.println("\n(1) Sim");
			System.out.println("(2) Voltar");
			
			// Le a opção e remove o usuario ou cancela, dependendo do caso
			switch(leOpcao(new int[] {1,2})) {
			case 1:
				adicionarProfissionais(a);
				
				a.print("\n----------------------------------------------\n",
						"----------------------------------------------\n", false);
				
				break;
			case 2:
				System.out.println("\nOperação terminada!");
				return;
			}	
		}
	}
	
	
	// Procura e remove uma atividade
	private static void removerAtividade() {
		if(atividades.size() == 0) {
			System.out.println("Não há atividades para remover!\n");
			return;
		}		
		
		int id;		
		boolean encontrado;
		System.out.println("\n== Remover Atividade ==");
		Atividade ativ = null; // Inicializa ativ
		
		// Imprime atividades cadastradas
		System.out.println("Atividades cadastradas:");
		System.out.println("----------------------------------------------");
		for(Atividade a : atividades) {
			a.print("", "----------------------------------------------\n", true);
		}
		
		while(true) {
			id = leID("ID da Atividade");
			
			// Supõe que não encontramos o usuário
			encontrado = false;
			
			// Procura na lista de atividades
			for(Atividade a : atividades) {
				if (a.getId() == id) {
					// Usuario encontrado
					encontrado = true;
					ativ = a;
					break;
				}
			}
			
			// Se encontrado
			if(encontrado) {
				System.out.println("\nDeseja remover a seguinte atividade?");

				ativ.print("\n----------------------------------------------\n", 
						"----------------------------------------------\n", true);
				
				
				System.out.println("\n(1) Confirmar e remover");
				System.out.println("(2) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2})) {
				case 1:
					atividades.remove(ativ);
					System.out.println("\nAtividade removida com sucesso!");
					return;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nAtividade não encontrada!");					
				continue;
			}	
		}
	}
	
	
	// Procura e modifica uma atividade
	private static void modificarAtividade() {
		if(atividades.size() == 0) {
			System.out.println("Não há atividades para modificar!\n");
			return;
		}
		
		int id;		
		boolean encontrado;
		System.out.println("\n== Modificar Atividade ==");
		Atividade ativ = null; // Inicializa ativ
		
		// Imprime atividades cadastradas
		System.out.println("Atividades cadastradas:");
		System.out.println("----------------------------------------------");
		for(Atividade a : atividades) {
			a.print("", "----------------------------------------------\n", true);
		}
		
		while(true) {
			id = leID("ID da Atividade");
			
			// Supõe que não encontramos o usuário
			encontrado = false;
			
			// Procura na lista de atividades
			for(Atividade a : atividades) {
				if (a.getId() == id) {
					// Usuario encontrado
					encontrado = true;
					ativ = a;
					break;
				}
			}
			
			// Se encontrado
			if(encontrado) {
				System.out.println("\nDeseja modificar a seguinte atividade?");

				ativ.print("\n----------------------------------------------\n", 
						"----------------------------------------------\n", true);
				
				
				System.out.println("\n(1) Confirmar e modificar");
				System.out.println("(2) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2})) {
				case 1:
					// Sai do switch para modificar a atividade
					break;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nAtividade não encontrada!");					
				continue;
			}
			
			while(true) {
				// Modificamos
				System.out.println("\nO que você deseja modificar?");
				System.out.println("(1) Responsável");
				System.out.println("(2) Descrição");
				System.out.println("(3) Início");
				System.out.println("(4) Término");
				System.out.println("(5) Incluir usuários envolvidos");
				System.out.println("(6) Excluir usuários envolvidos");
				System.out.println("(7) Alterar tarefa de usuarios");
				System.out.println("(8) Voltar");
				
				switch(leOpcao(new int[] {1,2,3,4,5,6,7,8})) {
				case 1:
					modificarResponsavel(ativ);					
					continue;
				case 2:
					modificarDescricaoAtividade(ativ);
					continue;
				case 3:
					modificarInicioAtividade(ativ);
					continue;
				case 4:
					modificarTerminoAtividade(ativ);
					continue;
				case 5:
					// Inclui usuario
					adicionarProfissionais(ativ);					
					continue;
				case 6:
					// Exclui usuario
					removerProfissional(ativ);					
					continue;
				case 7:
					// Altera tarefa de usuario
					modificarTarefa(ativ);
					continue;
				case 8:
					// Voltar
					return;
				}
			}			
		}
	}
	
	
	// Consultar atividades
	private static void consultarAtividades() {
		if(atividades.size() == 0) {
			System.out.println("Não há atividades para consultar!\n");
			return;
		}
				
		// Imprime mensagens do menu
		System.out.println("\n== Consultar atividades ==");
		
		System.out.println("(1) Lista de Atividades");
		System.out.println("(2) Buscar por ID");
		
		// Le a opção e executa de acordo
		switch(leOpcao(new int[] {1,2})) {
		case 1:
			System.out.println("\n== Lista de Atividades ==");
			System.out.println("----------------------------------------------");
			
			for(Atividade a : atividades) {
				a.print("", "----------------------------------------------\n", false);
			}
			
			return;
		case 2:
			System.out.println("\n== Buscar por ID ==");
			// Le RA
			int id = leID("ID");
			
			// Procura usuario com ra lido
			for(Atividade a : atividades) {
				
				// Se o id foi encontrado na lista de atividades
				if(a.getId() == id) {
					// Imprime informações da atividade
					a.print("\n----------------------------------------------\n",
							"----------------------------------------------\n", false);
					return;
				}
				
				
			}
			
			// Se não encontramos o id;
			System.out.println("\nAtividade não encontrada!");
			
			return;
		}
	}
	

	///////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////
	// Métodos auxiliares dos métodos de Atividades
	///////////////////////////////////////////////////////////////
	
	// Modifica o responsável em uma atividade
	private static void modificarResponsavel(Atividade ativ) {
		// Altera responsável
		
		System.out.println("\n== Modificar Responsável da Atividade ==");
		
		int ra;
		boolean encontrado;
		
		// Le o RA
		ra = leID("RA do Novo Responsável");
		
		// Verifica se o RA lido é igual ao do atual responsável
		if(ativ.getResponsavel().getRA() == ra) {
			System.out.println("O RA do Novo Responsável é idêntico ao RA do Antigo Responsável!");
			System.out.println("\nOperação cancelada!");
			return;
		}
		
		encontrado = false;
		// Verifica se o RA lido está na lista de usuarios cadastrados
		for(Usuario u : usuarios) {
			if(u.getRA() == ra) {
				encontrado = true;
				
				// Verifica se quer trocar responsável
				System.out.print("\nVocê deseja substituir o responsável?");
				
				ativ.getResponsavel().print("\n----------------------------------------------\nAntigo Responsável:\n",
						"----------------------------------------------\n");
				
				u.print("\n----------------------------------------------\nNovo Responsável:\n",
						"----------------------------------------------\n");
				
				System.out.println("(1) Confirmar e substituir");
				System.out.println("(2) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2})) {
				case 1:
					ativ.setResponsavel(u);
					System.out.println("\nResponsável alterado com sucesso!");
					return;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			}			
		}
		
		if(!encontrado){
			System.out.println("\nRA não encontrado! Operação cancelada!");
		}		
	}
	
	// Modifica a descrição da atividade
	private static void modificarDescricaoAtividade(Atividade ativ) {
		// Altera descrição
		
		System.out.println("\n== Modificar Descrição da Atividade ==");
		
		String descricao;
		
		System.out.println("Nova descrição: ");
		descricao = leLinha();
		
		System.out.println("\nDeseja substituir a descrição?");
		
		System.out.println("\nDescrição antiga: " + ativ.getDescricao());
		
		System.out.println("Nova descrição: " + descricao);
		
		System.out.println("\n(1) Confirmar e substituir");
		System.out.println("(2) Cancelar e voltar");
		
		switch(leOpcao(new int[] {1,2})) {
		case 1:
			ativ.setDescricao(descricao);
			System.out.println("\nDescrição alterada com sucesso!");
			return;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
	}
	
	// Modifica a data de incio da atividade
	private static void modificarInicioAtividade(Atividade ativ) {
		// Alterar início
		System.out.println("\n== Modificar Data de Início da Atividade ==");
		
		LocalDateTime inicio;
		
		System.out.println("Nova data de inicio:");
		inicio = leData();
		
		System.out.println("\nDeseja substituir a data de início?");
		
		System.out.println("\nData de início antigo: " + ativ.getInicio().toString());
		
		System.out.println("Nova data de início: " + inicio.toString());
		
		System.out.println("\n(1) Confirmar e substituir");
		System.out.println("(2) Cancelar e voltar");
		
		switch(leOpcao(new int[] {1,2})) {
		case 1:
			ativ.setInicio(inicio);
			System.out.println("\nData de início alterada com sucesso!");
			return;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
	}
	
	// Modifica a data de término da atividade
	private static void modificarTerminoAtividade(Atividade ativ) {
		// Alterar término
		
		System.out.println("\n== Modificar Data de Término da Atividade ==");
		
		LocalDateTime termino;
		
		System.out.println("Nova data de inicio:");
		termino = leData();
		
		System.out.println("\nDeseja substituir a data de término?");
		
		System.out.println("\nData de término antigo: " + ativ.getTermino().toString());
		
		System.out.println("Nova data de término: " + termino.toString());
		
		System.out.println("\n(1) Confirmar e substituir");
		System.out.println("(2) Cancelar e voltar");
		
		switch(leOpcao(new int[] {1,2})) {
		case 1:
			ativ.setTermino(termino);
			System.out.println("\nData de término alterada com sucesso!");
			return;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
	}
	
	// Adiciona profissionais a atividade
	private static void adicionarProfissionais(Atividade a) {
		System.out.println("\n== Adicionar Profissionais à Atividade ==");
		int ra;
		String tarefa;
		boolean encontrado;
		Usuario user = null; // Inicializa objeto
		
		while(true) {
			ra = leID("RA");
			
			// Supõe que não encontramos o usuário
			encontrado = false;
			
			// Procura na lista de usuários
			for(Usuario u : usuarios) {
				if (u.getRA() == ra) {
					// Usuario encontrado
					encontrado = true;
					user = u;
					break;
				}
			}
			
			// Se encontrado
			if(encontrado) {
				// Verifica se o profissional já está na atividade
				if(a.getProfissionais().contains(user)) {
					System.out.println("\nUsuário já consta na lista de profissionais associados a esta atividade!");					
					continue;
				} else {
					user.print("\n----------------------------------------------\n", 
							"----------------------------------------------\n");					
					
					System.out.println("\nTarefa: ");
					tarefa = leLinha();
					
					System.out.println("\nDeseja incluir o seginte profissional e tarefa?");
					
					System.out.println("\n----------------------------------------------");
					System.out.println("Nome: " + user.getNome());
					System.out.println("Tarefa: " + tarefa);
					System.out.println("----------------------------------------------\n");
					
					System.out.println("(1) Confirmar e incluir");
					System.out.println("(2) Cancelar e voltar");
					
					switch(leOpcao(new int[] {1,2})) {
					case 1:
						a.adicionarProfissional(user, tarefa);
						System.out.println("\nProfissional incluído com sucesso!");
						return;
					case 2:
						System.out.println("\nOperação cancelada!");
						return;
					}
				}
			} else {
				System.out.println("\nUsuário não encontrado!");					
				continue;
			}
		}
	}
	
	// Remove profissional da atividade
	private static void removerProfissional(Atividade ativ) {
		System.out.println("\n== Remover Profissionais da Atividade ==");
		int ra, index = -1;
		boolean encontrado;
		
		// Imprime lista de profissionais envolvidos na tarefa
		System.out.println("");
		ativ.printProfissionais();
		
		while(true) {
			ra = leID("RA");
			
			// Supõe que não encontramos o usuário
			encontrado = false;
			
			// Procura na lista de usuários por indice
			for(int i = 0; i < ativ.getProfissionais().size(); i++) {
				if (ativ.getProfissionais().get(i).getRA() == ra) {
					// Usuario encontrado
					encontrado = true;
					index = i;
					break;
				}
			}
			
			// Se encontrado
			if(encontrado) {
				System.out.println("\nDeseja remover o seginte profissional e tarefa?");
				
				System.out.println("\n----------------------------------------------");
				System.out.println("Nome: " + ativ.getProfissionais().get(index).getNome());
				System.out.println("Tarefa: " + ativ.getTarefas().get(index));
				System.out.println("----------------------------------------------\n");
				
				System.out.println("(1) Confirmar e remover");
				System.out.println("(2) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2})) {
				case 1:
					ativ.removerProfissional(index);
					System.out.println("\nProfissional removido com sucesso!");
					return;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nUsuário não encontrado!");					
				continue;
			}
		}
	}
	
	// Modifica tarefas de profissioal da atividade
	private static void modificarTarefa(Atividade ativ) {
		System.out.println("\n== Modificar Tarefa de Profissional da Atividade ==");
		int ra, index;
		
		// Imprime lista de profissionais envolvidos na tarefa
		System.out.println("");
		ativ.printProfissionais();
		
		while(true) {
			ra = leID("RA");
			
			// Verifica se o usuario está na lista
			index = Usuario.indiceEmLista(ativ.getProfissionais(), ra);
			
			// Se encontrado
			if(index != -1) {

				System.out.println("\nDeseja modificar a tarefa do seguinte profissional?");
				
				System.out.println("\n----------------------------------------------");
				System.out.println("Nome: " + ativ.getProfissionais().get(index).getNome());
				System.out.println("Tarefa: " + ativ.getTarefas().get(index));
				System.out.println("----------------------------------------------\n");
				
				System.out.println("(1) Confirmar e modificar");
				System.out.println("(2) Cancelar e voltar");
				
				switch(leOpcao(new int[] {1,2})) {
				case 1:
					substituirTarefa(ativ, index);
					return;
				case 2:
					System.out.println("\nOperação cancelada!");
					return;
				}
			} else {
				System.out.println("\nUsuário não encontrado!");					
				continue;
			}
		}
	}
	
	
	
	// Substitui a tarefa que um profissional já possui em uma atividade
	private static void substituirTarefa(Atividade ativ, int index) {
		System.out.println("\n== Substituir Tarefa ==");
		
		String tarefa;
		
		System.out.println("Nova tarefa: ");
		tarefa = leLinha();
		
		System.out.println("\nDeseja substituir a tarefa?");
		
		System.out.println("\n----------------------------------------------");
		System.out.println("Tarefa antiga: " + ativ.getTarefas().get(index));
		System.out.println("Nova tarefa: " + tarefa);
		System.out.println("----------------------------------------------\n");
		
		System.out.println("(1) Confirmar e substituir");
		System.out.println("(2) Cancelar e voltar");
		
		switch(leOpcao(new int[] {1,2})) {
		case 1:
			ativ.getTarefas().set(index, tarefa);
			return;
		case 2:
			System.out.println("\nOperação cancelada!");
			return;
		}
	
	}
	
	///////////////////////////////////////////////
	// Telas de Relatórios e Subtelas
	///////////////////////////////////////////////
	
	private static void telaRelatorios() {
		while(true) {
			// Imprime mensagens do menu
			System.out.println("\n== Relatórios ==");
			System.out.println("(1) Projetos");
			System.out.println("(2) Atividades");
			System.out.println("(3) Voltar");
							
			switch(leOpcao(new int[]{1,2,3})) {
			case 1:
				relatorioDeProjetos();
				break;
			case 2:
				relatorioDeAtividades();
				break;
			case 3:
				System.out.println("");
				// Encerra o método
				return;
			}
		}
	}
	

	
	// Exibe relatórios de todos os projetos
	
	private static void relatorioDeProjetos() {
		if(projetos.size() == 0) {
			System.out.println("Não há projetos para exibir relatórios!\n");
			return;
		}
		
		System.out.println("\n== Relatório de atividades");
		
		System.out.println("----------------------------------------------");
		for(Projeto p : projetos) {
			p.print("", "----------------------------------------------", false);
		}
		
		return;
	}
	
	
	
	// Exibe relatórios de todos as atividades
	
	private static void relatorioDeAtividades() {
		if(atividades.size() == 0) {
			System.out.println("Não há atividades para exibir relatórios!\n");
			return;
		}
		
		System.out.println("\n== Relatório de atividades");
		
		System.out.println("----------------------------------------------");
		for(Atividade a : atividades) {
			a.print("", "----------------------------------------------", false);
		}
		
		return;
	}
	
	
	
	/////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////
	// Função principal
	/////////////////////////////////////////////////////////////////
	public static void main(String [] args) {
		while(true) {
			switch(menuPrincipal()) {
			case 1:
				telaProjetos();
				break;
			case 2:
				telaUsuarios();
				break;
			case 3:
				telaAtividades();
				break;
			case 4:
				telaRelatorios();
				break;
			case 5:
				// Encerra o método
				return;	
			}
		}
	}
}

