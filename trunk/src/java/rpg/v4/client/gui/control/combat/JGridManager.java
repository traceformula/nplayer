package rpg.v4.client.gui.control.combat;

import rpg.swingx.JTransparentPanel;
import rpg.swingx.JTransparentScrollPane;
import rpg.v4.client.gui.control.combat.shape.EntityGridShape;
import rpg.v4.client.gui.util.listener.AncestorChangeListener;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 02-Jan-2005
 * Time: 13:49:45
 *
 *
 */
public class JGridManager extends JTransparentPanel implements Observer
{
    private JGrid jgrid = new JGrid();
    private Map<V4EntityNameKeyPair, EntityGridShape> shapeMap = new HashMap<V4EntityNameKeyPair, EntityGridShape>(100);

    public JGridManager()
    {
        JScrollPane gridScrollPane = new JTransparentScrollPane(jgrid, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        gridScrollPane.setEnabled(false);
        this.add(gridScrollPane, BorderLayout.CENTER);

        InitiativeOrderPanel sidePanel = new InitiativeOrderPanel();
        addPropertyChangeListener(new AncestorChangeListener(sidePanel));

        ClientProxyKit.CLIENT_PROXY.getEntityInitiativeOrderList().addObserver(this);
    }

    public void update(Observable o, Object arg)
    {
        ObservableArrayList<V4EntityNameKeyPair> initiativeOrderList = (ObservableArrayList<V4EntityNameKeyPair>) o;

        // Clear all
        if (initiativeOrderList.size() == 0)
        {
            for (EntityGridShape shape : shapeMap.values())
            {
                jgrid.remove(shape.getShape2D());
                jgrid.remove(shape.getReachPanel2D());
            }
            shapeMap.clear();
        } else
        {
            for (V4EntityNameKeyPair nkp : initiativeOrderList)
            {
                if (!shapeMap.containsKey(nkp))
                {
                    EntityGridShape shape = new EntityGridShape(nkp);
                    shapeMap.put(nkp, shape);
                    jgrid.add(shape.getShape2D());
                    jgrid.add(shape.getReachPanel2D());
                }
            }
        }

        jgrid.repaint();
    }
}
