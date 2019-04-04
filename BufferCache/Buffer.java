package BufferCache;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import BufferCache.Buffer.State;

class Block{//Block Class
	private int number;//block number
	private Datum data;//����Ÿ
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
class Datum{//�����͸� ��� Ŭ����
	Object D;//�߻� ������
}
class Data{//����ڰ� �Է��� text���� �����ϴ� class
	private int modN;//MOD
	private int bufferN;//Buffer Number
	private int blockN;//Getblk Number
	public Data() {
		
	}
	public Data(int modN,int bufferN) {//������
		this.modN=modN;
		this.bufferN=bufferN;
	}
	
	//�� ��� �������� set�ϴ� �Լ���
	public void setData(int modN,int bufferN) {
		this.modN=modN;
		this.bufferN=bufferN;
	}
	public void setBlockN(int b) {
		this.blockN=b;
	}
	
	//�� ����������� getMethod
	public int getModN() {
		return modN;
	}
	public int getBufferN() {
		return bufferN;
	}
	public int getBlockN() {
		return blockN;
	}
}

public class Buffer extends JLabel{//Buffer class
	private Block blk=new Block();//buffer ��ü�� ���� ��
	private State bufferState;//buffer�� ����
	private boolean freeState;//buffer�� ���� freeList�� �ִ���
	public void setBuffer(int number,State state) {//number�� state�� ����
		//blk number ���� �� �׸���
		blk.setNumber(number);
		setBufferState(state);
		setText("<html>"+Integer.toString(number)+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		setFont(new Font("����", Font.BOLD, 12));
		
		//���� ����
		setOpaque(true);
		setBackground(Color.white);
		
		
		//��輱 ����
		Border border=BorderFactory.createLineBorder(Color.black, 3);
		setBorder(border);
	}
	public void setBuffer(int number,int state) {//number�� state�� ����
		//blk number ���� �� �׸���
		blk.setNumber(number);
		setBufferState(state);
		setText("<html>"+Integer.toString(number)+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		setFont(new Font("����", Font.BOLD, 12));
		
		//���� ����
		setOpaque(true);
		setBackground(Color.white);
		
		
		//��輱 ����
		Border border=BorderFactory.createLineBorder(Color.black, 3);
		setBorder(border);
	}

	public void update(int state) {//������ ���°� �ٲ�� ���� ������Ʈ �����ε�
		setBufferState(state);
	}
	public enum State{
		LOCKED,UNLOCKED,DELAY,WRITE
	}
	public void setBufferState(State state) {//������ ���¸� ������Ʈ�ϰ� �ٽ� �׸�
		if(state==State.LOCKED)
		{
			this.bufferState=State.LOCKED;
			setForeground(Color.red);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		}
		else if(state==State.UNLOCKED)
		{
			this.bufferState=State.UNLOCKED;
			setForeground(Color.black);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		}
		else if(state==State.DELAY)
		{
			this.bufferState=State.DELAY;
			setForeground(Color.yellow);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		}
		else
		{
			this.bufferState=State.WRITE;
			setForeground(Color.green);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		}
	}
	public void setBufferState(int state) {//������ ���¸� ������Ʈ �ϰ� �ٽ� �׸�(�Ķ���ʹ� ���º��� int ��ġ)
		if(state==1)
		{
			this.bufferState=State.LOCKED;
			setForeground(Color.red);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		}
		else if(state==2)
		{
			this.bufferState=State.UNLOCKED;
			setForeground(Color.black);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		}
		else if(state==3)
		{
			this.bufferState=State.DELAY;
			setForeground(Color.yellow);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		}
		else
		{
			this.bufferState=State.WRITE;
			setForeground(Color.green);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//���ڻ� ����
		}
	}
	public void setBlock(Block block) {//block�� �Ķ���ͷ� �ʱ�ȭ
		this.blk=block;
	}
	public String getBufferStateToString() {//���ڻ� ���� �� buffer state string return
		String state="";
		switch(bufferState) {
		case LOCKED:
			state="Locked";
			break;
		case UNLOCKED:
			state="Unlocked";
			break;
		case DELAY:
			state= "Delay";
			break;
		default:

			state= "Write";
			break;
		}
		return state;
	}
	public State getBufferState() {//������ ���¸� ����
		return this.bufferState;
	}
	public Block getBlock() {//���� ����
		return blk;
	}
}

