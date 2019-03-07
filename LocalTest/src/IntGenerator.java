public abstract class IntGenerator {
    /**
     * canceled 是boolean类型， 所以是原子性的
     * volatile 保证canceled的 可见性
     */
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
