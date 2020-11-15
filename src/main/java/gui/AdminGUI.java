package gui;

/**
 * @author Software Engineering teachers
 */

import javax.swing.*;

import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame {

	private static BLFacade appFacadeInterface;
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton btnDeletefee=null;
	private JButton btnNewButton2=null;
	private JButton btnNewButton1=null;
	private JButton btnNewButton=null;
	private JButton btnDeleteQuestion;
	private JButton btnDeleteEvent;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton1;
	private JRadioButton rdbtnNewRadioButton2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnLogout;
	private JLabel lblAdmin;
	private JButton btnProfit=null;
	private JButton btnDeleteUser;
	private JButton btnGertaeraBikoiztu;
	private JLabel label;

	private static final String ETIQUETA = "Etiqueta";
	private static final String LOCALE = "Locale: ";
	private static final String TAHOMA = "Tahoma";

	private Image cesped;
	
	/**
	 * This is the default constructor
	 */
	public AdminGUI() {
		super();
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(887, 725);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle(ETIQUETA).getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnLogout());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getPanel());
			jContentPane.add(getLblAdmin());

			btnNewButton=new JButton();
			btnNewButton.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			btnNewButton.setText(ResourceBundle.getBundle(ETIQUETA).getString("CreateFee"));  //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					CreateFeesGUI createFees = new CreateFeesGUI();
					createFees.setVisible(true);

				}
			});
			btnNewButton.setBounds(12, 393, 370, 60);
			jContentPane.add(btnNewButton);

			btnNewButton1=new JButton();
			btnNewButton1.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			btnNewButton1.setText(ResourceBundle.getBundle(ETIQUETA).getString("CreateWorker"));//$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					CreateWorkerGUI createWorker = new CreateWorkerGUI();
					createWorker.setVisible(true);

				}
			});
			btnNewButton1.setBounds(12, 466, 370, 55);
			jContentPane.add(btnNewButton1);

			btnNewButton2=new JButton();
			btnNewButton2.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			btnNewButton2.setText(ResourceBundle.getBundle(ETIQUETA).getString("PutResult"));  //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					PutResultGUI putResult = new PutResultGUI();
					putResult.setVisible(true);

				}
			});
			btnNewButton2.setBounds(12, 534, 858, 60);
			jContentPane.add(btnNewButton2);

			btnDeletefee=new JButton();
			btnDeletefee.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			btnDeletefee.setText(ResourceBundle.getBundle(ETIQUETA).getString("DeleteFee")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDeletefee.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					DeleteFeesGUI deleteFees = new DeleteFeesGUI();
					deleteFees.setVisible(true);

				}
			});
			btnDeletefee.setBounds(500, 393, 370, 60);
			jContentPane.add(btnDeletefee);
			jContentPane.add(getBtnProfit());

			btnDeleteQuestion = new JButton(ResourceBundle.getBundle(ETIQUETA).getString("deleteQuestion")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDeleteQuestion.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			btnDeleteQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DeleteQuestionsGUI delquestion = new DeleteQuestionsGUI();
					delquestion.setVisible(true);
				}
			});
			btnDeleteQuestion.setBounds(500, 320, 370, 60);
			jContentPane.add(btnDeleteQuestion);
			jContentPane.add(getBtnDeleteUser());

			btnDeleteEvent = new JButton(ResourceBundle.getBundle(ETIQUETA).getString("deleteEvent")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDeleteEvent.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			btnDeleteEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DeleteEventsGUI delevent = new DeleteEventsGUI();
					delevent.setVisible(true);
				}
			});
			btnDeleteEvent.setBounds(500, 249, 370, 60);
			jContentPane.add(btnDeleteEvent);
			jContentPane.add(getBtnGertaeraBikoiztu());
			jContentPane.add(getLabel());
		}
		return jContentPane;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			jButtonCreateQuery.setBounds(12, 320, 370, 60);
			jButtonCreateQuery.setText(ResourceBundle.getBundle(ETIQUETA).getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					CreateQuestionGUI createQuestion = new CreateQuestionGUI();
					createQuestion.setVisible(true);

				}
			});
		}
		return jButtonCreateQuery;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			jButtonQueryQueries.setBounds(12, 174, 858, 60);
			jButtonQueryQueries.setText(ResourceBundle.getBundle(ETIQUETA).getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					FindQuestionsGUI findQuestions = new FindQuestionsGUI();
					findQuestions.setVisible(true);

				}
			});
		}
		return jButtonQueryQueries;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setFont(new Font(TAHOMA, Font.PLAIN, 20));
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println(LOCALE+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton1() {
		if (rdbtnNewRadioButton1 == null) {
			rdbtnNewRadioButton1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton1.setFont(new Font(TAHOMA, Font.PLAIN, 20));
			rdbtnNewRadioButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println(LOCALE+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton1);
		}
		return rdbtnNewRadioButton1;
	}
	private JRadioButton getRdbtnNewRadioButton2() {
		if (rdbtnNewRadioButton2 == null) {
			rdbtnNewRadioButton2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton2.setFont(new Font(TAHOMA, Font.PLAIN, 20));
			rdbtnNewRadioButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println(LOCALE+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton2);
		}
		return rdbtnNewRadioButton2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(210, 634, 458, 43);
			panel.add(getRdbtnNewRadioButton1());
			panel.add(getRdbtnNewRadioButton2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		jButtonQueryQueries.setText(ResourceBundle.getBundle(ETIQUETA).getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle(ETIQUETA).getString("CreateQuery"));
		btnLogout.setText(ResourceBundle.getBundle(ETIQUETA).getString("Logout"));
		lblAdmin.setText(ResourceBundle.getBundle(ETIQUETA).getString("Admin"));
		btnDeletefee.setText(ResourceBundle.getBundle(ETIQUETA).getString("DeleteFee"));
		btnNewButton2.setText(ResourceBundle.getBundle(ETIQUETA).getString("PutResult"));
		btnNewButton1.setText(ResourceBundle.getBundle(ETIQUETA).getString("CreateWorker"));
		btnNewButton.setText(ResourceBundle.getBundle(ETIQUETA).getString("CreateFee"));
		btnProfit.setText(ResourceBundle.getBundle(ETIQUETA).getString("Profit"));
		btnDeleteUser.setText(ResourceBundle.getBundle(ETIQUETA).getString("DeleteUser"));
		btnDeleteQuestion.setText(ResourceBundle.getBundle(ETIQUETA).getString("deleteQuestion"));
		btnDeleteEvent.setText(ResourceBundle.getBundle(ETIQUETA).getString("deleteEvent"));
		btnGertaeraBikoiztu.setText(ResourceBundle.getBundle(ETIQUETA).getString("GertaeraBikoiztu"));

		this.setTitle(ResourceBundle.getBundle(ETIQUETA).getString("MainTitle"));
	}

	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton(ResourceBundle.getBundle(ETIQUETA).getString("Logout")); //$NON-NLS-1$ //$NON-NLS-2$
			btnLogout.setFont(new Font(TAHOMA, Font.PLAIN, 20));
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//					----------------------------------------------------
					UnRegisteredGUI hasieraPantaila = new UnRegisteredGUI();
					hasieraPantaila.setVisible(true);
					dispose();
					//					----------------------------------------------------
				}
			});
			btnLogout.setBackground(Color.ORANGE);
			btnLogout.setForeground(Color.BLUE);
			btnLogout.setBounds(650, 84, 220, 60);
		}
		return btnLogout;
	}

	private JLabel getLblAdmin() {
		if (lblAdmin == null) {
			lblAdmin = new JLabel(ResourceBundle.getBundle(ETIQUETA).getString("Admin")); //$NON-NLS-1$ //$NON-NLS-2$
			lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdmin.setForeground(Color.PINK);
			lblAdmin.setFont(new Font(TAHOMA, Font.BOLD, 30));
			lblAdmin.setBounds(12, 0, 858, 43);
		}
		return lblAdmin;
	}
	private JButton getBtnProfit() {
		if (btnProfit == null) {
			btnProfit = new JButton();
			btnProfit.setBackground(Color.ORANGE);
			btnProfit.setForeground(Color.BLUE);
			btnProfit.setFont(new Font(TAHOMA, Font.PLAIN, 20));
			btnProfit.setText(ResourceBundle.getBundle(ETIQUETA).getString("Profit")); //$NON-NLS-1$ //$NON-NLS-2$
			btnProfit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle(ETIQUETA).getString("SistemarenIrabaziak") + "  " + Float.toString(getBusinessLogic().getIrabazia())+"\u20ac", "Bets",JOptionPane.INFORMATION_MESSAGE);
				}
			});
			btnProfit.setBounds(12, 86, 220, 60);
		}
		return btnProfit;
	}
	private JButton getBtnDeleteUser() {
		if (btnDeleteUser == null) {
			btnDeleteUser = new JButton(ResourceBundle.getBundle(ETIQUETA).getString("DeleteUser")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDeleteUser.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			btnDeleteUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DeleteErabiltzaileaGUI deleteUser = new DeleteErabiltzaileaGUI();
					deleteUser.setVisible(true);
					deleteUser.setLocationRelativeTo(null);
				}
			});
			btnDeleteUser.setBounds(500, 466, 370, 55);

		}
		return btnDeleteUser;
	}
	private JButton getBtnGertaeraBikoiztu() {
		if (btnGertaeraBikoiztu == null) {
			btnGertaeraBikoiztu = new JButton(ResourceBundle.getBundle(ETIQUETA).getString("GertaeraBikoiztu")); //$NON-NLS-1$ //$NON-NLS-2$
			btnGertaeraBikoiztu.setFont(new Font(TAHOMA, Font.PLAIN, 24));
			btnGertaeraBikoiztu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GertaeraBatBikoiztuGUI gertaeraBi = new GertaeraBatBikoiztuGUI();
					gertaeraBi.setVisible(true);
				}
			});
			btnGertaeraBikoiztu.setBounds(12, 247, 370, 60);
		}
		return btnGertaeraBikoiztu;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
			label.setBounds(0, 0, 900, 725);

			cesped = new ImageIcon(this.getClass().getResource("/Thanos.jpg")).getImage().getScaledInstance(910, 725, Image.SCALE_SMOOTH);
			label.setIcon(new ImageIcon(cesped));
		}
		return label;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

