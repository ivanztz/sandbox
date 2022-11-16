package com.iz.sandbox.otel;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.context.Context;
import io.opentelemetry.sdk.trace.data.LinkData;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.sdk.trace.samplers.SamplingDecision;
import io.opentelemetry.sdk.trace.samplers.SamplingResult;

import java.util.List;

public class OtelSampler implements Sampler {
    private static final String DESCRIPTION = "OtelSampler";
    private static final String HEALTHCHECK_PATH = "actuator/health";

    private static final AttributeKey<String> HTTP_TARGET_KEY = AttributeKey.stringKey("http.target");

    private final Sampler delegate;

    public OtelSampler(double ratio) {
        delegate = Sampler.traceIdRatioBased(ratio);
    }

    @Override
    public SamplingResult shouldSample(Context context, String traceId, String name, SpanKind spanKind, Attributes attributes, List<LinkData> list) {
        final String httpTarget = attributes.get(HTTP_TARGET_KEY);
        if (httpTarget != null && httpTarget.contains(HEALTHCHECK_PATH)) {
            return SamplingResult.create(SamplingDecision.DROP);
        } else {
            return delegate.shouldSample(context, traceId, name, spanKind, attributes, list);
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
