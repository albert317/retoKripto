package pe.com.albert.features.app_rating.domain.usecase

import pe.com.albert.features.app_rating.domain.model.Property
import pe.com.albert.features.app_rating.domain.repository.AppRatingRepository
import pe.com.albert.features.app_rating.domain.util.Result
import javax.inject.Inject

class GetAppStatisticUseCase @Inject constructor(
    private val appRatingRepository: AppRatingRepository
) {
    suspend operator fun invoke(idApplication: String): Property? {
        return when (val properties = appRatingRepository.getAppStatistic(idApplication)) {
            is Result.Success -> {
                calcularPropiedadRepresentativa(properties.data).copy(cantidadEncuentados = properties.data.size)
            }

            is Result.Error -> {
                null
            }
        }
    }


    private fun calcularPropiedadRepresentativa(listaPropiedades: List<Property>): Property {
        // Promedio de uso de CPU
        val promedioCpuResources =
            listaPropiedades.map { it.cpuResources?.toDouble() ?: 0.0 }.average().toFloat()

        // Promedio de uso de memoria
        val promedioMemoryResources =
            listaPropiedades.map { it.memoryResources?.toDouble() ?: 0.0 }.average().toFloat()

        // Promedio de frecuencia de uso
        val promedioFrequencyUse =
            listaPropiedades.map { it.frequencyUse?.toDouble() ?: 0.0 }.average().toInt()

        // Determinación de redundancia (más del 50% de los casos debe ser true)
        val esRedundante = listaPropiedades.count { it.isRedundant } > listaPropiedades.size / 2

        // Determinación de obsolescencia (más del 50% de los casos debe ser true)
        val esObsoleto = listaPropiedades.count { it.isObsolete } > listaPropiedades.size / 2

        // Fecha más reciente (suponemos que las fechas están en formato ISO o comparable)
        val fechaMasReciente =
            listaPropiedades.maxByOrNull { it.lastUpdate?.toDouble() ?: 0.0 }?.lastUpdate ?: ""

        // Devolvemos la propiedad representativa
        return Property(
            cpuResources = promedioCpuResources.toString(),
            memoryResources = promedioMemoryResources.toString(),
            frequencyUse = promedioFrequencyUse.toString(),
            isRedundant = esRedundante,
            isObsolete = esObsoleto,
            lastUpdate = fechaMasReciente,
            idApplication = listaPropiedades.first().idApplication,
            id = ""
        )
    }


}