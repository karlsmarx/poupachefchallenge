package dev.karls.utils;

public interface IHandler<T, R> {
    public R handle(T command); 
}
