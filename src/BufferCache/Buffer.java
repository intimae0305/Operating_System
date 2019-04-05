package BufferCache;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import BufferCache.Buffer.State;

class Block{//Block Class
	private int number;//block number
	private Datum data;//데이타
	public Block() {//default 생성자, number초기화 하지 않을시 -1로 지정
		number=-1;
	}
	public Block(int number) {//Block number을  생성자의 파라미터로 연결 받음
		this.number=number;
	}
	public void setNumber(int number) {//함수로 blk number초기화
		this.number=number;
	}
	public int getNumber() {//blk number 리턴
		return number;
	}
}
class Datum{//데이터를 담는 클래스
	Object D;//추상 데이터
}
class Data{//사용자가 입력한 text들을 저장하는 class
	private int modN;//MOD
	private int bufferN;//Buffer Number
	private int blockN;//Getblk Number
	public Data() {
		
	}
	public Data(int modN,int bufferN) {//생성자
		this.modN=modN;
		this.bufferN=bufferN;
	}
	
	//각 멤버 변수들을 set하는 함수들
	public void setData(int modN,int bufferN) {
		this.modN=modN;
		this.bufferN=bufferN;
	}
	public void setBlockN(int b) {
		this.blockN=b;
	}
	
	//각 멤버변수들의 getMethod
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
	private Block blk=new Block();//buffer 객체가 담은 블럭
	private State bufferState;//buffer의 상태
	private boolean freeState;//buffer가 현재 freeList에 있는지
	public void setBuffer(int number,State state) {//number와 state로 셋팅
		//blk number 삽입 및 그리기
		blk.setNumber(number);
		setBufferState(state);
		setText("<html>"+Integer.toString(number)+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		setFont(new Font("굴림", Font.BOLD, 12));
		
		//배경색 지정
		setOpaque(true);
		setBackground(Color.white);
		
		
		//경계선 지정
		Border border=BorderFactory.createLineBorder(Color.black, 3);
		setBorder(border);
	}
	public void setBuffer(int number,int state) {//number와 state로 셋팅
		//blk number 삽입 및 그리기
		blk.setNumber(number);
		setBufferState(state);
		setText("<html>"+Integer.toString(number)+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		setFont(new Font("굴림", Font.BOLD, 12));
		
		//배경색 지정
		setOpaque(true);
		setBackground(Color.white);
		
		
		//경계선 지정
		Border border=BorderFactory.createLineBorder(Color.black, 3);
		setBorder(border);
	}

	public void update(int state) {//버퍼의 상태가 바뀌는 것을 업데이트 오버로딩
		setBufferState(state);
	}
	public enum State{
		LOCKED,UNLOCKED,DELAY,WRITE
	}
	public void setBufferState(State state) {//버퍼의 상태를 업데이트하고 다시 그림
		if(state==State.LOCKED)
		{
			this.bufferState=State.LOCKED;
			setForeground(Color.red);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		}
		else if(state==State.UNLOCKED)
		{
			this.bufferState=State.UNLOCKED;
			setForeground(Color.black);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		}
		else if(state==State.DELAY)
		{
			this.bufferState=State.DELAY;
			setForeground(Color.yellow);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		}
		else
		{
			this.bufferState=State.WRITE;
			setForeground(Color.green);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		}
	}
	public void setBufferState(int state) {//버퍼의 상태를 업데이트 하고 다시 그림(파라미터는 상태별로 int 배치)
		if(state==1)
		{
			this.bufferState=State.LOCKED;
			setForeground(Color.red);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		}
		else if(state==2)
		{
			this.bufferState=State.UNLOCKED;
			setForeground(Color.black);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		}
		else if(state==3)
		{
			this.bufferState=State.DELAY;
			setForeground(Color.yellow);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		}
		else
		{
			this.bufferState=State.WRITE;
			setForeground(Color.green);
			setText("<html>"+Integer.toString(blk.getNumber())+"<br/>"+getBufferStateToString()+"</html>");//글자색 지정
		}
	}
	public void setBlock(Block block) {//block을 파라미터로 초기화
		this.blk=block;
	}
	public String getBufferStateToString() {//글자색 지정 및 buffer state string return
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
	public State getBufferState() {//버퍼의 상태를 리턴
		return this.bufferState;
	}
	public Block getBlock() {//블럭을 리턴
		return blk;
	}
}

