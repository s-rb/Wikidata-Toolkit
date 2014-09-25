package org.wikidata.wdtk.datamodel.json.jackson;

/*
 * #%L
 * Wikidata Toolkit Data Model
 * %%
 * Copyright (C) 2014 Wikidata Toolkit Developers
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class TestMonolingualTextValue extends JsonConversionTest {

	/**
	 * Tests the conversion of MonolingualTextValues from JSON to POJO
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@Test
	public void testMonolingualTextValueToJava() throws JsonParseException,
			JsonMappingException, IOException {
		JacksonMonolingualTextValue result = mapper.readValue(mltvJson,
				JacksonMonolingualTextValue.class);

		assertEquals("en", result.getLanguageCode());
		assertEquals("foobar", result.getText());
	}

	/**
	 * Tests the conversion of MonolingualTextValues from POJO to JSON
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	public void testMonolingualTextValueToJson() throws JsonProcessingException {
		String result = mapper.writeValueAsString(testMltv);
		JsonComparator.compareJsonStrings(mltvJson, result);
	}

	@Test
	public void testEquals() {
		JacksonMonolingualTextValue match = new JacksonMonolingualTextValue(
				"en", "foobar");
		JacksonMonolingualTextValue wrongLanguage = new JacksonMonolingualTextValue(
				"de", "foobar");
		JacksonMonolingualTextValue wrongValue = new JacksonMonolingualTextValue(
				"en", "barfoo");

		assertEquals(testMltv, testMltv);
		assertEquals(testMltv, match);
		assertFalse(testMltv.equals(wrongLanguage));
		assertFalse(testMltv.equals(wrongValue));
	}
}
