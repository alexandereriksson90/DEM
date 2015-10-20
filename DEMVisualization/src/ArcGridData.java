
public class ArcGridData {
	private double [][] arcGridData;
	private double xllCenter;
	private double yllCenter;
	private double cellSize;
	
	public ArcGridData(double xllCenter, double yllCenter, double cellSize) {
		this.xllCenter = xllCenter;
		this.yllCenter = yllCenter;
		this.cellSize = cellSize;
	}
	
	public void setArcGridData(double [][] arcGridData) {
		this.arcGridData = arcGridData;
		
	}
	public double [][] getArcGridData() {
		return arcGridData;
	}
	
	public double getXllCenter() {
		return xllCenter;
	}
	
	public double getYllCenter() {
		return yllCenter;
	}
	
	public double getCellSize() {
		return cellSize;
	}
	

}
