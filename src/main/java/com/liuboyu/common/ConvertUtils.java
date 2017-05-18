package com.liuboyu.common;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert
 * <p>
 * Created by Tony on 13/02/2017.
 */
public final class ConvertUtils {

    /**
     * Convert list object from entity to dto.
     *
     * @param sources
     * @param targetClazz
     * @param <S>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <S, T> List<T> convert(List<S> sources, Class<T> targetClazz) throws IllegalAccessException, InstantiationException {
        List<T> targets = new ArrayList<>();
        for (S source : sources) {
            T target = targetClazz.newInstance();
            BeanUtils.copyProperties(source, target);
            targets.add(target);
        }
        return targets;
    }

    public static <S, T> List<T> convert(List<S> sources, Converter<S, T> converter) throws IllegalAccessException, InstantiationException {
        List<T> targets = new ArrayList<>();
        for (S source : sources)
            targets.add(converter.convert(source));
        return targets;
    }

}
