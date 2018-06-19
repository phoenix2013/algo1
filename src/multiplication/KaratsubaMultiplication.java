package multiplication;

import java.math.BigInteger;

/*
 * to multiply two n digit numbers x and y, write x as a * 10^n/2 + b and y as c * 10^n/2 + d
 * the answer is ac * 10 ^ n + ((a + b)(c + d) - ac - bd) * 10 ^ n/2 + bd
 * all multiplications in the above expression are performed recursively
 * single digit multiplications use int data type
 * all additions and subtractions use BigInteger data type. a possible improvement is 
 * to remove this dependency using grade school addition and subtraction algorithms * 
 */
public class KaratsubaMultiplication {

	public static void main(String[] args) {
		// double first =
		// 3141592653589793238462643383279502884197169399375105820974944592d;
		// double second =
		// 2718281828459045235360287471352662497757247093699959574966967627d;

		String first = "5619452856";
		String second = "9165728456";

		System.out.println(multiply(first, second));
		// System.out.println(String.format("%.0f", 5.44d));
	}

	public static String multiply(String first, String second) {
		if (first.length() == 1 && second.length() == 1) {
			System.out.println(first + " * " + second + " = "
					+ Integer.toString(Integer.parseInt(first) * Integer.parseInt(second)));
			return Integer.toString(Integer.parseInt(first) * Integer.parseInt(second));
		}

		if (first.length() != second.length()) {
			String lz = new String(new char[Math.abs(first.length() - second.length())]).replace("\0", "0");
			if (first.length() < second.length()) {
				first = lz + first;
			} else {
				second = lz + second;
			}
		}

		String[] fss = splitInTwo(first);
		String[] sss = splitInTwo(second);

		String a = fss[0];
		String b = fss[1];
		String c = sss[0];
		String d = sss[1];
		int n = fss[1].length();

		String ac = multiply(a, c);
		String bd = multiply(b, d);
		String apbcpd = multiply(new BigInteger(a).add(new BigInteger(b)).toString(),
				new BigInteger(c).add(new BigInteger(d)).toString());
		String apbcpdmacmbd = new BigInteger(apbcpd).add(new BigInteger(ac).negate()).add(new BigInteger(bd).negate())
				.toString();
		ac = ac + new String(new char[n + n]).replace("\0", "0");
		apbcpdmacmbd = apbcpdmacmbd + new String(new char[n]).replace("\0", "0");
		System.out.println(first + " * " + second + " = "
				+ new BigInteger(ac).add(new BigInteger(apbcpdmacmbd)).add(new BigInteger(bd)).toString());
		return new BigInteger(ac).add(new BigInteger(apbcpdmacmbd)).add(new BigInteger(bd)).toString();
	}

	private static String[] splitInTwo(String number) {
		int n = number.length();
		if (n % 2 != 0) {
			number = "0" + number;
			n++;
		}
		System.out.println(number + " -> " + number.substring(0, n / 2) + " " + number.substring(n / 2, n));
		return new String[] { number.substring(0, n / 2), number.substring(n / 2, n) };
	}

}