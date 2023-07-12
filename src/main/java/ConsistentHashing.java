public interface ConsistentHashing {
    public void add(ServerNode node);
    public void remove(ServerNode node);
    public ServerNode locate(Object object);
}
