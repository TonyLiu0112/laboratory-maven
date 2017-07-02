package jmock;

import com.liuboyu.jmock.LocalService;
import com.liuboyu.jmock.RemoteService;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.action.ReturnValueAction;
import org.jmock.lib.action.ThrowAction;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * Jmock simple demo
 * <p>
 * Created by Tony on 02/07/2017.
 */
public class PublisherTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test(expected = NullPointerException.class)
    public void oneSubscriberReceivesAMessage() {
        // set up
        final RemoteService remoteService = context.mock(RemoteService.class);

        LocalService localService = new LocalService(remoteService);
        context.checking(new Expectations() {{
            // one的一次代码模拟的接口尽可使用一次
            allowing(remoteService).queryInfo("1");
            will(new ReturnValueAction("获取远程基金信息成功."));

            allowing(remoteService).queryInfo("2");
            will(new ReturnValueAction("获取远程股票信息成功."));

            allowing(remoteService).queryInfo("");
            will(new ThrowAction(new NullPointerException("请求参数不能为空!")));
        }});

        Assert.assertEquals("获取远程基金信息成功.", localService.doSomething1());
        Assert.assertEquals("获取远程股票信息成功.", localService.doSomething2());
        Assert.fail(localService.doSomething3());
    }

}
