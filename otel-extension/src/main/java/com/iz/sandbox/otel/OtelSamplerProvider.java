package com.iz.sandbox.otel;

import com.google.auto.service.AutoService;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.autoconfigure.spi.traces.ConfigurableSamplerProvider;
import io.opentelemetry.sdk.trace.samplers.Sampler;

@AutoService(ConfigurableSamplerProvider.class)
public class OtelSamplerProvider implements ConfigurableSamplerProvider {

    private static final String NAME = "sandbox_sampler";
    private static final double DEFAULT_RATIO = 1.0;
    private static final String OTEL_TRACES_SAMPLER_ARG = "otel.traces.sampler.arg";

    @Override
    public Sampler createSampler(ConfigProperties configProperties) {
        return Sampler.parentBasedBuilder(new OtelSampler(configProperties.getDouble(OTEL_TRACES_SAMPLER_ARG, DEFAULT_RATIO))).build();
    }

    @Override
    public String getName() {
        return NAME;
    }
}
