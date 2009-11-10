package rpg.swingx;

import rpg.swingx.ui.CheckBoxUI;

import javax.swing.*;

/**
 * To change this template use File | Settings | File Templates.
 */
public class JDarkCheckBox extends JCheckBox
{
    public JDarkCheckBox()
    {
        initUIModel();
    }

    public JDarkCheckBox(Action a)
    {
        super(a);
        initUIModel();
    }

    public JDarkCheckBox(Icon icon)
    {
        super(icon);
        initUIModel();
    }

    public JDarkCheckBox(Icon icon, boolean selected)
    {
        super(icon, selected);
        initUIModel();
    }

    public JDarkCheckBox(String text)
    {
        super(text);
        initUIModel();
    }

    public JDarkCheckBox(String text, Icon icon)
    {
        super(text, icon);
        initUIModel();
    }

    public JDarkCheckBox(String text, Icon icon, boolean selected)
    {
        super(text, icon, selected);
        initUIModel();
    }

    public JDarkCheckBox(String text, boolean selected)
    {
        super(text, selected);
        initUIModel();
    }

    private void initUIModel()
    {
        setUI(new CheckBoxUI());
    }
}
