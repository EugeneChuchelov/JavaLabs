package io;

import barBossHouse.Order;

public interface FileSource extends Source<Order> {
    public void setPath(String path);
    public String getPath();
}
