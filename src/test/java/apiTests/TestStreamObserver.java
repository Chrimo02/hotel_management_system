package apiTests;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class TestStreamObserver<T> implements StreamObserver<T> {

    private final List<T> values = new ArrayList<>();
    private Throwable error;
    private boolean completed = false;

    @Override
    public void onNext(T value) {
        values.add(value);
    }

    @Override
    public void onError(Throwable t) {
        error = t;
    }

    @Override
    public void onCompleted() {
        completed = true;
    }

    public List<T> getValues() {
        return values;
    }

    public Throwable getError() {
        return error;
    }

    public boolean isCompleted() {
        return completed;
    }
}
