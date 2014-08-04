package facturatron.omoikane.exceptions;

public class TicketFacturadoException extends Exception {
    public TicketFacturadoException(String message, Throwable cause) {
        super(message, cause);
    }
    public TicketFacturadoException(String message) {
        super(message);
    }
}
