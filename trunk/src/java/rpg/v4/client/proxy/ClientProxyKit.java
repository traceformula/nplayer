package rpg.v4.client.proxy;

import rpg.v4.middleware.proxy.Proxy;
import rpg.v4.server.proxy.ServerProxyKit;

/**
 * Loads the given proxy.
 */
public class ClientProxyKit
{
    public static Proxy CLIENT_PROXY = new InProcessProxy(ServerProxyKit.SERVER_PROXY);
}
