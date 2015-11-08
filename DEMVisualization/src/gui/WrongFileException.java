package gui;
/**
 * För fel som uppstår vid val av fel sorters filer
 * @author ndi13jed
 * @author nbt12aen
 *
 */
public class WrongFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4838176821117134452L;
	public WrongFileException(String message){
		super(message);
	}
}
