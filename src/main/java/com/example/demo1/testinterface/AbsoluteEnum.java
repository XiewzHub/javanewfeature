package com.example.demo1.testinterface;

import java.util.PrimitiveIterator;
import java.util.concurrent.Callable;

public interface AbsoluteEnum<T extends Enum,C,N> {

    N getNameByCode(C code );


}
