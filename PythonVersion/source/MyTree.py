from typing import *

from DataStructure.PythonVersion.source.MyConst import MAX_SIZE

T = TypeVar('T')

class MyTree(Generic[T]):
    class __TreeNode(Generic[T]):
        def __init__(self):
            self.leaf = None
            self.branch = None
        def set_leaf(self, leaf: T):
            self.leaf = leaf
        def set_branch(self, branch: 'MyTree[T]'):
            self.branch = branch
        def get_leaf(self) -> T:
            return self.leaf
        def get_branch(self) -> 'MyTree[T]':
            return self.branch
        def clear(self):
            self.leaf = None
            self.branch = None
    def __init__(self, parent = None):
        self.__parent = parent
        self.__elements = [self.__TreeNode[T]() for _ in range(MAX_SIZE)]
        self.__size = 0
    def add_branch(self) -> None:
        self.__size += 1
        if self.__size > MAX_SIZE:
            self.__size -= 1
            raise OverflowError
        self.__elements[self.__size - 1].set_branch(MyTree[T](self))
    def add_leaf(self, value: T) -> None:
        self.__size += 1
        if self.__size > MAX_SIZE:
            self.__size -= 1
            raise OverflowError
        self.__elements[self.__size - 1].set_leaf(value)
    def insert_branch(self, index: int):
        if index < 0 or index > self.__size:
            raise IndexError
        for i in range(self.__size - 1, index - 1, -1):
            leaf = self.__elements[i].get_leaf()
            branch = self.__elements[i].get_branch()
            self.__elements[i + 1].set_leaf(leaf)
            self.__elements[i + 1].set_branch(branch)
        self.__elements[index].set_leaf(None)
        self.__elements[index].set_branch(MyTree[T](self))
        self.__size += 1
    def insert_leaf(self, index: int, value: T):
        if index < 0 or index > self.__size:
            raise IndexError
        for i in range(self.__size - 1, index - 1, -1):
            leaf = self.__elements[i].get_leaf()
            branch = self.__elements[i].get_branch()
            self.__elements[i + 1].set_leaf(leaf)
            self.__elements[i + 1].set_branch(branch)
        self.__elements[index].set_leaf(value)
        self.__elements[index].set_branch(None)
        self.__size += 1
    def get_branch(self, index: int) -> 'MyTree[T]':
        if index < 0 or index > self.__size:
            raise IndexError
        return self.__elements[index].get_branch()
    def get_leaf(self, index: int) -> T:
        if index < 0 or index > self.__size:
            raise IndexError
        return self.__elements[index].get_leaf()
    def is_leaf(self, index: int) -> bool:
        if index < 0 or index > self.__size:
            raise IndexError
        return self.__elements[index].get_leaf() is not None
    def is_branch(self, index: int) -> bool:
        if index < 0 or index > self.__size:
            raise IndexError
        return self.__elements[index].get_branch() is not None
    def get_parent(self) -> 'MyTree[T]':
        return self.__parent
    def __len__(self):
        return self.__size
    def is_empty(self) -> bool:
        return self.__size == 0
    def remove_node(self, index: int) -> None:
        if index < 0 or index > self.__size:
            raise IndexError
        for i in range(index + 1, self.__size):
            leaf = self.__elements[i].get_leaf()
            branch = self.__elements[i].get_branch()
            self.__elements[i - 1].set_leaf(leaf)
            self.__elements[i - 1].set_branch(branch)
        self.__elements[self.__size - 1].clear()
        self.__size -= 1
    def clear(self) -> None:
        for i in range(self.__size):
            self.__elements[i].clear()
        self.__size = 0
    def __output(self, tree) -> str:
        sb = "["
        for i in range(tree.__size):
            if tree.is_leaf(i):
                s = str(tree.get_leaf(i))
            else:
                s = tree.__output(tree.get_branch(i))
            if i == tree.__size - 1:
                sb += str(s)
            else:
                sb += str(s) + ", "
        sb += "]"
        return sb
    def __str__(self):
        return self.__output(self)