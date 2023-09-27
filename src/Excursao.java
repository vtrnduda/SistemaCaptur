import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Locale;

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
            if (r.equals(nome)) throw new Exception("Este nome já foi cadastrado.");
        }
        String reserva = String.format("%s/%s", cpf, nome);
        lista_reservas.add(reserva);
    }

//    OK
    public void cancelarReserva(String cpf, String nome) throws Exception {
        String reserva = String.format("%s/%s", cpf, nome);

        for (String r : lista_reservas) {
        	if(r.equals(reserva)) {
        		lista_reservas.remove(reserva);
        		return;
        	}
        }
        throw new Exception("Não existe reserva na lista com esse cpf/nome");
    }
        
//    CHECKKKKK
    public void cancelarReserva(String cpf) throws Exception {
        boolean encontrouReserva = false;
        Iterator<String> iterator = lista_reservas.iterator();

        while (iterator.hasNext()) {
            String reserva = iterator.next();
            String digitos = reserva.split("/")[0];
            if (digitos.equals(cpf)) {
                iterator.remove(); // Remove o elemento de forma segura
                encontrouReserva = true;
            }
        }
        if (!encontrouReserva)
            throw new Exception("Não existe este cpf na lista de reservas.");
    }

    //Retorna as reservas que contém os dígitos do CPF ou nome passado como parâmetro (ou retorna todas as reservas caso dígitos seja vazio)
//    TODO Verificar com o professor se podemos criar um unico método p/ realizar essa busca
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
    public double calcularValorTotal(){
        return preco * lista_reservas.size();
    }

    //Gravar no arquivo “codigo.txt” o preço, max e as reservas
    public void salvar() {
        // P/ garantir que o preço seja salvo com o separador "." e não a ","
        Locale.setDefault(Locale.US);
        try {
            String caminho = String.format("./%d.txt", codigo);
            File file = new File(new File(caminho).getCanonicalPath());
            FileWriter arquivo = new FileWriter(file, false);

            arquivo.write(String.format("preco;%.2f%nmax;%d%n", preco, max));
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
            String caminho = String.format("./%d.txt", this.codigo);
            File file = new File(new File(caminho).getCanonicalPath());
            Scanner arquivo = new Scanner(file);
            this.preco = Double.parseDouble(arquivo.nextLine().replace("preco;", ""));
            this.max = Integer.parseInt(arquivo.nextLine().replace("max;",""));
            // Devido a formatação do arquivo salvo
            arquivo.nextLine();
            String linha;
            while (arquivo.hasNextLine()) {
                linha = arquivo.nextLine().replace(";", "/");
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
