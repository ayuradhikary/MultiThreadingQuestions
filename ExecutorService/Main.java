package ExecutorService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Use ExecutorService to process 100 tasks with 10 threads
public class Main {
    public static void main(String[] args) {

        int corePoolSize = 10;
        int maxPoolSize = 20;
        long keepAliveTime = 3000;

        ExecutorService threadPoolExecutorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

        for (int i = 1; i <= 100; i++) {
            int taskNumber = i;
            threadPoolExecutorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Completed task " + taskNumber +
                            " by " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread interrupted");
                }
            });
        }

        threadPoolExecutorService.shutdown();

    }

}

// when the active threads is less than core pool size
// then, executer service creates new threads equals to the core pool size one
// by one == core pool size
// when the active threads equals to core pool size
// the task is kept in the queue.

// if the queue is also full
// and active threads is less than max pool size
// then executor service creates new threads one by one == max pool size

// if queue is full and threads is == max pool size
// then task is rejected thus RejectedExecutionException is thrown

// this can be created if we decrease the queue size to max pool size then
// max capacity of our executor service becomes :- 20 threads i.e 10 core size
// and since max pool size is 20
// thus 20 - 10 = 10
// i.e 20 threads in total and queue size is 20 thus capacity = 20 + 20 = 40

// first 10 tasks
// → Create core threads
// Active threads = 10

// Next 20 tasks
// → Go into queue
// Queue full

// Next 10 tasks
// → Create extra threads (up to maxPoolSize)
// Active threads = 20

// Next task
// → Reject (queue full + threads == max)

// thus RejectedExecutionException occurs.

// we can also use scheduled executor service for executing tasks in a scheduled
// interval of time
// we can also use callable if we want to get the return value from the executed
// task
// since runnable does not return anything but callable can returns
// so if we are expecting any returning object from the task then we can use
// callable instead of runnable and do any operations
// with the returned object.

// we can also use .submit() method instead of .execute() method.
// the submit method returns Future and we can do different operations with the
// future
// such as cancel the task. checking future.isDone() or we can do future.get()
// with the callable to get the result.

// there methods like invokeAny() or invokeAll() to invoke one task between
// different tasks or to invoke all tasks that are provided
// to the executor service respectively

// we can also shutdown in different ways like shutdown(), shutdownNow()
// (shutdowns without letting the tasks to be completed)
// awaitTermination() by passing certain time which then waits until the time
// passes and sutdowns after the time gets completed
