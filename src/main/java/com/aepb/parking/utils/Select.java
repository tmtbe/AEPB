package com.aepb.parking.utils;

import com.aepb.parking.mapper.Table;
import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Select<T extends Table> implements Stream<T> {
    private Stream<T> delegate;

    Select(Stream<T> stream) {
        delegate = stream;
    }

    public static <T1> Builder<T1> builder() {
        return Stream.builder();
    }

    public static <T1> Stream<T1> empty() {
        return Stream.empty();
    }

    public static <T1> Stream<T1> of(T1 t1) {
        return Stream.of(t1);
    }

    public static <T1> Stream<T1> ofNullable(T1 t1) {
        return Stream.ofNullable(t1);
    }

    @SafeVarargs
    public static <T1> Stream<T1> of(T1... values) {
        return Stream.of(values);
    }

    public static <T1> Stream<T1> iterate(T1 seed, UnaryOperator<T1> f) {
        return Stream.iterate(seed, f);
    }

    public static <T1> Stream<T1> iterate(T1 seed, Predicate<? super T1> hasNext, UnaryOperator<T1> next) {
        return Stream.iterate(seed, hasNext, next);
    }

    public static <T1> Stream<T1> generate(Supplier<? extends T1> s) {
        return Stream.generate(s);
    }

    public static <T1> Stream<T1> concat(Stream<? extends T1> a, Stream<? extends T1> b) {
        return Stream.concat(a, b);
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return delegate.filter(predicate);
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> function) {
        return delegate.map(function);
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> toIntFunction) {
        return delegate.mapToInt(toIntFunction);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> toLongFunction) {
        return delegate.mapToLong(toLongFunction);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        return delegate.mapToDouble(toDoubleFunction);
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> function) {
        return delegate.flatMap(function);
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> function) {
        return delegate.flatMapToInt(function);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> function) {
        return delegate.flatMapToLong(function);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> function) {
        return delegate.flatMapToDouble(function);
    }

    @Override
    public Stream<T> distinct() {
        return delegate.distinct();
    }

    @Override
    public Stream<T> sorted() {
        return delegate.sorted();
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return delegate.sorted(comparator);
    }

    @Override
    public Stream<T> peek(Consumer<? super T> consumer) {
        return delegate.peek(consumer);
    }

    @Override
    public Stream<T> limit(long l) {
        return delegate.limit(l);
    }

    @Override
    public Stream<T> skip(long l) {
        return delegate.skip(l);
    }

    @Override
    public Stream<T> takeWhile(Predicate<? super T> predicate) {
        return delegate.takeWhile(predicate);
    }

    @Override
    public Stream<T> dropWhile(Predicate<? super T> predicate) {
        return delegate.dropWhile(predicate);
    }

    @Override
    public void forEach(Consumer<? super T> consumer) {
        delegate.forEach(consumer);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> consumer) {
        delegate.forEachOrdered(consumer);
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> intFunction) {
        return delegate.toArray(intFunction);
    }

    @Override
    public T reduce(T t, BinaryOperator<T> binaryOperator) {
        return delegate.reduce(t, binaryOperator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> binaryOperator) {
        return delegate.reduce(binaryOperator);
    }

    @Override
    public <U> U reduce(U u, BiFunction<U, ? super T, U> biFunction, BinaryOperator<U> binaryOperator) {
        return delegate.reduce(u, biFunction, binaryOperator);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> biConsumer, BiConsumer<R, R> biConsumer1) {
        return delegate.collect(supplier, biConsumer, biConsumer1);
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return delegate.collect(collector);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return delegate.min(comparator);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return delegate.max(comparator);
    }

    @Override
    public long count() {
        return delegate.count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return delegate.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return delegate.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return delegate.noneMatch(predicate);
    }

    @Override
    public Optional<T> findFirst() {
        return delegate.findFirst();
    }

    @Override
    public Optional<T> findAny() {
        return delegate.findAny();
    }

    @Override
    public Iterator<T> iterator() {
        return delegate.iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return delegate.spliterator();
    }

    @Override
    public boolean isParallel() {
        return delegate.isParallel();
    }

    @Override
    public Stream<T> sequential() {
        return delegate.sequential();
    }

    @Override
    public Stream<T> parallel() {
        return delegate.parallel();
    }

    @Override
    public Stream<T> unordered() {
        return delegate.unordered();
    }

    @Override
    public Stream<T> onClose(Runnable runnable) {
        return delegate.onClose(runnable);
    }

    @Override
    public void close() {
        delegate.close();
    }

    public <R> Select<T> where(Function<T, R> fun, R id) {
        return new Select<>(delegate.filter(n -> fun.apply(n).equals(id)));
    }

    public T one() {
        return delegate.findFirst().map(n -> (T) JSON.parseObject(JSON.toJSONString(n), n.getClass())).orElse(null);
    }

    public List<T> list() {
        return delegate.map(n -> (T) JSON.parseObject(JSON.toJSONString(n), n.getClass())).collect(Collectors.toList());
    }
}
