from typing import *

from DataStructure.PythonVersion.source.MyConst import MAX_SIZE

T = TypeVar('T')

class MyList(Generic[T]):
    def __init__(self):
        self.__elements = [None for _ in range(MAX_SIZE)]
        self.__size = 0
    def __len__(self):
        return self.__size
    def is_empty(self) -> bool:
        return self.__size == 0
    def get(self, index: int) -> T:
        if index < 0 or index >= self.__size:
            raise IndexError
        return self.__elements[index]
    def add(self, element: T) -> None:
        self.__size += 1
        if self.__size > MAX_SIZE:
            self.__size -= 1
            raise OverflowError
        self.__elements[self.__size - 1] = element
    def insert(self, index: int, element: T) -> None:
        if index < 0 or index >= self.__size:
            raise IndexError
        for i in range(self.__size - 1, index - 1, -1):
            self.__elements[i + 1] = self.__elements[i]
        self.__elements[index] = element
        self.__size += 1
    def remove(self, index: int) -> None:
        if index < 0 or index >= self.__size:
            raise IndexError
        for i in range(index + 1, self.__size):
            self.__elements[i - 1] = self.__elements[i]
        self.__elements[self.__size - 1] = None
        self.__size -= 1
    def clear(self) -> None:
        for i in range(self.__size):
            self.__elements[i] = None
        self.__size = 0
    def set(self, index: int, element: T) -> None:
        if index < 0 or index >= self.__size:
            raise IndexError
        self.__elements[index] = element
    def __str__(self):
        sb = "["
        for i in range(self.__size):
            if i == self.__size - 1:
                sb += str(self.__elements[i])
            else:
                sb += str(self.__elements[i]) + ", "
        sb += "]"
        return sb