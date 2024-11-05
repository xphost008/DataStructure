package javaVersion.example;

import javaVersion.source.*;

public class MyTest {
    public static void main(String[] args) {
        testList();
        testStack();
        testQueue();
        testMap();
        testTree();
    }
    public static void testList() {
        MyList<Integer> array = new MyList<>();
        array.add(111);
        array.add(222);
        array.add(333);
        array.add(444);
        array.add(555);
        array.add(666);
        array.add(777);
        assert array.size() == 7;
        assert array.get(1).equals(222);
        array.remove(1);
        assert array.size() == 6;
        assert array.get(1).equals(333);
        array.set(1, 234);
        assert array.get(1).equals(234);
        array.insert(1, 890);
        assert array.get(1).equals(890);
        assert array.size() == 7;
        assert array.toString().equals("[111, 890, 234, 444, 555, 666, 777]");
        array.clear();
        assert array.toString().equals("[]");
    }
    public static void testQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enQueue(1);
        queue.enQueue(2);
        assert queue.size() == 2;
        queue.enQueue(3);
        assert queue.size() == 3;
        assert queue.toString().equals("[1, 2, 3]");
        assert queue.deQueue().equals(1);
        assert queue.deQueue().equals(2);
        assert queue.size() == 1;
        assert queue.peek().equals(3);
    }
    public static void testStack() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        assert stack.size() == 2;
        stack.push(3);
        assert stack.size() == 3;
        assert stack.toString().equals("[1, 2, 3]");
        assert stack.pop().equals(3);
        assert stack.pop().equals(2);
        assert stack.size() == 1;
        assert stack.peek().equals(1);
    }
    public static void testMap() {
        MyMap<String, String> map = new MyMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        assert map.size() == 3;
        assert map.get("key1").equals("value1");
        assert map.getOrDefault("key4", "value4").equals("value4");
        map.replace("key2", "value5");
        assert map.get("key2").equals("value5");
        assert map.toString().equals("{key1=value1, key2=value5, key3=value3}");
        assert map.containKey("key3");
        map.remove("key1");
        assert map.toString().equals("{key2=value5, key3=value3}");
        map.insert(1, "key6", "value6");
        assert map.toString().equals("{key2=value5, key6=value6, key3=value3}");
        map.clear();
        assert map.toString().equals("{}");
    }
    public static void testTree() {
        MyTree<String> tree = new MyTree<>();
        tree.addLeaf("aaa");
        tree.addLeaf("bbb");
        tree.addLeaf("ccc");
        tree.addLeaf("ddd");
        tree.addLeaf("eee");
        tree.addBranch();
        System.out.println(tree);
        tree.insertBranch(1);
        System.out.println(tree);
        tree.insertLeaf(2, "miao");
        System.out.println(tree);
        assert tree.size() == 8;
        assert tree.toString().equals("[aaa, [], miao, bbb, ccc, ddd, eee, []]");
        for(int i = 0; i < tree.size(); i++) {
            if(tree.isBranch(i)){
                tree.getBranch(i).addLeaf("111");
                tree.getBranch(i).addLeaf("222");
            }
        }
        assert tree.size() == 8;
        assert tree.toString().equals("[aaa, [111, 222], miao, bbb, ccc, ddd, eee, [111, 222]]");
        tree.removeNode(1);
        assert tree.toString().equals("[aaa, miao, bbb, ccc, ddd, eee, [111, 222]]");
        assert tree.isBranch(6);
        MyTree<String> tree2 = tree.getBranch(6);
        assert tree2.size() == 2;
        assert tree2.toString().equals("[111, 222]");
        assert tree2.getParent().toString().equals("[aaa, miao, bbb, ccc, ddd, eee, [111, 222]]");
        assert tree.getLeaf(3).equals("ccc");
        assert tree.isLeaf(2);
    }
}
