package javaVersion.source;

import static javaVersion.source.MyConst.MAX_SIZE;

/**
 * 一个挺好用的键值对结构~
 * @param <K>
 * @param <V>
 */
public class MyMap<K, V> {
    private final K[] keys;
    private final V[] values;
    private int size;
    /**
     * 初始化一个新的键值对Map结构
     */
    public MyMap() {
        keys = (K[]) new Object[MAX_SIZE];
        values = (V[]) new Object[MAX_SIZE];
        for(int i = 0; i < MAX_SIZE; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
    }
    /**
     * 置一个值
     * @param key 键
     * @param value 值
     */
    public void put(K key, V value) {
        size++;
        if(size < 0) {
            size--;
            throw new ArrayIndexOutOfBoundsException(size + 1);
        }
        keys[size - 1] = key;
        values[size - 1] = value;
    }
    /**
     * 通过键获取一个值，如果键不存在，则抛出ArrayIndexOutOfBoundsException。
     * @param key 键
     */
    public V get(K key) {
        if(size <= 0)
            throw new ArrayIndexOutOfBoundsException(0);
        for(int i = 0; i < size; i++)
            if(key.equals(keys[i]))
                return values[i];
        return null;
    }
    /**
     * 通过键获取一个值，如果键对应的值不存在，则返回默认值。
     * @param key 获取键
     * @param defaultValue 默认值
     */
    public V getOrDefault(K key, V defaultValue) {
        V val = get(key);
        return val == null ? defaultValue : val;
    }
    /**
     * 删除键对应的键值
     * @param key 键
     */
    public void insert(int index, K key, V value) {
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        for(int i = size - 1; i >= index; i--) {
            keys[i + 1] = keys[i];
            values[i + 1] = values[i];
        }
        keys[index] = key;
        values[index] = value;
        size++;
    }
    public void remove(K key) {
        if(size <= 0)
            throw new ArrayIndexOutOfBoundsException(0);
        int index = -1;
        for(int i = 0; i < size; i++) {
            if(key.equals(keys[i])) {
                index = i;
                break;
            }
        }
        if(index == -1)
            return;
        for(int i = index + 1; i < size; i++){
            keys[i - 1] = keys[i];
            values[i - 1] = values[i];
        }
        keys[size - 1] = null;
        values[size - 1] = null;
        size--;
    }
    /**
     * 替换键值
     * @param key 已经有了的键值
     * @param value 要替换的值
     */
    public void replace(K key, V value) {
        for(int i = 0; i < size; i++) {
            if(key.equals(keys[i])) {
                values[i] = value;
                break;
            }
        }
    }
    /**
     * 是否包含某个键
     * @param key 键名
     * @return 是否包含
     */
    public boolean containKey(K key) {
        for(int i = 0; i < size; i++) {
            if(key.equals(keys[i])) {
                return true;
            }
        }
        return false;
    }
    /**
     * 清空所有键值对
     */
    public void clear() {
        for(int i = 0; i < size; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
    }
    /**
     * 判空
     * @return 是否为空
     */
    public boolean isEmpty(){
        return size == 0;
    }
    /**
     * 获取长度
     * @return 长度值
     */
    public int size() {
        return size;
    }
    /**
     * toString函数
     * @return 格式化输出
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for(int i = 0; i < size; i++) {
            if(i > 0)
                sb.append(", ").append(keys[i]).append("=").append(values[i]);
            else
                sb.append(keys[i]).append("=").append(values[i]);
        }
        sb.append("}");
        return sb.toString();
    }
}
