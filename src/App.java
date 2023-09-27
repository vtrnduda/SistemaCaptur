import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;

public class App {
	

	private JFrame frame;
	
	private Excursao excursao;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
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
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel rightPanel = new JPanel();
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JPanel codPanel = new JPanel();
		rightPanel.add(codPanel);
		
		JLabel codTitleLabel = new JLabel("Cód. da excursão:");
		codTitleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JLabel codigoLabel = new JLabel("null");
		codigoLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		codPanel.setLayout(new MigLayout("", "[150px]", "[145px]"));
		codPanel.add(codTitleLabel, "cell 0 0,growx,aligny center");
		codPanel.add(codigoLabel, "cell 0 0,grow");
		
		JButton criarReservaBtn = new JButton("Criar reserva");
		criarReservaBtn.setEnabled(false);
		rightPanel.add(criarReservaBtn);
		
		JButton cancelarIndividualBtn = new JButton("Cancelar reserva individual");
		cancelarIndividualBtn.setEnabled(false);
		rightPanel.add(cancelarIndividualBtn);
		
		JButton cancelarGrupoBtn = new JButton("Cancelar reserva em grupo");
		cancelarGrupoBtn.setEnabled(false);
		rightPanel.add(cancelarGrupoBtn);
		
		JButton listarCpfBtn = new JButton("Listar reservas por CPF");
		listarCpfBtn.setEnabled(false);
		rightPanel.add(listarCpfBtn);
		
		JButton listarNomeBtn = new JButton("Listar reservas por nome");
		listarNomeBtn.setEnabled(false);
		rightPanel.add(listarNomeBtn);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		rightPanel.add(verticalStrut_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		rightPanel.add(verticalStrut);
		
		JPanel centerPanel = new JPanel();
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		centerPanel.add(textArea);
		
		JPanel panel = new JPanel();
		centerPanel.add(panel);
		
		JLabel totalLabel = new JLabel("Total: ");
		panel.add(totalLabel);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		JLabel valorTotalLabel = new JLabel("0,00");
		valorTotalLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		panel.add(valorTotalLabel);
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JButton criarExcursaoBtn = new JButton("Criar uma excursão");
		toolBar.add(criarExcursaoBtn);
		
//		CRIAR EXCURSAO COD + CARREGAR()
		JButton recuperarExcursaoBtn = new JButton("Recuperar uma excursão existente");
		toolBar.add(recuperarExcursaoBtn);
		
		
//		LISTENERS
		
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
					criarReservaBtn.setEnabled(true);
					cancelarIndividualBtn.setEnabled(true);
					cancelarGrupoBtn.setEnabled(true);
					listarCpfBtn.setEnabled(true);
					listarNomeBtn.setEnabled(true);
					
					
//					Adiciona as excursões a textArea
					ArrayList<String> listaDeReservas = excursao.listarReservasporCpf("");
					String reservas = "";
					for(String reserva : listaDeReservas) {
						reservas += reserva.replace(";", "\t") + "\n";
					}
					textArea.setText(reservas);
					
//					Valor total
					double total = excursao.calcularValorTotal();
					valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
					
//					EXCURSAO CRIADA
//					JOptionPane.showMessageDialog(null, excursao, "Informações", JOptionPane.INFORMATION_MESSAGE);

				} catch(Exception er) {
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
					criarReservaBtn.setEnabled(true);
					cancelarIndividualBtn.setEnabled(true);
					cancelarGrupoBtn.setEnabled(true);
					listarCpfBtn.setEnabled(true);
					listarNomeBtn.setEnabled(true);
					
//					Adiciona as reservas no textArea
					ArrayList<String> listaDeReservas = excursao.listarReservasporCpf("");
					String reservas = "";
					for(String reserva : listaDeReservas) {
						reservas += reserva.replace("/", "\t") + "\n";
					}
					textArea.setText(reservas);
					
//					Atualiza o valorTotal
					double total = excursao.calcularValorTotal();
					valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
					
//					EXCURSAO CRIADA
//					JOptionPane.showMessageDialog(null, excursao, "Informações", JOptionPane.INFORMATION_MESSAGE);
				} catch(Exception er) {
					String mensagem = "Não foi possível realizar a operação por causa do input:\n" + er.getMessage();
					JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
//		CHECK: CLASSE CRIAR RESERVA !!!
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
					double total = excursao.calcularValorTotal();
					valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
					
				} catch(Exception er) {
					System.out.println(er.getMessage());
				}
			}
		});
//		CHECK: CANCELAR RESERVA !!!
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
				double total = excursao.calcularValorTotal();
				valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
				
				
				
				} catch(Exception er) {
					System.out.println(er.getMessage());
				}
				
				
			}
		});
		
		cancelarGrupoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String cpf = JOptionPane.showInputDialog("Digite o CPF: ");
					excursao.cancelarReserva(cpf);
					
					ArrayList<String> listaDeReservas = excursao.listarReservasporCpf("");
					String reservas = "";
					for(String reserva : listaDeReservas) {
						reservas += reserva.replace("/", "\t") + "\n";
					}
					textArea.setText(reservas);
					
					
//					Valor total
					double total = excursao.calcularValorTotal();
					valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
					
					
					
				} catch(Exception er) {
					
				}
				
				
			}
		});

		listarCpfBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpf = JOptionPane.showInputDialog("Digite o CPF: ");

				ArrayList<String> reservasFiltradas = excursao.listarReservasporCpf(cpf);
				String reservas = "";
				for(String reserva : reservasFiltradas) {
					reservas += reserva.replace("/", "\t") + "\n";
				}
				textArea.setText(reservas);
				
				double total = reservasFiltradas.size() * excursao.preco;
				valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
			}
		});
		
		listarNomeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = JOptionPane.showInputDialog("Digite o nome: ");

				ArrayList<String> reservasFiltradas = excursao.listarReservasPorNome(nome);
				String reservas = "";
				for(String reserva : reservasFiltradas) {
					reservas += reserva.replace("/", "\t") + "\n";
				}
				textArea.setText(reservas);
				
				double total = reservasFiltradas.size() * excursao.preco;
				valorTotalLabel.setText(String.format("%.2f", total).replace(".", ","));
			}
		});
		
	}

}
