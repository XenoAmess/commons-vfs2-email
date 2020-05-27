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

import javax.activation.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileObjectDataSource implements DataSource {
    /**
     * @see DataSource#getContentType
     */
    public static final String DEFAULT_MIME_TYPE = "application/octet-stream";

    private final FileObject fileObject;

    public FileObjectDataSource(FileObject fileObject) {
        this.fileObject = fileObject;
    }

    public FileObjectDataSource(String name) throws FileSystemException {
        this(VFS.getManager().resolveFile(name));
    }

    public static FileObjectDataSource of(FileObject fileObject) {
        return new FileObjectDataSource(fileObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return getFileObject().getContent().getInputStream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OutputStream getOutputStream() throws IOException {
        return getFileObject().getContent().getOutputStream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContentType() {
        String result = null;
        try {
            result = getFileObject().getContent().getContentInfo().getContentType();
        } catch (FileSystemException e) {
            result = DEFAULT_MIME_TYPE;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return getFileObject().getName().getBaseName();
    }

    public FileObject getFileObject() {
        return fileObject;
    }
}
