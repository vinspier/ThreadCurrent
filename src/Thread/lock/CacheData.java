package Thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 模拟一个获取数据的缓存系统
public class CacheData {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Map<String,Object> mapData = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(new CacheData().getData("a"));
    }

    public Object getData(String dataName){
        readWriteLock.readLock().lock();
        Object myData = null;
        try {
            myData = mapData.get(dataName);
            if(myData == null){
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    mapData.put("a","实际是去数据库取数据");
                    myData = mapData.get(dataName);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    readWriteLock.writeLock().unlock();
                }
                readWriteLock.readLock().lock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return myData;
    }
}
