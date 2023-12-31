
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Utils {

	public static void atualizarReservasTextArea(Excursao excursao, JTextArea textArea) throws Exception {
		try {
			ArrayList<String> listaDeReservas = excursao.listarReservasporCpf("");
			String reservas = "";
			for (String reserva : listaDeReservas) {
				reservas += reserva.replace("/", "\t") + "\n";
			}
			textArea.setText(reservas);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void habilitarBotoes(JButton criarReservaBtn, JButton cancelarIndividualBtn, JButton cancelarGrupoBtn,
			JButton listarCpfBtn, JButton listarNomeBtn) {

		criarReservaBtn.setEnabled(true);
		cancelarIndividualBtn.setEnabled(true);
		cancelarGrupoBtn.setEnabled(true);
		listarCpfBtn.setEnabled(true);
		listarNomeBtn.setEnabled(true);

	}
	
	public static void atualizarValorTotal(Excursao excursao, JLabel valorTotalLabel) {
        double total = excursao.calcularValorTotal();
        //valorTotalLabel.setText(String.valueOf(excursao.lista_reservas.size()));
        valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
    }
}
