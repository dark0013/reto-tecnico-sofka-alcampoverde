package com.alcampoverde.ms_transactions.domain.port.out;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface IRequestMessagePort {
    String sendMessage(String message);
}
