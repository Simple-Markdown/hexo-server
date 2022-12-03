package live.midreamsheep.hexo.server.message.queue;

import java.util.concurrent.LinkedBlockingQueue;

public class QueueApi {
    public static final LinkedBlockingQueue<Task> queue = new LinkedBlockingQueue<>();

    public static void addTask(Task task){
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static Task getTask(){
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean isEmpty(){
        return queue.isEmpty();
    }
}
