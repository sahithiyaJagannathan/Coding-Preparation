import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZATaskUtil
{
    private static ExecutorService service;

    static
    {
        service = Executors.newFixedThreadPool(4);
    }

    static class ZATaskRunnable implements Runnable
    {
        ZATask task;

        ZATaskRunnable(ZATask task)
        {
            this.task = task;
        }

        @Override
        public void run()
        {
            task.processTask();
        }

    }

    static CompletableFuture<Void> run(Runnable runnable) {
        return CompletableFuture.runAsync(runnable, service);

    }
    static ZATaskRunnable getRunnableTask(ZATask task)
    {
        return new ZATaskRunnable(task);
    }

    static List<ZATaskRunnable> getRunnableTaskList(List<ZATask> tasklist)
    {
        List<ZATaskRunnable> runnableList = new ArrayList<>();
        for (ZATask task : tasklist)
        {
            runnableList.add(getRunnableTask(task));
        }
        return runnableList;
    }

    static CompletableFuture<Void> completeTask(ZATaskRunnable taskRunnable)
    {
        return CompletableFuture.runAsync(taskRunnable, service);
    }

    static CompletableFuture<Void> completeTask(ZATask taskRunnable)
    {
        System.out.println("Inside CompleteTask " + Thread.currentThread());
        return CompletableFuture.runAsync(getRunnableTask(taskRunnable), service);
    }
    public static ExecutorService getService() {
        return service;
    }

    static ArrayList<CompletableFuture<Void>> completeAllTasks(List<ZATask> tasks)
    {
        ArrayList<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (ZATask taskRunnable : tasks)
        {
            futureList.add(completeTask(taskRunnable));
        }
        return futureList;
    }

    static CompletableFuture<Void> completeAllTasksFuture(List<ZATask> taskRunnables)
    {
        List<CompletableFuture<Void>> futures = completeAllTasks(taskRunnables);
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
    }

    public static void endService()
    {
        service.shutdown();
    }

}
