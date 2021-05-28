package org.simple.rmg;

class MeasureMethod {

    private static <T> T measureAndReport(
            final String methodName,
            final kotlin.jvm.functions.Function0<T> block
    ) {
        final long start = System.currentTimeMillis();
        final T result;
        result = block.invoke();
        final java.time.Duration timeTaken = java.time.Duration.ofMillis(System.currentTimeMillis() - start);

        org.simple.clinic.SqlPerformanceReporter.report("$CLASS_NAME$", methodName, timeTaken);

        return result;
    }
}
