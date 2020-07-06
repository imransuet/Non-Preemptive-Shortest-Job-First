
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Non_Preemptive_SJF {

	private JFrame Mainframe;
	private JLabel label, processlabel, label2, label3, label4, mainlabel;
	private JTextArea TextArea1, TextArea2, TextArea3;
	private JPanel Panel, Panel1, Panel2, Panel3, Panel4, Panel5, Panel6, Panel7;
	private JButton mybutton1, mybutton2, mybutton3;

	private JTextField process, arrival_time, brust_time;

	public Non_Preemptive_SJF() {

		prepareGUI();

	}

	public static void main(String[] args) {

		Non_Preemptive_SJF R = new Non_Preemptive_SJF();

	}

	private void prepareGUI() {
		Mainframe = new JFrame("SJF");
		Mainframe.setSize(800, 800);
		Mainframe.setLayout(new GridLayout(7, 1));
		Mainframe.setVisible(true);

		Panel6 = new JPanel();
		Mainframe.add(Panel6);

		Panel2 = new JPanel();
		Mainframe.add(Panel2);

		Panel3 = new JPanel();
		Mainframe.add(Panel3);

		Panel4 = new JPanel();
		Mainframe.add(Panel4);

		Panel5 = new JPanel();
		Mainframe.add(Panel5);

		Panel7 = new JPanel();
		Mainframe.add(Panel7);

		mainlabel = new JLabel("", JLabel.CENTER);
		mainlabel.setText("Non Preemptive Shortest Job First");
		mainlabel.setFont(new Font("RED", Font.BOLD, 21));
		mainlabel.setForeground(Color.magenta);

		processlabel = new JLabel("", JLabel.LEFT);
		processlabel.setFont(new Font("RED", Font.ITALIC, 19));
		processlabel.setForeground(Color.green);
		processlabel.setText("Process No        ");
		process = new JTextField(5);

		label3 = new JLabel("", JLabel.LEFT);
		label3.setFont(new Font("RED", Font.ROMAN_BASELINE, 19));
		label3.setForeground(Color.CYAN);
		label3.setText("Arrival Time     ");
		arrival_time = new JTextField(25);

		label4 = new JLabel("", JLabel.LEFT);
		label3.setFont(new Font("RED", Font.TRUETYPE_FONT, 19));
		label4.setForeground(Color.DARK_GRAY);
		label4.setText("Brust Time     ");
		brust_time = new JTextField(25);

		mybutton2 = new JButton("Start");
		mybutton3 = new JButton("Reset");

		Panel6.add(mainlabel);
		Panel2.add(processlabel);
		Panel2.add(processlabel);
		Panel2.add(process);

		Panel3.add(label3);
		Panel3.add(arrival_time);

		Panel4.add(label4);
		Panel4.add(brust_time);

		Panel5.add(mybutton2);

		Mainframe.setResizable(false);

		Panel1 = new JPanel();
		Mainframe.add(Panel1);
		Panel1.setLayout(new GridBagLayout());

		Panel5.add(mybutton3);

		final Graphics g = Panel7.getGraphics();

		mybutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int temp, totaltime = 0, smallvalue = 10000, burst = 0, temp2 = 0, temp1 = 0, mini = 0, k = 1,
						check = 0, i, j, t = 0, stall_index = 0;

				String s1 = process.getText();
				String s2 = arrival_time.getText();
				String s3 = brust_time.getText();
				int pro = Integer.parseInt(s1);

				StringTokenizer token1 = new StringTokenizer(s2, ",");
				StringTokenizer token2 = new StringTokenizer(s3, ",");

				int arrival[] = new int[1000];
				int brust[] = new int[1000];

				i = 0;
				while (token1.hasMoreElements()) {
					String tokenizer = token1.nextToken();
					int data = Integer.parseInt(tokenizer);

					arrival[i] = data;
					i++;
				}

				i = 0;
				while (token2.hasMoreElements()) {
					String tokenizer = token2.nextToken();
					int data = Integer.parseInt(tokenizer);
					brust[i] = data;
					i++;
				}

				int p[] = new int[100];
				int q[] = new int[100];
				int time[] = new int[100];
				int brustcopy[] = new int[100];
				int arrivalcopy[] = new int[100];
				int stall[] = new int[100];

				for (i = 0; i < pro; i++) {
					p[i] = i + 1;
				}

				for (i = 0; i < pro - 1; i++) {
					for (j = i + 1; j < pro; j++) {
						if (arrival[i] > arrival[j]) {
							temp = arrival[i];
							arrival[i] = arrival[j];
							arrival[j] = temp;

							temp = brust[i];
							brust[i] = brust[j];
							brust[j] = temp;

							temp = p[i];
							p[i] = p[j];
							p[j] = temp;

						}
					}
				}

				for (i = 0; i < pro; i++) {

					q[i] = p[i];
					brustcopy[i] = brust[i];
					arrivalcopy[i] = arrival[i];
				}

				for (j = 0; j < pro; j++) {
					burst = burst + brust[j];
					mini = brust[k];
					for (i = k; i < pro; i++)/* main logic */ {
						if (burst >= arrival[i]) {
							if (brust[i] < mini) {
								temp2 = p[k];
								p[k] = p[i];
								p[i] = temp2;
								temp2 = arrival[k];
								arrival[k] = arrival[i];
								arrival[i] = temp2;
								temp1 = brust[k];
								brust[k] = brust[i];
								brust[i] = temp1;

							}
						}
					}
					k++;
				}

				for (i = 0; i < pro; i++) {
					for (j = 0; j < pro; j++) {
						// printf("m");
						if (p[i] == q[j]) {
							if (i == 0) {
								if (arrivalcopy[j] <= t) {
									if (stall_index == 0) {
										time[0] = 0;
										stall[0] = 2;
										t = t + brustcopy[j];
										stall_index++;
										time[stall_index] = t;
										// j++;
										continue;

									}
									if (stall_index > 0) {
										stall[stall_index] = 2;
										t = t + brustcopy[j];
										stall_index++;
										time[stall_index] = t;
										// j++;
										continue;

									}

								}

								if (arrivalcopy[j] > t) {
									if (stall_index == 0) {
										time[0] = 0;
										stall[0] = -1;
										t++;
										stall_index++;
										time[stall_index] = t;
										j--;
										continue;

									}
									if (stall_index > 0) {
										stall[stall_index] = -1;
										t++;
										stall_index++;
										time[stall_index] = t;
										j--;
										continue;

									}
								}
							}
							if (i > 0) {
								if (arrivalcopy[j] <= t) {

									stall[stall_index] = 2;
									t = t + brustcopy[j];
									stall_index++;
									time[stall_index] = t;
									// j++;
									continue;

								}

								if (arrivalcopy[j] > t) {

									stall[stall_index] = -1;
									t++;
									stall_index++;
									time[stall_index] = t;
									j--;
									continue;

								}
							}

						}
					}
				}

				Graphics g = Panel7.getGraphics();
				g.setColor(Color.blue);

				int x1 = 10, x2 = 10, y1 = 40, y2 = 40;
				int px1 = (x1 + 8);
				int py1 = 35;
				int tx1 = x1 - 3;
				int ty1 = 65;

				int index = 0, st = 0;

				for (i = 0; i < pro; i++) {
					g.drawRect(x1, x2, y1, y2);
					if (stall[index] == -1) {
						g.drawString("St" + st, px1, py1);
						st++;
						g.drawString("" + time[index], tx1, ty1);
						x1 = x1 + y1;
						tx1 = x1 - 3;
						px1 = (x1 + 17);
						index++;
						i--;
					} else {
						g.drawString("P" + p[i], px1, py1);

						g.drawString("" + time[index], tx1, ty1);
						x1 = x1 + y1;
						tx1 = x1 - 3;
						px1 = (x1 + 17);
						index++;
					}

				}
				g.drawString("" + time[index], tx1, ty1);
			}
		});
		mybutton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				process.setText(null);
				arrival_time.setText(null);
				brust_time.setText(null);
				Panel7.repaint();

			}
		});
	}
}
