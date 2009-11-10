package rpg.v4.server.state;

/**
 * Will observe one or more states and will then apply some function to these states as they update.
 */
public interface Function
{
    public void addStateToObserve(State state);

    public void setOwner(State owner);
}
