public class Worker extends Thread {

	private int id;
	private Data data;

	public Worker(int id, Data d) {
		this.id = id;
		data = d;
		this.start();
	}
	@Override
	public void run() {
		synchronized (data) {
			for (int i = 0; i < 5; i++) {
				try {
					while (id != data.getState())
						data.wait();
					if (id == 1) {
						data.Tic();
					} else if (id == 2) {
						data.Toy();
					} else {
						data.Tak();
					}
					data.notify();
				} catch (InterruptedException ex) {
					System.out.println(ex);
				}
			}
		}
	}
}
