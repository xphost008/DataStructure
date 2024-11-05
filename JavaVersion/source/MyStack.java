package javaVersion.source;

import static javaVersion.source.MyConst.MAX_SIZE;

/**
 * 一个挺好用的栈~
 * @param <E>
 */
public class MyStack<E> {
    private final E[] element;
    private int size;
    /**
     * 构造一个新的栈
     */
    public MyStack() {
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
     * 将值压入栈
     * @param value 值
     */
    public void push(E value) {
        element[size] = value;
        size++;
    }
    /**
     * 从栈顶取出值
     * @return 栈值
     */
    public E pop() {
        if(size < 1) throw new ArrayIndexOutOfBoundsException("empty stack!!");
        size--;
        E e = element[size];
        element[size] = null;
        return e;
    }
    /**
     * 从栈顶检查值
     * @return 栈值
     */
    public E peek() {
        if(size < 1) throw new ArrayIndexOutOfBoundsException("empty stack!!");
        return element[size - 1];
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
