package BufferCache;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Draw extends JPanel{//�׸��� �׸��� Ŭ����
		int x[];
		int y[];
		int n=0;
		public void setLine(int[] x,int[] y,int n) {//������ �� end point�� �迭�� �����ϰ� ������ �ʱ�ȭ �ϴ� �Լ�
			this.x=x;
			this.y=y;
			this.n=n;
		}
		public double[] p2Arrow(int x, int y) {//ȭ��ǥ ����� �Լ�, ���� ���� �� 135��, -135�� ȸ����ȯ
			double vecX=x;
			double vecY=y;
			double X1=(-0.7)*vecX+(-0.7)*vecY;
			double Y1=(0.7)*vecX+(-0.7)*vecY;
			double X2=(-0.7)*vecX+(0.7)*vecY;
			double Y2=(-0.7)*vecX+(-0.7)*vecY;
			double size1=Math.sqrt(Math.pow(X1,2)+Math.pow(Y1, 2));
			double size2=Math.sqrt(Math.pow(X2,2)+Math.pow(Y2, 2));
			X1/=size1;//�������� ����
			Y1/=size1;
			X2/=size2;
			Y2/=size2;
			double arr[]=new double[4];
			arr[0]=X1*10;//������ 10�� ����
			arr[1]=Y1*10;
			arr[2]=X2*10;
			arr[3]=Y2*10;
			return arr;
		}
		public void paint(Graphics g) { //��������Ʈ �� �׸��� �Լ�
		        super.paint(g);
		        if(n!=0)//ȭ��ǥ ���� 0���̸� x,y���� �ʰ� ȭ��ǥ �׸��� �ʱ�
		        {
		        	g.drawLine(x[0]+35,y[0]-10, x[1]-35, y[1]);
		        	int arrX= x[1]-35-x[0]-35;
		        	int arrY= y[1]-y[0]+10;
		        	double arr[]=p2Arrow(arrX,arrY);//ȸ�� ��ȯ�� ���� ���� ��ȯ
		        	g.drawLine(x[1]-35, y[1], x[1]-35+(int)arr[0], y[1]+(int)arr[1]);//������ �������� �׸���
		        	g.drawLine(x[1]-35, y[1], x[1]-35+(int)arr[2], y[1]+(int)arr[3]);
		        	for(int i=1;i<n-2;i++) {
		        		g.drawLine(x[i]+35, y[i], x[i+1]-35, y[i+1]);
		        		int arrX1= x[i+1]-35-x[i]-35;
			        	int arrY1= y[i+1]-y[i];
			        	double arr1[]=p2Arrow(arrX1,arrY1);//ȸ�� ��ȯ�� ���� ���� ��ȯ
			        	g.drawLine(x[i+1]-35, y[i+1], x[i+1]-35+(int)arr1[0], y[i+1]+(int)arr1[1]);//������ �������� �׸���
			        	g.drawLine(x[i+1]-35, y[i+1], x[i+1]-35+(int)arr1[2], y[i+1]+(int)arr1[3]);
		        	}
		        	g.drawLine(x[n-2]+35,y[n-2], x[n-1]+35, y[n-1]+10);
		        	int arrX2= x[n-1]-x[n-2];
		        	int arrY2 =y[n-1]+10-y[n-2];
		        	double arr2[]=p2Arrow(arrX2,arrY2);//ȸ�� ��ȯ�� ���� ���� ��ȯ
		        	g.drawLine(x[n-1]+35, y[n-1]+10, x[n-1]+35+(int)arr2[0], y[n-1]+10+(int)arr2[1]);//������ �������� �׸���
		        	g.drawLine(x[n-1]+35, y[n-1]+10, x[n-1]+35+(int)arr2[2], y[n-1]+10+(int)arr2[3]);
		        }
		    }

}
