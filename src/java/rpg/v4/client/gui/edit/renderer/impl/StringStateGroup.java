package rpg.v4.client.gui.edit.renderer.impl;

import rpg.v4.client.gui.edit.renderer.ContentBlock;
import rpg.v4.client.gui.edit.renderer.StateGroup;
import static rpg.v4.client.proxy.ClientProxyKit.CLIENT_PROXY;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Shows the string states and allows modifications.
 * <p/>
 * TODO: Create files with options and, based on name match with state id, load options.
 */
public class StringStateGroup extends StateGroup
{
    private static final List<String> GENDER_LIST, ALIGNMENT_LIST, VISION_LIST, SIZE_LIST, SETUP, INTEGER_LIST;
    private int addedRows = 0;
    private int columns = 2;
    private List<Box> boxes;
    private boolean isProficiencies;
    private int entriesPrColumn;


    static
    {
        GENDER_LIST = new ArrayList<String>(3);
        GENDER_LIST.add("Male");
        GENDER_LIST.add("Female");
        GENDER_LIST.add("Other");

        ALIGNMENT_LIST = new ArrayList<String>(6);
        ALIGNMENT_LIST.add("Good");
        ALIGNMENT_LIST.add("Lawful Good");
        ALIGNMENT_LIST.add("Evil");
        ALIGNMENT_LIST.add("Chaotic Evil");
        ALIGNMENT_LIST.add("Unaligned");
        ALIGNMENT_LIST.add("Other");

        VISION_LIST = new ArrayList<String>(3);
        VISION_LIST.add("Normal");
        VISION_LIST.add("Low-light");
        VISION_LIST.add("Darkvision");

        SIZE_LIST = new ArrayList<String>();
        SIZE_LIST.add("Medium");
        SIZE_LIST.add("Tiny");
        SIZE_LIST.add("Small");
        SIZE_LIST.add("Large");
        SIZE_LIST.add("Huge");
        SIZE_LIST.add("Gargantuan");

        SETUP = new ArrayList<String>(20);
        SETUP.add("Race");
        SETUP.add("Character class");
        SETUP.add("Paragon path");
        SETUP.add("Size");
        SETUP.add("Gender");
        SETUP.add("Vision");
        SETUP.add("Alignment");
        SETUP.add("Speed");
        SETUP.add("Level");
        SETUP.add("Name");
        SETUP.add("Player name");
        SETUP.add("Height");
        SETUP.add("Age");
        SETUP.add("Deity");
        SETUP.add("Experience points");

        INTEGER_LIST = new ArrayList<String>(101);
        for (int i = 0; i < 101; i++)
        {
            INTEGER_LIST.add(""+i);
        }
    }

    public StringStateGroup(State state)
    {
        super(state);
        isProficiencies = state.getStateID().contains("prof.");
        columns = isProficiencies ? 1 : 2;
        Box collectionBox = Box.createHorizontalBox();

        boxes = new ArrayList<Box>(columns);
        for (int i = 0; i < columns; i++)
        {
            boxes.add(Box.createVerticalBox());
            collectionBox.add(boxes.get(i));
        }

        box.add(collectionBox);
        entriesPrColumn = ((SETUP.size() + 1) / columns);
    }

    public ContentBlock createBlock(State state)
    {
        ContentBlock block;

        if (state.getStateID().equals("Race"))
        {
            block = new StringPickerRow(state, CLIENT_PROXY.getAvailableRaces());
        } else if (state.getStateID().equals("Character class"))
        {
            block = new StringPickerRow(state, CLIENT_PROXY.getAvailableClasses());
        } else if (state.getStateID().equals("Paragon path"))
        {
            block = new StringPickerRow(state, CLIENT_PROXY.getAvailableParagonPaths());
        } else if (state.getStateID().equals("Gender"))
        {
            block = new StringPickerRow(state, GENDER_LIST);
        } else if (state.getStateID().equals("Alignment"))
        {
            block = new StringPickerRow(state, ALIGNMENT_LIST);
        } else if (state.getStateID().equals("Vision"))
        {
            block = new StringPickerRow(state, VISION_LIST);
        } else if (state.getStateID().equals("Size"))
        {
            block = new StringPickerRow(state, SIZE_LIST);
        } else
        {
            block = new StringStateRow(state);
        }

        return block;
    }

    @Override
    public void updateState(State state)
    {
        if (rowMap.keySet().contains(state.getStateID()))
        {
            rowMap.get(state.getStateID()).updateState(state);
        } else
        {
            Box alternatingBox = boxes.get(addedRows % columns);
            ContentBlock block = createBlock(state);
            rowMap.put(state.getStateID(), block);

            if (isProficiencies)
            {
                alternatingBox.add(block);
            } else
            {
                setupPredfinedComponents();
            }
            box.revalidate();
            box.repaint();
            addedRows++;
        }
    }

    private void setupPredfinedComponents()
    {
        for (Box box : boxes)
        {
            box.removeAll();
        }

        for (int i = 0; i < SETUP.size(); i++)
        {
            String key = SETUP.get(i);
            if (rowMap.keySet().contains(key))
            {
                int columnIndex = i / entriesPrColumn;

                Box column = boxes.get(columnIndex);
                ContentBlock block = rowMap.get(key);
                column.add(block);
            }
        }
    }

    public static List<String> getList(String stateID)
    {
        if (stateID.equals("Race"))
        {
            return CLIENT_PROXY.getAvailableRaces();
        } else if (stateID.equals("Character class"))
        {
            return CLIENT_PROXY.getAvailableClasses();
        } else if (stateID.equals("Paragon path"))
        {
            return CLIENT_PROXY.getAvailableParagonPaths();
        } else if (stateID.equals("Gender"))
        {
            return GENDER_LIST;
        } else if (stateID.equals("Alignment"))
        {
            return ALIGNMENT_LIST;
        } else if (stateID.equals("Vision"))
        {
            return VISION_LIST;
        } else if (stateID.equals("Size"))
        {
            return SIZE_LIST;
        }

        return INTEGER_LIST;
    }

}
