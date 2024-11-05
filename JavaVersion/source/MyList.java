package javaVersion.source;

import static javaVersion.source.MyConst.MAX_SIZE;

/**
 * 一个挺好用的数组列表~
 * @param <E>
 */
public class MyList<E> {
    private final E[] element;
    private short size;
    /**
     * 初始化一个新的可变Array结构
     */
    public MyList() {
        element = (E[]) new Object[MAX_SIZE];
        for (int i = 0; i < element.length; i++)
            element[i] = null;
        size = 0;
    }
    /**
     * 判空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * 通过索引获取一个值，如果索引超出范围，则抛出ArrayIndexOutOfBoundsException。
     * @param index 索引
     */
    public E get(int index){
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return element[index];
    }
    /**
     * 新增一个值。
     * @param value 索引
     */
    public void add(E value) {
        size++;
        if(size < 0) {
            size--;
            throw new ArrayIndexOutOfBoundsException(size + 1);
        }
        element[size - 1] = value;
    }
    /**
     * 在元素中间插入一个值
     * @param index 索引
     * @param value 值
     */
    public void insert(int index, E value) {
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        for(int i = size - 1; i >= index; i--) {
            element[i + 1] = element[i];
        }
        element[index] = value;
        size++;
    }
    /**
     * 清空所有数组列表元素
     */
    public void clear() {
        for (int i = 0, len = element.length; i < len; i++)
            element[i] = null;
        size = 0;
    }
    /**
     * 为某一索引的值重新赋值
     * @param index 索引
     * @param value 值
     */
    public void set(int index, E value) {
        if(index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        element[index] = value;
    }
    /**
     * 删除值
     * @param index 索引
     */
    public void remove(int index) {
        if(size <= 0 || index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        for(int i = index + 1; i < size; i++) {
            element[i - 1] = element[i];
        }
        element[size - 1] = null;
        size--;
    }
    /**
     * 获取长度
     * @return 长度
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
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < size; i++) {
            if(i >= size - 1){
                sb.append(element[i]);
            }else{
                sb.append(element[i]).append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
