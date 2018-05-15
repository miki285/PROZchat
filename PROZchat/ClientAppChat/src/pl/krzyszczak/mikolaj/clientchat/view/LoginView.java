/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import pl.krzyszczak.mikolaj.clientchat.appEvent.SendToServerEvent;
import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.appEvent.CreateAccountAppClientEvent;
import pl.krzyszczak.mikolaj.serverchat.appEvent.LoginAppClientEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserPassword;

/**
 * @author Miko³aj
 *
 */
public class LoginView

{
	/** referancja na kolejkê na eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;
	/** Ramka aplikacji */
	private JFrame frame;
	/** Przycisk logowania */
	private JButton loginButton;
	/** Przycisk tworzenia konta */
	private JButton createAccountButton;
	/** pole tekstowe do loginu */
	private JTextField loginTextField;
	/** pole tekstowe do podanie has³a */
	private JPasswordField passwordLoginTextField;
	/** Etykieta do opisuj¹ca pole loginTextField */
	private JLabel loginTextLabel;
	/** Etykieta do opisuj¹ca pole passwordloginTextField */
	private JLabel passwordLoginTextLabel;
	/** pole tekstowe do loginu */
	private JTextField createAccountTextField;
	/** pole tekstowe do podanie has³a */
	private JPasswordField passwordCreateAccountTextField;
	/** Etykieta do opisuj¹ca pole createAccountTextField */
	private JLabel createAccountTextLabel;
	/** Etykieta do opisuj¹ca pole passwordCreateAccountTextField */
	private JLabel passwordCreateAccountTextLabel;
	/** JTabbedPane logowania */
	private JTabbedPane tabbedPane;
	/** Panel do logowania */
	private JPanel logPanel;
	/** Panel do logowania */
	private JPanel creatPanel;

	public LoginView(final BlockingQueue<ApplicationEvent> eventQueue)
	{
		this.eventQueue = eventQueue;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Metoda zawieraj¹ca inicjalizacjê wszystkich elementów zwi¹zanych z GUI
	 * LoginView
	 */
	private void initialize()
	{
		//

		// Tworzê ramkê aplikacji
		frame = new JFrame("Chat Client");
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Tworze JTabbedPane
		tabbedPane = new JTabbedPane();
		tabbedPane.setSize(300, 300);

		// Tworzê JPanel
		logPanel = new JPanel();
		logPanel.setSize(300, 300);
		logPanel.setLayout(null);
		creatPanel = new JPanel();
		creatPanel.setSize(300, 300);
		creatPanel.setLayout(null);

		// Zebrane wszystkie obiekty

		loginTextField = new JTextField();
		createAccountTextField = new JTextField();

		loginTextLabel = new JLabel("Twoj numer id");
		passwordLoginTextLabel = new JLabel("Twoje haslo");
		createAccountTextLabel = new JLabel("Twoja nazwa");
		passwordCreateAccountTextLabel = new JLabel("Twoje haslo");

		passwordLoginTextField = new JPasswordField();
		passwordCreateAccountTextField = new JPasswordField();

		// Przyciski do logowania

		loginButton = new JButton("Zaloguj");
		loginButton.setBounds(90, 170, 120, 25);
		loginButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String toNumber = loginTextField.getText();
				
				
				if (toNumber.length()<1)
				{
					displayInfoMessage("Numer ID nieprawidlowy");
				} else
				{
					try{
					int id = Integer.parseInt(toNumber);
					System.out.println(toNumber + id);
					int hashPwd = (String
							.valueOf(passwordLoginTextField
									.getPassword())).hashCode();
					/** TODO*/
					System.out.println(hashPwd);
					eventQueue.offer(new SendToServerEvent(new LoginAppClientEvent(new UserId(id),
							new UserPassword(hashPwd))));
					}
					catch(NumberFormatException ex)
					{
						displayInfoMessage("Numer ID nieprawidlowy");
					}
				}
			}
		});

		// Przycisk do tworzenia nowego konta
		createAccountButton = new JButton("Nowe konto");
		createAccountButton.setBounds(90, 170, 120, 25);
		createAccountButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name = createAccountTextField.getText();
				if (name.length() > 20)
				{
					displayInfoMessage("Nick za dlugi");
				} else if (name.length() < 2)
				{
					displayInfoMessage("Nick za krotki");
				} else
				{
					int hashPwd = (String
							.valueOf(passwordCreateAccountTextField
									.getPassword())).hashCode();
					/** TODO*/
					System.out.println(hashPwd);
					if (hashPwd == 0)
						displayInfoMessage("Podaj haslo");
					eventQueue.offer(new SendToServerEvent( new CreateAccountAppClientEvent(name,
							new UserPassword(hashPwd))));

				}
			}
		});

		// Login textfield

		loginTextField.setBounds(90, 50, 120, 25);
		loginTextField.setColumns(22);

		// create account textfield

		createAccountTextField.setBounds(90, 50, 120, 25);
		createAccountTextField.setColumns(22);

		// passwordfield

		passwordCreateAccountTextField.setBounds(90, 110, 120, 25);
		passwordLoginTextField.setBounds(90, 110, 120, 25);

		// Labele

		loginTextLabel.setBounds(90, 30, 95, 25);
		passwordLoginTextLabel.setBounds(90, 90, 90, 25);

		createAccountTextLabel.setBounds(90, 30, 120, 25);
		passwordCreateAccountTextLabel.setBounds(90, 90, 90, 25);

		// Panel add
		logPanel.add(loginTextLabel);
		logPanel.add(passwordLoginTextLabel);
		creatPanel.add(createAccountTextLabel);
		creatPanel.add(passwordCreateAccountTextLabel);

		creatPanel.add(createAccountButton);
		logPanel.add(loginButton);

		logPanel.add(passwordLoginTextField);
		creatPanel.add(passwordCreateAccountTextField);

		logPanel.add(loginTextField);
		creatPanel.add(createAccountTextField);

		tabbedPane.addTab("Loguj", logPanel);
		tabbedPane.addTab("Utworz konto", creatPanel);
		frame.getContentPane().add(tabbedPane);
		frame.setResizable(false);
	}

	/**
	 * Metoda wyœwietlaj¹ca komunikaty o bledzie
	 */

	public void displayInfoMessage(final String message)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				JOptionPane.showMessageDialog(frame, message);

			}
		});
	}
	
	/**
	 * Niszczenie okna logowania - potrzebne jest tylko raz na poczatku
	 * rozgrywki
	 */
	public void disposeLoginView()
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				frame.setVisible(false);
				frame.dispose();
			}

		});
	}
}
