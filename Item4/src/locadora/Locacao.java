package locadora;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Locacao{

    private String data_aluguel;// = "0000-00-00";	//formato: 2015-04-12 (yyyy-mm-dd)
    private String data_devolucao;// = "0000-00-00";
    private String hora_aluguel;// = "00:00";	//formato: 23:20 (hh:mm)
    private String hora_devolucao;// = "00:00";
    private double preco_final;
    private boolean finalizada;
    private int protocolo;
    private Jogo game;
    private static int base_protocol = 0;

    public Locacao(Jogo game){
        finalizada = false;
        this.game = game;
    }

    public void setDataAluguel(String data_aluguel){
        this.data_aluguel = data_aluguel;
    }
    public String getDataAluguel(){
        return data_aluguel;
    }

    public void setDataDevolucao(String data_devolucao){
        this.data_devolucao = data_devolucao;
    }
    public String getDataDevolucao(){
        return data_devolucao;
    }

    public void setHoraAluguel(String hora_aluguel){
        this.hora_aluguel = hora_aluguel;
    }
    public String getHoraAluguel(){
        return hora_aluguel;
    }

    public void setHoraDevolucao(String hora_devolucao){
        this.hora_devolucao = hora_devolucao;
    }
    public String getHoraDevolucao(){
        return hora_devolucao;
    }

    public double PrecoFinal() {
    	try {
	        preco_final = game.getPrecoBase();
	        preco_final = preco_final*game.getPlataforma().getCoeficiente();
	        
	        int ano=0, mes=0, dia=0;
	
	        int i = 0;        
	        for(String a: data_aluguel.split("\\-")){
	            if(i==0){
	                ano = Integer.valueOf(a);
	                i++;
	            }
	            else if(i==1){
	                mes = Integer.valueOf(a);
	                i++;
	            }
	            else if(i==2){
	                dia = Integer.valueOf(a);
	                i++;
	            }
	        }
	        LocalDate d_aluguel = LocalDate.of(ano, mes, dia);
	        
	        // Caso queira apenas o valor da divida ate entao (e nao tiver devolvido ainda)
	        if(!this.finalizada){
	        	this.data_devolucao = LocalDate.now().toString();
	        }
	        
	        i = 0;
	        for(String a: data_devolucao.split("\\-")){
	            if(i==0){
	                ano = Integer.valueOf(a);
	                i++;
	            }
	            else if(i==1){
	                mes = Integer.valueOf(a);
	                i++;
	            }
	            else if(i==2){
	                dia = Integer.valueOf(a);
	                i++;
	            }
	        }
	        LocalDate d_devolucao = LocalDate.of(ano, mes, dia);
	        
	        long dias = ChronoUnit.DAYS.between(d_aluguel, d_devolucao);
	
	        if(dias == 0) dias = 1;
	        
	        preco_final = preco_final * dias;
	        return preco_final;
    	} catch(Exception ex){
    		ex.printStackTrace();
    		return 0.0f;
    	}
    }

    public void setFinalizada(boolean finalizada){
        this.finalizada = finalizada;
    }
    public boolean getFinalizada(){
        return finalizada;
    }

    private int generateProtocolo(){
        base_protocol++;
        return base_protocol;
    }

    public void setProtocolo(){
        this.protocolo = generateProtocolo();
    }

    public int getProtocolo(){
        return protocolo;
    }

    public void setJogo(Jogo game){
        this.game = game;
    }
    public Jogo getJogo(){
        return game;
    }

    public void alugar() throws Exception {
	        LocalDate dia = LocalDate.now();
	        LocalTime hora = LocalTime.now().withSecond(0).withNano(0);
	        data_aluguel = dia.toString();
	        hora_aluguel = hora.toString();
    }

    public void devolver() throws Exception {
    	if(!this.finalizada){
	        LocalDate dia = LocalDate.now();
	        LocalTime hora = LocalTime.now().withSecond(0).withNano(0);
	        data_devolucao = dia.toString();
	        hora_devolucao = hora.toString();
	        pagar();
    	} else {
    		throw new Exception("Locacao ja finalizada.");
    	}
    }

    public void pagar(){
        finalizada = true;
    }
    
    public String toString(){
    	String finalizada = this.finalizada ? "Sim" : "Nao";
    	String toret = "Jogo: " + game.getTitulo() + "\nPreco atual: " + PrecoFinal() + "\nFinalizada: " + finalizada + "\n";
    	return toret;
    }
    
}