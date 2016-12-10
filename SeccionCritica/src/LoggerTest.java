import init.FileLogger;

public class LoggerTest {

	public static int metodo;

	public static void main(String[] args) {
		if (args.length > 0) {
			if (args[0].contentEquals("0")) {
				metodo = 0;
			} else if (args[0].contentEquals("1")) {
				metodo = 1;
			} else {
				metodo = 2;
			}

		} else {
			metodo = 0;
		}

		LoggerThread t1 = new LoggerThread("Hilo 1");
		LoggerThread t2 = new LoggerThread("Hilo 2");
		t1.start();
		t2.start();
	}
}

class LoggerThread extends Thread {
	private String message;
	FileLogger logger;

	LoggerThread(String m) {
		message = m;
	}

	public void run() {
		if (LoggerTest.metodo == 0) {
			orig.FileLogger logger = orig.FileLogger.getFileLogger();
			logger.log("orig: " + message);
		} else if (LoggerTest.metodo == 1) {
			init.FileLogger logger = init.FileLogger.getFileLogger();
			logger.log("init " + message);
		} else {
			crit.FileLogger logger = crit.FileLogger.getFileLogger();
			logger.log("crit " + message);
		}
	}
}
