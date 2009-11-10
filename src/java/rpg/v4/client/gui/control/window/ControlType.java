package rpg.v4.client.gui.control.window;

/**
 * Maintains the disposable actions and display name for tooltip texts.
 */
public enum ControlType
{
    MINIMIZE("Minimize"),
    MAXIMIZE("Maximize"),
    CLOSE("Close");

    private String name;

    ControlType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
