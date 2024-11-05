from typing import *

from DataStructure.PythonVersion.source.MyConst import MAX_SIZE

K = TypeVar('K')
V = TypeVar('V')

class MyMap(Generic[K, V]):
    def __init__(self):
        self.__keys = [None for _ in range(MAX_SIZE)]
        self.__values = [None for _ in range(MAX_SIZE)]
        self.__size = 0
    def put(self, key: K, value: V) -> None:
        self.__size += 1
        if self.__size >= MAX_SIZE:
            self.__size -= 1
            raise OverflowError
        self.__keys[self.__size - 1] = key
        self.__values[self.__size - 1] = value
    def get(self, key: K) -> V:
        if self.__size <= 0:
            raise IndexError
        for i in range(self.__size):
            if self.__keys[i] == key:
                return self.__values[i]
        return None
    def get_or_default(self, key: K, value: V) -> V:
        val: V = self.get(key)
        return val if val is not None else value
    def remove(self, key: K) -> None:
        if self.__size <= 0:
            raise IndexError
        index = -1
        for i in range(self.__size):
            if self.__keys[i] == key:
                index = i
                break
        if index == -1:
            return
        for i in range(index + 1, self.__size):
            self.__keys[i - 1] = self.__keys[i]
            self.__values[i - 1] = self.__values[i]
        self.__keys[self.__size - 1] = None
        self.__values[self.__size - 1] = None
        self.__size -= 1
    def replace(self, key: K, value: V) -> None:
        for i in range(self.__size):
            if self.__keys[i] == key:
                self.__values[i] = value
                break
    def contains_key(self, key: K) -> bool:
        for i in range(self.__size):
            if self.__keys[i] == key:
                return True
        return False
    def clear(self) -> None:
        for i in range(self.__size):
            self.__keys[i] = None
            self.__values[i] = None
        self.__size = 0
    def __len__(self):
        return self.__size
    def is_empty(self) -> bool:
        return self.__size == 0
    def __str__(self) -> str:
        sb = "{"
        for i in range(self.__size):
            if i == self.__size - 1:
                sb += str(self.__keys[i]) + "=" + str(self.__values[i])
            else:
                sb += str(self.__keys[i]) + "=" + str(self.__values[i]) + ", "
        sb += "}"
        return sb
