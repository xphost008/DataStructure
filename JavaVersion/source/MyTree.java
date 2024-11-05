package javaVersion.source;

import static javaVersion.source.MyConst.MAX_SIZE;

/**
 * 一个挺好用的顺序树结构~
 * @param <E>
 */
public class MyTree<E> {
    private final MyTree<E> parent;
    private final TreeNode<E>[] current;
    private int size;
    /**
     * 构造一个初始的树状结构
     */
    public MyTree() {
        parent = null;
        current = (TreeNode<E>[]) new TreeNode[MAX_SIZE];
        for(int i = 0; i < MAX_SIZE; i++) {
            current[i] = new TreeNode<>();
        }
        size = 0;
    }
    /**
     * 构建一个拥有父节点的子树，与addBranch不同，该种方式不影响原先的父类。
     * @param parent 填入父结点
     */
    public MyTree(MyTree<E> parent) {
        this.parent = parent;
        current = (TreeNode<E>[]) new TreeNode[MAX_SIZE];
        for(int i = 0; i < MAX_SIZE ; i++) {
            current[i] = new TreeNode<>();
        }
        size = 0;
    }
    /**
     * 为该父节点新增一个子节点
     */
    public void addBranch() {
        size++;
        if(size < 0) {
            size--;
            throw new ArrayIndexOutOfBoundsException(size + 1);
        }
        current[size - 1].setBranch(new MyTree<>(this));
    }
    /**
     * 为该子节点新增一个叶子
     * @param value 叶子的值
     */
    public void addLeaf(E value) {
        size++;
        if(size < 0) {
            size--;
            throw new ArrayIndexOutOfBoundsException(size + 1);
        }
        current[size - 1].setLeaf(value);
    }
    /**
     * 插入节点
     * @param index 索引
     */
    public void insertBranch(int index) {
        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        for(int j = size - 1; j >= index; j--) {
            MyTree<E> tmp1 = current[j].getBranch();
            E tmp2 = current[j].getLeaf();
            current[j + 1].setBranch(current[j].getBranch());
            current[j + 1].setLeaf(current[j].getLeaf());
        }
        current[index].setLeaf(null);
        current[index].setBranch(new MyTree<>(this));
        size++;
    }
    /**
     * 插入叶子
     * @param index 索引
     * @param value 值
     */
    public void insertLeaf(int index, E value) {
        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        for(int j = size - 1; j >= index; j--) {
            current[j + 1].setBranch(current[j].getBranch());
            current[j + 1].setLeaf(current[j].getLeaf());
        }
        current[index].setBranch(null);
        current[index].setLeaf(value);
        size++;
    }
    /**
     * 判断该索引下是否为叶子。
     * @param index 填入索引
     * @return 是否为叶子
     */
    public boolean isLeaf(int index) {
        if(index < 0 || index >= size)
            return false;
        return current[index].getLeaf() != null;
    }
    /**
     * 判断该索引下是否为节点。
     * @param index 填入索引
     * @return 是否为节点
     */
    public boolean isBranch(int index) {
        if(index < 0 || index >= size)
            return false;
        return current[index].getBranch() != null;
    }
    /**
     * 获取该叶子值（如果不是叶子，则会返回null）
     * @param index 填入索引
     * @return 叶子值
     */
    public E getLeaf(int index) {
        if(index < 0 || index >= size || !isLeaf(index))
            return null;
        return current[index].getLeaf();
    }
    /**
     * 获取该节点（如果不属于节点，则返回null）。
     * @param index 填入索引
     * @return 是否为节点
     */
    public MyTree<E> getBranch(int index) {
        if(index < 0 || index >= size || !isBranch(index))
            return null;
        return current[index].getBranch();
    }
    /**
     * 获取父节点
     * @return 父节点，如果父节点为null，则已经到达了根节点
     */
    public MyTree<E> getParent() {
        return parent;
    }
    /**
     * 获取当前节点含有多少分叉
     * @return 分叉数量
     */
    public int size() {
        return size;
    }
    /**
     * 移除节点
     * @param index 索引
     */
    public void removeNode(int index) {
        if(size <= 0 || index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        for(int i = index + 1; i < size; i++) {
            current[i - 1].setBranch(current[i].getBranch());
            current[i - 1].setLeaf(current[i].getLeaf());
        }
        current[size - 1].clear();
        size--;
    }
    /**
     * 判空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * 清空树状结构
     */
    public void clear() {
        for(int i = 0; i < size; i++) {
            current[i].clear();
        }
        size = 0;
    }
    /**
     * 私有类，用于描述一个节点值（可能为分叉，也可能为值）
     */
    private static class TreeNode<E> {
        private E leaf;
        private MyTree<E> branch;
        public TreeNode() {
            this.leaf = null;
            this.branch = null;
        }
        public void setBranch(MyTree<E> branch) {
            this.branch = branch;
        }
        public MyTree<E> getBranch() {
            return branch;
        }
        public void setLeaf(E leaf) {
            this.leaf = leaf;
        }
        public E getLeaf() {
            return leaf;
        }
        public void clear() { leaf = null; branch = null; }
    }
    /**
     * 私有格式化输出
     */
    private String output(MyTree<E> tree) {
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < tree.size(); i++) {
            String s;
            if(tree.isLeaf(i)) {
                s = tree.getLeaf(i).toString();
            }else{
                s = output(tree.getBranch(i));
            }
            if(i == 0) {
                sb.append(s);
            }else{
                sb.append(", ").append(s);
            }
        }
        sb.append("]");
        return sb.toString();
    }
    /**
     * toString函数
     * @return 格式化输出
     */
    @Override
    public String toString() {
        return output(this);
    }
}
