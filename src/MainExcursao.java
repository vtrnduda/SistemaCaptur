
public class MainExcursao {

	public static void main(String[] args) {
		try {
			Excursao e = new Excursao(1);
			e.carregar();
			System.out.println(e);
			e.cancelarReserva("444");
			System.out.println(e);

		} catch(Exception er) {
			System.out.println(er.getMessage());
			
		}


	}

}
