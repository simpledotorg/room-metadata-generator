package org.simple.rmg;

class MeasureMethod {

    private static <T> T measureAndReport(
            final String methodName,
            final kotlin.jvm.functions.Function0<T> block
    ) {
        final long start = System.currentTimeMillis();
        final T result;
        $REPORTER_NAME$.begin("$CLASS_NAME$", start, methodName);
        result = block.invoke();

        $REPORTER_NAME$.end("$CLASS_NAME$", start, methodName);

        return result;
    }
}
