package com.adventofcode.day18;

import java.util.Queue;

public interface MessageQueueAwareExecutionContext extends ExecutionContext {

    Queue<Long> getMessageQueue();

}
