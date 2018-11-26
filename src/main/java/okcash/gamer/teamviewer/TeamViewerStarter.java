package okcash.gamer.teamviewer;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TeamViewerStarter {

	public void start() {
		try {
			Thread.sleep(15000);

			Robot testRobot = new Robot();
			testRobot.mouseMove(-1300, 330);
			testRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			testRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void mouseCenterPress() {
		try {
			Thread.sleep(500);
			
			Robot testRobot = new Robot();
			testRobot.mouseMove(-1200, 500);
			testRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			testRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(500);
			
			testRobot.keyPress(KeyEvent.VK_ENTER);
			testRobot.keyRelease(KeyEvent.VK_ENTER);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] ar) throws Exception {
		new TeamViewerStarter().mouseCenterPress();
	}

	public static boolean isProcessRunning(String serviceName) throws Exception {
		
		Process p = Runtime.getRuntime().exec("tasklist");
		InputStreamReader inputStreamReader = new InputStreamReader(p.getInputStream());
		BufferedReader reader = new BufferedReader(inputStreamReader);
		
		int lineNumber = 0;
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(lineNumber++ + " " + line);
			if (line.contains(serviceName)) {
				return true;
			}
		}
		return false;
	}

	public static void killProcess(String serviceName) throws Exception {
		Runtime.getRuntime().exec("taskkill /F /IM " + serviceName);
	}
}
