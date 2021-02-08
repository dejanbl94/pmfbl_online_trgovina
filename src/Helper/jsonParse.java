package Helper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class jsonParse {
	
	public static <T> T readJSONToList(String json, TypeReference<T> typeRef) {
		T t = null;
		try {
			MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
			t = MAPPER.readValue(json, typeRef);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static <T> String parseAndWrite(List<T> listToParse, String outputFile) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		String json = objectMapper.writeValueAsString(listToParse);

		// Create output file.
		StringBuilder filePath = new StringBuilder();
		filePath.append("src/resources/").append(outputFile).append(".json");
		try {
			objectMapper.writeValue(Paths.get(filePath.toString()).toFile(), listToParse);

			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

}
