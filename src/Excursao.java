import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Excursao {
    int codigo;
    double preco;
    int max;
    ArrayList<String> lista_reservas;

    public Excursao(int codigo, double preco, int max) throws Exception {
        this.codigo = codigo;
        this.preco = preco;
        this.max = max;
        this.lista_reservas = new ArrayList<>();

        if (this.codigo <= 0) throw new Exception("Código da excursão inválido.");
        if (this.preco <= 0) throw new Exception("Preço inválido.");
        if (this.max <= 0) throw new Exception("Numero máximo de reservas inválido.");
    }

    public Excursao(int codigo) throws Exception {
        this.codigo = codigo;
        this.lista_reservas = new ArrayList<>();
        if (this.codigo <= 0) throw new Exception("Código da excursão inválido.");
    }

    public void criarReserva(String cpf, String nome) throws Exception{
        if(lista_reservas.size() >= max) throw new Exception("O numero máximo de reservas foi atingido.");
        for (String r: lista_reservas){
            if (r.contains(nome)) throw new Exception("Este nome já foi cadastrado.");
        }
        String reserva = String.format("%s/%s", cpf, nome);
        lista_reservas.add(reserva);
    }

    public void cancelarReserva(String cpf, String nome) throws Exception {
        String reserva = String.format("%s/%s", cpf, nome);
        if(!lista_reservas.contains(reserva)) throw new Exception("Não existe este cpf/nome na lista de reservas.");

        lista_reservas.remove(reserva);
    }
    public void cancelarReserva(String cpf) throws Exception {
        if(!lista_reservas.contains(cpf)) throw new Exception("Não existe este cpf na lista de reservas.");
        for (String reserva : lista_reservas){
            if (lista_reservas.contains(cpf)) {
                lista_reservas.remove(reserva);
            }
        }
    }


    //Retorna as reservas dos cpfs que contém os dígitos (ou retorna todas as reservas caso dígitos seja vazio)
    public ArrayList<String> listarReservasporCpf (String digitos) {
        if (digitos == null || digitos.isBlank()) {
            return lista_reservas;
        }   
        ArrayList<String> reservas_filtradas = new ArrayList<String>();
        for (String reserva : lista_reservas) {
            if (reserva.contains(String.valueOf(digitos))) {
                reservas_filtradas.add(reserva);
            }
        }
        return reservas_filtradas;
    }
    public ArrayList<String> listarReservasPorNome(String nome){
        if (nome == null || nome.isBlank()) {
			return lista_reservas;
		}
        ArrayList<String> reservas_filtradas = new ArrayList<String>();
        for (String reserva : lista_reservas) {
            if (reserva.contains(nome)) {
                reservas_filtradas.add(reserva);
            }
        }
        return reservas_filtradas;
    }

    //Calcular valor total da excursão = preço * qde de reservas
    public Double calcularValorTotal(){
        return preco * lista_reservas.size();
    }

    //Gravar no arquivo “codigo.txt” o preço, max e as reservas
    public void salvar() {
        try {
            String caminho = String.format("./%d.txt", codigo);
            File file = new File(new File(caminho).getCanonicalPath());
            FileWriter arquivo = new FileWriter(file, false);
            arquivo.write(String.format("%d%n", max));
            arquivo.write(String.format("%.2f%nmax", preco));
            arquivo.write(System.lineSeparator());
            for (String reserva : lista_reservas) {
                arquivo.write(reserva.replace("/", ";"));
                arquivo.write(System.lineSeparator());
            }
            arquivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   

    //Ler do arquivo “codigo.txt” o preço, max e as reservas
    public void carregar(){
    	try {
    		String linha;
    	    String caminho = String.format("./%d.txt", this.codigo);
    	    File file = new File(new File(caminho).getCanonicalPath());
    	    Scanner arquivo = new Scanner(file);
    	    this.max = Integer.parseInt(arquivo.nextLine());
    	    this.preco = Double.parseDouble(arquivo.nextLine());
    	    while (arquivo.hasNextLine()) {
    			linha = arquivo.nextLine();
    	        this.lista_reservas.add(linha);
    	    }
    	    arquivo.close();
    	}
	    catch (Exception e) {
	    	//DEFINIR ONDE VAI SAIR A MENSAGEM DE ERRO!! (JOptionPane ou whatever)
	    	System.out.println(e.getMessage());
	    }
	    

            

    }


    @Override
	public String toString() {
		return "Excursao [codigo=" + codigo + ", preco=" + preco + ", max=" + max + ", lista_reservas=" + lista_reservas
				+ "]";
	}
    
}
