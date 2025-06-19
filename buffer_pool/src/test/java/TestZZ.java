import com.zsy.BufferPool;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestZZ {

    @Test
    public void testSingleThreadAllocateDeallocate() throws InterruptedException {
        BufferPool pool = new BufferPool(1024, 256);

        ByteBuffer buffer1 = pool.allocate(256, 1000);
        assertNotNull(buffer1);
        assertEquals(256, buffer1.capacity());

        pool.deallocate(buffer1);

        ByteBuffer buffer2 = pool.allocate(256, 1000);
        assertSame(buffer1, buffer2);
    }

    @Test
    public void testAllocateMoreThanTotalShouldThrow() {
        BufferPool pool = new BufferPool(1024, 256);
        assertThrows(RuntimeException.class, () -> pool.allocate(2048, 1000));
    }

    @Test
    public void testMultiThreadedAllocationBlocking() throws InterruptedException, ExecutionException, TimeoutException {
        BufferPool pool = new BufferPool(512, 256);

        ByteBuffer b1 = pool.allocate(256, 1000);
        ByteBuffer b2 = pool.allocate(256, 1000);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<ByteBuffer> future = executor.submit(() -> pool.allocate(256, 10000));

        Thread.sleep(1000);
        assertFalse(future.isDone());

        pool.deallocate(b1);
        ByteBuffer b3 = future.get(100, TimeUnit.MILLISECONDS);
        assertNotNull(b3);

        executor.shutdownNow();
    }
}
