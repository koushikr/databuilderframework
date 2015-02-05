package com.flipkart.databuilderframework.engine;

import com.flipkart.databuilderframework.model.*;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * The executor for a {@link com.flipkart.databuilderframework.model.DataFlow}.
 */
public abstract class DataFlowExecutor {
    protected List<DataBuilderExecutionListener> dataBuilderExecutionListener;
    protected DataBuilderFactory dataBuilderFactory;

    public DataFlowExecutor(DataBuilderFactory dataBuilderFactory) {
        this.dataBuilderExecutionListener = Lists.newArrayList();
        this.dataBuilderFactory = dataBuilderFactory;
    }

    /**
     * It uses {@link com.flipkart.databuilderframework.model.Data} present in the existing
     * {@link com.flipkart.databuilderframework.model.DataSet} and those provided by
     * {@link com.flipkart.databuilderframework.model.DataDelta} to generate more data.
     * {@link com.flipkart.databuilderframework.model.Data} generated by all executors invoked in a request
     * are registerd back into the {@link com.flipkart.databuilderframework.model.DataSet}
     *
     * @param dataFlowInstance An instance of the {@link com.flipkart.databuilderframework.model.DataFlow} to run.
     * @param dataDelta        The set of data to be considered for analysis.
     * @return A response containing responses from every {@link DataBuilder}
     * that was invoked in this stage. Note that these have already been added to the DataSet before returning.
     * @throws DataFrameworkException
     */
    public DataExecutionResponse run(DataFlowInstance dataFlowInstance, DataDelta dataDelta) throws DataFrameworkException {
        DataBuilderContext dataBuilderContext = new DataBuilderContext();
        return run(dataBuilderContext, dataFlowInstance, dataDelta);
    }

    /**
     * It uses {@link com.flipkart.databuilderframework.model.Data} present in the existing
     * {@link com.flipkart.databuilderframework.model.DataSet} and those provided by
     * {@link com.flipkart.databuilderframework.model.DataDelta} to generate more data.
     * {@link com.flipkart.databuilderframework.model.Data} generated by all executors invoked in a request
     * are registerd back into the {@link com.flipkart.databuilderframework.model.DataSet}
     *
     * @param dataBuilderContext An instance of the {@link com.flipkart.databuilderframework.engine.DataBuilderContext} object.
     * @param dataFlowInstance   An instance of the {@link com.flipkart.databuilderframework.model.DataFlow} to run.
     * @param dataDelta          The set of data to be considered for analysis.
     * @return A response containing responses from every {@link DataBuilder}
     * that was invoked in this stage. Note that these have already been added to the DataSet before returning.
     * @throws DataFrameworkException
     */
    abstract public DataExecutionResponse run(DataBuilderContext dataBuilderContext, DataFlowInstance dataFlowInstance, DataDelta dataDelta) throws DataFrameworkException;

    /**
     * A instance of {@link com.flipkart.databuilderframework.engine.DataBuilderExecutionListener}
     * that will be sent events when a builder is executed. This can be called multiple times with different listeners.
     * They will be called in order.
     *
     * @param listener Register a listener to be invoked during execution.
     */
    public void registerExecutionListener(DataBuilderExecutionListener listener) {
        dataBuilderExecutionListener.add(listener);
    }
}
