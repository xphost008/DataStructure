from DataStructure.PythonVersion.source.MyList import MyList
from DataStructure.PythonVersion.source.MyMap import MyMap
from DataStructure.PythonVersion.source.MyQueue import MyQueue
from DataStructure.PythonVersion.source.MyStack import MyStack
from DataStructure.PythonVersion.source.MyTree import MyTree


def test_list():
    mylist = MyList[int]()
    mylist.add(111)
    mylist.add(222)
    mylist.add(333)
    mylist.add(444)
    mylist.add(555)
    mylist.add(666)
    mylist.add(777)
    assert len(mylist) == 7
    assert mylist.get(1) == 222
    mylist.remove(1)
    assert len(mylist) == 6
    assert mylist.get(1) == 333
    mylist.set(1, 234)
    assert mylist.get(1) == 234
    mylist.insert(1, 890)
    assert mylist.get(1) == 890
    assert len(mylist) == 7
    assert str(mylist) == '[111, 890, 234, 444, 555, 666, 777]'
    mylist.clear()
    assert mylist.is_empty()

def test_map():
    mymap = MyMap[str, str]()
    mymap.put("key1", "value1")
    mymap.put("key2", "value2")
    mymap.put("key3", "value3")
    mymap.put("key4", "value4")
    mymap.put("key5", "value5")
    assert len(mymap) == 5
    assert mymap.get("key4") == "value4"
    assert mymap.get_or_default("key7", "value7") == "value7"
    mymap.replace("key3", "value6")
    assert str(mymap) == '{key1=value1, key2=value2, key3=value6, key4=value4, key5=value5}'
    assert mymap.contains_key("key5")
    mymap.remove("key1")
    assert str(mymap) == '{key2=value2, key3=value6, key4=value4, key5=value5}'
    mymap.clear()
    assert mymap.is_empty()

def test_stack():
    mystack = MyStack[int]()
    mystack.push(1)
    mystack.push(2)
    mystack.push(3)
    assert len(mystack) == 3
    mystack.push(4)
    mystack.push(5)
    assert len(mystack) == 5
    assert str(mystack) == '[1, 2, 3, 4, 5]'
    assert mystack.pop() == 5
    assert mystack.pop() == 4
    assert mystack.pop() == 3
    assert len(mystack) == 2
    assert mystack.peek() == 2
    assert len(mystack) == 2

def test_queue():
    myqueue = MyQueue[int]()
    myqueue.enqueue(1)
    myqueue.enqueue(2)
    myqueue.enqueue(3)
    assert len(myqueue) == 3
    myqueue.enqueue(4)
    myqueue.enqueue(5)
    assert len(myqueue) == 5
    assert str(myqueue) == '[1, 2, 3, 4, 5]'
    assert myqueue.dequeue() == 1
    assert myqueue.dequeue() == 2
    assert myqueue.dequeue() == 3
    assert len(myqueue) == 2
    assert myqueue.peek() == 4
    assert len(myqueue) == 2

def test_tree():
    mytree = MyTree[str]()
    mytree.add_leaf("aaa")
    mytree.add_leaf("bbb")
    mytree.add_leaf("ccc")
    mytree.add_leaf("ddd")
    mytree.add_leaf("eee")
    mytree.add_branch()
    mytree.insert_branch(1)
    mytree.insert_leaf(2, "miao")
    assert len(mytree) == 8
    assert str(mytree) == '[aaa, [], miao, bbb, ccc, ddd, eee, []]'
    for i in range(len(mytree)):
        if mytree.is_branch(i):
            mytree.get_branch(i).add_leaf("111")
            mytree.get_branch(i).add_leaf("222")
    assert str(mytree) == '[aaa, [111, 222], miao, bbb, ccc, ddd, eee, [111, 222]]'
    mytree.remove_node(1)
    assert str(mytree) == '[aaa, miao, bbb, ccc, ddd, eee, [111, 222]]'
    assert mytree.is_branch(6)
    mytree2 = mytree.get_branch(6)
    assert len(mytree2) == 2
    assert str(mytree2) == '[111, 222]'
    assert str(mytree2.get_parent()) == "[aaa, miao, bbb, ccc, ddd, eee, [111, 222]]"
    assert mytree.is_leaf(3)
    assert mytree.get_leaf(3) == "ccc"

if __name__ == '__main__':
    test_list()
    test_map()
    test_stack()
    test_queue()
    test_tree()