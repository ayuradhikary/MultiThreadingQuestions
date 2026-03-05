why parallel execution is required to process orders in concurrency playground project?

For an order containing multiple items, each item requires inventory check, price calculation, and shipping calculation. In the sequential implementation, items are processed one after another, causing unnecessary waiting. Since item processing is independent, we can process different items concurrently using multiple threads. Each thread handles one item sequentially, allowing multiple items to be processed simultaneously and reducing overall processing time.

i.e instead of doing this :-

Main Thread

//here the main thread waits until the laptop's order is processed only then phone's order is processed. Thus order are being processed one by one however they can be processed parallely in different threads.

Laptop
   inventory
   price
   shipping

Phone
   inventory
   price
   shipping

Keyboard
   inventory
   price
   shipping

 Mouse
    inventory
    price
    shipping

total time ~7500ms

we can do this :- 

Thread1 → Laptop
            inventory
            price
            shipping

Thread2 → Phone
            inventory
            price
            shipping

Thread3 → Keyboard
            inventory
            price
            shipping

Thread4 → Mouse
            inventory
            price
            shipping

in fully async version (the last version) each service is  being  executed parallelly in a different thread 


1 What’s happening in the fully async version

You have 5 items: Laptop, Phone, Keyboard, Mouse, Headphones.
Each item has 3 service calls: inventory, price, shipping.
we want all service calls for all items to run concurrently.

So total number of tasks:
5 items × 3 services = 15 tasks


2️ Why not just 5 threads?

If you create a thread pool of 5 threads:
Each thread will pick one service call at a time.
But now every item has 3 services trying to run in parallel.
Only 5 tasks can run at a time → the remaining 10 tasks wait in the queue.

This is called thread starvation: some async tasks are ready, but no thread is free.

3️ Why not 15 threads?

Technically, 15 threads would allow all tasks to run truly simultaneously.
But threads consume memory and CPU. Too many threads cause context switching overhead.
In real applications, creating 15 threads for just 5 items is overkill, especially if items increase in number.

4️ Choosing 8 threads — the compromise

We chose 8 threads because:
Enough to handle most of the tasks concurrently (8 out of 15 run at the same time).
Threads are reused — as soon as a thread finishes a task, it picks up the next one in the queue.
Prevents excessive thread creation, saves memory.
Balances concurrency vs resource usage.

5️ Summary Rules for Nested Async Tasks

Total tasks = items × inner tasks.
Thread pool size ≈ enough to run most tasks concurrently.
Avoid creating one thread per task (too heavy).

Measure & tune — thread pool size is rarely exact; start with 1–2× CPU cores for CPU-bound, or higher for IO-bound.
