package rpg.v4.client.gui.edit.renderer;

import static rpg.swingx.ColorConstants.ROW_TRANSPARENCY;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.edit.renderer.impl.StateGroupHeader;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Support updating a particular state.
 */
public abstract class StateGroup extends JTransparentPanel
{
    protected Map<String, ContentBlock> rowMap;
    // Used to maintain an alphabetically sorted list of the state ids. This again
    // is used to insert the new ContentBlock in the appropriate position.
    private List<String> stateIDList;
    protected Box box;

    public StateGroup(State state)
    {
        rowMap = new HashMap<String, ContentBlock>();
        stateIDList = new ArrayList<String>(20);
        box = Box.createVerticalBox();

        add(new StateGroupHeader(state.getCategory(), state.getSupportedModifierTypes()), BorderLayout.NORTH);
        add(box, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
    }

    public void clearGroup()
    {
        for (ContentBlock block : rowMap.values())
        {
            box.remove(block);
        }

        stateIDList.clear();
        rowMap.clear();
    }

    public void updateState(State state)
    {
        if (rowMap.keySet().contains(state.getStateID()))
        {
            rowMap.get(state.getStateID()).updateState(state);
        } else
        {
            stateIDList.add(state.getStateID());
            Collections.sort(stateIDList);
            int positionToAdd = stateIDList.indexOf(state.getStateID());

            ContentBlock block = createBlock(state);
            rowMap.put(state.getStateID(), block);

            updateRowHighlight();
            box.add(block, positionToAdd);
            box.revalidate();
            box.repaint();
        }
    }

    private void updateRowHighlight()
    {
        for (String stateID : stateIDList)
        {
            int rowNumber = stateIDList.indexOf(stateID);
            ContentBlock block = rowMap.get(stateID);

            // Create alternated row highlight.
            boolean isOpaque = rowNumber % 2 == 0;
            block.setOpaque(isOpaque);
            block.setBackground(ROW_TRANSPARENCY);

        }
    }

    public abstract ContentBlock createBlock(State state);
}
