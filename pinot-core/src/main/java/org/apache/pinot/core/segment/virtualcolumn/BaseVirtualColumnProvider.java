/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.core.segment.virtualcolumn;

import org.apache.pinot.core.segment.index.ColumnMetadata;
import org.apache.pinot.core.segment.index.column.ColumnIndexContainer;
import org.apache.pinot.spi.data.FieldSpec;


/**
 * Shared implementation code between virtual column providers.
 */
public abstract class BaseVirtualColumnProvider implements VirtualColumnProvider {

  protected ColumnMetadata.Builder getColumnMetadataBuilder(VirtualColumnContext context) {
    FieldSpec fieldSpec = context.getFieldSpec();
    return new ColumnMetadata.Builder().setVirtual(true).setColumnName(fieldSpec.getName())
        .setFieldType(fieldSpec.getFieldType()).setDataType(fieldSpec.getDataType())
        .setTotalDocs(context.getTotalDocCount());
  }

  @Override
  public ColumnIndexContainer buildColumnIndexContainer(VirtualColumnContext context) {
    return new VirtualColumnIndexContainer(buildReader(context), buildInvertedIndex(context), buildDictionary(context));
  }
}
