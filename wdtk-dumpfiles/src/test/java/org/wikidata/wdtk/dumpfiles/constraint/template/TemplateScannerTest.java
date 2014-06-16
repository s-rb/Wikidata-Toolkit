package org.wikidata.wdtk.dumpfiles.constraint.template;

/*
 * #%L
 * Wikidata Toolkit Dump File Handling
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

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link TemplateScanner}.
 * 
 * @author Julian Mendez
 * 
 */
public class TemplateScannerTest {

	static final String text = "\n<!-- ignored -->{{Constraint:Type|class=Q1048835\n|relation=instance<!-- ignored -->}}"
			+ "\n{{Constraint:Value <!-- ignored \n \n -->type|class=Q5|relation<!---->=instance}}<!-- nothing here -->"
			+ "\n{<!--ignored --->{Constraint:Target required claim|property=P21}}"
			+ "\n{{Constraint:One of|values=<!-- ignored-->{{Q|6581097}}, {{Q|6581072}}, {{Q|1097630}}, {{Q|44148}}, {{Q|43445}}, {{Q|1052281}}, {{Q|2449503}}, {{Q|48270}}, {{Q|1399232}}, {{Q|3277905}}, {{Q|746411}}, {{Q|350374}}, {{Q|660882}}}}"
			+ "\n";

	@Test
	public void testScanner() {
		List<String> expected = new ArrayList<String>();
		expected.add("{{Constraint:Type|class=Q1048835\n|relation=instance}}");
		expected.add("{{Constraint:Value type|class=Q5|relation=instance}}");
		expected.add("{{Constraint:Target required claim|property=P21}}");
		expected.add("{{Constraint:One of|values={{Q|6581097}}, {{Q|6581072}}, {{Q|1097630}}, {{Q|44148}}, {{Q|43445}}, {{Q|1052281}}, {{Q|2449503}}, {{Q|48270}}, {{Q|1399232}}, {{Q|3277905}}, {{Q|746411}}, {{Q|350374}}, {{Q|660882}}}}");
		TemplateScanner scanner = new TemplateScanner();
		Assert.assertEquals(expected, scanner.getTemplates(text));
	}

}
