package icircles.gui;

public class CirclesMain {

    public static void main(String args[]) {
        
    	//new CirclesFrame();
    	//System.out.println(args[0]);
    	//System.out.println();
    	if (args[0] != null){
    		new CirclesCmd(args[0]);
    	} else {
    		System.out.println("Enter the input correctly");
    	}
        
    }
}
