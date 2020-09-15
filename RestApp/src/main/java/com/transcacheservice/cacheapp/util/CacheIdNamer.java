package com.transcacheservice.cacheapp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CacheIdNamer {

    private final Logger logger = LoggerFactory.getLogger(CacheIdNamer.class);

    public String getNameOfExecutableMethod() {
        StackWalker walker = StackWalker.getInstance();
        Optional<String> methodName = walker.walk(frames -> frames
                //that's how much you need go through the stack, when use @Cacheable
                .skip(20)
                .findAny()
                .map(StackWalker.StackFrame::getMethodName));
        if (methodName.isPresent()) {
            return methodName.get();
        } else {
            logger.error("Exception during getting a method name");
            throw new RuntimeException("Exception during getting a method name");
        }
    }
}
