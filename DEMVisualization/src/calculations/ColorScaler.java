package calculations;

public class ColorScaler {
	double interval;
	double minValue;
	public ColorScaler(double minValue, double maxValue) {
		interval = maxValue-minValue;
		this.minValue = minValue;
	}
	public double getColorPercentage(double heightValue){
		if(heightValue < 0) {
			return (heightValue-minValue)/interval;
		} else 
		return heightValue/interval;
	}
	
}
