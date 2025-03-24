package com.palmrq.layoff.artingest.article.kafka.inbox;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Service;

import com.palmrq.layoff.artingest.article.kafka.ArticleOutbox;
import com.palmrq.layoff.artingest.article.kafka.ArticleInbox.ArticleSubmittedPayload;
import com.palmrq.layoff.artingest.article.kafka.ArticleOutbox.FailedExtractionPayload;
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

        final var articleSubmittedPayload = (ArticleSubmittedPayload) failed.bound().arguments()[0];

        this.articleOutbox.failedExtraction(new FailedExtractionPayload(articleSubmittedPayload.id(),
                articleSubmittedPayload.article(), stackTraceString));
    }

}
