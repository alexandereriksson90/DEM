package calculations;
/**
 * Ber�knar intensiteten p� f�rgerna!
 * @author ndi13jed
 * @author nbt12aen

 */
public class ColorScaler {
	double interval;
	double minValue;
	public ColorScaler(double minValue, double maxValue) {
		interval = maxValue-minValue;
		this.minValue = minValue;
	}
	/**
	 * Returnerar vilken f�rg intensitet en viss punkt ska ha!
	 * @param heightValue h�jd v�rdet f�r en punkt
	 * @return Procentv�rdet mellan 0 och 1 d�r 0 �r 0% och 1 �r 100%
	 */
	public double getColorPercentage(double heightValue){
		if(heightValue < 0) {
			return (heightValue-minValue)/interval;
		} else 
			
		return heightValue/interval;
	}
	
}
