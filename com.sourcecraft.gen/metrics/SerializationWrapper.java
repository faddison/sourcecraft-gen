package metrics;

public class SerializationWrapper<T extends AbstractMetrics>
{
	public MetricsSerializer<T> serializer = new MetricsSerializer<T>();
	public MetricsDeserializer<T> deserializer = new MetricsDeserializer<T>();
}
