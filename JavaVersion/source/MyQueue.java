package javaVersion.source;

import static javaVersion.source.MyConst.MAX_SIZE;

/**
 * 一个挺好用的队列~
 * @param <E>
 */
public class MyQueue<E>{
    private final E[] element;
    private int size;
    /**
     * 构造函数，构造一个初始的队列
     */
    public MyQueue() {
        element = (E[]) new Object[MAX_SIZE];
        for (int i = 0; i < element.length; i++)
            element[i] = null;
        size = 0;
    }
    /**
     * 获取长度
     * @return 长度
     */
    public int size() {
        return size;
    }
    /**
     * 判空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * 入队
     * @param value 值
     */
    public void enQueue(E value) {
        element[size] = value;
        size++;
    }
    /**
     * 出队
     * @return 值
     */
    public E deQueue() {
        if(size < 1) throw new ArrayIndexOutOfBoundsException("empty queue");
        E e = element[0];
        System.arraycopy(element, 1, element, 0, size);
        size--;
        return e;
    }
    /**
     * 检查队首值
     * @return 值
     */
    public E peek() {
        if(size <= 0) throw new ArrayIndexOutOfBoundsException("empty queue!");
        return element[0];
    }
    /**
     * toString函数
     * @return 格式化输出
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size; i++){
            if(i == size - 1){
                s.append(element[i]);
            }else{
                s.append(element[i]).append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
}
