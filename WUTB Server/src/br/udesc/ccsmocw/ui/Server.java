package br.udesc.ccsmocw.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import br.udesc.ccscmocw.core.CacheCoherenceServer;
import br.udesc.ccscmocw.values.Cache;
import br.udesc.ccscmocw.values.Question;

@SuppressWarnings("serial")
public class Server extends JFrame {
	private boolean isRunning = false;
	private CacheCoherenceServer ccs;
	JButton btnStart, btnQuit;
	JProgressBar progress;

	public Server() {
		super("WUTB Server");
		setSize(300, 150);
		setVisible(true);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		startCache();
		
		loadUI();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);

				Server s = new Server();

				s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				s.setVisible(true);

			}
		});
	}

	private void loadUI() {
		btnStart = new JButton("Start");
		btnStart.setBounds(20, 20, 100, 20);
		add(btnStart);

		btnQuit = new JButton("Quit");
		btnQuit.setBounds(160, 20, 100, 20);
		add(btnQuit);

		progress = new JProgressBar();
		progress.setBounds(20, 60, 240, 20);
		add(progress);

		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				doAction();

			}
		});

		btnQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Stop service
				try {
					ccs.stopServer();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Update UI
				progress.setIndeterminate(true);
				btnStart.setText("Stop");

				// Close app Server
				Server.this.dispose();
			}
		});

	}

	private void doAction() {
		if (isRunning) {
			ccs.stopServer();

			progress.setIndeterminate(false);
			btnStart.setText("Start");
		} else {
			ccs = new CacheCoherenceServer();
			ccs.start();

			progress.setIndeterminate(true);
			btnStart.setText("Stop");
		}

		// change state
		isRunning = !isRunning;
	}
	
	private void startCache() {
		
		//populate the questions array
		for(int i = 0; i<40; i++){
			Question q = new Question(i, " ", " ");
			Cache.questions.addLast(q);
			Cache.statusQuestionsAux[i] = " ";
		}
		
	}
}