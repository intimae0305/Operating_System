package BufferCache;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;


class Data{
	private int modN;
	private int bufferN;
	public Data() {
		modN=0;
		bufferN=0;
	}
	public void setData(int modN,int bufferN) {
		this.modN=modN;
		this.bufferN=bufferN;
	}
	public int getModN() {
		return modN;
	}
	public int getBufferN() {
		return bufferN;
	}
}
class Buffer extends JLabel{//Buffer class
	private Block blk=new Block();//buffer ��ü�� ���� ��
	private State bufferState;//buffer�� ����
	private boolean freeState;//buffer�� ���� freeList�� �ִ���
	public Buffer(int number) {
		//blk number ���� �� �׸���
		blk.setNumber(number);
		setText("<html>"+Integer.toString(number)+"<br/>"+getBufferState()+"</html>");//���ڻ� ����
		setFont(new Font("����", Font.BOLD, 12));
		
		//���� ����
		setOpaque(true);
		setBackground(Color.white);
		
		
		//��輱 ����
		Border border=BorderFactory.createLineBorder(Color.black, 3);
		setBorder(border);
	}
	public enum State{
		LOCKED,UNLOCKED,DELAY,WRITE
	}
	public void setFreeState(boolean state) {
		this.freeState=state;
	}
	public void setBufferState(State state) {
		this.bufferState=state;
	}
	public void setBlock(Block block) {
		this.blk=block;
	}
	public boolean getFreeState() {
		return freeState;
	}
	public String getBufferState() {//���ڻ� ���� �� buffer state string return
		String state="";
		switch(bufferState) {
		case LOCKED:
			setForeground(Color.red);
			state="Locked";
			break;
		case UNLOCKED:
			setForeground(Color.black);
			state="Unlocked";
			break;
		case DELAY:
			setForeground(Color.yellow);
			state= "Delay";
			break;
		default:
			setForeground(Color.green);
			state= "Write";
			break;
		}
		return state;
	}
	public Block getBlock() {
		return blk;
	}
	
}
class Block {//Block Class
	private int number;//block number
	public Block() {//default ������, number�ʱ�ȭ ���� ������ -1�� ����
		number=-1;
	}
	public Block(int number) {//Block number��  �������� �Ķ���ͷ� ���� ����
		this.number=number;
	}
	public void setNumber(int number) {//�Լ��� blk number�ʱ�ȭ
		this.number=number;
	}
	public int getNumber() {//blk number ����
		return number;
	}
}
	
public class BufferCache {

	private JFrame frame;
	private JTextField getBlkField;
	private JTextField bufferNumberField;
	private JTextField modNumberField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BufferCache window = new BufferCache();
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
	public BufferCache() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {//Absolute�� layout 
		frame = new JFrame("Buffer Cache GetBlk Algorithm");
		frame.setBounds(100, 100, 749, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton initBtn = new JButton("Buffers Init");
		initBtn.setFont(new Font("����", Font.BOLD, 10));
		initBtn.setBounds(415, 20, 102, 23);
		frame.getContentPane().add(initBtn);
		
		JButton getBlkBtn = new JButton("Get Blk");
		getBlkBtn.setFont(new Font("����", Font.BOLD, 10));
		getBlkBtn.setBounds(415, 73, 102, 23);
		frame.getContentPane().add(getBlkBtn);
		
		getBlkField = new JTextField();
		getBlkField.setBounds(230, 74, 58, 21);
		frame.getContentPane().add(getBlkField);
		getBlkField.setColumns(10);
		
		bufferNumberField = new JTextField();
		bufferNumberField.setColumns(10);
		bufferNumberField.setBounds(329, 21, 58, 21);
		frame.getContentPane().add(bufferNumberField);
		
		modNumberField = new JTextField();
		modNumberField.setColumns(10);
		modNumberField.setBounds(107, 21, 58, 21);
		frame.getContentPane().add(modNumberField);
		
		JLabel modNumberLabel = new JLabel("Mod Number :");
		modNumberLabel.setFont(new Font("����", Font.BOLD, 12));
		modNumberLabel.setBounds(12, 24, 97, 15);
		frame.getContentPane().add(modNumberLabel);
		
		JLabel buffersNumberLabel = new JLabel("Numbers of Buffers :");
		buffersNumberLabel.setFont(new Font("����", Font.BOLD, 12));
		buffersNumberLabel.setBounds(190, 24, 136, 15);
		frame.getContentPane().add(buffersNumberLabel);
		
		JLabel getBlkLabel = new JLabel("Number of Buffer to GetBlk :");
		getBlkLabel.setFont(new Font("����", Font.BOLD, 12));
		getBlkLabel.setBounds(38, 77, 197, 15);
		frame.getContentPane().add(getBlkLabel);
		
		JLabel messageTxt = new JLabel("Message");
		messageTxt.setFont(new Font("����", Font.BOLD, 16));
		messageTxt.setBounds(590, 21, 84, 19);
		frame.getContentPane().add(messageTxt);
		
		
		JLabel ho = new JLabel("");
		ho.setBounds(72, 244, 50, 15);
		frame.getContentPane().add(ho);
		int maxModTxt=10;//�ִ� mod�� ����
		int maxBlk=50;//�ִ� ���� ����
		JLabel[] modTxt=new JLabel[maxModTxt];//mod txt�� ������ �迭 ����
		Block[] Blk=new Block[maxBlk];//blk ������ �迭 ����
		Data data=new Data();//����� �����͸� ������ Ŭ����
		for(int i=0;i<maxModTxt;i++) {
			modTxt[i]=new JLabel();
			frame.getContentPane().add(modTxt[i]);//Label���� �̸� �߰�����(�̺�Ʈ �� �ٷ� ������ ����)
		}
		initBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					int modN=Integer.parseInt(modNumberField.getText());
					int bufferN=Integer.parseInt(bufferNumberField.getText());
					data.setData(modN, bufferN);
					for(int i=0;i<modN;i++) {
						modTxt[i].setText("MOD"+i);
						modTxt[i].setFont(new Font("����", Font.BOLD, 12));
						modTxt[i].setBounds(60,180+70*i,70,15);
					}
					for(int i=modN;i<maxModTxt;i++) {
						modTxt[i].setText("");
					}
			}
});

		JLabel scenarioTxt = new JLabel("Some Text");
		scenarioTxt.setHorizontalAlignment(SwingConstants.CENTER);
		scenarioTxt.setBounds(538, 49, 175, 43);
		frame.getContentPane().add(scenarioTxt);
		
	}
}
