package BufferCache;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Draw extends JPanel{//그림을 그리는 클래스
		int x[];
		int y[];
		int n=0;
		public void setLine(int[] x,int[] y,int n) {//라인의 각 end point를 배열로 지정하고 갯수를 초기화 하는 함수
			this.x=x;
			this.y=y;
			this.n=n;
		}
		public double[] p2Arrow(int x, int y) {//화살표 만드는 함수, 벡터 생성 후 135도, -135도 회전변환
			double vecX=x;
			double vecY=y;
			double X1=(-0.7)*vecX+(-0.7)*vecY;
			double Y1=(0.7)*vecX+(-0.7)*vecY;
			double X2=(-0.7)*vecX+(0.7)*vecY;
			double Y2=(-0.7)*vecX+(-0.7)*vecY;
			double size1=Math.sqrt(Math.pow(X1,2)+Math.pow(Y1, 2));
			double size2=Math.sqrt(Math.pow(X2,2)+Math.pow(Y2, 2));
			X1/=size1;//단위벡터 생성
			Y1/=size1;
			X2/=size2;
			Y2/=size2;
			double arr[]=new double[4];
			arr[0]=X1*10;//사이즈 10배 증가
			arr[1]=Y1*10;
			arr[2]=X2*10;
			arr[3]=Y2*10;
			return arr;
		}
		public void paint(Graphics g) { //프리리스트 선 그리는 함수
		        super.paint(g);
		        if(n!=0)//화살표 수가 0개이면 x,y보지 않고 화살표 그리지 않기
		        {
		        	g.drawLine(x[0]+35,y[0]-10, x[1]-35, y[1]);
		        	int arrX= x[1]-35-x[0]-35;
		        	int arrY= y[1]-y[0]+10;
		        	double arr[]=p2Arrow(arrX,arrY);//회전 변환한 양쪽 벡터 반환
		        	g.drawLine(x[1]-35, y[1], x[1]-35+(int)arr[0], y[1]+(int)arr[1]);//끝점을 기준으로 그리기
		        	g.drawLine(x[1]-35, y[1], x[1]-35+(int)arr[2], y[1]+(int)arr[3]);
		        	for(int i=1;i<n-2;i++) {
		        		g.drawLine(x[i]+35, y[i], x[i+1]-35, y[i+1]);
		        		int arrX1= x[i+1]-35-x[i]-35;
			        	int arrY1= y[i+1]-y[i];
			        	double arr1[]=p2Arrow(arrX1,arrY1);//회전 변환한 양쪽 벡터 반환
			        	g.drawLine(x[i+1]-35, y[i+1], x[i+1]-35+(int)arr1[0], y[i+1]+(int)arr1[1]);//끝점을 기준으로 그리기
			        	g.drawLine(x[i+1]-35, y[i+1], x[i+1]-35+(int)arr1[2], y[i+1]+(int)arr1[3]);
		        	}
		        	g.drawLine(x[n-2]+35,y[n-2], x[n-1]+35, y[n-1]+10);
		        	int arrX2= x[n-1]-x[n-2];
		        	int arrY2 =y[n-1]+10-y[n-2];
		        	double arr2[]=p2Arrow(arrX2,arrY2);//회전 변환한 양쪽 벡터 반환
		        	g.drawLine(x[n-1]+35, y[n-1]+10, x[n-1]+35+(int)arr2[0], y[n-1]+10+(int)arr2[1]);//끝점을 기준으로 그리기
		        	g.drawLine(x[n-1]+35, y[n-1]+10, x[n-1]+35+(int)arr2[2], y[n-1]+10+(int)arr2[3]);
		        }
		    }

}
