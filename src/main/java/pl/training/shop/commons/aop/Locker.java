package pl.training.shop.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static pl.training.shop.commons.aop.Lock.LockType.WRITE;

@Aspect
@Component
public class Locker {

    private final Map<String, ReadWriteLock> locks = Collections.synchronizedMap(new HashMap<>());

    @Around("@annotation(lock)")
    public Object lock(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        var lockName = lock.value();
        locks.putIfAbsent(lockName, new ReentrantReadWriteLock());
        var currentLock = locks.get(lockName);
        var targetLock = lock.type().equals(WRITE) ? currentLock.writeLock() : currentLock.readLock();
        targetLock.lock();
        try {
            return joinPoint.proceed();
        } finally {
            targetLock.unlock();
            // should remove old lock from map
        }
    }

}
