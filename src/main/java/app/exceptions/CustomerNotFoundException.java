package app.exceptions;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(int id) {
        super(String.format("Покупатель с идетификатором %d не найден", id));
    }
}
