package Thread.Concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//就像 倒时计数器 等到0时开始往下执行  .await()  .countDown();
public class MyCountdownLatch {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final CountDownLatch countDownLatch1 = new CountDownLatch(1);
		final CountDownLatch countDownLatch2 = new CountDownLatch(4);

		for(int i=0;i<4;i++){
			final int task =i;
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("运动员"+task+"已经准备就绪");
						countDownLatch1.await();
						Thread.sleep(5000);
						System.out.println("运动员"+task+"开始跑往终点");
						Thread.sleep((long)(Math.random()*10000));
						System.out.println("运动员"+task+"跑到终点");
						countDownLatch2.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			executorService.execute(runnable);
		}

		try {
			Thread.sleep(5000);
			countDownLatch1.countDown();
			System.out.println("裁判员已就位 准备发信号"+Thread.currentThread().getName());
			System.out.println("裁判发出信号 开始起跑！！！");
			countDownLatch2.await();
			System.out.println("比赛结束 公布成绩！！！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
	}

}
