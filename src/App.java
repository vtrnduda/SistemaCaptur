import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class App {

	private JFrame frmSistemaCaptur;

	private Excursao excursao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmSistemaCaptur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaCaptur = new JFrame();
		frmSistemaCaptur.setTitle("Sistema Captur");
		frmSistemaCaptur.setBounds(100, 100, 484, 301);
		frmSistemaCaptur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaCaptur.getContentPane().setLayout(null);

		JButton criarExcursaoBtn = new JButton("Criar uma Excursão");
		criarExcursaoBtn.setBounds(10, 11, 156, 23);
		frmSistemaCaptur.getContentPane().add(criarExcursaoBtn);

		JButton recuperarExcursaoBtn = new JButton("Recuperar uma excursão existente");
		recuperarExcursaoBtn.setBounds(176, 11, 258, 23);
		frmSistemaCaptur.getContentPane().add(recuperarExcursaoBtn);

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 45, 209, 188);
		frmSistemaCaptur.getContentPane().add(textArea);

		JLabel codTitleLabel = new JLabel("Cód. da excursão: ");
		codTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		codTitleLabel.setBounds(250, 55, 111, 23);
		frmSistemaCaptur.getContentPane().add(codTitleLabel);

		JLabel codigoLabel = new JLabel("null");
		codigoLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		codigoLabel.setBounds(371, 59, 46, 14);
		frmSistemaCaptur.getContentPane().add(codigoLabel);

		JButton criarReservaBtn = new JButton("Criar Reserva");
		criarReservaBtn.setEnabled(false);
		criarReservaBtn.setBounds(229, 90, 199, 23);
		frmSistemaCaptur.getContentPane().add(criarReservaBtn);

		JButton cancelarIndividualBtn = new JButton("Cancelar Reserva Individual");
		cancelarIndividualBtn.setEnabled(false);
		cancelarIndividualBtn.setBounds(229, 120, 199, 23);
		frmSistemaCaptur.getContentPane().add(cancelarIndividualBtn);

		JButton cancelarGrupoBtn = new JButton("Cancelar Reserva em Grupo");
		cancelarGrupoBtn.setEnabled(false);

		cancelarGrupoBtn.setBounds(229, 150, 199, 23);
		frmSistemaCaptur.getContentPane().add(cancelarGrupoBtn);

		JButton listarCpfBtn = new JButton("Listar Reservas por CPF");
		listarCpfBtn.setEnabled(false);

		listarCpfBtn.setBounds(229, 180, 199, 23);
		frmSistemaCaptur.getContentPane().add(listarCpfBtn);

		JLabel valorTotalLabel = new JLabel("0,00");
		valorTotalLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		valorTotalLabel.setBounds(137, 236, 46, 14);
		frmSistemaCaptur.getContentPane().add(valorTotalLabel);

		JLabel totalLabel = new JLabel("Total: ");
		totalLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		totalLabel.setBounds(49, 236, 46, 14);
		frmSistemaCaptur.getContentPane().add(totalLabel);

		JButton listarNomeBtn = new JButton("Listar Reservas por Nome");
		listarNomeBtn.setEnabled(false);

		listarNomeBtn.setBounds(229, 210, 199, 23);
		frmSistemaCaptur.getContentPane().add(listarNomeBtn);

		// LISTENERS

		criarExcursaoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código: "));
					double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço: "));
					int max = Integer.parseInt(JOptionPane.showInputDialog("Digite o número máximo de reservas: "));

					excursao = new Excursao(codigo, preco, max);
//					Atualiza o visualizador do codigo
					codigoLabel.setText(String.valueOf(codigo));

//					Libera o menu de opções
					Utils.habilitarBotoes(criarReservaBtn, cancelarIndividualBtn, cancelarGrupoBtn, listarCpfBtn,
							listarNomeBtn);

//					Adiciona as excursões a textArea
					Utils.atualizarReservasTextArea(excursao, textArea);
					// ArrayList<String> listaDeReservas = excursao.listarReservasporCpf("");
					// String reservas = "";
					// for (String reserva : listaDeReservas) {
					// reservas += reserva.replace(";", "\t") + "\n";
					// }
					// textArea.setText(reservas);

