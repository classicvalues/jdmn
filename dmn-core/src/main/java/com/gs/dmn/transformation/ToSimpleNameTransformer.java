/*
 * Copyright 2016 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.gs.dmn.transformation;

import com.gs.dmn.DMNModelRepository;
import com.gs.dmn.NameUtils;
import com.gs.dmn.ast.TDefinitions;
import com.gs.dmn.log.BuildLogger;
import com.gs.dmn.log.NopBuildLogger;
import com.gs.dmn.serialization.DMNConstants;
import com.gs.dmn.serialization.DMNSerializer;
import com.gs.dmn.serialization.xstream.XMLDMNSerializer;
import com.gs.dmn.tck.TCKSerializer;
import com.gs.dmn.tck.ast.TestCases;
import com.gs.dmn.tck.serialization.xstream.XMLTCKSerializer;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

public class ToSimpleNameTransformer extends NameTransformer {
    private int counter = 0;
    private final NameMappings namesMapping = new NameMappings();

    public ToSimpleNameTransformer() {
        super();
    }

    public ToSimpleNameTransformer(BuildLogger logger) {
        super(logger);
    }

    @Override
    public String transformName(String oldName) {
        if (StringUtils.isEmpty(oldName)) {
            return oldName;
        } else if (NameUtils.isSimpleName(oldName)) {
            return oldName;
        } else {
            String newName = this.namesMapping.get(oldName);
            if (newName == null) {
                newName = toSimpleName(oldName);
                if (!newName.equals(oldName)) {
                    // Check for duplicates
                    boolean isDuplicate = false;
                    for (String key: this.namesMapping.keys()) {
                        if (!key.equals(oldName) && newName.equals(this.namesMapping.get(key))) {
                            isDuplicate = true;
                            break;
                        }
                    }
                    if (isDuplicate) {
                        newName = newName + "_" + ++this.counter;
                    }
                    this.namesMapping.put(oldName, newName);
                }
            }

            return newName;
        }
    }

    private String toSimpleName(String name) {
        if (StringUtils.isEmpty(name)) {
            return name;
        }
        StringBuilder result = new StringBuilder();
        boolean skippedPrevious = false;
        for (int ch: name.codePoints().toArray()) {
            if (Character.isJavaIdentifierPart(ch)) {
                if (skippedPrevious) {
                    ch = Character.toUpperCase(ch);
                }
                result.append((char) ch);
                skippedPrevious = false;
            } else {
                skippedPrevious = true;
            }
        }
        String newName = result.toString();
        return newName.isEmpty() ? "_" : newName;
    }

    public static void main(String[] args) {
        NopBuildLogger logger = new NopBuildLogger();

        File inputFolder = new File("H:/Projects/EP/dmn/dmn-core/src/test/resources/tck/cl2/input/");
        File outputFolder = new File("H:/Projects/EP/dmn/dmn-tck-integration-tests/src/main/resources/tck/cl2/");

        for (File child: inputFolder.listFiles()) {
            if (DMNSerializer.isDMNFile(child)) {
                NameTransformer simpleNameTransformer = new ToSimpleNameTransformer(new NopBuildLogger());

                // Clean DMN
                String dmnFileName = child.getName();
                File inputFile = new File(inputFolder, dmnFileName);
                File outputFile = new File(outputFolder, dmnFileName);
                DMNModelRepository repository = transformDefinitions(simpleNameTransformer, inputFile, outputFile, logger);

                // Clean Test
                String testFileName = dmnFileName.replace(DMNConstants.DMN_FILE_EXTENSION, "-test-01" + TCKSerializer.DEFAULT_TEST_CASE_FILE_EXTENSION);
                File inputTestFile = new File(inputFolder, testFileName);
                if (inputTestFile.exists()) {
                    File outputTestFile = new File(outputFolder, testFileName);
                    transformTestCases(simpleNameTransformer, repository, inputTestFile, outputTestFile, logger);
                }
            }
        }
    }

    private static DMNModelRepository transformDefinitions(NameTransformer transformer, File inputFile, File outputFile, BuildLogger logger) {
        // Read
        DMNSerializer serializer = new XMLDMNSerializer(logger, false);
        TDefinitions result = serializer.readModel(inputFile);
        DMNModelRepository repository = new DMNModelRepository(result);

        // Transform
        transformer.transform(repository);

        // Write
        serializer.writeModel(repository.getRootDefinitions(), outputFile);

        return repository;
    }

    private static void transformTestCases(NameTransformer transformer, DMNModelRepository repository, File inputFile, File outputFile, BuildLogger logger) {
        // Read
        TCKSerializer tckSerializer = new XMLTCKSerializer(logger, true);
        TestCases testCases = tckSerializer.read(inputFile);

        // Clean
        transformer.transform(repository, Arrays.asList(testCases));

        // Write
        tckSerializer.write(testCases, outputFile);
    }

}

class NameMappings {
    private final Map<String, String> mappings = new LinkedHashMap<>();
    private List<String> orderedKeys;

    public String get(String name) {
        return this.mappings.get(name);
    }

    public void put(String key, String value) {
        this.mappings.put(key, value);
    }

    public List<String> keys() {
        return new ArrayList<>(this.mappings.keySet());
    }

    public List<String> values() {
        return new ArrayList<>(this.mappings.values());
    }
}