package BufferCache;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BufferCache.Buffer.State;


public class BC{

	private JFrame frame=new JFrame("Buffer Cache GetBlk Algorithm");;
	private JTextField getBlkField;
	private JTextField bufferNumberField;
	private JTextField modNumberField;
	JLabel scenarioTxt = new JLabel("");
	Draw panel=new Draw();
	JButton initBtn = new JButton("Buffers Init");
	JButton getBlkBtn = new JButton("Get Blk");
	int maxModTxt=6;//최대 모드수 + free txt
	int maxBff=50;//최대 블럭수 설정
	Data data=new Data();
	DoublyLinkedList bufferList[]=new DoublyLinkedList[maxModTxt];//입력 받은 노드 갯수 만큼 노드 생성
	DoublyLinkedList freeList=new DoublyLinkedList();
	JLabel[] modTxt=new JLabel[maxModTxt];//mod txt를 저장할 배열 생성
	Buffer[] bff=new Buffer[maxBff];//buff를 저장할 배열 생성
	
	public Buffer isInHashQueue(int num) {//버퍼가 해쉬큐에 존재하는지 확인하는 함수, 존재하면 해당 버퍼 리턴
		for(int i=0;i<data.getModN();i++) {
			int size=bufferList[i].getSize();
			DoublyLinkedList link=bufferList[i];
			for(int j=0;j<size;j++) {
				Buffer buffer=(Buffer)link.get(j);
				if(num==buffer.getBlock().getNumber())
					return buffer;
			}
		}
		return null;
	}
	public void Update() {
		//freeList 라인 새로 그리는 함수(getBlock 버튼 누를때마다)
		int x[]=new int[52];
		int y[]=new int[52];
		int n=freeList.getSize();
		Rectangle ranc1=modTxt[data.getModN()].getBounds();
		x[0]=ranc1.x+ranc1.width/2;
		y[0]=ranc1.y+ranc1.height/2;
		for(int i=0;i<n;i++) {
			Buffer buffer=(Buffer)freeList.get(i);
			Rectangle ranc=buffer.getBounds();
			x[i+1]=ranc.x+ranc.width/2;
			y[i+1]=ranc.y+ranc.height/2;
		}
		x[n+1]=ranc1.x+ranc1.width/2;
		y[n+1]=ranc1.y+ranc1.height/2;
		panel.setLine(x, y, n+2);
		if(freeList.getSize()==0)
		{
			x=null;
			y=null;
			panel.setLine(x, y, 0);
		}
		panel.repaint();
	}
	public void removeBufferInHashQueue(Buffer buffer) {//버퍼를 해쉬큐에서 삭제하는 함수
		for(int i=0;i<data.getModN();i++) {
			int size=bufferList[i].getSize();
			DoublyLinkedList link=bufferList[i];
			for(int j=0;j<size;j++) {
				if(link.get(j)==buffer)
				{
					link.remove(link.indexOf(buffer));
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {//메인하무
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BC window = new BC();
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
	public BC() {//BC 생성자
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {//BC 앱 실행
		Random r=new Random();
		System.out.println(scenarioTxt.getBounds());
		frame.setBounds(100, 100, 749, 610);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		
		initBtn.setFont(new Font("굴림", Font.BOLD, 10));
		initBtn.setBounds(415, 20, 102, 23);
		panel.add(initBtn);
		
		
		getBlkBtn.setFont(new Font("굴림", Font.BOLD, 10));
		getBlkBtn.setBounds(415, 73, 102, 23);
		panel.add(getBlkBtn);
		
		getBlkField = new JTextField();
		getBlkField.setBounds(230, 74, 58, 21);
		panel.add(getBlkField);
		getBlkField.setColumns(10);
		
		bufferNumberField = new JTextField();
		bufferNumberField.setColumns(10);
		bufferNumberField.setBounds(329, 21, 58, 21);
		panel.add(bufferNumberField);
		
		modNumberField = new JTextField();
		modNumberField.setColumns(10);
		modNumberField.setBounds(107, 21, 58, 21);
		panel.add(modNumberField);
		
		JLabel modNumberLabel = new JLabel("Mod Number :");
		modNumberLabel.setFont(new Font("굴림", Font.BOLD, 12));
		modNumberLabel.setBounds(12, 24, 97, 15);
		panel.add(modNumberLabel);
		
		JLabel buffersNumberLabel = new JLabel("Numbers of Buffers :");
		buffersNumberLabel.setFont(new Font("굴림", Font.BOLD, 12));
		buffersNumberLabel.setBounds(190, 24, 136, 15);
		panel.add(buffersNumberLabel);
		
		JLabel getBlkLabel = new JLabel("Number of Buffer to GetBlk :");
		getBlkLabel.setFont(new Font("굴림", Font.BOLD, 12));
		getBlkLabel.setBounds(38, 77, 197, 15);
		panel.add(getBlkLabel);
		
		JLabel messageTxt = new JLabel("Message");
		messageTxt.setFont(new Font("굴림", Font.BOLD, 16));
		messageTxt.setBounds(590, 21, 84, 19);
		panel.add(messageTxt);
		
		
		JLabel ho = new JLabel("");
		ho.setBounds(72, 244, 50, 15);
		panel.add(ho);



		boolean[] exist=new boolean[100];//block number 중복 방지
		for(int i=0;i<100;i++) {
			exist[i]=false;
		}
		getBlkBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int blkN=Integer.parseInt(getBlkField.getText());
				data.setBlockN(blkN);
				Buffer buffer=isInHashQueue(blkN);
				boolean loop=true;	
				while(loop) {
					if(buffer!=null) {//Hash큐에 있을떄
						//시나리오 5
						if(buffer.getBufferState()==State.LOCKED) {
							//sleep 구현
							scenarioTxt.setText("<html>Scenario 5. Buffer is Locked. Sleep<br/>");
							break;
						}//시나리오 1
						else if(buffer.getBufferState()==State.UNLOCKED) {
							int index=freeList.indexOf(buffer);
							if(index!=-1) {
								freeList.remove(index);
								buffer.setBufferState(State.LOCKED);
								scenarioTxt.setText("<html>Scenario 1. get "+buffer.getBlock().getNumber()+" block from the freeList<br/></html>");
								Update();
								break;
							}
							else {
								buffer.setBufferState(State.LOCKED);
								scenarioTxt.setText("<html>"+buffer.getBlock().getNumber()+" block be Locked<br/></html>");
								break;
							}
							
						}
						else if(buffer.getBufferState()==State.DELAY){//만약 프리리스트에 있는 Delay에 접근했다면
							freeList.remove(freeList.indexOf(buffer));
							buffer.setBufferState(State.WRITE);
							scenarioTxt.setText("<html>"+buffer.getBlock().getNumber()+" buffer is Delay<br/></html>");
							Update();
							break;
						}
						
					}
					else {//Hash큐에 없을때
						//시나리오4 구현
						if(freeList.getSize()==0) {//프리리스트가 비었다면
							//sleep구현
							Update();
							scenarioTxt.setText("<html>Scenario 4. <br/>FreeList is Empty. Sleep<br/></html>");
							break;
						}
						else//프리리스트가 차있다면
						{
							Buffer delayBuffer=(Buffer)freeList.get(0);
							//시나리오 3구현
							if(delayBuffer.getBufferState()==State.DELAY) {
								freeList.remove(0);
								delayBuffer.setBufferState(State.WRITE);
								bufferList[delayBuffer.getBlock().getNumber()%data.getModN()].addLast(delayBuffer);
								scenarioTxt.setText("<html>Scenario 3.<br/>Make 'Delay' buffer state to 'Write' and remove it from the freelist<br/></html>");
							}
							else {//시나리오 2 구현
								Buffer removedBuffer=(Buffer)freeList.remove(0);
								removedBuffer.setVisible(false);
								removeBufferInHashQueue(removedBuffer);//해쉬 큐에서 삭제
								int m=blkN%data.getModN();
								int size=bufferList[m].getSize();
								buffer=bff[m*10+size];
								buffer.getBlock().setNumber(blkN);
								bufferList[m].addLast(buffer);//테이블에 버퍼 추가
								buffer.setBuffer(blkN,State.LOCKED);//busy로 바꿔줌
								buffer.setVisible(true);
								bufferList[m].addLast(bff[m*1+size]);
								scenarioTxt.setText("<html>Scenario 2.<br/>get buffer from the freeList and add "+blkN+" block in HashQueue<br/><br/></html>");
								Update();
								break;
							}
							Update();
						}
					}
				}
			}
		});
		initBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					Arrays.fill(exist, false);
					initBtn.setEnabled(false);
					int modN=Integer.parseInt(modNumberField.getText());
					int bufferN=Integer.parseInt(bufferNumberField.getText());
					Data d=new Data(modN,bufferN);
					data.setData(modN,bufferN);
					//gui부분
					if(modN*3<=bufferN)
					{
						scenarioTxt.setText("");
						for(int i=0;i<modN;i++) {//mod갯수 만큼만 초기화
							modTxt[i].setText("MOD"+i);
						}
						modTxt[modN].setText("freelist");
						for(int i=modN+1;i<maxModTxt;i++) {//나머지는 txt 비워 버림
						modTxt[i].setText("");
						}
						for(int i=0;i<maxBff;i++) {//버퍼들을 전부 끔
							bff[i].setVisible(false);
						}		
						//프리리스트, 해쉬테이블
						for(int i=0;i<modN;i++) {//리스트를 전부 비움
							bufferList[i].MakeEmpty();
							freeList.MakeEmpty();
						}
						for(int i=0;i<modN;i++) {//모드 마다 3개씩 미리 배정
							for(int j=0;j<3;j++)
							{
								int stateNum=r.nextInt(100)+1;
								if(stateNum<=60)//60퍼센트로 언락
								{
									stateNum=2;
								}
								else if(stateNum<=90)//30퍼센트로 락
									stateNum=1;
								else
								{
									stateNum=3;//10퍼센트로 delay
								}
								while(true) {
									int blockNum=r.nextInt(10)*modN+i;
									if(!exist[blockNum]) {//block Number 중복 방지
										exist[blockNum]=true;//block number의 값이 이제 존재하므로 true로
										DoublyLinkedList link=bufferList[i];
										Buffer buffer=bff[10*i+link.getSize()];						
										link.addLast(buffer);
										buffer.setBuffer(blockNum,stateNum);
										if(buffer.getBufferStateToString()=="Delay") {//Delay는 freeList에 무조건 넣음
											freeList.addLast(buffer);
										}
										else if(buffer.getBufferStateToString()=="Unlocked") {
											int freeRan=r.nextInt(100)+1;
											if(freeRan>=0&&freeRan<=10) {//20퍼센트 확률로 freeList에 넣음
												freeList.addLast(buffer);
											}
										}
										buffer.setVisible(true);
										break;
									}
								}
							}
						}
						for(int i=3*modN;i<bufferN;i++) {
							int stateNum=r.nextInt(100)+1;
							if(stateNum<=60)//60퍼센트로 언락
							{
								stateNum=2;
							}
							else if(stateNum<=90)//30퍼센트로 락
								stateNum=1;
							else
							{
								stateNum=3;//10퍼센트로 delay
							}
							while(true) {
								int blockNum=r.nextInt(99)+1;
								if(!exist[blockNum]) {//block Number 중복 방지
									exist[blockNum]=true;//block number의 값이 이제 존재하므로 true로
									DoublyLinkedList link=bufferList[blockNum%modN];
									Buffer buffer=bff[10*(blockNum%modN)+link.getSize()];						
									link.addLast(buffer);
									buffer.setBuffer(blockNum,stateNum);
									if(buffer.getBufferStateToString()=="Delay") {//Delay는 freeList에 무조건 넣음
										freeList.addLast(buffer);
									}
									else if(buffer.getBufferStateToString()=="Unlocked") {
										int freeRan=r.nextInt(100)+1;
										if(freeRan>=0&&freeRan<=10) {//20퍼센트 확률로 freeList에 넣음
											freeList.addLast(buffer);
										}
									}
									buffer.setVisible(true);
									break;
								}
							}
						}
					}
					else {
						scenarioTxt.setText("<html>plz buffer Number>=mod*3<html/>");
					}
					int x[]=new int[52];
					int y[]=new int[52];
					int n=freeList.getSize();
					Rectangle ranc1=modTxt[modN].getBounds();
					x[0]=ranc1.x+ranc1.width/2;
					y[0]=ranc1.y+ranc1.height/2;
					for(int i=0;i<n;i++) {
						Buffer buffer=(Buffer)freeList.get(i);
						Rectangle ranc=buffer.getBounds();
						x[i+1]=ranc.x+ranc.width/2;
						y[i+1]=ranc.y+ranc.height/2;
					}
					x[n+1]=ranc1.x+ranc1.width/2;
					y[n+1]=ranc1.y+ranc1.height/2;
					if(n!=0)
						panel.setLine(x, y, n+2);//freelist가 0이 아닐 경우에만 화살표를 셋팅
					else
						panel.setLine(x, y, 0);//프리리스트가 0이면 화살표 수 0으로 셋팅
					panel.repaint();//버튼 누를때 마다 다시 그리기
					initBtn.setEnabled(true);
			}
});
		for(int i=0;i<maxModTxt;i++) {
			modTxt[i]=new JLabel();
			panel.add(modTxt[i]);//Label들을 미리 추가해줌(이벤트 후 바로 변경을 위해)
			modTxt[i].setFont(new Font("굴림", Font.BOLD, 12));
			modTxt[i].setBounds(10,140+70*i,70,15);
		}
		for(int i=0;i<maxBff;i++)//버퍼들 공간 할당 및 gui 기본 설정
		{
			bff[i]=new Buffer();
			bff[i].setFont(new Font("굴림", Font.BOLD, 12));
			panel.add(bff[i]);
			bff[i].setBounds(60+100*(i%10),125+70*(i/10),70,40);
			bff[i].setVisible(false);
		}
		for(int i=0;i<maxModTxt;i++) {//bufferlist 공간 할당
			bufferList[i]=new DoublyLinkedList();
		}
	

		scenarioTxt.setHorizontalAlignment(SwingConstants.CENTER);
		scenarioTxt.setVerticalAlignment(SwingConstants.TOP);
		scenarioTxt.setBounds(538, 40, 175, 200);
		panel.add(scenarioTxt);
		panel.add(new Draw());
		frame.setSize(1200,800);
		frame.setLocationRelativeTo(null);//프레임을 정중앙으로 배치(창이 열릴때 정중앙에)
		
	}
}
	