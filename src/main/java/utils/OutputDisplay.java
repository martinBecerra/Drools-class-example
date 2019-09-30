package utils;

public class OutputDisplay {

	public void showText(String text) {
		long time = System.currentTimeMillis();
		System.out.println("time="+time+"-"+text);
	}
}
