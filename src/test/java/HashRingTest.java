import org.junit.Test;

import java.util.SortedMap;

public class HashRingTest {
    
    @Test
    public void hashRingTest() {
        HashFunction hashFunction = new HashFunction("sha-1");

        HashRing hashRing = new HashRing(hashFunction);

        String[] serverNodeName = {"ServerNode_A", "ServerNode_B", "ServerNode_C", "ServerNode_D"};
        for(String name : serverNodeName) {
            System.out.println("Adding " + name);
            hashRing.add(new ServerNode(name));
        }

        {
            String[] objectKeyId = {"1", "2", "3", "4"};
            for(String id: objectKeyId) {
                String key = "Object_" + id;
                Object object = new Object(key, id);
                ServerNode node = hashRing.locate(object);
                System.out.println("ServerNode for Object " + object.getKey() + " is " + node.getId());
            }
        }
        
        String name = "ServerNode_A";
        System.out.println("Removing ServerNode :" + name);
        hashRing.remove(new ServerNode(name));

        {
            String[] objectKeyId = {"1", "2", "3", "4"};
            for(String id: objectKeyId) {
                String key = "Object_" + id;
                Object object = new Object(key, id);
                ServerNode node = hashRing.locate(object);
                System.out.println("ServerNode for Object " + object.getKey() + " is " + node.getId());
            }
        }
        
    }
}
