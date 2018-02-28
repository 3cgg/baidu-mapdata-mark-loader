package me.libme.baidu.mapdata.searchindex.keyword;

import me.libme.kernel._c.pubsub.Consume;
import me.libme.kernel._c.pubsub.Subscriber;
import me.libme.kernel._c.util.JUniqueUtils;
import me.libme.xstream.*;

/**
 * Created by J on 2018/2/27.
 */
public class BaiduSearchSourcer implements WindowSourcer {

    private final Subscriber subscriber;

    private final Consume consume;

    private final SourceMeta sourceMeta;

    public BaiduSearchSourcer(SourceMeta sourceMeta, Subscriber subscriber) {
        this.sourceMeta=sourceMeta;
        this.subscriber=subscriber;
        this.consume=subscriber.consume();
    }

    @Override
    public Tupe next() {
        Object object= consume.next();
        FlexTupe flexTupe=new FlexTupe();
        String sequence=JUniqueUtils.unique();
        flexTupe.add(new EntryTupe.Entry(JUniqueUtils.unique(),object));
        flexTupe.setSequence(sequence);
        return flexTupe;
    }

    @Override
    public boolean hasNext() {
        return consume.hasNext();
    }

    @Override
    public void success(Tupe tupe) {

    }

    @Override
    public void error(Tupe tupe, Throwable throwable) {

    }

    @Override
    public void prepare() {

    }


}
