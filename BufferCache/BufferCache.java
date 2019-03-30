package BufferCache;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

class Block extends JLabel{
	//상태, 가진 주소, 등등등
}
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
	private void initialize() {//Absolute로 layout 
		frame = new JFrame("Buffer Cache GetBlk Algorithm");
		frame.setBounds(100, 100, 749, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton initBtn = new JButton("Buffers Init");
		initBtn.setBounds(415, 20, 91, 23);
		frame.getContentPane().add(initBtn);
		
		JButton getBlkBtn = new JButton("Get Blk");
		getBlkBtn.setBounds(415, 73, 91, 23);
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
		modNumberLabel.setFont(new Font("굴림", Font.BOLD, 12));
		modNumberLabel.setBounds(12, 24, 97, 15);
		frame.getContentPane().add(modNumberLabel);
		
		JLabel buffersNumberLabel = new JLabel("Numbers of Buffers :");
		buffersNumberLabel.setFont(new Font("굴림", Font.BOLD, 12));
		buffersNumberLabel.setBounds(190, 24, 136, 15);
		frame.getContentPane().add(buffersNumberLabel);
		
		JLabel getBlkLabel = new JLabel("Number of Buffer to GetBlk :");
		getBlkLabel.setFont(new Font("굴림", Font.BOLD, 12));
		getBlkLabel.setBounds(38, 77, 197, 15);
		frame.getContentPane().add(getBlkLabel);
		
		JLabel messageTxt = new JLabel("Message");
		messageTxt.setFont(new Font("굴림", Font.BOLD, 16));
		messageTxt.setBounds(590, 21, 84, 19);
		frame.getContentPane().add(messageTxt);
		
		
		JLabel ho = new JLabel("");
		ho.setBounds(72, 244, 50, 15);
		frame.getContentPane().add(ho);
		int maxModTxt=10;//최대 mod수 설정
		int maxBlk=50;//최대 블럭수 설정
		JLabel[] modTxt=new JLabel[maxModTxt];//mod txt를 저장할 배열 생성
		Block[] Blk=new Block[maxBlk];//blk 저장할 배열 생성
		Data data=new Data();//사용자 데이터를 저장할 클래스
		for(int i=0;i<maxModTxt;i++) {
			modTxt[i]=new JLabel();
			frame.getContentPane().add(modTxt[i]);//Label들을 미리 추가해줌(이벤트 후 바로 변경을 위해)
		}
		initBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					int modN=Integer.parseInt(modNumberField.getText());
					int bufferN=Integer.parseInt(bufferNumberField.getText());
					data.setData(modN, bufferN);
					for(int i=0;i<modN;i++) {
						modTxt[i].setText("MOD"+i);
						modTxt[i].setFont(new Font("굴림", Font.BOLD, 12));
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
