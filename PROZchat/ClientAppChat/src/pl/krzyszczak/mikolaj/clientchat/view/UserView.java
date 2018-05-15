/**
 * 
 */
package pl.krzyszczak.mikolaj.clientchat.view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pl.krzyszczak.mikolaj.clientchat.appEvent.OpenMessageWindowEvent;
import pl.krzyszczak.mikolaj.clientchat.appEvent.SendToServerEvent;
import pl.krzyszczak.mikolaj.serverchat.appEvent.AddFriendAppEvent;
import pl.krzyszczak.mikolaj.serverchat.appEvent.ApplicationEvent;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UserId;
import pl.krzyszczak.mikolaj.serverchat.helpfull.UsersDataForClient;

/**
 * Klasa odpowiedzialma za wyœwietlanie listy userów u danego u¿ytkownika
 * 
 * @author Miko³aj
 *
 */
public class UserView
{

	/** Referencja na kolejkê do której dodawane s¹ eventy */
	BlockingQueue<ApplicationEvent> eventQueue;
	/** g³ówna ramka */
	private JFrame frame;
	/** lista zaprzyjaznionych uzytkownikow */
	private JList<UsersDataForClient> userList;
	/** lista zaprzyjaznionych uzytkownikow */
	private JList<UsersDataForClient> usersFriendList;
	/** Lista elementów do wyœwietlenia */
	private DefaultListModel<UsersDataForClient> listFriendsModel;
	/** Lista elementów do wyœwietlenia */
	private DefaultListModel<UsersDataForClient> listAllUsersModel;
	/**
	 * Etykieta do opisuj¹ca pole JList zawieraj¹cych wszystkich u¿ytkowników na
	 * serwerze
	 */
	private JLabel allUsersLabel;
	/**
	 * Etykieta do opisuj¹ca pole JList zawierajacych znajomych danego
	 * uzytkownika
	 */
	private JLabel usersFriendsLabel;
	/** Panel do wszystkich u¿ytkowników */
	private JPanel usersPanel;
	/** Panel do logowania */
	private JPanel usersFriendPanel;
	/** Jscrolpannel do wszysktich uzytkownikow */
	private JScrollPane usersPanelScroll;
	/** Jscrolpannel do listy znajomych */
	private JScrollPane friendsPanelScroll;
	/**Mouse Listenery*/
	private	MouseListener mouseUsersListener;
	private MouseListener mouseFriendsListener;

