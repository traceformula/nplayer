package rpg.v4.server.proxy;

import rpg.v4.middleware.proxy.Proxy;

/**
 * Loads the given server proxy.
 */
public class ServerProxyKit
{
    public static Proxy SERVER_PROXY = new InProcessProxy();
}
