from typing import *

from DataStructure.PythonVersion.source.MyConst import MAX_SIZE

T = TypeVar('T')

class MyQueue(Generic[T]):
    def __init__(self):
        self.__elements = [None for _ in range(MAX_SIZE)]
        self.__size = 0
    def __len__(self):
        return self.__size
    def is_empty(self) -> bool:
        return self.__size == 0
    def enqueue(self, element: T) -> None:
        self.__size += 1
        if self.__size > MAX_SIZE:
            self.__size -= 1
            raise OverflowError
        self.__elements[self.__size - 1] = element
    def dequeue(self) -> T:
        if self.__size < 1:
            raise IndexError('Queue is empty')
        e = self.__elements[0]
        for i in range(1, self.__size):
            self.__elements[i - 1] = self.__elements[i]
        self.__size -= 1
        return e
    def peek(self) -> T:
        if self.is_empty():
            raise IndexError('Queue is empty')
        return self.__elements[0]
    def __str__(self):
        sb = "["
        for i in range(self.__size):
            if i == self.__size - 1:
                sb += str(self.__elements[i])
            else:
                sb += str(self.__elements[i]) + ", "
        sb += "]"
        return sb