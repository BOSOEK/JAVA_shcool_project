package Test;

import java.awt.MouseInfo;
import java.awt.PointerInfo;

public class MousTest {
	
	public static void Tt() {
		for(int i = 0; i<10; i++) {
			PointerInfo pointerinfo = MouseInfo.getPointerInfo();
			System.out.println("Mouse Position : " + pointerinfo.getLocation());
			try {
				Thread.sleep(1000);
			} catch(InterruptedException ex) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		 PointerInfo pt = MouseInfo.getPointerInfo();
         while(true) {
                pt = MouseInfo.getPointerInfo();
                
                System.out.println(pt.getLocation()); // x,y 
                System.out.println(pt.getLocation().x);// x ÁÂÇ¥
                System.out.println(pt.getLocation().y); // y ÁÂÇ¥
         }

	}

}
