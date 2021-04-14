package cz.simecek.scheduler.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String entityName, Long id) {
        super("Could not find " + entityName + " id: "+ id);
    }
}
