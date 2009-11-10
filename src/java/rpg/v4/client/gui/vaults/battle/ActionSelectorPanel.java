package rpg.v4.client.gui.vaults.battle;

import org.apache.log4j.Logger;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.jaxb.V4GenericAction;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.server.battle.BattleStateEnum;
import rpg.v4.server.battle.BattleStateManager;
import rpg.v4.server.battle.EntityRoundManager;
import rpg.v4.server.battle.InitiativeOrderManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 2, 2009
 * Time: 8:50:38 PM
 * <p/>
 * Selectors for actions a user can take.
 */
public class ActionSelectorPanel extends JTransparentPanel implements Observer, PropertyChangeListener
{
    private static final Logger logger = Logger.getLogger(ActionSelectorPanel.class);
    private static final String SELECT_AN_ACTION = "select an action";
    private StringPicker actionPicker;
    private ObservableArrayList<String> actionList;

    public ActionSelectorPanel()
    {
        actionList = ClientProxyKit.CLIENT_PROXY.getAvailableActions();
        actionList.addObserver(this);

        actionPicker = new StringPicker(actionList);
        actionPicker.addPropertyChangeListener("text", this);
        update(actionList, null);

        JTransparentPanel p = new JTransparentPanel();
        p.add(actionPicker, BorderLayout.NORTH);

        add(p, BorderLayout.WEST);
        setBorder(BorderFactory.createEmptyBorder(0, 20, 2, 0));
        setVisible(false);

        BattleStateManager.instance.addObserver(this);
    }

    public void update(Observable observable, Object o)
    {
        if (observable == BattleStateManager.instance)
        {
            boolean isVisible = !BattleStateEnum.END_BATTLE.equals(o) &&
                    !BattleStateEnum.PRE_BATTLE.equals(o) &&
                    !BattleStateEnum.RESET.equals(o);
            setVisible(isVisible);
            repaint();
        } else if (observable == actionList)
        {
            List<String> copiedActionList = new ArrayList<String>(actionList);
            copiedActionList.add(0, SELECT_AN_ACTION);
            actionPicker.setDataRange(copiedActionList);
        }
    }


    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        if (!isVisible())
            return;
        
        String selectedText = actionPicker.getText();
        logger.info("Action selected: " + selectedText);

        if (!SELECT_AN_ACTION.equals(selectedText))
        {
            // Only active entity can can add actions for now.
            V4EntityNameKeyPair nkp = InitiativeOrderManager.getInstance().getActiveEntity();
            V4GenericAction v4GenericAction = ClientProxyKit.CLIENT_PROXY.getGenericAction(selectedText);
            v4GenericAction.setV4EntityNameKeyPair(nkp);
            EntityRoundManager.getInstance().addAction(v4GenericAction);
            actionPicker.setText(SELECT_AN_ACTION);
        }
    }
}
