package calculations;

public class ColorScaler {
	double interval;
	public ColorScaler(double minValue, double maxValue) {
		interval = maxValue-minValue;
//		interval =80;
	}
	public double getColorPercentage(double heightValue){
		return heightValue/interval;
	}
	
}