//					Valor total
					Utils.atualizarValorTotal(excursao, valorTotalLabel);
					// double total = excursao.calcularValorTotal();
					// valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));

//					EXCURSAO CRIADA
//					JOptionPane.showMessageDialog(null, excursao, "Informações", JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception er) {
					JOptionPane.showMessageDialog(null, er.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		recuperarExcursaoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código da excursão: "));
					excursao = new Excursao(codigo);
					excursao.carregar();

//					Atualiza o visualizador do codigo
					codigoLabel.setText(String.valueOf(codigo));

//					Libera o menu de opções
					Utils.habilitarBotoes(criarReservaBtn, cancelarIndividualBtn, cancelarGrupoBtn, listarCpfBtn,
							listarNomeBtn);

//					Adiciona as reservas no textArea

					// Utils.atualizarReservasTextArea(excursao, textArea);

					Utils.atualizarReservasTextArea(excursao, textArea);

//					Atualiza o valorTotal
					Utils.atualizarValorTotal(excursao, valorTotalLabel);

//					EXCURSAO CRIADA
//					JOptionPane.showMessageDialog(null, excursao, "Informações", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception er) {
					String mensagem = "Não foi possível realizar a operação por causa do input:\n" + er.getMessage();
					JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		criarReservaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cpf = JOptionPane.showInputDialog("Digite o CPF: ");
					String nome = JOptionPane.showInputDialog("Digite o nome: ");
					excursao.criarReserva(cpf, nome);
					excursao.salvar();

//					Atualiza o textArea
					String novaReserva = cpf + "\t" + nome + "\n";
					textArea.append(novaReserva);

//					Atualiza o valorTotal
					Utils.atualizarValorTotal(excursao, valorTotalLabel);

				} catch (Exception er) {
					System.out.println(er.getMessage());
		            JOptionPane.showMessageDialog(null, er.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		cancelarIndividualBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cpf = JOptionPane.showInputDialog("Digite o CPF: ");
					String nome = JOptionPane.showInputDialog("Digite o nome: ");
					excursao.cancelarReserva(cpf, nome);
					excursao.salvar();

//				Atualiza o textArea
					String reserva = cpf + "\t" + nome + "\n";
					textArea.setText(textArea.getText().replace(reserva, ""));

//				Valor total
					Utils.atualizarValorTotal(excursao, valorTotalLabel);

				} catch (Exception er) {
					System.out.println(er.getMessage());
				}

			}
		});

		cancelarGrupoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String cpf = JOptionPane.showInputDialog("Digite o CPF: ");
					excursao.cancelarReserva(cpf);
					excursao.salvar();

					ArrayList<String> listaDeReservas = excursao.listarReservasporCpf("");
					String reservas = "";
					for (String reserva : listaDeReservas) {
						reservas += reserva.replace("/", "\t") + "\n";
					}
					textArea.setText(reservas);

//					Valor total
					Utils.atualizarValorTotal(excursao, valorTotalLabel);

				} catch (Exception er) {

				}

			}
		});

		listarCpfBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = JOptionPane.showInputDialog("Digite o CPF: ");

				ArrayList<String> reservasFiltradas = excursao.listarReservasporCpf(cpf);
				String reservas = "";
				for (String reserva : reservasFiltradas) {
					reservas += reserva.replace("/", "\t") + "\n";
				}
				textArea.setText(reservas);

//				Valor total
				double total = excursao.preco * reservasFiltradas.size();
				valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
				
			}
		});

		listarNomeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = JOptionPane.showInputDialog("Digite o nome: ");
				

				ArrayList<String> reservasFiltradas = excursao.listarReservasPorNome(nome);
				
				String reservas = "";
				for (String reserva : reservasFiltradas) {
					reservas += reserva.replace("/", "\t") + "\n";
				}
				textArea.setText(reservas);
				
//				Valor total
				double total = excursao.preco * reservasFiltradas.size();
				valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
				//Utils.atualizarValorTotal(excursao, valorTotalLabel);
			}
		});

	}
}
