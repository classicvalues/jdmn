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
package com.gs.dmn.signavio.transformation;

import com.gs.dmn.DMNModelRepository;
import com.gs.dmn.log.BuildLogger;
import com.gs.dmn.signavio.SignavioDMNModelRepository;
import com.gs.dmn.signavio.testlab.InputParameterDefinition;
import org.omg.spec.dmn._20191111.model.TInputData;

public class MergeInputDataByLabelAndDiagramIdTransformer extends AbstractMergeInputDataTransformer {
    public MergeInputDataByLabelAndDiagramIdTransformer() {
        super();
    }

    public MergeInputDataByLabelAndDiagramIdTransformer(BuildLogger logger) {
        super(logger);
    }

    @Override
    protected String equivalenceKey(TInputData inputData, DMNModelRepository dmnModelRepository) {
        return String.format("%s-%s", diagramId(inputData, dmnModelRepository), inputData.getLabel().trim());
    }

    @Override
    protected String equivalenceKey(InputParameterDefinition parameter) {
        // requirement name is the label of corresponding InputData
        return String.format("%s-%s", parameter.getDiagramId(), parameter.getRequirementName().trim());
    }

    private String diagramId(TInputData inputData, DMNModelRepository repository) {
        SignavioDMNModelRepository signavioRepository = (SignavioDMNModelRepository) repository;
        return signavioRepository.getDiagramId(inputData);
    }
}
