package org.simple.rmg

sealed class MetadataGenerationResult

data class Succeeded(val metadata: String): MetadataGenerationResult()
