package com.palmrq.layoff.artingest.article.kafka.inbox;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Service;

import com.palmrq.layoff.artingest.article.kafka.ArticleOutbox;
import com.palmrq.layoff.artingest.article.kafka.InboxPayload;
import com.palmrq.layoff.artingest.article.kafka.outbox.FailedIngestionPayload;
import com.palmrq.layoff.artingest.config.KafkaInfra;

import lombok.RequiredArgsConstructor;
import me.ehp246.aufkafka.api.consumer.InvocationListener;
import me.ehp246.aufkafka.api.consumer.Invoked.Failed;

@Service(KafkaInfra.BEAN_INVOCATION_LISTENER)
@RequiredArgsConstructor
public class FailedInvocationListener implements InvocationListener.FailedListener {

    private final ArticleOutbox articleOutbox;

    @Override
    public void onFailed(Failed failed) {
        final var sw = new StringWriter();
        final var pw = new PrintWriter(sw);
        failed.thrown().printStackTrace(pw);
        final var stackTraceString = sw.toString();

        this.articleOutbox.failedIngestion(
                new FailedIngestionPayload((InboxPayload) failed.bound().arguments()[0], stackTraceString));
    }

}
