package org.glassfish.jersey.inject.guice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.glassfish.jersey.internal.inject.Binder;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.internal.inject.ForeignDescriptor;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.ServiceHolder;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Key;

public class GuiceInjectionManager extends AbstractModule implements InjectionManager {

    List<Runnable> ops = new LinkedList<>();
    Injector inj;

    @Override
    public void completeRegistration() {
    }

    @Override
    public void shutdown() {
    }

    @Override
    public void register(Binding binding) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void register(Iterable<Binding> descriptors) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void register(Binder binder) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void register(Object provider) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRegistrable(Class<?> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T createAndInitialize(Class<T> createMe) {
        return getInstance(createMe);
    }

    @Override
    public <T> List<ServiceHolder<T>> getAllServiceHolders(Class<T> contractOrImpl, Annotation... qualifiers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getInstance(Class<T> contractOrImpl, Annotation... qualifiers) {
        if (qualifiers == null || qualifiers.length == 0) {
            return getInstance(contractOrImpl);
        }
        if (qualifiers.length != 1) {
            throw new IllegalArgumentException("too many qualifiers");
        }
        return inj.getInstance(Key.get(contractOrImpl, qualifiers[0]));
    }

    @Override
    public <T> T getInstance(Class<T> contractOrImpl, String classAnalyzer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getInstance(Class<T> contractOrImpl) {
        return inj.getInstance(contractOrImpl);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getInstance(Type contractOrImpl) {
        return (T) inj.getInstance(Key.get(contractOrImpl));
    }

    @Override
    public Object getInstance(ForeignDescriptor foreignDescriptor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ForeignDescriptor createForeignDescriptor(Binding binding) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<T> getAllInstances(Type contractOrImpl) {
        // guice provides a single instance per key
        return Collections.singletonList(getInstance(contractOrImpl));
    }

    @Override
    public void inject(Object injectMe) {
        inj.injectMembers(injectMe);
    }

    @Override
    public void inject(Object injectMe, String classAnalyzer) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void preDestroy(Object preDestroyMe) {
        // Guice does not have any 'pre-destroy' mechanisms
    }

}