	public UserView(final BlockingQueue<ApplicationEvent> eventQueue, UserId userId)
	{
		this.eventQueue = eventQueue;

		// Frame
		frame = new JFrame("Chat ID:" + userId.getId());
		frame.setSize(330, 800);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Label
		usersFriendsLabel = new JLabel("Znajomi: ");
		usersFriendsLabel.setBounds(15, 15, 100, 50);

		allUsersLabel = new JLabel("Wszyscy uzytkownicy :");
		allUsersLabel.setBounds(15, 365, 150, 50);


		/** @ TODO obs³ugê przyciwsków */

		// ListModel
		listAllUsersModel = new DefaultListModel<UsersDataForClient>();
		listFriendsModel = new DefaultListModel<UsersDataForClient>();

		HashSet<UsersDataForClient> set = new HashSet<UsersDataForClient>();
		set.add(new UsersDataForClient(new UserId(2), "andrzej"));
		set.add(new UsersDataForClient(new UserId(3), "bogdan"));
		set.add(new UsersDataForClient(new UserId(4), "miki"));
		set.add(new UsersDataForClient(new UserId(2), "andrzej"));
		set.add(new UsersDataForClient(new UserId(3), "bogdan"));
		set.add(new UsersDataForClient(new UserId(4), "miki"));
		set.add(new UsersDataForClient(new UserId(2), "andrzej"));
		set.add(new UsersDataForClient(new UserId(3), "bogdan"));
		set.add(new UsersDataForClient(new UserId(4), "miki"));
		set.add(new UsersDataForClient(new UserId(2), "andrzej"));
		set.add(new UsersDataForClient(new UserId(3), "bogdan"));
		set.add(new UsersDataForClient(new UserId(4), "miki"));
		set.add(new UsersDataForClient(new UserId(2), "andrzej"));
		set.add(new UsersDataForClient(new UserId(3), "bogdan"));
		set.add(new UsersDataForClient(new UserId(4), "miki"));
		set.add(new UsersDataForClient(new UserId(2), "andrzej"));
		set.add(new UsersDataForClient(new UserId(3), "bogdan"));
		set.add(new UsersDataForClient(new UserId(4), "miki"));
		set.add(new UsersDataForClient(new UserId(2), "andrzej"));
		set.add(new UsersDataForClient(new UserId(3), "bogdan"));
		set.add(new UsersDataForClient(new UserId(4), "miki"));

		/*
		 * for (UsersDataForClient user : set) {
		 * listAllUsersModel.addElement(user); }
		 * 
		 * for (UsersDataForClient user : set) {
		 * listFriendsModel.addElement(user); }
		 */
		// User List
		userList = new JList<UsersDataForClient>(listAllUsersModel);
		userList.setBounds(15, 400, 300, 300);
		userList.setCellRenderer(new ownRenderer());
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userList.setLayoutOrientation(JList.VERTICAL);

		// UserFriendList
		usersFriendList = new JList<UsersDataForClient>(listFriendsModel);
		usersFriendList.setBounds(15, 50, 300, 300);
		usersFriendList.setCellRenderer(new ownRenderer());
		usersFriendList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usersFriendList.setLayoutOrientation(JList.VERTICAL);

		// Scroll
		usersPanelScroll = new JScrollPane(userList);
		usersPanelScroll.setBounds(15, 400, 300, 300);
		friendsPanelScroll = new JScrollPane(usersFriendList);
		friendsPanelScroll.setBounds(15, 50, 300, 300);

		/*
		 * userList.addListSelectionListener(new ListSelectionListener() {
		 * 
		 * @Override public void valueChanged(ListSelectionEvent e) {
		 * if(!e.getValueIsAdjusting()) { samochod seleList=
		 * userList.getSelectedValue(); listModel.addElement(seleList); resd();
		 * System.out.println(seleList);
		 * 
		 * }
		 * 
		 * } });
		 */
		mouseUsersListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					UsersDataForClient seleList = userList.getSelectedValue();
					eventQueue.offer(new SendToServerEvent(new AddFriendAppEvent(seleList.getUserId())));
				}
			}
		};
		userList.addMouseListener(mouseUsersListener);
		
		mouseFriendsListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					UsersDataForClient seleList = usersFriendList.getSelectedValue();
					System.out.println("Dupa dupa dupa" + seleList.getUserId().getId());
					eventQueue.offer(new OpenMessageWindowEvent(seleList.getUserId()));
				}
			}
		};
		usersFriendList.addMouseListener(mouseFriendsListener);

		// frame.add(userList);
		frame.add(usersFriendsLabel);
		frame.add(allUsersLabel);

		frame.add(friendsPanelScroll);
		frame.add(usersPanelScroll);
		frame.setResizable(false);
		// frame.setVisible(true);

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
	 * Ustawia widoczno ekranu listy u¿ytkowników
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
	 * Metoda ustawiaj¹ca listy kontaktów do wyœwietlenia
	 * 
	 * @param allUsers
	 * @param usersContacts
	 */
	public void setContacts(final HashSet<UsersDataForClient> allUsers,
			final HashSet<UsersDataForClient> usersContacts)
	{

		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				allUsers.containsAll(usersContacts);
				
				//if(allUsers==usersContacts)
				{
					listFriendsModel = (DefaultListModel<UsersDataForClient>) usersFriendList
							.getModel();
					listFriendsModel.removeAllElements();
					for (UsersDataForClient user : usersContacts)
					{
						listFriendsModel.addElement(user);
						System.out.println(user.getName());
					}

					
				}
				listAllUsersModel = (DefaultListModel<UsersDataForClient>) userList
						.getModel();
				
				listAllUsersModel.removeAllElements();
				
				// Tworzymy listê u¿ytkowników
				for (UsersDataForClient user : allUsers)
				{
					listAllUsersModel.addElement(user);
					System.out.println(user.getName());
				}

				
			}
		});

	}

	public class ownRenderer extends JLabel implements
			ListCellRenderer<UsersDataForClient>
	{
		/**
 * 
 */
		private static final long serialVersionUID = 1L;
		protected Border focusBorder;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing
		 * .JList, java.lang.Object, int, boolean, boolean)
		 */

		public ownRenderer()
		{
			focusBorder = new EmptyBorder(1, 1, 1, 1);
			setOpaque(true);
		}

		public Component getListCellRendererComponent(
				JList<? extends UsersDataForClient> list,
				UsersDataForClient value, int index, boolean isSelected,
				boolean cellHasFocus)
		{
			setBackground(isSelected ? list.getSelectionBackground() : list
					.getBackground());
			setForeground(isSelected ? list.getSelectionForeground() : list
					.getForeground());

			setFont(list.getFont());

			setBorder((cellHasFocus) ? UIManager
					.getBorder("List.focusCellHighlightBorder") : focusBorder);

			setText(value.getName() + "  " + value.getUserId().getId());
			return this;
		}

	}

}
