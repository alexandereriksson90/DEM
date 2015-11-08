package calculations;
/**
 * Beräknar intensiteten på färgerna!
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
	 * Returnerar vilken färg intensitet en viss punkt ska ha!
	 * @param heightValue höjd värdet för en punkt
	 * @return Procentvärdet mellan 0 och 1 där 0 är 0% och 1 är 100%
	 */
	public double getColorPercentage(double heightValue){
		if(heightValue < 0) {
			return (heightValue-minValue)/interval;
		} else 
			
		return heightValue/interval;
	}
	
}
