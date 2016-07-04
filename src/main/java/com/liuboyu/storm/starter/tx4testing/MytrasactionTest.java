package com.liuboyu.storm.starter.tx4testing;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.coordination.BatchOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseTransactionalBolt;
import backtype.storm.transactional.ITransactionalSpout;
import backtype.storm.transactional.TransactionAttempt;
import backtype.storm.transactional.TransactionalTopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tony on 7/3/16.
 */
public class MytrasactionTest {

    private static List<String> MOCK_KAFKA = new ArrayList<String>() {{
        for (int i = 1; i <= 200; i++) {
            add("data_" + i);
        }
    }};

    static class MySpout implements ITransactionalSpout {
        int index = 1;

        class MyCoordinator implements ITransactionalSpout.Coordinator {

            @Override
            public Object initializeTransaction(BigInteger txid, Object prevMetadata) {
                return null;
            }

            @Override
            public boolean isReady() {
                return index == 1 || index % 100 == 0;
            }

            @Override
            public void close() {

            }
        }

        class MyEmitter implements ITransactionalSpout.Emitter {

            @Override
            public void emitBatch(TransactionAttempt tx, Object coordinatorMeta, BatchOutputCollector collector) {
                while (true) {
                    if (index <= 200) {
                        collector.emit(new Values(tx, index, MOCK_KAFKA.get(index)));
                        index++;
                        if ((index % 100 == 0)) {
                            break;
                        }
                    }
                }
                System.out.println(" >>>>>>>>> " + index);
            }

            @Override
            public void cleanupBefore(BigInteger txid) {
            }

            @Override
            public void close() {

            }
        }

        @Override
        public Coordinator getCoordinator(Map conf, TopologyContext context) {
            return new MyCoordinator();
        }

        @Override
        public Emitter getEmitter(Map conf, TopologyContext context) {
            return new MyEmitter();
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
            declarer.declare(new Fields("tx", "index", "name"));
        }

        @Override
        public Map<String, Object> getComponentConfiguration() {
            return null;
        }
    }

    static class MyBolt extends BaseTransactionalBolt {

        private BatchOutputCollector _collector;

        private Map<Object, List<String>> map = new HashMap<>();

        @Override
        public void prepare(Map conf, TopologyContext context, BatchOutputCollector collector, TransactionAttempt id) {
            _collector = collector;
        }

        @Override
        public void execute(Tuple tuple) {
            System.out.println("<<<<<<<<<<<<");
            int index = tuple.getIntegerByField("index");
            String name = tuple.getStringByField("name");
            if (!map.containsKey(tuple.getValue(0)))
                map.put(tuple.getValue(0), new ArrayList<>());
            map.get(tuple.getValue(0)).add(index + "-" + name);
        }

        @Override
        public void finishBatch() {
            System.out.println("当前批次处理完毕 " + map.size());
            map.clear();
        }

        @Override
        public void declareOutputFields(OutputFieldsDeclarer declarer) {
        }
    }

    public static void main(String[] args) {
        TransactionalTopologyBuilder builder = new TransactionalTopologyBuilder("test-topology", "spout", new MySpout(), 1);
        builder.setBolt("mybolt", new MyBolt(), 1).shuffleGrouping("spout");
        LocalCluster cluster = new LocalCluster();

        Config config = new Config();
        config.setDebug(true);
        config.setMaxSpoutPending(3);

        cluster.submitTopology("top-n-topology", config, builder.buildTopology());

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        cluster.shutdown();

    }


}
