package framework;

public class MiscHelper {
	//**VARIABLES**//
	
	/*map dimensions*/
	private final static double mapLength = 700;
	private final static double mapHeight = 520/700*mapLength;
	
	/*towers*/
	
	//priorities
	private final static String first = "FIRST";
	private final static String last = "LAST";
	private final static String strong = "STRONG";
	private final static String close = "CLOSE";
	
	//**GETTERS AND SETTERS**//
	
	/*map dimensions*/
	/**
	 * getMapLength
	 * @return the mapLength constant
	 */
	public static double getMapLength() {
		return mapLength;
	}
	/**
	 * getMapHeight
	 * @return the mapHeight constant
	 */
	public static double getMapHeight() {
		return mapHeight;
	}
	
	/*towers*/
	
	//priorities
	/**
	 * getPriorityFirst
	 * @return the string that represents priority first
	 */
	public static String getPriorityFirst() {
		return first;
	}
	/**
	 * getPriorityLast
	 * @return the string that represents priority last
	 */
	public static String getPriorityLast() {
		return last;
	}
	/**
	 * getPriorityStrong
	 * @return the string that represents priority strong
	 */
	public static String getPriorityStrong() {
		return strong;
	}
	/**
	 * getPriorityClose
	 * @return the string that represents priority close
	 */
	public static String getPriorityClose() {
		return close;
	}
	
	//**OTHER METHODS**//
	
}