/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import pl.krzyszczak.mikolaj.clientchat.appEvent.CloseMessageWindowEvent;
import pl.krzyszczak.mikolaj.clientchat.appEvent.SendToServerEvent;
import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.appEvent.MessageAppEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.model.Message;
import pl.krzyszczak.mikolaj.serverchat.sendDummy.SendMessageDummy;

/**
 * @author Miko³aj
 *
 */
public class MessageView
{
	/** referancja na kolejkê na eventy */
	private BlockingQueue<ApplicationEvent> eventQueue;

	/** Ramka aplikacji */
	private JFrame frame;

	/** Obszar gdzie wyœwietlana jest rozmowa pomiedzy u¿ytkownikami */
	private JTextArea usersConversation;

	/** Obszar gdzie u¿ytkownik wpisuje swoj¹ wiadomoœæ */
	private JTextArea usersTextMessagefield;

	/** Przycisk sygnalizuj¹cy wys³anie wiadomoœci */
	private JButton sendButton;

	/** Scrollery poszczególnych obszarów tekstowych */
	private JScrollPane usersConversationScroll;
	private JScrollPane usersTextMessageScroll;

	/** Charakterystyczny numer ID uzytkownika z ktorym piszemy */
	private UserId friendsUserId;

	public MessageView(BlockingQueue<ApplicationEvent> eventQueue,
			UserId friendsUserId)
	{
		this.eventQueue = eventQueue;
		this.friendsUserId = friendsUserId;
		System.out.println(this.friendsUserId.getId());
		initialize();
		frame.setVisible(true);

	}

	/** Metoda inicjalizuj¹ca wszystkie zmienne MessageView */
	private void initialize()
	{
		// Frame
		frame = new JFrame("Rozmowa z uzytkownikiem o ID :"
				+ friendsUserId.getId());
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		usersConversation = new JTextArea();
		// usersConversation.setBounds(15, 15, 500, 250);
		usersConversation.setEditable(false);

		usersConversationScroll = new JScrollPane(usersConversation);
		usersConversationScroll
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		usersConversationScroll.setBounds(15, 15, 550, 270);

		usersTextMessagefield = new JTextArea();
		usersTextMessagefield.setEditable(true);

		usersTextMessageScroll = new JScrollPane(usersTextMessagefield);
		usersTextMessageScroll
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		usersTextMessageScroll.setBounds(15, 290, 550, 100);

		sendButton = new JButton("Wyœlij");
		sendButton.setBounds(465, 400, 100, 30);
		sendButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				eventQueue.offer(new SendToServerEvent(new MessageAppEvent(
						friendsUserId, usersTextMessagefield.getText())));
				usersTextMessagefield.setText("");
			}
		});

		/** Przeci¹¿am klikniêcie zamkniêcia okna chatu */
		WindowAdapter exitListener = new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				eventQueue.offer(new CloseMessageWindowEvent(friendsUserId));
				frame.dispose();

			}
		};

		frame.addWindowListener(exitListener);
		frame.add(usersConversationScroll);
		frame.add(usersTextMessageScroll);
		frame.add(sendButton);

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
	 * Ustawia widoczno ekranu rozmowy
	 * 
	 * @param visible
	 */
	public void setVisibleLobbyView(final boolean visible)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				frame.setVisible(visible);
			}
		});
	}

	/**
	 * @param messageDummy
	 */
	public void setMessageList(final ArrayList<Message> listOfMessages)
	{

		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				String toPrint="";
				Collections.sort(listOfMessages);
				for(Message message: listOfMessages)
				{
					toPrint=toPrint+df.format(message.getDate()) + " U¿ykownik o id: " + message.getByUser().getId() + "\n" + message.getMessage();
					System.out.println(toPrint);
				}
				usersConversation.setText("");
				usersConversation.setText(toPrint);
			}
		});
	}
}
