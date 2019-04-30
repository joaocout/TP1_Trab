package locadora;

public class Main {
	public static void main(String[] args){
		Plataforma ps4 = new Plataforma("PS4",1.5f);
		Jogo gow = new Jogo("God of War", 2.0f, 10, ps4);
		Locacao loc0 = new Locacao(gow);
		Cliente joao = new Cliente("Joao","1234567","09123182","joao@mail.com","99883229");
		joao.addLocacao(loc0);
		try{
			loc0.alugar();
		} catch(Exception ex){
			//ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		try{
			System.out.println(loc0.toString());
			loc0.devolver();
			System.out.println(loc0.toString());
			loc0.devolver();
		} catch(Exception ex){
			//ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		System.out.println(joao.Divida());
		
		System.exit(0);
	}
}
