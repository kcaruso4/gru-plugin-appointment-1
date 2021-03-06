package fr.paris.lutece.plugins.appointment.exception;

public class SlotFullException extends RuntimeException {

	private static final long serialVersionUID = 9155769692111357148L;
	
	  /**
     * Constructor
     *
     * @param strMessage
     *            The error message
     */
	public SlotFullException(String strMessage) {
		
		super( strMessage );
	}
	
    public SlotFullException(String strMessage, Exception e) {
		
		super( strMessage, e );
	}
}
