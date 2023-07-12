import org.junit.Test;

public class HashRingTest {

    @Test
    public void hashRingTest() {
        HashFunction hashFunction = new HashFunction("sha-1");
        HashRing hashRing = new HashRing(hashFunction);

        addServerNodes(hashRing);
        locateAllObjects(hashRing);
        removeServerNode(hashRing, "ServerNode_A");
        locateAllObjects(hashRing);
    }

    @Test
    public void hashRingTestWithVNodes() {
        HashFunction hashFunction = new HashFunction("sha-1");
        HashRing hashRing = new HashRing(hashFunction, 3);

        addServerNodes(hashRing);
        locateAllObjects(hashRing);
        removeServerNode(hashRing, "ServerNode_A");
        locateAllObjects(hashRing);
    }

    private void removeServerNode(HashRing hashRing, String name) {
        System.out.println("Removing ServerNode :" + name);
        hashRing.remove(new ServerNode(name));
    }
    
    private void locateAllObjects(HashRing hashRing) {
        String[] objectKeyId = {"1", "2", "3", "4"};
        for (String id : objectKeyId) {
            String key = "Object_" + id;
            Object object = new Object(key, id);
            ServerNode node = hashRing.locate(object);
            System.out.println("ServerNode for Object " + object.getKey() + " is " + node.getId());
        }
    }

    private void addServerNodes(HashRing hashRing) {
        String[] serverNodeName = {"ServerNode_A", "ServerNode_B", "ServerNode_C", "ServerNode_D"};
        for (String name : serverNodeName) {
            System.out.println("Adding " + name);
            hashRing.add(new ServerNode(name));
        }
    }
}
