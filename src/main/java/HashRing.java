import java.util.SortedMap;
import java.util.TreeMap;

public class HashRing implements ConsistentHashing {
    private final HashFunction hashFunction;
    private SortedMap<String, ServerNode> ring;

    public HashRing(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
        this.ring = new TreeMap<>();
    }

    @Override
    public void add(ServerNode serverNode) {
        String id = serverNode.getId();
        ring.put(hashFunction.hash(id), serverNode);
    }

    @Override
    public void remove(ServerNode node) {
        ring.remove(hashFunction.hash(node.getId()));
    }

    @Override
    public ServerNode locate(Object object) {
        if (ring.isEmpty()) {
            return null;
        }
        String keyHash = hashFunction.hash(object.getKey());
        SortedMap<String, ServerNode> tailMap = ring.tailMap(keyHash);

        String serverHash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(serverHash);
    }
}
