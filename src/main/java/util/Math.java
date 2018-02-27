package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Math {

	public static double round(double number) {
		DecimalFormat df = new DecimalFormat("#.##");      
		number = Double.valueOf(df.format(number));
		return number;
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static void main(String[] a) {
		System.err.println(round(0.01528257131576538,2));
		System.err.println(round(0.026696214452385902,2));
		System.err.println(round(0.04979443550109863,2));
	}
}
