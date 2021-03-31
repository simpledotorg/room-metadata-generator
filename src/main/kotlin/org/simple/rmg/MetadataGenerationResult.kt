package org.simple.rmg

sealed class MetadataGenerationResult

data class Succeeded(val metadata: String) : MetadataGenerationResult()

data class OverloadedMethodsFound(val methods: Map<String, Set<String>>) : MetadataGenerationResult()
