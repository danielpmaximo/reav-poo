================ Exceptions ================

-IOException:
	String Gerenciador: leLinha();

-NumberFormatException:
	int Gerenciador.java: leOpcao(), leInt(int min, int max), leID(String label);

-DateTimeException:
	LocalDateTime leData();
	
================ Padrões ================	

-Move Accumulation to Collecting Parameter:  
    Projeto.java: print()  
    Tecnico.java: print()  
    Atividade.java: print(), printProfissionais()  
    Todos os métodos de prints implementados  

-Extract Method:  
    Gerenciador.java: telaRelatorios(), relatorioDeProjetos(), relatorioDeAtividades(), substituirTarefa(Atividade ativ, int index), 
                      modificarTarefa(Atividade ativ), removerProfissional(Atividade ativ), entre outras.
                      
