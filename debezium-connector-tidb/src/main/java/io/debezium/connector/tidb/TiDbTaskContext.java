/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.connector.tidb;

import io.debezium.connector.common.CdcSourceTaskContext;
import io.debezium.relational.TableId;
import io.debezium.spi.topic.TopicNamingStrategy;

/**
 * The context of the single task of the TiDB connector.
 *
 * @author Aviral Srivastava
 */
public class TiDbTaskContext extends CdcSourceTaskContext {

    private final TiDbConnectorConfig connectorConfig;
    private final TiDbSchema schema;
    private final TopicNamingStrategy<TableId> topicNamingStrategy;

    @SuppressWarnings("unchecked")
    public TiDbTaskContext(TiDbConnectorConfig connectorConfig, TiDbSchema schema) {
        super(connectorConfig, connectorConfig.getCustomMetricTags(), schema::tableIds);
        this.connectorConfig = connectorConfig;
        this.schema = schema;
        this.topicNamingStrategy = connectorConfig.getTopicNamingStrategy(TiDbConnectorConfig.TOPIC_NAMING_STRATEGY);
    }

    public TiDbConnectorConfig getConnectorConfig() {
        return connectorConfig;
    }

    public TiDbSchema getSchema() {
        return schema;
    }

    public TopicNamingStrategy<TableId> getTopicNamingStrategy() {
        return topicNamingStrategy;
    }
}
