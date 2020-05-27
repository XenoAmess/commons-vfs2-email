/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.vfs2.email;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileObjectDataSourceTest {
    @Test
    public void contentTypeTest() throws Exception {
        FileObject baseObject = VFS.getManager().resolveFile(System.getProperty("user.dir"));
        testSingle(baseObject, "1.bmp", "image/bmp");
        testSingle(baseObject, "1.gif", "image/gif");
        testSingle(baseObject, "1.jpg", "image/jpeg");
        testSingle(baseObject, "1.png", "image/png");
        testSingle(baseObject, "1.txt", "text/plain");
    }

    public void testSingle(FileObject baseObject, String filename, String expectedType) throws FileSystemException {
        FileObject fileObject = baseObject.resolveFile("test/resources/" + filename);
        FileObjectDataSource fileObjectDataSource = FileObjectDataSource.of(fileObject);
        assertEquals(expectedType, fileObjectDataSource.getContentType());
        assertEquals(filename, fileObjectDataSource.getName());
    }
}