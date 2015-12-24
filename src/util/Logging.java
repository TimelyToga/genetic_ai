package util;

import game.G;

public class Logging {
	
	public static final int ALL = 0;
	public static final int ERROR = 1;
	public static final int WARNING = 2;
	public static final int DEBUG = 3;
	public static final int VERBOSE = 4;
	
	private static int curDefault = ALL;
	
	/** 
	 * Print a String with a newline at curDefault LogLevel
	 * 
	 * System.out.println();
	 * @param s: String in question
	 */
	public static void log(String s) {
		log(s, curDefault);
	}
	
	/**
	 * Print a String without a new line at curDefault LogLevel
	 * 
	 * System.out.print();
	 * @param s: String in question
	 */
	public static void logWord(String s){
		logWord(s, curDefault);
	}
	
	/**
	 * Print a String with a newline.
	 * 
	 * System.out.println();
	 * @param s: String in question
	 * @param logLevel
	 */
	public static void log(String s, int logLevel) {
		if(G.CUR_LOG_LEVEL >= logLevel) {
			System.out.println(s);
		}
	}
	
	/**
	 * Print a String without a new line.
	 * 
	 * System.out.print();
	 * @param s: String in question
	 * @param logLevel: final int in Logging class that describes Logging detail you want
	 */
	public static void logWord(String s, int logLevel){
		if(G.CUR_LOG_LEVEL >= logLevel) {
			System.out.print(s);
		}
	}
}

