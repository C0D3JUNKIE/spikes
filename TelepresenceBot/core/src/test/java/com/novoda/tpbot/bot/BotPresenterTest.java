package com.novoda.tpbot.bot;

import com.novoda.tpbot.Result;
import com.novoda.tpbot.support.TestableObservable;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BotPresenterTest {

    private static final Result SUCCESS_RESULT = Result.from("Connection Successful!");

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    BotTpService tpService;

    @Mock
    BotView botView;

    @Test
    public void givenSuccessfulConnection_whenStartPresenting_thenBotViewOnConnectIsCalled() {
        TestableObservable<Result> testObservable = TestableObservable.just(SUCCESS_RESULT);
        when(tpService.connect()).thenReturn(testObservable);

        BotPresenter presenter = new BotPresenter(tpService, botView);
        presenter.startPresenting();

        verify(botView).onConnect(SUCCESS_RESULT.message().get());
    }

}
