package net.sf.jargsemsat.jargsemsat.alg;

public class Misc {

	static boolean unprinted = true;

	public static void disclaimer() {
		if (unprinted) {
			/*System.err.println("*************************");
			System.err
					.println("jArgSemSAT has been designed for providing an efficient, self-contained, Java library,");
			System.err.println(
					"therefore it is not suitable to be used for evaluating the performance of the ArgSemSAT approach.");
			System.err.println(
					"On this regard, the C++ version, available at http://sourceforge.net/projects/argsemsat/ , must be used.");
			System.err.println("************************* ");*/
			unprinted = false;
		}
	}

}
