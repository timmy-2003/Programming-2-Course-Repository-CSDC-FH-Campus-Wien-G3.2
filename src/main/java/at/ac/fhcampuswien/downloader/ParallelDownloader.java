package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.exceptions.NewsAPIException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class ParallelDownloader extends Downloader{

    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) throws NewsAPIException {
        // TODO implement download function using multiple threads
        // Hint: use ExecutorService with Callables

        int count = 0;
        int threadNumbers = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threadNumbers);

        List<Callable<String>> callables = new ArrayList<>();
        for(String url : urls) {
            Callable<String> task = () -> {
                try{
                    return saveUrl2File(url);
                }
                catch (Exception e) {
                    return ("Your cat couldn't download url" + e.getMessage());
                }
            };
            callables.add(task);
        }
        try {
            List<Future<String>> allFutures = executorService.invokeAll(callables);
            for (Future<String> cat : allFutures) {
                if (!cat.get().contains("Your cat couldn't download url")) {
                    count++;
                }
                String result = cat.get();
            }
        }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        catch (ExecutionException e) {
            throw new NewsAPIException("Failed to download!");
        }
        return count;
    }
}
