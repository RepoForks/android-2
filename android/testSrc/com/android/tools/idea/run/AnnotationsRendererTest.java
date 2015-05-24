/*
 * Copyright (C) 2015 The Android Open Source Project
 *
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
 */
package com.android.tools.idea.run;

import com.android.tools.lint.checks.SupportAnnotationDetector;
import com.intellij.psi.PsiAnnotation;
import org.jetbrains.android.AndroidTestCase;
import org.jetbrains.annotations.NotNull;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnnotationsRendererTest extends AndroidTestCase {
  public void testColorIntRendering() {
    AnnotationsRenderer.Result result =
      AnnotationsRenderer.render(getProject(), createFakeAnnotation(SupportAnnotationDetector.COLOR_INT_ANNOTATION), 0x112233);
    assertEquals("0x00112233 {a=255 r=17 g=34 b=51}", result.label);
    assertNotNull(result.icon);

    result = AnnotationsRenderer.render(getProject(), createFakeAnnotation(SupportAnnotationDetector.COLOR_INT_ANNOTATION), 0x55112233);
    assertEquals("0x55112233 {a=85 r=17 g=34 b=51}", result.label);
    assertNotNull(result.icon);
  }

  public void testResourceRefRendering() {
    AnnotationsRenderer.Result result =
      AnnotationsRenderer.render(getProject(), createFakeAnnotation(SupportAnnotationDetector.RES_SUFFIX), 0x01080074);
    assertEquals("0x01080074 {@android:drawable/star_on}", result.label);
    assertNull(result.icon);

    result =
      AnnotationsRenderer.render(getProject(), createFakeAnnotation(SupportAnnotationDetector.RES_SUFFIX), 0xf1080074);
    assertEquals("0xf1080074 {@Res ?}", result.label);
  }

  @NotNull
  private static PsiAnnotation createFakeAnnotation(String fqcn) {
    PsiAnnotation annotation = mock(PsiAnnotation.class);
    when(annotation.getQualifiedName()).thenReturn(fqcn);
    return annotation;
  }
}
