package multiplication;

public class GradeSchoolAddition {

	public static void main(String[] args) {
		String first = "84998346593245920943579499943930239745023";
		String second = "798984880088030398677920987482020884200482";
		System.out.println(add(first, second));
	}

	public static String add(String first, String second) {
		if (first.length() != second.length()) {
			String lz = new String(new char[Math.abs(first.length() - second.length())]).replace("\0", "0");
			if (first.length() < second.length()) {
				first = lz + first;
			} else {
				second = lz + second;
			}
		}

		char[] farr = first.toCharArray();
		char[] sarr = second.toCharArray();
		char[] rarr = new char[first.length() + 1];
		int rIndex = 0;

		int carry = 0;
		for (int i = first.length() - 1; i >= 0; --i) {
			int sum = carry + Character.getNumericValue(farr[i]) + Character.getNumericValue(sarr[i]);
			char[] r = Integer.toString(sum).toCharArray();
			if (r.length == 2) {
				carry = Character.getNumericValue(r[0]);
				rarr[rIndex] = r[1];
			} else {
				rarr[rIndex] = r[0];
			}
			rIndex++;
		}
		rarr[rIndex] = Integer.toString(carry).toCharArray()[0];
		String rStr = new String(rarr);

		if (carry == 0) {
			rStr = rStr.substring(0, rStr.length() - 1);
		}

		return new StringBuilder(rStr).reverse().toString();
	}

}
